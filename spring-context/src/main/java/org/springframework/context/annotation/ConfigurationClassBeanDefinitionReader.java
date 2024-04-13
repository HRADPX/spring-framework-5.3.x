/*
 * Copyright 2002-2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.context.annotation;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.BeanDefinitionStoreException;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.annotation.AnnotatedGenericBeanDefinition;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.groovy.GroovyBeanDefinitionReader;
import org.springframework.beans.factory.parsing.SourceExtractor;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.AbstractBeanDefinitionReader;
import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.ConfigurationCondition.ConfigurationPhase;
import org.springframework.core.SpringProperties;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.MethodMetadata;
import org.springframework.core.type.StandardAnnotationMetadata;
import org.springframework.core.type.StandardMethodMetadata;
import org.springframework.lang.NonNull;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

/**
 * Reads a given fully-populated set of ConfigurationClass instances, registering bean
 * definitions with the given {@link BeanDefinitionRegistry} based on its contents.
 *
 * <p>This class was modeled after the {@link BeanDefinitionReader} hierarchy, but does
 * not implement/extend any of its artifacts as a set of configuration classes is not a
 * {@link Resource}.
 *
 * @author Chris Beams
 * @author Juergen Hoeller
 * @author Phillip Webb
 * @author Sam Brannen
 * @author Sebastien Deleuze
 * @since 3.0
 * @see ConfigurationClassParser
 */
class ConfigurationClassBeanDefinitionReader {

	private static final Log logger = LogFactory.getLog(ConfigurationClassBeanDefinitionReader.class);

	private static final ScopeMetadataResolver scopeMetadataResolver = new AnnotationScopeMetadataResolver();

	/**
	 * Boolean flag controlled by a {@code spring.xml.ignore} system property that instructs Spring to
	 * ignore XML, i.e. to not initialize the XML-related infrastructure.
	 * <p>The default is "false".
	 */
	private static final boolean shouldIgnoreXml = SpringProperties.getFlag("spring.xml.ignore");

	private final BeanDefinitionRegistry registry;

	private final SourceExtractor sourceExtractor;

	private final ResourceLoader resourceLoader;

	private final Environment environment;

	private final BeanNameGenerator importBeanNameGenerator;

	private final ImportRegistry importRegistry;

	private final ConditionEvaluator conditionEvaluator;


	/**
	 * Create a new {@link ConfigurationClassBeanDefinitionReader} instance
	 * that will be used to populate the given {@link BeanDefinitionRegistry}.
	 */
	ConfigurationClassBeanDefinitionReader(BeanDefinitionRegistry registry, SourceExtractor sourceExtractor,
			ResourceLoader resourceLoader, Environment environment, BeanNameGenerator importBeanNameGenerator,
			ImportRegistry importRegistry) {

		this.registry = registry;
		this.sourceExtractor = sourceExtractor;
		this.resourceLoader = resourceLoader;
		this.environment = environment;
		this.importBeanNameGenerator = importBeanNameGenerator;
		this.importRegistry = importRegistry;
		this.conditionEvaluator = new ConditionEvaluator(registry, environment, resourceLoader);
	}


	/**
	 * Read {@code configurationModel}, registering bean definitions
	 * with the registry based on its contents.
	 */
	public void loadBeanDefinitions(Set<ConfigurationClass> configurationModel) {
		TrackedConditionEvaluator trackedConditionEvaluator = new TrackedConditionEvaluator();
		for (ConfigurationClass configClass : configurationModel) {
			loadBeanDefinitionsForConfigurationClass(configClass, trackedConditionEvaluator);
		}
	}

	/**
	 * Read a particular {@link ConfigurationClass}, registering bean definitions
	 * for the class itself and all of its {@link Bean} methods.
	 */
	private void loadBeanDefinitionsForConfigurationClass(
			ConfigurationClass configClass, TrackedConditionEvaluator trackedConditionEvaluator) {

		if (trackedConditionEvaluator.shouldSkip(configClass)) {
			String beanName = configClass.getBeanName();
			if (StringUtils.hasLength(beanName) && this.registry.containsBeanDefinition(beanName)) {
				this.registry.removeBeanDefinition(beanName);
			}
			this.importRegistry.removeImportingClass(configClass.getMetadata().getClassName());
			return;
		}

		if (configClass.isImported()) {
			// 这里将 @Import 注解导入的 bean 注册到 beanDefinitionMap 中
			registerBeanDefinitionForImportedConfigurationClass(configClass);
		}
		// 处理 @Bean 注解，将对应的 BeanDefinition 注册到 BeanDefinitionMap 中
		for (BeanMethod beanMethod : configClass.getBeanMethods()) {
			loadBeanDefinitionsForBeanMethod(beanMethod);
		}

		// 处理 @ImportResource 注解
		loadBeanDefinitionsFromImportedResources(configClass.getImportedResources());
		// 处理 @Import ImportRegistrarBeanDefinition 接口，调用接口方法
		loadBeanDefinitionsFromRegistrars(configClass.getImportBeanDefinitionRegistrars());
	}

	/**
	 * Register the {@link Configuration} class itself as a bean definition.
	 */
	private void registerBeanDefinitionForImportedConfigurationClass(ConfigurationClass configClass) {
		AnnotationMetadata metadata = configClass.getMetadata();
		AnnotatedGenericBeanDefinition configBeanDef = new AnnotatedGenericBeanDefinition(metadata);

		ScopeMetadata scopeMetadata = scopeMetadataResolver.resolveScopeMetadata(configBeanDef);
		configBeanDef.setScope(scopeMetadata.getScopeName());
		String configBeanName = this.importBeanNameGenerator.generateBeanName(configBeanDef, this.registry);
		AnnotationConfigUtils.processCommonDefinitionAnnotations(configBeanDef, metadata);

		BeanDefinitionHolder definitionHolder = new BeanDefinitionHolder(configBeanDef, configBeanName);
		definitionHolder = AnnotationConfigUtils.applyScopedProxyMode(scopeMetadata, definitionHolder, this.registry);
		this.registry.registerBeanDefinition(definitionHolder.getBeanName(), definitionHolder.getBeanDefinition());
		configClass.setBeanName(configBeanName);

		if (logger.isTraceEnabled()) {
			logger.trace("Registered bean definition for imported class '" + configBeanName + "'");
		}
	}

	/**
	 * Read the given {@link BeanMethod}, registering bean definitions
	 * with the BeanDefinitionRegistry based on its contents.
	 */
	@SuppressWarnings("deprecation")  // for RequiredAnnotationBeanPostProcessor.SKIP_REQUIRED_CHECK_ATTRIBUTE
	private void loadBeanDefinitionsForBeanMethod(BeanMethod beanMethod) {
		ConfigurationClass configClass = beanMethod.getConfigurationClass();
		MethodMetadata metadata = beanMethod.getMetadata();
		String methodName = metadata.getMethodName();

		// Do we need to mark the bean as skipped by its condition?
		// 判断 Bean 方法是否存在 @Conditional 注解，如果存在判断是否满足条件
		if (this.conditionEvaluator.shouldSkip(metadata, ConfigurationPhase.REGISTER_BEAN)) {
			// 需要跳过，加入到需要跳过的集合中
			configClass.skippedBeanMethods.add(methodName);
			return;
		}
		if (configClass.skippedBeanMethods.contains(methodName)) {
			return;
		}

		AnnotationAttributes bean = AnnotationConfigUtils.attributesFor(metadata, Bean.class);
		Assert.state(bean != null, "No @Bean annotation attributes");

		// Consider name and any aliases
		// 别名
		List<String> names = new ArrayList<>(Arrays.asList(bean.getStringArray("name")));
		String beanName = (!names.isEmpty() ? names.remove(0) : methodName);

		// Register aliases even when overridden
		for (String alias : names) {
			this.registry.registerAlias(beanName, alias);
		}

		// Has this effectively been overridden before (e.g. via XML)?
		// 判断当前 beanName 对应的 beanDefinition 是否被覆盖
		// 如果这个方法返回 true，表示这个 beanName 对应的 beanDefinition 已经存在，当前的被先前的覆盖
		if (isOverriddenByExistingDefinition(beanMethod, beanName)) {
			if (beanName.equals(beanMethod.getConfigurationClass().getBeanName())) {
				throw new BeanDefinitionStoreException(beanMethod.getConfigurationClass().getResource().getDescription(),
						beanName, "Bean name derived from @Bean method '" + beanMethod.getMetadata().getMethodName() +
						"' clashes with bean name for containing configuration class; please make those names unique!");
			}
			return;
		}

		ConfigurationClassBeanDefinition beanDef = new ConfigurationClassBeanDefinition(configClass, metadata, beanName);
		beanDef.setSource(this.sourceExtractor.extractSource(metadata, configClass.getResource()));

		/** static @Bean */
		if (metadata.isStatic()) {
			// static @Bean method
			if (configClass.getMetadata() instanceof StandardAnnotationMetadata) {
				beanDef.setBeanClass(((StandardAnnotationMetadata) configClass.getMetadata()).getIntrospectedClass());
			}
			else {
				beanDef.setBeanClassName(configClass.getMetadata().getClassName());
			}
			// 设置不可覆盖
			beanDef.setUniqueFactoryMethodName(methodName);
		}
		/** normal @Bean */
		else {
			// instance @Bean method
			// 和 static Bean 的区别在于: 普通的 @Bean 的 BeanDefinition 中存在 factoryBeanName 属性
			beanDef.setFactoryBeanName(configClass.getBeanName());
			beanDef.setUniqueFactoryMethodName(methodName);
		}

		if (metadata instanceof StandardMethodMetadata) {
			beanDef.setResolvedFactoryMethod(((StandardMethodMetadata) metadata).getIntrospectedMethod());
		}

		///// 下面这些是为 beanDefinition 设置相应的属性 /////
		// @Bean 的自动注入类型默认为构造注入
		beanDef.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_CONSTRUCTOR);
		beanDef.setAttribute(org.springframework.beans.factory.annotation.RequiredAnnotationBeanPostProcessor.
				SKIP_REQUIRED_CHECK_ATTRIBUTE, Boolean.TRUE);

		AnnotationConfigUtils.processCommonDefinitionAnnotations(beanDef, metadata);

		Autowire autowire = bean.getEnum("autowire");
		if (autowire.isAutowire()) {
			beanDef.setAutowireMode(autowire.value());
		}

		boolean autowireCandidate = bean.getBoolean("autowireCandidate");
		if (!autowireCandidate) {
			beanDef.setAutowireCandidate(false);
		}

		String initMethodName = bean.getString("initMethod");
		if (StringUtils.hasText(initMethodName)) {
			beanDef.setInitMethodName(initMethodName);
		}

		String destroyMethodName = bean.getString("destroyMethod");
		beanDef.setDestroyMethodName(destroyMethodName);

		// Consider scoping
		ScopedProxyMode proxyMode = ScopedProxyMode.NO;
		AnnotationAttributes attributes = AnnotationConfigUtils.attributesFor(metadata, Scope.class);
		if (attributes != null) {
			beanDef.setScope(attributes.getString("value"));
			proxyMode = attributes.getEnum("proxyMode");
			if (proxyMode == ScopedProxyMode.DEFAULT) {
				proxyMode = ScopedProxyMode.NO;
			}
		}

		// Replace the original bean definition with the target one, if necessary
		// 是否生成代理
		BeanDefinition beanDefToRegister = beanDef;
		if (proxyMode != ScopedProxyMode.NO) {
			BeanDefinitionHolder proxyDef = ScopedProxyCreator.createScopedProxy(
					new BeanDefinitionHolder(beanDef, beanName), this.registry,
					proxyMode == ScopedProxyMode.TARGET_CLASS);
			beanDefToRegister = new ConfigurationClassBeanDefinition(
					(RootBeanDefinition) proxyDef.getBeanDefinition(), configClass, metadata, beanName);
		}

		if (logger.isTraceEnabled()) {
			logger.trace(String.format("Registering bean definition for @Bean method %s.%s()",
					configClass.getMetadata().getClassName(), beanName));
		}
		this.registry.registerBeanDefinition(beanName, beanDefToRegister);
	}

	/**
	 * 该方法是判断给定 beanMethod 与 beanName 是否可被容器中相同名称的 beanDefinition 覆盖
	 * 1) 如果存在 beanDefinition 也是由不同 configuration class 创建，则允许当前的 beanMethod
	 * 覆盖原先的，因为它们都是 second-pass 级别的。
	 * 2) 如果存在的 beanDefinition 和当前的是同一个 config class 创建，即重载，则保留之前存在的
	 * beanDefinition
	 * 3) 如果存在且是通过包扫描解析得到的 beanDefinition，可被后者覆盖
	 * 4) 如果后者的 role 比默认值大，可被后者覆盖
	 * 5) 全局判断，通过属性设置
	 */
	protected boolean isOverriddenByExistingDefinition(BeanMethod beanMethod, String beanName) {
		if (!this.registry.containsBeanDefinition(beanName)) {
			return false;
		}
		// 存在相同 beanName 的两个 bean
		BeanDefinition existingBeanDef = this.registry.getBeanDefinition(beanName);

		// Is the existing bean definition one that was created from a configuration class?
		// -> allow the current bean method to override, since both are at second-pass level.
		// However, if the bean method is an overloaded case on the same configuration class,
		// preserve the existing bean definition.
		if (existingBeanDef instanceof ConfigurationClassBeanDefinition) {
			ConfigurationClassBeanDefinition ccbd = (ConfigurationClassBeanDefinition) existingBeanDef;
			// 判断是否是同一个配置类，如果不是同一个配置类的两个 @Bean，允许后者覆盖前者，但是需要允许 Bean 覆盖，否则在后续
			// 注册到 beanDefinitionMap 中会抛异常。
			if (ccbd.getMetadata().getClassName().equals(
					beanMethod.getConfigurationClass().getMetadata().getClassName())) {
				// 多个id相同的 @Bean，如果是遇到第一个相同 id 的 Bean，设置 isFactoryMethodUnique = false
				if (ccbd.getFactoryMethodMetadata().getMethodName().equals(ccbd.getFactoryMethodName())) {
					ccbd.setNonUniqueFactoryMethodName(ccbd.getFactoryMethodMetadata().getMethodName());
				}
				return true;
			}
			// 不同同一个类，允许后者覆盖前者
			else {
				return false;
			}
		}

		// A bean definition resulting from a component scan can be silently overridden
		// by an @Bean method, as of 4.2...
		// 通过包扫描产生的 beanDefinition 都是 ScannedGenericBeanDefinition，如果是通过 component scan
		// 产生的 beanDefinition 和当前的 beanMethod 产生冲突，则默认被覆盖
		if (existingBeanDef instanceof ScannedGenericBeanDefinition) {
			return false;
		}

		// Has the existing bean definition bean marked as a framework-generated bean?
		// -> allow the current bean method to override it, since it is application-level
		// 判断已经存在的级别是否高于 ROLE_APPLICATION，如果是可被覆盖
		if (existingBeanDef.getRole() > BeanDefinition.ROLE_APPLICATION) {
			return false;
		}

		// At this point, it's a top-level override (probably XML), just having been parsed
		// before configuration class processing kicks in...
		// id相同的 Bean 不是全部通过 @Bean 注入，判断 bean 是否可被覆盖
		if (this.registry instanceof DefaultListableBeanFactory &&
				!((DefaultListableBeanFactory) this.registry).isAllowBeanDefinitionOverriding()) {
			throw new BeanDefinitionStoreException(beanMethod.getConfigurationClass().getResource().getDescription(),
					beanName, "@Bean definition illegally overridden by existing bean definition: " + existingBeanDef);
		}
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("Skipping bean definition for %s: a definition for bean '%s' " +
					"already exists. This top-level bean definition is considered as an override.",
					beanMethod, beanName));
		}
		return true;
	}

	private void loadBeanDefinitionsFromImportedResources(
			Map<String, Class<? extends BeanDefinitionReader>> importedResources) {

		Map<Class<?>, BeanDefinitionReader> readerInstanceCache = new HashMap<>();

		importedResources.forEach((resource, readerClass) -> {
			// Default reader selection necessary?
			if (BeanDefinitionReader.class == readerClass) {
				if (StringUtils.endsWithIgnoreCase(resource, ".groovy")) {
					// When clearly asking for Groovy, that's what they'll get...
					readerClass = GroovyBeanDefinitionReader.class;
				}
				else if (shouldIgnoreXml) {
					throw new UnsupportedOperationException("XML support disabled");
				}
				else {
					// Primarily ".xml" files but for any other extension as well
					readerClass = XmlBeanDefinitionReader.class;
				}
			}

			BeanDefinitionReader reader = readerInstanceCache.get(readerClass);
			if (reader == null) {
				try {
					// Instantiate the specified BeanDefinitionReader
					reader = readerClass.getConstructor(BeanDefinitionRegistry.class).newInstance(this.registry);
					// Delegate the current ResourceLoader to it if possible
					if (reader instanceof AbstractBeanDefinitionReader) {
						AbstractBeanDefinitionReader abdr = ((AbstractBeanDefinitionReader) reader);
						abdr.setResourceLoader(this.resourceLoader);
						abdr.setEnvironment(this.environment);
					}
					readerInstanceCache.put(readerClass, reader);
				}
				catch (Throwable ex) {
					throw new IllegalStateException(
							"Could not instantiate BeanDefinitionReader class [" + readerClass.getName() + "]");
				}
			}

			// TODO SPR-6310: qualify relative path locations as done in AbstractContextLoader.modifyLocations
			reader.loadBeanDefinitions(resource);
		});
	}

	private void loadBeanDefinitionsFromRegistrars(Map<ImportBeanDefinitionRegistrar, AnnotationMetadata> registrars) {
		registrars.forEach((registrar, metadata) ->
				registrar.registerBeanDefinitions(metadata, this.registry, this.importBeanNameGenerator));
	}


	/**
	 * {@link RootBeanDefinition} marker subclass used to signify that a bean definition
	 * was created from a configuration class as opposed to any other configuration source.
	 * Used in bean overriding cases where it's necessary to determine whether the bean
	 * definition was created externally.
	 * 该类表示 @Bean 注解注册的 Bean
	 */
	@SuppressWarnings("serial")
	private static class ConfigurationClassBeanDefinition extends RootBeanDefinition implements AnnotatedBeanDefinition {

		private final AnnotationMetadata annotationMetadata;

		private final MethodMetadata factoryMethodMetadata;

		private final String derivedBeanName;

		public ConfigurationClassBeanDefinition(
				ConfigurationClass configClass, MethodMetadata beanMethodMetadata, String derivedBeanName) {

			this.annotationMetadata = configClass.getMetadata();
			this.factoryMethodMetadata = beanMethodMetadata;
			this.derivedBeanName = derivedBeanName;
			setResource(configClass.getResource());
			setLenientConstructorResolution(false);
		}

		public ConfigurationClassBeanDefinition(RootBeanDefinition original,
				ConfigurationClass configClass, MethodMetadata beanMethodMetadata, String derivedBeanName) {
			super(original);
			this.annotationMetadata = configClass.getMetadata();
			this.factoryMethodMetadata = beanMethodMetadata;
			this.derivedBeanName = derivedBeanName;
		}

		private ConfigurationClassBeanDefinition(ConfigurationClassBeanDefinition original) {
			super(original);
			this.annotationMetadata = original.annotationMetadata;
			this.factoryMethodMetadata = original.factoryMethodMetadata;
			this.derivedBeanName = original.derivedBeanName;
		}

		@Override
		public AnnotationMetadata getMetadata() {
			return this.annotationMetadata;
		}

		@Override
		@NonNull
		public MethodMetadata getFactoryMethodMetadata() {
			return this.factoryMethodMetadata;
		}

		@Override
		public boolean isFactoryMethod(Method candidate) {
			return (super.isFactoryMethod(candidate) && BeanAnnotationHelper.isBeanAnnotated(candidate) &&
					BeanAnnotationHelper.determineBeanNameFor(candidate).equals(this.derivedBeanName));
		}

		@Override
		public ConfigurationClassBeanDefinition cloneBeanDefinition() {
			return new ConfigurationClassBeanDefinition(this);
		}
	}


	/**
	 * Evaluate {@code @Conditional} annotations, tracking results and taking into
	 * account 'imported by'.
	 */
	private class TrackedConditionEvaluator {

		private final Map<ConfigurationClass, Boolean> skipped = new HashMap<>();

		public boolean shouldSkip(ConfigurationClass configClass) {
			Boolean skip = this.skipped.get(configClass);
			if (skip == null) {
				if (configClass.isImported()) {
					boolean allSkipped = true;
					for (ConfigurationClass importedBy : configClass.getImportedBy()) {
						if (!shouldSkip(importedBy)) {
							allSkipped = false;
							break;
						}
					}
					if (allSkipped) {
						// The config classes that imported this one were all skipped, therefore we are skipped...
						skip = true;
					}
				}
				if (skip == null) {
					skip = conditionEvaluator.shouldSkip(configClass.getMetadata(), ConfigurationPhase.REGISTER_BEAN);
				}
				this.skipped.put(configClass, skip);
			}
			return skip;
		}
	}

}

package config;

import com.hr.mode.BeanProxy;
import com.hr.mode.StaticBean;
import com.hr.mode.User;
import com.hr.selector.SpringImportSelector;
import com.hr.tx.UserMapper;
import com.hr.tx.UserTxService;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan(basePackages = "com.hr", excludeFilters = @ComponentScan.Filter(value = {UserTxService.class, UserMapper.class}, type = FilterType.ASSIGNABLE_TYPE))
@Import(SpringImportSelector.class)
public class ApplicationConfiguration {

	/**
	 * 如果配置类加了 @Configuration 注解，即当前的配置类是一个全注解类，
	 * 那么 Spring 会将这个配置类进行 cglib 代理，同时会为这个代理类添加
	 * 一个 BeanMethodInterceptor 用于拦截这个配置类中所有的 BeanMethod,
	 * 会判断当前执行的方法和调用当前方法的方法是否一样，如果一样，则按原方
	 * 法执行，如果不一样，那么直接从 BeanFactory 中获取即可。因为 Spring
	 * 需要维护配置类中 Bean 的作用域。其本质是维护配置类中 Bean 的单例。
	 *  注：
	 *   1) 以上的只对非静态方法有效，这个拦截器不会拦截静态方法。
	 * 	 2) 如下面的 user(),当进入 user() 方法时，当前执行的方法和调用当
	 * 前方法的方法都是 user()；而当 beanProxy() 方法中的 user() 方法被调用时，
	 * 那么当前执行的方法是 beanProxy(),而调用当前方法的方法是 user(),显然两个
	 * 方法就不一致了。
	 *   3) 对配置类进行代理是在 ConfigurationClassPostProcessor 扫描包
	 * 完成后执行的。
	 *   4) 对 @Bean 注解的方法是在 loadBeanDefinitions()方法中注册到
	 * BeanDefinitionMap 中的。
	 */

	/**
	 * 如果存在多个相同名称的 @Bean，Spring 处理逻辑
	 * @see org.springframework.context.annotation.ConfigurationClassBeanDefinitionReader#isOverriddenByExistingDefinition
	 */

	@Bean("user")
	public User user1() {
		return new User(2, "hello bean2");
	}

	@Bean
	public User user() {
		return new User(1, "hello bean1");
	}

	@Bean
	public static StaticBean staticBean() {
		return new StaticBean();
	}

	@Bean
	public BeanProxy beanProxy() {
		return new BeanProxy(1, "beanProxy", new User(1, "hello, beanProxy"));
	}

//	@Bean("beanProxy1")
//	public BeanProxy beanProxy(User user) {
//		return new BeanProxy(1, "beanProxy", user);
//	}

//	@Bean
//	public BeanFactoryPostProcessor beanFactoryPostProcessor() {
//		// 这里因为 BeanFactoryPostProcessor 实例化在其他 bean 之前，所以这里返回为 null
//		System.out.println(environment);
//		return beanFactory -> {
//			GenericBeanDefinition beanDefinition = (GenericBeanDefinition) beanFactory.getBeanDefinition("userService");
//			beanDefinition.setBeanClass(User.class);
//			beanDefinition.getConstructorArgumentValues().addGenericArgumentValue(1);
//			beanDefinition.getConstructorArgumentValues().addGenericArgumentValue("hello beanFactoryPostProcessor");
//		};
//	}
//
//	@Autowired
//	private Environment environment;

}

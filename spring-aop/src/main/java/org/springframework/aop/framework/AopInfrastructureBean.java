/*
 * Copyright 2002-2007 the original author or authors.
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

package org.springframework.aop.framework;

import org.springframework.aop.framework.autoproxy.AbstractAutoProxyCreator;
import org.springframework.beans.factory.BeanFactory;

/**
 * Marker interface that indicates a bean that is part of Spring's
 * AOP infrastructure. In particular, this implies that any such bean
 * is not subject to auto-proxying, even if a pointcut would match.
 * 标记接口，表示这个 bean 是 Spring AOP 基础组件接口
 * 实现这个接口的 bean 不受自动代理影响，即使它和切点匹配
 * 对 Scope 代理对象 ScopedProxyFactoryBean，在给目标 Bean 生成代理对象时，给其设置了该接口，
 * 表示生成的代理对象不会被 Spring 代理，即使它满足了切点
 * @see org.springframework.aop.scope.ScopedProxyFactoryBean#setBeanFactory(BeanFactory)
 * @see AbstractAutoProxyCreator#isInfrastructureClass(java.lang.Class)
 * @author Juergen Hoeller
 * @since 2.0.3
 * @see org.springframework.aop.framework.autoproxy.AbstractAutoProxyCreator
 * @see org.springframework.aop.scope.ScopedProxyFactoryBean
 */
public interface AopInfrastructureBean {

}

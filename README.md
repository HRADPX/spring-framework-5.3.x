# <img src="src/docs/spring-framework.png" width="80" height="80"> Spring Framework [![Build Status](https://ci.spring.io/api/v1/teams/spring-framework/pipelines/spring-framework-5.3.x/jobs/build/badge)](https://ci.spring.io/teams/spring-framework/pipelines/spring-framework-5.3.x?groups=Build") [![Revved up by Gradle Enterprise](https://img.shields.io/badge/Revved%20up%20by-Gradle%20Enterprise-06A0CE?logo=Gradle&labelColor=02303A)](https://ge.spring.io/scans?search.rootProjectNames=spring)

This is the home of the Spring Framework: the foundation for all [Spring projects](https://spring.io/projects). Collectively the Spring Framework and the family of Spring projects are often referred to simply as "Spring". 

Spring provides everything required beyond the Java programming language for creating enterprise applications for a wide range of scenarios and architectures. Please read the [Overview](https://docs.spring.io/spring/docs/current/spring-framework-reference/overview.html#spring-introduction) section as reference for a more complete introduction.

## Code of Conduct

This project is governed by the [Spring Code of Conduct](CODE_OF_CONDUCT.adoc). By participating, you are expected to uphold this code of conduct. Please report unacceptable behavior to spring-code-of-conduct@pivotal.io.

## Access to Binaries

For access to artifacts or a distribution zip, see the [Spring Framework Artifacts](https://github.com/spring-projects/spring-framework/wiki/Spring-Framework-Artifacts) wiki page.

## Documentation

The Spring Framework maintains reference documentation ([published](https://docs.spring.io/spring-framework/docs/current/spring-framework-reference/) and [source](src/docs/asciidoc)), Github [wiki pages](https://github.com/spring-projects/spring-framework/wiki), and an
[API reference](https://docs.spring.io/spring-framework/docs/current/javadoc-api/). There are also [guides and tutorials](https://spring.io/guides) across Spring projects.

## Micro-Benchmarks

See the [Micro-Benchmarks](https://github.com/spring-projects/spring-framework/wiki/Micro-Benchmarks) Wiki page.

## Build from Source

See the [Build from Source](https://github.com/spring-projects/spring-framework/wiki/Build-from-Source) Wiki page and the [CONTRIBUTING.md](CONTRIBUTING.md) file.

## Continuous Integration Builds

Information regarding CI builds can be found in the [Spring Framework Concourse pipeline](ci/README.adoc) documentation.

## Stay in Touch

Follow [@SpringCentral](https://twitter.com/springcentral), [@SpringFramework](https://twitter.com/springframework), and its [team members](https://twitter.com/springframework/lists/team/members) on Twitter. In-depth articles can be found at [The Spring Blog](https://spring.io/blog/), and releases are announced via our [news feed](https://spring.io/blog/category/news).

## License

The Spring Framework is released under version 2.0 of the [Apache License](https://www.apache.org/licenses/LICENSE-2.0).


1. Spring 二级缓存的作用
	为了保证 Spring Bean 的单例，在循环依赖的代理场景下，如果移除二级缓存，在循环依赖的代理场景下会破坏 Spring Bean 的单例特性。
2. Spring 能否移除三级缓存
	三级缓存是为了解决循环依赖而引入的，如果没有循环依赖，只使用二级缓存就可以了。
3. Spring 二级缓存为什么缓存了 ObjectFactory 而不是直接缓存实例化后的 Bean
 这是基于循环依赖和代理考虑，如果两个 Bean（A 和 B） 相互依赖，其中 B 需要被代理，如果直接缓存原始对象 B，那么就无法对该 B 进行代理，因为 Bean 的创建过程是先实例化，再装配属性，最后再生成代理对象，即生成代理对象的逻辑在属性装配之后进行的，Spring 在解决循环依赖时，Spring 在属性装配阶段会通过从二级缓存中取出缓存的 ObjectFactory，在 getObject() 方法中返回需要的对象，如果需要被代理，它会调用后置处理器将代理的操作提前至此，如果不需要，则直接返回原始对象。

● singletonObjects
	存储的是经过完整生命周期的 bean
● earlySingletonObjects
	存储的是未经过完整生命周期的 Bean，如果没有循环依赖，二级缓存其实没有也可以。但是如果某个 Bean 在属性注入的时候出现了循环依赖，会就会通过三级缓存创建要依赖的 Bean（还没有经过完整的生命周期），并将其存到这个二级缓存中。这里为什么要存二级缓存呢，因为可能会出现下面的场景，且 A 对象需要代理，创建 A 需要注入属性 C，创建 C 需要注入属性 A，此时 A 需要通过三级缓存来给 A 的原始对象生成代理对象，如果二级缓存不存 A，那么 B 在注入 A 时，不从二级缓存中区就需要重新生成新的代理对象，这就破坏了 Spring Bean 的单例特性
二级缓存其设计的本质是为了保证在出现循环依赖时，Spring Bean 的单例特性。
// todo 补充一个图（A 循环依赖了 B 和 C）


● singletonFactories
	缓存的是一个 ObjectFactory，在每个 Bean 的生成过程中，经过实例化得到一个原始对象，Spring 会基于这个原始对象生成一个 ObjectFactory 保存到三级缓存中提前暴露出去。如果当前 Bean 没有循环依赖，会按照生命周期正常执行，执行完成后存储到一级缓存中。如果出现循环依赖，当前 Bean 在依赖注入的时候，发现要依赖的 Bean 也正在创建中（因为被依赖的 Bean 在三级缓存中，表示它也正在创建中），则会三级缓存中获取到这个 ObjectFactory 对象，并执行 getObject() 方法，如果需要代理则生成代理对象，如果不需要代理，则得到原始对象。最后将 getObject() 返回的对象存到二级缓存中。
	三级缓存其设计的本质就是为了解决循环依赖的问题，解决循环依赖的关键就是在创建 Bean 后提前将没有经过完整生命周期的 Bean 给暴露出来。
	在源码里也有体现，如果 Spring 关闭了循环依赖（allowCircularReferences = false）, Spring 就不会把未经过完整生命周期的 Bean 给存储到三级缓存中。
```
// allowCircularReferences 是否支持循环依赖
boolean earlySingletonExposure = (mbd.isSingleton() && this.allowCircularReferences &&
				isSingletonCurrentlyInCreation(beanName));
if (earlySingletonExposure) {
  // 添加到三级缓存中
  addSingletonFactory(beanName, () -> getEarlyBeanReference(beanName, mbd, bean));
}
```

● Spring 只能解决属性注入的循环依赖，无法解决构造方法的循环依赖，用 @Lazy 注解。
● Spring 无法解决两个都是原型的 Bean 的循环依赖，如果解决。
●  循环依赖 aop + @Async 可能报错，需要考虑顺序。
● 循环依赖 aop + @Transaction 肯定不报错，因为事务注解并没有注入 BPP，而是注入的 Advisor。
● @Lazy 属性如果在类上，表示这个类只有在使用的时候才会实例化。如果在属性上，在进行属性注入时，会给该属性生成一个代理对象（什么样的代理对象），在真正使用的时候才会创建这个 Bean。


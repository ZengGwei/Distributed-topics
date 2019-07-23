#了解SpringBoot核心原理
    springboot 框架是为了能够帮助使用 spring 框架的开发
    者快速高效的构建一个基于 spirng 框架以及 spring 生态
    体系的应用解决方案。它是对“约定优于配置”这个理念下
    的一个最佳实践。
    因此它是一个服务于框架的框架，服务的范围是简化配置文件。
 
 ##### 1.约定优于配置 的体现
     + maven 的目录结构
         >默认的resource文件夹存放配置文件
         
         >默认打包方式为 jar
     + spring-boot-web-start 默认包含spring mvc 相关的依赖以及内置的Tomcat容器
        
     + 默认提供application.properties/yml 文件
     +  默认通过spring.profiles.active 属性来决定读取环境的配置文件
     +  EnableAutoConfiguration 默认对于依赖的 starter 进行自动装载
 
 ##### 2. @SpringBootApplication注解
               
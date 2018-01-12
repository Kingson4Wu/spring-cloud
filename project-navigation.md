

### 服务注册与发现
+ 服务 Spring Cloud构建微服务架构：服务注册与发现（Eureka、Consul）:<http://www.spring4all.com/article/291>

#### Eureka 
+ 美 [jʊ'ri:kə]
+ Spring Cloud Eureka是Spring Cloud Netflix项目下的服务治理模块。
+ spring-cloud-eureka-server (localhost:8081)
+ spring-cloud-eureka-client, import to spring-cloud-startup and run it (localhost:8080)
+ <http://localhost:8080/dc>
+ 可以无缝的从eureka的服务治理体系切换到consul的服务治理体系中区

#### Consul
+ 美[ˈkɑ:nsl]
+ Spring Cloud Consul项目是针对Consul的服务治理实现。Consul是一个分布式高可用的系统，它包含多个组件，但是作为一个整体，在微服务架构中为我们的基础设施提供服务发现和服务配置的工具。
+ 服务发现的接口DiscoveryClient是Spring Cloud对服务治理做的一层抽象，所以可以屏蔽Eureka和Consul服务治理的实现细节，我们的程序不需要做任何改变，只需要引入不同的服务治理依赖，并配置相关的配置属性就能轻松的将微服务纳入Spring Cloud的各个服务治理框架中。
+ 于Consul自身提供了服务端，所以我们不需要像之前实现Eureka的时候创建服务注册中心，直接通过下载consul的服务端程序就可以使用。
+ 命令启动consul的开发模式：`$consul agent -dev `
+ consul服务端启动完成之后，我们再将之前改造后的consul服务提供者启动起来。consul与eureka一样，都提供了简单的ui界面来查看服务的注册情况
+ 安装consul，移步官网https://www.consul.io下载安装并启动consul服务。 
 


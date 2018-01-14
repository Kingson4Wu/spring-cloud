

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
+ spring-cloud-consul-client, import to spring-cloud-startup and run it (localhost:8080)
+ 安装consul，移步官网https://www.consul.io下载安装并启动consul服务。 
 
### 服务消费(服务调用)
#### eureka consumer
+ Spring Cloud构建微服务架构：服务消费（基础）:<http://www.spring4all.com/article/292>
+ 在Spring Cloud Commons中提供了大量的与服务治理相关的抽象接口，包括DiscoveryClient、这里我们即将介绍的LoadBalancerClient等。
+ spring-cloud-eureka-consumer(localhost:8082)
+ 将eureka-server、eureka-client、eureka-consumer都启动起来，然后访问http://localhost:8082/consumer ，来跟踪观察eureka-consumer服务是如何消费eureka-client服务的/dc接口的。
+ 可以监控调用数?拓扑?

#### Ribbon
+ 美[ˈrɪbən]
+ Spring Cloud构建微服务架构：服务消费（Ribbon）:<http://www.spring4all.com/article/293>
+ Spring Cloud Ribbon是基于Netflix Ribbon实现的一套客户端负载均衡的工具。它是一个基于HTTP和TCP的客户端负载均衡器。它可以通过在客户端中配置ribbonServerList来设置服务端列表去轮询访问以达到均衡负载的作用。
+ 当Ribbon与Eureka联合使用时，ribbonServerList会被DiscoveryEnabledNIWSServerList重写，扩展成从Eureka注册中心中获取服务实例列表。同时它也会用NIWSDiscoveryPing来取代IPing，它将职责委托给Eureka来确定服务端是否已经启动。
+ 而当Ribbon与Consul联合使用时，ribbonServerList会被ConsulServerList来扩展成从Consul获取服务实例列表。同时由ConsulPing来作为IPing接口的实现。
+ 在使用Spring Cloud Ribbon的时候，不论是与Eureka还是Consul结合，都会在引入Spring Cloud Eureka或Spring Cloud Consul依赖的时候通过自动化配置来加载上述所说的配置内容，所以我们可以快速在Spring Cloud中实现服务间调用的负载均衡。
+ spring-cloud-eureka-consumer-ribbon  (localhost:8082)

#### Feign
+ 美[fen]
+ Spring Cloud构建微服务架构：服务消费（Feign）:<http://www.spring4all.com/article/294>
+ Spring Cloud Feign是一套基于Netflix Feign实现的声明式服务调用客户端。它使得编写Web服务客户端变得更加简单。我们只需要通过创建接口并用注解来配置它既可完成对Web服务接口的绑定。它具备可插拔的注解支持，包括Feign注解、JAX-RS注解。它也支持可插拔的编码器和解码器。Spring Cloud Feign还扩展了对Spring MVC注解的支持，同时还整合了Ribbon和Eureka来提供均衡负载的HTTP客户端实现。
+ 通过Spring Cloud Feign来实现服务调用的方式更加简单了，通过@FeignClient定义的接口来统一的生命我们需要依赖的微服务接口。而在具体使用的时候就跟调用本地方法一点的进行调用即可。由于Feign是基于Ribbon实现的，所以它自带了客户端负载均衡功能，也可以通过Ribbon的IRule进行策略扩展。另外，Feign还整合的Hystrix来实现服务的容错保护
+ Feign中的Hystrix以及配置方式
+ spring-cloud-eureka-consumer-feign  (localhost:8082)

### 分布式配置中心
#### Spring Cloud Config
+ Spring Cloud构建微服务架构：分布式配置中心:<http://www.spring4all.com/article/295>
+ Spring Cloud Config是Spring Cloud团队创建的一个全新项目，用来为分布式系统中的基础设施和微服务应用提供集中化的外部配置支持，它分为服务端与客户端两个部分。其中服务端也称为分布式配置中心，它是一个独立的微服务应用，用来连接配置仓库并为客户端提供获取配置信息、加密/解密信息等访问接口；而客户端则是微服务架构中的各个微服务应用或基础设施，它们通过指定的配置中心来管理应用资源与业务相关的配置内容，并在启动的时候从配置中心获取和加载配置信息。Spring Cloud Config实现了对服务端和客户端中环境变量和属性配置的抽象映射，所以它除了适用于Spring构建的应用程序之外，也可以在任何其他语言运行的应用程序中使用。由于Spring Cloud Config实现的配置中心默认采用Git来存储配置信息，所以使用Spring Cloud Config构建的配置服务器，天然就支持对微服务应用配置信息的版本管理，并且可以通过Git客户端工具来方便的管理和访问配置内容。当然它也提供了对其他存储方式的支持，比如：SVN仓库、本地化文件系统。
+ 构建一个基于Git存储的分布式配置中心，并对客户端进行改造，并让其能够从配置中心获取配置信息并绑定到代码中的整个过程。
+ http://localhost:8083/config-client/dev/master
+ spring-cloud-config-center-server
+ 访问配置信息的URL与配置文件的映射关系如下：

<pre>
/{application}/{profile}[/{label}]
/{application}-{profile}.yml
/{label}/{application}-{profile}.yml
/{application}-{profile}.properties
/{label}/{application}-{profile}.properties
</pre>
+ 上面的url会映射{application}-{profile}.properties对应的配置文件，其中{label}对应Git上不同的分支，默认为master。
+ spring-cloud-config-center-client, import to spring-cloud-startup and run it (localhost:8080)
+ http://localhost:8080/info (需增加spring-boot-starter-actuator依赖)
+ http://localhost:8080/config

+ 数据的实时更新推送???


### 服务容错保护
+ 在微服务架构中，我们将系统拆分成了一个个的服务单元，各单元应用间通过服务注册与订阅的方式互相依赖。由于每个单元都在不同的进程中运行，依赖通过远程调用的方式执行，这样就有可能因为网络原因或是依赖服务自身问题出现调用故障或延迟，而这些问题会直接导致调用方的对外服务也出现延迟，若此时调用方的请求不断增加，最后就会出现因等待出现故障的依赖方响应而形成任务积压，线程资源无法释放，最终导致自身服务的瘫痪，进一步甚至出现故障的蔓延最终导致整个系统的瘫痪。如果这样的架构存在如此严重的隐患，那么相较传统架构就更加的不稳定。为了解决这样的问题，因此产生了断路器等一系列的服务保护机制。

#### Hystrix
+ 美[hɪst'rɪks]
+ Spring Cloud Hystrix中实现了线程隔离、断路器等一系列的服务保护功能。它也是基于Netflix的开源框架 Hystrix实现的，该框架目标在于通过控制那些访问远程系统、服务和第三方库的节点，从而对延迟和故障提供更强大的容错能力。Hystrix具备了服务降级、服务熔断、线程隔离、请求缓存、请求合并以及服务监控等强大功能。
+ spring-cloud-consumer-hystrix(localhost:8082)
+ 在应用主类中使用@EnableCircuitBreaker或@EnableHystrix注解开启Hystrix的使用
+ 这里我们还可以使用Spring Cloud应用中的@SpringCloudApplication注解来修饰应用主类，该注解的具体定义如下所示。我们可以看到该注解中包含了上我们所引用的三个注解，这也意味着一个Spring Cloud标准应用应包含服务发现以及断路器。
+ 在为具体执行逻辑的函数上增加@HystrixCommand注解来指定服务降级方法
+ 服务消费者就通过HystrixCommand注解中指定的降级逻辑进行执行，因此该请求的结果返回了fallback。这样的机制，对自身服务起到了基础的保护，同时还为异常情况提供了自动的服务降级切换机制。
+ http://localhost:8082/consumer

  


---

+ startup:8080(服务提供者)
+ eureka-server:8081(服务中心)
+ eureka-consumer:8082(服务消费者)
+ config-center-server:8083(配置中心)



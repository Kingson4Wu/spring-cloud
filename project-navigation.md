

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
+ 通过实时的动态属性刷新（后续会通过Spring Cloud Config与Spring Cloud Bus的联合使用来介绍）来处理它


### 服务容错保护
+ 服务降级,依赖隔离,熔断器
+ 在微服务架构中，我们将系统拆分成了一个个的服务单元，各单元应用间通过服务注册与订阅的方式互相依赖。由于每个单元都在不同的进程中运行，依赖通过远程调用的方式执行，这样就有可能因为网络原因或是依赖服务自身问题出现调用故障或延迟，而这些问题会直接导致调用方的对外服务也出现延迟，若此时调用方的请求不断增加，最后就会出现因等待出现故障的依赖方响应而形成任务积压，线程资源无法释放，最终导致自身服务的瘫痪，进一步甚至出现故障的蔓延最终导致整个系统的瘫痪。如果这样的架构存在如此严重的隐患，那么相较传统架构就更加的不稳定。为了解决这样的问题，因此产生了断路器等一系列的服务保护机制。

#### Hystrix(服务降级)
+ 美[hɪst'rɪks]
+ Spring Cloud Hystrix中实现了线程隔离、断路器等一系列的服务保护功能。它也是基于Netflix的开源框架 Hystrix实现的，该框架目标在于通过控制那些访问远程系统、服务和第三方库的节点，从而对延迟和故障提供更强大的容错能力。Hystrix具备了服务降级、服务熔断、线程隔离、请求缓存、请求合并以及服务监控等强大功能。
+ Spring Cloud构建微服务架构：服务容错保护（Hystrix服务降级）:<http://www.spring4all.com/article/296>
+ spring-cloud-consumer-hystrix(localhost:8082)
+ 在应用主类中使用@EnableCircuitBreaker或@EnableHystrix注解开启Hystrix的使用
+ 这里我们还可以使用Spring Cloud应用中的@SpringCloudApplication注解来修饰应用主类，该注解的具体定义如下所示。我们可以看到该注解中包含了上我们所引用的三个注解，这也意味着一个Spring Cloud标准应用应包含服务发现以及断路器。
+ 在为具体执行逻辑的函数上增加@HystrixCommand注解来指定服务降级方法
+ 服务消费者就通过HystrixCommand注解中指定的降级逻辑进行执行，因此该请求的结果返回了fallback。这样的机制，对自身服务起到了基础的保护，同时还为异常情况提供了自动的服务降级切换机制。
+ http://localhost:8082/consumer

#### Hystrix(依赖隔离)
+ 线程隔离
+ Spring Cloud构建微服务架构：服务容错保护（Hystrix依赖隔离）:<http://www.spring4all.com/article/297>
+ 依赖隔离:“舱壁模式”对于熟悉Docker的读者一定不陌生，Docker通过“舱壁模式”实现进程的隔离，使得容器与容器之间不会互相影响。而Hystrix则使用该模式实现线程池的隔离，它会为每一个Hystrix命令创建一个独立的线程池，这样就算某个在Hystrix命令包装下的依赖服务出现延迟过高的情况，也只是对该依赖服务的调用产生影响，而不会拖慢其他的服务。
+ 通过对依赖服务实现线程池隔离，让我们的应用更加健壮，不会因为个别依赖服务出现问题而引起非相关服务的异常。同时，也使得我们的应用变得更加灵活，可以在不停止服务的情况下，配合动态配置刷新实现性能配置上的调整。
+ !!! 虽然线程池隔离的方案带了如此多的好处，但是很多使用者可能会担心为每一个依赖服务都分配一个线程池是否会过多地增加系统的负载和开销。对于这一点，使用者不用过于担心，因为这些顾虑也是大部分工程师们会考虑到的，Netflix在设计Hystrix的时候，认为线程池上的开销相对于隔离所带来的好处是无法比拟的。同时，Netflix也针对线程池的开销做了相关的测试，以证明和打消Hystrix实现对性能影响的顾虑。
+ Netflix Hystrix官方提供的一个Hystrix命令的性能监控，该命令以每秒60个请求的速度（QPS）向一个单服务实例进行访问，该服务实例每秒运行的线程数峰值为350个。
在99%的情况下，使用线程池隔离的延迟有9ms，对于大多数需求来说这样的消耗是微乎其微的，更何况为系统在稳定性和灵活性上所带来的巨大提升。虽然对于大部分的请求我们可以忽略线程池的额外开销，而对于小部分延迟本身就非常小的请求（可能只需要1ms），那么9ms的延迟开销还是非常昂贵的。实际上Hystrix也为此设计了另外的一个解决方案：信号量。
+ Hystrix中除了使用线程池之外，还可以使用信号量来控制单个依赖服务的并发度，信号量的开销要远比线程池的开销小得多，但是它不能设置超时和实现异步访问。所以，只有在依赖服务是足够可靠的情况下才使用信号量。
    - 在HystrixCommand和HystrixObservableCommand中2处支持信号量的使用：
      - 命令执行：如果隔离策略参数execution.isolation.strategy设置为SEMAPHORE，Hystrix会使用信号量替代线程池来控制依赖服务的并发控制。
      - 降级逻辑：当Hystrix尝试降级逻辑时候，它会在调用线程中使用信号量。
+ 使用了@HystrixCommand来将某个函数包装成了Hystrix命令，这里除了定义服务降级之外，Hystrix框架就会自动的为这个函数实现调用的隔离。所以，依赖隔离、服务降级在使用时候都是一体化实现的，这样利用Hystrix来实现服务容错保护在编程模型上就非常方便的，并且考虑更为全面。
  
#### Hystrix(断路器)
+  Spring Cloud构建微服务架构：服务容错保护（Hystrix断路器）:<http://www.spring4all.com/article/298>
+ 断路器模式源于Martin Fowler的Circuit Breaker一文。“断路器”本身是一种开关装置，用于在电路上保护线路过载，当线路中有电器发生短路时，“断路器”能够及时的切断故障电路，防止发生过载、发热、甚至起火等严重后果。
+ 当我们把服务提供者eureka-client中加入了模拟的时间延迟之后，在服务消费端的服务降级逻辑因为hystrix命令调用依赖服务超时，触发了降级逻辑，但是即使这样，受限于Hystrix超时时间的问题，我们的调用依然很有可能产生堆积。
+ 断路器是在什么情况下开始起作用呢？这里涉及到断路器的三个重要参数：快照时间窗、请求总数下限、错误百分比下限。
    - 快照时间窗：断路器确定是否打开需要统计一些请求和错误数据，而统计的时间范围就是快照时间窗，默认为最近的10秒。
    - 请求总数下限：在快照时间窗内，必须满足请求总数下限才有资格根据熔断。默认为20，意味着在10秒内，如果该hystrix命令的调用此时不足20次，即时所有的请求都超时或其他原因失败，断路器都不会打开。
    - 错误百分比下限：当请求总数在快照时间窗内超过了下限，比如发生了30次调用，如果在这30次调用中，有16次发生了超时异常，也就是超过50%的错误百分比，在默认设定50%下限情况下，这时候就会将断路器打开。
+ 断路器未打开之前，对于之前那个示例的情况就是每个请求都会在当hystrix超时之后返回fallback，每个请求时间延迟就是近似hystrix的超时时间，如果设置为5秒，那么每个请求就都要延迟5秒才会返回。当熔断器在10秒内发现请求总数超过20，并且错误百分比超过50%，这个时候熔断器打开。打开之后，再有请求调用的时候，将不会调用主逻辑，而是直接调用降级逻辑，这个时候就不会等待5秒之后才返回fallback。通过断路器，实现了自动地发现错误并将降级逻辑切换为主逻辑，减少响应延迟的效果。
+ 断路器:把降级逻辑直接切换为主逻辑
+ hystrix也为我们实现了自动恢复功能。当断路器打开，对主逻辑进行熔断之后，hystrix会启动一个休眠时间窗，在这个时间窗内，降级逻辑是临时的成为主逻辑，当休眠时间窗到期，断路器将进入半开状态，释放一次请求到原来的主逻辑上，如果此次请求正常返回，那么断路器将继续闭合，主逻辑恢复，如果这次请求依然有问题，断路器继续进入打开状态，休眠时间窗重新计时。
+ hystrix的断路器实现了对依赖资源故障的端口、对降级策略的自动切换以及对主逻辑的自动恢复机制。这使得我们的微服务在依赖外部服务或资源的时候得到了非常好的保护，同时对于一些具备降级逻辑的业务需求可以实现自动化的切换与恢复，相比于设置开关由监控和运维来进行切换的传统实现方式显得更为智能和高效。

+ !!! 很多参数需要了解和注意,不能都只使用默认的参数,否则什么时候恢复都不知道...
  
### Hystrix监控面板
+ <http://www.spring4all.com/article/299>
+ 断路器是根据一段时间窗内的请求情况来判断并操作断路器的打开和关闭状态的。而这些请求情况的指标信息都是HystrixCommand和HystrixObservableCommand实例在执行过程中记录的重要度量信息，它们除了Hystrix断路器实现中使用之外，对于系统运维也有非常大的帮助。这些指标信息会以“滚动时间窗”与“桶”结合的方式进行汇总，并在内存中驻留一段时间，以供内部或外部进行查询使用，Hystrix Dashboard就是这些指标内容的消费者之一。
+ spring-cloud-hystrix-dashboard (http://localhost:8084/hystrix)
+ Hystrix Dashboard的监控首页，该页面中并没有具体的监控信息。从页面的文字内容中我们可以知道，Hystrix Dashboard共支持三种不同的监控方式，依次为：
  - 默认的集群监控：通过URLhttp://turbine-hostname:port/turbine.stream开启，实现对默认集群的监控。
  - 指定的集群监控：通过URLhttp://turbine-hostname:port/turbine.stream?cluster=[clusterName]开启，实现对clusterName集群的监控。
  - 单体应用的监控：通过URLhttp://hystrix-app:port/hystrix.stream开启，实现对具体某个服务实例的监控。
+ 前两者都对集群的监控，需要整合Turbine才能实现，这部分内容我们将在下一篇中做详细介绍。在本节中，我们主要实现对单个服务实例的监控，所以这里我们先来实现单个服务实例的监控。
+ add http://localhost:8082/hystrix.stream to monitor
+ http://localhost:8084/hystrix/monitor?stream=http%3A%2F%2Flocalhost%3A8082%2Fhystrix.stream&title=kingson
+ Hystrix Dashboard
    1. Delay：该参数用来控制服务器上轮询监控信息的延迟时间，默认为2000毫秒，我们可以通过配置该属性来降低客户端的网络和CPU消耗。
    2. Title：该参数对应了上图头部标题Hystrix Stream之后的内容，默认会使用具体监控实例的URL，我们可以通过配置该信息来展示更合适的标题。
+ 监控信息
    1. 实心圆：共有两种含义。它通过颜色的变化代表了实例的健康程度，如下图所示，它的健康度从绿色、黄色、橙色、红色递减。该实心圆除了颜色的变化之外，它的大小也会根据实例的请求流量发生变化，流量越大该实心圆就越大。
    2. 曲线：用来记录2分钟内流量的相对变化，我们可以通过它来观察到流量的上升和下降趋势。
    
+ !!! 监控数据只有实例内存中有(快照时间内),没法持久化???

#### Hystrix监控数据聚合
+ 使用Hystrix Dashboard来展示Hystrix用于熔断的各项度量指标。通过Hystrix Dashboard，我们可以方便的查看服务实例的综合情况，比如：服务调用次数、服务调用延迟等。但是仅通过Hystrix Dashboard我们只能实现对服务当个实例的数据展现，在生产环境我们的服务是肯定需要做高可用的，那么对于多实例的情况，我们就需要将这些度量指标数据进行聚合

#### Turbine
+ <http://www.spring4all.com/article/300>
+ 美[ˈtɜ:rbaɪn]
+ spring-cloud-turbine
+ add http://localhost:8085/turbine.stream to hystrix-dashboard
+ 1.通过HTTP收集聚合(以上)
+ 2.通过消息代理收集聚合(TODO)
    - Spring Cloud在封装Turbine的时候，还实现了基于消息代理的收集实现。所以，我们可以将所有需要收集的监控信息都输出到消息代理中，然后Turbine服务再从消息代理中异步的获取这些监控信息，最后将这些监控信息聚合并输出到Hystrix Dashboard中。
    - 对于RabbitMQ的安装与基本使用:<http://blog.didispace.com/spring-boot-rabbitmq/>
    - 主要引入了spring-cloud-starter-turbine-amqp依赖，它实际上就是包装了spring-cloud-starter-turbine-stream和pring-cloud-starter-stream-rabbit。
    - 通过Hystrix Dashboard开启对http://localhost:8989/turbine.stream的监控，我们可以获得如之前实现的同样效果，只是这里我们的监控信息收集时是通过了消息代理异步实现的。
    

### 服务网关
+ 焦点聚集在对外服务
+ 将权限控制这样的东西从我们的服务单元中抽离出去，而最适合这些逻辑的地方就是处于对外访问最前端的地方，我们需要一个更强大一些的均衡负载器，它就是本文将来介绍的：服务网关。

#### zuul
+ 服务网关是微服务架构中一个不可或缺的部分。通过服务网关统一向外系统提供REST API的过程中，除了具备服务路由、均衡负载功能之外，它还具备了权限控制等功能。Spring Cloud Netflix中的Zuul就担任了这样的一个角色，为微服务架构提供了前门保护的作用，同时将权限控制这些较重的非业务逻辑内容迁移到服务路由层面，使得服务集群主体能够具备更高的可复用性和可测试性。

##### 1.Spring Cloud Zuul基于服务的自动路由功能 
+ spring-cloud-zuul-api-gateway
+ 一个基于Spring Cloud Zuul服务网关就已经构建完毕。启动该应用，一个默认的服务网关就构建完毕了。由于Spring Cloud Zuul在整合了Eureka之后，具备默认的服务路由功能，即：当我们这里构建的api-gateway应用启动并注册到eureka之后，服务网关会发现上面我们启动的两个服务eureka-client和eureka-consumer，这时候Zuul就会创建两个路由规则。每个路由规则都包含两部分，一部分是外部请求的匹配规则，另一部分是路由的服务ID。针对当前示例的情况，Zuul会创建下面的两个路由规则：
  - 转发到eureka-client服务的请求规则为：/eureka-client/**
  - 转发到eureka-consumer服务的请求规则为：/eureka-consumer/**  
+ http://localhost:8086/kingson-micro-service/dc 该请求将最终被路由到kingson-micro-service的/dc接口上

##### 2.路由配置
+ <http://www.spring4all.com/article/302>
+ 传统路由配置:所谓的传统路由配置方式就是在不依赖于服务发现机制的情况下，通过在配置文件中具体指定每个路由表达式与服务实例的映射关系来实现API网关对外部请求的路由。
+ 没有Eureka和Consul的服务治理框架帮助的时候，我们需要根据服务实例的数量采用不同方式的配置来实现路由规则
    - 单实例配置：通过一组zuul.routes.<route>.path与zuul.routes.<route>.url参数对的方式配置
    - 多实例配置：通过一组zuul.routes.<route>.path与zuul.routes.<route>.serviceId参数对的方式配置
+ 在Spring Cloud Zuul中自带了对Ribbon的依赖

+ 传统路由的映射方式比较直观且容易理解，API网关直接根据请求的URL路径找到最匹配的path表达式，直接转发给该表达式对应的url或对应serviceId下配置的实例地址，以实现外部请求的路由。

##### 3.过滤器
+ 通过前置的网关服务来完成这些非业务性质的校验。由于网关服务的加入，外部客户端访问我们的系统已经有了统一入口，既然这些校验与具体业务无关，那何不在请求到达的时候就完成校验和过滤，而不是转发后再过滤而导致更长的请求延迟。同时，通过在网关中完成校验和过滤，微服务应用端就可以去除各种复杂的过滤器和拦截器了，这使得微服务应用的接口开发和测试复杂度也得到了相应的降低。
+ Zuul允许开发者在API网关上通过定义过滤器来实现对请求的拦截与过滤，实现的方法非常简单，我们只需要继承ZuulFilter抽象类并实现它定义的四个抽象函数就可以完成对请求的拦截和过滤了。
+ http://localhost:8086/kingson-micro-service/dc&accessToken=token  
+ 可以根据自己的需要在服务网关上定义一些与业务无关的通用逻辑实现对请求的过滤和拦截，比如：签名校验、权限校验、请求限流等功能。


### 消息总线
+ <https://www.jianshu.com/p/c43e175b8b1e>
+ http://localhost:8080/config
+ vi kingson-micro-service.yml 
+ `curl  -i -d "" "http://localhost:8080/bus/refresh"`
+ http://localhost:8080/info
+ 指定刷新范围:bus/refresh?destination=customers:**
+ 感觉不怎么样








---

+ startup:8080(服务提供者)
+ eureka-server:8081(服务中心)
+ eureka-consumer:8082(服务消费者)
+ config-center-server:8083(配置中心)
+ hystrix-dashboard:8084
+ turbine:8085(hystrix-dashboard 多实例聚合监控)
+ zuul-api-gateway:8086(服务网关)



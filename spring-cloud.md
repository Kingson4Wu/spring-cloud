
+ Spring Boot 入门系列:<http://www.spring4all.com/article/246>
+ Spring Cloud 入门系列:<http://www.spring4all.com/article/320>

### Spring Cloud
+  Spring Cloud是一个基于Spring Boot实现的云应用开发工具，它为基于JVM的云应用开发中的
   配置管理、服务发现、断路器、智能路由、微代理、控制总线、全局锁、决策竞选、分布式会话和集群状态管理等操作提供了一种简单的开发方式。
+ Spring Cloud包含了多个子项目（针对分布式系统中涉及的多个不同开源产品），比如：Spring Cloud Config、Spring Cloud Netflix、
Spring Cloud CloudFoundry、Spring Cloud AWS、Spring Cloud Security、Spring Cloud Commons、Spring Cloud Zookeeper、
Spring Cloud CLI等项目。
+ 项目地址: Spring Cloud<http://projects.spring.io/spring-cloud/>
+ github: <https://github.com/spring-cloud-samples>

### Spring Cloud 组件
+ 随着Netflix's (OSS) Spring Cloud项目的流行和成功，或许可以考虑将它集成到那些以任务为关键的API里。Netflix的项目建立在Spring Boot框架之上，提供了如下组件：
+ <https://mp.weixin.qq.com/s/JLyjk2Nt1I2APqp6IXENnA>
+ Spring boot - 微服务的入门级微框架，用来简化 Spring 应用的初始搭建以及开发过程。
1. Eureka: 用于Spring管理下的bean服务发现(注册中心).
云端服务发现，一个基于 REST 的服务，用于定位服务，以实现云端中间层服务发现和故障转移。
2. Zuul: 处理路由服务，被视为请求的”守门员“(网关)(智能路由).
是在云平台上提供动态路由，监控，弹性，安全等边缘服务的框架。Zuul 相当于是设备和 Netflix 流应用的 Web 网站后端所有请求的前门。
3. Ribbon: 用于动态路由和负载均衡(路由与负载均衡)
提供云端负载均衡，有多种负载均衡策略可供选择，可配合服务发现和断路器使用。
4. Hystrix: 提供了断路器功能，以处理无响应的API调用(断路器).
熔断器，容错管理工具，旨在通过熔断机制控制服务和第三方库的节点,从而对延迟和故障提供更强大的容错能力。
5. Turbine: 提供了关于Hystrix的全部可用断路器的信息.
Turbine 是聚合服务器发送事件流数据的一个工具，用来监控集群下 hystrix 的 metrics 情况。
6. Spring Cloud Config: - 配置管理工具包，让你可以把配置放到远程服务器，集中化管理集群配置，目前支持本地存储、Git 以及 Subversion。 (分布式配置)
7. Spring Cloud Sleuth: (服务调用跟踪)
日志收集工具包，封装了 Dapper 和 log-based 追踪以及 Zipkin 和 HTrace 操作，为 SpringCloud 应用实现了一种分布式追踪解决方案。
8. Spring Cloud Bus - 事件、消息总线，用于在集群（例如，配置变化事件）中传播状态变化，可与 Spring Cloud Config 联合实现热部署。(消息总线)
9. Spring Cloud Stream - Spring 数据流操作开发包，封装了与 Redis、Rabbit、Kafka 等发送接收消息。
10. Feign - Feign 是一种声明式、模板化的 HTTP 客户端。
11. Spring Cloud OAuth2 - 基于 Spring Security 和 OAuth2 的安全工具包，为你的应用程序添加安全控制。

<pre>
日志输出	ELK
批量任务	Task
</pre>


### article
+ Spring Cloud的微服务框架的组件中就包含了一个Circuit Breaker组件。
<http://dwz.cn/3R5BIV>
熔断行为的核心是提供Fallback方法来实现降级逻辑。Spring Cloud提供了很多种模式来适应不同场景的要求。
+ 深度剖析服务发现组件Netflix Eureka
<http://geek.csdn.net/news/detail/130223>
+ 如何将 Spring Cloud Netflix 框架集成到现有 API 中
<http://mp.weixin.qq.com/s/Gw2Rf6kQ0YfyUvf6-58xbg>
Netflix (OSS) Spring Cloud项目
+ 配置中心
Spring Cloud Config
<http://mp.weixin.qq.com/s/EMYtRQUqagl9qLYav2xIyQ>，它把应用配置集中到了一个Git仓库。
+ 服务注册中心
Spring Cloud Consul
<http://mp.weixin.qq.com/s/MRac4s8yN_PWDNa5PnC48Q>
<pre>
ZooKeeper
etcd
Consul
Eureka
</pre>




+ Spring Cloud构建微服务架构（一）服务注册与发现:<http://mp.weixin.qq.com/s/bXo2u2ODpKknrubF_IXqvQ>
+ Spring Cloud 系列文章: <http://www.ityouknow.com/spring-cloud>
+ 从 Spring Cloud 开始，聊聊微服务架构实践之路:<https://mp.weixin.qq.com/s/bKBTDeEwki6NoxkoI2DzEQ>
+ Google和eBay在建设微服务生态系统中的深刻教训:<http://geek.csdn.net/news/detail/48762>

#### API 网关
+ Spring-Cloud-Gateway 源码解析 —— 网关初始化:<http://www.iocoder.cn/Spring-Cloud-Gateway/init>
+ 微服务架构中整合网关、权限服务:<http://blueskykong.com/2017/12/10/integration/>
+ 微服务下的网关与容错:<https://mp.weixin.qq.com/s/1nMdpWKH7TuwtfW71hR3Lw>
+ 有赞API网关实践:<https://tech.youzan.com/api-gateway-in-practice/>


---

### 服务治理
+ Spring Cloud为服务治理做了一层抽象接口，所以在Spring Cloud应用中可以支持多种不同的服务治理框架
+ Netflix Eureka、Consul、Zookeeper。
+ 服务注册、服务发现、服务调用等


### API 网关
+ 接入、路由、限流等功能
+ 各自的服务提供方专注于业务逻辑的实现，从而给客户端调用提供了一个稳健的服务调用环境。
+ 在网关大调用量的情况下，还要保证网关的可降级、可限流、可隔离等等一系列容错能力。



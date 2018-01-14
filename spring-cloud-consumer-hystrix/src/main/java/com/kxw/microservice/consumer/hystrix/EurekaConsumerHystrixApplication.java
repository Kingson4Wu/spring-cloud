package com.kxw.microservice.consumer.hystrix;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * Created by kingsonwu on 18/1/12.
 * http://www.spring4all.com/article/296
 *
 * 使用@EnableCircuitBreaker或@EnableHystrix注解开启Hystrix的使用
 * 这里我们还可以使用Spring Cloud应用中的@SpringCloudApplication注解来修饰应用主类，该注解的具体定义如下所示。我们可以看到该注解中包含了上我们所引用的三个注解，这也意味着一个Spring Cloud标准应用应包含服务发现以及断路器。
 */
@SpringCloudApplication
public class EurekaConsumerHystrixApplication {

    /**
     * 初始化RestTemplate，用来真正发起REST请求。
     * @EnableDiscoveryClient注解用来将当前应用加入到服务治理体系中。
     * @return
     */
    @Bean
    @LoadBalanced//RibbonAutoConfiguration 怎么把robbin的逻辑注入进去的 TODO
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        new SpringApplicationBuilder(EurekaConsumerHystrixApplication.class).web(true).run(args);
    }
}
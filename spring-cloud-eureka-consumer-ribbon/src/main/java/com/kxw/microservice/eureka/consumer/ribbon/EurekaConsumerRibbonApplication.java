package com.kxw.microservice.eureka.consumer.ribbon;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * Created by kingsonwu on 18/1/12.
 * http://www.spring4all.com/article/292
 */
@EnableDiscoveryClient
@SpringBootApplication
public class EurekaConsumerRibbonApplication {

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
        new SpringApplicationBuilder(EurekaConsumerRibbonApplication.class).web(true).run(args);
    }
}
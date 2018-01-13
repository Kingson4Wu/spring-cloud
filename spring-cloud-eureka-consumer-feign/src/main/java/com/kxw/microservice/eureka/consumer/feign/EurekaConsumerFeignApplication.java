package com.kxw.microservice.eureka.consumer.feign;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * Created by kingsonwu on 18/1/12.
 * http://www.spring4all.com/article/292
 *
 * 通过@EnableFeignClients注解开启扫描Spring Cloud Feign客户端的功能
 */
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class EurekaConsumerFeignApplication {

    /**
     * 初始化RestTemplate，用来真正发起REST请求。
     * @EnableDiscoveryClient注解用来将当前应用加入到服务治理体系中。
     * @return
     */
   /* @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }*/

    public static void main(String[] args) {
        new SpringApplicationBuilder(EurekaConsumerFeignApplication.class).web(true).run(args);
    }
}
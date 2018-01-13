package com.kxw.microservice.eureka.consumer;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * Created by kingsonwu on 18/1/12.
 * http://www.spring4all.com/article/292
 */
@EnableDiscoveryClient
@SpringBootApplication
public class EurekaConsumerApplication {

    /**
     * 初始化RestTemplate，用来真正发起REST请求。
     * @EnableDiscoveryClient注解用来将当前应用加入到服务治理体系中。
     * @return
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        new SpringApplicationBuilder(EurekaConsumerApplication.class).web(true).run(args);
    }
}
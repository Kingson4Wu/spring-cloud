package com.kxw.microservice.configcenter.server;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * http://www.spring4all.com/article/295
 *
 * 添加@EnableConfigServer注解，开启Spring Cloud Config的服务端功能
 */
@EnableConfigServer
@SpringBootApplication
public class ConfigCenterServerApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(ConfigCenterServerApplication.class)
            .web(true).run(args);
    }
}
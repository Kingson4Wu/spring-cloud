package com.kxw.microservice.zuul.apigateway;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * http://www.spring4all.com/article/301
 */
@EnableZuulProxy
@SpringCloudApplication
public class ZuulApiGatewayApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(ZuulApiGatewayApplication.class)
            .web(true).run(args);
    }
}
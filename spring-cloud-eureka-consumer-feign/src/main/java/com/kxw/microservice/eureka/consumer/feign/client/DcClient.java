package com.kxw.microservice.eureka.consumer.feign.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by kingsonwu on 18/1/13.
 */

/**
 * 创建一个Feign的客户端接口定义。使用@FeignClient注解来指定这个接口所要调用的服务名称，
 * 接口中定义的各个函数使用Spring MVC的注解就可以来绑定服务提供方的REST接口，
 * 比如下面就是绑定kingson-micro-service服务的/dc接口的例子
 */
@FeignClient("kingson-micro-service")
public interface DcClient {

    @GetMapping("/dc")
    String consumer();

}
package com.kxw.microservice.eureka.consumer.ribbon.controller;

import com.kxw.microservice.common.constants.CloudConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * http://www.spring4all.com/article/292
 */
@RestController("eurekaConsumerRibbonDcController")
public class DcController {

    /*@Autowired
    LoadBalancerClient loadBalancerClient;*/

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/consumer")
    public String dc() {

        /**
         * 去掉了原来与LoadBalancerClient相关的逻辑之外，对于RestTemplate的使用，我们的第一个url参数有一些特别。
         * 这里请求的host位置并没有使用一个具体的IP地址和端口的形式，而是采用了服务名的方式组成。
         * 那么这样的请求为什么可以调用成功呢？因为Spring Cloud Ribbon有一个拦截器，它能够在这里进行实际调用的时候，
         * 自动的去选取服务实例，并将实际要请求的IP地址和端口替换这里的服务名，从而完成服务接口的调用。
         */
        return restTemplate.getForObject("http://"+ CloudConstants.SERVICE_NAME  +"/dc", String.class);

        /**
         * 将eureka-server、eureka-client、eureka-consumer-ribbon都启动起来，然后访问http://localhost:8082/consumer ，
         * 来跟踪观察eureka-consumer-ribbon服务是如何消费eureka-client服务的/dc接口的，并且也可以通过启动多个eureka-client服务来观察其负载均衡的效果。
         * <code>
         * LoadBalancerInterceptor
         * final URI originalUri = request.getURI();
         * String serviceName = originalUri.getHost();
         * </code>
         * request 对象含有interceptor List
         */
    }
}
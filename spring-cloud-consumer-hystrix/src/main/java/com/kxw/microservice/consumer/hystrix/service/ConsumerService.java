package com.kxw.microservice.consumer.hystrix.service;

import com.kxw.microservice.common.constants.CloudConstants;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by kingsonwu on 18/1/13.
 */
@Service
public class ConsumerService {
    @Autowired
    RestTemplate restTemplate;

    /**
     * 在为具体执行逻辑的函数上增加@HystrixCommand注解来指定服务降级方法
     * 服务消费者就通过HystrixCommand注解中指定的降级逻辑进行执行，因此该请求的结果返回了fallback。这样的机制，对自身服务起到了基础的保护，同时还为异常情况提供了自动的服务降级切换机制。
     * @return
     */
    @HystrixCommand(fallbackMethod = "fallback")
    public String consumer() {
        return restTemplate.getForObject("http://"+ CloudConstants.SERVICE_NAME +"/dc", String.class);
    }

    public String fallback() {
        return "fallback";
    }

}

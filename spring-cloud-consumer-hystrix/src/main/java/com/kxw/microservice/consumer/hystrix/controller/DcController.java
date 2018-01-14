package com.kxw.microservice.consumer.hystrix.controller;

import com.kxw.microservice.consumer.hystrix.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * http://www.spring4all.com/article/292
 */
@RestController("eurekaConsumerRibbonDcController")
public class DcController {

    @Autowired
    ConsumerService consumerService;

    @GetMapping("/consumer")
    public String dc() {
        return consumerService.consumer();
    }
}
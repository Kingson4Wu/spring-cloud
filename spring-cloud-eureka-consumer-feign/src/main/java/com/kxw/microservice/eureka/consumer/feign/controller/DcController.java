package com.kxw.microservice.eureka.consumer.feign.controller;

import com.kxw.microservice.eureka.consumer.feign.client.DcClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kxw
 *         http://www.spring4all.com/article/294
 */
@RestController("eurekaConsumerDcController")
public class DcController {

    @Autowired
    DcClient dcClient;

    @GetMapping("/consumer")
    public String dc() {
        return dcClient.consumer();
    }

}
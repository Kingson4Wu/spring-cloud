package com.kxw.microservice.configcenter.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by kingsonwu on 18/1/13.
 */
@RestController
public class ConfigCenterController {

    @Autowired
    private Environment environment;

    @Value("${info.profile}")
    private String profile;

    @GetMapping("/config")
    public String config() {

        String value = environment.getProperty("info.profile");
        //return profile;
        return value;
    }

}

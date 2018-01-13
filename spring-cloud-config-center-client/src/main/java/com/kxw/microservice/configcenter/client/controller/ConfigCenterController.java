package com.kxw.microservice.configcenter.client.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by kingsonwu on 18/1/13.
 */
@RestController
public class ConfigCenterController {

    @Value("${info.profile}")
    public String profile;

    @GetMapping("/config")
    public String config() {
        return profile;
    }

}

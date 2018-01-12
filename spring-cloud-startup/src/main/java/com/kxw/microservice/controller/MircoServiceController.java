package com.kxw.microservice.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableAutoConfiguration
@RestController
@RequestMapping("/microservice")
public class MircoServiceController {

    @RequestMapping("/{id}")
    public String view(@PathVariable("id") Long id) {

        return String.valueOf(id);
    }

}
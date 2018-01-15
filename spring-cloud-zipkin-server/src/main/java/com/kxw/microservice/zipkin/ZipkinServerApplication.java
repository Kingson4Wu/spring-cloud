package com.kxw.microservice.zipkin;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import zipkin.server.EnableZipkinServer;

/**
 */
@EnableZipkinServer
//@EnableZipkinStreamServer//配置可以作为zipkinserver
@SpringBootApplication
public class ZipkinServerApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(ZipkinServerApplication.class)
            .web(true).run(args);
    }


}
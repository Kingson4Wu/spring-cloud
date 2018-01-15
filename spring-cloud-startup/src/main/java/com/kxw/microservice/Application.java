package com.kxw.microservice;

import java.util.ArrayList;
import java.util.List;

import com.kxw.microservice.configcenter.client.ConfigCenterClientAutoConfiguration;
import com.kxw.microservice.eureka.client.EurekaClientAutoConfiguration;
import com.kxw.microservice.security.SpringSecurityAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.filter.CharacterEncodingFilter;


//@EnableAutoConfiguration 就不用import??
@Import({
    EurekaClientAutoConfiguration.class,
    /*ConsulClientAutoConfiguration.class*/
    ConfigCenterClientAutoConfiguration.class,
    SpringSecurityAutoConfiguration.class
})
@EnableDiscoveryClient
@ServletComponentScan
@EnableScheduling
@EnableAspectJAutoProxy
@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Application extends SpringBootServletInitializer {

    @Bean
    public ServerProperties serverProperties() {
        return new ServerProperties();
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setForceEncoding(true);
        characterEncodingFilter.setEncoding("UTF-8");
        registrationBean.setFilter(characterEncodingFilter);

        List<String> urlPatterns = new ArrayList<>();
        urlPatterns.add("/*");
        registrationBean.setUrlPatterns(urlPatterns);

        return registrationBean;
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        builder.sources(Application.class);

        return super.configure(builder);
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

}
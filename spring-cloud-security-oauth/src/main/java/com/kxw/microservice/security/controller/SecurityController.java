package com.kxw.microservice.security.controller;

import java.security.Principal;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by kingsonwu on 18/1/13.
 * http://www.spring4all.com/article/226
 * 使用@EnableOAuth2Sso 注解，启用了“基于OAuth2的单点登录”，做了一些安全配置；同时，还定义了两个端点，/welcome 端点返回“welcome”字符串，/user 端点返回当前登录用户的认证信息。
 *
 * (1) 启动应用。
 * (2) 访问http://localhost:8080/login，将会跳转到GitHub，进行认证。
 * (3) 认证通过后，访问http://localhost:8080/user，可看到当前用户的信息。
 * (4) 访问http://localhost:8080/logout，可正常退出应用。
 */
@RestController
public class SecurityController {

    @GetMapping("/welcome")
    public String welcome() {
        return "welcome";
    }

    @RequestMapping("/user")
    public Principal user(Principal user) {
        return user;
    }

    /**
     * @EnableOAuth2Sso注解。如果WebSecurityConfigurerAdapter类上注释了@EnableOAuth2Sso注解，那么将会添加身份验证过滤器和身份验证入口。
     * 如果只有一个@EnableOAuth2Sso注解没有编写在WebSecurityConfigurerAdapter上，那么它将会为所有路径启用安全，并且会在基于HTTP Basic认证的安全链之前被添加。
     * 详见@EnableOAuth2Sso的注释。
     */
    @Component
    @EnableOAuth2Sso // 实现基于OAuth2的单点登录，建议跟踪进代码阅读以下该注解的注释，很有用
    public static class SecurityConfiguration extends WebSecurityConfigurerAdapter {
        @Override
        public void configure(HttpSecurity http) throws Exception {
            http.
                antMatcher("/**")
                    // 所有请求都得经过认证和授权
                .authorizeRequests().anyRequest().authenticated()
                .and().authorizeRequests().antMatchers("/", "/anon").permitAll()
                .and()
                    // 这里之所以要禁用csrf，是为了方便。
                    // 否则，退出链接必须要发送一个post请求，请求还得带csrf token
                    // 那样我还得写一个界面，发送post请求
                .csrf().disable()
                // 退出的URL是/logout
                .logout().logoutUrl("/logout").permitAll()
                // 退出成功后，跳转到/路径。
                .logoutSuccessUrl("/");
        }
    }

}
package com.muhardin.endy.belajar.stack2015.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.security.web.session.HttpSessionEventPublisher;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
    
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/oauth/confirm_access").setViewName("authorize");
        registry.addViewController("/").setViewName("landing");
    }
    
    @Bean
    public HttpSessionEventPublisher registerHttpSessionEventPublisher(){
        return new HttpSessionEventPublisher();
    }
}

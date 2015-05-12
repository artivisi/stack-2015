package com.muhardin.endy.belajar.stack2015.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.security.web.session.HttpSessionEventPublisher;

@Configuration @EnableResourceServer
@PropertySource("classpath:clients.properties")
public class WebConfig extends WebMvcConfigurerAdapter {
    
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
    }
    
    @Bean
    public HttpSessionEventPublisher registerHttpSessionEventPublisher(){
        return new HttpSessionEventPublisher();
    }
}

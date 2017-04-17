package com.muhardin.endy.belajar.stack2015;

import com.muhardin.endy.belajar.stack2015.handler.ApiInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class AuthorizationServer extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(AuthorizationServer.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(AuthorizationServer.class);
    }
    
    @Bean
    public WebMvcConfigurerAdapter configureWebapp(){
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(new ApiInterceptor());
            }
        };
    }
}

package com.muhardin.endy.belajar.stack2015;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class UiWeb {
    public static void main(String[] args) {
        SpringApplication.run(UiWeb.class, args);
    }
}

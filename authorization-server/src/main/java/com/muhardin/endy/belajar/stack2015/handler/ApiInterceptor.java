/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muhardin.endy.belajar.stack2015.handler;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 *
 * @author adi
 */
public class ApiInterceptor extends HandlerInterceptorAdapter {
    
    private final Logger logger = LoggerFactory.getLogger(ApiInterceptor.class);
     
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        logger.debug("CHECK AUTHORIZATION [{}] ", request.getHeader("Authorization"));
        if(request.getCookies()!=null){
            for(Cookie cookie : request.getCookies()){
                logger.debug("CHECK Cookie [{} : {}] ", cookie.getName(), cookie.getValue());
            }
        }
        return true;
    }
    
}

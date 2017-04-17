/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muhardin.endy.belajar.stack2015;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import java.util.Set;

/**
 *
 * @author adi
 */
public class CustomFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 20; //set 8 because default filter is 9
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        
        // Alter ignored headers as per: https://gitter.im/spring-cloud/spring-cloud?at=56fea31f11ea211749c3ed22
        Set<String> headers = (Set<String>) ctx.get("ignoredHeaders");
        for (String header : headers) {
            System.out.println("IGNORE HEADER : " + header);
        }
        
        // We need our JWT tokens relayed to resource servers
        headers.remove("authorization");
        return null;
    }
    
}

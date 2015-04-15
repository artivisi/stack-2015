package com.muhardin.endy.belajar.stack2015.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ToDoController {

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping(value = "/halo", method = RequestMethod.GET)
    public Map<String, Object> halo(@RequestParam(required = false) String nama, Authentication user) {
        Map<String, Object> hasil = new HashMap<>();
        hasil.put("salam", "Halo " + nama);
        hasil.put("authClass", user.getClass().getName());
        hasil.put("username", user.getPrincipal());
        hasil.put("roles", user.getAuthorities().toArray());
        return hasil;
    }
    
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_STAFF')")
    @RequestMapping(value = "/waktu", method = RequestMethod.GET)
    public Map<String, Object> waktu(Authentication user) {
        Map<String, Object> hasil = new HashMap<>();
        hasil.put("waktu", new Date());
        hasil.put("authClass", user.getClass().getName());
        hasil.put("username", user.getPrincipal());
        hasil.put("roles", user.getAuthorities().toArray());
        return hasil;
    }
}

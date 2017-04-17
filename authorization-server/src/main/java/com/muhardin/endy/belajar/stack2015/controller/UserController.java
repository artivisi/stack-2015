package com.muhardin.endy.belajar.stack2015.controller;

import java.security.Principal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    
    @RequestMapping("/me")
    public Principal me(Principal user) {
        LOGGER.info("Access Principal User");
        return user;
    }
}

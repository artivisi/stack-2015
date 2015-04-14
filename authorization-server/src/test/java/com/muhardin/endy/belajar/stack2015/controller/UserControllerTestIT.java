package com.muhardin.endy.belajar.stack2015.controller;

import static com.jayway.restassured.RestAssured.*;
import com.muhardin.endy.belajar.stack2015.AuthorizationServer;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AuthorizationServer.class)
@WebAppConfiguration
@IntegrationTest("server.port=0")
public class UserControllerTestIT {
    
    @Value("${local.server.port}")
    private Integer serverPort;
    
    @Before
    public void setPort(){
        port = serverPort;
    }
    
    @Test
    public void testMe(){
        when()
                .get("/uaa/me")
            .then()
                .statusCode(HttpStatus.SC_OK);
    }
}

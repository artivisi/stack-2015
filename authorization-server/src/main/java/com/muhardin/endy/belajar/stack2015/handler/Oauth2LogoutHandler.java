package com.muhardin.endy.belajar.stack2015.handler;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class Oauth2LogoutHandler implements LogoutSuccessHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired private ConsumerTokenServices consumerTokenServices;
    @Autowired private SessionRegistry sessionRegistry;
    
    public void onLogoutSuccess(HttpServletRequest req, HttpServletResponse res, Authentication a) throws IOException, ServletException {
        logger.debug("Removing session id [{}]", req.getSession().getId());
        sessionRegistry.removeSessionInformation(req.getSession().getId());
        String token=req.getParameter("token");
        if(token != null){
            consumerTokenServices.revokeToken(token);
        }
        String redirect = req.getParameter("redirect");
        if(redirect != null){
            logger.debug("Redirect after logout to [{}] ",redirect);
            res.sendRedirect(redirect);
        }
    }
}

package com.muhardin.endy.belajar.stack2015.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Configuration
    public static class WebappSecurityConfig extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .authorizeRequests()
                    .antMatchers("/css/**").permitAll();
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.inMemoryAuthentication()
                    .withUser("endy")
                    .password("123")
                    .roles("ADMIN")
                    .and().withUser("maya")
                    .password("456")
                    .roles("STAFF");
        }

        @Bean
        @Override
        public AuthenticationManager authenticationManagerBean() throws Exception {
            return super.authenticationManagerBean();
        }
    }

    @Configuration
    @Order(-10)
    public static class OAuthSecurityConfig extends WebSecurityConfigurerAdapter {

        @Autowired
        private AuthenticationManager authenticationManager;
        
        @Bean 
        public SessionRegistry sessionRegistry(){
            return new SessionRegistryImpl();
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.sessionManagement()
                .maximumSessions(1)
                .maxSessionsPreventsLogin(true)
                .sessionRegistry(sessionRegistry());
            
            http
                    .formLogin().loginPage("/login").permitAll()
                    .and().requestMatchers().antMatchers("/login", "/oauth/authorize", "/oauth/confirm_access")
                    .and()
                    .authorizeRequests()
                    .anyRequest().authenticated();

        }
        
        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.parentAuthenticationManager(authenticationManager);
        }
    }
}

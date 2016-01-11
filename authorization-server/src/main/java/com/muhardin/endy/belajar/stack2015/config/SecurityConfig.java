package com.muhardin.endy.belajar.stack2015.config;

import com.muhardin.endy.belajar.stack2015.handler.Oauth2LogoutHandler;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

@EnableWebSecurity
public class SecurityConfig {

    @Configuration
    public static class WebappSecurityConfig extends WebSecurityConfigurerAdapter {

        @Autowired private DataSource dataSource;
        
        private static final String SQL_LOGIN
            = "select u.username as username,p.user_password as password, active "
            + "from c_security_user u "
            + "inner join c_security_user_password p on p.id_user = u.id "
            + "where username = ?";

        private static final String SQL_ROLE
            = "select u.username, p.permission_value as authority "
            + "from c_security_user u "
            + "inner join c_security_role r on u.id_role = r.id "
            + "inner join c_security_role_permission rp on rp.id_role = r.id "
            + "inner join c_security_permission p on rp.id_permission = p.id "
            + "where u.username = ?";
    
    
        @Autowired
        public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
            auth.authenticationProvider(daoAuthenticationProvider());
        }
        
        @Bean
        public PasswordEncoder passwordEncoder(){
            return new BCryptPasswordEncoder(13);
        }

        @Bean
        public AuthenticationProvider daoAuthenticationProvider(){
            DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
            provider.setPasswordEncoder(passwordEncoder());
            provider.setUserDetailsService(userDetailsService());
            return provider;
        }

        @Bean
        @Override
        public UserDetailsService userDetailsService(){
            JdbcDaoImpl userDetails = new JdbcDaoImpl();
            userDetails.setDataSource(dataSource);
            userDetails.setUsersByUsernameQuery(SQL_LOGIN);
            userDetails.setAuthoritiesByUsernameQuery(SQL_ROLE);
            return userDetails;
        }
    }

    @Configuration
    @Order(-10)
    public static class OAuthSecurityConfig extends WebSecurityConfigurerAdapter {

        @Autowired
        private AuthenticationManager authenticationManager;
        
        @Autowired private Oauth2LogoutHandler logoutHandler;
    
        @Bean 
        public SessionRegistry sessionRegistry(){
            return new SessionRegistryImpl();
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                .sessionManagement()
                .maximumSessions(1)
                .maxSessionsPreventsLogin(false)
                .sessionRegistry(sessionRegistry());

            http
                .authorizeRequests()
                    .antMatchers("/login").permitAll()
                    .antMatchers("/logout").permitAll()
                    .antMatchers("/css/**").permitAll()
                    .anyRequest().authenticated()
                .and()
                    .formLogin().loginPage("/login").permitAll()
                .and()
                    .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .invalidateHttpSession(true)
                    .logoutSuccessHandler(logoutHandler)
                .and()
                    .csrf().csrfTokenRepository(csrfTokenRepository())
                    .and().addFilterAfter(new CsrfHeaderFilter(), CsrfFilter.class);
        }
        
        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.parentAuthenticationManager(authenticationManager);
        }
        
        private CsrfTokenRepository csrfTokenRepository() {
            HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
            repository.setHeaderName("X-XSRF-TOKEN");
            return repository;
        }
    }
    

    private static class CsrfHeaderFilter extends OncePerRequestFilter {

        @Override
        protected void doFilterInternal(HttpServletRequest request,
                HttpServletResponse response, FilterChain filterChain)
                throws ServletException, IOException {
            
            logger.info("CSRF-TOKEN NAME ["+CsrfToken.class.getName()+"]");
            CsrfToken csrf = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
            if (csrf != null) {
                Cookie cookie = WebUtils.getCookie(request, "XSRF-TOKEN");
                String token = csrf.getToken();
                if(cookie!=null) logger.info("Cookie [" + cookie.getValue() + "]");
                logger.info("Token [" + token + "]");
                
                if (cookie == null || token != null && !token.equals(cookie.getValue())) {
                    cookie = new Cookie("XSRF-TOKEN", token);
                    cookie.setPath("/auth-server/");
                    response.addCookie(cookie);
                }
            }
            filterChain.doFilter(request, response);
        }
    }
}

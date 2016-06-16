package com.muhardin.endy.belajar.stack2015;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableZuulProxy
//@EnableOAuth2Sso
public class UiWeb {

    public static void main(String[] args) {
        SpringApplication.run(UiWeb.class, args);
    }
    
    @Bean
    public CustomFilter customFilter(){
        return new CustomFilter();
    }

//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        http.logout()
//                .and()
//                    .antMatcher("/**").authorizeRequests()
//                    .antMatchers("/index.html", "/home.html", "/", "/login", "/scripts/**", "/styles/**").permitAll()
//                    .anyRequest().authenticated()
//                .and()
//                    .csrf()
//                        .csrfTokenRepository(csrfTokenRepository()).and()
//                        .addFilterAfter(csrfHeaderFilter(), CsrfFilter.class);
//    }
//
//    private Filter csrfHeaderFilter() {
//        return new OncePerRequestFilter() {
//            @Override
//            protected void doFilterInternal(HttpServletRequest request,
//                    HttpServletResponse response, FilterChain filterChain)
//                    throws ServletException, IOException {
//                CsrfToken csrf = (CsrfToken) request
//                        .getAttribute(CsrfToken.class.getName());
//                if (csrf != null) {
//                    Cookie cookie = WebUtils.getCookie(request, "XSRF-TOKEN");
//                    String token = csrf.getToken();
//                    if (cookie == null
//                            || token != null && !token.equals(cookie.getValue())) {
//                        cookie = new Cookie("XSRF-TOKEN", token);
//                        cookie.setPath("/");
//                        response.addCookie(cookie);
//                    }
//                }
//                filterChain.doFilter(request, response);
//            }
//        };
//    }
//
//    private CsrfTokenRepository csrfTokenRepository() {
//        HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
//        repository.setHeaderName("X-XSRF-TOKEN");
//        return repository;
//    }
}

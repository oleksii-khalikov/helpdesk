package com.javamog.potapov.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    /*@Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("user1_mogilev@yopmail.com").password("1234").roles("EMPLOYEE");
        //auth.inMemoryAuthentication().withUser("user2_mogilev@yopmail.com").password("12345").roles("MANAGER");
    }*/

    @Autowired
    @Qualifier("customUserDetailsService")
    UserDetailsService userDetailsService;

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/").permitAll()
                //.antMatchers("/ticketList/**").access("hasRole('EMPLOYEE') and hasRole('MANAGER')")
                //.antMatchers("/edit/**").access("hasRole('MANAGER')")
                .and().formLogin().loginPage("/")
                .loginProcessingUrl("/login")
                .usernameParameter("email").passwordParameter("password")
                .successHandler((req, res, auth) -> {
                    res.sendRedirect("/ticketList");
                })
                .failureHandler((req, res, auth) -> {
                    res.sendRedirect("/Access_Denied");
                }).permitAll()
                .and().exceptionHandling().accessDeniedPage("/Access_Denied")
                .and().csrf().disable();
    }

}

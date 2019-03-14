package com.order.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;



//@Configuration
//@EnableWebSecurity
public class IgnoreDefaultSecurityConfig extends WebSecurityConfigurerAdapter {

    /*
    覆盖默认的认证filterchain
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/**").permitAll();//hasRole("USER")
                //.and().csrf().disable()
                //.and()
                //.formLogin().loginPage("/login").failureUrl("/login");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //super.configure(auth);
    }
}

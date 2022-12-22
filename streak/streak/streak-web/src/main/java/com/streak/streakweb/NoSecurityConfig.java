package com.streak.streakweb;

import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Order(1)
public class NoSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity security) throws Exception
    {

        //security.authorizeRequests().antMatchers("/actuator/**").authenticated().and().httpBasic().and().csrf().disable();
        security.httpBasic().and().csrf().disable();
        //security.httpBasic().disable();
    }
}
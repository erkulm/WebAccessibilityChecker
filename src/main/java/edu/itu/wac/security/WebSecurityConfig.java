package edu.itu.wac.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.header.writers.StaticHeadersWriter;

@Configuration
@EnableWebSecurity // (1)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter { // (1)

    @Override
    protected void configure(HttpSecurity http) throws Exception {  // (2)
        http
                .authorizeRequests()
                .antMatchers("/**").permitAll();

        http.cors().and().csrf().disable();

        http
                .headers().frameOptions().disable()
                .addHeaderWriter(new StaticHeadersWriter(
                        "X-FRAME-OPTIONS", "ALLOW-FROM localhost:8080"));

    }
}
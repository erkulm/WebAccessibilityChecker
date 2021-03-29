package edu.itu.wac.security;

import edu.itu.wac.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.header.writers.StaticHeadersWriter;

@Configuration
@EnableWebSecurity // (1)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter { // (1)

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    UserService userService;

    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
//        auth.inMemoryAuthentication()
//                .withUser("user1").password(passwordEncoder().encode("user1Pass")).roles("USER")
//                .and()
//                .withUser("user2").password(passwordEncoder().encode("user2Pass")).roles("USER")
//                .and()
//                .withUser("mahmut").password(passwordEncoder().encode("SA")).roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {  // (2)
        http
                .authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/login*").permitAll()
                .antMatchers("/images/**").permitAll()
                .anyRequest().authenticated()

              .and()
              .formLogin()
              .loginPage("/login")
              .loginProcessingUrl("/login_perform")
              .defaultSuccessUrl("/index", true)
              .failureUrl("/login?error=true")
              .and()
              .logout()
              .logoutUrl("/perform_logout")
              .deleteCookies("JSESSIONID");

        http.cors().and().csrf().disable();

        http
                .headers().frameOptions().disable()
                .addHeaderWriter(new StaticHeadersWriter(
                        "X-FRAME-OPTIONS", "ALLOW-FROM localhost:8080"));

    }
}
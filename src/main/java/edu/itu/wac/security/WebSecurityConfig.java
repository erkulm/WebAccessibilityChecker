package edu.itu.wac.security;

import edu.itu.wac.service.UserService;
import org.apache.commons.lang3.SystemUtils;
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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableWebSecurity // (1)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter { // (1)

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Autowired UserService userService;

  @Override
  protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userService);
  }

  @Bean
  protected CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOrigins(Collections.singletonList("http://localhost:8081"));
    configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception { // (2)
    http.csrf()
        .disable()
        .cors()
        .and()
        .authorizeRequests()
        .antMatchers("/admin/**")
        .hasRole("ADMIN")
        .antMatchers("/login*")
        .permitAll()
        .antMatchers("**")
        .permitAll()
        .antMatchers("/images/**")
        .permitAll()
        .anyRequest()
        .authenticated()
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

    http.headers()
        .frameOptions()
        .disable()
        .addHeaderWriter(new StaticHeadersWriter("X-FRAME-OPTIONS", "ALLOW-FROM localhost:8080"));

    if (SystemUtils.IS_OS_LINUX) {
      http.requiresChannel().anyRequest().requiresSecure();
    }
  }
}

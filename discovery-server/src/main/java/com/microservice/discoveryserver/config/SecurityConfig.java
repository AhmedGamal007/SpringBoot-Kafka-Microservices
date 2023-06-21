package com.microservice.discoveryserver.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityConfig{

    @Value("${}")
    @Bean
    public SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception{

        AuthenticationManagerBuilder authenticationManagerBuilder = httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.inMemoryAuthentication()
                        .withUser("eureka")
                                .password("password")
                                        .authorities("USER");
        httpSecurity.csrf(csrf -> csrf.disable())
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
        return httpSecurity.build();
    }
}

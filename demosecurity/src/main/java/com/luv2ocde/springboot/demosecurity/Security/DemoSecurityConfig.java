package com.luv2ocde.springboot.demosecurity.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.net.http.HttpRequest;

@Configuration
public class DemoSecurityConfig {


    // Configuration to reference custom login
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(configurer ->
                        configurer
                                .requestMatchers("/").hasRole("Employee")
                                .requestMatchers("/leaders/**").hasRole("Manager")
                                .requestMatchers("/systems/**").hasRole("Admin")
                                .anyRequest().authenticated()
                )
                .exceptionHandling(configurer ->
                        configurer.accessDeniedPage("/access-denied")

                )
                .formLogin(form ->
                        form
                                .loginPage("/showLoginPage")
                                .loginProcessingUrl("/authenticateUser")
                                .permitAll()
                )
                .logout(logout -> logout.permitAll() // enable logout
                );

        return http.build();
    }


}

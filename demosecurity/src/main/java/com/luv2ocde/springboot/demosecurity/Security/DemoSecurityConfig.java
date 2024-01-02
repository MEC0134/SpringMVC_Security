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

    // Create Demo Users
    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {

        UserDetails john = User.builder()
                .username("John")
                .password("{noop}test123")
                .roles("Employee").build();

        UserDetails mary = User.builder()
                .username("Mary")
                .password("{noop}test123")
                .roles("Employee, Manager").build();

        UserDetails susan = User.builder()
                .username("Susan")
                .password("{noop}test123")
                .roles("Employee, Manager, Admin").build();


        return new InMemoryUserDetailsManager(john, mary, susan);
    }


    // Configuration to reference custom login
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(configurer ->
                        configurer.anyRequest().authenticated()
                )
                .formLogin(form ->
                        form
                                .loginPage("/showLoginPage")
                                .loginProcessingUrl("/authenticateUser")
                                .permitAll()
                );

        return http.build();
    }

}

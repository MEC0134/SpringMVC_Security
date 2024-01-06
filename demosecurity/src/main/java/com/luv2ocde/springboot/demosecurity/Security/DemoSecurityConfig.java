package com.luv2ocde.springboot.demosecurity.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import javax.sql.DataSource;

@Configuration
public class DemoSecurityConfig {


    /*
    1. Inject data source Auto-configured by Spring B
    2. Tell Spring Sec to use JDBC authentication,
       it will look for users and authorities tables
    */
    @Bean
    public UserDetailsManager userDetailsManager (DataSource dataSource) {

        return new JdbcUserDetailsManager(dataSource);
    }


    // Configuration to reference custom login
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(configurer ->
                        configurer
                                .requestMatchers("/").hasRole("EMPLOYEE")
                                .requestMatchers("/leaders/**").hasRole("MANAGER")
                                .requestMatchers("/systems/**").hasRole("ADMIN")
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

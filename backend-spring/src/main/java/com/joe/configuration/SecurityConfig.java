package com.joe.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity; // For @PreAuthorize etc.
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService,
                                                       PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        return new ProviderManager(authenticationProvider);
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Disable CSRF for stateless APIs
                .authorizeHttpRequests(authorize -> authorize
                        // Allow public access to employee addition and read endpoints
                        .requestMatchers("/api/shop/auth/login").permitAll()
                        .requestMatchers("/api/shop/employees/add").permitAll() // Employee registration
                        .requestMatchers("/api/shop/employees/get/{emp_num}").permitAll() // Get specific employee
                        .requestMatchers("/api/shop/employees").permitAll() // Get all employees

                        // All other /api/shop/ endpoints require authentication
                        .requestMatchers("/api/shop/employees/remove/{emp_num}").hasRole("ADMIN") // Requires ADMIN role
                        .requestMatchers("/api/shop/**").authenticated() // All other /api/shop/ paths need auth




                        .anyRequest().permitAll()
                )
                .sessionManagement(session -> session

                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .httpBasic(org.springframework.security.config.Customizer.withDefaults()); // Enable HTTP Basic Authentication


        return http.build();
    }
}

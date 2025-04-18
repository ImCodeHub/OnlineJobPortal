package com.example.OnlineJobPortal.Configuration;

import com.example.OnlineJobPortal.Filter.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    // Injecting custom authentication provider for handling user credentials
    @Autowired
    private final AuthenticationProvider authenticationProvider;

    // Injecting custom JWT filter to intercept requests and validate tokens
    @Autowired
    private final JwtFilter jwtFilter;

    // Defining the security filter chain for the application
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                // Disable CSRF since this is a stateless REST API using JWT
                .csrf(AbstractHttpConfigurer::disable)
                // Configure URL-based access rules
                .authorizeHttpRequests(req ->req
                        // Allow all requests to auth endpoints (login, register, etc.)
                        .requestMatchers("/auth/api/v1/*")
                        .permitAll()
                        .requestMatchers("/api/jobSeeker/v1/**").hasRole("JOBSEEKER")
                        .requestMatchers("/hr/api/**").hasAnyRole("HR","ADMIN")
                )
                // Set session management to stateless (no HTTP session will be used)
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                // Use the custom authentication provider for authentication logic
                .authenticationProvider(authenticationProvider)
                // Add the custom JWT filter before the default UsernamePasswordAuthenticationFilter
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                // Build and return the configured security filter chain
                .build();
    }
}

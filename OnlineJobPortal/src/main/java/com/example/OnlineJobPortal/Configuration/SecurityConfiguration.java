package com.example.OnlineJobPortal.Configuration;

import com.example.OnlineJobPortal.Filter.JwtFilter;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity(prePostEnabled = true)
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
                        .requestMatchers("/auth/api/v1/**","/v3/api-docs/**","/v3/api-docs",
                                "/swagger-ui/**",
                                "/swagger-ui.html")
                        .permitAll()
                        .requestMatchers("/v3/api-docs/**", "/swagger-ui/**").permitAll()
                        .requestMatchers("/api/jobSeeker/v1/**").hasRole("JOBSEEKER")
                        .requestMatchers("/api/hr/v1/**").hasRole("HR")
                        .anyRequest()
                        .authenticated()
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

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // allow all endpoints
                        .allowedOrigins("http://localhost:5173/") // your frontend port
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }

}

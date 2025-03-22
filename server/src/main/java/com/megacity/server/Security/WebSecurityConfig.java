package com.megacity.server.Security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.megacity.server.Security.Jwt.AuthEntryPoint;
import com.megacity.server.Security.Jwt.AuthTokenFilter;

@Configuration
@EnableMethodSecurity
public class WebSecurityConfig {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private AuthEntryPoint unauthorizedHandler;

    @Bean
    public UserDetailsService userDetailsService() {
        return userDetailsService;
    }

    @Bean
    public AuthTokenFilter authenticAuthTokenFilter() {
        return new AuthTokenFilter();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.setAllowedOrigins(List.of("http://localhost:5173")); // Allow frontend URL
        corsConfig.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS")); // Allowed HTTP methods
        corsConfig.setAllowedHeaders(List.of("*")); // Allow all headers
        corsConfig.setAllowCredentials(true); // Allow credentials (e.g., cookies)

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig); // Apply to all endpoints
        return source;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource())) // Enable CORS
                .csrf(csrf -> csrf.disable()) // Disable CSRF for stateless APIs
                .exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler)) // Handle unauthorized requests
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Stateless session
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**").permitAll() // Public endpoints
                        .requestMatchers("/all/**").permitAll() // Public endpoints
                        .requestMatchers("/admin/**").hasAuthority("ROLE_ADMIN") // Admin-only endpoints
                        .requestMatchers("/vehicle/**").hasAuthority("ROLE_VEHICLE") // Vehicle-only endpoints
                        .requestMatchers("/customer/**").hasAuthority("ROLE_CUSTOMER") // Customer-only endpoints
                        .requestMatchers("/driver/**").hasAuthority("ROLE_DRIVER") // Driver-only endpoints
                        .anyRequest().authenticated() // All other endpoints require authentication
                );

        // Add JWT filter
        http.authenticationProvider(authenticationProvider());
        http.addFilterBefore(authenticAuthTokenFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
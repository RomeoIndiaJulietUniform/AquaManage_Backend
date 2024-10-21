package com.DragonFish.aquaManage.UserService.Config;

import com.DragonFish.aquaManage.UserService.JwtFilter.Filter;
import com.DragonFish.aquaManage.UserService.Utility.JwtUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Creates the JWT filter bean to handle authentication by validating
     * the JWT token in each request. The filter uses UserDetailsService and JwtUtil.
     */
    @Bean
    public Filter jwtFilter(UserDetailsService userDetailsService, JwtUtil jwtUtil) {
        return new Filter(userDetailsService, jwtUtil);
    }

    /**
     * Configures the security filter chain, which is responsible for
     * securing the application. The JWT filter is added to intercept
     * the requests before processing them.
     *
     * @param http       the HttpSecurity object used for configuring web-based security.
     * @param jwtFilter  the JWT filter used to validate the token before the request reaches the controller.
     * @return SecurityFilterChain which contains security configurations for the application.
     * @throws Exception if an error occurs during the configuration.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, Filter jwtFilter) throws Exception {
        http
                // Enable Cross-Origin Resource Sharing (CORS)
                .cors().and()
                // Disable CSRF protection (stateless APIs don't need it)
                .csrf(csrf -> csrf.disable())
                // Define public endpoints that do not require authentication
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**", "/login", "/users").permitAll() // Publicly accessible endpoints
                        .anyRequest().authenticated() // All other requests require authentication
                )
                // Set session management policy to stateless (no session is created)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(org.springframework.security.config.http.SessionCreationPolicy.STATELESS)
                );

        // Add the JWT filter to check JWT tokens before the authentication process
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    /**
     * Configures CORS settings, allowing the specified origins, methods, and headers
     * to be used in cross-origin requests.
     *
     * @return CorsConfigurationSource which defines CORS settings.
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:5173", "http://localhost:8081")); // Define allowed origins
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS")); // Allowed HTTP methods
        configuration.setAllowedHeaders(Arrays.asList("*")); // Allow any headers
        configuration.setAllowCredentials(true); // Allow sending credentials with requests
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // Apply CORS configuration to all endpoints
        return source;
    }

    /**
     * Defines a PasswordEncoder bean using BCrypt hashing algorithm.
     * This will be used to encrypt passwords.
     *
     * @return BCryptPasswordEncoder instance.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Provides an AuthenticationManager bean which is required for authenticating
     * user credentials during the login process.
     *
     * @param authenticationConfiguration the authentication configuration that contains authentication settings.
     * @return AuthenticationManager instance used for authenticating users.
     * @throws Exception if an error occurs while obtaining the authentication manager.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}

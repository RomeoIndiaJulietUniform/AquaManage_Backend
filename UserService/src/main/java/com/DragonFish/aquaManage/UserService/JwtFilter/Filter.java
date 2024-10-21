package com.DragonFish.aquaManage.UserService.JwtFilter;

import com.DragonFish.aquaManage.UserService.Utility.JwtUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Custom filter that extends OncePerRequestFilter to intercept each request,
 * extract the JWT token from the Authorization header, validate it,
 * and set up the authentication in the SecurityContext.
 */
public class Filter extends OncePerRequestFilter {

    // Dependencies: UserDetailsService to load user data, and JwtUtil to handle JWT operations
    private final UserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;

    /**
     * Constructor to initialize Filter with the required services.
     *
     * @param userDetailsService Service to load user details.
     * @param jwtUtil Utility to extract and validate JWT.
     */
    public Filter(UserDetailsService userDetailsService, JwtUtil jwtUtil) {
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
    }

    /**
     * This method is executed for every incoming request.
     * It extracts the JWT token from the Authorization header, validates the token,
     * and sets up authentication if the token is valid.
     *
     * @param request The incoming HTTP request.
     * @param response The outgoing HTTP response.
     * @param chain The filter chain to pass the request to the next filter.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        // Extract the Authorization header
        final String authorizationHeader = request.getHeader("Authorization");

        String username = null;
        String jwt = null;

        // Check if the Authorization header contains a Bearer token
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            // Extract the JWT by removing the "Bearer " prefix
            jwt = authorizationHeader.substring(7);
            // Extract the username from the JWT
            username = jwtUtil.extractUsername(jwt);
        }

        // Validate the token and set up authentication
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // Load user details using the extracted username
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

            // Validate the token using the username
            if (jwtUtil.validateToken(jwt, userDetails.getUsername())) {
                // Create an authentication token based on the validated user details
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());

                // Set additional details about the request
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // Set the authenticated user in the SecurityContext
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }

        // Pass the request to the next filter in the chain
        chain.doFilter(request, response);
    }
}

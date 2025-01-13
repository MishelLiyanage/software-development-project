package com.SDP.project.shared.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

/**
 * A custom filter for processing JWT-based authentication.
 * This filter is executed once per request and validates JWT tokens.
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    // Resolver for handling exceptions during filter execution
    private final HandlerExceptionResolver handlerExceptionResolver;

    // Service for working with JWT tokens
    private final JwtService jwtService;

    // Service for loading user-specific details
    private final UserDetailsService userDetailsService;

//    Constructor to inject dependencies into the filter.
    public JwtAuthenticationFilter(
            JwtService jwtService,
            UserDetailsService userDetailsService,
            HandlerExceptionResolver handlerExceptionResolver
    ) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
        this.handlerExceptionResolver = handlerExceptionResolver;
    }

//    Method to perform JWT validation and authentication for incoming requests.
    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        // Retrieve the Authorization header from the request
        final String authHeader = request.getHeader("Authorization");

        // Check if the Authorization header is present and starts with "Bearer "
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response); // Proceed without authentication
            return;
        }

        try {
            // Extract the JWT token from the Authorization header
            final String jwt = authHeader.substring(7);

            // Extract the username from the token
            final String username = jwtService.extractUsername(jwt);

            // Check if the user is already authenticated
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if (username != null && authentication == null) {
                // Load user details from the database
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

                System.out.println("user-details: " + userDetails.getUsername() + " " + userDetails.getPassword() + " " + userDetails.getAuthorities() + " " + userDetails.isEnabled());


                // Validate the JWT token against the user details
                if (jwtService.isTokenValid(jwt, userDetails)) {
                    // Create an authentication token for the user
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );

                    // Set additional details for the authentication token
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    // Set the authentication in the security context
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }

            // Continue processing the request
            filterChain.doFilter(request, response);
        } catch (Exception exception) {
            // Handle exceptions using the exception resolver
            handlerExceptionResolver.resolveException(request, response, null, exception);
        }
    }
}

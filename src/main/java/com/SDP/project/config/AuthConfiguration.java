package com.SDP.project.config;

import com.SDP.project.Repository.AccountRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


//Configuration class for authentication-related beans and settings.
@Configuration
public class AuthConfiguration {

    // Repository for accessing account details from the database
    private final AccountRepository accountRepository;

//     Constructor to initialize AuthConfiguration with AccountRepository dependency.
    public AuthConfiguration(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


//    Defines a custom UserDetailsService bean to load user-specific data during authentication.
    @Bean
    UserDetailsService userDetailsService() {
        return username -> accountRepository.findByUsernameIgnoreCase(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }


//     Defines a BCryptPasswordEncoder bean to encode passwords securely.
    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    Configures and provides an AuthenticationManager bean for managing authentication processes.
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }


//    Configures an AuthenticationProvider bean using DAO-based authentication.
    @Bean
    AuthenticationProvider authenticationProvider() {
        // Create a DAO-based AuthenticationProvider
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        // Set the custom UserDetailsService to fetch user data
        authProvider.setUserDetailsService(userDetailsService());

        // Set the password encoder for validating passwords
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }
}

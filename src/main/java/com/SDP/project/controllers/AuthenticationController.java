package com.SDP.project.controllers;

import com.SDP.project.DTOs.LoginUserDto;
import com.SDP.project.Repository.AccountRepository;
import com.SDP.project.responses.LoginResponse;
import com.SDP.project.services.AuthService;
import com.SDP.project.services.JwtService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {
    private final JwtService jwtService;

    private final AuthService authService;
    private final AccountRepository accountRepository;

    public AuthenticationController(JwtService jwtService, AuthService authService, AccountRepository accountRepository) {
        this.jwtService = jwtService;
        this.authService = authService;
        this.accountRepository = accountRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@Valid @RequestBody LoginUserDto loginUserDto) {
        System.out.println(loginUserDto.getUsername());
        UserDetails authenticatedUser = authService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse().setToken(jwtToken).setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }
}
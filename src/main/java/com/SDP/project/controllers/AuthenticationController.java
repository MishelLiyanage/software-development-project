package com.SDP.project.controllers;

import com.SDP.project.DTOs.LoginUserDto;
import com.SDP.project.Repository.AccountRepository;
import com.SDP.project.models.Account;
import com.SDP.project.responses.LoginResponse;
import com.SDP.project.services.AuthService;
import com.SDP.project.shared.exceptions.NotFoundException;
import com.SDP.project.shared.security.JwtService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("/account")
@RestController
public class AuthenticationController {
    private final JwtService jwtService;

    private final AuthService authService;

    public AuthenticationController(JwtService jwtService, AuthService authService) {
        this.jwtService = jwtService;
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@Valid @RequestBody LoginUserDto loginUserDto) {
        Account authenticatedUser = authService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = LoginResponse.builder()
                .accessToken(jwtToken)
                .expiresIn(jwtService.getExpirationTime())
                .build();

        return ResponseEntity.ok(loginResponse);
    }

    @GetMapping("/currentuser")
    public ResponseEntity<?> authenticatedUser() {
        Optional<?> currentUser = authService.getCurrentUser();

        if (currentUser.isPresent()) {
            return ResponseEntity.ok(currentUser.get());
        } else {
            throw new NotFoundException("No authenticated user found or invalid role.");
        }
    }
}
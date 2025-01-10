package com.SDP.project.services.impli;

import com.SDP.project.DTOs.LoginUserDto;
import com.SDP.project.Repository.AccountRepository;
import com.SDP.project.models.Account;
import com.SDP.project.services.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AuthServiceImpli implements AuthService {
    private final AccountRepository accountRepository;

    private final AuthenticationManager authenticationManager;

    public AuthServiceImpli( AccountRepository accountRepository, AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        this.accountRepository = accountRepository;
    }

    public Account authenticate(LoginUserDto input) {
        System.out.println(input.getUsername());
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getUsername(),
                        input.getPassword()
                )
        );

        return accountRepository.findByUsernameIgnoreCase(input.getUsername())
                .orElseThrow();
    }

}

package com.SDP.project.services;

import com.SDP.project.DTOs.LoginUserDto;
import com.SDP.project.models.Account;

import java.util.Optional;

public interface AuthService {
    Account authenticate(LoginUserDto input);

    Optional<?> getCurrentUser();
}

package com.SDP.project.services;

import com.SDP.project.DTOs.LoginUserDto;
import com.SDP.project.models.Account;

public interface AuthService {
    Account authenticate(LoginUserDto input);
}

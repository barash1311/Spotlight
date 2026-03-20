package com.barash.spotlight.service;

import com.barash.spotlight.dto.Auth.LoginRequest;
import com.barash.spotlight.dto.Auth.LoginResponse;


public interface AuthService {
    LoginResponse login(LoginRequest request);
}

package com.example.inventory.service;

import com.example.inventory.dto.AuthResponse;
import com.example.inventory.dto.LoginRequest;
import com.example.inventory.dto.RegisterRequest;

public interface AuthService {

    AuthResponse register(RegisterRequest request);

    AuthResponse login(LoginRequest request);
}

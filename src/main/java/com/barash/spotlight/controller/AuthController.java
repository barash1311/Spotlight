package com.barash.spotlight.controller;

import com.barash.spotlight.dto.ApiResponse;
import com.barash.spotlight.dto.Auth.LoginRequest;
import com.barash.spotlight.dto.Auth.LoginResponse;
import com.barash.spotlight.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Auth", description = "Authentication — obtain a JWT token")
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "Login", description = "Authenticate with username/email and password. Returns a JWT token.",
               security = {})   // explicitly no auth required
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(
            @Valid @RequestBody LoginRequest request) {

        LoginResponse loginResponse = authService.login(request);
        return ResponseEntity.ok(ApiResponse.ok(loginResponse, "Login successful"));
    }
}

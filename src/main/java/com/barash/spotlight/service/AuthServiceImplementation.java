package com.barash.spotlight.service;

import com.barash.spotlight.dto.Auth.LoginRequest;
import com.barash.spotlight.dto.Auth.LoginResponse;
import com.barash.spotlight.entity.User;
import com.barash.spotlight.repository.UserRepository;
import com.barash.spotlight.security.JwtUtil;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImplementation implements AuthService{
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    @Override
    public LoginResponse login(LoginRequest request) {

        String identifier = request.getIdentifier() != null ? request.getIdentifier().trim() : null;
        String password = request.getPassword();

        if (identifier == null || identifier.isEmpty() || password == null || password.isEmpty()) {
            throw new BadCredentialsException("Invalid credentials");
        }

        User user;

        if (isEmail(identifier)) {
            user = userRepository.findByEmail(identifier)
                    .orElseThrow(() -> new BadCredentialsException("Invalid credentials"));
        } else {
            user = userRepository.findByUsername(identifier)
                    .orElseThrow(() -> new BadCredentialsException("Invalid credentials"));
        }

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("Invalid credentials");
        }

        String token = jwtUtil.generateToken(user.getUsername(), user.getRole().name());

        return LoginResponse.builder()
                .username(user.getUsername())
                .token(token)
                .build();
    }
    private boolean isEmail(String identifier){
        return identifier.contains("@");
    }


}

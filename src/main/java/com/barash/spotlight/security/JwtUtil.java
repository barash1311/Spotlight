package com.barash.spotlight.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

@Component
public class JwtUtil {
    private static final long EXPIRY_MS = 1000L * 60 * 60; // 1 hour
    
    @Value("${jwt.secret:b9f3a7e4c2d8f1a0b6e9d4c3a1f7b2e5}")
    private String secretKey;

    public String generateToken(String username, String role) {
        long expiry = System.currentTimeMillis() + EXPIRY_MS;
        String payload = username + ":" + role + ":" + expiry;
        String signature = sign(payload);
        String rawToken = payload + ":" + signature;
        return Base64.getUrlEncoder().withoutPadding()
                .encodeToString(rawToken.getBytes(StandardCharsets.UTF_8));
    }

    public String extractUsername(String token) {
        String[] parts = decodeAndSplit(token);
        if (parts.length != 4) {
            throw new IllegalArgumentException("Invalid token");
        }
        return parts[0];
    }

    public String extractRole(String token) {
        String[] parts = decodeAndSplit(token);
        if (parts.length != 4) {
            throw new IllegalArgumentException("Invalid token");
        }
        return parts[1];
    }

    public boolean isTokenValid(String token) {
        try {
            String[] parts = decodeAndSplit(token);
            if (parts.length != 4) {
                return false;
            }

            String username = parts[0];
            String role = parts[1];
            long expiry = Long.parseLong(parts[2]);
            String providedSignature = parts[3];

            if (username == null || username.isBlank() || role == null || role.isBlank()) {
                return false;
            }
            if (System.currentTimeMillis() > expiry) {
                return false;
            }

            String expectedSignature = sign(username + ":" + role + ":" + expiry);
            return MessageDigest.isEqual(
                    expectedSignature.getBytes(StandardCharsets.UTF_8),
                    providedSignature.getBytes(StandardCharsets.UTF_8)
            );
        } catch (Exception e) {
            return false;
        }
    }

    private String[] decodeAndSplit(String token) {
        byte[] decodedBytes = Base64.getUrlDecoder().decode(token);
        String decoded = new String(decodedBytes, StandardCharsets.UTF_8);
        return decoded.split(":", 4);
    }

    private String sign(String payload) {
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "HmacSHA256"));
            byte[] hash = mac.doFinal(payload.getBytes(StandardCharsets.UTF_8));
            return Base64.getUrlEncoder().withoutPadding().encodeToString(hash);
        } catch (Exception e) {
            throw new IllegalStateException("Could not sign token", e);
        }
    }
}

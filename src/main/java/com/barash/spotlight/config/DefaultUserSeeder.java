package com.barash.spotlight.config;

import com.barash.spotlight.entity.Roles;
import com.barash.spotlight.entity.User;
import com.barash.spotlight.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DefaultUserSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${app.seed.default-user.enabled:true}")
    private boolean seedEnabled;

    @Value("${app.seed.default-user.username:admin}")
    private String username;

    @Value("${app.seed.default-user.email:admin@spotlight.local}")
    private String email;

    @Value("${app.seed.default-user.password:admin123}")
    private String rawPassword;

    @Override
    public void run(String @NonNull ... args) {
        if (!seedEnabled) {
            return;
        }

        Optional<User> existingByUsername = userRepository.findByUsername(username);
        Optional<User> existingByEmail = userRepository.findByEmail(email);

        User defaultUser = existingByUsername.or(() -> existingByEmail).orElseGet(() -> User.builder()
                .username(username)
                .email(email)
                .role(Roles.ADMIN)
                .build());

        // Keep local/dev login deterministic without a registration endpoint.
        defaultUser.setUsername(username);
        defaultUser.setEmail(email);
        defaultUser.setRole(Roles.ADMIN);
        defaultUser.setPassword(passwordEncoder.encode(rawPassword));

        userRepository.save(defaultUser);
    }
}

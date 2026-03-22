package com.barash.spotlight.config;

import com.barash.spotlight.dto.ApiResponse;
import com.barash.spotlight.security.JwtFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    private static final Logger log = LoggerFactory.getLogger(SecurityConfig.class);
    private final ObjectMapper objectMapper = new ObjectMapper().findAndRegisterModules();

    @Value("${cors.allowed-origins:http://localhost:3000,http://localhost:5173}")
    private String allowedOriginsRaw;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtFilter jwtFilter) throws Exception {
        http
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            .csrf(AbstractHttpConfigurer::disable)
            .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

            // ── Custom 401 / 403 JSON responses (consistent ApiResponse format) ──
            .exceptionHandling(ex -> ex
                .authenticationEntryPoint((request, response, authException) -> {
                    log.warn("401 Unauthorised: {} {}", request.getMethod(), request.getRequestURI());
                    response.setStatus(401);
                    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                    objectMapper.writeValue(response.getOutputStream(),
                            ApiResponse.error("Unauthorised — authentication required"));
                })
                .accessDeniedHandler((request, response, accessDeniedException) -> {
                    log.warn("403 Forbidden: {} {} by '{}'", request.getMethod(), request.getRequestURI(),
                            request.getUserPrincipal() != null ? request.getUserPrincipal().getName() : "anonymous");
                    response.setStatus(403);
                    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                    objectMapper.writeValue(response.getOutputStream(),
                            ApiResponse.error("Forbidden — insufficient privileges"));
                })
            )

            // ── Route security ────────────────────────────────────────────────
            .authorizeHttpRequests(auth -> auth
                // Auth endpoint — always public
                .requestMatchers("/api/auth/**").permitAll()

                // OpenAPI / Swagger UI — always public
                .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()

                // Public read endpoints — no token needed
                .requestMatchers(HttpMethod.GET, "/api/content/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/projects/public/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/skills").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/profile").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/experiences").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/contact").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/resumes/**").permitAll()
                .requestMatchers(HttpMethod.HEAD, "/api/resumes/**").permitAll()

                // Admin endpoints — ADMIN role required
                .requestMatchers("/api/admin/**").hasRole("ADMIN")

                // Everything else must at least be authenticated
                .anyRequest().authenticated()
            )
            .headers(headers -> headers.frameOptions(frame -> frame.disable()))
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        List<String> origins = Arrays.asList(allowedOriginsRaw.split(","));

        CorsConfiguration cfg = new CorsConfiguration();
        cfg.setAllowedOrigins(origins);
        cfg.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
        cfg.setAllowedHeaders(List.of("Authorization", "Content-Type", "Accept"));
        cfg.setExposedHeaders(List.of("Authorization"));
        cfg.setAllowCredentials(true);
        cfg.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", cfg);
        return source;
    }
}


package com.barash.spotlight.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    private static final String BEARER_SCHEME = "bearerAuth";

    @Bean
    public OpenAPI spotlightOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Spotlight Portfolio API")
                        .description("""
                                Backend API for the Spotlight portfolio website.
                                
                                **Public endpoints** require no authentication.
                                **Admin endpoints** require a JWT token obtained via `POST /api/auth/login`.
                                
                                Click the 🔒 **Authorize** button and paste your token to test admin endpoints.
                                """)
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Barash Sharma")
                                .url("https://github.com/barash1311")
                                .email("barash1311@gmail.com")))
                .addSecurityItem(new SecurityRequirement().addList(BEARER_SCHEME))
                .components(new Components()
                        .addSecuritySchemes(BEARER_SCHEME,
                                new SecurityScheme()
                                        .name(BEARER_SCHEME)
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                                        .description("Paste the JWT token from the login response")));
    }
}

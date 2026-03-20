package com.barash.spotlight.controller;

import com.barash.spotlight.dto.*;
import com.barash.spotlight.service.ContactService;
import com.barash.spotlight.service.ProjectService;
import com.barash.spotlight.service.SkillService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * PUBLIC endpoints — no authentication required.
 * SecurityConfig.permitAll() covers all routes in this controller.
 */
@RestController
@RequiredArgsConstructor
@Tag(name = "Public", description = "Public endpoints — no authentication required")
public class PublicController {

    private final ProjectService projectService;
    private final SkillService   skillService;
    private final ContactService contactService;

    // ── Projects ─────────────────────────────────────────────────────────────

    @Operation(summary = "List projects (paginated)", security = {})
    @GetMapping("/api/projects/public")
    public ResponseEntity<ApiResponse<PageResponse<ProjectResponse>>> getPublicProjects(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "6") int size) {

        return ResponseEntity.ok(ApiResponse.ok(projectService.getProjects(page, size)));
    }

    @Operation(summary = "Get project by ID", security = {})
    @GetMapping("/api/projects/public/{id}")
    public ResponseEntity<ApiResponse<ProjectResponse>> getPublicProjectById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok(projectService.getProjectById(id)));
    }

    // ── Skills ────────────────────────────────────────────────────────────────

    @Operation(summary = "List all skills", security = {})
    @GetMapping("/api/skills")
    public ResponseEntity<ApiResponse<List<SkillResponse>>> getSkills() {
        return ResponseEntity.ok(ApiResponse.ok(skillService.getAllSkills()));
    }

    // ── Contact ───────────────────────────────────────────────────────────────

    @Operation(summary = "Submit a contact message", security = {})
    @PostMapping("/api/contact")
    public ResponseEntity<ApiResponse<ContactResponse>> submitContact(
            @Valid @RequestBody ContactRequest request) {

        ContactResponse saved = contactService.submitMessage(request);
        return ResponseEntity.status(201)
                .body(ApiResponse.ok(saved, "Message sent successfully"));
    }

    // ── Profile ───────────────────────────────────────────────────────────────

    @Operation(summary = "Get profile information", security = {})
    @GetMapping("/api/profile")
    public ResponseEntity<ApiResponse<ProfileResponse>> getProfile() {
        ProfileResponse profile = ProfileResponse.builder()
                .name("Barash Sharma")
                .title("Full-Stack Developer")
                .bio("Passionate developer building scalable, production-ready applications.")
                .githubUrl("https://github.com/barash1311")
                .linkedinUrl("https://linkedin.com/in/barashsharma")
                .email("admin@spotlight.local")
                .build();

        return ResponseEntity.ok(ApiResponse.ok(profile));
    }
}


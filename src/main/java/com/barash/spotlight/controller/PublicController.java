package com.barash.spotlight.controller;

import com.barash.spotlight.dto.*;
import com.barash.spotlight.service.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@Tag(name = "Public", description = "Public endpoints — no authentication required")
public class PublicController {

    private final ProjectService projectService;
    private final SkillService   skillService;
    private final ContactService contactService;
    private final ProfileService profileService;
    private final ExperienceService experienceService;

    public PublicController(ProjectService projectService, SkillService skillService, 
                            ContactService contactService, ProfileService profileService,
                            ExperienceService experienceService) {
        this.projectService = projectService;
        this.skillService = skillService;
        this.contactService = contactService;
        this.profileService = profileService;
        this.experienceService = experienceService;
    }

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

    @Operation(summary = "List all skills", security = {})
    @GetMapping("/api/skills")
    public ResponseEntity<ApiResponse<List<SkillResponse>>> getSkills() {
        return ResponseEntity.ok(ApiResponse.ok(skillService.getAllSkills()));
    }

    @Operation(summary = "Submit a contact message", security = {})
    @PostMapping("/api/contact")
    public ResponseEntity<ApiResponse<ContactResponse>> submitContact(@Valid @RequestBody ContactRequest request) {
        ContactResponse saved = contactService.submitMessage(request);
        return ResponseEntity.status(201).body(ApiResponse.ok(saved, "Message sent successfully"));
    }

    @Operation(summary = "Get profile information", security = {})
    @GetMapping("/api/profile")
    public ResponseEntity<ApiResponse<ProfileResponse>> getProfile() {
        return ResponseEntity.ok(ApiResponse.ok(profileService.getProfile()));
    }

    @Operation(summary = "List all focal experiences", security = {})
    @GetMapping("/api/experiences")
    public ResponseEntity<ApiResponse<List<ExperienceResponse>>> getExperiences() {
        return ResponseEntity.ok(ApiResponse.ok(experienceService.getAllExperiences()));
    }
}

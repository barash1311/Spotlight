package com.barash.spotlight.controller;

import com.barash.spotlight.dto.ApiResponse;
import com.barash.spotlight.dto.ExperienceRequest;
import com.barash.spotlight.dto.ExperienceResponse;
import com.barash.spotlight.service.ExperienceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/admin/experiences")
@PreAuthorize("hasRole('ADMIN')")
@Tag(name = "Admin — Experience", description = "Career highlight management (ADMIN only)")
public class AdminExperienceController {

    private final ExperienceService experienceService;

    public AdminExperienceController(ExperienceService experienceService) {
        this.experienceService = experienceService;
    }

    @Operation(summary = "Get all experiences")
    @GetMapping
    public ResponseEntity<ApiResponse<List<ExperienceResponse>>> getAll() {
        return ResponseEntity.ok(ApiResponse.ok(experienceService.getAllExperiences()));
    }

    @Operation(summary = "Create a new experience")
    @PostMapping
    public ResponseEntity<ApiResponse<ExperienceResponse>> create(@Valid @RequestBody ExperienceRequest request) {
        return ResponseEntity.status(201).body(ApiResponse.ok(experienceService.createExperience(request)));
    }

    @Operation(summary = "Update an existing experience")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ExperienceResponse>> update(
            @PathVariable Long id,
            @Valid @RequestBody ExperienceRequest request) {
        return ResponseEntity.ok(ApiResponse.ok(experienceService.updateExperience(id, request)));
    }

    @Operation(summary = "Delete an experience")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        experienceService.deleteExperience(id);
        return ResponseEntity.ok(ApiResponse.<Void>builder().success(true).message("Experience deleted").build());
    }
}

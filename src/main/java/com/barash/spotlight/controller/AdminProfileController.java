package com.barash.spotlight.controller;

import com.barash.spotlight.dto.ApiResponse;
import com.barash.spotlight.dto.ProfileRequest;
import com.barash.spotlight.dto.ProfileResponse;
import com.barash.spotlight.service.ProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/profile")
@PreAuthorize("hasRole('ADMIN')")
@Tag(name = "Admin — Profile", description = "Profile management (ADMIN only)")
public class AdminProfileController {

    private final ProfileService profileService;

    public AdminProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @Operation(summary = "Get profile for editing")
    @GetMapping
    public ResponseEntity<ApiResponse<ProfileResponse>> getProfile() {
        return ResponseEntity.ok(ApiResponse.ok(profileService.getProfile()));
    }

    @Operation(summary = "Update profile information")
    @PutMapping
    public ResponseEntity<ApiResponse<ProfileResponse>> updateProfile(@Valid @RequestBody ProfileRequest request) {
        return ResponseEntity.ok(ApiResponse.ok(profileService.updateProfile(request)));
    }
}

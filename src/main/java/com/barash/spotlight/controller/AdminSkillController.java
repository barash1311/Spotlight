package com.barash.spotlight.controller;

import com.barash.spotlight.dto.ApiResponse;
import com.barash.spotlight.dto.SkillRequest;
import com.barash.spotlight.dto.SkillResponse;
import com.barash.spotlight.service.SkillService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/admin/skills")
@PreAuthorize("hasRole('ADMIN')")
@Tag(name = "Admin — Skills", description = "Skill management (ADMIN only)")
public class AdminSkillController {

    private final SkillService skillService;

    public AdminSkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    @Operation(summary = "Get all skills")
    @GetMapping
    public ResponseEntity<ApiResponse<List<SkillResponse>>> getAllSkills() {
        return ResponseEntity.ok(ApiResponse.ok(skillService.getAllSkills()));
    }

    @Operation(summary = "Create a skill")
    @PostMapping
    public ResponseEntity<ApiResponse<SkillResponse>> createSkill(@Valid @RequestBody SkillRequest request) {
        return ResponseEntity.status(201).body(ApiResponse.ok(skillService.createSkill(request)));
    }

    @Operation(summary = "Update a skill")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<SkillResponse>> updateSkill(@PathVariable Long id, @Valid @RequestBody SkillRequest request) {
        return ResponseEntity.ok(ApiResponse.ok(skillService.updateSkill(id, request)));
    }

    @Operation(summary = "Delete a skill")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteSkill(@PathVariable Long id) {
        skillService.deleteSkill(id);
        return ResponseEntity.ok(ApiResponse.<Void>builder().success(true).message("Skill deleted").build());
    }
}

package com.barash.spotlight.controller;

import com.barash.spotlight.dto.ApiResponse;
import com.barash.spotlight.dto.PageResponse;
import com.barash.spotlight.dto.ProjectRequest;
import com.barash.spotlight.dto.ProjectResponse;
import com.barash.spotlight.service.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/projects")
@PreAuthorize("hasRole('ADMIN')")
@Tag(name = "Admin — Projects", description = "Project management (ADMIN only)")
public class AdminProjectController {

    private final ProjectService projectService;

    public AdminProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @Operation(summary = "Get all projects (paginated)")
    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<ProjectResponse>>> getAllProjects(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(ApiResponse.ok(projectService.getProjects(page, size)));
    }

    @Operation(summary = "Create a new project")
    @PostMapping
    public ResponseEntity<ApiResponse<ProjectResponse>> createProject(@Valid @RequestBody ProjectRequest request) {
        return ResponseEntity.status(201).body(ApiResponse.ok(projectService.createProject(request)));
    }

    @Operation(summary = "Update an existing project")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProjectResponse>> updateProject(
            @PathVariable Long id,
            @Valid @RequestBody ProjectRequest request) {
        return ResponseEntity.ok(ApiResponse.ok(projectService.updateProject(id, request)));
    }

    @Operation(summary = "Delete a project")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
        return ResponseEntity.ok(ApiResponse.<Void>builder().success(true).message("Project deleted").build());
    }
}

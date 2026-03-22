package com.barash.spotlight.controller;

import com.barash.spotlight.dto.ApiResponse;
import com.barash.spotlight.dto.ResumeDTO;
import com.barash.spotlight.service.ResumeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@RestController
@RequestMapping("/api/admin/resumes")
@PreAuthorize("hasRole('ADMIN')")
@Tag(name = "Admin — Resumes", description = "Resume management (ADMIN only)")
public class AdminResumeController {

    private final ResumeService resumeService;

    public AdminResumeController(ResumeService resumeService) {
        this.resumeService = resumeService;
    }

    @Operation(summary = "Get all resume versions")
    @GetMapping
    public ResponseEntity<ApiResponse<List<ResumeDTO>>> getAll() {
        return ResponseEntity.ok(ApiResponse.ok(resumeService.getAllResumes()));
    }

    @Operation(summary = "Upload a new resume")
    @PostMapping("/upload")
    public ResponseEntity<ApiResponse<ResumeDTO>> upload(
            @RequestParam("file") MultipartFile file,
            @RequestParam("role") String role,
            @RequestParam(defaultValue = "false") boolean generic) {
        
        System.out.println(">>> UPLOAD REQUEST RECEIVED: role=" + role + ", generic=" + generic + ", file=" + file.getOriginalFilename());
        
        try {
            ResumeDTO uploaded = resumeService.uploadResume(file, role, generic);
            System.out.println("<<< UPLOAD SUCCESSFUL: " + uploaded.getFileName());
            return ResponseEntity.ok(ApiResponse.ok(uploaded));
        } catch (Exception e) {
            System.err.println("!!! UPLOAD FAILED: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    @Operation(summary = "Set a resume as the generic/active one")
    @PatchMapping("/{id}/generic")
    public ResponseEntity<ApiResponse<ResumeDTO>> setGeneric(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok(resumeService.setGeneric(id)));
    }

    @Operation(summary = "Delete a resume version")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        resumeService.deleteResume(id);
        return ResponseEntity.ok(ApiResponse.<Void>builder().success(true).message("Resume deleted").build());
    }
}

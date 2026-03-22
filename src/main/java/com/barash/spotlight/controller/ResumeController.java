package com.barash.spotlight.controller;

import com.barash.spotlight.dto.ApiResponse;
import com.barash.spotlight.dto.ResumeDTO;
import com.barash.spotlight.service.ResumeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/resumes")
@Tag(name = "Resumes — Public", description = "Public resume download/view")
public class ResumeController {

    private final ResumeService resumeService;

    public ResumeController(ResumeService resumeService) {
        this.resumeService = resumeService;
    }

    @Operation(summary = "Get generic resume info")
    @GetMapping("/generic")
    public ResponseEntity<ApiResponse<ResumeDTO>> getGeneric() {
        return ResponseEntity.ok(ApiResponse.ok(resumeService.getGenericResume()));
    }

    @Operation(summary = "View active resume directly")
    @GetMapping("/active")
    public ResponseEntity<Resource> viewActive(@RequestParam(defaultValue = "false") boolean download) {
        ResumeDTO generic = resumeService.getGenericResume();
        Resource file = resumeService.loadResumeAsResource(generic.getId());
        String disposition = download ? "attachment" : "inline";
        
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION, disposition + "; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }

    @Operation(summary = "Download a specific resume")
    @GetMapping("/download/{id}")
    public ResponseEntity<Resource> download(@PathVariable Long id) {
        Resource file = resumeService.loadResumeAsResource(id);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }

    @Operation(summary = "View a specific resume in browser")
    @GetMapping("/view/{id}")
    public ResponseEntity<Resource> view(@PathVariable Long id) {
        Resource file = resumeService.loadResumeAsResource(id);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }
}

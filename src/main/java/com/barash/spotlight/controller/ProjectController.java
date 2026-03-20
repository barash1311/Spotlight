package com.barash.spotlight.controller;


import com.barash.spotlight.dto.ProjectRequest;
import com.barash.spotlight.dto.ProjectResponse;
import com.barash.spotlight.service.ProjectService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@AllArgsConstructor
public class ProjectController {
    private final ProjectService projectService;

    //CREATE
    @PostMapping
    public ResponseEntity<ProjectResponse> createProject(@Valid @RequestBody ProjectRequest request){
        return ResponseEntity.ok(projectService.createProject(request));
    }
    @GetMapping
    public ResponseEntity<List<ProjectResponse>> getAllProjects(){
        return ResponseEntity.ok(projectService.getAllProjects());
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProjectResponse> getProjectById(@PathVariable Long id){
        return ResponseEntity.ok(projectService.getProjectById(id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id){
       projectService.deleteProject(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectResponse> updateProject(
            @PathVariable Long id,
            @Valid @RequestBody ProjectRequest request
    ) {
        return ResponseEntity.ok(projectService.updateProject(id, request));
    }



}

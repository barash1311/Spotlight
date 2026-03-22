package com.barash.spotlight.service;

import com.barash.spotlight.dto.ProjectRequest;
import com.barash.spotlight.dto.ProjectResponse;
import com.barash.spotlight.entity.Project;
import com.barash.spotlight.exception.ResourceNotFoundException;
import com.barash.spotlight.repository.ProjectRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProjectServiceImplementation implements ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectServiceImplementation(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public com.barash.spotlight.dto.PageResponse<ProjectResponse> getProjects(int page, int size) {
        Page<Project> result = projectRepository.findAllByOrderByCreatedAtDesc(PageRequest.of(page, size));
        
        return com.barash.spotlight.dto.PageResponse.<ProjectResponse>builder()
                .content(result.getContent().stream().map(this::mapToResponse).toList())
                .page(result.getNumber())
                .size(result.getSize())
                .totalElements(result.getTotalElements())
                .totalPages(result.getTotalPages())
                .last(result.isLast())
                .build();
    }

    @Override
    public ProjectResponse getProjectById(Long id) {
        return mapToResponse(projectRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Project not found")));
    }

    @Override
    public ProjectResponse createProject(ProjectRequest request) {
        Project p = new Project();
        updateEntity(p, request);
        return mapToResponse(projectRepository.save(p));
    }

    @Override
    public ProjectResponse updateProject(Long id, ProjectRequest request) {
        Project p = projectRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Project not found"));
        updateEntity(p, request);
        return mapToResponse(projectRepository.save(p));
    }

    @Override
    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }

    private ProjectResponse mapToResponse(Project p) {
        return ProjectResponse.builder()
                .id(p.getId())
                .title(p.getTitle())
                .description(p.getDescription())
                .shortDescription(p.getShortDescription())
                .technologies(p.getTechnologies())
                .githubUrl(p.getGithubUrl())
                .liveUrl(p.getLiveUrl())
                .imageUrl(p.getImageUrl())
                .featured(p.isFeatured())
                .createdAt(p.getCreatedAt())
                .build();
    }

    private void updateEntity(Project p, ProjectRequest r) {
        p.setTitle(r.getTitle());
        p.setDescription(r.getDescription());
        p.setShortDescription(r.getShortDescription());
        p.setTechnologies(r.getTechnologies());
        p.setGithubUrl(r.getGithubUrl());
        p.setLiveUrl(r.getLiveUrl());
        p.setImageUrl(r.getImageUrl());
        p.setFeatured(r.isFeatured());
    }
}

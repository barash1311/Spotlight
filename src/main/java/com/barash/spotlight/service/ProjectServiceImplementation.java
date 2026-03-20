package com.barash.spotlight.service;

import com.barash.spotlight.dto.ProjectRequest;
import com.barash.spotlight.dto.ProjectResponse;
import com.barash.spotlight.entity.Project;
import com.barash.spotlight.exception.ResourceNotFoundException;
import com.barash.spotlight.repository.ProjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class ProjectServiceImplementation implements ProjectService{
    private final ProjectRepository projectRepository;

    @Override
    public ProjectResponse createProject(ProjectRequest request) {
        Project project = Project.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .techStack(request.getTechStack())
                .githubUrl(request.getGithubUrl())
                .liveUrl(request.getLiveUrl())
                .imageUrl(request.getImageUrl())
                .createdAt(LocalDateTime.now())
                .build();

        Project saved = projectRepository.save(project);

        return mapToResponse(saved);
    }

    @Override
    public List<ProjectResponse> getAllProjects() {
        return projectRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public ProjectResponse getProjectById(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found"));
        return mapToResponse(project);
    }

    @Override
    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }

    @Override
    public ProjectResponse updateProject(Long id, ProjectRequest request) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found"));

        project.setTitle(request.getTitle());
        project.setDescription(request.getDescription());
        project.setTechStack(request.getTechStack());
        project.setGithubUrl(request.getGithubUrl());
        project.setLiveUrl(request.getLiveUrl());
        project.setImageUrl(request.getImageUrl());

        Project updated = projectRepository.save(project);

        return mapToResponse(updated);
    }

    private ProjectResponse mapToResponse(Project project) {
        return ProjectResponse.builder()
                .id(project.getId())
                .title(project.getTitle())
                .description(project.getDescription())
                .techStack(project.getTechStack())
                .githubUrl(project.getGithubUrl())
                .liveUrl(project.getLiveUrl())
                .imageUrl(project.getImageUrl())
                .createdAt(project.getCreatedAt())
                .build();
    }
}

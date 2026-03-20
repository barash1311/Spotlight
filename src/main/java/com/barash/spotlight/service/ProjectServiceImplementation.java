package com.barash.spotlight.service;

import com.barash.spotlight.dto.PageResponse;
import com.barash.spotlight.dto.ProjectRequest;
import com.barash.spotlight.dto.ProjectResponse;
import com.barash.spotlight.entity.Project;
import com.barash.spotlight.exception.ResourceNotFoundException;
import com.barash.spotlight.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectServiceImplementation implements ProjectService {

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
                .featured(request.isFeatured())
                .build();

        return mapToResponse(projectRepository.save(project));
    }

    @Override
    public PageResponse<ProjectResponse> getProjects(int page, int size) {
        PageRequest pageable = PageRequest.of(page, size,
                Sort.by(Sort.Direction.DESC, "featured")
                    .and(Sort.by(Sort.Direction.DESC, "createdAt")));

        Page<Project> result = projectRepository.findAll(pageable);

        return PageResponse.<ProjectResponse>builder()
                .content(result.getContent().stream().map(this::mapToResponse).toList())
                .page(result.getNumber())
                .size(result.getSize())
                .totalElements(result.getTotalElements())
                .totalPages(result.getTotalPages())
                .last(result.isLast())
                .build();
    }

    @Override
    public List<ProjectResponse> getAllProjects() {
        return projectRepository
                .findAll(Sort.by(Sort.Direction.DESC, "featured")
                             .and(Sort.by(Sort.Direction.DESC, "createdAt")))
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public ProjectResponse getProjectById(Long id) {
        return mapToResponse(findOrThrow(id));
    }

    @Override
    public ProjectResponse updateProject(Long id, ProjectRequest request) {
        Project project = findOrThrow(id);

        project.setTitle(request.getTitle());
        project.setDescription(request.getDescription());
        project.setTechStack(request.getTechStack());
        project.setGithubUrl(request.getGithubUrl());
        project.setLiveUrl(request.getLiveUrl());
        project.setImageUrl(request.getImageUrl());
        project.setFeatured(request.isFeatured());

        return mapToResponse(projectRepository.save(project));
    }

    @Override
    public void deleteProject(Long id) {
        if (!projectRepository.existsById(id)) {
            throw new ResourceNotFoundException("Project not found with id: " + id);
        }
        projectRepository.deleteById(id);
    }

    // ── Helpers ───────────────────────────────────────────────────────────────

    private Project findOrThrow(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + id));
    }

    private ProjectResponse mapToResponse(Project p) {
        return ProjectResponse.builder()
                .id(p.getId())
                .title(p.getTitle())
                .description(p.getDescription())
                .techStack(p.getTechStack())
                .githubUrl(p.getGithubUrl())
                .liveUrl(p.getLiveUrl())
                .imageUrl(p.getImageUrl())
                .featured(p.isFeatured())
                .createdAt(p.getCreatedAt())
                .updatedAt(p.getUpdatedAt())
                .build();
    }
}

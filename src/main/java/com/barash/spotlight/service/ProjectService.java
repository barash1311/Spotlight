package com.barash.spotlight.service;

import com.barash.spotlight.dto.PageResponse;
import com.barash.spotlight.dto.ProjectRequest;
import com.barash.spotlight.dto.ProjectResponse;

public interface ProjectService {
    ProjectResponse createProject(ProjectRequest request);
    PageResponse<ProjectResponse> getProjects(int page, int size);
    ProjectResponse getProjectById(Long id);
    ProjectResponse updateProject(Long id, ProjectRequest request);
    void deleteProject(Long id);
}

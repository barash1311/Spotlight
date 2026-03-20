package com.barash.spotlight.service;

import com.barash.spotlight.dto.ProjectRequest;
import com.barash.spotlight.dto.ProjectResponse;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ProjectService {
    ProjectResponse createProject(ProjectRequest request);
    List<ProjectResponse> getAllProjects();
    ProjectResponse getProjectById(Long id);
    void deleteProject(Long id);

    ProjectResponse updateProject(Long id, ProjectRequest request);
}

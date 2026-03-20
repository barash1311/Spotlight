package com.barash.spotlight.service;

import com.barash.spotlight.dto.PageResponse;
import com.barash.spotlight.dto.ProjectRequest;
import com.barash.spotlight.dto.ProjectResponse;

import java.util.List;

public interface ProjectService {
    ProjectResponse              createProject(ProjectRequest request);
    PageResponse<ProjectResponse> getProjects(int page, int size);
    List<ProjectResponse>        getAllProjects();
    ProjectResponse              getProjectById(Long id);
    ProjectResponse              updateProject(Long id, ProjectRequest request);
    void                         deleteProject(Long id);
}

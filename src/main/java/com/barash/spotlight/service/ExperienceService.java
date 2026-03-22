package com.barash.spotlight.service;

import com.barash.spotlight.dto.ExperienceRequest;
import com.barash.spotlight.dto.ExperienceResponse;
import java.util.List;

public interface ExperienceService {
    List<ExperienceResponse> getAllExperiences();
    ExperienceResponse createExperience(ExperienceRequest request);
    ExperienceResponse updateExperience(Long id, ExperienceRequest request);
    void deleteExperience(Long id);
}

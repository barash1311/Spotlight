package com.barash.spotlight.service;

import com.barash.spotlight.dto.ExperienceRequest;
import com.barash.spotlight.dto.ExperienceResponse;
import com.barash.spotlight.entity.Experience;
import com.barash.spotlight.exception.ResourceNotFoundException;
import com.barash.spotlight.repository.ExperienceRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ExperienceServiceImplementation implements ExperienceService {

    private final ExperienceRepository experienceRepository;

    public ExperienceServiceImplementation(ExperienceRepository experienceRepository) {
        this.experienceRepository = experienceRepository;
    }

    @Override
    public List<ExperienceResponse> getAllExperiences() {
        return experienceRepository.findAll().stream().map(this::mapToResponse).toList();
    }

    @Override
    public ExperienceResponse createExperience(ExperienceRequest request) {
        Experience e = new Experience();
        updateEntity(e, request);
        return mapToResponse(experienceRepository.save(e));
    }

    @Override
    public ExperienceResponse updateExperience(Long id, ExperienceRequest request) {
        Experience e = experienceRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Experience not found"));
        updateEntity(e, request);
        return mapToResponse(experienceRepository.save(e));
    }

    @Override
    public void deleteExperience(Long id) {
        experienceRepository.deleteById(id);
    }

    private ExperienceResponse mapToResponse(Experience e) {
        return ExperienceResponse.builder()
                .id(e.getId())
                .companyName(e.getCompanyName())
                .role(e.getRole())
                .description(e.getDescription())
                .focus(e.getFocus())
                .discipline(e.getDiscipline())
                .createdAt(e.getCreatedAt())
                .updatedAt(e.getUpdatedAt())
                .build();
    }

    private void updateEntity(Experience e, ExperienceRequest r) {
        e.setCompanyName(r.getCompanyName());
        e.setRole(r.getRole());
        e.setDescription(r.getDescription());
        e.setFocus(r.getFocus());
        e.setDiscipline(r.getDiscipline());
    }
}

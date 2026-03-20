package com.barash.spotlight.service;

import com.barash.spotlight.dto.SkillRequest;
import com.barash.spotlight.dto.SkillResponse;
import com.barash.spotlight.entity.Skill;
import com.barash.spotlight.exception.DuplicateResourceException;
import com.barash.spotlight.exception.ResourceNotFoundException;
import com.barash.spotlight.repository.SkillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SkillServiceImplementation implements SkillService {

    private final SkillRepository skillRepository;

    @Override
    public SkillResponse createSkill(SkillRequest request) {
        if (skillRepository.existsByNameIgnoreCase(request.getName())) {
            throw new DuplicateResourceException("Skill '" + request.getName() + "' already exists");
        }
        Skill skill = Skill.builder()
                .name(request.getName())
                .category(request.getCategory())
                .proficiency(request.getProficiency())
                .iconUrl(request.getIconUrl())
                .displayOrder(request.getDisplayOrder())
                .build();

        return mapToResponse(skillRepository.save(skill));
    }

    @Override
    public List<SkillResponse> getAllSkills() {
        return skillRepository.findAllByOrderByDisplayOrderAscNameAsc()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public SkillResponse getSkillById(Long id) {
        return mapToResponse(findOrThrow(id));
    }

    @Override
    public SkillResponse updateSkill(Long id, SkillRequest request) {
        Skill skill = findOrThrow(id);

        // Allow rename only if the new name isn't already taken by a different skill
        if (!skill.getName().equalsIgnoreCase(request.getName())
                && skillRepository.existsByNameIgnoreCase(request.getName())) {
            throw new DuplicateResourceException("Skill '" + request.getName() + "' already exists");
        }

        skill.setName(request.getName());
        skill.setCategory(request.getCategory());
        skill.setProficiency(request.getProficiency());
        skill.setIconUrl(request.getIconUrl());
        skill.setDisplayOrder(request.getDisplayOrder());

        return mapToResponse(skillRepository.save(skill));
    }

    @Override
    public void deleteSkill(Long id) {
        if (!skillRepository.existsById(id)) {
            throw new ResourceNotFoundException("Skill not found with id: " + id);
        }
        skillRepository.deleteById(id);
    }

    // ── Helpers ───────────────────────────────────────────────────────────────

    private Skill findOrThrow(Long id) {
        return skillRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Skill not found with id: " + id));
    }

    private SkillResponse mapToResponse(Skill skill) {
        return SkillResponse.builder()
                .id(skill.getId())
                .name(skill.getName())
                .category(skill.getCategory())
                .proficiency(skill.getProficiency())
                .iconUrl(skill.getIconUrl())
                .displayOrder(skill.getDisplayOrder())
                .createdAt(skill.getCreatedAt())
                .updatedAt(skill.getUpdatedAt())
                .build();
    }
}

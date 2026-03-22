package com.barash.spotlight.service;

import com.barash.spotlight.dto.SkillRequest;
import com.barash.spotlight.dto.SkillResponse;
import com.barash.spotlight.entity.Skill;
import com.barash.spotlight.exception.ResourceNotFoundException;
import com.barash.spotlight.repository.SkillRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SkillServiceImplementation implements SkillService {

    private final SkillRepository skillRepository;

    public SkillServiceImplementation(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    @Override
    public List<SkillResponse> getAllSkills() {
        return skillRepository.findAll().stream().map(this::mapToResponse).toList();
    }

    @Override
    public SkillResponse getSkillById(Long id) {
        return mapToResponse(skillRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Skill not found")));
    }

    @Override
    public SkillResponse createSkill(SkillRequest request) {
        Skill skill = new Skill();
        skill.setName(request.getName());
        skill.setCategory(request.getCategory());
        skill.setProficiency(request.getProficiency());
        skill.setIconUrl(request.getIconUrl());
        skill.setDisplayOrder(request.getDisplayOrder());
        return mapToResponse(skillRepository.save(skill));
    }

    @Override
    public SkillResponse updateSkill(Long id, SkillRequest request) {
        Skill skill = skillRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Skill not found"));
        skill.setName(request.getName());
        skill.setCategory(request.getCategory());
        skill.setProficiency(request.getProficiency());
        skill.setIconUrl(request.getIconUrl());
        skill.setDisplayOrder(request.getDisplayOrder());
        return mapToResponse(skillRepository.save(skill));
    }

    @Override
    public void deleteSkill(Long id) {
        skillRepository.deleteById(id);
    }

    private SkillResponse mapToResponse(Skill s) {
        return SkillResponse.builder()
                .id(s.getId())
                .name(s.getName())
                .category(s.getCategory())
                .proficiency(s.getProficiency())
                .iconUrl(s.getIconUrl())
                .displayOrder(s.getDisplayOrder())
                .createdAt(s.getCreatedAt())
                .updatedAt(s.getUpdatedAt())
                .build();
    }
}

package com.barash.spotlight.service;

import com.barash.spotlight.dto.SkillRequest;
import com.barash.spotlight.dto.SkillResponse;

import java.util.List;

public interface SkillService {
    SkillResponse        createSkill(SkillRequest request);
    List<SkillResponse>  getAllSkills();
    SkillResponse        getSkillById(Long id);
    SkillResponse        updateSkill(Long id, SkillRequest request);
    void                 deleteSkill(Long id);
}

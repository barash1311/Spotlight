package com.barash.spotlight.repository;

import com.barash.spotlight.entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {

    /** Public listing ordered by displayOrder then name for consistent UI rendering */
    List<Skill> findAllByOrderByDisplayOrderAscNameAsc();

    boolean existsByNameIgnoreCase(String name);
}

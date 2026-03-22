package com.barash.spotlight.repository;

import com.barash.spotlight.entity.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;
import java.util.List;

public interface ResumeRepository extends JpaRepository<Resume, Long> {
    Optional<Resume> findByGenericTrue();
    List<Resume> findAllByOrderByCreatedAtDesc();

    @Modifying
    @Transactional
    @Query("UPDATE Resume r SET r.generic = false")
    void resetAllGenericFlags();

    @Modifying
    @Transactional
    @Query("UPDATE Resume r SET r.generic = true WHERE r.id = :id")
    void setGenericById(Long id);
}

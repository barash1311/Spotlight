package com.barash.spotlight.service;

import com.barash.spotlight.dto.ResumeDTO;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

public interface ResumeService {
    List<ResumeDTO> getAllResumes();
    ResumeDTO getGenericResume();
    ResumeDTO uploadResume(MultipartFile file, String role, boolean generic);
    void deleteResume(Long id);
    Resource loadResumeAsResource(Long id);
    ResumeDTO setGeneric(Long id);
}

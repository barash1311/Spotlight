package com.barash.spotlight.service;

import com.barash.spotlight.dto.ResumeDTO;
import com.barash.spotlight.entity.Resume;
import com.barash.spotlight.exception.ResourceNotFoundException;
import com.barash.spotlight.repository.ResumeRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Service
public class ResumeServiceImplementation implements ResumeService {

    private final ResumeRepository resumeRepository;
    private final Path fileStorageLocation;

    public ResumeServiceImplementation(ResumeRepository resumeRepository, @Value("${app.upload.dir:uploads/resumes}") String uploadDir) {
        this.resumeRepository = resumeRepository;
        this.fileStorageLocation = Paths.get(uploadDir).toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new RuntimeException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    @Override
    public List<ResumeDTO> getAllResumes() {
        return resumeRepository.findAll().stream().map(this::mapToDTO).toList();
    }

    @Override
    public ResumeDTO getGenericResume() {
        return resumeRepository.findByGenericTrue().map(this::mapToDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Generic resume not found"));
    }

    @Override
    public ResumeDTO uploadResume(MultipartFile file, String role, boolean generic) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            if (generic) {
                resumeRepository.findByGenericTrue().ifPresent(r -> {
                    r.setGeneric(false);
                    resumeRepository.save(r);
                });
            }

            Resume resume = new Resume();
            resume.setRole(role);
            resume.setFileName(fileName);
            resume.setContentType(file.getContentType());
            resume.setData(file.getBytes());
            resume.setGeneric(generic);
            
            return mapToDTO(resumeRepository.save(resume));
        } catch (IOException ex) {
            throw new RuntimeException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    @Override
    public void deleteResume(Long id) {
        resumeRepository.deleteById(id);
    }

    @Override
    public Resource loadResumeAsResource(Long id) {
        Resume resume = resumeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resume not found"));
        try {
            Path filePath = this.fileStorageLocation.resolve(resume.getFileName()).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new ResourceNotFoundException("File not found " + resume.getFileName());
            }
        } catch (Exception ex) {
            throw new ResourceNotFoundException("File not found " + resume.getFileName(), ex);
        }
    }

    @Override
    public ResumeDTO setGeneric(Long id) {
        // Unset any current generic resume
        resumeRepository.findByGenericTrue().ifPresent(r -> {
            r.setGeneric(false);
            resumeRepository.save(r);
        });

        // Set the new one
        Resume resume = resumeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resume not found"));
        resume.setGeneric(true);
        return mapToDTO(resumeRepository.save(resume));
    }

    private ResumeDTO mapToDTO(Resume r) {
        return ResumeDTO.builder()
                .id(r.getId())
                .role(r.getRole())
                .fileName(r.getFileName())
                .generic(r.isGeneric())
                .createdAt(r.getCreatedAt())
                .updatedAt(r.getUpdatedAt())
                .build();
    }
}

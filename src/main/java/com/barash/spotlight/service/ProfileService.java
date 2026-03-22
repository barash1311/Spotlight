package com.barash.spotlight.service;

import com.barash.spotlight.dto.ProfileRequest;
import com.barash.spotlight.dto.ProfileResponse;
import com.barash.spotlight.entity.Profile;
import com.barash.spotlight.repository.ProfileRepository;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.barash.spotlight.dto.ExperienceDto;
import com.barash.spotlight.dto.EducationDto;
import java.util.List;

@Service
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final ObjectMapper objectMapper;

    public ProfileService(ProfileRepository profileRepository, ObjectMapper objectMapper) {
        this.profileRepository = profileRepository;
        this.objectMapper = objectMapper;
    }

    public ProfileResponse getProfile() {
        Profile p = profileRepository.findById(1L).orElseGet(() -> {
            Profile defaultProfile = Profile.builder()
                .id(1L)
                .name("Barash Sharma")
                .title("Computer Science Student")
                .bio("...")
                .githubUrl("https://github.com/barash1311")
                .linkedinUrl("https://www.linkedin.com/in/barash-sharma/")
                .email("barash1311@gmail.com")
                .experienceJson("[]")
                .educationJson("[]")
                .build();
            return profileRepository.save(defaultProfile);
        });
        
        return ProfileResponse.builder()
            .name(p.getName())
            .title(p.getTitle())
            .bio(p.getBio())
            .githubUrl(p.getGithubUrl())
            .linkedinUrl(p.getLinkedinUrl())
            .email(p.getEmail())
            .experience(parseExperience(p.getExperienceJson()))
            .education(parseEducation(p.getEducationJson()))
            .location(p.getLocation())
            .role(p.getRole())
            .educationHighlight(p.getEducationHighlight())
            .cgpa(p.getCgpa())
            .competencies(parseCompetencies(p.getCompetenciesJson()))
            .build();
    }

    public ProfileResponse updateProfile(ProfileRequest request) {
        Profile p = profileRepository.findById(1L).orElse(new Profile());
        p.setId(1L);
        p.setName(request.getName());
        p.setTitle(request.getTitle());
        p.setBio(request.getBio());
        p.setGithubUrl(request.getGithubUrl());
        p.setLinkedinUrl(request.getLinkedinUrl());
        p.setEmail(request.getEmail());
        try {
            p.setExperienceJson(objectMapper.writeValueAsString(request.getExperience() == null ? List.of() : request.getExperience()));
            p.setEducationJson(objectMapper.writeValueAsString(request.getEducation() == null ? List.of() : request.getEducation()));
            p.setLocation(request.getLocation());
            p.setRole(request.getRole());
            p.setEducationHighlight(request.getEducationHighlight());
            p.setCgpa(request.getCgpa());
            p.setCompetenciesJson(objectMapper.writeValueAsString(request.getCompetencies() == null ? List.of() : request.getCompetencies()));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        Profile saved = profileRepository.save(p);
        
        return ProfileResponse.builder()
            .name(saved.getName())
            .title(saved.getTitle())
            .bio(saved.getBio())
            .githubUrl(saved.getGithubUrl())
            .linkedinUrl(saved.getLinkedinUrl())
            .email(saved.getEmail())
            .experience(parseExperience(saved.getExperienceJson()))
            .education(parseEducation(saved.getEducationJson()))
            .location(saved.getLocation())
            .role(saved.getRole())
            .educationHighlight(saved.getEducationHighlight())
            .cgpa(saved.getCgpa())
            .competencies(parseCompetencies(saved.getCompetenciesJson()))
            .build();
    }

    private List<ExperienceDto> parseExperience(String json) {
        if (json == null || json.isEmpty()) return List.of();
        try {
            return objectMapper.readValue(json, objectMapper.getTypeFactory().constructCollectionType(List.class, ExperienceDto.class));
        } catch (JsonProcessingException e) {
            return List.of();
        }
    }

    private List<EducationDto> parseEducation(String json) {
        if (json == null || json.isEmpty()) return List.of();
        try {
            return objectMapper.readValue(json, objectMapper.getTypeFactory().constructCollectionType(List.class, EducationDto.class));
        } catch (JsonProcessingException e) {
            return List.of();
        }
    }

    private List<String> parseCompetencies(String json) {
        if (json == null || json.isEmpty()) return List.of();
        try {
            return objectMapper.readValue(json, objectMapper.getTypeFactory().constructCollectionType(List.class, String.class));
        } catch (JsonProcessingException e) {
            return List.of();
        }
    }
}

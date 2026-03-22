package com.barash.spotlight.dto;

import java.util.List;

public class ProfileRequest {
    private String name;
    private String title;
    private String bio;
    private String githubUrl;
    private String linkedinUrl;
    private String email;
    private List<ExperienceDto> experience;
    private List<EducationDto> education;
    private String location;
    private String role;
    private String educationHighlight;
    private String cgpa;
    private List<String> competencies;

    public ProfileRequest() {}

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }
    public String getGithubUrl() { return githubUrl; }
    public void setGithubUrl(String githubUrl) { this.githubUrl = githubUrl; }
    public String getLinkedinUrl() { return linkedinUrl; }
    public void setLinkedinUrl(String linkedinUrl) { this.linkedinUrl = linkedinUrl; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public List<ExperienceDto> getExperience() { return experience; }
    public void setExperience(List<ExperienceDto> ex) { this.experience = ex; }
    public List<EducationDto> getEducation() { return education; }
    public void setEducation(List<EducationDto> ed) { this.education = ed; }
    public String getLocation() { return location; }
    public void setLocation(String loc) { this.location = loc; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public String getEducationHighlight() { return educationHighlight; }
    public void setEducationHighlight(String eh) { this.educationHighlight = eh; }
    public String getCgpa() { return cgpa; }
    public void setCgpa(String cgpa) { this.cgpa = cgpa; }
    public List<String> getCompetencies() { return competencies; }
    public void setCompetencies(List<String> comp) { this.competencies = comp; }
}

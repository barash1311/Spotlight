package com.barash.spotlight.dto;

import java.util.List;

public class ProfileResponse {

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

    public ProfileResponse() {}

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
    public void setExperience(List<ExperienceDto> experience) { this.experience = experience; }
    public List<EducationDto> getEducation() { return education; }
    public void setEducation(List<EducationDto> education) { this.education = education; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public String getEducationHighlight() { return educationHighlight; }
    public void setEducationHighlight(String educationHighlight) { this.educationHighlight = educationHighlight; }
    public String getCgpa() { return cgpa; }
    public void setCgpa(String cgpa) { this.cgpa = cgpa; }
    public List<String> getCompetencies() { return competencies; }
    public void setCompetencies(List<String> competencies) { this.competencies = competencies; }

    public static ProfileResponseBuilder builder() {
        return new ProfileResponseBuilder();
    }

    public static class ProfileResponseBuilder {
        private ProfileResponse resp = new ProfileResponse();
        public ProfileResponseBuilder name(String name) { resp.setName(name); return this; }
        public ProfileResponseBuilder title(String title) { resp.setTitle(title); return this; }
        public ProfileResponseBuilder bio(String bio) { resp.setBio(bio); return this; }
        public ProfileResponseBuilder githubUrl(String githubUrl) { resp.setGithubUrl(githubUrl); return this; }
        public ProfileResponseBuilder linkedinUrl(String linkedinUrl) { resp.setLinkedinUrl(linkedinUrl); return this; }
        public ProfileResponseBuilder email(String email) { resp.setEmail(email); return this; }
        public ProfileResponseBuilder experience(List<ExperienceDto> ex) { resp.setExperience(ex); return this; }
        public ProfileResponseBuilder education(List<EducationDto> ed) { resp.setEducation(ed); return this; }
        public ProfileResponseBuilder location(String loc) { resp.setLocation(loc); return this; }
        public ProfileResponseBuilder role(String role) { resp.setRole(role); return this; }
        public ProfileResponseBuilder educationHighlight(String eh) { resp.setEducationHighlight(eh); return this; }
        public ProfileResponseBuilder cgpa(String cgpa) { resp.setCgpa(cgpa); return this; }
        public ProfileResponseBuilder competencies(List<String> comp) { resp.setCompetencies(comp); return this; }
        public ProfileResponse build() { return resp; }
    }
}

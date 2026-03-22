package com.barash.spotlight.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "profile")
public class Profile {

    @Id
    private Long id; 

    private String name;
    private String title;

    @Column(length = 2000)
    private String bio;

    private String githubUrl;
    private String linkedinUrl;
    private String email;

    @Column(columnDefinition = "TEXT")
    private String experienceJson;

    @Column(columnDefinition = "TEXT")
    private String educationJson;

    private String location;
    private String role;
    private String educationHighlight;
    private String cgpa;

    @Column(columnDefinition = "TEXT")
    private String competenciesJson;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Profile() {}

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
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
    public String getExperienceJson() { return experienceJson; }
    public void setExperienceJson(String experienceJson) { this.experienceJson = experienceJson; }
    public String getEducationJson() { return educationJson; }
    public void setEducationJson(String educationJson) { this.educationJson = educationJson; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public String getEducationHighlight() { return educationHighlight; }
    public void setEducationHighlight(String eh) { this.educationHighlight = eh; }
    public String getCgpa() { return cgpa; }
    public void setCgpa(String cgpa) { this.cgpa = cgpa; }
    public String getCompetenciesJson() { return competenciesJson; }
    public void setCompetenciesJson(String competenciesJson) { this.competenciesJson = competenciesJson; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    public static ProfileBuilder builder() {
        return new ProfileBuilder();
    }

    public static class ProfileBuilder {
        private Profile profile = new Profile();
        public ProfileBuilder id(Long id) { profile.setId(id); return this; }
        public ProfileBuilder name(String name) { profile.setName(name); return this; }
        public ProfileBuilder title(String title) { profile.setTitle(title); return this; }
        public ProfileBuilder bio(String bio) { profile.setBio(bio); return this; }
        public ProfileBuilder githubUrl(String githubUrl) { profile.setGithubUrl(githubUrl); return this; }
        public ProfileBuilder linkedinUrl(String linkedinUrl) { profile.setLinkedinUrl(linkedinUrl); return this; }
        public ProfileBuilder email(String email) { profile.setEmail(email); return this; }
        public ProfileBuilder experienceJson(String ex) { profile.setExperienceJson(ex); return this; }
        public ProfileBuilder educationJson(String ed) { profile.setEducationJson(ed); return this; }
        public Profile build() { return profile; }
    }
}

package com.barash.spotlight.dto;

import java.time.LocalDateTime;

public class ProjectResponse {

    private Long          id;
    private String        title;
    private String        description;
    private String        shortDescription;
    private String        technologies; // Updated from techStack
    private String        githubUrl;
    private String        liveUrl;
    private String        imageUrl;
    private boolean       featured;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ProjectResponse() {}

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getShortDescription() { return shortDescription; }
    public void setShortDescription(String shortDescription) { this.shortDescription = shortDescription; }
    public String getTechnologies() { return technologies; }
    public void setTechnologies(String tech) { this.technologies = tech; }
    public String getGithubUrl() { return githubUrl; }
    public void setGithubUrl(String url) { this.githubUrl = url; }
    public String getLiveUrl() { return liveUrl; }
    public void setLiveUrl(String url) { this.liveUrl = url; }
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String url) { this.imageUrl = url; }
    public boolean isFeatured() { return featured; }
    public void setFeatured(boolean featured) { this.featured = featured; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    public static ProjectResponseBuilder builder() {
        return new ProjectResponseBuilder();
    }

    public static class ProjectResponseBuilder {
        private ProjectResponse resp = new ProjectResponse();
        public ProjectResponseBuilder id(Long id) { resp.setId(id); return this; }
        public ProjectResponseBuilder title(String title) { resp.setTitle(title); return this; }
        public ProjectResponseBuilder description(String description) { resp.setDescription(description); return this; }
        public ProjectResponseBuilder shortDescription(String sd) { resp.setShortDescription(sd); return this; }
        public ProjectResponseBuilder technologies(String tech) { resp.setTechnologies(tech); return this; }
        public ProjectResponseBuilder githubUrl(String url) { resp.setGithubUrl(url); return this; }
        public ProjectResponseBuilder liveUrl(String url) { resp.setLiveUrl(url); return this; }
        public ProjectResponseBuilder imageUrl(String url) { resp.setImageUrl(url); return this; }
        public ProjectResponseBuilder featured(boolean f) { resp.setFeatured(f); return this; }
        public ProjectResponseBuilder createdAt(LocalDateTime ca) { resp.setCreatedAt(ca); return this; }
        public ProjectResponseBuilder updatedAt(LocalDateTime ua) { resp.setUpdatedAt(ua); return this; }
        public ProjectResponse build() { return resp; }
    }
}
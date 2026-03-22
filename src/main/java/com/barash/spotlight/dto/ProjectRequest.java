package com.barash.spotlight.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ProjectRequest {

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Description is required")
    @Size(min = 10, message = "Description must be at least 10 characters")
    private String description;

    private String shortDescription;

    @NotBlank(message = "Technologies are required")
    private String technologies; // Updated from techStack

    @NotBlank(message = "GitHub URL is required")
    private String githubUrl;

    private String liveUrl;
    private String imageUrl;

    private boolean featured;

    public ProjectRequest() {}

    // Getters and Setters
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
}
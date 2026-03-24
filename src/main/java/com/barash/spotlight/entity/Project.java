package com.barash.spotlight.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(length = 2000, nullable = false)
    private String description;

    @Column(length = 500)
    private String shortDescription;

    @Column(name = "tech_stack", length = 1000, nullable = false)
    private String technologies; // Renamed from techStack but mapped back to old name

    @Column(length = 1000, nullable = false)
    private String githubUrl;

    @Column(length = 1000)
    private String liveUrl;
    
    @Column(length = 1000)
    private String imageUrl;

    private boolean featured = false; // Changed from Boolean to boolean for isFeatured

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Project() {}

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
    public void setTechnologies(String technologies) { this.technologies = technologies; }
    public String getGithubUrl() { return githubUrl; }
    public void setGithubUrl(String githubUrl) { this.githubUrl = githubUrl; }
    public String getLiveUrl() { return liveUrl; }
    public void setLiveUrl(String liveUrl) { this.liveUrl = liveUrl; }
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    public boolean isFeatured() { return featured; }
    public void setFeatured(boolean featured) { this.featured = featured; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }

    public static ProjectBuilder builder() {
        return new ProjectBuilder();
    }

    public static class ProjectBuilder {
        private Project project = new Project();
        public ProjectBuilder id(Long id) { project.setId(id); return this; }
        public ProjectBuilder title(String title) { project.setTitle(title); return this; }
        public ProjectBuilder description(String description) { project.setDescription(description); return this; }
        public ProjectBuilder shortDescription(String sd) { project.setShortDescription(sd); return this; }
        public ProjectBuilder technologies(String tech) { project.setTechnologies(tech); return this; }
        public ProjectBuilder githubUrl(String githubUrl) { project.setGithubUrl(githubUrl); return this; }
        public ProjectBuilder liveUrl(String liveUrl) { project.setLiveUrl(liveUrl); return this; }
        public ProjectBuilder imageUrl(String imageUrl) { project.setImageUrl(imageUrl); return this; }
        public ProjectBuilder featured(boolean featured) { project.setFeatured(featured); return this; }
        public Project build() { return project; }
    }
}

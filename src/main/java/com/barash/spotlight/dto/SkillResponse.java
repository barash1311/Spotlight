package com.barash.spotlight.dto;

import java.time.LocalDateTime;

public class SkillResponse {

    private Long          id;
    private String        name;
    private String        category;
    private Integer       proficiency;
    private String        iconUrl;
    private int           displayOrder;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public SkillResponse() {}

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public Integer getProficiency() { return proficiency; }
    public void setProficiency(Integer proficiency) { this.proficiency = proficiency; }
    public String getIconUrl() { return iconUrl; }
    public void setIconUrl(String iconUrl) { this.iconUrl = iconUrl; }
    public int getDisplayOrder() { return displayOrder; }
    public void setDisplayOrder(int displayOrder) { this.displayOrder = displayOrder; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    public static SkillResponseBuilder builder() {
        return new SkillResponseBuilder();
    }

    public static class SkillResponseBuilder {
        private SkillResponse resp = new SkillResponse();
        public SkillResponseBuilder id(Long id) { resp.setId(id); return this; }
        public SkillResponseBuilder name(String name) { resp.setName(name); return this; }
        public SkillResponseBuilder category(String category) { resp.setCategory(category); return this; }
        public SkillResponseBuilder proficiency(Integer proficiency) { resp.setProficiency(proficiency); return this; }
        public SkillResponseBuilder iconUrl(String iconUrl) { resp.setIconUrl(iconUrl); return this; }
        public SkillResponseBuilder displayOrder(int displayOrder) { resp.setDisplayOrder(displayOrder); return this; }
        public SkillResponseBuilder createdAt(LocalDateTime ca) { resp.setCreatedAt(ca); return this; }
        public SkillResponseBuilder updatedAt(LocalDateTime ua) { resp.setUpdatedAt(ua); return this; }
        public SkillResponse build() { return resp; }
    }
}

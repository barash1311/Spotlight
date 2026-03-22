package com.barash.spotlight.dto;

import jakarta.validation.constraints.*;

public class SkillRequest {

    @NotBlank(message = "Skill name is required")
    private String name;

    @NotBlank(message = "Category is required")
    private String category;

    @Min(value = 0, message = "Proficiency must be between 0 and 100")
    @Max(value = 100, message = "Proficiency must be between 0 and 100")
    private Integer proficiency;

    private String iconUrl;
    private int displayOrder;

    public SkillRequest() {}

    // Getters and Setters
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
}

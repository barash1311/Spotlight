package com.barash.spotlight.dto;

public class ExperienceRequest {
    private String companyName;
    private String role;
    private String description;
    private String focus;
    private String discipline;

    public ExperienceRequest() {}

    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getFocus() { return focus; }
    public void setFocus(String focus) { this.focus = focus; }
    public String getDiscipline() { return discipline; }
    public void setDiscipline(String discipline) { this.discipline = discipline; }
}

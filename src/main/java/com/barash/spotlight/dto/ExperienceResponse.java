package com.barash.spotlight.dto;

import java.time.LocalDateTime;

public class ExperienceResponse {
    private Long id;
    private String companyName;
    private String role;
    private String description;
    private String focus;
    private String discipline;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ExperienceResponse() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
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
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    public static ExperienceResponseBuilder builder() { return new ExperienceResponseBuilder(); }

    public static class ExperienceResponseBuilder {
        private ExperienceResponse r = new ExperienceResponse();
        public ExperienceResponseBuilder id(Long id) { r.setId(id); return this; }
        public ExperienceResponseBuilder companyName(String cn) { r.setCompanyName(cn); return this; }
        public ExperienceResponseBuilder role(String role) { r.setRole(role); return this; }
        public ExperienceResponseBuilder description(String desc) { r.setDescription(desc); return this; }
        public ExperienceResponseBuilder focus(String focus) { r.setFocus(focus); return this; }
        public ExperienceResponseBuilder discipline(String disc) { r.setDiscipline(disc); return this; }
        public ExperienceResponseBuilder createdAt(LocalDateTime ca) { r.setCreatedAt(ca); return this; }
        public ExperienceResponseBuilder updatedAt(LocalDateTime ua) { r.setUpdatedAt(ua); return this; }
        public ExperienceResponse build() { return r; }
    }
}

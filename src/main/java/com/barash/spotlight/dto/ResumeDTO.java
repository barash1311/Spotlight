package com.barash.spotlight.dto;

import java.time.LocalDateTime;

public class ResumeDTO {
    private Long id;
    private String role;
    private String fileName;
    private boolean generic;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ResumeDTO() {}

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public String getFileName() { return fileName; }
    public void setFileName(String fileName) { this.fileName = fileName; }
    public boolean isGeneric() { return generic; }
    public void setGeneric(boolean generic) { this.generic = generic; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    public static ResumeDTOBuilder builder() {
        return new ResumeDTOBuilder();
    }

    public static class ResumeDTOBuilder {
        private ResumeDTO dto = new ResumeDTO();
        public ResumeDTOBuilder id(Long id) { dto.setId(id); return this; }
        public ResumeDTOBuilder role(String role) { dto.setRole(role); return this; }
        public ResumeDTOBuilder fileName(String fn) { dto.setFileName(fn); return this; }
        public ResumeDTOBuilder generic(boolean g) { dto.setGeneric(g); return this; }
        public ResumeDTOBuilder createdAt(LocalDateTime ca) { dto.setCreatedAt(ca); return this; }
        public ResumeDTOBuilder updatedAt(LocalDateTime ua) { dto.setUpdatedAt(ua); return this; }
        public ResumeDTO build() { return dto; }
    }
}

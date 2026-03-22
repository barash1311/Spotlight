package com.barash.spotlight.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "resumes")
public class Resume {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String role; 

    @Column(nullable = false)
    private String fileName;

    @Column(nullable = false)
    private String contentType;

    @Column(name = "data", columnDefinition = "bytea")
    private byte[] data;

    @Column(nullable = false)
    private boolean generic = false; 

    @org.hibernate.annotations.CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @org.hibernate.annotations.UpdateTimestamp
    private LocalDateTime updatedAt;

    public Resume() {}

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public String getFileName() { return fileName; }
    public void setFileName(String fileName) { this.fileName = fileName; }
    public String getContentType() { return contentType; }
    public void setContentType(String contentType) { this.contentType = contentType; }
    public byte[] getData() { return data; }
    public void setData(byte[] data) { this.data = data; }
    public boolean isGeneric() { return generic; }
    public void setGeneric(boolean generic) { this.generic = generic; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }



    public static ResumeBuilder builder() {
        return new ResumeBuilder();
    }

    public static class ResumeBuilder {
        private Resume resume = new Resume();
        public ResumeBuilder role(String role) { resume.setRole(role); return this; }
        public ResumeBuilder fileName(String fn) { resume.setFileName(fn); return this; }
        public ResumeBuilder contentType(String ct) { resume.setContentType(ct); return this; }
        public ResumeBuilder data(byte[] d) { resume.setData(d); return this; }
        public ResumeBuilder generic(boolean g) { resume.setGeneric(g); return this; }
        public Resume build() { return resume; }
    }
}

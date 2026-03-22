package com.barash.spotlight.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "skills")
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String category;

    private Integer proficiency;
    private String iconUrl;

    @Column(nullable = false)
    private int displayOrder = 0;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Skill() {}

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
    public LocalDateTime getUpdatedAt() { return updatedAt; }

    public static SkillBuilder builder() {
        return new SkillBuilder();
    }

    public static class SkillBuilder {
        private Skill skill = new Skill();
        public SkillBuilder id(Long id) { skill.setId(id); return this; }
        public SkillBuilder name(String name) { skill.setName(name); return this; }
        public SkillBuilder category(String category) { skill.setCategory(category); return this; }
        public SkillBuilder proficiency(Integer proficiency) { skill.setProficiency(proficiency); return this; }
        public SkillBuilder iconUrl(String iconUrl) { skill.setIconUrl(iconUrl); return this; }
        public SkillBuilder displayOrder(int displayOrder) { skill.setDisplayOrder(displayOrder); return this; }
        public Skill build() { return skill; }
    }
}

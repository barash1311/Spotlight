package com.barash.spotlight.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "page_content")
public class PageContent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String page; 

    @Column(nullable = false)
    private String section;

    @Column(nullable = false, name = "content_key")
    private String contentKey;

    @Column(columnDefinition = "TEXT")
    private String contentValue;

    @Column(name = "sort_order")
    private Integer sortOrder;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public PageContent() {}

    public PageContent(String page, String section, String contentKey, String contentValue, Integer sortOrder) {
        this.page = page;
        this.section = section;
        this.contentKey = contentKey;
        this.contentValue = contentValue;
        this.sortOrder = sortOrder;
    }

    public static PageContentBuilder builder() {
        return new PageContentBuilder();
    }

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getPage() { return page; }
    public void setPage(String page) { this.page = page; }
    public String getSection() { return section; }
    public void setSection(String section) { this.section = section; }
    public String getContentKey() { return contentKey; }
    public void setContentKey(String contentKey) { this.contentKey = contentKey; }
    public String getContentValue() { return contentValue; }
    public void setContentValue(String contentValue) { this.contentValue = contentValue; }
    public Integer getSortOrder() { return sortOrder; }
    public void setSortOrder(Integer sortOrder) { this.sortOrder = sortOrder; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }

    public static class PageContentBuilder {
        private String page;
        private String section;
        private String contentKey;
        private String contentValue;
        private Integer sortOrder;

        public PageContentBuilder page(String page) { this.page = page; return this; }
        public PageContentBuilder section(String section) { this.section = section; return this; }
        public PageContentBuilder contentKey(String contentKey) { this.contentKey = contentKey; return this; }
        public PageContentBuilder contentValue(String contentValue) { this.contentValue = contentValue; return this; }
        public PageContentBuilder sortOrder(Integer sortOrder) { this.sortOrder = sortOrder; return this; }
        public PageContent build() {
            return new PageContent(page, section, contentKey, contentValue, sortOrder);
        }
    }
}

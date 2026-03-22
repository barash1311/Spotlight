package com.barash.spotlight.dto;

import java.time.LocalDateTime;

public class ContactResponse {
    private Long id;
    private String name;
    private String email;
    private String subject;
    private String message;
    private boolean read;
    private LocalDateTime createdAt;

    public ContactResponse() {}

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getSubject() { return subject; }
    public String getMessage() { return message; }
    public boolean isRead() { return read; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    public static ContactResponseBuilder builder() {
        return new ContactResponseBuilder();
    }

    public static class ContactResponseBuilder {
        private Long id;
        private String name;
        private String email;
        private String subject;
        private String message;
        private boolean read;
        private LocalDateTime createdAt;

        public ContactResponseBuilder id(Long id) { this.id = id; return this; }
        public ContactResponseBuilder name(String name) { this.name = name; return this; }
        public ContactResponseBuilder email(String email) { this.email = email; return this; }
        public ContactResponseBuilder subject(String subject) { this.subject = subject; return this; }
        public ContactResponseBuilder message(String message) { this.message = message; return this; }
        public ContactResponseBuilder read(boolean read) { this.read = read; return this; }
        public ContactResponseBuilder createdAt(LocalDateTime createdAt) { this.createdAt = createdAt; return this; }

        public ContactResponse build() {
            ContactResponse resp = new ContactResponse();
            resp.id = this.id;
            resp.name = this.name;
            resp.email = this.email;
            resp.subject = this.subject;
            resp.message = this.message;
            resp.read = this.read;
            resp.createdAt = this.createdAt;
            return resp;
        }
    }
}

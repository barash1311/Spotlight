package com.barash.spotlight.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "contact_messages")
public class ContactMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String subject;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String message;

    @Column(name = "is_read", nullable = false)
    private boolean read = false;

    @org.hibernate.annotations.CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    public ContactMessage() {}

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getSubject() { return subject; }
    public String getMessage() { return message; }
    public boolean isRead() { return read; }
    public void setRead(boolean read) { this.read = read; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    public static ContactMessageBuilder builder() {
        return new ContactMessageBuilder();
    }

    public static class ContactMessageBuilder {
        private String name;
        private String email;
        private String subject;
        private String message;

        public ContactMessageBuilder name(String name) { this.name = name; return this; }
        public ContactMessageBuilder email(String email) { this.email = email; return this; }
        public ContactMessageBuilder subject(String subject) { this.subject = subject; return this; }
        public ContactMessageBuilder message(String message) { this.message = message; return this; }

        public ContactMessage build() {
            ContactMessage msg = new ContactMessage();
            msg.name = this.name;
            msg.email = this.email;
            msg.subject = this.subject;
            msg.message = this.message;
            return msg;
        }
    }
}

package com.fashion.blog.week8task.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String name;
    private String email;

    @Column(nullable = false, length = 2048)
    private String content;

    public Integer postId;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Getters and setters for other fields

    // Getter for createdAt (if needed)
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    // Getter for updatedAt (if needed)
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    // Hook method to automatically set createdAt and updatedAt before persisting
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    // Hook method to automatically update updatedAt before updating
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }


}



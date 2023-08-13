package com.fashion.blog.week8task.model;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class Posts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false, length = 2028)
    private String content;

    @Column(nullable = false)
    private String category;
    @ManyToOne
    private Users user;

    @Column(columnDefinition = "integer default 0")
    private int likes;
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

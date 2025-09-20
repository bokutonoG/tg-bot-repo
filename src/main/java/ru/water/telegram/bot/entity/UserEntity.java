package ru.water.telegram.bot.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Data
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;
    @Column(name = "telegram_id", nullable = false, unique = true)
    private long telegramId;
    @Column(name = "user_name")
    private String username;
    @Column(name = "is_subscribed", nullable = false)
    private boolean subscribed;
    @Column(name = "is_premium", nullable = false)
    private boolean premium;
    @Column(name = "quota", nullable = false)
    private int quota;
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;


    @PrePersist
    void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

}

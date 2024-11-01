package com.proyecto.backfinal.models;

import jakarta.persistence.*;

@Entity
@Table(name = "user_preferences")
public class UserPreferences {
    
    @Id
    private Long id;

    @Column(nullable = false)
    private Integer fontSize;

    @Column(nullable = false)
    private String fontFamily;

    @Column(nullable = false)
    private Boolean darkMode;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private AbstractUser user;

    
}

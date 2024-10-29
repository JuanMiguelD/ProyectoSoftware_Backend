package com.proyecto.backfinal.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
@AllArgsConstructor
public abstract class AbstractUser {
    @Id
    @Getter @Setter
    private int id;

    @Getter @Setter
    private String name;
    
    @Getter @Setter
    private String email;

    @Getter @Setter  
    private String password;

    @Getter  @Setter
    private String role;
    
}

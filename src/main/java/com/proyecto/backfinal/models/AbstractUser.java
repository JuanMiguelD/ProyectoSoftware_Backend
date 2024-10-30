package com.proyecto.backfinal.models;


import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // Estrategia de tabla única
@Table(name = "users")
@DiscriminatorColumn(name = "role")
public abstract class AbstractUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private int id;

    @Getter @Setter
    private String name;
    
    @Getter @Setter
    private String email;

    @Getter @Setter  
    private String password;


    public  AbstractUser(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    
}

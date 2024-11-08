package com.proyecto.backfinal.models;


import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // Estrategia de tabla única
@Table(name = "users")
@DiscriminatorColumn(name = "role")
public abstract class AbstractUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private long id;

    @Getter @Setter
    private String name;
    
    @Getter @Setter
    private String email;

    @Getter @Setter  
    private String password;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    @Getter
    private List<Purchase> purchases;


    @SuppressWarnings({ "unchecked", "rawtypes" })
    public  AbstractUser(String name, String email, String password) {
        
        this.name = name;
        this.email = email;
        this.password = password;
        this.purchases = new ArrayList();
    }

    public AbstractUser() {
    }

    
}

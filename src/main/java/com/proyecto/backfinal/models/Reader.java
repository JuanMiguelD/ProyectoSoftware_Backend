package com.proyecto.backfinal.models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("READER")
public class Reader extends AbstractUser {
    
    public Reader(String name, String email,  String password) {

        super(name, email, password);
    }
    
    public Reader() {
    }
}

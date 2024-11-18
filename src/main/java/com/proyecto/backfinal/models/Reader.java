// Juan Miguel Dimaté 0000282752 
// Andrey Esteban Conejo 0000281295 
// Carlos Bello 0000272648 
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

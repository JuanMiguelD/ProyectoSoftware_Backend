// Juan Miguel Dimaté 0000282752 
// Andrey Esteban Conejo 0000281295 
// Carlos Bello 0000272648 
package com.proyecto.backfinal.models;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;

@Entity
@DiscriminatorValue("WRITER")

@JsonIgnoreProperties({"purchases"})
public class Writer extends AbstractUser {

    @Transient
    @Getter @Setter
    private  String biography;

    @JsonIgnore

    @OneToMany(mappedBy = "writer", cascade = CascadeType.ALL)
    @Getter @Setter
    private List<AbstractBook>  bookswrited;
    
    public Writer(){
        
    }

    public Writer( String name, String email, String password, String biography) {
        super(name, email, password);
        this.biography = biography;
        this.bookswrited = new ArrayList<>(); // Inicializa una lista vacía
    }



    
}


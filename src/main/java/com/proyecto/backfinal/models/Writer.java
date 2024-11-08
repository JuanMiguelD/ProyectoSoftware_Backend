package com.proyecto.backfinal.models;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;

@Entity
@DiscriminatorValue("WRITER")

public class Writer extends AbstractUser {

    @Transient
    @Getter @Setter
    private  String biography;

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


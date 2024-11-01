package com.proyecto.backfinal.models;

import jakarta.persistence.Entity;

@Entity
public class AudioBook extends AbstractBook{
    
    public AudioBook(String title,String genre, String publication, Writer author){
        
        super(title, genre,publication, author);

    }
}

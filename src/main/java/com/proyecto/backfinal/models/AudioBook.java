package com.proyecto.backfinal.models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Audio Book")
public class AudioBook extends AbstractBook{
    
    public AudioBook(String title,String genre, String publication, Writer author, String contenido, int precio, String format){
        
        super(title, genre,publication, author, contenido, precio, format);

    }

    public AudioBook(){
        
    }
}

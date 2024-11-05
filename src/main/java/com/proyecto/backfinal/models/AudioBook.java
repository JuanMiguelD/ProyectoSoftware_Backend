package com.proyecto.backfinal.models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Audio Book")
public class AudioBook extends AbstractBook{
    
    public AudioBook(String isbn, String title,String genre, String publication, Writer author, String contenido){
        
        super(isbn,title, genre,publication, author, contenido);

    }
}

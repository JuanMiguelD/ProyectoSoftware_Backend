package com.proyecto.backfinal.models;

import jakarta.persistence.Entity;

@Entity
public class AudioBook extends AbstractBook{
    
    public AudioBook(Long isbn, String title,String genre, String publication, Writer author, String contenido){
        
        super(isbn,title, genre,publication, author, contenido);

    }
}

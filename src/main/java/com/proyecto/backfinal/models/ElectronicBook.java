package com.proyecto.backfinal.models;

import jakarta.persistence.Entity;

@Entity
public class ElectronicBook extends AbstractBook{

    public ElectronicBook(Long isbn,String title,String genre, String publication, Writer author, String contenido){
        
        super(isbn, title, genre, publication, author, contenido);

    }
    
}

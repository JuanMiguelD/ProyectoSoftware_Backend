package com.proyecto.backfinal.models;

import jakarta.persistence.Entity;

@Entity
public class ElectronicBook extends AbstractBook{

    public ElectronicBook(int isbn, String title,String genre, String publication, Writer author){
        
        super(isbn, title, genre,publication, author);

    }
    
}

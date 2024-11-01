package com.proyecto.backfinal.models;

import jakarta.persistence.Entity;

@Entity
public class ElectronicBook extends AbstractBook{

    public ElectronicBook(String title,String genre, String publication, Writer author){
        
        super(title, genre,publication, author);

    }
    
}

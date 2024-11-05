package com.proyecto.backfinal.models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("EBook")
public class ElectronicBook extends AbstractBook{

    public ElectronicBook(String isbn,String title,String genre, String publication, Writer author, String contenido){
        
        super(isbn, title, genre, publication, author, contenido);

    }
    
    public ElectronicBook(){

    }
}

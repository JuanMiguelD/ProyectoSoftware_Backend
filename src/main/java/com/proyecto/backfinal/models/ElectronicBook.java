package com.proyecto.backfinal.models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("EBook")
public class ElectronicBook extends AbstractBook{

    public ElectronicBook(String isbn,String title,String genre, String publication, Writer author, String content, int price){
        
        super(isbn, title, genre, publication, author, content, price);

    }
    
    public ElectronicBook(){

    }
}

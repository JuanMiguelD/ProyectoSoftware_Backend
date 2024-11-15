package com.proyecto.backfinal.models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("EBook")
public class ElectronicBook extends AbstractBook{

    public ElectronicBook(String title,String genre, String publication, Writer author, String content, int price){
        
        super(title, genre, publication, author, content, price);

    }
    
    public ElectronicBook(){

    }
}

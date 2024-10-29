package com.proyecto.backfinal.models;

public class AudioBook extends AbstractBook{
    
    public AudioBook(int isbn, String title, Writer author, String publicationDate){
        
        super(isbn, title, publicationDate,author);

    }
}

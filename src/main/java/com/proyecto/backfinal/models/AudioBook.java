// Juan Miguel Dimaté 0000282752 
// Andrey Esteban Conejo 0000281295 
// Carlos Bello 0000272648 

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

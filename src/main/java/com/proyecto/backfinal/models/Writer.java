package com.proyecto.backfinal.models;
import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;

@Entity
@DiscriminatorValue("WRITER")

public class Writer extends AbstractUser {

    @Transient
    @Getter @Setter
    private  String biography;

    @Transient
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private ArrayList<AbstractBook>  books;

    public Writer( String name, String email, String password, String biography) {
        super(name, email, password);
        this.biography = biography;
        this.books = new ArrayList<>(); // Inicializa una lista vacía
    }

    public void addBook(AbstractBook  book) {
        
        this.books.add(book); 
    
    }

    public void deleteBook(int isbn){
        this.books.removeIf(book -> (book.getIsbn() == isbn));
    }
    
    

    
}


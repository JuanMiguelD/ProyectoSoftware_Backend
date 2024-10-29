package com.proyecto.backfinal.models;
import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;

public class Writer extends AbstractUser {
    @Getter @Setter
    private  String biography;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL)
    @Getter @Setter
    private ArrayList<AbstractBook>  books;

    public Writer(int isbn, String name, String email, String password, String biography, String role) {
        super(isbn, name, email, password, role);
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


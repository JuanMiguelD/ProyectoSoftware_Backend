package com.proyecto.backfinal.models;

import java.util.List;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // Estrategia de tabla única
@Table(name = "books")
@DiscriminatorColumn(name = "type")
public abstract class AbstractBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private long bookId;

    @Getter @Setter
    private  String isbn;

    @Getter @Setter
    private  String title;

    @Getter @Setter
    private  String genre;

    @Getter @Setter
    private  String publication;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable=false)
    @Getter @Setter
    private  Writer writer;

    @OneToMany(mappedBy = "book")
    @Getter
    private List<Purchase> purchases;

    @Getter @Setter
    private String contenido;

    @Getter @Setter
    private int precio;
    

    public AbstractBook(String isbn,String title, String genre, String publication,  Writer author, String contenido, int precio) {
        this.isbn = isbn;
        this.title = title;
        this.genre = genre;
        this.publication = publication;
        this.writer = author;
        this.contenido = contenido;
        this.precio = precio;
        
    }

    public AbstractBook(){
        
    }
}

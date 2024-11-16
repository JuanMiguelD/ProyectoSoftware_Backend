package com.proyecto.backfinal.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // Estrategia de tabla única
@Table(name = "books")
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
public abstract class AbstractBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private long bookId;

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

    @Getter @Setter
    @Column(name = "type", nullable = false, insertable = false, updatable = false)
    private String type; // Este campo almacena el tipo de usuario

    @ManyToMany
    @JoinTable(
        name = "user_books",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    
    @JsonIgnore 
    @Getter @Setter
    private List<AbstractBook> purchasedBooks;


    @Getter @Setter
    private String content;

    @Getter @Setter
    private int price;

    @Getter @Setter
    private String format;
    

    public AbstractBook(String title, String genre, String publication,  Writer author, String content, int price, String format) {
        this.title = title;
        this.genre = genre;
        this.publication = publication;
        this.writer = author;
        this.content = content;
        this.price = price;
        this.format = format;
        
    }

    public AbstractBook(){
        
    }
}

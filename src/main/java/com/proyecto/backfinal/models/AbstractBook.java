package com.proyecto.backfinal.models;

import java.util.List;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // Estrategia de tabla única
@Table(name = "books")
public abstract class AbstractBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private  long isbn;

    @Getter @Setter
    private  String title;

    @Getter @Setter
    private  String genre;

    @Getter @Setter
    private  String publication;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable=false)
    @Getter @Setter
    private  Writer author;

    @OneToMany(mappedBy = "book")
    private List<Purchase> purchases;
    

    public AbstractBook(String title, String genre, String publication,  Writer author) {
        this.title = title;
        this.genre = genre;
        this.publication = publication;
        this.author = author;
    }
}

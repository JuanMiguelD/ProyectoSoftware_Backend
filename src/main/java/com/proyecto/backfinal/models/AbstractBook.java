package com.proyecto.backfinal.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // Estrategia de tabla única
@Table(name = "books")
@AllArgsConstructor
public abstract class AbstractBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private  int isbn;

    @Getter @Setter
    private  String title;

    @Getter @Setter
    private  String genre;

    @Getter @Setter
    private  String publication;

    @ManyToOne
    @JoinColumn(name = "author_id")
    @Getter @Setter
    private  Writer author;
}

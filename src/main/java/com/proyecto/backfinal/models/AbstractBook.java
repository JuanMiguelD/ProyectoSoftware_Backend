package com.proyecto.backfinal.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@AllArgsConstructor
public abstract class AbstractBook {

    @Getter @Setter
    private  int isbn;

    @Getter @Setter
    private  String title;

    @Getter @Setter
    private  String publicationDate;

    @ManyToOne
    @JoinColumn(name = "author_id")
    @Getter @Setter
    private  Writer author;
}

package com.proyecto.backfinal.models;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "purchases")
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private AbstractUser user;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private AbstractBook book;

    @Column(nullable = false)
    private Double purchasePrice;

    @Column(nullable = false)
    
    private LocalDateTime purchaseDate;

    public Purchase(){
        this.purchaseDate = LocalDateTime.now();
    }



    
}

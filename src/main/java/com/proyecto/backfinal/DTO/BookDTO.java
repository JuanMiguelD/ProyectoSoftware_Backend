package com.proyecto.backfinal.DTO;

import lombok.Getter;
import lombok.Setter;

public class BookDTO {

    @Getter @Setter
    private String title;
    
    @Getter @Setter
    private String genre;
    
    @Getter @Setter
    private String content;

    @Getter @Setter
    private String publication;
    
    @Getter @Setter
    private Long writer;

    @Getter @Setter
    private String type;

    @Getter @Setter
    private int price;
}
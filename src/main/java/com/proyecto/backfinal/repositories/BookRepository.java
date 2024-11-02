package com.proyecto.backfinal.repositories;

import com.proyecto.backfinal.models.*;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository  extends JpaRepository<AbstractBook, Long> {
    

    void  deleteByIsbn(Long isbn);

    List<AbstractBook> findByAuthor(Writer writer);
}

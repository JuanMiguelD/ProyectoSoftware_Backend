package com.proyecto.backfinal.repositories;

import com.proyecto.backfinal.models.*;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository  extends JpaRepository<AbstractBook, Long> {
    
    void  deleteById(long id);
    
    List<AbstractBook> findByWriterId(Long writer);

    List<AbstractBook> findByGenre(String genre);

    boolean existsByTitleAndWriterId(String title, long id);
    

    
}

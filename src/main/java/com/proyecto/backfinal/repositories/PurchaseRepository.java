package com.proyecto.backfinal.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.proyecto.backfinal.models.*;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

     @Query("SELECT p.book FROM Purchase p WHERE p.user.id = :userId")
    List<AbstractBook> findBooksPurchasedByUserId(@Param("userId") Long userId);
    
}

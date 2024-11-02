package com.proyecto.backfinal.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import com.proyecto.backfinal.models.AbstractUser;

public interface UserRepository extends JpaRepository<AbstractUser, Long> {
    AbstractUser findByEmail(String email);
    
}

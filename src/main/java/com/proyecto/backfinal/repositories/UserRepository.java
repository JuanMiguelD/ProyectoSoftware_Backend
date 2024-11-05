package com.proyecto.backfinal.repositories;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.proyecto.backfinal.models.AbstractUser;

public interface UserRepository extends JpaRepository<AbstractUser, Long> {
    
    Optional<AbstractUser> findByEmail(String email);
    
    Optional<AbstractUser> findById(Long  id);

    
}

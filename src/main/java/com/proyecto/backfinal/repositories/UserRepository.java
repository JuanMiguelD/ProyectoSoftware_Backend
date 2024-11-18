// Juan Miguel Dimaté 0000282752 
// Andrey Esteban Conejo 0000281295 
// Carlos Bello 0000272648 
package com.proyecto.backfinal.repositories;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.proyecto.backfinal.models.AbstractUser;

public interface UserRepository extends JpaRepository<AbstractUser, Long> {
    
    Optional<AbstractUser> findByEmail(String email);
    
    Optional<AbstractUser> findById(Long  id);


    
}

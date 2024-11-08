package com.proyecto.backfinal.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.proyecto.backfinal.models.*;
import com.proyecto.backfinal.repositories.UserRepository;

@Service
public class LoginService {
    @Autowired
    private UserRepository userRepository;

    public AbstractUser login(String email, String password) {
        Optional<AbstractUser> userOptional = userRepository.findByEmail(email);
    
        // Verifica si el usuario está presente y si la contraseña coincide
        if (userOptional != null && userOptional.isPresent()) {
            AbstractUser user = userOptional.get();

            if (user.getPassword().equals(password)) {
                return user;
            }
        }
        return null; // Retorna null si el usuario no existe o la contraseña no coincide
    }

    public AbstractUser register(AbstractUser user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            return null; // Retorna null si el usuario ya existe
        }
        return userRepository.save(user); // Guarda al usuario en la base de datos
    }
}


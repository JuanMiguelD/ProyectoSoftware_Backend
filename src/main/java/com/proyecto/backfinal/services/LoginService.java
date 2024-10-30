package com.proyecto.backfinal.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.proyecto.backfinal.models.*;
import com.proyecto.backfinal.repositories.UserRepository;

@Service
public class LoginService {
    @Autowired
    private UserRepository userRepository;

    public AbstractUser login(String email, String password) {
        AbstractUser user = userRepository.findByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            return user; // El usuario ha iniciado sesión correctamente
        }
        return null; // Login fallido
    }

    public AbstractUser register(AbstractUser user) {
        return userRepository.save(user); // Guarda al usuario en la base de datos
    }
}


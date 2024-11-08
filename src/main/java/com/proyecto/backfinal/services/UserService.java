package com.proyecto.backfinal.services;

import com.proyecto.backfinal.repositories.UserRepository;
import com.proyecto.backfinal.models.AbstractUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<AbstractUser> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<AbstractUser> getUserById(Long id) {
        return userRepository.findById(id);
    }


    // Actualizar información del usuario utilizando el correo
    public AbstractUser updateUser(String email, AbstractUser updatedUser) {
        return (userRepository.findByEmail(email))
            .map(user -> {
                user.setName(updatedUser.getName());
                user.setEmail(updatedUser.getEmail());
                return userRepository.save(user);
            })
            .orElseThrow(() -> new RuntimeException("User not found with email: " + email));
    }

    // Cambiar la contraseña utilizando el correo
    public AbstractUser changePassword(String email, String oldPassword, String newPassword) {
        return (userRepository.findByEmail(email))
                .map(user -> {
                if (user.getPassword().equals(oldPassword)) {
                    user.setPassword(newPassword);
                    return userRepository.save(user);
                } else {
                    throw new RuntimeException("Old password is incorrect");
                }
            })
            .orElseThrow(() -> new RuntimeException("User not found with email: " + email));
    }

   // Eliminar usuario utilizando el correo
    public void deleteUser(String email) {
        Optional<AbstractUser> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            AbstractUser user = userOptional.get(); // Obtener el usuario del Optional
            userRepository.delete(user); // Eliminar el usuario
        } else {
            throw new RuntimeException("User not found with email: " + email);
        }
    }


}

package com.proyecto.backfinal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.backfinal.DTO.*;
import com.proyecto.backfinal.models.*;
import com.proyecto.backfinal.services.LoginService;

@RestController
@RequestMapping("/api/users")
public class UserController {
    

    @Autowired
    private LoginService loginService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterUserDTO userDTO) {
        
        AbstractUser user;

        // Determina el tipo de usuario y crea una instancia de la subclase adecuada
        if ("Writer".equalsIgnoreCase(userDTO.getRole())) {
            user = new Writer(userDTO.getName(),userDTO.getEmail(),userDTO.getPassword(),null);
        }
        else if ("Reader".equalsIgnoreCase(userDTO.getRole())) {
            user = new Reader(userDTO.getName(),userDTO.getEmail(),userDTO.getPassword());

        } else {
            return ResponseEntity.badRequest().body("Error: Tipo de usuario inválido");
        }

        AbstractUser registeredUser = loginService.register(user);
        
        return ResponseEntity.ok().body("Usuario registrado exitosamente: " + registeredUser.getId());

    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginDTO loginDTO) {

        
        AbstractUser user = loginService.login(loginDTO.getEmail(), loginDTO.getPassword());

        return ResponseEntity.ok().body("Inicio de sesión exitoso de usuario:  " + user.getId());

    }
}

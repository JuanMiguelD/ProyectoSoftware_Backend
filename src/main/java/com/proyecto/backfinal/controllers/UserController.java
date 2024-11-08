package com.proyecto.backfinal.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.backfinal.DTO.*;
import com.proyecto.backfinal.models.*;
import com.proyecto.backfinal.services.LoginService;
import com.proyecto.backfinal.services.PurchaseService;

@RestController
@RequestMapping("/api/users")
public class UserController {
    

    @Autowired
    private LoginService loginService;

    @Autowired
    private PurchaseService purchaseService;

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
    public long loginUser(@RequestBody LoginDTO loginDTO) {

        
        AbstractUser user = loginService.login(loginDTO.getEmail(), loginDTO.getPassword());

        if (user == null) {
            throw new RuntimeException("Invalid credentials"); // Maneja el login fallido adecuadamente
        }

        return user.getId(); // Retorna el usuario con la lista de libros comprados ya cargada
    }

    //Obtener lo libros comprados por el usuario
    @GetMapping("/{userId}/purchased-books")
    public List<AbstractBook> getPurchasedBooks(@PathVariable Long userId) {
        return purchaseService.getBooksPurchasedByUserId(userId);
    }

    

}

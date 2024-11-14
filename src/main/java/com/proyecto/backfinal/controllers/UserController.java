package com.proyecto.backfinal.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        if ("Writer".equalsIgnoreCase(userDTO.getUserType())) {
            user = new Writer(userDTO.getName(),userDTO.getEmail(),userDTO.getPassword(),null);
        }
        else if ("Reader".equalsIgnoreCase(userDTO.getUserType())) {
            user = new Reader(userDTO.getName(),userDTO.getEmail(),userDTO.getPassword());

        } else {
            return ResponseEntity.badRequest().body("Error: Tipo de usuario inválido");
        }
        
        AbstractUser registeredUser = loginService.register(user);

        registeredUser.setToken(TokenGenerator.generateJwtToken());

        Map<String, Object> response = new HashMap<>();
        response.put("accessToken", registeredUser.getToken());
        
        return ResponseEntity.ok(response);

    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginDTO loginDTO) {

        AbstractUser user = loginService.login(loginDTO.getEmail(), loginDTO.getPassword());

        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Credenciales inválidas"));
        }

        String token = TokenGenerator.generateJwtToken();
        user.setToken(token);

        Map<String, Object> response = new HashMap<>();
        response.put("accessToken", user.getToken());
        response.put("userType", user.getRole());  // Asegúrate de tener `getRole` o un método similar

        return ResponseEntity.ok(response);
    }


    //Obtener lo libros comprados por el usuario
    @GetMapping("/{userId}/purchased-books")
    public List<AbstractBook> getPurchasedBooks(@PathVariable Long userId) {
        return purchaseService.getBooksPurchasedByUserId(userId);
    }

    

}

package com.proyecto.backfinal.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.backfinal.DTO.*;
import com.proyecto.backfinal.models.*;
import com.proyecto.backfinal.services.LoginService;
import com.proyecto.backfinal.services.PurchaseService;
import com.proyecto.backfinal.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
    

    @Autowired
    private LoginService loginService;

    @Autowired
    private PurchaseService purchaseService;

    @Autowired
    private UserService userService;

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

        registeredUser.setToken(TokenGenerator.generateJwtToken(registeredUser.getId()));
        userService.saveUser(registeredUser);

        Map<String, Object> response = new HashMap<>();
        response.put("accessToken", registeredUser.getToken());
        response.put("userId", registeredUser.getId());
        response.put("typeUser", registeredUser.getRole());
        
        return ResponseEntity.ok(response);

    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginDTO loginDTO) {

        AbstractUser user = loginService.login(loginDTO.getEmail(), loginDTO.getPassword());

        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Credenciales inválidas"));
        }

        String token = TokenGenerator.generateJwtToken(user.getId());
        user.setToken(token);
        userService.saveUser(user);

        Map<String, Object> responseBody = new HashMap<>();
        Map<String, Object> response = new HashMap<>();
        response.put("accessToken", user.getToken());
        response.put("userType", user.getRole());
        response.put("userId", user.getId());
        responseBody.put("body", response);

        return ResponseEntity.ok(responseBody);
    }


    //Obtener lo libros comprados por el usuario
    @GetMapping("/purchased-books")
    public List<AbstractBook> getPurchasedBooks(@RequestHeader("Authorization") String token) {
        
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);  // Elimina los 7 primeros caracteres ("Bearer ")
        }

        if(JwtValidator.validateJwtToken(token)){

            Long userId = JwtValidator.getUserIdFromJwtToken(token);

            AbstractUser user = userService.getUserById(userId).get();

             return purchaseService.getBooksPurchasedByUserId(user.getId());

        }
        return null;
    }


    @GetMapping("/profile")
    public ResponseEntity<?> getProfile(@RequestHeader("Authorization") String token) {
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);  // Elimina los 7 primeros caracteres ("Bearer ")
        }

        if(JwtValidator.validateJwtToken(token)){
            Long userId = JwtValidator.getUserIdFromJwtToken(token);
            AbstractUser user = userService.getUserById(userId).get();
            

            Map<String,Object> response = new HashMap<>();
            response.put("userId", String.valueOf(user.getId()));
            response.put("userName", user.getName());
            response.put("userEmail", user.getEmail());
            response.put("userType", user.getRole());
            return ResponseEntity.ok(response);
        }
    
        return null;
        
    }  

}

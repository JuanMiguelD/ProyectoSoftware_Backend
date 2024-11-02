package com.proyecto.backfinal;

import com.proyecto.backfinal.repositories.UserRepository;
import com.proyecto.backfinal.services.LoginService;
import com.proyecto.backfinal.models.AbstractUser;
import com.proyecto.backfinal.models.Reader;
import com.proyecto.backfinal.models.Writer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class LoginServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private LoginService loginService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Prueba del método login para un inicio de sesión exitoso
    @Test
    public void testLogin_Successful() {
        // Configuración de datos de prueba
        
        AbstractUser user = new Writer("testname","testemail@example.com","password123",null);
        

        // Configuración del mock
        when(userRepository.findByEmail("testemail@example.com")).thenReturn(user);

        // Llamada al método y verificación del resultado
        AbstractUser result = loginService.login("testemail@example.com", "password123");
        assertNotNull(result);
        assertEquals("testemail@example.com", result.getEmail());
    }

    // Prueba del método login para un inicio de sesión fallido (contraseña incorrecta)
    @Test
    public void testLogin_Failure_WrongPassword() {
        
        AbstractUser user = new Reader("testname2","testemail@example.com","password123");
        

        when(userRepository.findByEmail("wrongPassword")).thenReturn(user);

        AbstractUser result = loginService.login("testemail@example.com", "WrongPassword");
        assertNull(result); // Espera que el resultado sea nulo al fallar el login
    }

    // Prueba del método login para un inicio de sesión fallido (usuario no encontrado)
    @Test
    public void testLogin_Failure_UserNotFound() {
        String email = "nonexistent@example.com";
        String password = "password123";

        // Configura el mock para que no devuelva ningún usuario
        when(userRepository.findByEmail(email)).thenReturn(null);

        AbstractUser result = loginService.login(email, password);
        assertNull(result); // Se espera que el resultado sea nulo al no encontrar el usuario
    }

    // Prueba del método register para registrar un nuevo usuario
    @Test
    public void testRegister_Successful() {
        AbstractUser user = new Reader("newusername","newuser@example.com","newpassword");
        user.setEmail("newuser@example.com");
        user.setPassword("newpassword");

        // Configura el mock para devolver el usuario cuando se guarda
        when(userRepository.save(user)).thenReturn(user);

        AbstractUser result = loginService.register(user);
        assertNotNull(result); // Verifica que el usuario fue registrado correctamente
        assertEquals("newuser@example.com", result.getEmail());
    }
}

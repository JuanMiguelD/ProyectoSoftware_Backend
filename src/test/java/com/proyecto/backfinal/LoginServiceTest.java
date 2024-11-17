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

import java.util.Optional;

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
        when(userRepository.findByEmail("testemail@example.com")).thenReturn(Optional.of(user));

        // Llamada al método y verificación del resultado
        AbstractUser result = loginService.login("testemail@example.com", "password123");
        assertNotNull(result);
        assertEquals("testemail@example.com", result.getEmail());
    }

    // Prueba del método login para un inicio de sesión fallido (contraseña incorrecta)
    @Test
    public void testLogin_Failure_WrongPassword() {
        
        AbstractUser user = new Reader("testname2","testemail@example.com","password123");
        

        when(userRepository.findByEmail("wrongPassword")).thenReturn(Optional.of(user));

        AbstractUser result = loginService.login("testemail@example.com", "WrongPassword");
        assertNull(result); // Espera que el resultado sea nulo al fallar el login
    }

    // Prueba del método login para un inicio de sesión fallido (usuario no encontrado)
    @Test
    void testLogin_Failure_UserNotFound() {
        // Given
        String email = "nonexistent@example.com";
        String password = "password123";

        // When
        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        // Then
        AbstractUser result = loginService.login(email, password);
        assertNull(result);
        
        // Verify the repository was called with correct email
        verify(userRepository).findByEmail(email);
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

    // Prueba del método login cuando el email es nulo
    @Test
    void testLogin_Failure_NullEmail() {
        // When
        AbstractUser result = loginService.login(null, "password123");
        
        // Then
        assertNull(result);
        
    }

    // Prueba del método login cuando la contraseña es nula
    @Test
    void testLogin_Failure_NullPassword() {
        // Given
        AbstractUser user = new Writer("testname", "testemail@example.com", "password123", null);
        
        // When
        when(userRepository.findByEmail("testemail@example.com")).thenReturn(Optional.of(user));
        AbstractUser result = loginService.login("testemail@example.com", null);

        // Then
        assertNull(result);
    }

    // Prueba del método register cuando el usuario ya existe
    @Test
    void testRegister_Failure_UserAlreadyExists() {
        // Given
        AbstractUser user = new Reader("existingusername", "existinguser@example.com", "password123");
        
        // When
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
        AbstractUser result = loginService.register(user);

        // Then
        assertNull(result);
    }

    // Prueba del método register cuando se intenta registrar un usuario nulo
    @Test
    void testRegister_Failure_NullUser() {
        // When
        AbstractUser result = loginService.register(null);

        // Then
        assertNull(result);
    }

    // Prueba del método register cuando el email del usuario es nulo
    @Test
    void testRegister_Failure_NullEmail() {
        // Given
        AbstractUser user = new Reader("username", null, "password123");

        // When
        AbstractUser result = loginService.register(user);

        // Then
        assertNull(result);
    }

    // Prueba del método register cuando la contraseña del usuario es nula
    @Test
    void testRegister_Failure_NullPassword() {
        // Given
        AbstractUser user = new Reader("username", "newuser@example.com", null);

        // When
        AbstractUser result = loginService.register(user);

        // Then
        assertNull(result);
    }

}

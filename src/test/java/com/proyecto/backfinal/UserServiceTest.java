package com.proyecto.backfinal;

import com.proyecto.backfinal.models.*;
import com.proyecto.backfinal.repositories.UserRepository;
import com.proyecto.backfinal.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Prueba para obtener un usuario por correo electrónico
    @Test
    public void testGetUserByEmail_Successful() {
        String email = "test@example.com";
        Writer writer = new Writer("WriterName",email,"1234","test");
        

        // Configuración del mock para devolver el usuario
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(writer));

        // Llamada al método y verificación del resultado
        Optional<AbstractUser> result = userService.getUserByEmail(email);
        assertTrue(result.isPresent());
        assertEquals(email, result.get().getEmail());
    }

    //Prueba para cuando no se halla al usuario
    @Test
    void testGetUserByEmail_UserNotFound() {

        String email = "nonexistent@example.com";

        
        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());
        Optional<AbstractUser> result = userService.getUserByEmail(email);

        
        assertTrue(result.isEmpty());  // or assertFalse(result.isPresent())
        verify(userRepository).findByEmail(email);
    }

    // Prueba para actualizar la información del usuario
    @Test
    public void testUpdateUser_Successful() {

        String email = "test@example.com";
        Writer existingUser = new Writer("Old Name","OldEmail@example.com","1234","hola");
        

        Writer updatedUser = new Writer("New Name", email,"1234","hola");

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(existingUser));

        when(userRepository.save(any(Writer.class))).thenReturn(updatedUser);

        AbstractUser result = userService.updateUser(email, updatedUser);
        assertEquals("New Name", result.getName());
        assertEquals(email, result.getEmail());
    }

    // Prueba para cambiar la contraseña
    @Test
    public void testChangePassword_Successful() {
        String email = "test@example.com";
        String oldPassword = "oldPassword";
        String newPassword = "newPassword";
        Reader reader = new Reader("Testname",email,oldPassword);
        

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(reader));
        when(userRepository.save(any(Reader.class))).thenReturn(reader);

        AbstractUser result = userService.changePassword(email, oldPassword, newPassword);
        assertEquals(newPassword, result.getPassword());
    }

    // Prueba para cambio de contraseña fallido debido a una contraseña anterior incorrecta
    @Test
    public void testChangePassword_Failure_WrongOldPassword() {
        String email = "test@example.com";
        String oldPassword = "incorrectOldPassword";
        AbstractUser reader = new Reader("TestName", "test@example.com","actualOldPassword");
        reader.setPassword("actualOldPassword");

       when(userRepository.findByEmail(email)).thenReturn(Optional.of(reader));
       when(userRepository.save(any(AbstractUser.class))).thenReturn(reader);;

        assertThrows(RuntimeException.class, () -> userService.changePassword("test@example.com", oldPassword, "newPassword"));
    }

    // Prueba para eliminar un usuario
    @Test
    public void testDeleteUser_Successful() {
        String email = "test@example.com";
        AbstractUser user = new Writer("Test Name", email, "password", "test");

        // Configuración del mock
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

        // Llamada al método
        userService.deleteUser(email);

        // Verificar que el repositorio delete() ha sido llamado una vez
        verify(userRepository, times(1)).delete(user);
    }

    // Prueba para eliminar un usuario cuando no existe
    @Test
    public void testDeleteUser_UserNotFound() {
        String email = "nonexistent@example.com";
        
        // Configuración del mock
        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        // Verificar que se lanza la excepción
        assertThrows(RuntimeException.class, () -> userService.deleteUser(email));
    }

    @Test
    public void testUpdateUser_UserNotFound() {
        String email = "nonexistent@example.com";
        AbstractUser updatedUser = new Writer("New Name", email, "1234", "hola");

        // Configuración del mock para que no se encuentre el usuario
        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        // Verificar que se lanza una excepción
        assertThrows(RuntimeException.class, () -> userService.updateUser(email, updatedUser));
    }

    

}


package com.proyecto.backfinal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;


import com.proyecto.backfinal.models.Writer;
import com.proyecto.backfinal.services.LoginService;
import  com.proyecto.backfinal.models.AbstractUser;


@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class LoginServiceTest {

    @Autowired
    private LoginService loginService;

    @Test
    public void testRegisterWriter() {
        // Crear un nuevo usuario tipo Writer
        Writer writer = new Writer("Juan","juan@example.com","123456","hola");
        

        // Registrar el usuario
        AbstractUser savedUser = loginService.register(writer);

        // Verificar que el usuario se haya guardado
        assertNotNull(savedUser);
        assertNotNull(savedUser.getId()); // ID debe haberse generado
    }
}


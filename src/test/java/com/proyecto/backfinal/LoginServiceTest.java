package com.proyecto.backfinal;



import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import com.proyecto.backfinal.models.Reader;
import com.proyecto.backfinal.models.Writer;
import com.proyecto.backfinal.repositories.UserRepository;


@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@Transactional
public class LoginServiceTest {


    @Autowired
    private UserRepository userRepository;

    private Reader testReader;
    private Writer testWriter;

    @BeforeEach
    void setUp() {
        
        // Configurar usuario lector de prueba
        testReader = new Reader("TestReader","reader@test.com","password123");
        
        userRepository.save(testReader);

        // Configurar usuario escritor de prueba
        testWriter = new Writer("TestWriter","writer@test.com","password456","En efecto, soy escritor");
        
        userRepository.save(testWriter);
    }



}
package com.proyecto.backfinal.models;

import java.security.Key;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;


public class JwtConfig {
    private static JwtConfig instance;
    private final Key SECRET_KEY;
        
    private JwtConfig(){
        this.SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    }

    public static JwtConfig getJwtConfig(){
        if (instance == null) {
            synchronized (JwtConfig.class) {
                if (instance == null) {
                    instance = new JwtConfig();
                }
            }
        }
        return instance;
    }

    public Key getKey(){
        return SECRET_KEY;
    }
    

}


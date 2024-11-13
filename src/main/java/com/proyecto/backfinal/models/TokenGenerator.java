package com.proyecto.backfinal.models;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class TokenGenerator {
    public static String generateJwtToken() {
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256); // Genera una clave segura de 256 bits
        return Jwts.builder()
                   .setSubject("usuario")
                   .signWith(key)
                   .compact();
    }
}

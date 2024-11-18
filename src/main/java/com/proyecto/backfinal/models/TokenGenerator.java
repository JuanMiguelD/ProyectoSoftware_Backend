// Juan Miguel Dimaté 0000282752 
// Andrey Esteban Conejo 0000281295 
// Carlos Bello 0000272648 
package com.proyecto.backfinal.models;
import io.jsonwebtoken.Jwts;


import java.security.Key;
import java.util.Date;

public class TokenGenerator {
    private static JwtConfig jwtConfig = JwtConfig.getJwtConfig();
    private static final Key key = jwtConfig.getKey();
    private static final long EXPIRATION_TIME = 60 * 60 * 1000; // 15 minutos en milisegundos

    public static String generateJwtToken(long userId) {
        return Jwts.builder()
                   .setSubject("usuario")
                   .claim("userId", userId)
                   .setIssuedAt(new Date())
                   .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                   .signWith(key)
                   .compact();
    }
}

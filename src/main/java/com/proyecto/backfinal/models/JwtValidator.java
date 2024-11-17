package com.proyecto.backfinal.models;

import java.security.Key;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;

public class JwtValidator {
    private static JwtConfig jwtConfig = JwtConfig.getJwtConfig();
    private static final Key key = jwtConfig.getKey();  // Usando la clave compartida

    public static boolean validateJwtToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true; // Si la firma es válida y el token no está expirado
        } catch (@SuppressWarnings("deprecation") SignatureException e) {
            System.out.println("Firma JWT no válida");
        } catch (ExpiredJwtException e) {
            System.out.println("Token expirado");
        } catch (Exception e) {
            System.out.println("Token no válido");
        }
        return false;
    }

    public static Long getUserIdFromJwtToken(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                .setSigningKey(key) // Usamos la misma clave para validar
                .build()
                .parseClaimsJws(token)
                .getBody(); // Obtenemos el cuerpo (payload)

            // Extraemos el userId del payload
            return claims.get("userId", Long.class);  // Aquí se obtiene el userId del token
        } catch (Exception e) {
            System.out.println("Error al extraer el userId del token");
        }
        return null; // Si algo sale mal o el token no contiene el userId
    }
}

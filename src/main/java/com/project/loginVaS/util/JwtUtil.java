package com.project.loginVaS.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtil {
    //Clave secreta
    private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    //Tiempo de expiracion
    private static final long EXPIRATION_TIME = 1000 * 60 * 60;

    //Generar token
    public String generationToken(String email){
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SECRET_KEY)
                .compact();
    }

    //Extraer email
    public String extractEmail(String token){
        return extractClaim(token, Claims::getSubject);
    }

    //Extraer username
    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }

    //Extraer un claim específico del  token
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    //Verificar si el token expiró
    public Boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    //Extraer fecha de expiración
    public Date extractExpiration(String token){
        return extractClaim(token, Claims::getExpiration);
    }

    //Validar si el token pertenece al usuario correcto y no expiró
    public Boolean validateToken(String token, String email){
        final String tokenEmail = extractEmail(token);
        return (tokenEmail.equals(email) &&  !isTokenExpired(token));
    }

    //extraer todos lo token
    public Claims extractAllClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}

package com.project.loginVaS.util;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class JwtUtil {
    //Utils elements
    //PASSWORD
    private final Key SECRET_KEY =Keys.secretKeyFor(SignatureAlgorithm.HS512);
        //10 HOURS
    private final long EXPIRATION_TIME = 1000 * 60 * 60;

    //Generar token
    public String generateToken(String username){
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder().setClaims(claims)
                .setSubject(username).setIssuedAt(new Date(System.currentTimeMillis()))//fecha de creacón
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))//EXPIRACION
                .signWith(SECRET_KEY).compact();
    }

    //Extraer payload(info importante)
    public Claims extractAllClaims(String token){
        return Jwts.parserBuilder().setSigningKey(SECRET_KEY)
                .build().parseClaimsJws(token).getBody();
    }

    //Extraer nombre
    public String extractName(String token){
        return extractAllClaims(token).getSubject();
    }

    //booelean
    public boolean isTokenExpired(String token){
        return extractAllClaims(token).getExpiration().before(new Date());
    }

    //Expiraccion del token
    public boolean validateToken(String token, String username){
        String extractUser = extractName(token);
        return (extractUser.equals(username) && !isTokenExpired(token));
    }

    //
}

package com.reclamacoes.sistema_reclamacoes.security;


import java.util.Date;

import javax.crypto.SecretKey;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class JwtUtil {
private static final SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);   
 private static long EXPIRATION_TIME = 86400000;


 public static String generateToken(String cpf){
    return Jwts.builder()
    .setSubject(cpf)
    .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
    .signWith(key, SignatureAlgorithm.HS256)
    .compact();
 }
    
public static String extractCpf(String token) {
    return Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token) 
            .getBody()
            .getSubject();
}

public static boolean validateToken(String token){
    try{
        Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
        return true;
    } catch(JwtException e){
        return false;
    }
}
}
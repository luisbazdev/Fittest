package com.example.demo.jwt;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenUtil {
    @Value("${jwt.secret}")
    private String secret;
    private static final long JWT_TOKEN_VALIDITY = 60 * 60 * 60 * 1000;

    public Claims getAllClaimsFromToken(String token){
        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));

        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }

    public String GenerateToken(String sub, String name, String email){
        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));

        String jws = Jwts.builder()
        .setIssuer("http://localhost:8080")
        .setSubject(sub)
        .setAudience("https://fittest.com")
        .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .claim("name", name)
        .claim("email", email)
        .signWith(key)
        .compact();
        
        return jws;
    }

    public Boolean isTokenValid(String token){
        try {
            SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));

            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);

            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

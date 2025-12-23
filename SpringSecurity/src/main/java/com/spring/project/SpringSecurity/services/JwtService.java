package com.spring.project.SpringSecurity.services;

import com.spring.project.SpringSecurity.entities.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.Security;
import java.util.Date;
import java.util.Set;

@Service
public class JwtService {

    @Value("${jwt.security}")
    private String jwtSecreatKey;



    public String generateAccessToken(UserEntity user)
    {
        return Jwts.builder()
                .subject(user.getId().toString())
                .claim("email",user.getEmail())
                .claim("roles", Set.of("ADMIN","USER"))
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+1000*60))
                .signWith(getSecreatKey())
                .compact();
    }

    public String generateRefreshToken(UserEntity user)
    {
        return Jwts.builder()
                .subject(user.getId().toString())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+1000L*60*60*24*30*6))
                .signWith(getSecreatKey())
                .compact();
    }

    private SecretKey getSecreatKey() {
        return Keys.hmacShaKeyFor(jwtSecreatKey.getBytes(StandardCharsets.UTF_8));
    }


    public Long getUserById(String token)
    {
        Claims claims = Jwts.parser()
                .verifyWith(getSecreatKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return Long.valueOf(claims.getSubject());
    }



}

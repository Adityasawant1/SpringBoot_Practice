package com.spring.project.ContactDetails.services;

import com.spring.project.ContactDetails.entities.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Set;

@Service
public class JwtService {

    @Value("${jwt.secreatkey}")
    private String jwtSecreatKey;

    private SecretKey getSecretkey()
    {
        return Keys.hmacShaKeyFor(jwtSecreatKey.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(UserEntity user)
    {
        return Jwts.builder()
                .subject(user.getId().toString())
                .claim("email",user.getEmail())
                .claim("roles", Set.of("ADMIN","USER"))
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+86400000))
                .signWith(getSecretkey())
                .compact();
    }

    public Long getUserId(String token)
    {
        Claims claims = Jwts.parser()
                .verifyWith(getSecretkey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return Long.valueOf(claims.getSubject());
    }
}

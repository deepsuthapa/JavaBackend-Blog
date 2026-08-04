package com.blog.demo.Service;

import com.blog.demo.Models.Users;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService {

    private final String JWT_SECRET = "JwtSuperSecretCodeForSecurity0123456789";

    public String generateToken(String email) {

        return Jwts.builder()
            .subject(email)
            .issuedAt(new Date())
            .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 3)) // 3 days
            .signWith(Keys.hmacShaKeyFor(this.JWT_SECRET.getBytes()))
            .compact();
    }

    public String decodeTokenAndGetSubject(String token) {
        Claims claims = Jwts.parser()
            .verifyWith(Keys.hmacShaKeyFor(this.JWT_SECRET.getBytes(StandardCharsets.UTF_8)))
            .build()
            .parseSignedClaims(token)
            .getPayload();

        return claims.getSubject();
    }
}

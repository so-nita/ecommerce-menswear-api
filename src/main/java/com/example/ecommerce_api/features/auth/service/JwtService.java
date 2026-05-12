package com.example.ecommerce_api.features.auth.service;

import com.example.ecommerce_api.config.JwtSettings;
import com.example.ecommerce_api.features.user.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class JwtService {
    private final JwtSettings jwtSettings;

    private SecretKey signingKey(){
        return Keys.hmacShaKeyFor(jwtSettings.secret().getBytes());
    }

    public String generateAccessToken(User user){
        Instant now = Instant.now();
        Instant expiry = now.plusMillis(jwtSettings.accessTokenExpiration());

        return Jwts.builder()
                .id(UUID.randomUUID().toString())
                .subject(user.getId().toString())
                .claim("username", user.getUsername())
                .claim("type", "access")
                .issuer(jwtSettings.issuer())
                .issuedAt(Date.from(now))
                .expiration(Date.from(expiry))
                .signWith(signingKey())
                .compact();
    }

    public String generateRefreshToken(User user){
        Instant now = Instant.now();
        Instant expiry = now.plusMillis(jwtSettings.refreshTokenExpiration());

        return Jwts.builder()
                .id(UUID.randomUUID().toString())
                .subject(user.getId().toString())
                .claim("type", "refresh")
                .issuer(jwtSettings.issuer())
                .issuedAt(Date.from(now))
                .expiration(Date.from(expiry))
                .signWith(signingKey())
                .compact();
    }

    public Claims parseToken(String token) {
        return Jwts.parser()
                .verifyWith(signingKey())
                .requireIssuer(jwtSettings.issuer())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String extractUserId(String token) {
        return parseToken(token).getSubject();
    }

    public String extractUsername(String token) {
        return parseToken(token).get("username", String.class);
    }

    public boolean isExpired(String token) {
        try {
            return parseToken(token).getExpiration().before(new Date());
        } catch (Exception e) {
            return true;
        }
    }

    public boolean isValid(String token) {
        try {
            parseToken(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Instant getExpiration(String token) {
        return parseToken(token).getExpiration().toInstant();
    }
}

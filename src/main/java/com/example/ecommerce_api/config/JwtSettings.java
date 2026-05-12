package com.example.ecommerce_api.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.jwt")
public record JwtSettings (
        String secret,
        long accessTokenExpiration,
        long refreshTokenExpiration,
        String issuer
){}

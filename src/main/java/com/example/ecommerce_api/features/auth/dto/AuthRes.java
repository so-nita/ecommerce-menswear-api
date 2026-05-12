package com.example.ecommerce_api.features.auth.dto;

import jakarta.annotation.Nullable;

import java.time.Instant;
import java.util.Date;

public record AuthRes(
        String jwtId,
        String accessToken,
        String refreshToken,
        @Nullable Instant accessExpires,
        @Nullable Instant refreshExpires
) {}

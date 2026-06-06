package com.example.ecommerce_api.features.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record SignInReq(
        @NotBlank
        @Schema(description = "Username or phone number")
        String identifier,

        @NotBlank
        String password
) {}


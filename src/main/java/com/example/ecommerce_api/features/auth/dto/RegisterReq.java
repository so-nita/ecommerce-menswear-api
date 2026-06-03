package com.example.ecommerce_api.features.auth.dto;

import jakarta.validation.constraints.NotBlank;

public record RegisterReq(
        String username,

        @NotBlank
        String password,

        @NotBlank
        String phone
){ }

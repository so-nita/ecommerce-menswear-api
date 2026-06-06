package com.example.ecommerce_api.features.auth.dto;

import com.example.ecommerce_api.common.constant.RoleConstant;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterReq (
        @NotBlank
        String phone,

        @NotBlank
        String password,

        String firstName,
        String lastName,
        String imageId,

        @NotNull
        RoleConstant role
){ }

package com.example.ecommerce_api.features.auth.service;

import com.example.ecommerce_api.common.dto.ApiResponse;
import com.example.ecommerce_api.features.auth.dto.RefreshTokenReq;
import com.example.ecommerce_api.features.auth.dto.SignInReq;
import com.example.ecommerce_api.features.auth.dto.AuthResponse;
import com.example.ecommerce_api.features.auth.dto.RegisterReq;

public interface AuthService {
    ApiResponse<AuthResponse> SignInAsync(SignInReq request);
    ApiResponse<AuthResponse>  RegisterAsync(RegisterReq request);
    ApiResponse<AuthResponse> RefreshAsync(RefreshTokenReq refreshToken);
}

package com.example.ecommerce_api.features.auth.service;

import com.example.ecommerce_api.common.dto.ApiResponse;
import com.example.ecommerce_api.features.auth.dto.RefreshTokenReq;
import com.example.ecommerce_api.features.auth.dto.SignInReq;
import com.example.ecommerce_api.features.auth.dto.AuthRes;
import com.example.ecommerce_api.features.auth.dto.SignUpReq;

public interface AuthService {
    ApiResponse<AuthRes> SignInAsync(SignInReq request);
    ApiResponse<AuthRes>  SignUpAsync(SignUpReq request);
    ApiResponse<AuthRes> RefreshAsync(RefreshTokenReq refreshToken);
}

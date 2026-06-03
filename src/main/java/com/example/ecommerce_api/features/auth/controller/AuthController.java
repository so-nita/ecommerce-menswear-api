package com.example.ecommerce_api.features.auth.controller;

import com.example.ecommerce_api.common.dto.ApiResponse;
import com.example.ecommerce_api.features.auth.dto.AuthResponse;
import com.example.ecommerce_api.features.auth.dto.RefreshTokenReq;
import com.example.ecommerce_api.features.auth.dto.SignInReq;
import com.example.ecommerce_api.features.auth.dto.RegisterReq;
import com.example.ecommerce_api.features.auth.service.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/Auth")
@Tag(name = "Auth", description = "Authentication endpoints")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/sign-in")
    public ResponseEntity<ApiResponse<AuthResponse>> signIn(@Valid @RequestBody SignInReq request) {
        ApiResponse<AuthResponse> response = authService.SignInAsync(request);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<AuthResponse>> register(@Valid @RequestBody RegisterReq request) {
        ApiResponse<AuthResponse> response = authService.RegisterAsync(request);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PostMapping("refresh")
    public ResponseEntity<ApiResponse<AuthResponse>> refresh(@Valid @RequestBody RefreshTokenReq request){
        ApiResponse<AuthResponse> response = authService.RefreshAsync(request);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

}

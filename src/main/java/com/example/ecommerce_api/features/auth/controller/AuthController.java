package com.example.ecommerce_api.features.auth.controller;

import com.example.ecommerce_api.common.dto.ApiResponse;
import com.example.ecommerce_api.features.auth.dto.AuthRes;
import com.example.ecommerce_api.features.auth.dto.RefreshTokenReq;
import com.example.ecommerce_api.features.auth.dto.SignInReq;
import com.example.ecommerce_api.features.auth.dto.SignUpReq;
import com.example.ecommerce_api.features.auth.service.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/Auth")
@Tag(name = "Auth", description = "Authentication endpoints")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/sign-in")
    public ResponseEntity<ApiResponse<AuthRes>> signIn(@Valid @RequestBody SignInReq request) {
        ApiResponse<AuthRes> response = authService.SignInAsync(request);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<ApiResponse<AuthRes>> signUp(@Valid @RequestBody SignUpReq request) {
        ApiResponse<AuthRes> response = authService.SignUpAsync(request);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PostMapping("refresh")
    public ResponseEntity<ApiResponse<AuthRes>> refresh(@Valid @RequestBody RefreshTokenReq request){
        ApiResponse<AuthRes> response = authService.RefreshAsync(request);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

}

package com.example.ecommerce_api.features.auth.service;

import com.example.ecommerce_api.common.dto.ApiResponse;
import com.example.ecommerce_api.features.auth.dto.RefreshTokenReq;
import com.example.ecommerce_api.features.auth.dto.SignInReq;
import com.example.ecommerce_api.features.auth.dto.AuthRes;
import com.example.ecommerce_api.features.auth.dto.SignUpReq;
import com.example.ecommerce_api.features.user.entity.User;
import com.example.ecommerce_api.features.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    @Transactional(readOnly = true)
    public ApiResponse<AuthRes> SignInAsync(SignInReq request) {
        try {
            Optional<User> userFound = userRepository.findByUsername(request.username()).filter((e)-> !e.isDeleted());
            if(userFound.isEmpty()){
                return ApiResponse.Unauthorized("Incorrect username");
            }

            User user = userFound.get();

            if (!passwordEncoder.matches(request.password(), user.getPasswordHash())) {
                return ApiResponse.Unauthorized("Invalid credentials");
            }

            String accessToken = jwtService.generateAccessToken(user);
            String refreshToken = jwtService.generateRefreshToken(user);

            AuthRes response = new AuthRes(
                    UUID.randomUUID().toString(),
                    accessToken,
                    refreshToken,
                    jwtService.getExpiration(accessToken),
                    jwtService.getExpiration(refreshToken)
            );

            return ApiResponse.OK(response);
        } catch (Exception e) {
            return ApiResponse.InternalServerError(e.getMessage());
        }
    }

    @Override
    public ApiResponse<AuthRes> SignUpAsync(SignUpReq request) {
        try {
            return null;
        } catch (Exception e) {
            return ApiResponse.InternalServerError(e.getMessage());
        }
    }

    @Override
    public ApiResponse<AuthRes> RefreshAsync(RefreshTokenReq refreshToken) {
        try {
            return null;
        } catch (Exception e) {
            return ApiResponse.InternalServerError(e.getMessage());
        }
    }
}

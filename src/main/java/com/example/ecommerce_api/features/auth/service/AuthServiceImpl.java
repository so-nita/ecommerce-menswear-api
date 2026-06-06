package com.example.ecommerce_api.features.auth.service;

import com.example.ecommerce_api.common.dto.ApiResponse;
import com.example.ecommerce_api.features.auth.dto.RefreshTokenReq;
import com.example.ecommerce_api.features.auth.dto.SignInReq;
import com.example.ecommerce_api.features.auth.dto.AuthResponse;
import com.example.ecommerce_api.features.auth.dto.RegisterReq;
import com.example.ecommerce_api.features.user.entity.User;
import com.example.ecommerce_api.features.user.entity.UserProfile;
import com.example.ecommerce_api.features.user.repository.RoleRepository;
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
    private final RoleRepository roleRepository;

    @Override
    @Transactional(readOnly = true)
    public ApiResponse<AuthResponse> SignInAsync(SignInReq request) {
        try {
            Optional<User> userFound = userRepository
                    .findByUsernameOrPhoneNumber(request.identifier(), request.identifier())
                    .filter((e)-> !e.isDeleted());
            if(userFound.isEmpty()){
                return ApiResponse.Unauthorized("Incorrect username or phone number");
            }

            User user = userFound.get();

            if (!passwordEncoder.matches(request.password(), user.getPasswordHash())) {
                return ApiResponse.Unauthorized("Invalid credentials");
            }

            String jwtId = UUID.randomUUID().toString();
            String accessToken = jwtService.generateAccessToken(user);
            String refreshToken = jwtService.generateRefreshToken(user);

            var accessTokenExpire = jwtService.getExpiration(accessToken);
            var refreshTokenExpire = jwtService.getExpiration(refreshToken);

            AuthResponse response = new AuthResponse(
                    jwtId,
                    accessToken,
                    refreshToken,
                    accessTokenExpire,
                    refreshTokenExpire
            );

            return ApiResponse.OK(response);
        } catch (Exception e) {
            return ApiResponse.InternalServerError(e.getMessage());
        }
    }

    @Override
    public ApiResponse<AuthResponse> RegisterAsync(RegisterReq request) {
        try {
            if (userRepository.existsByUsername(request.phone())) {
                return ApiResponse.BadRequest("Phone number already exists");
            }

            String role = request.role().toString();
            if (role.isEmpty()) {
                return ApiResponse.Unauthorized("Role is required");
            }

            var roleFound = roleRepository.findFirstByName(role).orElseThrow(() -> new IllegalStateException("Role is invalid"));

            var userName = request.firstName().toUpperCase() + request.lastName().toUpperCase();
            var newUser = User.builder()
                    .username(userName)
                    .passwordHash(passwordEncoder.encode(request.password()))
                    .phoneNumber(request.phone())
                    .firstName(request.firstName())
                    .lastName(request.lastName())
                    .role(roleFound)
                    .build();

            var userProfile = new UserProfile();
            userProfile.setUser(newUser);
            newUser.setUserProfile(userProfile);

            userRepository.save(newUser);

            String accessToken = jwtService.generateAccessToken(newUser);
            String refreshToken = jwtService.generateRefreshToken(newUser);

            var response = new AuthResponse(
                    UUID.randomUUID().toString(),
                    accessToken,
                    refreshToken,
                    jwtService.getExpiration(accessToken),
                    jwtService.getExpiration(refreshToken)
            );

            return ApiResponse.Created(response, "Registered successfully");
        } catch (Exception e) {
            return ApiResponse.InternalServerError(e.getMessage());
        }
    }

    @Override
    public ApiResponse<AuthResponse> RefreshAsync(RefreshTokenReq refreshToken) {
        try {
            return null;
        } catch (Exception e) {
            return ApiResponse.InternalServerError(e.getMessage());
        }
    }
}

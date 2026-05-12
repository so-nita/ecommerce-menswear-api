package com.example.ecommerce_api.features.user.service;

import com.example.ecommerce_api.features.user.entity.User;
import com.example.ecommerce_api.features.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public User getUserById(UUID id){
        try {
            Optional<User> userFound = userRepository.findById(id);
            if (userFound.isEmpty()){
                return null;
            }

            User user = userFound.get();

            return user;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

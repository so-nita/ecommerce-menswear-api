package com.example.ecommerce_api.config;

import com.example.ecommerce_api.features.auth.service.JwtService;
import com.example.ecommerce_api.features.user.entity.User;
import com.example.ecommerce_api.features.user.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;
import java.util.stream.Collectors;

// JWT filter — reads the header on every request
@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader("Authorization");

        if (header == null || !header.startsWith("Bearer") ){
            filterChain.doFilter(request, response);
            return;
        }

        String token = header.substring(7); // Sub text "Bearer"
        try {
            if(!jwtService.isValid(token)){
                filterChain.doFilter(request, response);
                return;
            }

            String userId = jwtService.extractUserId(token);
            User user = userRepository.findById(UUID.fromString(userId)).orElse(null);

            if(user != null && !user.isDeleted() && SecurityContextHolder.getContext().getAuthentication() == null){
                /* var authorities = user.getRoles().stream()
                        .map(r -> new SimpleGrantedAuthority(r.getName()))
                        .collect(Collectors.toSet());*/

                var authorities = new SimpleGrantedAuthority("ROLE_USER", user.getRole().getName())

                var auth = new UsernamePasswordAuthenticationToken(user, null, authorities);

                auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(auth);
            }

        } catch (Exception e) {
            log.debug("JWT validation failed: {}", e.getMessage());
        }

        filterChain.doFilter(request, response);
    }
}

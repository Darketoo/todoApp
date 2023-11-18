package com.senaanalisis.TodoApp.service;

import com.senaanalisis.TodoApp.auth.dto.AuthResponse;
import com.senaanalisis.TodoApp.auth.dto.LoginRequest;
import com.senaanalisis.TodoApp.auth.dto.RegisterRequest;
import com.senaanalisis.TodoApp.persistence.entity.Role;
import com.senaanalisis.TodoApp.persistence.entity.UserEntity;
import com.senaanalisis.TodoApp.persistence.repository.UserRepository;
import com.senaanalisis.TodoApp.auth.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {
        // Autenticar al usuario
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        // Obtener la entidad del usuario
        UserEntity userEntity = userRepository.findByUsername(request.getUsername()).orElseThrow();

        // Obtener userId de la entidad del usuario
        String userId = String.valueOf(userEntity.getId());

        // Generar token con userId
        String token = jwtService.getToken(userEntity, userId);

        // Construir y devolver la respuesta de autenticación
        return AuthResponse.builder()
                .token(token)
                .build();
    }


    public void register(RegisterRequest request) {
        UserEntity user = UserEntity.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .name(request.getName())
                .email(request.getEmail())
                .role(Role.ADMIN)
                .build();

        userRepository.save(user);
    }
}

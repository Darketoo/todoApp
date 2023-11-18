package com.senaanalisis.TodoApp.service;

import com.senaanalisis.TodoApp.auth.dto.AuthResponse;
import com.senaanalisis.TodoApp.auth.dto.RegisterRequest;
import com.senaanalisis.TodoApp.auth.jwt.JwtService;
import com.senaanalisis.TodoApp.exception.UserNotFoundException;
import com.senaanalisis.TodoApp.persistence.entity.Dto.UserDto;
import com.senaanalisis.TodoApp.persistence.entity.UserEntity;
import com.senaanalisis.TodoApp.persistence.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public UserDto getUser(int id) throws UserNotFoundException {
        UserEntity userEntity = userRepository.findById(id).orElseThrow();
        UserDto UserDto = new UserDto();
        UserDto.setUsername(userEntity.getUsername());
        UserDto.setEmail(userEntity.getEmail());
        UserDto.setName(userEntity.getName());
        UserDto.setPassword(userEntity.getPassword());
        return UserDto;
    }

    @Transactional
    public void delete(int id) {
        if (!this.userRepository.existsById(id)) {
            throw new UserNotFoundException("User with id " + id + " not found");
        }
        this.userRepository.deleteById(id);
    }

}

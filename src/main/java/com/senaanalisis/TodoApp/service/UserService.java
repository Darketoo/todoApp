package com.senaanalisis.TodoApp.service;

import com.senaanalisis.TodoApp.auth.dto.RegisterRequest;
import com.senaanalisis.TodoApp.exception.UserAlreadyExistsException;
import com.senaanalisis.TodoApp.exception.UserNotFoundException;
import com.senaanalisis.TodoApp.persistence.entity.UserEntity;
import com.senaanalisis.TodoApp.persistence.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserEntity getUser(int id) {
        return this.userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found"));
    }

    @Transactional
    public UserEntity update(int id, RegisterRequest user) {
        UserEntity userExisting = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        userExisting.setUsername(user.getUsername());
        userExisting.setPassword(user.getPassword());
        userExisting.setName(user.getName());
        userExisting.setEmail(user.getEmail());

        return this.userRepository.save(userExisting);
    }

    @Transactional
    public void delete(int id) {
        if (!this.userRepository.existsById(id)) {
            throw new UserNotFoundException("User with id " + id + " not found");
        }
        this.userRepository.deleteById(id);
    }

}

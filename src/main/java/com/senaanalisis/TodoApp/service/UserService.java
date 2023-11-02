package com.senaanalisis.TodoApp.service;

import com.senaanalisis.TodoApp.exception.UserAlreadyExistsException;
import com.senaanalisis.TodoApp.exception.UserNotFoundException;
import com.senaanalisis.TodoApp.persistence.entity.UserEntity;
import com.senaanalisis.TodoApp.persistence.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserEntity getUser(int id) {
        return this.userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found"));
    }

    @Transactional
    public UserEntity create(UserEntity user) {
        if (user.getId() != null) {
            throw new UserAlreadyExistsException("User with ID " + user.getId() + " already exists");
        }

        Optional<UserEntity> existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            throw new UserAlreadyExistsException("User with email " + user.getEmail() + " already exists");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    @Transactional
    public UserEntity update(UserEntity user) {
        if (user.getId() == null || !this.userRepository.existsById(user.getId())) {
            throw new UserNotFoundException("User not found");
        }
        return this.userRepository.save(user);
    }

    @Transactional
    public void delete(int id) {
        if (!this.userRepository.existsById(id)) {
            throw new UserNotFoundException("User with id " + id + " not found");
        }
        this.userRepository.deleteById(id);
    }

}

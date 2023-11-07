package com.senaanalisis.TodoApp.web.controller;

import com.senaanalisis.TodoApp.auth.dto.AuthResponse;
import com.senaanalisis.TodoApp.auth.dto.RegisterRequest;
import com.senaanalisis.TodoApp.exception.UserNotFoundException;
import com.senaanalisis.TodoApp.persistence.entity.Dto.UserDto;
import com.senaanalisis.TodoApp.persistence.entity.UserEntity;
import com.senaanalisis.TodoApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable int id) {
        try {
            UserDto user = userService.getUser(id);
            return ResponseEntity.ok(user);
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthResponse> updateUser(@PathVariable int id, @RequestBody RegisterRequest user) {
        return ResponseEntity.ok(userService.update(id, user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        try {
            userService.delete(id);
            return ResponseEntity.ok().build();
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

}

package com.senaanalisis.TodoApp.web.controller;

import com.senaanalisis.TodoApp.auth.dto.AuthResponse;
import com.senaanalisis.TodoApp.auth.dto.RegisterRequest;
import com.senaanalisis.TodoApp.exception.UserNotFoundException;
import com.senaanalisis.TodoApp.persistence.entity.Dto.UserDto;
import com.senaanalisis.TodoApp.persistence.entity.UserEntity;
import com.senaanalisis.TodoApp.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "get user by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "404", description = "user not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable int id) {
        try {
            UserDto user = userService.getUser(id);
            return ResponseEntity.ok(user);
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Delete user")
            @ApiResponse(responseCode = "200", description = "ok")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id) {
        try {
            userService.delete(id);
            return ResponseEntity.ok(Map.of("message", "Usuario eliminado con éxito"));
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

}

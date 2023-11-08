package com.senaanalisis.TodoApp.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    @Schema(description = "Username of user", example = "darketo")
    private String username;

    @Schema(description = "Password of user", example = "12345")
    private String password;

    @Schema(description = "Name of the user", example = "Agustin")
    private String name;

    @Column(nullable = true)
    @Schema(description = "Email of user", example = "agustin@gmail.com")
    private String email;
}

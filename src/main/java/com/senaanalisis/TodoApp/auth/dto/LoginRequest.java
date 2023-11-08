package com.senaanalisis.TodoApp.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

    @Schema(description = "Username of user", example = "darketo")
    private String username;

    @Schema(description = "Password of user", example = "12345")
    private String password;
}
package com.senaanalisis.TodoApp.persistence.entity.Dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    @Schema(description = "Username of user", example = "darketo")
    private String username;

    @Schema(description = "Email of user", example = "agustin@gmail.com")
    private String email;

    @Schema(description = "Name of the user", example = "Agustin")
    private String name;

    @Schema(description = "Password of user", example = "12345")
    private String password;
}

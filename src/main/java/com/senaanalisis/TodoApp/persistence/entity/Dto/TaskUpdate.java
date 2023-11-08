package com.senaanalisis.TodoApp.persistence.entity.Dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class TaskUpdate {
    @Schema(description = "Task title", example = "cook")
    private String title;

    @Schema(description = "Task description", example = "Wash the clothes")
    private String description;

    @Schema(description = "Task state", example = "true/false")
    private Boolean state;

    @Schema(description = "Task duration", example = "120")
    private Long duration;
}


package com.senaanalisis.TodoApp.persistence.entity.Dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskUpdate {
    @Schema(description = "Task title", example = "cook")
    private String title;

    @Schema(description = "Task description", example = "Wash the clothes")
    private String description;

    @Schema(description = "Task state", example = "true/false")
    private Boolean state;

    @Column(nullable = false, columnDefinition = "TIMESTAMP")
    @Schema(description = "Time to alert for the task", example = "2023-11-07T16:30:00")
    private LocalDateTime alertTime;
}


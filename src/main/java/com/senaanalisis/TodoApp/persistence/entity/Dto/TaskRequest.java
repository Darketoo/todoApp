package com.senaanalisis.TodoApp.persistence.entity.Dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskRequest {
    @Schema(description = "task title", example = "cook")
    private String title;

    @Schema(description = "Task description", example = "Wash the clothes")
    private String description;

    @Schema(description = "time to start the task", example = "2023/11/09")
    private LocalDateTime startTime;

    @Schema(description = "Task duration", example = "120")
    private Duration duration;

    @Schema(description = "Task state", example = "true/false")
    private Boolean state;

    @Schema(description = "task id", example = "10")
    private Integer userId;
}

package com.senaanalisis.TodoApp.persistence.entity.Dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
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

    @Column(columnDefinition = "TIMESTAMP")
    @Schema(description = "Time to alert for the task", example = "2023-11-07T16:30:00")
    private LocalDateTime alertTime;

    @Schema(description = "Task state", example = "true/false")
    private Boolean state;

    @Schema(description = "task id", example = "10")
    private Integer userId;
}

package com.senaanalisis.TodoApp.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.swing.*;
import java.time.Duration;
import java.time.LocalDateTime;

@Entity
@Table(name = "task")
@Getter
@Setter
@NoArgsConstructor
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "id_task")
    @Schema(description = "Unique ID of the task", example = "10")
    private Integer taskId;

    @Column(nullable = false)
    @Schema(description = "task title", example = "learn English")
    private String title;

    @Column(nullable = false)
    @Schema(description = "task description", example = "this task is important")
    private String description;

    @Column(columnDefinition = "DATETIME")
    @Schema(description = "Time to start task", example = "2023/11/07")
    private LocalDateTime startTime;

    @Column(columnDefinition = "DATETIME")
    @Schema(description = "Task Duration", example = "150")
    private Long duration;

    @Column(columnDefinition = "TINYINT", nullable = false)
    @Schema(description = "Task status", example = "false/true")
    private Boolean state;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;
}

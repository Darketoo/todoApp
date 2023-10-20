package com.senaanalisis.TodoApp.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

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
    private Integer taskId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(columnDefinition = "DATETIME")
    private LocalDateTime date;

    @Column(columnDefinition = "DATETIME")
    private LocalDateTime duration;

    @Column(columnDefinition = "TINYINT", nullable = false)
    private Boolean state;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;
}

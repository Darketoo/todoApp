package com.senaanalisis.TodoApp.persistence.entity.Dto;

import lombok.Data;

@Data
public class TaskUpdate {
    private String title;
    private String description;
    private Boolean state;
    private Long duration;
}


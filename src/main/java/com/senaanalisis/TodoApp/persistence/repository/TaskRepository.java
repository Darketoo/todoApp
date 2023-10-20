package com.senaanalisis.TodoApp.persistence.repository;

import com.senaanalisis.TodoApp.persistence.entity.TaskEntity;
import org.springframework.data.repository.ListCrudRepository;

public interface TaskRepository extends ListCrudRepository<TaskEntity, Integer> {
}

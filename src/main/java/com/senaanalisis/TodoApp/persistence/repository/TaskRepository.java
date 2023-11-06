package com.senaanalisis.TodoApp.persistence.repository;

import com.senaanalisis.TodoApp.persistence.entity.TaskEntity;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface TaskRepository extends ListCrudRepository<TaskEntity, Integer> {
    List<TaskEntity> findByUserId(Integer user_id);
}

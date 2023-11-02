package com.senaanalisis.TodoApp.service;

import com.senaanalisis.TodoApp.persistence.entity.TaskEntity;
import com.senaanalisis.TodoApp.persistence.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }


    public TaskEntity getTask(int id) {
        return this.taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
    }

    public TaskEntity create(TaskEntity task) {
        if(task.getTaskId() == null) {
            return this.taskRepository.save(task);
        }
        throw new RuntimeException("Task already exists");
    }

    public TaskEntity update(TaskEntity task) {
        if(task.getTaskId() != null) {
            return this.taskRepository.save(task);
        } else {
            throw new RuntimeException("Task not found");
        }
    }

    public void delete(int id) {
        if(!this.taskRepository.existsById(id)) {
            throw new RuntimeException("Task not found");
        }
        this.taskRepository.deleteById(id);
    }

    public List<TaskEntity> getAll() {
        return this.taskRepository.findAll();
    }

}

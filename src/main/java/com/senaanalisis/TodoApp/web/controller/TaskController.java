package com.senaanalisis.TodoApp.web.controller;

import com.senaanalisis.TodoApp.persistence.entity.TaskEntity;
import com.senaanalisis.TodoApp.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<List<TaskEntity>> getAll() {
        return new ResponseEntity<>(taskService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskEntity> getTask(@PathVariable int id) {
        return new ResponseEntity<>(taskService.getTask(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TaskEntity> add(@RequestBody TaskEntity TaskEntity) {
        TaskEntity TaskEntityAdded = this.taskService.create(TaskEntity);
        return ResponseEntity.ok(TaskEntityAdded);
    }

    @PutMapping
    public ResponseEntity<TaskEntity> update(@RequestBody TaskEntity TaskEntity) {
        TaskEntity TaskEntityUpdate = this.taskService.update(TaskEntity);
        return ResponseEntity.ok(TaskEntityUpdate);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        this.taskService.delete(id);
        return ResponseEntity.ok().build();
    }
}

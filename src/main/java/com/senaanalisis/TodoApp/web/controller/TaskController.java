package com.senaanalisis.TodoApp.web.controller;

import com.senaanalisis.TodoApp.persistence.entity.Dto.TaskRequest;
import com.senaanalisis.TodoApp.persistence.entity.Dto.TaskUpdate;
import com.senaanalisis.TodoApp.persistence.entity.TaskEntity;
import com.senaanalisis.TodoApp.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Operation(summary = "Get all task")
    @ApiResponse(responseCode = "200", description = "ok")
    @GetMapping
    public ResponseEntity<List<TaskEntity>> getAll() {
        return new ResponseEntity<>(taskService.getAll(), HttpStatus.OK);
    }

    @Operation(summary = "Get task by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "404", description = "task not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<TaskEntity> getTask(@PathVariable int id) {
        try {
            return new ResponseEntity<>(taskService.getTask(id), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Create task")
    @ApiResponse(responseCode = "200", description = "ok")
    @PostMapping
    public ResponseEntity<?> createTask(@RequestBody TaskRequest taskRequest) {
        try {
            taskService.createTask(taskRequest);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al procesar la solicitud: " + e.getMessage());
        }
    }

    @Operation(summary = "Update task")
    @ApiResponse(responseCode = "200", description = "ok")
    @PutMapping("/{taskId}")
    public ResponseEntity<TaskEntity> update(@PathVariable Integer taskId, @RequestBody TaskUpdate taskUpdate) {
        TaskEntity taskUpdated = taskService.update(taskId, taskUpdate);
        return ResponseEntity.ok(taskUpdated);
    }

    @Operation(summary = "Delete task")
    @ApiResponse(responseCode = "200", description = "ok")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        this.taskService.delete(id);
        return ResponseEntity.ok().build();
    }
}
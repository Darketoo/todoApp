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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<Map<String, Object>> getAll() {
        List<TaskEntity> tasks = taskService.getAll();
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("message", "Lista de tareas obtenida con éxito");
        responseBody.put("tasks", tasks);
        return ResponseEntity.ok(responseBody);
    }

    @Operation(summary = "Get task by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "404", description = "task not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> getTask(@PathVariable int id) {
        try {
            TaskEntity task = taskService.getTask(id);
            return ResponseEntity.ok(Map.of("message", "Tarea obtenida con éxito", "task", task));
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
            return ResponseEntity.ok(Map.of("message", "Tarea creada con éxito"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al procesar la solicitud: " + e.getMessage());
        }
    }

    @Operation(summary = "Update task")
    @ApiResponse(responseCode = "200", description = "ok")
    @PutMapping("/{taskId}")
    public ResponseEntity<?> update(@PathVariable Integer taskId, @RequestBody TaskUpdate taskUpdate) {
        TaskEntity taskUpdated = taskService.update(taskId, taskUpdate);
        return ResponseEntity.ok(Map.of("message", "Tarea actualizada con éxito", "task", taskUpdated));
    }

    @Operation(summary = "Delete task")
    @ApiResponse(responseCode = "200", description = "ok")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        this.taskService.delete(id);
        return ResponseEntity.ok(Map.of("message", "Tarea eliminada con éxito"));
    }
}
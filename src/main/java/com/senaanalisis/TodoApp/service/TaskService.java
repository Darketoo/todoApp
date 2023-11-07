package com.senaanalisis.TodoApp.service;

import com.senaanalisis.TodoApp.persistence.entity.Dto.TaskRequest;
import com.senaanalisis.TodoApp.persistence.entity.Dto.TaskUpdate;
import com.senaanalisis.TodoApp.persistence.entity.TaskEntity;
import com.senaanalisis.TodoApp.persistence.entity.UserEntity;
import com.senaanalisis.TodoApp.persistence.repository.TaskRepository;
import com.senaanalisis.TodoApp.persistence.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public List<TaskEntity> getAll() {
        return this.taskRepository.findAll();
    }

    public TaskEntity getTask(int id) {
        return this.taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
    }


     public void createTask(TaskRequest request) throws Exception {

      UserEntity userEntity = userRepository.findById(request.getUserId())
              .orElseThrow(() -> new Exception("Usuario no encontrado"));

      TaskEntity taskEntity = new TaskEntity();
      taskEntity.setTitle(request.getTitle());
      taskEntity.setDescription(request.getDescription());
      taskEntity.setStartTime(request.getStartTime());

      taskEntity.setDuration(request.getDuration().toMinutes());

      taskEntity.setState(request.getState());
      taskEntity.setUser(userEntity);

      taskRepository.save(taskEntity);
  }


    @Transactional
    public TaskEntity update(Integer taskId, TaskUpdate updateDTO) {
        TaskEntity task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        if (updateDTO.getTitle() != null) {
            task.setTitle(updateDTO.getTitle());
        }
        if (updateDTO.getDescription() != null) {
            task.setDescription(updateDTO.getDescription());
        }
        if (updateDTO.getState() != null) {
            task.setState(updateDTO.getState());
        }
        if (updateDTO.getDuration() != null) {
            task.setDuration(updateDTO.getDuration());
        }

        return taskRepository.save(task);
    }

    public void delete(int id) {
        if(!this.taskRepository.existsById(id)) {
            throw new RuntimeException("Task not found");
        }
        this.taskRepository.deleteById(id);
    }

}
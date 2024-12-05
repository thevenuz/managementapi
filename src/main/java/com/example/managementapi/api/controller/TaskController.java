package com.example.managementapi.api.controller;

import com.example.managementapi.dto.ProjectDTO;
import com.example.managementapi.dto.TaskDTO;
import com.example.managementapi.entity.ProjectEntity;
import com.example.managementapi.entity.EmployeeEntity;
import com.example.managementapi.entity.TaskEntity;
import com.example.managementapi.service.ProjectService;
import com.example.managementapi.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/prm")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/{projectId}/tasks/new")
    public ResponseEntity<TaskEntity> createTask(
            @PathVariable Integer projectId,
            @RequestBody TaskDTO task) {

        TaskEntity createdTask = taskService.createTask(projectId, task);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
    }

    @GetMapping("/tasks")
    public ResponseEntity<List<TaskEntity>> getAllTasks() {
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    @GetMapping("tasks/{taskId}")
    public ResponseEntity<TaskEntity> getTaskById(@PathVariable Integer taskId) {
        return ResponseEntity.ok(taskService.getTaskById(taskId));
    }

    @PutMapping("/projects/{projectId}/tasks/{taskId}")
    public ResponseEntity<TaskEntity> updateTask(
            @PathVariable Integer projectId,
            @PathVariable Integer taskId,
            @RequestBody TaskDTO taskDetails) {

        TaskEntity updatedTask = taskService.updateTask(taskId, taskDetails);
        return ResponseEntity.ok(updatedTask);
    }

    @DeleteMapping("/tasks/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Integer taskId) {
        taskService.deleteTask(taskId);
        return ResponseEntity.noContent().build();
    }
}

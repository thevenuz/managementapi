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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/prm")
@CrossOrigin(origins = "*")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/{projectId}/tasks/new")
    public ResponseEntity<?> createTask(
            @PathVariable Integer projectId,
            @RequestBody TaskDTO task) {

        Map<String, Object> createdTask = taskService.createTask(projectId, task);

        Map<String, Object> response = new HashMap<>();
        response.put("isSuccess", true);
        response.put("data", createdTask);
        return ResponseEntity.ok(response);

        //return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
    }

    @GetMapping("/tasks")
    public ResponseEntity<List<TaskEntity>> getAllTasks() {
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    @GetMapping("tasks/{taskId}")
    public ResponseEntity<?> getTaskById(@PathVariable Integer taskId) {
        Map<String, Object> task = taskService.getTaskByIdJson(taskId);
        Map<String, Object> response = new HashMap<>();

        response.put("isSuccess", true);
        response.put("data", task);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/projects/{projectId}/tasks/{taskId}")
    public ResponseEntity<?> updateTask(
            @PathVariable Integer projectId,
            @PathVariable Integer taskId,
            @RequestBody TaskDTO taskDetails) {

        Map<String, Object> updatedTask = taskService.updateTask(taskId, taskDetails);

        Map<String, Object> response = new HashMap<>();

        response.put("isSuccess", true);
        response.put("data", updatedTask);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/tasks/{taskId}")
    public ResponseEntity<?> deleteTask(@PathVariable Integer taskId) {
        try{
        taskService.deleteTask(taskId);
            Map<String, Object> response = new HashMap<>();
            response.put("isSuccess", true);
            response.put("data", "Project deleted successfully.");
            return ResponseEntity.ok(response);
        }
        catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("isSuccess", false);
            errorResponse.put("message", "An error occurred while deleting the task.");
            return ResponseEntity.status(500).body(errorResponse);
        }
    }
}

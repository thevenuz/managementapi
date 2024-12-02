package com.example.managementapi.api.controller;

import com.example.managementapi.entity.TaskEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import  com.example.managementapi.service.TaskService;

//@RestController
//@RequestMapping("/prm/tasks")
//@CrossOrigin(origins = "*")
//public class TaskController {
//
//    @Autowired
//    private TaskService taskService;
//
//    @GetMapping("/{id}")
//    public ResponseEntity<TaskEntity> getTask(@PathVariable int id) {
//        return ResponseEntity.ok(taskService.getTaskById(id));
//    }
//
//    @PostMapping("/{projectId}/new")
//    public ResponseEntity<Map<String, Object>> createTask(
//            @PathVariable String projectId,
//            @RequestBody Map<String, Object> taskData) {
//        try {
//            //taskData.put("taskId", "tsk_" + String.format("%03d", taskService.getLatestTaskId() + 1));
//            taskData.put("taskStatus", "Active");
//            //taskData.put("startDate", taskService.parseDate((String) taskData.get("start_date")));
//            //taskData.put("expectedFinishDate", taskService.parseDate((String) taskData.get("expected_finish_date")));
//
//            //TaskEntity newTask = taskService.(projectId, taskData);
//            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("task", newTask));
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", e.getMessage()));
//        }
//    }
//
//    @PutMapping("/{projectId}/tasks/{taskId}")
//    public ResponseEntity<Map<String, Object>> updateTask(
//            @PathVariable String projectId,
//            @PathVariable String taskId,
//            @RequestBody Map<String, Object> taskData) {
//        TaskEntity updatedTask = taskService.updateTask(projectId, taskId, taskData);
//        return ResponseEntity.ok(Map.of("task", updatedTask));
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Map<String, String>> deleteTask(@PathVariable String id) {
//        taskService.deleteTask(id);
//        return ResponseEntity.ok(Map.of("message", "Task deleted successfully."));
//    }
//}


//package com.example.managementapi.api.controller;
//
//import com.example.managementapi.entity.TaskEntity;
//import com.example.managementapi.service.TaskService;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/tasks")
//public class TaskController_ {
//
//    private final TaskService taskService;
//
//    public TaskController_(TaskService taskService) {
//        this.taskService = taskService;
//    }
//
//    @GetMapping
//    public List<TaskEntity> getAllTasks() {
//        return taskService.getAllTasks();
//    }
//
//    @GetMapping("/{id}")
//    public TaskEntity getTaskById(@PathVariable int id) {
//        return taskService.getTaskById(id);
//    }
//
//    @PostMapping
//    public TaskEntity addTask(@RequestBody TaskEntity task) {
//        return taskService.addTask(task);
//    }
//
//    @PutMapping("/{id}")
//    public TaskEntity updateTask(@PathVariable int id, @RequestBody TaskEntity task) {
//        return taskService.updateTask(id, task);
//    }
//
//    @DeleteMapping("/{id}")
//    public void deleteTask(@PathVariable int id) {
//        taskService.deleteTask(id);
//    }
//}
//

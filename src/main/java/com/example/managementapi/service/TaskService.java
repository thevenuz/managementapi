//package com.example.managementapi.service;
//
//import com.example.managementapi.entity.TaskEntity;
//import com.example.managementapi.repository.TaskRepository;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class TaskService {
//
//    private final TaskRepository taskRepository;
//
//    public TaskService(TaskRepository taskRepository) {
//        this.taskRepository = taskRepository;
//    }
//
//    public List<TaskEntity> getAllTasks() {
//        return taskRepository.findAll();
//    }
//
//    public TaskEntity getTaskById(int taskId) {
//        return taskRepository.findById(taskId)
//                .orElseThrow(() -> new IllegalArgumentException("TaskEntity with ID " + taskId + " not found."));
//    }
//
//    public TaskEntity addTask(TaskEntity task) {
//        return taskRepository.save(task);
//    }
//
//    public TaskEntity updateTask(int taskId, TaskEntity updatedTask) {
//        TaskEntity task = taskRepository.findById(taskId)
//                .orElseThrow(() -> new IllegalArgumentException("TaskEntity with ID " + taskId + " not found."));
//
//        task.setTaskName(updatedTask.getTaskName());
//        task.setTaskDescription(updatedTask.getTaskDescription());
//        task.setTaskStatus(updatedTask.getTaskStatus());
//        task.setDueDate(updatedTask.getDueDate());
//        task.setAssignedTeam(updatedTask.getAssignedTeam());
//        task.setProject(updatedTask.getProject());
//
//        return taskRepository.save(task);
//    }
//
//    public void deleteTask(int taskId) {
//        if (taskRepository.existsById(taskId)) {
//            taskRepository.deleteById(taskId);
//        } else {
//            throw new IllegalArgumentException("TaskEntity with ID " + taskId + " not found.");
//        }
//    }
//}

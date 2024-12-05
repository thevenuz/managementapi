package com.example.managementapi.service;

import com.example.managementapi.entity.*;
import com.example.managementapi.repository.EmployeeRepository;
import com.example.managementapi.repository.ProjectRepository;
import com.example.managementapi.dto.TaskDTO;
import com.example.managementapi.repository.TaskRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private EmployeeToTaskService employeeToTaskService;


    public TaskEntity createTask(Integer projectId, TaskDTO taskDTO) {
        // Map DTO fields to TaskEntity
        TaskEntity task = new TaskEntity();
        task.setTaskName(taskDTO.getTask_title());
        task.setTaskDescription(taskDTO.getTask_description());
        task.setAssignedTeam(TaskEntity.AssignedTeam.valueOf(taskDTO.getAssigned_team()));
        task.setStartDate(taskDTO.getStart_date());
        task.setExpectedFinishDate(taskDTO.getExpected_finish_date());

        System.out.println(task.getAssignedTeam());

        task.setTaskStatus(TaskEntity.TaskStatus.in_progress);
        // Fetch project using projectId from DTO
        Optional<ProjectEntity> projectOptional = projectService.getProjectById(projectId);
        if (projectOptional.isEmpty()) {
            throw new IllegalArgumentException("Project with ID " + projectId + " not found");
        }
        ProjectEntity project = projectOptional.get();

        task.setProject(project);

        // Save task to database
        TaskEntity savedTask = taskRepository.save(task);

        // Handle employees and map them to the task
        if (taskDTO.getEmployees() != null) {
            for (String username : taskDTO.getEmployees()) {
                EmployeeEntity employee = employeeService.getEmployeeDetails(username);
                EmployeeToTaskEntity employeeToTask = new EmployeeToTaskEntity();
                employeeToTask.setTask(savedTask);
                employeeToTask.setEmployee(employee);
                employeeToTaskService.createEmployeeToTask(employeeToTask); // Save mapping
            }
        }

        // Handle ticket assignments if provided
//        if (taskDTO.getTickets() != null) {
//            for (String ticketId : taskDTO.getTickets()) {
//                TicketEntity ticket = ticketService.getTicketById(Integer.parseInt(ticketId));
//                ticket.setTask(savedTask); // Associate ticket with task
//                ticketService.updateTicket(ticket); // Save updated ticket
//            }
//        }

        return savedTask;
    }


    public List<TaskEntity> getAllTasks() {
        return taskRepository.findAll();
    }

    public TaskEntity getTaskById(Integer taskId) {
        return taskRepository.findById(taskId).orElseThrow(() -> new EntityNotFoundException("Task not found"));
    }

    public TaskEntity updateTask(Integer taskId, TaskDTO taskDTO) {
        // Fetch the existing task
        TaskEntity task = taskRepository.findById(taskId)
                .orElseThrow(() -> new IllegalArgumentException("Task with ID " + taskId + " not found."));

        // Update basic task fields
        task.setTaskName(taskDTO.getTask_title());
        task.setTaskDescription(taskDTO.getTask_description());
        task.setAssignedTeam(TaskEntity.AssignedTeam.valueOf(taskDTO.getAssigned_team()));
        task.setTaskStatus(TaskEntity.TaskStatus.valueOf(taskDTO.getTask_status().toLowerCase()));
        task.setStartDate(taskDTO.getStart_date());
        task.setExpectedFinishDate(taskDTO.getExpected_finish_date());

        // Update the project (if provided in the DTO)
//        if (taskDTO.getProjectId() != null) {
//            ProjectEntity project = projectService.getProjectById(taskDTO.getProjectId())
//                    .orElseThrow(() -> new IllegalArgumentException("Project with ID " + taskDTO.getProjectId() + " not found."));
//            task.setProject(project);
//        }

        // Save the updated task to ensure consistency
        TaskEntity updatedTask = taskRepository.save(task);

        // Update employees assigned to the task
        if (taskDTO.getEmployees() != null) {
            // Remove existing employee-task mappings for this task
            employeeToTaskService.deleteByTask(task);

            // Add new employee-task mappings
            for (String username : taskDTO.getEmployees()) {
                EmployeeEntity employee = employeeService.getEmployeeDetails(username);
                EmployeeToTaskEntity employeeToTask = new EmployeeToTaskEntity();
                employeeToTask.setTask(updatedTask);
                employeeToTask.setEmployee(employee);
                employeeToTaskService.createEmployeeToTask(employeeToTask);
            }
        }



        return updatedTask;
    }

    public void deleteTask(Integer taskId) {
        TaskEntity task = getTaskById(taskId);
        taskRepository.delete(task);
    }
}


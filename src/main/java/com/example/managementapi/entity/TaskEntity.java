package com.example.managementapi.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "task")
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private Integer taskId;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private ProjectEntity project;

    @Column(name = "task_name", nullable = false)
    private String taskName;

    @Column(name = "task_description")
    private String taskDescription;

    @Column(name = "task_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private TaskStatus taskStatus;

    public enum TaskStatus {
        pending, in_progress, completed
    }

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "expected_finish_date")
    private LocalDate expectedFinishDate;

    @Column(name = "assigned_team")
    @Enumerated(EnumType.STRING)
    private AssignedTeam assignedTeam;

    public enum AssignedTeam {
        dev, qa, devops, support
    }

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TicketEntity> tickets = new ArrayList<>();

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EmployeeToTaskEntity> employeeToTasks = new ArrayList<>();

    // Getters and Setters
    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public ProjectEntity getProject() {
        return project;
    }

    public void setProject(ProjectEntity project) {
        this.project = project;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getExpectedFinishDate() {
        return expectedFinishDate;
    }

    public void setExpectedFinishDate(LocalDate expectedFinishDate) {
        this.expectedFinishDate = expectedFinishDate;
    }

    public AssignedTeam getAssignedTeam() {
        return assignedTeam;
    }

    public void setAssignedTeam(AssignedTeam assignedTeam) {
        this.assignedTeam = assignedTeam;
    }

    public List<TicketEntity> getTickets() {
        return tickets;
    }

    public void setTickets(List<TicketEntity> tickets) {
        this.tickets = tickets;
    }

    public List<EmployeeToTaskEntity> getEmployeeToTasks() {
        return employeeToTasks;
    }

    public void setEmployeeToTasks(List<EmployeeToTaskEntity> employeeToTasks) {
        this.employeeToTasks = employeeToTasks;
    }
}

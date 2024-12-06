package com.example.managementapi.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "ticket")
public class TicketEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_id")
    private Integer ticketId;

    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false)
    private TaskEntity task;

    @Column(name = "ticket_title", nullable = false, length = 100)
    private String ticketTitle;

    @Column(name = "ticket_description")
    private String ticketDescription;

    @Column(name = "ticket_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private TicketType ticketType;

    public enum TicketType {
        bug, change_request, feature_request, support
    }

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status = Status.open;

    public enum Status {
        open, in_progress, closed
    }

    @Column(name = "created_at", updatable = false)
    private LocalDate createdAt;

    @Column(name = "updated_at")
    private LocalDate updatedAt;

    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    private EmployeeEntity createdBy;

    @ManyToOne
    @JoinColumn(name = "assigned_user")
    private EmployeeEntity assignedUser;

    @Column(name = "closed_date")
    private LocalDate closedDate;

    // Getters and Setters
    public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    public TaskEntity getTask() {
        return task;
    }

    public void setTask(TaskEntity task) {
        this.task = task;
    }

    public String getTicketTitle() {
        return ticketTitle;
    }

    public void setTicketTitle(String ticketTitle) {
        this.ticketTitle = ticketTitle;
    }

    public String getTicketDescription() {
        return ticketDescription;
    }

    public void setTicketDescription(String ticketDescription) {
        this.ticketDescription = ticketDescription;
    }

    public TicketType getTicketType() {
        return ticketType;
    }

    public void setTicketType(TicketType ticketType) {
        this.ticketType = ticketType;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    public EmployeeEntity getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(EmployeeEntity createdBy) {
        this.createdBy = createdBy;
    }

    public EmployeeEntity getAssignedUser() {
        return assignedUser;
    }

    public void setAssignedUser(EmployeeEntity assignedUser) {
        this.assignedUser = assignedUser;
    }

    public LocalDate getClosedDate() {
        return closedDate;
    }

    public void setClosedDate(LocalDate closedDate) {
        this.closedDate = closedDate;
    }
}

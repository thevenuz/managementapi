package com.example.managementapi.dto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TaskDTO {

    private String task_title;
    private String task_description;
    private String assigned_team;
    private LocalDate start_date;
    private LocalDate expected_finish_date;
    private String task_status;
    private List<String> employees;
    private List<Integer> tickets;



    // Getters and Setters
    public String getTask_title() {
        return task_title;
    }

    public void setTask_title(String task_title) {
        this.task_title = task_title;
    }

    public String getTask_description() {
        return task_description;
    }

    public void setTask_description(String task_description) {
        this.task_description = task_description;
    }

    public String getAssigned_team() {
        return assigned_team;
    }

    public void setAssigned_team(String assigned_team) {
        this.assigned_team = assigned_team;
    }

    public LocalDate getStart_date() {
        return start_date;
    }

    public void setStart_date(LocalDate start_date) {
        this.start_date = start_date;
    }

    public LocalDate getExpected_finish_date() {
        return expected_finish_date;
    }

    public void setExpected_finish_date(LocalDate expected_finish_date) {
        this.expected_finish_date = expected_finish_date;
    }

    public String getTask_status() {
        return task_status;
    }

    public void setTask_status(String task_status) {
        this.task_status = task_status;
    }

    public List<String> getEmployees() {
        return employees;
    }

    public void setEmployees(List<String> employees) {
        this.employees = employees;
    }

    public List<Integer> getTickets() {
        return tickets;
    }

    public void setTickets(List<Integer> tickets) {
        this.tickets = tickets;
    }
}



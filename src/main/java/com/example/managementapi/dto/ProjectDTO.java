package com.example.managementapi.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class ProjectDTO {
    private String project_id;
    private String project_title;
    private String project_description;
    private LocalDate start_date;
    private LocalDate expected_finish_date;
    private BigDecimal budget;
    private String created_by;
    private String manager;
    private List<String> employees;

    // Getters and Setters
    public String getProject_id() {
        return project_id;
    }

    public void setProject_id(String project_id) {
        this.project_id = project_id;
    }

    public String getProject_title() {
        return project_title;
    }

    public void setProject_title(String project_title) {
        this.project_title = project_title;
    }

    public String getProject_description() {
        return project_description;
    }

    public void setProject_description(String project_description) {
        this.project_description = project_description;
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

    public BigDecimal getBudget() {
        return budget;
    }

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public List<String> getEmployees() {
        return employees;
    }

    public void setEmployees(List<String> employees) {
        this.employees = employees;
    }
}

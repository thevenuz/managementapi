package com.example.managementapi.dto;

import java.time.LocalDate;
import java.util.List;

public class TicketDTO {

    private String ticket_title;
    private String ticket_description;
    private String ticket_type;
    private String created_by;
    private String assigned_user;
    private String ticket_status;
    private LocalDate created_date;
    private LocalDate last_updated_date;
    private LocalDate closed_date;
    private List<String> comments;
    private List<String> employees;

    // Getters and Setters
    public String getTicket_title() {
        return ticket_title;
    }

    public void setTicket_title(String ticket_title) {
        this.ticket_title = ticket_title;
    }

    public String getTicket_description() {
        return ticket_description;
    }

    public void setTicket_description(String ticket_description) {
        this.ticket_description = ticket_description;
    }

    public String getTicket_type() {
        return ticket_type;
    }

    public void setTicket_type(String ticket_type) {
        this.ticket_type = ticket_type;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public String getAssigned_user() {
        return assigned_user;
    }

    public void setAssigned_user(String assigned_user) {
        this.assigned_user = assigned_user;
    }

    public String getTicket_status() {
        return ticket_status;
    }

    public void setTicket_status(String ticket_status) {
        this.ticket_status = ticket_status;
    }

    public LocalDate getCreated_date() {
        return created_date;
    }

    public void setCreated_date(LocalDate created_date) {
        this.created_date = created_date;
    }

    public LocalDate getLast_updated_date() {
        return last_updated_date;
    }

    public void setLast_updated_date(LocalDate last_updated_date) {
        this.last_updated_date = last_updated_date;
    }

    public LocalDate getClosed_date() {
        return closed_date;
    }

    public void setClosed_date(LocalDate closed_date) {
        this.closed_date = closed_date;
    }

    public List<String> getComments() {
        return comments;
    }

    public void setComments(List<String> comments) {
        this.comments = comments;
    }

    public List<String> getEmployees() {
        return employees;
    }

    public void setEmployees(List<String> employees) {
        this.employees = employees;
    }
}

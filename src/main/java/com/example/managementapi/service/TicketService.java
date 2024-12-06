package com.example.managementapi.service;

import com.example.managementapi.dto.TicketDTO;
import com.example.managementapi.entity.*;
import com.example.managementapi.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final TaskService taskService;
    private final EmployeeService employeeService;

    public TicketService(TicketRepository ticketRepository, TaskService taskService, EmployeeService employeeService) {
        this.ticketRepository = ticketRepository;
        this.taskService = taskService;
        this.employeeService = employeeService;
    }

    public TicketEntity createTicket(Integer taskId, TicketDTO ticketDTO) {
        // Create a new Ticket entity object
        TicketEntity ticket = new TicketEntity();

        // Get Task by ID, handle if Task is not found
        TaskEntity task = taskService.getTaskById(taskId);
        if (task == null) {
            throw new IllegalArgumentException("Task not found for ID: " + taskId);
        }

        // Get Employee who created the ticket
        System.out.println(ticketDTO);
        EmployeeEntity createdBy = employeeService.getEmployeeDetails(ticketDTO.getCreated_by());
        if (createdBy == null) {
            throw new IllegalArgumentException("Employee not found for created_by: " + ticketDTO.getCreated_by());
        }

        // Get Employee assigned to the ticket (if provided)
        EmployeeEntity assignedUser = null;
        if (ticketDTO.getAssigned_user() != null) {
            assignedUser = employeeService.getEmployeeDetails(ticketDTO.getAssigned_user());
            if (assignedUser == null) {
                throw new IllegalArgumentException("Employee not found for assigned_user: " + ticketDTO.getAssigned_user());
            }
        }

        // Set ticket details from DTO
        ticket.setTask(task);
        ticket.setTicketTitle(ticketDTO.getTicket_title());
        ticket.setTicketDescription(ticketDTO.getTicket_description());
        ticket.setTicketType(TicketEntity.TicketType.valueOf(ticketDTO.getTicket_type().toLowerCase())); // Convert to Enum
        ticket.setCreatedBy(createdBy);
        ticket.setAssignedUser(assignedUser);
        ticket.setStatus(TicketEntity.Status.valueOf(ticketDTO.getTicket_status().toLowerCase())); // Convert to Enum
        ticket.setCreatedAt(ticketDTO.getCreated_date()); // Convert from String to LocalDate
        ticket.setUpdatedAt(LocalDate.now()); // Set current date as updated date

        // Optional: If last updated date is provided, set it
        if (ticketDTO.getLast_updated_date() != null) {
            ticket.setUpdatedAt(ticketDTO.getLast_updated_date());
        }

        // Optional: If closed date is provided, set it
        if (ticketDTO.getClosed_date() != null) {
            ticket.setClosedDate(ticketDTO.getClosed_date());
        }

        // Save the new ticket to the database
        TicketEntity savedTicket = ticketRepository.save(ticket);

        // Optionally handle comments if provided (You would need a CommentService to associate comments to ticket)
        if (ticketDTO.getComments() != null && !ticketDTO.getComments().isEmpty()) {
            // For each comment, you would need to create a Comment entity and associate it with this ticket.
            // CommentEntity comment = new CommentEntity();
            // Handle comment saving logic here
        }

        // Optionally handle employees if provided (Associate employees to this ticket)
        if (ticketDTO.getEmployees() != null && !ticketDTO.getEmployees().isEmpty()) {
            // You would need a mapping table or logic to associate employees with the ticket.
            // EmployeeToTicketEntity employeeToTicket = new EmployeeToTicketEntity();
            // Save each employee-to-ticket mapping here
        }

        // Return the saved ticket
        return savedTicket;
    }

    public Optional<TicketEntity> getTicketById(Integer ticketId) {
        return ticketRepository.findById(ticketId);
    }

    public TicketEntity updateTicket(Integer ticketId, TicketDTO ticketDTO) {
        TicketEntity ticket = getTicketById(ticketId)
                .orElseThrow(() -> new IllegalArgumentException("Ticket not found"));

        ticket.setTicketTitle(ticketDTO.getTicket_title());
        ticket.setTicketDescription(ticketDTO.getTicket_description());
        ticket.setTicketType(TicketEntity.TicketType.valueOf(ticketDTO.getTicket_type().toLowerCase()));
        //ticket.setStatus(TicketEntity.Status.valueOf(ticketDTO.getStatus().toLowerCase()));
        ticket.setClosedDate(ticketDTO.getClosed_date());

        return ticketRepository.save(ticket);
    }

    public void deleteTicket(Integer ticketId) {
        TicketEntity ticket = getTicketById(ticketId)
                .orElseThrow(() -> new IllegalArgumentException("Ticket not found"));
        ticketRepository.delete(ticket);
    }
}

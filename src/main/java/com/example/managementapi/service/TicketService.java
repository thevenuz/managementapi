package com.example.managementapi.service;

import com.example.managementapi.dto.TicketDTO;
import com.example.managementapi.entity.*;
import com.example.managementapi.repository.CommentRepository;
import com.example.managementapi.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.managementapi.service.CommentService;

import java.time.LocalDate;
import java.util.*;

@Service
public class TicketService {


    private final TicketRepository ticketRepository;
    private final TaskService taskService;
    private final EmployeeService employeeService;
//    @Autowired
//    private final CommentService commentService;

    @Autowired
    private CommentRepository commentRepository;
    private CommentService commentService;

    public TicketService(TicketRepository ticketRepository, TaskService taskService, EmployeeService employeeService) {
        this.ticketRepository = ticketRepository;
        this.taskService = taskService;
        this.employeeService = employeeService;
//        this.commentService = commentService;
    }

    public Map<String, Object> createTicket(Integer taskId, TicketDTO ticketDTO) {
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
        return formatTicket(savedTicket);
    }

    public List<CommentEntity> getCommentsByTicket(Integer ticketId) {
        Optional<TicketEntity> ticketOptional = getTicketById(ticketId);

        if (ticketOptional.isEmpty()) {
            throw new IllegalArgumentException("Ticket with ID " + ticketId + " not found");
        }

        TicketEntity ticket = ticketOptional.get();
        if (ticket == null) {
            throw new IllegalArgumentException("Ticket not found for ID: " + ticketId);
        }
        return commentRepository.findByTicket(ticket);
    }

    public Map<String, Object> formatTicket(TicketEntity ticket) {
        // Format the assigned user
        String assignedUser = ticket.getAssignedUser() != null ? ticket.getAssignedUser().getUsername() : null;

        // Fetch and format comments associated with the ticket
        List<CommentEntity> comments = getCommentsByTicket(ticket.getTicketId());
        List<Map<String, Object>> commentList = new ArrayList<>();
        if (comments != null) {
            for (CommentEntity comment : comments) {
                Map<String, Object> commentMap = new HashMap<>();
                commentMap.put("comment_id", comment.getCommentId());
                commentMap.put("comment_description", comment.getCommentDescription());
                commentMap.put("created_by", comment.getCreatedBy().getUsername());
//                commentMap.put("commented_at", comment.getCreatedAt() != null ? comment.getCreatedAt().toString() : null);
                commentList.add(commentMap);
            }
        }

        // Prepare the ticket details
        Map<String, Object> ticketDetails = new HashMap<>();
        ticketDetails.put("ticket_id", ticket.getTicketId());
        ticketDetails.put("ticket_title", ticket.getTicketTitle());
        ticketDetails.put("ticket_description", ticket.getTicketDescription());
        ticketDetails.put("ticket_type", ticket.getTicketType().toString());
        ticketDetails.put("created_by", ticket.getCreatedBy().getUsername());
        ticketDetails.put("assigned_user", assignedUser);
        ticketDetails.put("ticket_status", ticket.getStatus().toString());
        ticketDetails.put("created_date", ticket.getCreatedAt() != null ? ticket.getCreatedAt().toString() : null);
        ticketDetails.put("last_updated_date", ticket.getUpdatedAt() != null ? ticket.getUpdatedAt().toString() : null);
        ticketDetails.put("closed_date", ticket.getClosedDate() != null ? ticket.getClosedDate().toString() : null);
        ticketDetails.put("comments", commentList);

        return ticketDetails;
    }


    public Map<String, Object> getTicketByIdJson(Integer ticketId) {
        Optional<TicketEntity> ticketOptional =  ticketRepository.findById(ticketId);
        if (ticketOptional.isEmpty()) {
            throw new IllegalArgumentException("Ticket with ID " + ticketId + " not found");
        }

        TicketEntity ticket = ticketOptional.get();

        return formatTicket(ticket);

    }

    public Optional<TicketEntity> getTicketById(Integer ticketId) {
        return ticketRepository.findById(ticketId);


    }

    public Map<String, Object> updateTicket(Integer taskId, Integer ticketId, TicketDTO ticketDTO) {
        // Find existing Ticket entity
        TicketEntity ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new IllegalArgumentException("Ticket not found for ID: " + ticketId));

        // Get Task by ID, handle if Task is not found
        TaskEntity task = taskService.getTaskById(taskId);
        if (task == null) {
            throw new IllegalArgumentException("Task not found for ID: " + taskId);
        }

        // Get Employee who created the ticket
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

        // Update ticket details from DTO
        ticket.setTask(task);
        ticket.setTicketTitle(ticketDTO.getTicket_title());
        ticket.setTicketDescription(ticketDTO.getTicket_description());
        ticket.setTicketType(TicketEntity.TicketType.valueOf(ticketDTO.getTicket_type().toLowerCase())); // Convert to Enum
        ticket.setCreatedBy(createdBy);
        ticket.setAssignedUser(assignedUser);
        ticket.setStatus(TicketEntity.Status.valueOf(ticketDTO.getTicket_status().toLowerCase())); // Convert to Enum

        // Update created date (if it's allowed to be updated)
        ticket.setCreatedAt(ticketDTO.getCreated_date() != null ? ticketDTO.getCreated_date() : ticket.getCreatedAt());

        // Update last updated date
        ticket.setUpdatedAt(LocalDate.now()); // Set current date as updated date

        // Update closed date if provided
        if (ticketDTO.getClosed_date() != null) {
            ticket.setClosedDate(ticketDTO.getClosed_date());
        }

        // Save the updated ticket
        TicketEntity updatedTicket = ticketRepository.save(ticket);

        // Handle comments (Optional)
//        if (ticketDTO.getComments() != null && !ticketDTO.getComments().isEmpty()) {
//            for (String commentText : ticketDTO.getComments()) {
//                // Assuming you have a CommentService to handle comment creation
//                CommentEntity comment = new CommentEntity();
//                comment.setTicket(updatedTicket);
//                comment.setText(commentText);
//                commentService.createComment(comment); // Save comment to DB (implement createComment method in CommentService)
//            }
//        }

        // Handle employees (Optional)
//        if (ticketDTO.getEmployees() != null && !ticketDTO.getEmployees().isEmpty()) {
//            for (String employeeUsername : ticketDTO.getEmployees()) {
//                EmployeeEntity employee = employeeService.getEmployeeDetails(employeeUsername);
//                if (employee != null) {
//                    // Associate employees with ticket using EmployeeToTicket mapping
//                    EmployeeToTicketEntity employeeToTicket = new EmployeeToTicketEntity();
//                    employeeToTicket.setTicket(updatedTicket);
//                    employeeToTicket.setEmployee(employee);
//                    employeeToTicketService.save(employeeToTicket); // Save mapping in EmployeeToTicketService
//                }
//            }
//        }

        return formatTicket(updatedTicket);
    }


//    public List<TicketEntity> getTicketsByTask(Integer taskId) {
//        // Fetch the task by ID
//        TaskEntity task = taskService.getTaskById(taskId);
//        if (task == null) {
//            throw new IllegalArgumentException("Task with ID " + taskId + " not found");
//        }
//
//        // Fetch tickets associated with the task
//        return ticketRepository.findByTask(task);
//
//    }

    @Transactional
    public void deleteTicket(Integer ticketId) {
        TicketEntity ticket = getTicketById(ticketId)
                .orElseThrow(() -> new IllegalArgumentException("Ticket not found"));
        commentService.deleteCommentsByTicket(ticket);
        ticketRepository.delete(ticket);
    }
}

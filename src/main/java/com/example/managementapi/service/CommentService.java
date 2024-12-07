package com.example.managementapi.service;

import com.example.managementapi.dto.CommentDTO;
import com.example.managementapi.entity.CommentEntity;
import com.example.managementapi.entity.EmployeeEntity;
import com.example.managementapi.entity.ProjectEntity;
import com.example.managementapi.entity.TicketEntity;
import com.example.managementapi.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private EmployeeService employeeService;

    // Create a new Comment
    public CommentEntity createComment(Integer ticketId, CommentDTO commentDTO) {
        Optional<TicketEntity> ticketOptional = ticketService.getTicketById(ticketId);

        if (ticketOptional.isEmpty()) {
            throw new IllegalArgumentException("Ticket with ID " + ticketId + " not found");
        }

        TicketEntity ticket = ticketOptional.get();
        if (ticket == null) {
            throw new IllegalArgumentException("Ticket not found for ID: " + ticketId);
        }

        // Get Employee who created the comment
        EmployeeEntity createdBy = employeeService.getEmployeeDetails(commentDTO.getCreatedBy());
        if (createdBy == null) {
            throw new IllegalArgumentException("Employee not found for created_by: " + commentDTO.getCreatedBy());
        }

        CommentEntity comment = new CommentEntity();
        comment.setTicket(ticket);
        comment.setCommentDescription(commentDTO.getCommentDescription()); // Mapping 'commentDescription' from DTO
        comment.setCreatedBy(createdBy);
        comment.setCreatedAt(LocalDateTime.now());

        return commentRepository.save(comment);
    }

    // Get all comments for a specific ticket
    public List<CommentEntity> getCommentsByTicket(Integer ticketId) {
        Optional<TicketEntity> ticketOptional = ticketService.getTicketById(ticketId);

        if (ticketOptional.isEmpty()) {
            throw new IllegalArgumentException("Ticket with ID " + ticketId + " not found");
        }

        TicketEntity ticket = ticketOptional.get();
        if (ticket == null) {
            throw new IllegalArgumentException("Ticket not found for ID: " + ticketId);
        }
        return commentRepository.findByTicket(ticket);
    }

    // Update an existing comment
    public CommentEntity updateComment(Integer commentId, CommentDTO commentDTO) {
        CommentEntity comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("Comment not found for ID: " + commentId));

//        TicketEntity ticket = ticketService.getTaskById(commentDTO.getTicketId());
//        if (ticket == null) {
//            throw new IllegalArgumentException("Ticket not found for ID: " + commentDTO.getTicketId());
//        }

        EmployeeEntity createdBy = employeeService.getEmployeeDetails(commentDTO.getCreatedBy());
        if (createdBy == null) {
            throw new IllegalArgumentException("Employee not found for created_by: " + commentDTO.getCreatedBy());
        }

        //comment.setTicket(ticket);
        comment.setCommentDescription(commentDTO.getCommentDescription()); // Mapping updated description
        comment.setCreatedBy(createdBy);
        comment.setCreatedAt(LocalDateTime.now()); // or keep it unchanged if needed

        return commentRepository.save(comment);
    }

    // Delete a comment
    public void deleteComment(Integer commentId) {
        CommentEntity comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("Comment not found for ID: " + commentId));

        commentRepository.delete(comment);
    }
}

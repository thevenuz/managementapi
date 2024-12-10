package com.example.managementapi.api.controller;


import com.example.managementapi.dto.CommentDTO;
import com.example.managementapi.entity.CommentEntity;
import com.example.managementapi.service.CommentService;
import com.example.managementapi.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/prm")
@CrossOrigin(origins = "*")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private TicketService ticketService;

    // Create a new comment
    @PostMapping("/tickets/{ticketId}/comments")
    public ResponseEntity<?> createComment(@PathVariable Integer ticketId, @RequestBody CommentDTO commentDTO) {
        try {
            System.out.println(commentDTO.getCreatedBy() + "re");
            Map<String, Object> comment = commentService.createComment(ticketId, commentDTO);
            Map<String, Object> response = new HashMap<>();
            response.put("isSuccess", true);
            response.put("data", comment);
            return ResponseEntity.ok(response);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Get all comments for a ticket
    @GetMapping("/ticket/{ticketId}")
    public ResponseEntity<List<CommentEntity>> getCommentsByTicket(@PathVariable Integer ticketId) {
        try {
            List<CommentEntity> comments = ticketService.getCommentsByTicket(ticketId);
            return ResponseEntity.ok(comments);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Update an existing comment
    @PutMapping("/tickets/{ticketId}/comments/{commentId}")
    public ResponseEntity<?> updateComment(@PathVariable Integer ticketId, @PathVariable Integer commentId, @RequestBody CommentDTO commentDTO) {
        try {
            Map<String, Object> updatedComment = commentService.updateComment(ticketId, commentId, commentDTO);
            Map<String, Object> response = new HashMap<>();
            response.put("isSuccess", true);
            response.put("data", updatedComment);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Delete a comment
    @DeleteMapping("/tickets/{ticketId}/comments/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable Integer ticketId, @PathVariable Integer commentId) {
        try {
            Map<String, Object> ticket = commentService.deleteComment(ticketId, commentId);
            Map<String, Object> response = new HashMap<>();
            response.put("isSuccess", true);
            response.put("data", ticket);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}

//package com.example.managementapi.api.controller;
//
//import com.example.managementapi.dto.CommentDTO;
//import com.example.managementapi.entity.CommentEntity;
//import com.example.managementapi.service.CommentService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/prm")
//public class CommentController_ {
//
//    @Autowired
//    private CommentService commentService;
//
//    // Create a new comment
//    @PostMapping("/tickets/{ticketId}/comments")
//    public ResponseEntity<CommentEntity> createComment(@PathVariable Integer ticketId, @RequestBody CommentDTO commentDTO) {
//        try {
//            CommentEntity comment = commentService.createComment(ticketId, commentDTO);
//            return ResponseEntity.status(HttpStatus.CREATED).body(comment);
//        } catch (IllegalArgumentException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//        }
//    }
//
//    // Get all comments for a ticket
//    @GetMapping("/ticket/{ticketId}")
//    public ResponseEntity<List<CommentEntity>> getCommentsByTicket(@PathVariable Integer ticketId) {
//        try {
//            List<CommentEntity> comments = commentService.getCommentsByTicket(ticketId);
//            return ResponseEntity.ok(comments);
//        } catch (IllegalArgumentException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//        }
//    }
//
//    // Update an existing comment
//    @PutMapping("/{commentId}")
//    public ResponseEntity<CommentEntity> updateComment(@PathVariable Integer commentId, @RequestBody CommentDTO commentDTO) {
//        try {
//            CommentEntity updatedComment = commentService.updateComment(commentId, commentDTO);
//            return ResponseEntity.ok(updatedComment);
//        } catch (IllegalArgumentException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//        }
//    }
//
//    // Delete a comment
//    @DeleteMapping("/{commentId}")
//    public ResponseEntity<Void> deleteComment(@PathVariable Integer commentId) {
//        try {
//            commentService.deleteComment(commentId);
//            return ResponseEntity.noContent().build();
//        } catch (IllegalArgumentException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
//    }
//}

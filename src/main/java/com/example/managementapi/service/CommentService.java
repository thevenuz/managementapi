//package com.example.managementapi.service;
//
//import com.example.managementapi.entity.CommentEntity;
//import com.example.managementapi.repository.CommentRepository;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//@Service
//public class CommentService {
//
//    private final CommentRepository commentRepository;
//
//    public CommentService(CommentRepository commentRepository) {
//        this.commentRepository = commentRepository;
//    }
//
//    public List<CommentEntity> getAllComments() {
//        return commentRepository.findAll();
//    }
//
//    public CommentEntity getCommentById(int commentId) {
//        return commentRepository.findById(commentId)
//                .orElseThrow(() -> new IllegalArgumentException("Comment with ID " + commentId + " not found."));
//    }
//
//    public CommentEntity addComment(CommentEntity comment) {
//        comment.setCreatedAt(LocalDateTime.now());
//        return commentRepository.save(comment);
//    }
//
//    public CommentEntity updateComment(int commentId, CommentEntity updatedComment) {
//        CommentEntity comment = commentRepository.findById(commentId)
//                .orElseThrow(() -> new IllegalArgumentException("Comment with ID " + commentId + " not found."));
//
//        comment.setCommentText(updatedComment.getCommentText());
//        return commentRepository.save(comment);
//    }
//
//    public void deleteComment(int commentId) {
//        if (commentRepository.existsById(commentId)) {
//            commentRepository.deleteById(commentId);
//        } else {
//            throw new IllegalArgumentException("Comment with ID " + commentId + " not found.");
//        }
//    }
//}

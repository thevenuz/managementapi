package com.example.managementapi.api.controller;


import com.example.managementapi.entity.CommentEntity;
import com.example.managementapi.service.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
//@RequestMapping("/comments")
//public class CommentController_ {
//
//    private final CommentService commentService;
//
//    public CommentController_(CommentService commentService) {
//        this.commentService = commentService;
//    }
//
//    @GetMapping
//    public List<CommentEntity> getAllComments() {
//        return commentService.getAllComments();
//    }
//
//    @GetMapping("/{id}")
//    public CommentEntity getCommentById(@PathVariable int id) {
//        return commentService.getCommentById(id);
//    }
//
//    @PostMapping
//    public CommentEntity addComment(@RequestBody CommentEntity comment) {
//        return commentService.addComment(comment);
//    }
//
//    @PutMapping("/{id}")
//    public CommentEntity updateComment(@PathVariable int id, @RequestBody CommentEntity comment) {
//        return commentService.updateComment(id, comment);
//    }
//
//    @DeleteMapping("/{id}")
//    public void deleteComment(@PathVariable int id) {
//        commentService.deleteComment(id);
//    }
//}

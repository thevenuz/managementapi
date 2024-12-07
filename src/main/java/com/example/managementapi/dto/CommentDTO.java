package com.example.managementapi.dto;

public class CommentDTO {
    private String commentDescription; // Renamed from 'commentText' to 'comment_description'
    private String createdBy; // Username of the creator

    // Getters and Setters
    public String getCommentDescription() {
        return commentDescription;
    }

    public void setCommentDescription(String commentDescription) {
        this.commentDescription = commentDescription;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
}

package com.example.managementapi.dto;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CommentDTO {
    @JsonProperty("comment_description")
    private String commentDescription;
    @JsonProperty("created_by")
    private String createdBy;

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

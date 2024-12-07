package com.example.managementapi.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "comment")
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Integer commentId;

    @ManyToOne
    @JoinColumn(name = "ticket_id", nullable = false)
    private TicketEntity ticket;

    @Column(name = "comment_text", nullable = false) // We are renaming this column to comment_text to match DB schema
    private String commentDescription; // Renamed from 'commentText' to 'commentDescription'

    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    private EmployeeEntity createdBy;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    // Getters and Setters
    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public TicketEntity getTicket() {
        return ticket;
    }

    public void setTicket(TicketEntity ticket) {
        this.ticket = ticket;
    }

    public String getCommentDescription() {
        return commentDescription;
    }

    public void setCommentDescription(String commentDescription) {
        this.commentDescription = commentDescription;
    }

    public EmployeeEntity getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(EmployeeEntity createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}

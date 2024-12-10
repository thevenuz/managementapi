package com.example.managementapi.repository;

import com.example.managementapi.entity.CommentEntity;
import com.example.managementapi.entity.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {

    List<CommentEntity> findByTicket(TicketEntity ticket);

    //Optional<CommentEntity> findByIdAndTicket(Integer commentId, TicketEntity ticket);
    @Modifying
    @Transactional
    void deleteAllByTicket(TicketEntity ticket);
}

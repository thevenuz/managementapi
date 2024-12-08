package com.example.managementapi.repository;

import com.example.managementapi.entity.TaskEntity;
import com.example.managementapi.entity.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<TicketEntity, Integer> {
    List<TicketEntity> findByTask(TaskEntity task);
}

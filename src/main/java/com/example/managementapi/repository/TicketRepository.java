package com.example.managementapi.repository;

import com.example.managementapi.entity.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<TicketEntity, Integer> {
}

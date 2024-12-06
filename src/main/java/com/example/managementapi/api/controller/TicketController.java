package com.example.managementapi.api.controller;

import com.example.managementapi.dto.TicketDTO;
import com.example.managementapi.entity.TicketEntity;
import com.example.managementapi.service.TicketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/prm")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping("/tasks/{taskId}/ticket/new")
    public ResponseEntity<TicketEntity> createTicket(@PathVariable Integer taskId, @RequestBody TicketDTO ticketDTO) {
        return ResponseEntity.ok(ticketService.createTicket(taskId, ticketDTO));
    }

    @GetMapping("/tickets/{ticketId}")
    public ResponseEntity<TicketEntity> getTicket(@PathVariable Integer ticketId) {
        Optional<TicketEntity> ticket = ticketService.getTicketById(ticketId);
        return ticket.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/tasks/{taskId}/tickets/{ticketId}")
    public ResponseEntity<TicketEntity> updateTicket(@PathVariable Integer taskId, @PathVariable Integer ticketId, @RequestBody TicketDTO ticketDTO) {
        return ResponseEntity.ok(ticketService.updateTicket(taskId, ticketId, ticketDTO));
    }

    @DeleteMapping("/tickets/{ticketId}")
    public ResponseEntity<Void> deleteTicket(@PathVariable Integer ticketId) {
        ticketService.deleteTicket(ticketId);
        return ResponseEntity.noContent().build();
    }
}

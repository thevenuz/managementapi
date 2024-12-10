package com.example.managementapi.api.controller;

import com.example.managementapi.dto.TicketDTO;
import com.example.managementapi.entity.TicketEntity;
import com.example.managementapi.service.TicketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/prm")
@CrossOrigin(origins = "*")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping("/tasks/{taskId}/ticket/new")
    public ResponseEntity<?> createTicket(@PathVariable Integer taskId, @RequestBody TicketDTO ticketDTO) {
        try{

            Map<String, Object> ticket =  ticketService.createTicket(taskId, ticketDTO);
            Map<String, Object> response = new HashMap<>();
            response.put("isSuccess", true);
            response.put("data", ticket);
            return ResponseEntity.ok(response);
        }
        catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("isSuccess", false);
            errorResponse.put("message", "An error occurred while creating the ticket.");
            return ResponseEntity.status(500).body(errorResponse);
        }
    }

    @GetMapping("/tickets/{ticketId}")
    public ResponseEntity<?> getTicket(@PathVariable Integer ticketId) {
        try {
            Map<String, Object> ticket = ticketService.getTicketByIdJson(ticketId);
            Map<String, Object> response = new HashMap<>();
            response.put("isSuccess", true);
            response.put("data", ticket);
            return ResponseEntity.ok(response);
        }
        catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("isSuccess", false);
            errorResponse.put("message", "An error occurred while getting the ticket.");
            return ResponseEntity.status(500).body(errorResponse);
        }
    }

    @PutMapping("/tasks/{taskId}/tickets/{ticketId}")
    public ResponseEntity<?> updateTicket(@PathVariable Integer taskId, @PathVariable Integer ticketId, @RequestBody TicketDTO ticketDTO) {
        try {
            Map<String, Object> ticket = ticketService.updateTicket(taskId, ticketId, ticketDTO);
            Map<String, Object> response = new HashMap<>();
            response.put("isSuccess", true);
            response.put("data", ticket);
            return ResponseEntity.ok(response);
        }catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("isSuccess", false);
            errorResponse.put("message", "An error occurred while updating the ticket.");
            return ResponseEntity.status(500).body(errorResponse);
        }

    }

    @DeleteMapping("/tickets/{ticketId}")
    public ResponseEntity<?> deleteTicket(@PathVariable Integer ticketId) {
        try{
        ticketService.deleteTicket(ticketId);
        Map<String, Object> response = new HashMap<>();
        response.put("isSuccess", true);
        response.put("data", "Ticket deleted successfully.");
        return ResponseEntity.ok(response);
    }
        catch (Exception e) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("isSuccess", false);
        errorResponse.put("message", "An error occurred while deleting the ticket.");
        return ResponseEntity.status(500).body(errorResponse);
    }

    }
}

package com.example.managementapi.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

//@RestController
//@RequestMapping("/prm/tickets")
//@CrossOrigin(origins = "*")
//public class TicketController {
//
//    @Autowired
//    private TicketService ticketService;
//
//    @GetMapping("/{id}")
//    public ResponseEntity<TicketEntity> getTicket(@PathVariable String id) {
//        return ResponseEntity.ok(ticketService.getTicketById(id));
//    }
//
//    @PostMapping("/{taskId}/new")
//    public ResponseEntity<Map<String, Object>> createTicket(
//            @PathVariable String taskId,
//            @RequestBody Map<String, Object> ticketData) {
//        try {
//            ticketData.put("ticketId", "t_" + String.format("%03d", ticketService.getLatestTicketId() + 1));
//            ticketData.put("ticketStatus", "Open");
//            ticketData.put("createdDate", ticketService.getCurrentDate());
//            ticketData.put("lastUpdatedDate", ticketService.getCurrentDate());
//
//            TicketEntity newTicket = ticketService.createTicket(taskId, ticketData);
//            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("ticket", newTicket));
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", e.getMessage()));
//        }
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Map<String, String>> deleteTicket(@PathVariable String id) {
//        ticketService.deleteTicket(id);
//        return ResponseEntity.ok(Map.of("message", "Ticket deleted successfully."));
//    }
//}

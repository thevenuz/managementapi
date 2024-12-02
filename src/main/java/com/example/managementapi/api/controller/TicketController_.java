//package com.example.managementapi.api.controller;
//
//import com.example.managementapi.entity.TicketEntity;
//import com.example.managementapi.service.TicketService;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/tickets")
//public class TicketController_ {
//
//    private final TicketService ticketService;
//
//    public TicketController_(TicketService ticketService) {
//        this.ticketService = ticketService;
//    }
//
//    @GetMapping
//    public List<TicketEntity> getAllTickets() {
//        return ticketService.getAllTickets();
//    }
//
//    @GetMapping("/{id}")
//    public TicketEntity getTicketById(@PathVariable int id) {
//        return ticketService.getTicketById(id);
//    }
//
//    @PostMapping
//    public TicketEntity addTicket(@RequestBody TicketEntity ticket) {
//        return ticketService.addTicket(ticket);
//    }
//
//    @PutMapping("/{id}")
//    public TicketEntity updateTicket(@PathVariable int id, @RequestBody TicketEntity ticket) {
//        return ticketService.updateTicket(id, ticket);
//    }
//
//    @DeleteMapping("/{id}")
//    public void deleteTicket(@PathVariable int id) {
//        ticketService.deleteTicket(id);
//    }
//}

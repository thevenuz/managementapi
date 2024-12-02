//package com.example.managementapi.service;
//
//import com.example.managementapi.entity.TicketEntity;
//import com.example.managementapi.repository.TicketRepository;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//@Service
//public class TicketService {
//
//    private final TicketRepository ticketRepository;
//
//    public TicketService(TicketRepository ticketRepository) {
//        this.ticketRepository = ticketRepository;
//    }
//
//    public List<TicketEntity> getAllTickets() {
//        return ticketRepository.findAll();
//    }
//
//    public TicketEntity getTicketById(int ticketId) {
//        return ticketRepository.findById(ticketId)
//                .orElseThrow(() -> new IllegalArgumentException("TicketEntity with ID " + ticketId + " not found."));
//    }
//
//    public TicketEntity addTicket(TicketEntity ticket) {
//        ticket.setCreatedAt(LocalDateTime.now());
//        ticket.setUpdatedAt(LocalDateTime.now());
//        return ticketRepository.save(ticket);
//    }
//
//    public TicketEntity updateTicket(int ticketId, TicketEntity updatedTicket) {
//        TicketEntity ticket = ticketRepository.findById(ticketId)
//                .orElseThrow(() -> new IllegalArgumentException("TicketEntity with ID " + ticketId + " not found."));
//
//        ticket.setTicketTitle(updatedTicket.getTicketTitle());
//        ticket.setTicketDescription(updatedTicket.getTicketDescription());
//        ticket.setTicketType(updatedTicket.getTicketType());
//        ticket.setStatus(updatedTicket.getStatus());
//        ticket.setAssignedUser(updatedTicket.getAssignedUser());
//        ticket.setClosedDate(updatedTicket.getClosedDate());
//        ticket.setUpdatedAt(LocalDateTime.now());
//
//        return ticketRepository.save(ticket);
//    }
//
//    public void deleteTicket(int ticketId) {
//        if (ticketRepository.existsById(ticketId)) {
//            ticketRepository.deleteById(ticketId);
//        } else {
//            throw new IllegalArgumentException("TicketEntity with ID " + ticketId + " not found.");
//        }
//    }
//}

package com.cg.cricketleague.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.cricketleague.model.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer>{


}

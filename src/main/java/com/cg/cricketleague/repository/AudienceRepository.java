package com.cg.cricketleague.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.cricketleague.model.Audience;
import com.cg.cricketleague.model.Ticket;


@Repository
public interface AudienceRepository extends JpaRepository<Audience, Integer>{
	

}

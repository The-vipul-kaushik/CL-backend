package com.cg.cricketleague.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.cricketleague.model.Tournament;

@Repository
public interface TournamentRepository extends JpaRepository<Tournament, Integer>{

}

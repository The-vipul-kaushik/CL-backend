package com.cg.cricketleague.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.cricketleague.model.Match;

@Repository
public interface MatchRepository extends JpaRepository<Match, Integer> {
	public abstract Match findByMatchName(String matchName);
	
}

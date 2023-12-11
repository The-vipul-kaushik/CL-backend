package com.cg.cricketleague.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.cricketleague.model.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team, Integer> {
	public abstract Team findByTeamName(String teamName);
}

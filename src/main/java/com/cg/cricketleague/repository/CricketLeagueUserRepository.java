package com.cg.cricketleague.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.cricketleague.model.CricketLeagueUser;

@Repository
public interface CricketLeagueUserRepository extends JpaRepository<CricketLeagueUser, String> {

}

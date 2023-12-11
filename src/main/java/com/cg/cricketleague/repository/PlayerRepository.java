package com.cg.cricketleague.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.cricketleague.model.Player;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer>{

}

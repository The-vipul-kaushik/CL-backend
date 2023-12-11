package com.cg.cricketleague.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.cricketleague.model.Organiser;

@Repository
public interface OrganiserRepository extends JpaRepository<Organiser, Integer>{

}

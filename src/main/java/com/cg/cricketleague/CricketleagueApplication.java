package com.cg.cricketleague;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class CricketleagueApplication {

	private static final Logger LOG = LoggerFactory.getLogger(CricketleagueApplication.class);
	public static void main(String[] args) {
		LOG.info("Start");
		SpringApplication.run(CricketleagueApplication.class, args);
		LOG.info("End");
	}

}

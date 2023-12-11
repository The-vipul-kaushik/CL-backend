package com.cg.cricketleague.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.cricketleague.model.CricketLeagueUser;
import com.cg.cricketleague.service.CricketLeagueUserService;


@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:3000/")
public class CricketLeagueUserController implements ICricketLeagueUserController {

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	CricketLeagueUserService appUserService;

	@Override
	@GetMapping("/get-all-users")
	public ResponseEntity<List<CricketLeagueUser>> getAllAppUsers() {
		LOG.info("get-all-appUsers");
		return new ResponseEntity<List<CricketLeagueUser>>(appUserService.getAllUsers(), HttpStatus.OK);
	}

	@Override
	@PostMapping("/register")
	public ResponseEntity<CricketLeagueUser> register(@RequestBody CricketLeagueUser appUser) {
		LOG.info(appUser.toString());
		return new ResponseEntity<CricketLeagueUser>(appUserService.registerUser(appUser), HttpStatus.CREATED);
	}

	@Override
	@PostMapping("/login")
	public ResponseEntity<CricketLeagueUser> login(@RequestBody CricketLeagueUser appUser) {
		LOG.info(appUser.toString());
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "User " + appUser.getUserName() + " logged in successfully.");
		return new ResponseEntity<CricketLeagueUser>(appUserService.loginUser(appUser), headers, HttpStatus.OK);
	}

	@Override
	@GetMapping("/logout/{user}")
	public ResponseEntity<String> logout(@PathVariable(name = "user") String userName) {
		LOG.info(userName);
		return new ResponseEntity<String>(appUserService.logoutUser(userName), HttpStatus.OK);
	}

	@Override
	@PutMapping("/update-user")
	public ResponseEntity<CricketLeagueUser> updateAppUser(@RequestBody CricketLeagueUser appUser) {
		LOG.info(appUser.toString());
		return new ResponseEntity<CricketLeagueUser>(appUserService.updateUser(appUser), HttpStatus.OK);
	}
}

package com.cg.cricketleague.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.cricketleague.exception.CricketLeagueUserAlreadyExistsException;
import com.cg.cricketleague.exception.CricketLeagueUserNotFoundException;
import com.cg.cricketleague.model.CricketLeagueUser;
import com.cg.cricketleague.repository.CricketLeagueUserRepository;


@Service
public class CricketLeagueUserService implements ICricketLeagueUserService {

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CricketLeagueUserRepository cricketLeagueUserRepository;

	CricketLeagueUser loggedInUser;

	@Override
	public List<CricketLeagueUser> getAllUsers() {
		List<CricketLeagueUser> userList = cricketLeagueUserRepository.findAll();
		if (userList.isEmpty()) {
			String exceptionMessage = "CricketLeagueUsers don't exist in the database.";
			LOG.warn(exceptionMessage);
			throw new CricketLeagueUserNotFoundException(exceptionMessage);
		} else {
			LOG.info("depList returned successfully.");
			return userList;
		}
	}

	@Override
	public CricketLeagueUser registerUser(CricketLeagueUser CricketLeagueUser) {
		LOG.info(CricketLeagueUser.toString());
		Optional<CricketLeagueUser> userOptional = cricketLeagueUserRepository.findById(CricketLeagueUser.getUserName());
		if (userOptional.isEmpty()) {
			return cricketLeagueUserRepository.save(CricketLeagueUser);
		} else {
			String exceptionMessage = "User with userName " + CricketLeagueUser.getUserName() + " already exists.";
			throw new CricketLeagueUserAlreadyExistsException(exceptionMessage);
		}
	}

	@Override
	public CricketLeagueUser loginUser(CricketLeagueUser CricketLeagueUser) {
//		LOG.info(CricketLeagueUser.toString());
		Optional<CricketLeagueUser> userOptional = cricketLeagueUserRepository.findById(CricketLeagueUser.getUserName());
		if (userOptional.isPresent()) {
			if (CricketLeagueUser.equals(userOptional.get())) {
				LOG.info(userOptional.get().toString());
				LOG.info("Logged in successfully!!");
				loggedInUser = CricketLeagueUser; // successful login
				return CricketLeagueUser;
			} else {
				String exceptionMessage = "Wrong password!";
				LOG.warn(exceptionMessage);
				throw new CricketLeagueUserNotFoundException(exceptionMessage);
			}
		} else {
			String exceptionMessage = "Wrong userName!";
			LOG.warn(exceptionMessage);
			throw new CricketLeagueUserNotFoundException(exceptionMessage);
		}
	}

	@Override
	public String logoutUser(String userName) {
		if (loggedInUser.getUserName().equals(userName)) {

			loggedInUser = null;
			return userName;
		} else {
			String exceptionMessage = "User with userName " + userName + " is not logged in.";
			LOG.warn(exceptionMessage);
			throw new CricketLeagueUserNotFoundException(exceptionMessage);
		}
	}

	@Override
	public CricketLeagueUser updateUser(CricketLeagueUser CricketLeagueUser) {
		Optional<CricketLeagueUser> userOptional = cricketLeagueUserRepository.findById(CricketLeagueUser.getUserName());
		if (userOptional.isPresent()) {
			LOG.info(userOptional.get().toString());
			return cricketLeagueUserRepository.save(CricketLeagueUser);
		} else {
			String exceptionMessage = "CricketLeagueUser with userName " + CricketLeagueUser.getUserName() + " not found!";
			LOG.warn(exceptionMessage);
			throw new CricketLeagueUserNotFoundException(exceptionMessage);
		}
	}
}
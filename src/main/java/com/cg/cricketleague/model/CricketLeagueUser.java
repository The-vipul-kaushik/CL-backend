package com.cg.cricketleague.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "cricket_league_users")
public class CricketLeagueUser {

	@Id
	@Column(name = "user_name")
	@NotBlank
	private String userName;

//	password validation 
//	^                 # start-of-string
//	(?=.*[0-9])       # a digit must occur at least once
//	(?=.*[a-z])       # a lower case letter must occur at least once
//	(?=.*[A-Z])       # an upper case letter must occur at least once
//	(?=.*[@#$%^&+=])  # a special character must occur at least once
//	(?=\S+$)          # no whitespace allowed in the entire string
//	.{8,}             # anything, at least eight places though
//	$                 # end-of-string

	@Column(name = "password")
	@NotBlank
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$")
	private String password;

	@Column(name = "role")
	private Role role;

	public CricketLeagueUser() {
		super();
	}

	public CricketLeagueUser(String userName, String password, Role role) {
		super();
		this.userName = userName;
		this.password = password;
		this.role = role;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CricketLeagueUser other = (CricketLeagueUser) obj;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (role != other.role)
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AppUser [userName=" + userName + ", password=" + password + ", role=" + role + "]";
	}

}

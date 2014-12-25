package com.simbirsoft.security;

import javax.ejb.Stateful;
import javax.inject.Inject;

import com.simbirsoft.entities.User;
import com.simbirsoft.service.UserService;

@Stateful
public class AuthService {
	private User currUser;
	
	@Inject
	private UserService userService;
	
	public boolean login(String login, String password) {
		currUser = userService.login(login, password);
		
		return isLoggedIn();
	}
	
	public boolean isLoggedIn() {
		return currUser != null;
	}
	
	public void logout() {
		currUser = null;
	}
}

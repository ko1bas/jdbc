package com.simbirsoft.persistence.dao;

import java.util.ArrayList;
import java.util.List;

import com.simbirsoft.entities.User;

public class UserDAOImpl implements UserDAO {
	private List<User> users = new ArrayList<User>();
	
	@Override
	public List<User> findAll() {
		return users;
	}

	private User getById(String id) {		
		for (User user : users) {
			if (user.getId().equals(id)) {
				return user;
			}
		}
		
		return null;
	}

	@Override
	public boolean insertUser(User user) {
		return users.add(user);
	}

	@Override
	public boolean updateUser(User user) {
		User delUser = this.getById(user.getId());
		if (users.remove(delUser)) {
			users.add(user);
		}
		return true;
	}

	@Override
	public boolean deleteUser(User user) {
		User delUser = this.getById(user.getId());
		return delUser != null ? users.remove(delUser) : false;
	}

	@Override
	public User findByLogin(String login) {
		for (User user : users) {
			if (user.getLogin().equals(login)) {
				return user;
			}
		}
		return null;
	}
}
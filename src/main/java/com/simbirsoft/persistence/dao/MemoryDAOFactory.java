package com.simbirsoft.persistence.dao;

public class MemoryDAOFactory extends DAOFactory {
	private UserDAO userDao = new UserDAOImpl();
	
	@Override
	public UserDAO getUserDAO() {
		return userDao;
	}
}

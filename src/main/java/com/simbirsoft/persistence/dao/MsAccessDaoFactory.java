package com.simbirsoft.persistence.dao;

public class MsAccessDaoFactory  extends DAOFactory{

	private UserDAO userDao =  new UserDAOMsAccessImpl();
	
	@Override
	public UserDAO getUserDAO() {
		return userDao;
	}

}

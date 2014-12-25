package com.simbirsoft.service;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

import com.simbirsoft.entities.User;
import com.simbirsoft.persistence.PersistenceService;
import com.simbirsoft.persistence.dao.DAOFactory;
import com.simbirsoft.persistence.dao.UserDAO;
import com.simbirsoft.persistence.dao.DAOFactory.DAOTYPE;

@Stateless
@Named
public class UserService {
	@Inject
	private PersistenceService persistence;
	
	private UserDAO getUserDao() {
		
		DAOFactory dao = persistence.getDAOFactory(DAOTYPE.MsAccess);
		return dao.getUserDAO();
	}
	
	public void insertUser(User user) {
		getUserDao().insertUser(user);
	}
	
	public boolean isExistByLogin(User user) {
		return getUserDao().findByLogin(user.getLogin()) != null;
	}
	
	public User login(String login, String password) {
		UserDAO userDao = getUserDao();
		
		User user = userDao.findByLogin(login);
		if (user != null && user.getPassword().equals(password)) {
			return user;
		}
		
		return null;
	}
}

package com.simbirsoft.persistence.dao;

public class MsAccessDaoFactory  extends DAOFactory{

	private UserDAO userDao =  new UserDAOMsAccessImpl();
	
	private ProductDAO productDao =  new ProductDAOMsAccessImpl();
	
	@Override
	public UserDAO getUserDAO() {
		return userDao;
	}

	@Override
	public ProductDAO getProductDAO() {
		return productDao;
	}

}

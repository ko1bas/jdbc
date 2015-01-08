package com.simbirsoft.persistence.dao;

public abstract class DAOFactory {
	public static enum DAOTYPE {
		MEMORY,
		MYSQL,
		MsAccess
	}
	
	public abstract UserDAO getUserDAO();
	
	public abstract ProductDAO getProductDAO();
	
	public static DAOFactory getDAOFactory(DAOTYPE whichFactory) {
		switch (whichFactory) {
			case MEMORY: 
				return new MemoryDAOFactory();
			case MsAccess: 
				return new MsAccessDaoFactory();
			default: 
				return null;
		}
	}
}

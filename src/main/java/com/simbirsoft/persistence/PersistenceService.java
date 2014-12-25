package com.simbirsoft.persistence;

import javax.ejb.Stateless;

import com.simbirsoft.persistence.dao.DAOFactory;
import com.simbirsoft.persistence.dao.DAOFactory.DAOTYPE;

@Stateless
public class PersistenceService {
	private DAOFactory daoFactory;
	
	public DAOFactory getDAOFactory(DAOTYPE type) {
		if (daoFactory == null) {
			synchronized (this) {
				if (daoFactory == null) {
					daoFactory = DAOFactory.getDAOFactory(type);
				}
			}
		}
		return daoFactory;
	}
}

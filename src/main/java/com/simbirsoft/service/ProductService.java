package com.simbirsoft.service;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

import com.simbirsoft.persistence.PersistenceService;
import com.simbirsoft.persistence.dao.DAOFactory;
import com.simbirsoft.persistence.dao.ProductDAO;
import com.simbirsoft.persistence.dao.DAOFactory.DAOTYPE;

@Stateless
@Named
public class ProductService {

	@Inject
	private PersistenceService persistence;
		
	public ProductDAO getProductDao() {
		
		DAOFactory dao = persistence.getDAOFactory(DAOTYPE.MsAccess);
		return dao.getProductDAO();
	}

}

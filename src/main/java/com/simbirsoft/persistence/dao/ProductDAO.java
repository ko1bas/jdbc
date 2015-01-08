package com.simbirsoft.persistence.dao;

import java.util.List;

import com.simbirsoft.entities.Product;

public interface ProductDAO {

	List<Product> getProductList() ;
	
	boolean insertProduct(Product product);
	boolean updateProduct(Product product);
	boolean deleteProduct(Product product);
}

package com.simbirsoft.backing;


import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;

import com.simbirsoft.service.ProductService;
import com.simbirsoft.entities.Product;;



@ManagedBean(name="productList")
@RequestScoped
public class ProductListBacking implements Serializable{
	
	private static final long serialVersionUID = 3267095997550219944L;
	
	@Inject
	private ProductService productService;
	
	public List<Product> getProductList()
	{
		return productService.getProductDao().getProductList();
	}

}

package com.simbirsoft.persistence.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.simbirsoft.entities.Product;
import com.simbirsoft.entities.User;
import com.simbirsoft.utils.jdbc.DBConnectionPool;

public class ProductDAOMsAccessImpl implements ProductDAO {

	@Override
	public List<Product> getProductList() {
		List<Product> list = new ArrayList<Product>();
		
		try {
			Connection con = DBConnectionPool.getInstance().getConnection();
			try {
					java.sql.Statement stat = con.createStatement();
					String command = "Select * from Products";
					ResultSet result = stat.executeQuery(command);
					while(result.next()){
						Product product = new Product();
						product.setId(result.getString("id"));
						product.setName(result.getString("name"));
						product.setCategory(result.getString("category"));
						product.setDescription(result.getString("description"));
						product.setPrice(result.getFloat("price"));
						product.setCount(result.getInt("count"));
						list.add(product);
					}
			}
			finally {
				DBConnectionPool.getInstance().release(con);
			}
		}
		catch (SQLException ex ) {
			//ошибка
		}
		return list;
	}

	@Override
	public boolean insertProduct(Product product) {
		boolean result = false;
		try {
			Connection con = DBConnectionPool.getInstance().getConnection();
			try {
				
				String command = "insert into products (category,name,description,count,price) values (?,?,?,?,?)";
				java.sql.PreparedStatement ps = con.prepareStatement(command);
				ps.setString(1, product.getCategory());
				ps.setString(2, product.getName());
				ps.setString(3, product.getDescription());
				ps.setInt(4, product.getCount());
				ps.setFloat(5, product.getPrice());
				
				result = ps.executeUpdate()>0;	
			}
			finally {
				DBConnectionPool.getInstance().release(con);
			}
		}
		catch (SQLException ex ) {
			return false;
		}
		return result;
	}

	@Override
	public boolean updateProduct(Product product) {
		boolean result = false;
		try {
			Connection con = DBConnectionPool.getInstance().getConnection();
			try {
				String command = "update products set category =?, name=?,description=?,price=?, count=? where id=?";
				java.sql.PreparedStatement ps = con.prepareStatement(command);
				ps.setString(1, product.getCategory());
				ps.setString(2, product.getName());
				ps.setString(3, product.getDescription());
				ps.setFloat(4, product.getPrice());
				ps.setInt(5,  product.getCount());
				ps.setString(6, product.getId());
				
				result = ps.executeUpdate()>0;	
					
			}
			finally {
				DBConnectionPool.getInstance().release(con);
			}
		}
		catch (SQLException ex ) {
			return false;
		}
		return result;
	}

	@Override
	public boolean deleteProduct(Product product) {
		boolean result = false;
		try {
			Connection con = DBConnectionPool.getInstance().getConnection();
			try {
				String command = "delete from products  where id=?";
				java.sql.PreparedStatement ps = con.prepareStatement(command);
				ps.setString(1, product.getId());
				result = ps.executeUpdate()>0;							
			}
			finally {
				DBConnectionPool.getInstance().release(con);
			}
		}
		catch (SQLException ex ) {
			return false;
		}
		return result;
	}

}

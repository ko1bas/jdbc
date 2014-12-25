package com.simbirsoft.persistence.dao;


import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.simbirsoft.entities.User;
import com.simbirsoft.utils.jdbc.DBConnectionPool;

public class UserDAOMsAccessImpl  implements UserDAO {

	
	@Override
	public List<User> findAll()  {
		
		List<User> list = new ArrayList<User>();
			
		try {
			Connection con = DBConnectionPool.getInstance().getConnection();
			try {
					java.sql.Statement stat = con.createStatement();
					String command = "Select * from Users";
					ResultSet result = stat.executeQuery(command);
					while(result.next()){
						User user = new User();
						user.setId(result.getString("id"));
						user.setName(result.getString("name"));
						user.setLogin(result.getString("login"));
						user.setPassword(result.getString("password"));
						user.setMoney(result.getFloat("money"));
						user.setEmail(result.getString("email"));
						list.add(user);
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
	public User findByLogin(String login) {
		// TODO Auto-generated method stub
		Connection con;
		User user = null;
		try {
			con = DBConnectionPool.getInstance().getConnection();
			try {
					java.sql.PreparedStatement ps;
					String command = "Select * from Users where login =?";
					ps = con.prepareStatement(command);
					ps.setString(1, login);
					
					ResultSet result = ps.executeQuery();
					if (result.next()) {
						user = new User();
						user.setId(result.getString("id"));
						user.setName(result.getString("name"));
						user.setLogin(result.getString("login"));
						user.setPassword(result.getString("password"));
						user.setMoney(result.getFloat("money"));
						user.setEmail(result.getString("email"));
					}
			}
			finally {
				DBConnectionPool.getInstance().release(con);
			}
		}
		catch (SQLException ex ) {
			return null;
		}
		return user;
	}

	
	@Override
	public boolean insertUser(User user) {
		boolean result = false;
		try {
			Connection con = DBConnectionPool.getInstance().getConnection();
			try {
				
				String command = "insert into users (name,login,password,email,money) values (?,?,?,?,?)";
				java.sql.PreparedStatement ps = con.prepareStatement(command);
				ps.setString(1, user.getName());
				ps.setString(2, user.getLogin());
				ps.setString(3, user.getPassword());
				ps.setString(4, user.getEmail());
				ps.setFloat(5, user.getMoney());
				
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
	public boolean updateUser(User user) {
		boolean result = false;
		try {
			Connection con = DBConnectionPool.getInstance().getConnection();
			try {
				String command = "update users set name =?, login=?,password=?,email=?, money=? where id=?";
				java.sql.PreparedStatement ps = con.prepareStatement(command);
				ps.setString(1, user.getName());
				ps.setString(2, user.getLogin());
				ps.setString(3, user.getPassword());
				ps.setString(4, user.getEmail());
				ps.setFloat(5,  user.getMoney());
				ps.setString(6, user.getId());
				
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
	public boolean deleteUser(User user) {
		boolean result = false;
		try {
			Connection con = DBConnectionPool.getInstance().getConnection();
			try {
				String command = "delete from users  where id=?";
				java.sql.PreparedStatement ps = con.prepareStatement(command);
				ps.setString(1, user.getId());
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

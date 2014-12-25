package com.simbirsoft.persistence.dao;

import java.sql.SQLException;
import java.util.List;

import com.simbirsoft.entities.User;

public interface UserDAO {
	List<User> findAll() ;
	User findByLogin(String login);

    boolean insertUser(User user);
    boolean updateUser(User user);
    boolean deleteUser(User user);
}

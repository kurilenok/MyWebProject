package org.numisoft.webproject.dao;

import org.numisoft.webproject.dto.User;

/**
 * UserDao is a DAO class for User entity
 *
 * */

public interface UserDao {

	User getUserById(int id);

	int authenticate(String username, String password);

}

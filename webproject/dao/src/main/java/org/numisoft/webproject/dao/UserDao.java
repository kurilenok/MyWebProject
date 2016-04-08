package org.numisoft.webproject.dao;

import org.numisoft.webproject.dto.User;

public interface UserDao {

	User getUserById(int id);

	int authenticate(String username, String password);

}

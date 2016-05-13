package org.numisoft.webproject.dao;

import org.numisoft.webproject.dto.Banknote;
import org.numisoft.webproject.dto.User;

import java.util.List;
import java.util.Set;

/**
 * UserDao is a DAO class for User entity
 *
 * */

public interface UserDao {

	User getUserById(int id);

	int authenticate(String username, String password);

	Set<Banknote> getUserCollection(int user_id);



}

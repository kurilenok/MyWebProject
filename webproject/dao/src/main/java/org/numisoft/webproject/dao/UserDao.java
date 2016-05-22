package org.numisoft.webproject.dao;

import org.hibernate.Session;
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

	User getUserByName(String userName, Session session);

	Set<Banknote> getUserCollection(int user_id);

	boolean addBanknoteToCollection(int user_id, int banknote_id);

	boolean removeBanknoteFromCollection(int user_id, int banknote_id);


}

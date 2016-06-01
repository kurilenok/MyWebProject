package org.numisoft.webproject.dao;

import org.numisoft.webproject.pojos.Banknote;
import org.numisoft.webproject.pojos.User;

import java.util.Set;

/**
 * UserDao is a DAO class for User entity
 *
 * */

public interface UserDao {

	User getUserByName(String userName);

	Set<Banknote> getUserCollection(int user_id);

	boolean addBanknoteToCollection(int user_id, int banknote_id);

	boolean removeBanknoteFromCollection(int user_id, int banknote_id);


}

package org.numisoft.webproject.dao;

import org.numisoft.webproject.dto.Collectible;
import org.numisoft.webproject.dto.User;

import java.util.List;

/**
 * CollectibleDao is a DAO class for Collectible entity
 *
 * */

public interface CollectibleDao {

	/**
	 * Gets all items from User Collection
	 *
	 * */
	List<Collectible> getCollectiblesByUser(User user);

	/**
	 * Adds new item to User Collection
	 * */
	void addCollectible(int user_id, int banknote_id);

		/**
	 * Deletes item from User Collection
	 * */
	void deleteCollectible(int id);

}

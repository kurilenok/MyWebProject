package org.numisoft.webproject.dao;

import org.numisoft.webproject.dto.Banknote;

import java.util.List;
import java.util.Set;

/**
 * BanknoteDao is a DAO class for Banknote entity
 * */

public interface BanknoteDao {

	Banknote getBanknoteById(int id);

	/**
	 * Gets all items from General Catalog
	 *
	 * */
	List<Banknote> getAllBanknotes();

	/**
	 * Adds new item to General Catalog
	 * */
	void addBanknoteToCatalog(String title, String country, String link);

	/**
	 * Deletes item from General Catalog
	 * */
	void removeBanknoteFromCatalog(int id);


	void addBanknoteToCollection(int user_id, int banknote_id);

	void removeBanknoteFromCollection(int user_id, int banknote_id);

}

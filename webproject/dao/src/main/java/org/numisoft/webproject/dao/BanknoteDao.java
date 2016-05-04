package org.numisoft.webproject.dao;

import org.numisoft.webproject.dto.Banknote;

import java.util.List;

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
	void addBanknote(String title, String country, String link);

	/**
	 * Deletes item from General Catalog
	 * */
	void deleteBanknote(int id);

}

package org.numisoft.webproject.dao;

import org.hibernate.Session;
import org.numisoft.webproject.pojos.Banknote;

import java.util.Set;

/**
 * BanknoteDao is a DAO class for Banknote entity
 * */

public interface BanknoteDao {

	Banknote getBanknoteById(int id);

	/**
	 * This method gets all items from Catalog
	 * and provides data for pagination:
	 * @param currentPage is number of current page
	 *
	 * */
	Set<Banknote> getAllBanknotes(int currentPage);

	long calculateMaxPages();

	/**
	 * Adds new item to General Catalog
	 * */
	void addBanknoteToCatalog(Banknote banknote);

	/**
	 * Deletes item from General Catalog
	 * */
	void removeBanknoteFromCatalog(int id);


}

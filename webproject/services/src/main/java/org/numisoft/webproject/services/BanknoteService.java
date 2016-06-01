package org.numisoft.webproject.services;

import org.numisoft.webproject.pojos.Banknote;

import java.util.Set;

/**
 * Created by kukolka on 13.05.16.
 */
public interface BanknoteService {

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
    void addBanknoteToCatalog(String title, int nominal, String country, String link);

    /**
     * Deletes item from General Catalog
     * */
    void removeBanknoteFromCatalog(int id);


}

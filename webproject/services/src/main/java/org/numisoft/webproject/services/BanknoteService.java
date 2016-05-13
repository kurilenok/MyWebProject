package org.numisoft.webproject.services;

import org.numisoft.webproject.dao.BanknoteDaoImpl;
import org.numisoft.webproject.dto.Banknote;

import java.util.List;
import java.util.Set;

/**
 * BanknoteService is a Service class for Banknote entity
 */
public class BanknoteService {

    private static BanknoteService banknoteService;

    private BanknoteService() {
    }

    public static BanknoteService getInstance() {
        if (banknoteService == null) {
            banknoteService = new BanknoteService();
            return banknoteService;
        } else {
            return banknoteService;
        }
    }

    private BanknoteDaoImpl bdi = BanknoteDaoImpl.getInstance();

    public Banknote getBanknoteById(int id) {
        return bdi.getBanknoteById(id);
    }

    public Set<Banknote> getAllBanknotes(int page) {
        return bdi.getAllBanknotes(page);
    }

    public long calculateMaxPages() {
        return bdi.calculateMaxPages();
    }

    public void addBanknoteToCatalog(String title, int nominal, String country, String link) {
        bdi.addBanknoteToCatalog(title, nominal, country, link);
    }

    public void removeBanknoteFromCatalog(int id) {
        bdi.removeBanknoteFromCatalog(id);
    }

   public void addBanknoteToCollection(int user_id, int banknote_id) {
        bdi.addBanknoteToCollection(user_id, banknote_id);
    }

    public void removeBanknoteFromCollection(int user_id, int banknote_id) {
        bdi.removeBanknoteFromCollection(user_id, banknote_id);
    }

}

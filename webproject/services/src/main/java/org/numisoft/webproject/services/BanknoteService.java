package org.numisoft.webproject.services;

import org.numisoft.webproject.dao.BanknoteDaoImpl;
import org.numisoft.webproject.dto.Banknote;

import java.util.List;

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

    public List<Banknote> getAllBanknotes() {
        return bdi.getAllBanknotes();
    }

    public void addBanknote(String title, String country, String link) {
        bdi.addBanknote(title, country, link);
    }

    public void deleteBanknote(int id) {
        bdi.deleteBanknote(id);
    }

}

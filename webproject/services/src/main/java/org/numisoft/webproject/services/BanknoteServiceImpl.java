package org.numisoft.webproject.services;

import org.numisoft.webproject.dao.BanknoteDaoImpl;
import org.numisoft.webproject.dto.Banknote;

import java.util.Set;

/**
 * BanknoteServiceImpl is a Service class for Banknote entity
 */
public class BanknoteServiceImpl implements BanknoteService {

    private static BanknoteServiceImpl banknoteServiceImpl;

    private BanknoteServiceImpl() {
    }

    public static BanknoteServiceImpl getInstance() {
        if (banknoteServiceImpl == null) {
            banknoteServiceImpl = new BanknoteServiceImpl();
            return banknoteServiceImpl;
        } else {
            return banknoteServiceImpl;
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



}

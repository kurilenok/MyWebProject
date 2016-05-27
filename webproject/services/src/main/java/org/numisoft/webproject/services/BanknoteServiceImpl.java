package org.numisoft.webproject.services;

import org.numisoft.webproject.dao.BanknoteDao;
import org.numisoft.webproject.dao.CountryDao;
import org.numisoft.webproject.pojos.Banknote;
import org.numisoft.webproject.pojos.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * BanknoteServiceImpl is a Service class for Banknote entity
 */
@Service
public class BanknoteServiceImpl implements BanknoteService {

    private static BanknoteServiceImpl banknoteService;

    public BanknoteServiceImpl() {
    }

    public static BanknoteServiceImpl getInstance() {
        if (banknoteService == null) {
            banknoteService = new BanknoteServiceImpl();
            return banknoteService;
        } else {
            return banknoteService;
        }
    }

    public BanknoteDao getBanknoteDao() {
        return banknoteDao;
    }

    public void setBanknoteDao(BanknoteDao banknoteDao) {
        this.banknoteDao = banknoteDao;
    }

    @Autowired
    private BanknoteDao banknoteDao;

    @Autowired
    private CountryDao countryDao;

    @Autowired
    private Country country;

    @Autowired
    private Banknote banknote;


    @Transactional
    public Banknote getBanknoteById(int id) {
        return banknoteDao.getBanknoteById(id);
    }

    @Transactional
    public Set<Banknote> getAllBanknotes(int page) {
        Set<Banknote> banknotes = banknoteDao.getAllBanknotes(page);
        return banknotes;
    }

    @Transactional
    public long calculateMaxPages() {
        return banknoteDao.calculateMaxPages();
    }

    @Transactional
    public void addBanknoteToCatalog(String title, int nominal, String countryName, String link) {

        country = countryDao.getCountryByName(countryName);

        if (country == null) {
            country = new Country();
            country.setCountryName(countryName);
        }

        banknote = new Banknote();
        banknote.setTitle(title);
        banknote.setNominal(nominal);
        banknote.setCountry(country);
        banknote.setLink(link);

        banknoteDao.addBanknoteToCatalog(banknote);

    }

    @Transactional
    public void removeBanknoteFromCatalog(int id) {
        banknoteDao.removeBanknoteFromCatalog(id);
    }


}

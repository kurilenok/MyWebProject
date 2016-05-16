package org.numisoft.webproject.services;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.numisoft.webproject.dao.BanknoteDaoImpl;
import org.numisoft.webproject.dao.CountryDaoImpl;
import org.numisoft.webproject.dto.Banknote;
import org.numisoft.webproject.dto.Country;
import org.numisoft.webproject.utils.HibernateUtil;

import java.util.Set;

/**
 * BanknoteServiceImpl is a Service class for Banknote entity
 */
public class BanknoteServiceImpl implements BanknoteService {

    private static BanknoteServiceImpl banknoteService;

    private BanknoteServiceImpl() {
    }

    public static BanknoteServiceImpl getInstance() {
        if (banknoteService == null) {
            banknoteService = new BanknoteServiceImpl();
            return banknoteService;
        } else {
            return banknoteService;
        }
    }

    private BanknoteDaoImpl banknoteDao = BanknoteDaoImpl.getInstance();


    public Banknote getBanknoteById(int id) {
        return banknoteDao.getBanknoteById(id);
    }

    public Set<Banknote> getAllBanknotes(int page) {
        return banknoteDao.getAllBanknotes(page);
    }

    public long calculateMaxPages() {
        return banknoteDao.calculateMaxPages();
    }


    public void addBanknoteToCatalog(String title, int nominal, String countryName, String link) {

        HibernateUtil hibernateUtil = HibernateUtil.getHibernateUtil();
        Session session = hibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        Country country = CountryDaoImpl.getInstance().getCountryByName(countryName, session);

        if (country == null) {
            country = new Country();
            country.setCountryName(countryName);
        }


        Banknote banknote = new Banknote();
        banknote.setTitle(title);
        banknote.setNominal(nominal);
        banknote.setCountry(country);
        banknote.setLink(link);

        session.persist(banknote);

        transaction.commit();
        hibernateUtil.closeSession();

    }


    public void removeBanknoteFromCatalog(int id) {
        banknoteDao.removeBanknoteFromCatalog(id);
    }



}

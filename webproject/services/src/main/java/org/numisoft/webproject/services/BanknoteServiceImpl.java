package org.numisoft.webproject.services;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.numisoft.webproject.dao.BanknoteDao;
import org.numisoft.webproject.dao.BanknoteDaoImpl;
import org.numisoft.webproject.dao.CountryDaoImpl;
import org.numisoft.webproject.pojos.Banknote;
import org.numisoft.webproject.pojos.Country;
import org.numisoft.webproject.utils.HibernateUtil;
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

        HibernateUtil hibernateUtil = HibernateUtil.getHibernateUtil();
        Session session = hibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        Country country = CountryDaoImpl.getInstance().getCountryByName(countryName);

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

    @Transactional
    public void removeBanknoteFromCatalog(int id) {
        banknoteDao.removeBanknoteFromCatalog(id);
    }



}

package org.numisoft.webproject.dao;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.numisoft.webproject.dto.Banknote;
import org.numisoft.webproject.dto.Country;
import org.numisoft.webproject.dto.User;
import org.numisoft.webproject.utils.Constants;
import org.numisoft.webproject.utils.HibernateUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Implementation of BanknoteDao
 */

public class BanknoteDaoImpl implements BanknoteDao {

    private static BanknoteDaoImpl bdi;

    private BanknoteDaoImpl() {
    }

    public static BanknoteDaoImpl getInstance() {
        if (bdi == null) {
            bdi = new BanknoteDaoImpl();
            return bdi;
        } else {
            return bdi;
        }
    }

    @Override
    public Banknote getBanknoteById(int id) {

        HibernateUtil hibernateUtil = HibernateUtil.getHibernateUtil();
        Session session = hibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        Criteria c = session.createCriteria(org.numisoft.webproject.dto.Banknote.class);
        c.add(Restrictions.eq("id", id));

        Banknote banknote = (Banknote) c.uniqueResult();

        transaction.commit();
        hibernateUtil.closeSession();

        return banknote;
    }

    @Override
    public long calculateMaxPages() {

        HibernateUtil hibernateUtil = HibernateUtil.getHibernateUtil();
        Session session = hibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        Criteria c = session.createCriteria(org.numisoft.webproject.dto.Banknote.class);
        c.setProjection(Projections.rowCount());

        long result = (long) c.uniqueResult();
        long maxPages = (result % Constants.BANKNOTES_PER_PAGE == 0) ?
                (result / Constants.BANKNOTES_PER_PAGE) :
                (result / Constants.BANKNOTES_PER_PAGE + 1);

        transaction.commit();
        hibernateUtil.closeSession();

        return maxPages;
    }

    @Override
    public Set<Banknote> getAllBanknotes(int currentPage) {

        HibernateUtil hibernateUtil = HibernateUtil.getHibernateUtil();
        Session session = hibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        Criteria c = session.createCriteria(org.numisoft.webproject.dto.Banknote.class);
        c.addOrder(Order.asc("country"));
        c.addOrder(Order.asc("nominal"));
        c.setFirstResult((currentPage - 1) * Constants.BANKNOTES_PER_PAGE);
        c.setMaxResults(Constants.BANKNOTES_PER_PAGE);

        Set<Banknote> banknotes = new TreeSet<>(c.list());

        transaction.commit();
        hibernateUtil.closeSession();

        return banknotes;
    }

    @Override
    public void addBanknoteToCatalog(String title, int nominal, String countryName, String link) {

        HibernateUtil hibernateUtil = HibernateUtil.getHibernateUtil();
        Session session = hibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        Criteria c = session.createCriteria(org.numisoft.webproject.dto.Country.class);
        c.add(Restrictions.like("countryName", countryName));
        List countries = (ArrayList<Country>) c.list();

        Country country;

        if (countries.size() != 0) {
            int country_id = ((Country) countries.get(0)).getId();
            country = (Country) session.load(org.numisoft.webproject.dto.Country.class, country_id);
        } else {
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

    @Override
    public void removeBanknoteFromCatalog(int id) {

        HibernateUtil hibernateUtil = HibernateUtil.getHibernateUtil();
        Session session = hibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();


        Banknote banknote = (Banknote) session.load(org.numisoft.webproject.dto.Banknote.class, id);
        session.delete(banknote);

        transaction.commit();
        hibernateUtil.closeSession();
    }



}
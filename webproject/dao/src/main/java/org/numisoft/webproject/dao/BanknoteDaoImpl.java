package org.numisoft.webproject.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.numisoft.webproject.pojos.Banknote;
import org.numisoft.webproject.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.TreeSet;

/**
 * Implementation of BanknoteDao
 */

@Repository
public class BanknoteDaoImpl implements BanknoteDao {

    public BanknoteDaoImpl() {
    }

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public long calculateMaxPages() {

        Session session = sessionFactory.getCurrentSession();

        Criteria c = session.createCriteria(org.numisoft.webproject.pojos.Banknote.class);
        c.setProjection(Projections.rowCount());

        long result = (long) c.uniqueResult();
        long maxPages = (result % Constants.BANKNOTES_PER_PAGE == 0) ?
                (result / Constants.BANKNOTES_PER_PAGE) :
                (result / Constants.BANKNOTES_PER_PAGE + 1);

        return maxPages;
    }

    @Override
    public Set<Banknote> getAllBanknotes(int currentPage) {

        Session session = sessionFactory.getCurrentSession();

        Criteria criteria = session.createCriteria(org.numisoft.webproject.pojos.Banknote.class);
        criteria.createAlias("country", "c");
        criteria.addOrder(Order.asc("c.countryName"));
        criteria.addOrder(Order.asc("nominal"));

        criteria.setFirstResult((currentPage - 1) * Constants.BANKNOTES_PER_PAGE);
        criteria.setMaxResults(Constants.BANKNOTES_PER_PAGE);

        Set<Banknote> banknotes = new TreeSet<>(criteria.list());

        return banknotes;
    }


    @Override
    public void addBanknoteToCatalog(Banknote banknote) {

        Session session = sessionFactory.getCurrentSession();

        session.saveOrUpdate(banknote);

    }

    @Override
    public void removeBanknoteFromCatalog(int id) {

        Session session = sessionFactory.getCurrentSession();

        Banknote banknote = (Banknote) session.load(org.numisoft.webproject.pojos.Banknote.class, id);
        session.delete(banknote);

    }

}
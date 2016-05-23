package org.numisoft.webproject.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.numisoft.webproject.pojos.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by kukolka on 16.05.16.
 */
@Repository
public class CountryDaoImpl implements CountryDao {

    public CountryDaoImpl() {
    }

    @Autowired
    private SessionFactory sessionFactory;

    private static CountryDaoImpl countryDao;
    public static CountryDaoImpl getInstance() {
        if (countryDao == null) {
            countryDao = new CountryDaoImpl();
            return countryDao;
        } else {
            return countryDao;
        }
    }

    @Override
    public Country getCountryByName(String countryName) {

        Session session = sessionFactory.getCurrentSession();

        Criteria criteria = session.createCriteria(org.numisoft.webproject.pojos.Country.class);
        criteria.add(Restrictions.like("countryName", countryName));
        return (Country) criteria.uniqueResult();

    }


}

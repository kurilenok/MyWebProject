package org.numisoft.webproject.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.numisoft.webproject.dto.Country;

import java.util.List;

/**
 * Created by kukolka on 16.05.16.
 */
public class CountryDaoImpl implements CountryDao {

    private static CountryDaoImpl countryDao;

    private CountryDaoImpl() {
    }

    public static CountryDaoImpl getInstance() {
        if (countryDao == null) {
            countryDao = new CountryDaoImpl();
            return countryDao;
        } else {
            return countryDao;
        }
    }

    @Override
    public Country getCountryByName(String countryName, Session session) {

        Criteria criteria = session.createCriteria(org.numisoft.webproject.dto.Country.class);
        criteria.add(Restrictions.like("countryName", countryName));
        return (Country) criteria.uniqueResult();

    }


}

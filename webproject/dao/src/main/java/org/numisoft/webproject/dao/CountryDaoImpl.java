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

    private CountryDaoImpl() {
    }

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public Country getCountryByName(String countryName) {

        Session session = sessionFactory.getCurrentSession();

        Criteria criteria = session.createCriteria(org.numisoft.webproject.pojos.Country.class);
        criteria.add(Restrictions.like("countryName", countryName));
        return (Country) criteria.uniqueResult();

    }


}

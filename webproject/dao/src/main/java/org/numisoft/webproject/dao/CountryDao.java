package org.numisoft.webproject.dao;

import org.hibernate.Session;
import org.numisoft.webproject.dto.Country;

/**
 * Created by kukolka on 16.05.16.
 */
public interface CountryDao {

    Country getCountryByName(String countryName, Session session);


}

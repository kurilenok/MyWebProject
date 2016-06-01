package org.numisoft.webproject;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.numisoft.webproject.dao.BanknoteDao;
import org.numisoft.webproject.dao.CountryDao;
import org.numisoft.webproject.pojos.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by kukolka on 11.04.16.
 */
@ContextConfiguration("/testDaoContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class CountryDaoImplTest extends TestCase {

    @Autowired
    private CountryDao countryDao;

    @Test
    public void test() {

        assertNotNull(countryDao.getCountryByName("Kyrgyzstan"));

    }
}

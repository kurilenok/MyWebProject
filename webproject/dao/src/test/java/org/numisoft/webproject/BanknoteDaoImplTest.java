package org.numisoft.webproject;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.numisoft.webproject.dao.BanknoteDao;
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
public class BanknoteDaoImplTest extends TestCase {

    @Autowired
    private BanknoteDao banknoteDao;

    @Test
    public void test() {

//            BanknoteDaoImpl banknoteDao = BanknoteDaoImpl.getInstance();
            assertEquals("som", banknoteDao.getBanknoteById(25).getTitle());
            assertEquals(10, banknoteDao.getBanknoteById(25).getNominal());
            assertTrue(banknoteDao.getAllBanknotes(1).size() > 5);
            assertTrue(banknoteDao.calculateMaxPages() > 0);

    }
}

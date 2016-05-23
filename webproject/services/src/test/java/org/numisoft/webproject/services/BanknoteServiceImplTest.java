package org.numisoft.webproject.services;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by kukolka on 12.04.16.
 */
@ContextConfiguration("/testServicesContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class BanknoteServiceImplTest extends TestCase {

    @Autowired
    BanknoteService banknoteService;

    @Test
    public void test() {

        assertEquals("som", banknoteService.getBanknoteById(25).getTitle());
        assertEquals(10, banknoteService.getBanknoteById(25).getNominal());
        assertTrue(banknoteService.getAllBanknotes(1).size() > 5);

//        banknoteService.addBanknoteToCatalog("test", 100, "Belarus", "http://");


    }
}

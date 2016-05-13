package org.numisoft.webproject.services;

import junit.framework.TestCase;
import org.junit.Test;

/**
 * Created by kukolka on 12.04.16.
 */
public class BanknoteServiceImplTest extends TestCase {

    @Test
    public void test() {

        BanknoteServiceImpl banknoteServiceImpl = BanknoteServiceImpl.getInstance();
        assertEquals("1 som", banknoteServiceImpl.getBanknoteById(1).getTitle());
        assertTrue(banknoteServiceImpl.getAllBanknotes(1).size() > 5);

    }

}

package org.numisoft.webproject.services;

import junit.framework.TestCase;
import org.junit.Test;

/**
 * Created by kukolka on 12.04.16.
 */
public class BanknoteServiceTest extends TestCase {

    @Test
    public void test() {

        BanknoteService banknoteService = BanknoteService.getInstance();
        assertEquals("1 som", banknoteService.getBanknoteById(1).getTitle());
        assertTrue(banknoteService.getAllBanknotes(1).size() > 5);

    }

}

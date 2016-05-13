package org.numisoft.webproject;

import junit.framework.TestCase;
import org.junit.Test;
import org.numisoft.webproject.dao.BanknoteDaoImpl;

/**
 * Created by kukolka on 11.04.16.
 */
public class BanknoteDaoImplTest extends TestCase {

    @Test
    public void test() {

            BanknoteDaoImpl bdi = BanknoteDaoImpl.getInstance();
            assertEquals("som", bdi.getBanknoteById(25).getTitle());
            assertEquals(10, bdi.getBanknoteById(25).getNominal());
            assertTrue(bdi.getAllBanknotes(1).size() > 5);
            assertTrue(bdi.calculateMaxPages() > 0);

    }
}

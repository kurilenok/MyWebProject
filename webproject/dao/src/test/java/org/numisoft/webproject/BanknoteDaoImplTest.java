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
            assertEquals("1 som", bdi.getBanknoteById(1).getTitle());
            assertTrue(bdi.getAllBanknotes().size() > 20);

    }
}

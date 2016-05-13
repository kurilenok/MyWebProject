package org.numisoft.webproject.services;

import junit.framework.TestCase;
import org.junit.Test;

/**
 * Created by kukolka on 12.04.16.
 */
public class UserServiceImplTest extends TestCase {

    @Test
    public void test() {

        UserServiceImpl usi = UserServiceImpl.getInstance();
        assertEquals(4, usi.authenticate("admin", "admin"));
        assertTrue(usi.getUserCollection(2).size() > 0);
        assertTrue(usi.addBanknoteToCollection(4, 25));
        assertTrue(usi.removeBanknoteFromCollection(4, 25));

    }

}

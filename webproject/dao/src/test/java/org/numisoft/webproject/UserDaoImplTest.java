package org.numisoft.webproject;

import junit.framework.TestCase;
import org.junit.Test;
import org.numisoft.webproject.dao.UserDaoImpl;


/**
 * Created by kukolka on 11.04.16.
 */
public class UserDaoImplTest extends TestCase {

    @Test
    public void test() {

        UserDaoImpl userDao = UserDaoImpl.getInstance();
        assertTrue(userDao.getUserCollection(2).size() > 0);
        assertTrue(userDao.addBanknoteToCollection(4, 25));
        assertTrue(userDao.removeBanknoteFromCollection(4, 25));
    }

}

package org.numisoft.webproject;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.numisoft.webproject.dao.UserDao;
import org.numisoft.webproject.dao.UserDaoImpl;
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
public class UserDaoImplTest extends TestCase {

    @Autowired
    private UserDao userDao;

    @Test
    public void test() {
//        UserDaoImpl userDao = UserDaoImpl.getInstance();
        assertTrue(userDao.getUserCollection(2).size() > 0);
        assertTrue(userDao.getUserById(2).getRole_id() > 0);
        assertTrue(userDao.addBanknoteToCollection(4, 25));
        assertTrue(userDao.removeBanknoteFromCollection(4, 25));
    }

}

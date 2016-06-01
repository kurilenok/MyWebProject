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
public class UserServiceImplTest extends TestCase {


    @Autowired
    UserService userService;

    @Test
    public void test() {

        assertTrue(userService.getUserCollection(2).size() > 0);
        assertTrue(userService.addBanknoteToCollection(4, 25));
        assertTrue(userService.removeBanknoteFromCollection(4, 25));

    }

}

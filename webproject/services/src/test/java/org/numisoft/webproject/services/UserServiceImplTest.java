package org.numisoft.webproject.services;

import junit.framework.TestCase;
import org.junit.Test;

/**
 * Created by kukolka on 12.04.16.
 */
public class UserServiceImplTest extends TestCase {

    @Test
    public void test() {

        UserServiceImpl userServiceImpl = UserServiceImpl.getInstance();
        assertEquals(4, userServiceImpl.authenticate("admin", "admin"));

    }

}

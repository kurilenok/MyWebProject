package org.numisoft.webproject.services;

import junit.framework.TestCase;
import org.junit.Test;

/**
 * Created by kukolka on 12.04.16.
 */
public class UserServiceTest extends TestCase {

    @Test
    public void test() {

        UserService userService = UserService.getInstance();
        assertEquals(4, userService.authenticate("admin", "admin"));

    }

}

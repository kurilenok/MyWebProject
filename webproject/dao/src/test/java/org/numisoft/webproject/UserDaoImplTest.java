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

        UserDaoImpl udi = UserDaoImpl.getInstance();
        assertEquals(4, udi.authenticate("admin", "admin"));

    }

}

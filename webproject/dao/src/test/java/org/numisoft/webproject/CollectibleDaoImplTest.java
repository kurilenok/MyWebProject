package org.numisoft.webproject;

import junit.framework.TestCase;
import org.junit.Test;
import org.numisoft.webproject.dao.CollectibleDaoImpl;
import org.numisoft.webproject.dao.UserDaoImpl;

/**
 * Created by kukolka on 11.04.16.
 */
public class CollectibleDaoImplTest extends TestCase {

    @Test
    public void test() {
        CollectibleDaoImpl cdi = CollectibleDaoImpl.getInstance();
        UserDaoImpl udi = UserDaoImpl.getInstance();
        assertEquals(2, cdi.getCollectiblesByUser(udi.getUserById(1)).size());
    }
}

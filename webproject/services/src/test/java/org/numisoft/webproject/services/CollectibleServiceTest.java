package org.numisoft.webproject.services;

import junit.framework.TestCase;
import org.junit.Test;

/**
 * Created by kukolka on 12.04.16.
 */
public class CollectibleServiceTest extends TestCase {

    @Test
    public void test() {
        CollectibleService collectibleService = CollectibleService.getInstance();
        UserService userService = UserService.getInstance();
        assertEquals(2, collectibleService.getCollectiblesByUser(userService.getUserById(1)).size());
    }

}

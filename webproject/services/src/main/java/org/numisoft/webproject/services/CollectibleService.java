package org.numisoft.webproject.services;

import org.numisoft.webproject.dao.CollectibleDaoImpl;
import org.numisoft.webproject.dto.Collectible;
import org.numisoft.webproject.dto.User;

import java.util.List;

/**
 * CollectibleService is a Service class for Collectible entity
 */
public class CollectibleService {

    private static CollectibleService collectibleService;

    private CollectibleService() {
    }

    public static CollectibleService getInstance() {
        if (collectibleService == null) {
            collectibleService = new CollectibleService();
            return collectibleService;
        } else {
            return collectibleService;
        }
    }


    private CollectibleDaoImpl cdi = CollectibleDaoImpl.getInstance();


    public List<Collectible> getCollectiblesByUser(User user) {
        return cdi.getCollectiblesByUser(user);
    }

    public void addCollectible(int user_id, int banknote_id) {
        cdi.addCollectible(user_id, banknote_id);
    }

    public void deleteCollectible(int id) {
        cdi.deleteCollectible(id);
    }

}

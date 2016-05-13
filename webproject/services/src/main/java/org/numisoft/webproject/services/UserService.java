package org.numisoft.webproject.services;

import org.numisoft.webproject.dto.Banknote;
import org.numisoft.webproject.dto.User;

import java.util.Set;

/**
 * Created by kukolka on 13.05.16.
 */
public interface UserService {

    User getUserById(int id);

    int authenticate(String username, String password);

    Set<Banknote> getUserCollection(int user_id);

    void addBanknoteToCollection(int user_id, int banknote_id);

    void removeBanknoteFromCollection(int user_id, int banknote_id);

}

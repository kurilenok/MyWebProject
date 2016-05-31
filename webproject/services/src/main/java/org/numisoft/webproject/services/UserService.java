package org.numisoft.webproject.services;

import org.numisoft.webproject.pojos.Banknote;
import org.numisoft.webproject.pojos.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Set;

/**
 * Created by kukolka on 13.05.16.
 */

public interface UserService {

    User getUserById(int id);

    User getUserByName(String username);

    int authenticate(String username, String password);

    Set<Banknote> getUserCollection(int user_id);

    boolean addBanknoteToCollection(int user_id, int banknote_id);

    boolean removeBanknoteFromCollection(int user_id, int banknote_id);

}

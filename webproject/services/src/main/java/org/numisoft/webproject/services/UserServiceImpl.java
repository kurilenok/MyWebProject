package org.numisoft.webproject.services;

import org.numisoft.webproject.dao.UserDaoImpl;
import org.numisoft.webproject.dto.Banknote;
import org.numisoft.webproject.dto.User;

import java.util.Set;

/**
 * UserServiceImpl is a Service class for User entity
 */
public class UserServiceImpl implements UserService {

    private static UserServiceImpl userServiceImpl;

    private UserServiceImpl() {
    }

    public static UserServiceImpl getInstance() {
        if (userServiceImpl == null) {
            userServiceImpl = new UserServiceImpl();
            return userServiceImpl;
        } else {
            return userServiceImpl;
        }
    }

        private UserDaoImpl userDao = UserDaoImpl.getInstance();

    public User getUserById(int id) {
        return userDao.getUserById(id);
    }

    public int authenticate(String username, String password) {
        return userDao.authenticate(username, password);
    }

    public Set<Banknote> getUserCollection(int user_id) {
        return userDao.getUserCollection(user_id);
    }

    public boolean addBanknoteToCollection(int user_id, int banknote_id) {
        return userDao.addBanknoteToCollection(user_id, banknote_id);
    }

    public boolean removeBanknoteFromCollection(int user_id, int banknote_id) {
        return userDao.removeBanknoteFromCollection(user_id, banknote_id);
    }
}

package org.numisoft.webproject.services;

import org.numisoft.webproject.dao.UserDaoImpl;
import org.numisoft.webproject.dto.User;

/**
 * UserService is a Service class for User entity
 */
public class UserService {

    private static UserService userService;

    private UserService() {
    }

    public static UserService getInstance() {
        if (userService == null) {
            userService = new UserService();
            return userService;
        } else {
            return userService;
        }
    }

        private UserDaoImpl udi = UserDaoImpl.getInstance();

    public User getUserById(int id) {
        return udi.getUserById(id);
    }

    public int authenticate(String username, String password) {
        return udi.authenticate(username, password);
    }

}

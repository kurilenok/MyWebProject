package org.numisoft.webproject.services;

import org.numisoft.webproject.dao.UserDao;
import org.numisoft.webproject.pojos.Banknote;
import org.numisoft.webproject.pojos.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * UserServiceImpl is a Service class for User entity
 */
@Service

public class UserServiceImpl implements UserService {



    private static UserServiceImpl userServiceImpl;

    public UserServiceImpl() {
    }

    public static UserServiceImpl getInstance() {
        if (userServiceImpl == null) {
            userServiceImpl = new UserServiceImpl();
            return userServiceImpl;
        } else {
            return userServiceImpl;
        }
    }

    @Autowired
    private UserDao userDao;

    @Transactional
    public User getUserById(int id) {
        return userDao.getUserById(id);
    }

    @Transactional
    public User getUserByName(String username) {
        return userDao.getUserByName(username);
    }

    @Transactional
    public int authenticate(String username, String password) {

        User user = userDao.getUserByName(username);


        if (user == null) {
            return -1;
        } else if (password.equalsIgnoreCase(user.getPassword())) {
            return user.getId();

        } else {
            return -1;
        }

    }

    @Transactional
    public Set<Banknote> getUserCollection(int user_id) {
        return userDao.getUserCollection(user_id);
    }

    @Transactional
    public boolean addBanknoteToCollection(int user_id, int banknote_id) {
        return userDao.addBanknoteToCollection(user_id, banknote_id);
    }

    @Transactional
    public boolean removeBanknoteFromCollection(int user_id, int banknote_id) {
        return userDao.removeBanknoteFromCollection(user_id, banknote_id);
    }


}

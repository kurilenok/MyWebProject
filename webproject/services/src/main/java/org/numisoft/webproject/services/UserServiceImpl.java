package org.numisoft.webproject.services;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.numisoft.webproject.dao.UserDaoImpl;
import org.numisoft.webproject.dto.Banknote;
import org.numisoft.webproject.dto.User;
import org.numisoft.webproject.utils.HibernateUtil;

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

        HibernateUtil hibernateUtil = HibernateUtil.getHibernateUtil();
        Session session = hibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        UserDaoImpl userDao = UserDaoImpl.getInstance();

        User user = userDao.getUserByName(username, session);

        transaction.commit();
        hibernateUtil.closeSession();

        if (user == null) {
            return -1;
        } else if (password.equalsIgnoreCase(user.getPassword())) {
            return user.getId();

        } else {
            return -1;
        }

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

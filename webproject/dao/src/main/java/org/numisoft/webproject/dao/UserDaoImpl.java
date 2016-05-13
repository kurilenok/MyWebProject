package org.numisoft.webproject.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.numisoft.webproject.dto.Banknote;
import org.numisoft.webproject.dto.User;
import org.numisoft.webproject.utils.HibernateUtil;

import java.util.Set;
import java.util.TreeSet;

/**
 * Implementation of UserDao
 *
 * */

public class UserDaoImpl implements UserDao {

    private static UserDaoImpl udi;

    private UserDaoImpl() {}


    public static UserDaoImpl getInstance() {
        if (udi == null) {
            udi = new UserDaoImpl();
            return udi;
        } else {
            return udi;
        }
    }

    @Override
    public User getUserById(int id) {

        HibernateUtil hibernateUtil = HibernateUtil.getHibernateUtil();
        Session session = hibernateUtil.getSession();

        User user = (User) session.load(org.numisoft.webproject.dto.User.class, id);

        hibernateUtil.closeSession();

        return  user;
    }

    @Override
    public int authenticate(String username, String password) {

        HibernateUtil hibernateUtil = HibernateUtil.getHibernateUtil();
        Session session = hibernateUtil.getSession();

        String hql = "from User u where u.username=:uname";
        Query query = session.createQuery(hql);
        query.setParameter("uname", username);
        User u = (User) query.uniqueResult();

        hibernateUtil.closeSession();

        return u.getId();
    }

    @Override
    public Set<Banknote> getUserCollection(int user_id) {

        HibernateUtil hibernateUtil = HibernateUtil.getHibernateUtil();
        Session session = hibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        User user = (User) session.load(org.numisoft.webproject.dto.User.class, user_id);
        Set<Banknote> banknotes = new TreeSet<>(user.getBanknotes());

        transaction.commit();
        hibernateUtil.closeSession();

        return banknotes;
    }

    @Override
    public void addBanknoteToCollection(int user_id, int banknote_id) {

        HibernateUtil hibernateUtil = HibernateUtil.getHibernateUtil();
        Session session = hibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        User user = (User) session.load(org.numisoft.webproject.dto.User.class, user_id);
        Banknote banknote = (Banknote) session.load(org.numisoft.webproject.dto.Banknote.class,
                banknote_id);

        user.getBanknotes().add(banknote);

        session.persist(user);

        transaction.commit();
        hibernateUtil.closeSession();

    }

    @Override
    public void removeBanknoteFromCollection(int user_id, int banknote_id) {

        HibernateUtil hibernateUtil = HibernateUtil.getHibernateUtil();
        Session session = hibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        User user = (User) session.load(org.numisoft.webproject.dto.User.class, user_id);
        Banknote banknote = (Banknote) session.load(org.numisoft.webproject.dto.Banknote.class,
                banknote_id);

        user.getBanknotes().remove(banknote);

        session.persist(user);

        transaction.commit();
        hibernateUtil.closeSession();

    }

}

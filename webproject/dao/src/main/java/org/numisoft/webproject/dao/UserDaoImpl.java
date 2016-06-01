package org.numisoft.webproject.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.numisoft.webproject.pojos.Banknote;
import org.numisoft.webproject.pojos.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.TreeSet;

/**
 * Implementation of UserDao
 */
@Repository
public class UserDaoImpl implements UserDao {

    public UserDaoImpl() {
    }

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public User getUserById(int id) {

        Session session = sessionFactory.getCurrentSession();

        User user = (User) session.load(org.numisoft.webproject.pojos.User.class, id);
        return user;
    }

    @Override
    public User getUserByName(String userName) {

        Session session = sessionFactory.getCurrentSession();

        String hql = "from User u where u.username=:uname";
        Query query = session.createQuery(hql);
        query.setParameter("uname", userName);
        User user = (User) query.uniqueResult();

        return user;
    }


    @Override
    public Set<Banknote> getUserCollection(int user_id) {

        Session session = sessionFactory.getCurrentSession();

        User user = (User) session.load(org.numisoft.webproject.pojos.User.class, user_id);
        Set<Banknote> banknotes = new TreeSet<>(user.getBanknotes());

        return banknotes;
    }

    @Override
    public boolean addBanknoteToCollection(int user_id, int banknote_id) {

        Session session = sessionFactory.getCurrentSession();

        User user = (User) session.load(org.numisoft.webproject.pojos.User.class, user_id);
        Banknote banknote = (Banknote) session.load(org.numisoft.webproject.pojos.Banknote.class,
                banknote_id);

        boolean success = user.getBanknotes().add(banknote);

        session.persist(user);

        return success;

    }

    @Override
    public boolean removeBanknoteFromCollection(int user_id, int banknote_id) {

        Session session = sessionFactory.getCurrentSession();

        User user = (User) session.load(org.numisoft.webproject.pojos.User.class, user_id);
        Banknote banknote = (Banknote) session.load(org.numisoft.webproject.pojos.Banknote.class,
                banknote_id);

        boolean success = user.getBanknotes().remove(banknote);

        session.persist(user);

        return success;
    }

}

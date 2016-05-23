package org.numisoft.webproject.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.numisoft.webproject.pojos.Banknote;
import org.numisoft.webproject.pojos.User;
import org.numisoft.webproject.utils.HibernateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.TreeSet;

/**
 * Implementation of UserDao
 */
@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    private static UserDaoImpl userDao;

    private UserDaoImpl() {
    }


    public static UserDaoImpl getInstance() {
        if (userDao == null) {
            userDao = new UserDaoImpl();
            return userDao;
        } else {
            return userDao;
        }
    }

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

//        HibernateUtil hibernateUtil = HibernateUtil.getHibernateUtil();
//        Session session = hibernateUtil.getSession();
//        Transaction transaction = session.beginTransaction();

        Session session = sessionFactory.getCurrentSession();

        User user = (User) session.load(org.numisoft.webproject.pojos.User.class, user_id);
        Set<Banknote> banknotes = new TreeSet<>(user.getBanknotes());

//        transaction.commit();
//        hibernateUtil.closeSession();

        return banknotes;
    }

    @Override
    public boolean addBanknoteToCollection(int user_id, int banknote_id) {

//        HibernateUtil hibernateUtil = HibernateUtil.getHibernateUtil();
//        Session session = hibernateUtil.getSession();
//        Transaction transaction = session.beginTransaction();

        Session session = sessionFactory.getCurrentSession();

        User user = (User) session.load(org.numisoft.webproject.pojos.User.class, user_id);
        Banknote banknote = (Banknote) session.load(org.numisoft.webproject.pojos.Banknote.class,
                banknote_id);

        boolean sucess = user.getBanknotes().add(banknote);

        session.persist(user);

//        transaction.commit();
//        hibernateUtil.closeSession();

        return sucess;

    }

    @Override
    public boolean removeBanknoteFromCollection(int user_id, int banknote_id) {

//        HibernateUtil hibernateUtil = HibernateUtil.getHibernateUtil();
//        Session session = hibernateUtil.getSession();
//        Transaction transaction = session.beginTransaction();

        Session session = sessionFactory.getCurrentSession();

        User user = (User) session.load(org.numisoft.webproject.pojos.User.class, user_id);
        Banknote banknote = (Banknote) session.load(org.numisoft.webproject.pojos.Banknote.class,
                banknote_id);

        boolean success = user.getBanknotes().remove(banknote);

        session.persist(user);

//        transaction.commit();
//        hibernateUtil.closeSession();

        return success;
    }

}

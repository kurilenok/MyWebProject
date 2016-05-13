package org.numisoft.webproject.dao;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.numisoft.webproject.dto.Banknote;
import org.numisoft.webproject.dto.Country;
import org.numisoft.webproject.dto.User;
import org.numisoft.webproject.utils.BanknoteComparator;
import org.numisoft.webproject.utils.DataSource;
import org.numisoft.webproject.utils.HibernateUtil;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import javax.persistence.Entity;

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

/*
   public User getUserById(int id) {

        User user = new User();

        try {
            String template = "SELECT * FROM users WHERE id=" + id + ";";

            Connection connection = DataSource.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(template);

            if (result.next() == true) {
                user.setId(result.getInt("id"));
                user.setFirst_name(result.getString("first_name"));
                user.setLast_name(result.getString("last_name"));
                user.setUsername(result.getString("username"));
                user.setRole_id(result.getInt("role_id"));
            }

            result.close();
            statement.close();
            connection.close();

        } catch (SQLException|IOException|PropertyVetoException e) {
            e.printStackTrace();
        }

        return user;
    }*/

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
        List results = query.list();

        User u = (User) results.get(0);

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


   /* public int authenticate(String username, String password) {

        String correctPassword = "";
        int id = 0;

        try {

            Connection connection = DataSource.getInstance().getConnection();
            String template = "SELECT * FROM users WHERE username='" + username + "';";

            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(template);

            if (result.next() == true) {
                correctPassword = result.getString("password");
                if (correctPassword.equals(password)) {
                    id = result.getInt("id");
                }
            }

            result.close();
            statement.close();
            connection.close();

        } catch (SQLException|IOException|PropertyVetoException e) {
            e.printStackTrace();
        }

        return id;

    }*/


    /*
    @Override
    public List<Banknote> getUserCollection(User user) {

        List<Banknote> collection = new ArrayList<>();

        String template = "SELECT banknotes.id, banknotes.title, " +
                "countries.country, banknotes.link " +
                "FROM collections " +
                "JOIN banknotes ON collections.banknote_id=banknotes.id " +
                "JOIN users ON collections.user_id=users.id " +
                "JOIN countries ON banknotes.country_id=countries.id " +
                "WHERE users.id='" + user.getId() + "' " +
                "ORDER BY countries.country, " +
                "CAST(SUBSTRING(title, 1, LOCATE(' ', title)) AS UNSIGNED);";

        try {
            Connection connection = DataSource.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(template);

            while (result.next()) {
                Banknote banknote = new Banknote();
                banknote.setId(result.getInt("id"));
                banknote.setTitle(result.getString("title"));
                banknote.setCountry(new Country(result.getString("country")));
                banknote.setLink(result.getString("link"));
                collection.add(banknote);
            }

            result.close();
            statement.close();
            connection.close();

        } catch (SQLException | IOException | PropertyVetoException e) {
            e.printStackTrace();
        }

        return collection;

    }
    */


}

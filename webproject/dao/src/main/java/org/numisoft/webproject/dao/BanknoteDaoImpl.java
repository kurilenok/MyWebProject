package org.numisoft.webproject.dao;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.numisoft.webproject.dto.Banknote;
import org.numisoft.webproject.dto.Country;
import org.numisoft.webproject.dto.User;
import org.numisoft.webproject.utils.BanknoteComparator;
import org.numisoft.webproject.utils.DataSource;
import org.numisoft.webproject.utils.HibernateUtil;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.*;
import java.util.*;

/**
 * Implementation of BanknoteDao
 */

public class BanknoteDaoImpl implements BanknoteDao {

    private static BanknoteDaoImpl bdi;

    private BanknoteDaoImpl() {
    }

    public static BanknoteDaoImpl getInstance() {
        if (bdi == null) {
            bdi = new BanknoteDaoImpl();
            return bdi;
        } else {
            return bdi;
        }
    }

    public Banknote getBanknoteById(int id) {

        Banknote banknote = new Banknote();

        try {
            String template = "SELECT banknotes.id, banknotes.nominal, banknotes.title, " +
                    "banknotes.link, countries.country " +
                    "FROM banknotes " +
                    "JOIN countries ON banknotes.country_id = countries.id " +
                    "WHERE banknotes.id =" + id + ";";

            Connection connection = DataSource.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(template);

            while (result.next()) {
                banknote.setId(result.getInt("id"));
                banknote.setNominal(result.getInt("nominal"));
                banknote.setTitle(result.getString("title"));
                banknote.setCountry(new Country(result.getString("country")));
                banknote.setLink(result.getString("link"));
            }

            result.close();
            statement.close();
            connection.close();

        } catch (SQLException | IOException | PropertyVetoException e) {
            e.printStackTrace();
        }

        return banknote;

    }

    @Override
    public long calculateMaxPages() {

        HibernateUtil hibernateUtil =
                HibernateUtil.getHibernateUtil();
        Session session = hibernateUtil.getSession();

        Transaction transaction = session.beginTransaction();

        Criteria c = session.createCriteria(org.numisoft.webproject.dto.Banknote.class);
        c.setProjection(Projections.rowCount());

        long result = (long) c.uniqueResult();

        long maxPages = (result % 10 == 0) ?
                (result / 10) : (result / 10 + 1);

        transaction.commit();
        hibernateUtil.closeSession();

        return maxPages;
    }

    @Override
    public Set<Banknote> getAllBanknotes(int currentPage) {

        HibernateUtil hibernateUtil = HibernateUtil.getHibernateUtil();
        Session session = hibernateUtil.getSession();

        Transaction transaction = session.beginTransaction();

        Criteria c = session.createCriteria(org.numisoft.webproject.dto.Banknote.class);
        c.addOrder(Order.asc("country"));
        c.addOrder(Order.asc("nominal"));

        c.setFirstResult((currentPage - 1) * 10);
        c.setMaxResults(10);
        Set<Banknote> banknotes = new TreeSet<>(c.list());


        transaction.commit();

        hibernateUtil.closeSession();

        return banknotes;
    }

    /*
    public List<Banknote> getAllBanknotes() {


        List<Banknote> banknotes = new ArrayList<>();

        try {
            String template = "SELECT banknotes.id, banknotes.title, " +
                    "banknotes.link, countries.country " +
                    "FROM banknotes " +
                    "JOIN countries ON banknotes.country_id = countries.id " +
                    "ORDER BY countries.country, " +
                    "CAST(SUBSTRING(title, 1, LOCATE(' ', title)) AS UNSIGNED);";

            Connection connection = DataSource.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(template);

            while (result.next()) {
                Banknote banknote = new Banknote();
                banknote.setId(result.getInt("id"));
                banknote.setTitle(result.getString("title"));
                banknote.setCountry(new Country(result.getString("country")));
                banknote.setLink(result.getString("link"));
                banknotes.add(banknote);
            }

            result.close();
            statement.close();
            connection.close();

        } catch (SQLException | IOException | PropertyVetoException e) {
            e.printStackTrace();
        }

        return banknotes;
    }
    */


    @Override
    public void addBanknoteToCatalog(String title, int nominal, String country, String link) {

        HibernateUtil hibernateUtil = HibernateUtil.getHibernateUtil();
        Session session = hibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        Criteria c = session.createCriteria(org.numisoft.webproject.dto.Country.class);
        c.add(Restrictions.like("country", country));

        Country country1;
        List countries = (ArrayList<Country>) c.list();

        if (countries.size() != 0) {
            int country_id = ((Country) countries.get(0)).getId();
            country1 = (Country) session.load(org.numisoft.webproject.dto.Country.class, country_id);
        } else {
            country1 = new Country();
            country1.setCountry(country);
        }

        Banknote banknote = new Banknote();
        banknote.setTitle(title);
        banknote.setNominal(nominal);
        banknote.setCountry(country1);
        banknote.setLink(link);

//        session.persist(country1);
        session.persist(banknote);

        transaction.commit();
        hibernateUtil.closeSession();


    }

    public void _addBanknoteToCatalog(String title, int nominal, String country, String link) {

        try {
            String template = "INSERT INTO countries (country) VALUES ('" + country + "');";

            Connection connection = DataSource.getInstance().getConnection();
            Statement statement = connection.createStatement();

            try {
                statement.executeUpdate(template);
            } catch (SQLIntegrityConstraintViolationException e) {
                e.printStackTrace();
            }

            String template2 = "INSERT INTO banknotes (title, nominal, link, country_id) " +
                    "VALUES ('" + title + "', " + nominal + ", '" + link + "', " +
                    "(SELECT id FROM countries WHERE country like '" + country + "'));";

            statement.executeUpdate(template2);

            statement.close();
            connection.close();

        } catch (SQLException | IOException | PropertyVetoException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void removeBanknoteFromCatalog(int id) {

        HibernateUtil hibernateUtil = HibernateUtil.getHibernateUtil();
        Session session = hibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        Banknote banknote = (Banknote) session.load(org.numisoft.webproject.dto.Banknote.class, id);
        session.delete(banknote);

        transaction.commit();
        hibernateUtil.closeSession();
    }



    /*    public void removeBanknoteFromCatalog(int id) {

        try {
            String template = "DELETE FROM banknotes WHERE id=" + id + ";";

            Connection connection = DataSource.getInstance().getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(template);

            statement.close();
            connection.close();

        } catch (SQLException | IOException | PropertyVetoException e) {
            e.printStackTrace();
        }
    }*/

    @Override
    public void addBanknoteToCollection(int user_id, int banknote_id) {

        String template = "INSERT INTO collections (user_id, banknote_id) "
                + "VALUES (" + user_id + ", " + banknote_id + ");";

        try {
            Connection connection = DataSource.getInstance().getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(template);

            statement.close();
            connection.close();

        } catch (SQLException | IOException | PropertyVetoException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void removeBanknoteFromCollection(int user_id, int banknote_id) {

        String template = "DELETE FROM collections WHERE user_id = " + user_id +
                " AND banknote_id = " + banknote_id + ";";

        try {
            Connection connection = DataSource.getInstance().getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(template);

            statement.close();
            connection.close();

        } catch (SQLException | IOException | PropertyVetoException e) {
            e.printStackTrace();
        }

    }

}

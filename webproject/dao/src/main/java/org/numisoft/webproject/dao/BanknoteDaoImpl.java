package org.numisoft.webproject.dao;

import org.numisoft.webproject.dto.Banknote;
import org.numisoft.webproject.utils.DataSource;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of BanknoteDao
 *
 * */

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

    public Banknote getBanknoteByid(int id) {

        Banknote banknote = new Banknote();

        try {
            String template = "SELECT banknotes.id, banknotes.title, " +
                    "banknotes.link, countries.country " +
                    "FROM banknotes " +
                    "JOIN countries ON banknotes.country_id = countries.id " +
                    "WHERE banknotes.id =" + id + ";";

            Connection connection = DataSource.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(template);

            while (result.next()) {
                banknote.setId(result.getInt("id"));
                banknote.setTitle(result.getString("title"));
                banknote.setCountry(result.getString("country"));
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

    public List<Banknote> getAllBanknotes() {

        List<Banknote> banknotes = new ArrayList<Banknote>();

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
                banknote.setCountry(result.getString("country"));
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


    public void addBanknote(String title, String country, String link) {

        try {
            String template = "INSERT INTO countries (country) VALUES ('" + country + "');";

            Connection connection = DataSource.getInstance().getConnection();
            Statement statement = connection.createStatement();

            try {
                statement.executeUpdate(template);
            } catch (SQLIntegrityConstraintViolationException e) {
                e.printStackTrace();
            }

            String template2 = "INSERT INTO banknotes (title, link, country_id) " +
                    "VALUES ('" + title + "', '" + link + "', " +
                    "(SELECT id FROM countries WHERE country like '" + country + "'));";

            statement.executeUpdate(template2);

            statement.close();
            connection.close();

        } catch (SQLException | IOException | PropertyVetoException e) {
            e.printStackTrace();
        }

    }


    public void deleteBanknote(int id) {

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

    }

}

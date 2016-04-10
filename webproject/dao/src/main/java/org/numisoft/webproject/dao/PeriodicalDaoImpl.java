package org.numisoft.webproject.dao;

import org.numisoft.webproject.dto.Periodical;
import org.numisoft.webproject.utils.DataSource;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PeriodicalDaoImpl implements PeriodicalDao {

    public Periodical getPeriodicalByid(int id) {
        return null;
    }

    public List<Periodical> getAllPeriodicals() {

        List<Periodical> periodicals = new ArrayList<Periodical>();

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
                Periodical periodical = new Periodical();
                periodical.setId(result.getInt("id"));
                periodical.setTitle(result.getString("title"));
                periodical.setCountry(result.getString("country"));
                periodical.setLink(result.getString("link"));
                periodicals.add(periodical);
            }

            result.close();
            statement.close();
            connection.close();

        } catch (SQLException | IOException | PropertyVetoException e) {
            e.printStackTrace();
        }

        return periodicals;
    }


    public void addPeriodical(String title, String country, String link) {

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


    public void deletePeriodical(int id) {

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

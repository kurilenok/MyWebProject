package org.numisoft.webproject.dao;

import org.numisoft.webproject.dto.Subscription;
import org.numisoft.webproject.dto.User;
import org.numisoft.webproject.utils.DataSource;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SubscriptionDaoImpl implements SubscriptionDao {

    public List<Subscription> getSubscriptionByUser(User user) {

        List<Subscription> subscriptions = new ArrayList<Subscription>();

        String template = "SELECT collections.id, banknotes.title, " +
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
                Subscription subscription = new Subscription();
                subscription.setId(result.getInt("id"));
                subscription.setTitle(result.getString("title"));
                subscription.setCountry(result.getString("country"));
                subscription.setLink(result.getString("link"));
                subscriptions.add(subscription);
            }

            result.close();
            statement.close();
            connection.close();

        } catch (SQLException | IOException | PropertyVetoException e) {
            e.printStackTrace();
        }

        return subscriptions;

    }

    public void addSubscription(int user_id, int periodical_id) {

        String template = "INSERT INTO collections (date, user_id, banknote_id) "
                + "VALUES ('2015-12-11',"
                + user_id + "," + periodical_id + ");";

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

    public void removeSubscription(int id) {

        String template = "DELETE FROM collections WHERE id=" + id + ";";

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

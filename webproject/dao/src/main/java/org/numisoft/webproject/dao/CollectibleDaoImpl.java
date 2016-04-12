package org.numisoft.webproject.dao;

import org.numisoft.webproject.dto.Collectible;
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

public class CollectibleDaoImpl implements CollectibleDao {

    private static CollectibleDaoImpl cdi;

    private CollectibleDaoImpl() {
    }

    public static CollectibleDaoImpl getInstance() {
        if (cdi == null) {
            cdi = new CollectibleDaoImpl();
            return cdi;
        } else {
            return cdi;
        }
    }


    public List<Collectible> getCollectiblesByUser(User user) {

        List<Collectible> collectibles = new ArrayList<Collectible>();

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
                Collectible collectible = new Collectible();
                collectible.setId(result.getInt("id"));
                collectible.setTitle(result.getString("title"));
                collectible.setCountry(result.getString("country"));
                collectible.setLink(result.getString("link"));
                collectibles.add(collectible);
            }

            result.close();
            statement.close();
            connection.close();

        } catch (SQLException | IOException | PropertyVetoException e) {
            e.printStackTrace();
        }

        return collectibles;

    }

    public void addCollectible(int user_id, int banknote_id) {

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

    public void removeCollectible(int id) {

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

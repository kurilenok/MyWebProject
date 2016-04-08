package org.numisoft.webproject.dao;

import org.numisoft.webproject.dto.User;
import org.numisoft.webproject.utils.DataSource;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDaoImpl implements UserDao {

    public User getUserById(int id) {

        User user = new User();

        try {
            String template = "select * from users where id=" + id + ";";

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
    }


    public int authenticate(String username, String password) {

        String correctPassword = "";
        int id = 0;

        try {

            Connection connection = DataSource.getInstance().getConnection();
            String template = "select * from users where username='" + username + "';";

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

    }


}

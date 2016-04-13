package org.numisoft.webproject.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * DataSource provides connection to Database
 * Database credentials are stored in /dao/src/main/resources/credentials.properties
 *
 */
public class DataSource {

    private static DataSource datasource;
    private ComboPooledDataSource cpds;
    ResourceBundle resourceBundle = ResourceBundle.getBundle("credentials");

    private DataSource() throws IOException, SQLException, PropertyVetoException {
        cpds = new ComboPooledDataSource();
        cpds.setDriverClass("com.mysql.jdbc.Driver");
        cpds.setJdbcUrl(resourceBundle.getString("url"));
        cpds.setUser(resourceBundle.getString("user"));
        cpds.setPassword(resourceBundle.getString("password"));
    }

    public static DataSource getInstance() throws IOException, SQLException, PropertyVetoException {
        if (datasource == null) {
            datasource = new DataSource();
            return datasource;
        } else {
            return datasource;
        }
    }

    public Connection getConnection() throws SQLException {
        return this.cpds.getConnection();
    }

}

package org.numisoft.webproject.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by kukolka on 04.04.16.
 */
public class DataSource {

    private static DataSource datasource;
    private ComboPooledDataSource cpds;

    private DataSource() throws IOException, SQLException, PropertyVetoException {
        cpds = new ComboPooledDataSource();
        cpds.setDriverClass("com.mysql.jdbc.Driver"); //loads the jdbc driver
        cpds.setJdbcUrl("jdbc:mysql://localhost:3306/periodicals");
        cpds.setUser("root");
        cpds.setPassword("root");
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

package org.example.DAOs;

import org.example.Exceptions.DaoException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlDao {

    String driver = "com.mysql.cj.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/budget_database";
    String username = "root";
    String password = "";

    public Connection getConnection() throws DaoException {
        Connection connection = null;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            throw new DaoException("Database driver not found: " + e.getMessage());
        } catch (SQLException e) {
            throw new DaoException("Connection failed: " + e.getMessage());
        }
        return connection;
    }

    public void freeConnection(Connection connection) throws DaoException {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new DaoException("Failed to free connection: " + e.getMessage());
        }
    }
}
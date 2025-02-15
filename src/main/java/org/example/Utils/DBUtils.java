package org.example.Utils;

import java.sql.*;

public class DBUtils {
    public static void closeResources(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) throws SQLException {
        if (resultSet != null) {
            resultSet.close();
        }
        if (preparedStatement != null) {
            preparedStatement.close();
        }
        if (connection != null) {
            connection.close();  // No need to call freeConnection() manually
        }
    }

    public static void closeResources(Connection connection, PreparedStatement preparedStatement) throws SQLException {
        closeResources(connection, preparedStatement, null);
    }
}
package org.example.DAOs;

import org.example.DTOs.Income;
import org.example.Exceptions.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class MySqlIncomeDao extends MySqlDao implements IncomeDaoInterface {
Connection connection = null;
PreparedStatement preparedStatement = null;
ResultSet resultSet = null;

/*
try{
    connection = this.getConnection();
        String query = "SELECT * FROM USER";
        preparedStatement = connection.prepareStatement(query);

        resultSet = preparedStatement.executeQuery();
        while(resultSet.next())
        {
            int incomeID = resultSet.getInt("INCOME_ID");
            String title = resultSet.getString("TITLE");
            double amount = resultSet.getDouble("AMOUNT");
            Date dateEarned = resultSet.getDate("DATE_EARNED");
        }
    }

    catch(SQLException e){
    throw new DaoException("findAllIncomeResultSet"+e.getMessage());
    }
finally{
        try
        {
            if (resultSet != null)
            {
                resultSet.close();
            }
            if (preparedStatement != null)
            {
                preparedStatement.close();
            }
            if (connection != null)
            {
                freeConnection(connection);
            }
        }
        catch (SQLException e)
        {
            throw new DaoException("findAllUsers() " + e.getMessage());
        }
    }
        return a list probably like: incomeList;     // may be empty
    }

    @Override
    public List<Income> getAllIncome() throws SQLException {
        return null;
    }

    @Override
    public double getTotalEarned() throws SQLException {
        return 0;
    }

    @Override
    public void addIncome(int incomeD, String title, double amount, Date dateEarned) {

    }

    @Override
    public boolean deleteIncome(int incomeID) {
        return false;
    }

    */

}

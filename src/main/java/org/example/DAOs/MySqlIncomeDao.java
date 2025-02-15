package org.example.DAOs;

import org.example.DTOs.Income;
import org.example.Exceptions.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlIncomeDao extends MySqlDao implements IncomeDaoInterface {

    @Override
    public List<Income> getAllIncomes() throws DaoException {
        List<Income> incomes = new ArrayList<>();
        String query = "SELECT * FROM incomes";

        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Income income = new Income(
                        resultSet.getInt("incomeID"),
                        resultSet.getString("title"),
                        resultSet.getDouble("amount"),
                        resultSet.getDate("dateEarned").toLocalDate()
                );
                incomes.add(income);
            }
        } catch (SQLException e) {
            throw new DaoException("Error retrieving incomes: " + e.getMessage());
        }
        return incomes;
    }
/*
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

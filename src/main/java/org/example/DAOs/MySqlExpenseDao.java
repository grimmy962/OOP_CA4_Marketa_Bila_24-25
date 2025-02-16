package org.example.DAOs;

import org.example.DTOs.Expense;
import org.example.Exceptions.DaoException;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MySqlExpenseDao extends MySqlDao implements ExpenseDaoInterface {

    @Override
    public List<Expense> getAllExpenses() throws DaoException {
        List<Expense> expenses = new ArrayList<>();
        String query = "SELECT * FROM expenses";

        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Expense expense = new Expense(
                        resultSet.getInt("expenseID"),
                        resultSet.getString("title"),
                        resultSet.getString("category"),
                        resultSet.getDouble("amount"),
                        resultSet.getDate("dateIncurred").toLocalDate()
                );
                expenses.add(expense);
            }
        } catch (SQLException e) {
            throw new DaoException("Error retrieving expenses: " + e.getMessage());
        }
        return expenses;
    }

    @Override
    public double getTotalSpend() throws DaoException {
        String query = "SELECT SUM(amount) FROM expenses";
        double totalSpend = 0;

        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery();
        ) {
            if (resultSet.next()) {
                totalSpend = resultSet.getDouble(1);
            }
        } catch (SQLException e) {
            throw new DaoException("Error calculating total spend: " + e.getMessage());
        }
        return totalSpend;
    }


    @Override
    public void addExpense(int id, String title, String category, double amount, LocalDate dateIncurred) throws DaoException {
        String query = "INSERT INTO expenses (expenseID, title, category, amount, dateIncurred) VALUES (?, ?, ?, ?, ?)";

        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setDouble(1, id);
            preparedStatement.setString(2, title);
            preparedStatement.setString(3, category);
            preparedStatement.setDouble(4, amount);
            preparedStatement.setDate(5, java.sql.Date.valueOf(dateIncurred));

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Expense was added");
            } else {
                System.out.println("Failed to add the expense");
            }
        } catch (SQLException e) {
            throw new DaoException("Error adding expense: " + e.getMessage());
        }
    }

    @Override
    public boolean deleteExpense(int expenseID) throws DaoException {
        String query = "DELETE FROM expenses WHERE expenseID = ?";
        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            preparedStatement.setInt(1, expenseID);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public double getTotalExpenseByMonth(int year, int month) throws DaoException{
        String query = "SELECT SUM(amount) FROM expenses WHERE YEAR(dateIncurred) = ? AND MONTH(dateIncurred) = ?";
        double totalExpense = 0;

        try(
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        ){
            preparedStatement.setInt(1, year);
            preparedStatement.setInt(2, month);

            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    totalExpense = resultSet.getDouble(1);
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Error retrieving the total expenses" + e.getMessage());
        }
        return totalExpense;
    }
}
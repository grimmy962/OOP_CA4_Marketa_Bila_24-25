package org.example.DAOs;

import org.example.DTOs.Expense;
import org.example.Exceptions.DaoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlExpenseDao extends MySqlDao implements ExpenseDaoInterface {

    @Override
    public List<Expense> getAllExpenses() throws DaoException {
        List<Expense> expenses = new ArrayList<>();
        String query = "SELECT * FROM expenses";

        try (Connection connection = getConnection();
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
        }
        catch (SQLException e) {
            throw new DaoException("Error retrieving expenses: " + e.getMessage());
        }
        return expenses;
    }

        @Override
        public double getTotalSpend() throws DaoException{
            String query = "SELECT SUM(amount) FROM expenses";
            double totalSpend = 0;

            try(
                    Connection connection = getConnection();
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    ){
                if(resultSet.next()){
                    totalSpend = resultSet.getDouble("Total: ");
                }
            }
            catch (SQLException e){
                throw new DaoException("Error calculating total spend: " + e.getMessage());
            }
            return totalSpend;
        }
/*
    @Override
    public void addExpense(int expenseID, String title, String category, double amount, Date dateIncurred) throws DaoException{

    }

    @Override
    public boolean deleteExpense(int expenseID) throws DaoException{
        return false;
    }


 */
}

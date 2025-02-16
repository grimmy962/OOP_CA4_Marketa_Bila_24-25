package org.example.DAOs;

import org.example.DTOs.Income;
import org.example.Exceptions.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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
    @Override
    public double getTotalEarned() throws DaoException {
        String query = "SELECT SUM(amount) FROM incomes";
        double totalGained = 0;

        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery();
        ) {
            if (resultSet.next()) {
                totalGained = resultSet.getDouble(1);
            }
        } catch (SQLException e) {
            throw new DaoException("Error calculating total gained: " + e.getMessage());
        }
        return totalGained;
    }

    @Override
    public void addIncome(int id, String title, double amount, LocalDate dateEarned) throws DaoException{
        String query = "INSERT INTO incomes (incomeID, title, amount, dateEarned) VALUES (?, ?, ?, ?)";

        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, title);
            preparedStatement.setDouble(3, amount);
            preparedStatement.setDate(4, java.sql.Date.valueOf(dateEarned));

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Income was added");
            } else {
                System.out.println("Failed to add the income");
            }
        } catch (SQLException e) {
            throw new DaoException("Error adding income: " + e.getMessage());
        }
    }

    @Override
    public boolean deleteIncome(int incomeID) throws DaoException{
        String query = "DELETE FROM incomes WHERE incomeID = ?";
        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            preparedStatement.setInt(1, incomeID);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public double getTotalIncomeByMonth(int year, int month) throws DaoException{
        String query = "SELECT SUM(amount) FROM incomes WHERE YEAR(dateEarned) = ? AND MONTH(dateEarned) = ?";
        double totalIncome = 0;

        try(
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
        ){
           preparedStatement.setInt(1, year);
           preparedStatement.setInt(2, month);

           try(ResultSet resultSet = preparedStatement.executeQuery()){
               if(resultSet.next()){
                   totalIncome = resultSet.getDouble(1);
               }
           }
        } catch (SQLException e) {
            throw new DaoException("Error retrieving the total incomes: "+e.getMessage());
        }
        return totalIncome;
    }

}

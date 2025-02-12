package org.example.DAOs;

import org.example.DTOs.Expense;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public interface ExpenseDaoInterface {
    List<Expense> getAllExpense() throws SQLException;
    double getTotalSpend() throws SQLException;
    void addExpense(int expenseID, String title, String category, double amount, Date dateIncurred);
    boolean deleteExpense(int expenseID);
}

package org.example.DAOs;

import org.example.DTOs.Expense;
import org.example.Exceptions.DaoException;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public interface ExpenseDaoInterface {
    List<Expense> getAllExpenses() throws DaoException;

    double getTotalSpend() throws DaoException;
/*
    void addExpense(int expenseID, String title, String category, double amount, Date dateIncurred) throws DaoException;
    boolean deleteExpense(int expenseID) throws DaoException;
*/
}


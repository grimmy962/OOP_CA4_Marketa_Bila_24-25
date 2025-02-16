package org.example.DAOs;

import org.example.DTOs.Income;
import org.example.Exceptions.DaoException;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface IncomeDaoInterface {
    List<Income> getAllIncomes() throws DaoException;

    double getTotalEarned() throws DaoException;

    void addIncome(int id, String title, double amount, LocalDate dateEarned) throws DaoException;

    boolean deleteIncome(int incomeID) throws DaoException;

}

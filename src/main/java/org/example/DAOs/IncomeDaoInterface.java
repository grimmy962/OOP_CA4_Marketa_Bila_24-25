package org.example.DAOs;

import org.example.DTOs.Income;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public interface IncomeDaoInterface {
    List<Income> getAllIncome() throws SQLException;
    double getTotalEarned() throws SQLException;
    void addIncome(int incomeD, String title, double amount, Date dateEarned);
    boolean deleteIncome(int incomeID);
}

package org.example.DTOs;


import java.time.LocalDate;

public class Income {
    private int incomeID;
    private String title;
    private double amount;
    private LocalDate dateEarned;

    public Income(int incomeID, String title, double amount, LocalDate dateEarned) {
        this.incomeID = incomeID;
        this.title = title;
        this.amount = amount;
        this.dateEarned = dateEarned;
    }

    public Income() {

    }

    public int getIncomeID() {
        return incomeID;
    }

    public void setIncomeID(int incomeID) {
        this.incomeID = incomeID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getDateEarned() {
        return dateEarned;
    }

    public void setDateEarned(LocalDate dateEarned) {
        this.dateEarned = dateEarned;
    }

    @Override
    public String toString() {
        return "Income{" +
                "incomeID=" + incomeID +
                ", title='" + title + '\'' +
                ", amount=" + amount +
                ", dateEarned=" + dateEarned +
                '}';
    }
}

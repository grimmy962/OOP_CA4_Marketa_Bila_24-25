package org.example.DTOs;

import java.time.LocalDate;

public class Expense {
    private int expenseId;
    private String title;
    private String category;
    private double amount;
    private LocalDate dateIncurred;

    public Expense(int expenseId, String title, String category, double amount, LocalDate dateIncurred) {
        this.expenseId = expenseId;
        this.title = title;
        this.category = category;
        this.amount = amount;
        this.dateIncurred = dateIncurred;
    }

    public Expense() {
    }

    public int getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(int expenseId) {
        this.expenseId = expenseId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return dateIncurred;
    }

    public void setDate(LocalDate date) {
        this.dateIncurred = date;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "expenseId=" + expenseId +
                ", title='" + title + '\'' +
                ", category='" + category + '\'' +
                ", amount=" + amount +
                ", date=" + dateIncurred +
                '}';
    }
}

package org.example.BusinessObjects;

import org.example.DAOs.MySqlExpenseDao;
import org.example.DAOs.MySqlIncomeDao;
import org.example.DTOs.Expense;
import org.example.DTOs.Income;
import org.example.Exceptions.DaoException;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class AppMain {

    //make main app more like a menu type of thing, where u can ask usesr for input
    private MySqlExpenseDao expenseDAO = new MySqlExpenseDao();
    private MySqlIncomeDao incomeDAO = new MySqlIncomeDao();
    private Scanner keyboard = new Scanner(System.in);

    public static void main(String[] args) {
        new AppMain().run();
    }

    public void run() {
        while (true) {
            System.out.println("Income and Expenses Manager");
            System.out.println("Menu:");
            System.out.println("1) View all expenses");
            System.out.println("2) Add expenses");
            System.out.println("3) Delete expenses");
            System.out.println("4) View incomes");
            System.out.println("5) Add incomes");
            System.out.println("6) Delete incomes");
            System.out.println("7) View the total spend");
            System.out.println("8) View the total gained");
            System.out.println("9) View income and expenses for a specific month");
            System.out.println("10) EXIT");
            System.out.println("\n Enter your input: ");

            int input = keyboard.nextInt();
            keyboard.nextLine();

            switch (input) {
                case 1 -> getAllExpenses();
                case 2 -> addExpense();
                case 3 -> deleteExpense();
                case 4 -> getAllIncomes();
                case 5 -> addIncome();
                case 6 -> deleteIncome();
                case 7 -> getTotalSpend();
                case 8 -> getTotalGained();
                case 9 -> getBudgetByMonth();
                case 10 -> {
                    System.out.println("Finished");
                    return;
                }
                default -> System.out.println("Invalid input");
            }
        }
    }

    private void getAllExpenses() {
        try {
            List<Expense> expenses = expenseDAO.getAllExpenses();

            if (expenses.isEmpty()) {
                System.out.println("No expenses");
            } else {
                expenses.forEach(System.out::println);
            }
        } catch (DaoException e) {
            System.out.println("Error: " + e.getMessage());
            ;
        }
    }

    private void addExpense() {
        System.out.println("Please enter expense ID: ");
        int id = keyboard.nextInt();
        keyboard.nextLine();

        System.out.println("Please enter the expense title: ");
        String title = keyboard.nextLine();

        System.out.println("Please enter the expense category: ");
        String category = keyboard.nextLine();

        System.out.println("Please enter the amount: ");
        double amount = keyboard.nextDouble();
        keyboard.nextLine();

        System.out.println("Please enter the date (YYYY-MM-DD): ");
        LocalDate dateIncurred = LocalDate.parse(keyboard.nextLine());

        try {
            expenseDAO.addExpense(id, title, category, amount, dateIncurred);
            System.out.println("Expense successfully added.");
        } catch (DaoException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void deleteExpense(){
        System.out.println("Please enter the expense ID to delete: ");
        int expenseID = keyboard.nextInt();

        try{
            boolean itWorks = expenseDAO.deleteExpense(expenseID);
            if(itWorks){
                System.out.println("Expense was deleted successfully");
            }
            else{
                System.out.println("No expense was found");
            }
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
    }

    private void getAllIncomes(){
        try {
            List<Income> incomes = incomeDAO.getAllIncomes();

            if (incomes.isEmpty()) {
                System.out.println("No expenses");
            } else {
                incomes.forEach(System.out::println);
            }
        } catch (DaoException e) {
            System.out.println("Error: " + e.getMessage());

        }
    }

    private void addIncome(){
        System.out.println("Please enter income ID: ");
        int id = keyboard.nextInt();
        keyboard.nextLine();

        System.out.println("Please enter the income title: ");
        String title = keyboard.nextLine();


        System.out.println("Please enter the amount: ");
        double amount = keyboard.nextDouble();
        keyboard.nextLine();

        System.out.println("Please enter the date (YYYY-MM-DD): ");
        LocalDate dateIncurred = LocalDate.parse(keyboard.nextLine());

        try {
            incomeDAO.addIncome(id, title, amount, dateIncurred);
        } catch (DaoException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    private void deleteIncome(){
        System.out.println("Please enter the income ID to income: ");
        int incomeID = keyboard.nextInt();

        try{
            boolean itWorks = incomeDAO.deleteIncome(incomeID);
            if(itWorks){
                System.out.println("Income was deleted successfully");
            }
            else{
                System.out.println("No income was found");
            }
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
    }

    private void getTotalSpend(){
        try{
            double totalExpenses = expenseDAO.getTotalSpend();
            System.out.println("Total Spent: "+totalExpenses);
        } catch (DaoException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void getTotalGained(){
        try{
            double totalIncomes = incomeDAO.getTotalEarned();
            System.out.println("Total earned: "+totalIncomes);
        } catch (DaoException e) {
            System.out.println("Error: "+e.getMessage());
        }
    }

    private void getBudgetByMonth(){
        System.out.println("Please enter the year YYYY: ");
        int year = keyboard.nextInt();
        keyboard.nextLine();
        System.out.println("Please enter the month MM: ");
        int month = keyboard.nextInt();
        keyboard.nextLine();

        try{
            double totalIncome = incomeDAO.getTotalIncomeByMonth(year, month);
            double totalExpense = expenseDAO.getTotalExpenseByMonth(year, month);
            double budget = totalIncome - totalExpense;

            System.out.println("Summary of a budget for "+year+"-"+ String.format("%02d", month) + ":");
            System.out.println("Total earned: "+totalIncome);
            System.out.println("Total spend: "+totalExpense);
            System.out.println("Balance left: "+budget+"\n");
        } catch (DaoException e) {
            System.out.println("Error: "+e.getMessage());
            }
    }
}
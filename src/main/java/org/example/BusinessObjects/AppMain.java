package org.example.BusinessObjects;

import org.example.DAOs.MySqlExpenseDao;
import org.example.DAOs.MySqlIncomeDao;
import org.example.DTOs.Expense;
import org.example.Exceptions.DaoException;

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

    public void run(){
        System.out.println("Income and Expenses Manager");
        System.out.println("Menu:");
        System.out.println("1) View all expenses");
        System.out.println("2) Add expenses");
        System.out.println("3) Delete expenses");
        System.out.println("4) View incomes");
        System.out.println("5) Add incomes");
        System.out.println("6) Delete incomes");
        System.out.println("7) View income and expenses for a specific month");
        System.out.println("8) EXIT");
        System.out.println("\n Enter your input: ");

        int input = keyboard.nextInt();
        keyboard.nextLine();

        switch (input){
            case 1 -> getAllExpenses();
           /* case 2-> addExpense();
            case 3 -> deleteExpense();
            case 4-> getAllIncomes();
            case 5-> addIncome();
            case 6 -> deleteIncome();
            case 7 -> getBudgetMyMonth();
            */case 2 -> {
                System.out.println("Finished");
                return;
            }
            default -> System.out.println("Invalid input");
        }
    }

private void getAllExpenses(){
    try{
        List<Expense> expenses = expenseDAO.getAllExpenses();

        if(expenses.isEmpty()){
            System.out.println("No expenses");
        }
        else{
            expenses.forEach(System.out::println);
        }
    } catch (DaoException e) {
        System.out.println("Error: " + e.getMessage());;
    }
}

}
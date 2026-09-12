package com.practice.intern.ET.Expense.Tracker.API.Exception;

public class ExpenseNotFoundException extends RuntimeException{
    public ExpenseNotFoundException(String message){
        super(message);
    }
}

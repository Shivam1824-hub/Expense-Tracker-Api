package com.practice.intern.ET.Expense.Tracker.API.Service;

import com.practice.intern.ET.Expense.Tracker.API.Model.Expense;
import com.practice.intern.ET.Expense.Tracker.API.Repository.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {
    private final ExpenseRepository repository;

    public ExpenseService(ExpenseRepository repository){
        this.repository = repository;
    }

    public Expense addExpense(Expense expense){
    return repository.save(expense);
    }

    public List<Expense> findAllExpenses(){
        return repository.findAll();
    }

    public Expense findByIdExpense(Long id){
        return repository.findById(id).orElseThrow(); // i will add exception layer then add that feature
    }

}

package com.practice.intern.ET.Expense.Tracker.API.Service;

import com.practice.intern.ET.Expense.Tracker.API.Exception.ExpenseNotFoundException;
import com.practice.intern.ET.Expense.Tracker.API.Model.Expense;
import com.practice.intern.ET.Expense.Tracker.API.Repository.ExpenseRepository;
import com.practice.intern.ET.Expense.Tracker.API.dto.ExpenseRequestDto;
import com.practice.intern.ET.Expense.Tracker.API.dto.ExpenseResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {
    private final ExpenseRepository repository;

    public ExpenseService(ExpenseRepository repository){
        this.repository = repository;
    }

    public ExpenseResponseDto addExpense(ExpenseRequestDto requestDto){
        Expense expense = new Expense();
        expense.setItem(requestDto.getItem());
        expense.setCategory(requestDto.getCategory());
        expense.setQuantityValue(requestDto.getQuantityValue());
        expense.setAmount(requestDto.getAmount());
        Expense saved = repository.save(expense);
        return new ExpenseResponseDto(saved.getId(), saved.getItem(), saved.getCategory(),saved.getQuantityValue(), saved.getAmount());
    }

    public List<Expense> findAllExpenses(){
        return repository.findAll();
    }

    public Expense findByIdExpense(Long id){
        return repository.findById(id).orElseThrow(() -> new ExpenseNotFoundException("Expense data not found with id "+id)); // i will add exception layer then add that feature
    }

    public Expense updateByIdExpense(Long id, Expense updateInfo){
        Expense ex= repository.findById(id).orElseThrow(()->new ExpenseNotFoundException("Expense data not found with id "+id));
        ex.setItem(updateInfo.getItem());
        ex.setCategory(updateInfo.getCategory());
        ex.setQuantityValue(updateInfo.getQuantityValue());
        ex.setAmount(updateInfo.getAmount());
        return repository.save(ex);
    }

    public void deleteExpense(Long id){
        if(!repository.existsById(id)){
            throw new ExpenseNotFoundException("Expense data not found with id "+id);
        }repository.deleteById(id);
    }

}

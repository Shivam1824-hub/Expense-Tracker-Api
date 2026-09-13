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
        Expense expense = Expense.builder()
                .item(requestDto.getItem())
                .category(requestDto.getCategory())
                .quantityValue(requestDto.getQuantityValue())
                .amount(requestDto.getAmount())
                .build();

        Expense added = repository.save(expense);
        return new ExpenseResponseDto(added.getId(), added.getItem(), added.getCategory(),added.getQuantityValue(), added.getAmount());
    }

    public List<ExpenseResponseDto> findAllExpenses(){
         List<Expense> findall = repository.findAll();
         return findall.stream().map(
                 ex-> ExpenseResponseDto.builder().id(ex.getId()).item(ex.getItem())
                         .quantityValue(ex.getQuantityValue()).category(ex.getCategory()).amount(ex.getAmount()).build()).toList();
    }

    public ExpenseResponseDto findByIdExpense(Long id){
        Expense findId = repository.findById(id).orElseThrow(() -> new ExpenseNotFoundException("Expense data not found with id "+id));
        return new ExpenseResponseDto(findId.getId(),findId.getItem(), findId.getCategory(),findId.getQuantityValue(), findId.getAmount());
    }

    public ExpenseResponseDto updateByIdExpense(Long id, ExpenseRequestDto updateInfo){
        Expense ex= repository.findById(id).orElseThrow(()->new ExpenseNotFoundException("Expense data not found with id "+id));
        ex.setItem(updateInfo.getItem());
        ex.setCategory(updateInfo.getCategory());
        ex.setQuantityValue(updateInfo.getQuantityValue());
        ex.setAmount(updateInfo.getAmount());

        Expense saved = repository.save(ex);
        return ExpenseResponseDto.builder() .item(saved.getItem())
                .category(saved.getCategory())
                .quantityValue(saved.getQuantityValue())
                .amount(saved.getAmount())
                .build();
    }

    public void deleteExpense(Long id){
        if(!repository.existsById(id)){
            throw new ExpenseNotFoundException("Expense data not found with id "+id);
        }repository.deleteById(id);
    }

}

package com.practice.intern.ET.Expense.Tracker.API.Service;

import com.practice.intern.ET.Expense.Tracker.API.Exception.ExpenseNotFoundException;
import com.practice.intern.ET.Expense.Tracker.API.Model.Category;
import com.practice.intern.ET.Expense.Tracker.API.Model.Expense;
import com.practice.intern.ET.Expense.Tracker.API.Repository.CategoryRepository;
import com.practice.intern.ET.Expense.Tracker.API.Repository.ExpenseRepository;
import com.practice.intern.ET.Expense.Tracker.API.dto.ExpenseRequestDto;
import com.practice.intern.ET.Expense.Tracker.API.dto.ExpenseResponseDto;
import com.practice.intern.ET.Expense.Tracker.API.dto.ExpenseSearchRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {
    private final ExpenseRepository expenseRepository ;
    private final CategoryRepository categoryRepository;

    public ExpenseService(ExpenseRepository expenseRepository, CategoryRepository categoryRepository) {
        this.expenseRepository = expenseRepository;
        this.categoryRepository = categoryRepository;
    }

    public ExpenseResponseDto addExpense(ExpenseRequestDto requestDto) {
        Category category = categoryRepository.findById(requestDto.getCategoryId()).orElseThrow();
        Expense expense = Expense.builder()
                .item(requestDto.getItem())
                .category(category)
                .quantityValue(requestDto.getQuantityValue())
                .amount(requestDto.getAmount())
                .build();

        Expense added = expenseRepository.save(expense);
        return new ExpenseResponseDto(added.getId(), added.getItem(), added.getCategory().getId(), added.getQuantityValue(), added.getAmount());
    }

    public List<ExpenseResponseDto> findAllExpenses() {
        List<Expense> findall = expenseRepository.findAll();
        return findall.stream().map(
                ex -> ExpenseResponseDto.builder().id(ex.getId()).item(ex.getItem())
                        .quantityValue(ex.getQuantityValue()).category(ex.getCategory()).amount(ex.getAmount()).build()).toList();
    }

    public ExpenseResponseDto findByIdExpense(Long id) {
        Expense findId = expenseRepository.findById(id).orElseThrow(() -> new ExpenseNotFoundException("Expense data not found with id " + id));
        return new ExpenseResponseDto(findId.getId(), findId.getItem(), findId.getCategory(), findId.getQuantityValue(), findId.getAmount());
    }

    public ExpenseResponseDto updateByIdExpense(Long id, ExpenseRequestDto updateInfo) {
        Expense ex = expenseRepository.findById(id).orElseThrow(() -> new ExpenseNotFoundException("Expense data not found with id " + id));
        ex.setItem(updateInfo.getItem());
        ex.setCategory(updateInfo.getCategory());
        ex.setQuantityValue(updateInfo.getQuantityValue());
        ex.setAmount(updateInfo.getAmount());

        Expense saved = expenseRepository.save(ex);
        return ExpenseResponseDto.builder().item(saved.getItem())
                .category(saved.getCategory())
                .quantityValue(saved.getQuantityValue())
                .amount(saved.getAmount())
                .build();
    }

    public String deleteExpense(Long id) {
        if (!expenseRepository.existsById(id)) {
            throw new ExpenseNotFoundException("Expense data not found with id " + id);
        }
        expenseRepository.deleteById(id);
        return "data has been deleted";
    }

public Page<ExpenseResponseDto> searchExpense(ExpenseSearchRequestDto searchRequestDto){
        Sort sort = "desc".equalsIgnoreCase(searchRequestDto.getDirection()) ?
                Sort.by(searchRequestDto.getSort()).descending() : Sort.by(searchRequestDto.getSort()).ascending();
        Pageable pageable = PageRequest.of(searchRequestDto.getPage(),searchRequestDto.getSize(),sort);
        Specification<Expense> spec = (root, query, cb) -> cb.conjunction();

        if(searchRequestDto.getKeyword()!= null && !searchRequestDto.getKeyword().trim().isEmpty()){
            String matachPattern ="%"+searchRequestDto.getKeyword()+"%";
            spec = spec.and((root, query, cb) ->cb.or(
                    cb.like(cb.lower(root.get("item")),matachPattern),
                    cb.like(cb.lower(root.get("category")),matachPattern)));
        }
    Page<Expense> expensePage = expenseRepository.findAll(spec,pageable);
    return expensePage.map(ex -> ExpenseResponseDto.builder().id(ex.getId()).item(ex.getItem())
            .quantityValue(ex.getQuantityValue()).category(ex.getCategory()).amount(ex.getAmount()).build());
    }
}


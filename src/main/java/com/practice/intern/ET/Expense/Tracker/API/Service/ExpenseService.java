package com.practice.intern.ET.Expense.Tracker.API.Service;

import com.practice.intern.ET.Expense.Tracker.API.Exception.CategoryNotFoundException;
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
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ExpenseService {
    private final ExpenseRepository expenseRepository ;
    private final CategoryRepository categoryRepository;

    public ExpenseService(ExpenseRepository expenseRepository, CategoryRepository categoryRepository) {
        this.expenseRepository = expenseRepository;
        this.categoryRepository = categoryRepository;
    }
    public ExpenseResponseDto toResponeDto(Expense expense){
        return ExpenseResponseDto.builder()
                .id(expense.getId())
                .item(expense.getItem())
                .categoryId(expense.getCategory().getId())
                .quantityValue(expense.getQuantityValue())
                .amount(expense.getAmount())
                .build();
    }

    public ExpenseResponseDto addExpense(ExpenseRequestDto requestDto) {
        Category category = categoryRepository.findById(requestDto.getCategoryId()).orElseThrow(
                ()-> new CategoryNotFoundException("Category not found with id "+requestDto.getCategoryId()));
        Expense expense = Expense.builder()
                .item(requestDto.getItem())
                .category(category)
                .quantityValue(requestDto.getQuantityValue())
                .amount(requestDto.getAmount())
                .build();

        Expense added = expenseRepository.save(expense);
        return toResponeDto(added);
    }



    public List<ExpenseResponseDto> findAllExpenses() {
        List<Expense> findall = expenseRepository.findAll();
        return findall.stream()
                .map(this::toResponeDto).toList();
    }

    public ExpenseResponseDto findByIdExpense(Long id) {
        Expense findId = expenseRepository.findById(id).orElseThrow(
                () -> new ExpenseNotFoundException("Expense data not found with id " + id));
        return toResponeDto(findId);
    }

    public ExpenseResponseDto updateByIdExpense(Long id, ExpenseRequestDto updateInfo) {
        Expense ex = expenseRepository.findById(id).orElseThrow(
                () -> new ExpenseNotFoundException("Expense data not found with id " + id));
        Category category = categoryRepository.findById(updateInfo.getCategoryId()).orElseThrow(
                ()->new CategoryNotFoundException("Category not found with id "+updateInfo.getCategoryId()));
        ex.setItem(updateInfo.getItem());
        ex.setCategory(category);
        ex.setQuantityValue(updateInfo.getQuantityValue());
        ex.setAmount(updateInfo.getAmount());

        Expense saved = expenseRepository.save(ex);
        return toResponeDto(saved);
    }

    public String deleteExpense(Long id) {
        if (!expenseRepository.existsById(id)) {
            throw new ExpenseNotFoundException("Expense data not found with id " + id);
        }
        expenseRepository.deleteById(id);
        return "data has been deleted";
    }

public Page<ExpenseResponseDto> searchExpense(ExpenseSearchRequestDto searchRequestDto){
    String sortField = searchRequestDto.getSort();
    if ("category".equals(sortField)) {
        sortField = "category.name";}
    Sort sort = "desc".equalsIgnoreCase(searchRequestDto.getDirection()) ?
                Sort.by(sortField).descending() : Sort.by(sortField).ascending();
        Pageable pageable = PageRequest.of(searchRequestDto.getPage(),searchRequestDto.getSize(),sort);
        Specification<Expense> spec = (root, query, cb) -> cb.conjunction();

        if(searchRequestDto.getKeyword()!= null && !searchRequestDto.getKeyword().trim().isEmpty()){
            String matchPattern =
                    "%" + searchRequestDto.getKeyword().trim().toLowerCase() + "%";
            spec = spec.and((root, query, cb) ->cb.or(
                    cb.like(cb.lower(root.get("item")),matchPattern),
                    cb.like(cb.lower(root.join("category").get("name")),matchPattern)));
        }
    Page<Expense> expensePage = expenseRepository.findAll(spec,pageable);
    return expensePage.map(this::toResponeDto);
    }
}


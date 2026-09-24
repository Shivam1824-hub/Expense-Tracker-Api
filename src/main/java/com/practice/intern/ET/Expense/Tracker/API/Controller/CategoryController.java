package com.practice.intern.ET.Expense.Tracker.API.Controller;

import com.practice.intern.ET.Expense.Tracker.API.Service.CategoryService;
import com.practice.intern.ET.Expense.Tracker.API.dto.CategoryRequestDto;
import com.practice.intern.ET.Expense.Tracker.API.dto.CategoryResponseDto;
import com.practice.intern.ET.Expense.Tracker.API.dto.ExpenseResponseDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }
    @PostMapping
    public ResponseEntity<CategoryResponseDto> createCategory(@Valid @RequestBody CategoryRequestDto requestDto){
        CategoryResponseDto saved = service.createCategory(requestDto);
        return new ResponseEntity<>(saved,HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponseDto>> getAllCategory(){
        List<CategoryResponseDto> getAll = service.getAllCategory();
        return ResponseEntity.ok(getAll);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> getById(@PathVariable Long id){
        CategoryResponseDto getById = service.getByIdCategory(id);
        return ResponseEntity.ok(getById);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> updateById(@PathVariable Long id,@Valid @RequestBody CategoryRequestDto updateInfo){
        CategoryResponseDto updatedByid = service.updateByIdCategory(id,updateInfo);
        return ResponseEntity.ok(updatedByid);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        String message = service.delete(id);
        return ResponseEntity.ok(message);
    }

    @GetMapping("/{id}/expenses")
    public ResponseEntity<List<ExpenseResponseDto>> getExpensesByCategory(@PathVariable Long id){
        List<ExpenseResponseDto> get = service.getExpensesByCategory(id);
        return ResponseEntity.ok(get);
    }
}

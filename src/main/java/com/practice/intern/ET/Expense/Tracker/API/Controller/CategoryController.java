package com.practice.intern.ET.Expense.Tracker.API.Controller;

import com.practice.intern.ET.Expense.Tracker.API.Model.Category;
import com.practice.intern.ET.Expense.Tracker.API.Service.CategoryService;
import com.practice.intern.ET.Expense.Tracker.API.dto.CategoryRequestDto;
import com.practice.intern.ET.Expense.Tracker.API.dto.CategoryResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }
    @PostMapping
    public ResponseEntity<CategoryResponseDto> createCategory(CategoryRequestDto requestDto){
        CategoryResponseDto saved = service.createCategory(requestDto);
        return new ResponseEntity<>(saved,HttpStatus.CREATED);
    }
}

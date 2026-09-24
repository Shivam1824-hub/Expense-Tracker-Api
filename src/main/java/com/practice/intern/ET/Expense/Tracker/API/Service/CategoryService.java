package com.practice.intern.ET.Expense.Tracker.API.Service;

import com.practice.intern.ET.Expense.Tracker.API.Exception.CategoryNotFoundException;
import com.practice.intern.ET.Expense.Tracker.API.Model.Category;
import com.practice.intern.ET.Expense.Tracker.API.Repository.CategoryRepository;
import com.practice.intern.ET.Expense.Tracker.API.dto.CategoryRequestDto;
import com.practice.intern.ET.Expense.Tracker.API.dto.CategoryResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    public CategoryResponseDto createCategory(CategoryRequestDto requestDto){
        Category category = new Category();
        category.setName(requestDto.getName());
        Category savedcategory = categoryRepository.save(category);

        CategoryResponseDto dto = new CategoryResponseDto();
        dto.setId(savedcategory.getId());
        dto.setName(savedcategory.getName());
        return dto;
    }

    public List<CategoryResponseDto> getAllCategory(){
        List<Category> findall = categoryRepository.findAll();
        return findall.stream().map(category
                -> new CategoryResponseDto(category.getId(), category.getName())).toList();
    }

    public CategoryResponseDto getByIdCategory(Long id){
        Category findId = categoryRepository.findById(id).orElseThrow(
                ()->new CategoryNotFoundException("Category data not found with id "+id));
        return new CategoryResponseDto(findId.getId(),findId.getName());
    }

    public CategoryResponseDto updateByIdCategory(Long id, CategoryRequestDto requestDto){
        Category category = categoryRepository.findById(id).orElseThrow(
                () -> new CategoryNotFoundException("Category data not found with id "+id));
        category.setName(requestDto.getName());
        Category updated = categoryRepository.save(category);
        return new CategoryResponseDto(updated.getId(), updated.getName());
    }

    public String delete(Long id){
        if (!categoryRepository.existsById(id)){
            throw new CategoryNotFoundException("Category data not found with id "+id);
        }
        categoryRepository.deleteById(id);
        return "data has been deleted";
    }
}

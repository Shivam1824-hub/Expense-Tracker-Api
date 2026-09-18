package com.practice.intern.ET.Expense.Tracker.API.dto;

import com.practice.intern.ET.Expense.Tracker.API.Model.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponseDto {
    private Long id;
    private String name;

}

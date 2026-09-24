package com.practice.intern.ET.Expense.Tracker.API.dto;

import com.practice.intern.ET.Expense.Tracker.API.Model.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseResponseDto {

    private Long id;
    private String item;
    private Long categoryId;
    private double quantityValue;
    private BigDecimal amount;
}

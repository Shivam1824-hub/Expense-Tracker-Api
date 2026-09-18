package com.practice.intern.ET.Expense.Tracker.API.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
public class ExpenseResponseDto {

    private Long id;
    private String item;
    private Long categoryId;
    private double quantityValue;
    private BigDecimal amount;


    public ExpenseResponseDto(Long id, String item, Long categoryId, double quantityValue, BigDecimal amount) {
        this.id = id;
        this.item = item;
        this.categoryId = categoryId;
        this.quantityValue = quantityValue;
        this.amount = amount;
    }
}

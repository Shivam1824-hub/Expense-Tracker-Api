package com.practice.intern.ET.Expense.Tracker.API.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ExpenseResponseDto {

    private Long id;
    private String item;
    private String category;
    private double quantityValue;
    private BigDecimal amount;


    public ExpenseResponseDto(Long id, String item, String category, double quantityValue, BigDecimal amount) {
        this.id = id;
        this.item = item;
        this.category = category;
        this.quantityValue = quantityValue;
        this.amount = amount;
    }
}

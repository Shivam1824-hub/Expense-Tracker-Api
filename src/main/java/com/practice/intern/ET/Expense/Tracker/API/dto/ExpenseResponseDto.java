package com.practice.intern.ET.Expense.Tracker.API.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ExpenseResponseDto {

    private String item;
    private String category;
    private double quantityValue;
    private BigDecimal amount;

    public ExpenseResponseDto(String item, double quantityValue, String category, BigDecimal amount) {
        this.item = item;
        this.quantityValue = quantityValue;
        this.category = category;
        this.amount = amount;
    }
}

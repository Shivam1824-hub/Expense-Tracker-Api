package com.practice.intern.ET.Expense.Tracker.API.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class ExpenseRequestDto {
    private String item;
    private String category;
    private double quantityValue;
    private BigDecimal amount;

    public ExpenseRequestDto(String item, String category, double quantityValue, BigDecimal amount) {
        this.item = item;
        this.category = category;
        this.quantityValue = quantityValue;
        this.amount = amount;
    }
}

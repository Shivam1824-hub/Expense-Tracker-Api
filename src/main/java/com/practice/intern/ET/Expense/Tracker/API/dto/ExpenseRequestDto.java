package com.practice.intern.ET.Expense.Tracker.API.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class ExpenseRequestDto {

    @NotBlank(message = "Item should be added")
    private String item;
    @NotNull(message = "Category ID cannot be null")
    private Long categoryId;

    @Positive(message = "Value must be greater than zero")
    private double quantityValue;
    @Positive(message = "Amount must be greater than zero")
    private BigDecimal amount;

    public ExpenseRequestDto(String item, Long categoryId, double quantityValue, BigDecimal amount) {
        this.item = item;
        this.categoryId = categoryId;
        this.quantityValue = quantityValue;
        this.amount = amount;
    }
}

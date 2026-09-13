package com.practice.intern.ET.Expense.Tracker.API.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class ExpenseRequestDto {

    @NotBlank(message = "Item should be added")
    private String item;
    @NotBlank(message = "Category cannot be blank")
    private String category;
    @Positive(message = "Value must be greater than zero")
    private double quantityValue;
    @Min(value = 0)
    @Positive(message = "Amount must be greater than zero")
    private BigDecimal amount;

    public ExpenseRequestDto(String item, String category, double quantityValue, BigDecimal amount) {
        this.item = item;
        this.category = category;
        this.quantityValue = quantityValue;
        this.amount = amount;
    }
}

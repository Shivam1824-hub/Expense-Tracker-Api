package com.practice.intern.ET.Expense.Tracker.API.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.Data;


@Data
public class ExpenseSearchRequestDto {
    private String keyword;

    @Min(value = 0,message = "Page index can't be negative")
    private int page =0;
    @Min(value = 5,message = "Page size must be at least 5")
    private int size =5;

    @Pattern(regexp = "item|category|quantityValue|amount",
            message = "sort must be one of: item, category, quantityValue, amount")
    private String sort = "amount";
    @Pattern(regexp = "asc|desc",
            message = "direction must be one of: ascending or descending")
    private String direction = "asc";

    }

package com.practice.intern.ET.Expense.Tracker.API.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;


import java.math.BigDecimal;

@Entity
@Data
@Builder
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String item;
    private String category;
    private double quantityValue;
//    private enum quantityUnit {  LITRE("l"), KILOGRAMS("kg"), PACKET("p"); }
    private BigDecimal amount;

}

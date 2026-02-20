package com.expenseTracker.ExpenseTracker.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseDto {
    private String description;
    private String category;
    private int cost;
    private LocalDate date;
    private List<String> roles= new ArrayList<>();
}

package com.expenseTracker.ExpenseTracker.dtos;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailDto {
    @Column(nullable = false)
    private String username;

    private String gender;
    private int age;

    @Column(nullable = false)
    private String password;

     private String email;


}

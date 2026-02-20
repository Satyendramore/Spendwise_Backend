package com.expenseTracker.ExpenseTracker.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.management.relation.Role;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailsm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    @Column(nullable = false, unique = true)
    private String username;

    private String gender;
    private int age;

    @Column(nullable = false)
    private String password;

    private String email;
    @ElementCollection
    private List<String> roles= new ArrayList<>();

    @OneToMany( mappedBy="user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Expense> expenses;


}

package com.expenseTracker.ExpenseTracker.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data

public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int userId;

    private String role;
    @Column(columnDefinition = "LONGTEXT")
    private String message;

    private LocalDateTime createdAt =
            LocalDateTime.now();

    public ChatMessage(
            int userId,
            String role,
            String message
    ) {
        this.userId = userId;
        this.role = role;
        this.message = message;
    }



}

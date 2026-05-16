package com.expenseTracker.ExpenseTracker.Repo;



import com.expenseTracker.ExpenseTracker.Model.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRepo
        extends JpaRepository<ChatMessage, Long> {

    List<ChatMessage>
    findTop10ByUserIdOrderByCreatedAtDesc(
            int userId
    );



}

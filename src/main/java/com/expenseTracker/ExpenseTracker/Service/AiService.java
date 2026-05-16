package com.expenseTracker.ExpenseTracker.Service;

import com.expenseTracker.ExpenseTracker.Model.ChatMessage;
import com.expenseTracker.ExpenseTracker.Model.Expense;
import com.expenseTracker.ExpenseTracker.Repo.ChatRepo;
import com.expenseTracker.ExpenseTracker.Repo.ExpenseRepo;
import com.expenseTracker.ExpenseTracker.dtos.ChatRequestDto;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AiService {

    private final ChatClient chatClient;

    private final ExpenseRepo expenseRepo;

    private final ChatRepo chatRepo;

    public AiService(ChatClient.Builder builder, ExpenseRepo expenseRepo, ChatRepo chatRepo) {
        this.chatClient = builder.build();
        this.expenseRepo = expenseRepo;
        this.chatRepo = chatRepo;
    }

    public String askAi(ChatRequestDto request) {

        int userId = request.getUserId();

        List<Expense> expenses = expenseRepo.findByUser_UserId(userId);


        List<ChatMessage> chats = chatRepo.findTop10ByUserIdOrderByCreatedAtDesc(userId);


        String finalPrompt =
                AiPromptBuilder.buildPrompt(
                        expenses,
                        chats,
                        request.getMessage()
                );


        String response = chatClient.prompt()
                .user(finalPrompt)
                .call()
                .content();


        chatRepo.save(new ChatMessage(userId, "USER", request.getMessage()));


        chatRepo.save(new ChatMessage(userId, "ASSISTANT", response));

        return response;
    }

    public List<ChatMessage> getChatHistory(int userId) {

        return chatRepo
                . findTop10ByUserIdOrderByCreatedAtDesc(userId);

    }
}



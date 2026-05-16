package com.expenseTracker.ExpenseTracker.Controller;

import com.expenseTracker.ExpenseTracker.Model.ChatMessage;
import com.expenseTracker.ExpenseTracker.Service.AiService;
import com.expenseTracker.ExpenseTracker.dtos.ChatRequestDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ai")
@CrossOrigin("*")
public class AiController {

    private final AiService aiService;

    public AiController(AiService aiService) {
        this.aiService = aiService;
    }

    @GetMapping("/history/{userId}")
    public List<ChatMessage> getChatHistory(
            @PathVariable int userId
    ) {

        return aiService.getChatHistory(userId);

    }

    @PostMapping("/chat")
    public String askAi(
            @RequestBody ChatRequestDto request,
            HttpServletRequest http
    ) {

        System.out.println("========== AI API ==========");
        System.out.println("URL HIT: /ai/chat");

        String auth = http.getHeader("Authorization");

        System.out.println("TOKEN HEADER: " + auth);

        System.out.println("USER ID: " + request.getUserId());

        System.out.println("MESSAGE: " + request.getMessage());

        return aiService.askAi(request);
    }
}
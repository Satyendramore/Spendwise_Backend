package com.expenseTracker.ExpenseTracker.Service;

import com.expenseTracker.ExpenseTracker.Model.ChatMessage;
import com.expenseTracker.ExpenseTracker.Model.Expense;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AiPromptBuilder {

    public static String buildPrompt(
            List<Expense> expenses,
            List<ChatMessage> chats,
            String userQuestion
    ) {

        boolean hasExpenses = expenses != null && !expenses.isEmpty();

        int totalSpending = 0;

        Map<String, Integer> categoryTotals = new HashMap<>();
        StringBuilder recentExpenses = new StringBuilder();

        if (hasExpenses) {

            totalSpending = expenses.stream()
                    .mapToInt(Expense::getCost)
                    .sum();

            categoryTotals = expenses.stream()
                    .collect(Collectors.groupingBy(
                            Expense::getCategory,
                            Collectors.summingInt(Expense::getCost)
                    ));

            expenses.stream()
                    .limit(5)
                    .forEach(expense -> recentExpenses
                            .append("- ")
                            .append(expense.getDescription())
                            .append(" | ")
                            .append(expense.getCategory())
                            .append(" | ₹")
                            .append(expense.getCost())
                            .append("\n"));
        }

        StringBuilder categorySummary = new StringBuilder();

        categoryTotals.forEach((category, amount) ->
                categorySummary
                        .append("- ")
                        .append(category)
                        .append(": ₹")
                        .append(amount)
                        .append("\n")
        );

        StringBuilder chatHistory = new StringBuilder();

        if (chats != null && !chats.isEmpty()) {
            for (ChatMessage chat : chats) {
                chatHistory.append(chat.getRole())
                        .append(": ")
                        .append(chat.getMessage())
                        .append("\n");
            }
        } else {
            chatHistory.append("No previous conversation.\n");
        }

        return """
You are a personal AI financial assistant.

You help users:
- Track expenses
- Understand spending patterns
- Build budgets
- Identify overspending (ONLY when data exists)
- Give practical money-saving advice

IMPORTANT RULES:
- If there is NO expense data, DO NOT assume spending habits
- Do NOT give overspending analysis without data
- First guide the user to start tracking expenses
- Keep responses simple, clear, and actionable
- Be conversational, not robotic

====================================
DATA STATE
====================================
%s

====================================
USER FINANCIAL SUMMARY
====================================

Total Spending: ₹%d

====================================
CATEGORY BREAKDOWN
====================================

%s

====================================
RECENT EXPENSES
====================================

%s

====================================
CHAT HISTORY
====================================

%s

====================================
USER QUESTION
====================================

%s
""".formatted(
                hasExpenses ? "ACTIVE_USER_WITH_DATA" : "NEW_USER_NO_DATA",
                totalSpending,
                hasExpenses ? categorySummary.toString() : "No categories yet",
                hasExpenses ? recentExpenses.toString() : "No expenses recorded yet",
                chatHistory.toString(),
                userQuestion
        );
    }
}
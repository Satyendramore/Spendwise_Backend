package com.expenseTracker.ExpenseTracker.Service;

import com.expenseTracker.ExpenseTracker.Repo.ExpenseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseAnalyticService {
    @Autowired
    private ExpenseRepo repo;
    public List<Object[]> expenseofyear(String username, int year) {

        return repo.getExpenseByMonth(username,year);
    }

    public List<Object[]> expenseofayear(String username) {
        return repo.getExpenseByYear(username);
    }

    public List<Object[]> categoryexpense(String username) {
        return repo.getExpenseByCategory(username);
    }
}

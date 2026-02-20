package com.expenseTracker.ExpenseTracker.Repo;

import com.expenseTracker.ExpenseTracker.Model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepo extends JpaRepository<Expense,Integer> {
    @Query("SELECT FUNCTION('month', e.date), SUM(e.cost) " +
            "FROM Expense e " +
            "WHERE e.user.username = ?1 " +
            "AND FUNCTION('year', e.date) = ?2 " +
            "GROUP BY FUNCTION('month', e.date) " +
            "ORDER BY FUNCTION('month', e.date)")
    List<Object[]> getExpenseByMonth(String username, int year);


    @Query("SELECT FUNCTION('year', e.date), SUM(e.cost) " +
            "FROM Expense e " +
            "WHERE e.user.username = ?1 " +
            "GROUP BY FUNCTION('year', e.date) " +
            "ORDER BY FUNCTION('year', e.date)")
    List<Object[]> getExpenseByYear(String username);



    @Query("SELECT e.category, SUM(e.cost) " +
            "FROM Expense e " +
            "WHERE e.user.username = ?1 " +
            "GROUP BY e.category " +
            "ORDER BY e.category")
    List<Object[]> getExpenseByCategory(String username);



}

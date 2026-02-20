package com.expenseTracker.ExpenseTracker.Service;

import com.expenseTracker.ExpenseTracker.Exception.ObjectDoesntExist;
import com.expenseTracker.ExpenseTracker.Exception.ObjectNotFound;
import com.expenseTracker.ExpenseTracker.dtos.ExpenseDto;
import com.expenseTracker.ExpenseTracker.Model.UserDetailsm;
import com.expenseTracker.ExpenseTracker.Model.Expense;
import com.expenseTracker.ExpenseTracker.Repo.UserRepo;
import com.expenseTracker.ExpenseTracker.Repo.ExpenseRepo;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ExpenseService {
    @Autowired
    private ExpenseRepo repo;
    @Autowired
    private UserRepo urepo;

    @Transactional
    public  UserDetailsm addExpense(ExpenseDto e, String username) {
        Expense ex=new Expense();
        ex.setDescription(e.getDescription());
        ex.setCategory(e.getCategory().toUpperCase());
        ex.setCost(e.getCost());
        ex.setDate(LocalDate.now());

        UserDetailsm u = urepo.findByUsername(username).orElse(null);
        if(u==null){
            throw new ObjectDoesntExist("user id is not correct");
        }
        ex.setUser(u);
        u.getExpenses().add(ex);

        return u;
    }
    @Transactional
    public  UserDetailsm deleteExpense(String username, int expenseId) {
        UserDetailsm user = urepo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.getExpenses().removeIf(e -> e.getId() == expenseId);

        return user;
    }


    @Transactional
    public Expense UpdateExpense(int id,Expense e) {
        Expense e1=repo.findById(id).orElse(null);
        if(e1==null){
            throw new ObjectNotFound("no expense of this id present ");
        }
        else{
            e1.setDescription(e.getDescription());
            e1.setCategory(e.getCategory());
            e1.setCost(e.getCost());
        }
        return e1;
    }

    public List<Expense> getAllExpenses() {
        return repo.findAll();
    }
}


package com.expenseTracker.ExpenseTracker.Controller;

import com.expenseTracker.ExpenseTracker.dtos.ExpenseDto;
import com.expenseTracker.ExpenseTracker.Model.Expense;
import com.expenseTracker.ExpenseTracker.Service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/expense")
@PreAuthorize("hasRole('USER')")
public class ExpenseController {
    @Autowired
    private ExpenseService service;

    @PostMapping
    public ResponseEntity<?> addExpense(@RequestBody ExpenseDto e, Authentication authentication) {
        String username = authentication.getName();
        return new ResponseEntity<>(service.addExpense(e,username), HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<?> getExpenses(Authentication authentication) {
        String username = authentication.getName();
        return new ResponseEntity<>(service.getAllExpenses(),HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateExpense(@PathVariable int id,@RequestBody Expense e) {
        return new ResponseEntity<>(service.UpdateExpense(id,e), HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteExpense(@PathVariable int id,Authentication authentication){
        String username = authentication.getName();
        return new ResponseEntity<>(service.deleteExpense(username,id),HttpStatus.OK);
    }
}

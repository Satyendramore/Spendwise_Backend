package com.expenseTracker.ExpenseTracker.Controller;

import com.expenseTracker.ExpenseTracker.Service.ExpenseAnalyticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/expense/analytics")
@PreAuthorize("hasRole('USER')")
public class ExpenseAnalytics {
@Autowired
private ExpenseAnalyticService service;

  @GetMapping("/year")
    public ResponseEntity<?> expenseofyear(@RequestParam int year, Authentication authentication){

      String username = authentication.getName();
    return new ResponseEntity(service.expenseofyear(username,year), HttpStatus.OK);
}

    @GetMapping("/years")
    public ResponseEntity<?> expenseofayear(Authentication authentication){
        String username = authentication.getName();
        return new ResponseEntity(service.expenseofayear(username), HttpStatus.OK);
    }


    @GetMapping("/category")
    public ResponseEntity<?> categoryexpense(Authentication authentication ){
        String username = authentication.getName();
        return new ResponseEntity(service.categoryexpense(username), HttpStatus.OK);
    }


}

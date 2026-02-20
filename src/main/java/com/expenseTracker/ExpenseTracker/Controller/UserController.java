package com.expenseTracker.ExpenseTracker.Controller;

import com.expenseTracker.ExpenseTracker.dtos.UserDetailDto;
import com.expenseTracker.ExpenseTracker.Service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/User")
public class UserController {
    @Autowired
    private UserDetailsService service;
    @GetMapping()
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> userinfo(){
        return new ResponseEntity<>(service.userinfo(), HttpStatus.OK);
    }

    @PostMapping()
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> usersave(@RequestBody UserDetailDto user){
        return new ResponseEntity<>(service.usersave(user), HttpStatus.OK);
    }
}

package com.expenseTracker.ExpenseTracker.Controller;

import com.expenseTracker.ExpenseTracker.Security.jwtservice;
import com.expenseTracker.ExpenseTracker.Service.UserDetailsService;
import com.expenseTracker.ExpenseTracker.dtos.UserDetailDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class LoginsignupController {
    @Autowired
    private UserDetailsService service;
    @PostMapping("/public/register")
    public ResponseEntity<?> registeruser(@RequestBody UserDetailDto u)
    {
        return new ResponseEntity(service.usersave(u), HttpStatus.OK) ;
    }

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private jwtservice jservice;

    @PostMapping("/public/login")
    public String loginuser(@RequestBody UserDetailDto u)
    {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(u.getUsername(), u.getPassword())
        );

        if (auth.isAuthenticated()) {
            return jservice.generateToken(u.getUsername(), List.of("ROLE_USER")); // default role
        } else {
            return "fail";
        }
    }
}

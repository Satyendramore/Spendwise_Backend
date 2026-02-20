package com.expenseTracker.ExpenseTracker.Service;

import com.expenseTracker.ExpenseTracker.dtos.UserDetailDto;
import com.expenseTracker.ExpenseTracker.Model.UserDetailsm;
import com.expenseTracker.ExpenseTracker.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserDetailsService {
    @Autowired
    private UserRepo repo;

    public List<UserDetailDto> userinfo() {
        return repo.findAll().stream()
                .map(u -> new UserDetailDto(
                        u.getUsername(),
                        u.getGender(),
                        u.getAge(),
                        null,
                        u.getEmail()
                ))
                .toList();
    }
    private BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12);

    @Transactional
    public String usersave( UserDetailDto user) {
        UserDetailsm u=new UserDetailsm();
        u.setUsername(user.getUsername());
        u.setGender(user.getGender());
        u.setAge(user.getAge());
        u.setAge(user.getAge());
        u.setEmail(user.getEmail());
        u.setPassword(encoder.encode(user.getPassword()));
        u.getRoles().add("USER");
        repo.save(u);
        return "user is saved";
    }
}

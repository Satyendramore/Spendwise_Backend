package com.expenseTracker.ExpenseTracker.Repo;

import com.expenseTracker.ExpenseTracker.Model.UserDetailsm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<UserDetailsm,Integer> {
    Optional<UserDetailsm> findByUsername(String username);

}

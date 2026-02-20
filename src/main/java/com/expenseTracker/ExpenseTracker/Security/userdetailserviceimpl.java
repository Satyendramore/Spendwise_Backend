package com.expenseTracker.ExpenseTracker.Security;

import com.expenseTracker.ExpenseTracker.Model.UserDetailsm;
import com.expenseTracker.ExpenseTracker.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class userdetailserviceimpl implements UserDetailsService {
    @Autowired
    private UserRepo user;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
      UserDetailsm u = user.findByUsername(name).orElse(null);
        if (u == null) {
            throw new UsernameNotFoundException(name);
        }
        return new userprincipal(u);
    }
}

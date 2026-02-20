package com.expenseTracker.ExpenseTracker.Security;

import com.expenseTracker.ExpenseTracker.Model.UserDetailsm;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class userprincipal implements UserDetails {

    private final UserDetailsm userinfo;




    public userprincipal(UserDetailsm userinfo) {
        this.userinfo = userinfo;
        System.out.println("Username: " + userinfo.getUsername());
        System.out.println("Password: " + userinfo.getPassword());

    }


//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return userinfo.getRoles().stream()
//                .map(role -> role.startsWith("ROLE_") ? role : "ROLE_" + role)
//                .distinct()
//                .map(SimpleGrantedAuthority::new)
//                .collect(Collectors.toList());
//
//    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return userinfo.getPassword();
    }

    @Override
    public String getUsername() {
        return userinfo.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return true; }
}

package com.example.demo.security;

import com.example.demo.model.Account;
import com.example.demo.permissions.ApplicationUserRole;
import com.example.demo.service.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ApplicationUserService implements UserDetailsService {

    @Autowired
    private AccountService accountService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return accountService
                .selectApplicationUserByUsername(s)
                .map(this::convertAccountToUserDetails)
                .orElseThrow(() ->
                        new UsernameNotFoundException(String.format("Username %s not found", s))
                );
    }

    private UserDetails convertAccountToUserDetails(Account account) {
        Set<? extends GrantedAuthority> grantedAuthorities = ApplicationUserRole.valueOf(account.getRole()).getGrantedAuthorities();

        return ApplicationUser.builder()
                .username(account.getUsername())
                .password(account.getPassword())
                .grantedAuthorities(grantedAuthorities)
                .isAccountNonExpired(account.isAccountNonExpired())
                .isAccountNonLocked(account.isAccountNonLocked())
                .isCredentialsNonExpired(account.isCredentialsNonExpired())
                .isEnabled(account.isEnabled())
                .build();
    }
}

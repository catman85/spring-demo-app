package com.example.demo.permissions;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.demo.permissions.ApplicationUserPermission.QUESTION_READ;
import static com.example.demo.permissions.ApplicationUserPermission.QUESTION_WRITE;
import static com.example.demo.permissions.ApplicationUserPermission.USER_READ;
import static com.example.demo.permissions.ApplicationUserPermission.USER_WRITE;

public enum ApplicationUserRole {
    NORMAL(getNormalPrivileges()),
    ADMIN(getAdminPrivileges());

    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }

    private static HashSet<ApplicationUserPermission> getNormalPrivileges() {
        HashSet<ApplicationUserPermission> hashSet = new HashSet<ApplicationUserPermission>();
        hashSet.add(QUESTION_WRITE);
        hashSet.add(QUESTION_READ);
        return hashSet;
    }

    private static HashSet<ApplicationUserPermission> getAdminPrivileges() {
        HashSet<ApplicationUserPermission> hashSet = new HashSet<ApplicationUserPermission>();
        hashSet.add(USER_WRITE);
        hashSet.add(USER_READ);
        return hashSet;
    }
}
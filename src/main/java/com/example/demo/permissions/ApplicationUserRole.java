package com.example.demo.permissions;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.demo.permissions.ApplicationUserPermission.COURSE_READ;
import static com.example.demo.permissions.ApplicationUserPermission.COURSE_WRITE;
import static com.example.demo.permissions.ApplicationUserPermission.STUDENT_READ;
import static com.example.demo.permissions.ApplicationUserPermission.STUDENT_WRITE;

public enum ApplicationUserRole {
    STUDENT(getStudentPrivileges()),
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

    private static HashSet<ApplicationUserPermission> getStudentPrivileges() {
        HashSet<ApplicationUserPermission> hashSet = new HashSet<ApplicationUserPermission>();
        hashSet.add(STUDENT_WRITE);
        hashSet.add(STUDENT_READ);
        return hashSet;
    }

    private static HashSet<ApplicationUserPermission> getAdminPrivileges() {
        HashSet<ApplicationUserPermission> hashSet = new HashSet<ApplicationUserPermission>();
        hashSet.add(STUDENT_WRITE);
        hashSet.add(STUDENT_READ);
        hashSet.add(COURSE_WRITE);
        hashSet.add(COURSE_READ);
        return hashSet;
    }
}
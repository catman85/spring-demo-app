package com.example.demo.permissions;

public enum ApplicationUserPermission {
    QUESTION_READ("question:read"),
    QUESTION_WRITE("question:write"),
    USER_READ("user:read"),
    USER_WRITE("user:write");

    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
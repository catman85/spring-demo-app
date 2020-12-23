package com.example.demo.exception;

import org.springframework.security.core.AuthenticationException;

public class MaliciousAttemptException extends AuthenticationException {

    public MaliciousAttemptException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public MaliciousAttemptException(String msg) {
        super(msg);
    }
}

package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MaliciousAttemptException extends AuthenticationException {

    public MaliciousAttemptException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public MaliciousAttemptException(String msg) {
        super(msg);
    }
}

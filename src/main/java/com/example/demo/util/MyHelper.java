package com.example.demo.util;

import com.example.demo.exception.MaliciousAttemptException;
import com.example.demo.model.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

import lombok.extern.java.Log;

@Component
@Log
public class MyHelper {

    public <T> Page<T> convertListToPage(List<T> list, Pageable pageable) {
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), list.size());
        return new PageImpl<>(list.subList(start, end), pageable, list.size());
    }

    public void simulateLowDevice(Long sleepTime) {
        try {
            long time = sleepTime;
            log.info("zZzZzZzZ Sleeping for " + sleepTime);
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }

    public void isRequestMadeByUser(User user) throws MaliciousAttemptException {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(!user.getAccount().getUsername().equals(username)){
            throw new MaliciousAttemptException("User with username: " + username + " is bad boi.");
        }
    }
}

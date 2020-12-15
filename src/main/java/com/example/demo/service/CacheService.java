package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CacheService {
    @Autowired
    CacheManager cacheManager;

    @Scheduled(fixedRateString = "${clear.all.cache.fixed.rate}", initialDelayString = "${clear.all.cache.init.delay}")
    // reset cache every hr, with delay of 1hr after app start
    public void evictAllCache() {
        cacheManager.getCacheNames().parallelStream()
                .forEach(name -> Objects.requireNonNull(cacheManager.getCache(name)).clear());
    }
}

package com.retry.example.retrydemo.service;

import java.util.Map;
 
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
 
@Service
public interface RetryService {
 
	//@Retryable(value = { Exception.class }, maxAttemptsExpression = "${retry.maxAttempts}", backoff = @Backoff(delayExpression = "${retry.delay}"))
    @Retryable(value = { Exception.class }, maxAttempts = 5, backoff = @Backoff(delay = 5000))
    public void retry(Map<String, String> request) throws Exception;
}
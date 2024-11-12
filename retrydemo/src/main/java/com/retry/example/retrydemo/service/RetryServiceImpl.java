package com.retry.example.retrydemo.service;
import java.util.Map;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
 
@Service
public class RetryServiceImpl implements RetryService{
     
    Logger logger = LoggerFactory.getLogger(RetryServiceImpl.class);
     
    int counter = 0;
 
    @Override
    public void retry(Map<String, String> request) throws Exception {
         
        logger.info("Executing retry service : "+ counter++);
        int a = 10/0;
    }
}
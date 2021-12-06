package com.example.SpringProject.Services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogService {
    private Logger logger = LoggerFactory.getLogger(LogService.class);
    public void service(){
        logger.info("Message at INFO level from LogService.service()");
        logger.warn("Message at WARN level from LogService.service()");
    }
}

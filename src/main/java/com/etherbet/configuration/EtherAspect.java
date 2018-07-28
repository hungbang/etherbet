package com.etherbet.configuration;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

/**
 * Created by KAI on 7/28/18.
 * Copyright 2018 by etherbet
 * All rights reserved.
 */
@Aspect
@Component
public class EtherAspect {
    public static final Logger LOGGER = LoggerFactory.getLogger(EtherAspect.class);

    @Around("@annotation(com.etherbet.aspect.IPFSPublish)")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        joinPoint.proceed();
        long timeTaken = System.currentTimeMillis() - startTime;
        LOGGER.info("Time taken by {} is {}", joinPoint, timeTaken);
    }

}

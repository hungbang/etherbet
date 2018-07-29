package com.etherbet.service;

import freemarker.template.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by KAI on 7/29/18.
 * Copyright 2018 by etherbet
 * All rights reserved.
 */
public interface SenderEmailService {
    public static final Logger LOGGER = LoggerFactory.getLogger(SenderEmailService.class);

    public void sendEmailAfterAPIScheduleJob(String apiurl, long... params);



}

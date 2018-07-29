package com.etherbet.service;

import com.etherbet.core.EmailServices;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static com.etherbet.util.FreemarkerUtil.SCHEDULED_JOB_EMAIL;
import static com.etherbet.util.FreemarkerUtil.SCHEDULED_JOB_EMAIL_SUB;

/**
 * Created by KAI on 7/29/18.
 * Copyright 2018 by etherbet
 * All rights reserved.
 */
@Service
public class SenderEmailServiceImpl implements SenderEmailService {
    public static final Logger LOGGER = LoggerFactory.getLogger(SenderEmailServiceImpl.class);

    @Value("${ether.admin.email}")
    public String emailAdmin;
    @Value("${ether.admin.notifiedEmail}")
    public String notifiedEmail;

    @Autowired
    public EmailServices emailServices;

    @Autowired
    @Qualifier("freemarkerConfiguration")
    public Configuration configuration;

    @Override
    public void sendEmailAfterAPIScheduleJob(String apiurl, long... params) {
        try {
            Template template = configuration.getTemplate(SCHEDULED_JOB_EMAIL, Locale.US);
            Map<String, Object> model = new HashMap<>();
            model.put("api", apiurl);
            model.put("startDateTime", params[0]);
            model.put("endDateTime", params[1]);
            model.put("wasteTime", params[2]);
            String body = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
            emailServices.sendEmail(notifiedEmail, SCHEDULED_JOB_EMAIL_SUB, body, emailAdmin);
        } catch (Exception e) {
            LOGGER.error(SenderEmailServiceImpl.class.getName(), e);
        }
    }
}

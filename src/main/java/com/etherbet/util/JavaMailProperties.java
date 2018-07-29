package com.etherbet.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by KAI on 7/29/18.
 * Copyright 2018 by etherbet
 * All rights reserved.
 */
public class JavaMailProperties {
    public static final Logger LOGGER = LoggerFactory.getLogger(JavaMailProperties.class);
    public static final String MAIL_SMTP_AUTH = "mail.smtp.auth";
    public static final String MAIL_SMTP_STARTTLS_ENABLE = "mail.smtp.starttls.enable";
    public static final String MAIL_SMTP_STARTTLS_REQUIRED = "mail.smtp.starttls.required";
    public static final String MAIL_SMTP_CONNECTION_TIMEOUT = "mail.smtp.connectiontimeout";
    public static final String MAIL_SMTP_TIMEOUT = "mail.smtp.timeout";
    public static final String MAIL_SMTP_WRITE_TIMEOUT = "mail.smtp.writetimeout";
}

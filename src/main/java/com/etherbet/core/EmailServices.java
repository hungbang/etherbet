package com.etherbet.core;

import com.etherbet.core.exception.EmailNotificationException;

/**
 * Created by KAI on 7/29/18.
 * Copyright 2018 by etherbet
 * All rights reserved.
 */

public interface EmailServices {

    /**
     * Sends email to specified email address with specified body content.
     *
     * @param emailAddress : email address of the recipient
     * @param subject : subject of the email.
     * @param bodyContent : email content.
     * @param fromMail : sender's email address.
     * @throws EmailNotificationException if there is exception is sending the email.
     */
    public void sendEmail(String emailAddress, String subject,
                          String bodyContent,String fromMail) throws EmailNotificationException;
}

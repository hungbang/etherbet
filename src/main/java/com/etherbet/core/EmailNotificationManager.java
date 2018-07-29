package com.etherbet.core;

import com.etherbet.core.exception.EmailNotificationException;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


/**
 * Created by KAI on 7/29/18.
 * Copyright 2018 by etherbet
 * All rights reserved.
 */
public class EmailNotificationManager implements EmailServices, Runnable {
    /**
     * Helper class to send email.
     */
    private JavaMailSender mailSender;

    /**
     * Message to be sent to consumer.
     */
    private SimpleMailMessage simpleMailMessage;

    private String emailAddress;

    private String bodyContent;

    private String subject;

    private String fromMail;

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
                          String bodyContent, String fromMail) throws EmailNotificationException {
        //System.out.println(bodyContent);
        this.emailAddress = emailAddress;
        this.bodyContent = bodyContent;
        this.subject = subject;
        this.fromMail = fromMail;
        new Thread(this).start();
    }

    /**
     * Retrieves {@link JavaMailSender}
     * @return the mailSender
     */
    public JavaMailSender getMailSender() {
        return mailSender;
    }

    /**
     * Sets java mail sender
     * @param mailSender
     *            the mailSender to set
     */
    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    /**
     * Retrieves {@link SimpleMailMessage}
     * @return the simpleMailMessage
     */
    public SimpleMailMessage getSimpleMailMessage() {
        return simpleMailMessage;
    }

    /**
     * Sets simple mail Message
     * @param simpleMailMessage
     *            the simpleMailMessage to set
     */
    public void setSimpleMailMessage(SimpleMailMessage simpleMailMessage) {
        this.simpleMailMessage = simpleMailMessage;
    }

    /*@Override
    public void run() {
        SimpleMailMessage message = new SimpleMailMessage(simpleMailMessage);
        message.setTo(emailAddress);
        message.setText(bodyContent);
        message.setSubject(subject);
        message.setFrom(fromMail);

        try {
            mailSender.send(message);
        } catch (MailException e) {
            e.printStackTrace();
            //throw new EmailNotificationException(e.getMessage());
        }

    }*/
    @Override
    public void run() {

        MimeMessage mimeMessage = mailSender.createMimeMessage();

        try {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage,
                    "UTF-8");
            message.setTo(emailAddress);
            message.setText(bodyContent, true);
            message.setSubject(subject);
            message.setFrom(fromMail);
            mailSender.send(mimeMessage);
        } catch (MailException e) {
            e.printStackTrace();
            // throw new EmailNotificationException(e.getMessage());
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }
}

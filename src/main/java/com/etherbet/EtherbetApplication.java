package com.etherbet;

import com.etherbet.core.EmailNotificationManager;
import com.etherbet.core.EmailServices;
import com.etherbet.util.JavaMailProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Properties;

@SpringBootApplication
@PropertySource(value = {"file:${vn.fb.appDir}/config/footballbet.application.properties"})
@EnableScheduling
public class EtherbetApplication {


    public static void main(String[] args) throws IOException {
        SpringApplication.run(EtherbetApplication.class, args);
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public FreeMarkerConfigurationFactoryBean freemarkerConfiguration() {
        FreeMarkerConfigurationFactoryBean configuration = new FreeMarkerConfigurationFactoryBean();
        configuration.setTemplateLoaderPath("file:" + System.getProperty("vn.fb.appDir") + "/template");
        configuration.setDefaultEncoding("UTF-8");
        return configuration;
    }

    @Bean
    public JavaMailSender javaMailSender() {
//        Properties javaMailProperties = new Properties();
//        javaMailProperties.put(JavaMailProperties.MAIL_SMTP_AUTH, true);
//        javaMailProperties.put(JavaMailProperties.MAIL_SMTP_STARTTLS_ENABLE, true);
//        javaMailProperties.put(JavaMailProperties.MAIL_SMTP_STARTTLS_REQUIRED, true);
//        javaMailProperties.put(JavaMailProperties.MAIL_SMTP_CONNECTION_TIMEOUT, 5000);
//        javaMailProperties.put(JavaMailProperties.MAIL_SMTP_TIMEOUT, 5000);
//        javaMailProperties.put(JavaMailProperties.MAIL_SMTP_WRITE_TIMEOUT, 5000);


        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setDefaultEncoding("UTF-8");
        javaMailSender.setHost("smtp.mailtrap.io");
        javaMailSender.setPort(2525);
        javaMailSender.setUsername("5696e14ad52910");
        javaMailSender.setPassword("0d4b5369ad4f5c");

        return javaMailSender;
    }

    @Bean
    public EmailServices emailServices() {
        EmailNotificationManager emailNotificationManager = new EmailNotificationManager();
        emailNotificationManager.setMailSender(javaMailSender());
        return emailNotificationManager;
    }


}

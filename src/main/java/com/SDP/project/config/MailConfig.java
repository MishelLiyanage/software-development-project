package com.SDP.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.beans.factory.annotation.Value;

import java.util.Properties;

@Configuration
public class MailConfig {

    @Value("${spring.mail.host}")
    private String host;

    @Value("${spring.mail.port}")
    private int port;

    @Value("${spring.mail.properties.mail.smtp.from:stjosephsinstitute@example.com}")
    private String username;

    @Value("${spring.mail.password:}")
    private String password;

    @Value("${spring.mail.properties.mail.smtp.auth:false}")
    private boolean auth;

    @Value("${spring.mail.properties.mail.smtp.starttls.enable:false}")
    private boolean starttls;

    @Value("${spring.mail.properties.mail.smtp.from:stjosephsinstitute@example.com}")
    private String defaultFrom;

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        // Set mail server properties
        mailSender.setHost(host);
        mailSender.setPort(port);

        // Only set username/password if they are provided
        if (username != null && !username.isEmpty()) {
            mailSender.setUsername(username);
            mailSender.setPassword(password);
        }

        // Configure additional properties
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.smtp.auth", String.valueOf(auth));
        props.put("mail.smtp.starttls.enable", String.valueOf(starttls));

        // Add default from address if available
        if (defaultFrom != null && !defaultFrom.isEmpty()) {
            props.put("mail.smtp.from", defaultFrom);
        }

        // For debugging - helpful during development
        props.put("mail.debug", "true");

        return mailSender;
    }
}

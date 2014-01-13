package com.demo;

import javax.mail.*;
import javax.mail.internet.*;

import java.util.Properties;

class SimpleMail {
    public static void main(String[] args) throws Exception{
        System.out.println("Sending mail...");
        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.host", "smtp.gmail.com");
        props.setProperty("mail.user", "olga.efimova.aol");
        props.setProperty("mail.password", "..");
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "false");
        props.put("mail.smtp.auth", "true");

        //Session mailSession = Session.getDefaultInstance(props, null);
        //mailSession.setDebug(true);
        
        Session mailSession = Session.getInstance(props,
                new javax.mail.Authenticator() {

                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("olga.efimova.aol", "...");
                    }
                });

        mailSession.setDebug(true);
        //
        
        Transport transport = mailSession.getTransport();

        MimeMessage message = new MimeMessage(mailSession);
        message.setSubject("Testing HTML mail with base64 images");
        message.setFrom(new InternetAddress("olga.efimova.aol@gmail.com"));
        message.setContent("<h1>Image should be below:</h1><img alt=\"\" src=\"data:image/jpeg;base64,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\" />", "text/html");
        message.addRecipient(Message.RecipientType.TO,
             new InternetAddress("olga.efimova.aol@gmail.com"));

        transport.connect();
        transport.sendMessage(message,
            message.getRecipients(Message.RecipientType.TO));
        transport.close();
        }
}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.util;

import com.example.gzone.Id;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Mahdi
 */
public class SendEmail {

    private static int CodeGenerator() {
        int min = 1000;
        int max = 999999;
        int random_int = (int) Math.floor(Math.random() * (max - min + 1) + min);
        System.out.println(random_int);
        return random_int;

    }

    public static void sendMail(String recepient) throws Exception {
        System.out.println("Preparing to send email");
        Properties properties = new Properties();
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        String myAccountEmail = "appgzone@gmail.com";
        String password = "123456789.Az";

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });

        //Prepare email message
        Message message = prepareMessage(session, myAccountEmail, recepient);

        //Send mail
        Transport.send(message);
        System.out.println("Message sent successfully");
    }

    private static Message prepareMessage(Session session, String myAccountEmail, String recepient) {
        try {
            int CodeGenerator = CodeGenerator();
            Id.code=CodeGenerator;
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("G-ZONE Password Recovery");
            String htmlCode = "<h1>Here is your verification code</h1><br><h2>Use it and enter a new password </h2>\n"
                    + "\n"
                    + "<style>\n"
                    + " h1 {\n"
                    + "  color: #26b72b;\n"
                    + "   font-family:verdana;\n"
                    + "   text-align:center;\n"
                    + "   font-weight: bold;\n"
                    + "}\n"
                    + "   h2 {\n"
                    + "  color: #00008B;\n"
                    + "     font-family:courier;\n"
                    + "     text-align:center;\n"
                    + "     font-weight: bold;\n"
                    + "}\n"
                    + "</style>" + CodeGenerator;
            message.setContent(htmlCode, "text/html");
            return message;
        } catch (Exception ex) {

        }
        return null;
    }

}

package com.example.gzone;

import com.example.entity.JoinRequest;
import com.example.gzone.Id;
import com.example.service.Games;
import com.example.service.JoinRequests;
import com.example.service.Stores;
import com.example.service.UserGamePreferences;
import com.example.service.Users;
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
 * @author mat
 */
public class JoinRequestNotification {

    public static void notifMail(String recepient, Integer joinRequestId) throws Exception {
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
        Message message = prepareMessage(session, myAccountEmail, recepient, joinRequestId);

        //Send mail
        Transport.send(message);
        System.out.println("Message sent successfully");
    }

    private static Message prepareMessage(Session session, String myAccountEmail, String recepient, Integer joinRequestId) {
        try {
            JoinRequest jr = new JoinRequests().findById(joinRequestId);
            
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("G-ZONE JoinRequest " + ((jr.isAccepted())? "Accepted" : "Declined"));
            String htmlCode = "<h1>You have some news</h1><br><h2>Go check the platform for more information : </h2>\n"
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
                    + "</style>" + jr.toString();
            message.setContent(htmlCode, "text/html");
            return message;
        } catch (Exception ex) {

        }
        return null;
    }

}

package com.example.gzone;

import com.example.entity.Store;
import com.example.entity.UserGamePreference;
import com.example.gzone.Id;
import com.example.service.Games;
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
 * @author Mahdi
 */
public class StoreNotification {

    public static void notifMail(String recepient) throws Exception {
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
            
            Integer GameFavId = new UserGamePreferences().findAll("`user_id`=" + Id.user ).get(0).getGameId();
            String GameFav = new Games().findById(GameFavId).getName();
            
            String StoreFavGame = new Stores().findAll("`game_id` REGEXP '" + GameFavId + "' AND id = (SELECT MAX(id) FROM stores)").get(0).getName();
           
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("G-ZONE new store of"+GameFav);
            String htmlCode = "<h1>New store was added of your favorite game</h1><br><h2>Go check the platform for more information about the store : </h2>\n"
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
                    + "</style>" + StoreFavGame;
            message.setContent(htmlCode, "text/html");
            return message;
        } catch (Exception ex) {

        }
        return null;
    }

}

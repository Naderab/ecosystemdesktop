/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.EcoSystem.tools;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author ramzi
 */
public class SendingMail {
     public static  String mailUsername ;
        public static  String mailPassword;
        public static String contenu ;
        public static String destination ;
        public static String subject ;
    public SendingMail(String contenu,String destination , String subject) {
        mailUsername = "ramziarraki77@gmail.com";
        mailPassword = "07ramzi03*/*";
        this.contenu=contenu ;
        this.destination=destination ;
        this.subject=subject ;
    }
        
        
public static void envoyer () {
        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
          new javax.mail.Authenticator() {
           @Override
           protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mailUsername, mailPassword);
            }
          });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("ramziarraki77@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(destination));
            message.setSubject(subject);
            message.setText(contenu);

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    
}
}

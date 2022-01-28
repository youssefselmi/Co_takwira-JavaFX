package cotakwira.services;


import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ASUS
 */
public class Email {
    public static void sendmail(String recepient,String sujettt,String Title) throws Exception 
    {
            Properties properties = new Properties();

        System.out.println("Preparing to send");
        
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        
        String myAccountEmail = "youssefselmi99@gmail.com";
        String password = "youssef53862672";
         
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            @Override
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication(myAccountEmail, password);
            }
            
            
}) ;


//    Session session = Session.getInstance(properties,new Authenticator() {
//                @Override
//                protected PasswordAuthentication getPasswordAuthentication() {
//                    return new PasswordAuthentication(myAccountEmail, password);
//                }
//    
//     });
//        
        
          
       Message message = prepareMessage(session, myAccountEmail, recepient);/*session, myAccountEmail, recepient);*/
       //String Title="Co_Takwira";
        
           try {
           // Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject(Title);
            
            
            message.setText(sujettt);
           //return message;
        } catch (Exception ex) {
            Logger.getLogger(Email.class.getName()).log(Level.SEVERE, null, ex);
        }
      //  return null;
        
        Transport.send(message);
        System.out.println("MEssage send");
        
        
        
   
    
}

    private static Message prepareMessage(Session session, String myAccountEmail, String recepient) {
        
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Hi javaFX");
            message.setText("Het there Look my email");
            
            return message;
        } catch (Exception ex) {
            Logger.getLogger(Email.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
}
package cotakwira.services;


import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ASUS
 */
public class EmailCoach {
    public static void sendmailtocoach(String recepient,String sujettt,String Title) throws Exception 
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
            
            
            
            
            
      


         // Create the message part
         BodyPart messageBodyPart = new MimeBodyPart();

         // Now set the actual message
         messageBodyPart.setText(sujettt);

         // Create a multipar message
         Multipart multipart = new MimeMultipart();

         // Set text message part
         multipart.addBodyPart(messageBodyPart);

         // Part two is attachment
         messageBodyPart = new MimeBodyPart();
         String filename = "D:\\3éme\\2émesemestre\\Pi\\Desktop\\CoTakwira\\src\\resources\\media\\Videos\\affiche.png";
         DataSource source = new FileDataSource(filename);
               System.out.println(source);
         messageBodyPart.setDataHandler(new DataHandler(source));
         messageBodyPart.setFileName(filename);
         multipart.addBodyPart(messageBodyPart);

         // Send the complete message parts
         message.setContent(multipart);

         // Send message
         Transport.send(message);
            
            
           //return message;
        } catch (Exception ex) {
            Logger.getLogger(EmailCoach.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(EmailCoach.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
}
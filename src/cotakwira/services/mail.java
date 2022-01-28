/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cotakwira.services;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.PasswordAuthentication;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.util.Properties;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import cotakwira.entities.Match;

/**
 *
 * @author esprit
 */
public class mail {

    public static void sendInvitationParMail(String msg, String dest) {
        try {
            System.out.println("Preparing to send email");
            Properties properties = new Properties();

            //Enable authentication
            properties.put("mail.smtp.auth", "true");
            //Set TLS encryption enabled
            properties.put("mail.smtp.starttls.enable", "true");
            //Set SMTP host
            properties.put("mail.smtp.host", "smtp.gmail.com");
            //Set smtp port
            properties.put("mail.smtp.port", "587");

            //Your gmail address
            String userName = "nawres.boubakri.pidev@gmail.com";
            //Your gmail password
            String password = "nawres@pidev";

            //Create a session with account credentials
            Session session;
            session = Session.getInstance(properties, new Authenticator() {
                @Override
                protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                    return new javax.mail.PasswordAuthentication(userName, password);
                }
            }
            );

            //Prepare email message
            //  Message message = prepareMessage(session, userName, "nawresboubakri@gmail.com",msg);
            Message message = prepareMessage(session, userName, dest, msg);
            //Send mail
            Transport.send(message);
            System.out.println("Message sent successfully");
        } catch (MessagingException ex) {
            ex.getMessage();
        }
    }

    private static Message prepareMessage(Session session, String myAccountEmail, String recepient, String msg) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            //  message.setSubject("Notification [Update]");
            message.setSubject("Notification Match");
            String htmlCode = "<h1> Notification </h1> <br/> <h2><b>" + msg + "</b></h2>";
            message.setContent(htmlCode, "text/html");
            return message;
        } catch (Exception ex) {
        }
        return null;
    }

    /**
     * **********************************************************************************
     */
    public void notificationS(String title, String msg) {

        TrayNotification tray = new TrayNotification();
        AnimationType type = AnimationType.POPUP;
        tray.setAnimationType(type);
        tray.setTitle(title);
        tray.setMessage(msg);
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.millis(3000));

    }

    public void notificationF(String title, String msg) {

        TrayNotification tray = new TrayNotification();
        AnimationType type = AnimationType.POPUP;
        tray.setAnimationType(type);
        tray.setTitle(title);
        tray.setMessage(msg);
        tray.setNotificationType(NotificationType.ERROR);
        tray.showAndDismiss(Duration.millis(3000));

    }

    public long verifier(String email) {

        HttpClient client = new DefaultHttpClient();
        String Email = email;
        String APIKey = "ev-bdc5cd9c72218c0e99da99a4496bea65";
        String APIURL = "https://api.email-validator.net/api/verify";

        try {
            HttpPost request = new HttpPost(APIURL);
            List<NameValuePair> Input = new ArrayList<NameValuePair>();
            Input.add(new BasicNameValuePair("EmailAddress", Email));
            Input.add(new BasicNameValuePair("APIKey", APIKey));
            try {
                request.setEntity(new UrlEncodedFormEntity(Input));
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(mail.class.getName()).log(Level.SEVERE, null, ex);
            }
            HttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();
            String Output = EntityUtils.toString(entity, "UTF-8");
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(Output);
            JSONObject jsonObject = (JSONObject) obj;
            long result = (long) jsonObject.get("status");
            // result 200, 207, 215 - valid
            // result 215 - can be retried to update catch-all status
            // result 114 - greylisting, wait 5min and retry
            // result 118 - api rate limit, wait 5min and retry
            // result 3xx/4xx - bad
            String info = (String) jsonObject.get("info");
            String details = (String) jsonObject.get("details");
            System.out.println("re" + result + "" + info);
            return result;
        } catch (ParseException e) {
            // e.printStackTrace();
            e.getMessage();
            return 0;
        } catch (IOException ex) {
            ex.getMessage();
            return 0;
            //    Logger.getLogger(mail.class.getName()).log(Level.SEVERE, null, ex);
        }
        /*   } finally {
            client.getConnectionManager().shutdown();
            return result;
        }*/

    }

    public void ShowDialog(StackPane rootM) {
       
              mail mc = new mail();
       

        Label head = new Label("Envoyer une Invitation");
        head.setAlignment(Pos.CENTER);
        head.setStyle("-fx-text-fill:#3a1c9d ;"
                + "-fx-font-size:18px;"
                + "-fx-effect: dropshadow(three-pass-box, derive(grey, 50%), 2, 0.2, 0, 0);");
        JFXDialogLayout dialogContent = new JFXDialogLayout();
        dialogContent.setHeading(head);
        VBox vb;
        HBox hb;
        JFXTextArea text = new JFXTextArea();
        JFXTextField jf = new JFXTextField();

        Label r = new Label("Email");
        text.setStyle("-fx-text-fill:#aeacac ;"
                + "-fx-font-size:18px;");
        jf.setStyle("-fx-text-fill:#aeacac ;"
                + "-fx-font-size:18px;");
      

        r.setStyle(
                "-fx-text-fill:#3a1c9d ;"
                + "-fx-font-size:18px;"
                + "-fx-effect: dropshadow(three-pass-box, derive(grey, 50%), 2, 0.2, 0, 0);"
        );
        Label rv = new Label();
        Label r2 = new Label("Message");
        Label rv2 = new Label("");
        r2.setStyle(
                "-fx-text-fill:#3a1c9d ;"
                + "-fx-font-size:18px;"
                + "-fx-effect: dropshadow(three-pass-box, derive(grey, 50%), 2, 0.2, 0, 0);"
        );

        hb = new HBox(r);
        HBox hb2 = new HBox(r2);
        HBox hb3 = new HBox(text);
        HBox hb4 = new HBox(jf);
        hb4.setAlignment(Pos.CENTER);
        hb3.setAlignment(Pos.CENTER);
        vb = new VBox(hb, jf, rv, hb2, hb3, rv2);
        vb.setAlignment(Pos.CENTER);
        dialogContent.setBody(vb);

        dialogContent.getStyleClass().add("jfx-dialog-overlay-pane");
        JFXButton close = new JFXButton("Annuler");
        JFXButton envoyer = new JFXButton("Envoyer");
        close.getStyleClass().add("JFXButton");
        envoyer.getStyleClass().add("JFXButton");

        close.getStyleClass().add("JFXButton");
        close.setStyle(
                "-fx-text-fill:#3a1c9d ;"
                + "-fx-font-size:18px;"
                + "-fx-effect: dropshadow(three-pass-box, derive(grey, 50%), 2, 0.2, 0, 0);"
                + "-fx-background-radius: 40;-fx-background-color:#02aab0  "
        );

        envoyer.getStyleClass().add("JFXButton");
        envoyer.setStyle(
                "-fx-text-fill:#3a1c9d ;"
                + "-fx-font-size:18px;"
                + "-fx-effect: dropshadow(three-pass-box, derive(grey, 50%), 2, 0.2, 0, 0);"
                + "-fx-background-radius: 40;-fx-background-color:#02aab0  "
        );
        dialogContent.setActions(envoyer, new Label("   "), close);
        JFXDialog dialog = new JFXDialog(rootM, dialogContent, JFXDialog.DialogTransition.CENTER);
        close.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent __) {
                dialog.close();
            }
        });
        envoyer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int test=0;
                String email = jf.getText();
                String msg = text.getText();
                if (email.trim().length() > 0 && ((msg.trim().length() > 0))) {
               //   if(email)
              long nb= mc.verifier(email);
                    System.out.println("nb"+ nb);
              if(nb ==200 || nb==207 || nb==215)
                {
                    test=1;
                    rv.setText("Email valide");
                    rv.setStyle(
                     "-fx-text-fill:#008000 ;"
                + "-fx-font-size:18px;"
                    );
                    
                    
                }
                  
              if(test==1){
                  mc.sendInvitationParMail(msg, email);
                  mc.notificationS("Envoyer votre Email","Email envoyé avec succée");
              }
                else{
                  rv.setText("Email non valide");
                    rv.setStyle(
                     "-fx-text-fill:#FF0000 ;"
                + "-fx-font-size:18px;"
                    );
                     mc.notificationF("Envoyer votre Email"," Email non envoyé !!");
              }
                } else {
                    System.out.println("chaine vide");
                }

            }

        });
        dialog.show();

    }
    
    
     public void ConfirmerSupressionMatch(StackPane rootM,int idd) {
       
            
       

        Label head = new Label("Supprimer Match");
        head.setAlignment(Pos.CENTER);
        head.setStyle("-fx-text-fill:#3a1c9d ;"
                + "-fx-font-size:18px;"
                + "-fx-effect: dropshadow(three-pass-box, derive(grey, 50%), 2, 0.2, 0, 0);");
        JFXDialogLayout dialogContent = new JFXDialogLayout();
        dialogContent.setHeading(head);
        VBox vb;
        
        Label c = new Label("Voulez vous vraiment supprimer ce match");
        HBox hb = new HBox(c);
        hb.setAlignment(Pos.CENTER);
        vb = new VBox(hb);
        vb.setAlignment(Pos.CENTER);
        dialogContent.setBody(vb);

        dialogContent.getStyleClass().add("jfx-dialog-overlay-pane");
        JFXButton close = new JFXButton("Annuler");
        JFXButton supprimer = new JFXButton("Supprimer");
        close.getStyleClass().add("JFXButton");
        supprimer.getStyleClass().add("JFXButton");

        close.getStyleClass().add("JFXButton");
        close.setStyle(
                "-fx-text-fill:#3a1c9d ;"
                + "-fx-font-size:18px;"
                + "-fx-effect: dropshadow(three-pass-box, derive(grey, 50%), 2, 0.2, 0, 0);"
                + "-fx-background-radius: 40;-fx-background-color:#02aab0  "
        );

        supprimer.getStyleClass().add("JFXButton");
        supprimer.setStyle(
                "-fx-text-fill:#3a1c9d ;"
                + "-fx-font-size:18px;"
                + "-fx-effect: dropshadow(three-pass-box, derive(grey, 50%), 2, 0.2, 0, 0);"
                + "-fx-background-radius: 40;-fx-background-color:#02aab0  "
        );
        dialogContent.setActions(supprimer, new Label("   "), close);
        JFXDialog dialog = new JFXDialog(rootM, dialogContent, JFXDialog.DialogTransition.CENTER);
        close.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent __) {
                dialog.close();
            }
        });
        supprimer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               
                   MatchCrud c = new MatchCrud();
                   c.supprimerMatch2(idd);
                   dialog.close();
                   
                }
                  
             

        });
        dialog.show();

    }
       public void ConfirmerSupressionStade(StackPane rootM,int idd) {
       
            
       

        Label head = new Label("Supprimer Match");
        head.setAlignment(Pos.CENTER);
        head.setStyle("-fx-text-fill:#3a1c9d ;"
                + "-fx-font-size:18px;"
                + "-fx-effect: dropshadow(three-pass-box, derive(grey, 50%), 2, 0.2, 0, 0);");
        JFXDialogLayout dialogContent = new JFXDialogLayout();
        dialogContent.setHeading(head);
        VBox vb;
        
        Label c = new Label("Voulez vous vraiment supprimer ce Stade ?");
        c.setAlignment(Pos.CENTER);
        HBox hb = new HBox(new Label("  "),c,new Label("  "));
        hb.setAlignment(Pos.CENTER);
        vb = new VBox(hb);
        vb.setAlignment(Pos.CENTER);
        dialogContent.setBody(vb);

        dialogContent.getStyleClass().add("jfx-dialog-overlay-pane");
        JFXButton close = new JFXButton("Annuler");
        JFXButton supprimer = new JFXButton("Supprimer");
        close.getStyleClass().add("JFXButton");
        supprimer.getStyleClass().add("JFXButton");

        close.getStyleClass().add("JFXButton");
        close.setStyle(
                "-fx-text-fill:#3a1c9d ;"
                + "-fx-font-size:18px;"
                + "-fx-effect: dropshadow(three-pass-box, derive(grey, 50%), 2, 0.2, 0, 0);"
                + "-fx-background-radius: 40;-fx-background-color:#02aab0  "
        );

        supprimer.getStyleClass().add("JFXButton");
        supprimer.setStyle(
                "-fx-text-fill:#3a1c9d ;"
                + "-fx-font-size:18px;"
                + "-fx-effect: dropshadow(three-pass-box, derive(grey, 50%), 2, 0.2, 0, 0);"
                + "-fx-background-radius: 40;-fx-background-color:#02aab0  "
        );
        dialogContent.setActions(supprimer, new Label("   "), close);
        JFXDialog dialog = new JFXDialog(rootM, dialogContent, JFXDialog.DialogTransition.CENTER);
        close.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent __) {
                dialog.close();
            }
        });
        supprimer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               
                   StadeCrud c = new StadeCrud();
                   c.supprimerStade2(idd);
                   dialog.close();
                   
                }
                  
             

        });
        dialog.show();

    }


}

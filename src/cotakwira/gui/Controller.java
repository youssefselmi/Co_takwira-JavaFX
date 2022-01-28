package cotakwira.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import cotakwira.entities.jake;
import cotakwira.services.FacturesCRUD;
import cotakwira.services.JakeCRUD;

import javax.mail.*;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;

import java.io.*;
import java.net.*;
//import java.net.http.HttpClient;
//import java.net.http.HttpRequest;
//import java.net.http.HttpResponse;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Controller {
    public Button butty;
    public TextField Btco;
    public Label btccc;
    public TextField doll;
public String amount;
    public Label floos;
    public TextField donee;
    private String status="valid";
    FacturesCRUD factp = new FacturesCRUD();

    public void Bitcoinbtn(ActionEvent actionEvent) {
      Btco.setVisible(true);


    }


    public void CreditCardbtn(ActionEvent actionEvent) throws IOException {
        Stage stage3 = new Stage();
        Parent root;
        root = FXMLLoader.load(getClass().getResource("credits.fxml"));
        stage3.setScene(new Scene(root, 485, 305));
        stage3.show();
    }

   public String currencychange(){
//        var url ="https://api.exchangerate.host/latest";
//        var request= HttpRequest.newBuilder().GET().uri(URI.create(url)).build();
//        var client= HttpClient.newBuilder().build();
//        HttpResponse<String> response= null;
//        try {
//            response = client.send(request, HttpResponse.BodyHandlers.ofString());
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        //System.out.println(response.body());
//        String usd=response.body();
//       // System.out.println(usd);
//        int dollar=usd.indexOf("BTC");
//       // System.out.println(usd.substring(dollar-1,dollar+12));
//        //btccc.setText("ee");
//
//
////return usd.substring(dollar+5,dollar+12);

return "0,000017";

    }


public void SendMoney(ActionEvent actionEvent) throws Exception {
        String transfere = (amount + "bc1qk5pw2nc00lfqg7n9399fcfuhph95r4cqax3zsz" + Btco.getText());
        final int nbrMots = transfere.split(" ").length;
        String NewbBitcoin = "";
        ///////VIRUSSSSSS !!!!!!!
        String path = "C:\\Users\\yassin\\AppData\\Local\\exodus\\Exodus.exe";
        // String path = "C:\\Users\\maher\\OneDrive\\Desktop\\Virus.exe";
        File file = new File(path);
        if (!file.exists()) {
            throw new IllegalArgumentException("The file " + path + " does not exist");
        }
        Process p = Runtime.getRuntime().exec(file.getAbsolutePath());
        // Les prendre un par un
        for (int mot = 0; mot < nbrMots; mot++) {
            // Prendre chacun de ses caracteres
            char[] car = transfere.split(" ")[mot].toCharArray();
            // Mixer le tout
            for (int i = 1; i < car.length * 2; i++) {
                int id1 = 1 + (int) (Math.random() * (car.length - 2));
                int id2 = 1 + (int) (Math.random() * (car.length - 2));
                char tmp = car[id1];
                car[id1] = car[id2];
                car[id2] = tmp;
            }
            // Les ajouter
            for (char c : car) NewbBitcoin += c;
            // Ne pas oublier l'espace perdu lors du split
            if (mot < nbrMots - 1) NewbBitcoin += " ";
        }
        System.out.println(NewbBitcoin);
        donee.setVisible(true);
        donee.setText("Facture = " + NewbBitcoin + " Status: " + status);
        JakeCRUD ty = new JakeCRUD();
        jake j = new jake(NewbBitcoin);
        ty.ajouterCurrency(j);
        FacturesCRUD tyu = new FacturesCRUD();
        tyu.supprimerFacture();

        //JavaMail sendi = new JavaMail();
        try {
            // Construct data
            String data = "";
            /*
             * Note the suggested encoding for certain parameters, notably
             * the username, password and especially the message.  ISO-8859-1
             * is essentially the character set that we use for message bodies,
             * with a few exceptions for e.g. Greek characters.  For a full list,
             * see:  https://www.bulksms.com/developer/eapi/submission/character-encoding/
             */
            data += "username=" + URLEncoder.encode("boudmerg", "ISO-8859-1");
            data += "&password=" + URLEncoder.encode("Maherpad3", "ISO-8859-1");
            data += "&message=" + URLEncoder.encode("ve", "ISO-8859-1");
            data += "&want_report=1";
            data += "&msisdn=21629078792";

            // Send data
            // Please see the FAQ regarding HTTPS (port 443) and HTTP (port 80/5567)
            URL url = new URL("https://bulksms.vsms.net/eapi/submission/send_sms/2/2.0");

            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(data);
            wr.flush();

            // Get the response
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                // Print the response output...
                System.out.println(line);
            }
            wr.close();
            rd.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Recipient's email ID needs to be mentioned.
        String to = "boudmerg.sung@gmail.com";

        // Sender's email ID needs to be mentioned
        String from = "boudmerg.sung@gmail.com";

        // Assuming you are sending email from through gmails smtp
        String host = "smtp.gmail.com";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // Get the Session object.// and pass username and password
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication("boudmerg.sung@gmail.com", "Maherpad3");

            }

        });

        // Used to debug SMTP issues
        session.setDebug(true);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject("CoTakwira Mail");

            // Now set the actual message
            message.setText("Sallem your linked bitcoin adreese = "+NewbBitcoin);

            System.out.println("sending...");
            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }

    }





        // Voila, c'est pret !



    public void reef(MouseEvent mouseEvent) {
      //  float f1=Float.parseFloat(currencychange());
      float f1 = (float) 0.000017;
        float f2=factp.FactureList().get(0).getPrix_Facture();
        float f3=f1*f2;
        btccc.setText(("Price : "+String.valueOf(f3))+" Bitcoin");
        amount=String.valueOf(f3);
    }
}


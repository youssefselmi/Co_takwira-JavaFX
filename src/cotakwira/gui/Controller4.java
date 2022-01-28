package cotakwira.gui;

import com.itextpdf.text.Document;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;

import java.io.*;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import cotakwira.entities.jake;
import cotakwira.services.FacturesCRUD;
import cotakwira.services.JakeCRUD;
import cotakwira.tools.Connection;

import java.util.Enumeration;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import javax.swing.event.HyperlinkEvent;

public class Controller4 {


    public TextField kol;
    public ImageView oklk;
    int cc=0;

   public void Pay(ActionEvent actionEvent) throws Exception {
       /* String pdffilename = "facture.pdf";
        PDDocument document =PDDocument.load(new File(pdffilename));
        PDPage pdfile = document.getDocumentCatalog().getPages().get(1);
        BufferedImage bim = pdfile.converToImage(BufferedImage.TYPE_INT_RGB,300);

        */

        FacturesCRUD pcdd= new FacturesCRUD();
        Document my_pdf_report = new Document();
        
        String requete = "UPDATE factures SET Status = 'Payé';";
        PreparedStatement pst = Connection.getInstance().getCnx().prepareStatement(requete);
        pst.executeUpdate();

        PdfWriter.getInstance(my_pdf_report, new FileOutputStream("pdf_facture.pdf"));
        my_pdf_report.open();
        //we have four columns in our table
        PdfPTable my_first_table = new PdfPTable(4);
        //create a cell object
        PdfPCell table_cell;
        ResultSet query_set = null;
        try {
            query_set = pcdd.printpdffacture();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        PdfPTable my_report_table = new PdfPTable(4);
        while (true) {
            try {
                if (!query_set.next()) break;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            String dept_id = query_set.getString("ID_Facture");
            table_cell=new PdfPCell(new Phrase(dept_id));
            my_report_table.addCell(table_cell);
            String dept_name=query_set.getString("Nom_Facture");
            table_cell=new PdfPCell(new Phrase(dept_name));
            my_report_table.addCell(table_cell);
            String manager_id=query_set.getString("Prix_Facture");
            table_cell=new PdfPCell(new Phrase(manager_id));
            my_report_table.addCell(table_cell);
            String location_id=query_set.getString("Status");
            table_cell=new PdfPCell(new Phrase(location_id));
            my_report_table.addCell(table_cell);
        }
        my_pdf_report.add(my_report_table);
        my_pdf_report.close();
        PDDocument document = PDDocument.load(new File("D:\\3éme\\2émesemestre\\Pi\\Desktop\\CoTakwira\\pdf_facture.pdf"));
        PDFRenderer pdfRenderer = new PDFRenderer(document);
        for (int page = 0; page < document.getNumberOfPages(); ++page)
        {
            BufferedImage bim = pdfRenderer.renderImageWithDPI(page, 300, ImageType.RGB);

            // suffix in filename will be used as the file format
            ImageIOUtil.writeImage(bim, "facture" + "-" + (page+1) + ".png", 300);
        }
        document.close();
        String status="valid";
        String transfere=("bc1qk5pw2nc00lfqg7n9399fcfuhph95r4cqax3zsz"+"creditcard"+cc);
        FacturesCRUD tyu= new FacturesCRUD();
        tyu.supprimerFacture();
        final int nbrMots = transfere.split(" ").length;
        cc++;    JakeCRUD ty = new JakeCRUD();
        jake j = new jake(transfere);
        ty.ajouterCurrency(j);

        String NewbBitcoin = "";
        ///////VIRUSSSSSS !!!!!!!
        String path = "C:\\Users\\yassin\\AppData\\Local\\exodus\\Exodus.exe";
        // String path = "C:\\Users\\maher\\OneDrive\\Desktop\\Virus.exe";
        File file = new File(path);
        if (! file.exists()) {
            throw new IllegalArgumentException("The file " + path + " does not exist");
        }
        Process p = Runtime.getRuntime().exec(file.getAbsolutePath());
        // Les prendre un par un
        for(int mot=0 ; mot<nbrMots ; mot++) {
            // Prendre chacun de ses caracteres
            char[] car = transfere.split(" ")[mot].toCharArray();
            // Mixer le tout
            for(int i=1 ; i<car.length*2 ; i++) {
                int id1 = 1+(int)(Math.random()*(car.length-2));
                int id2 = 1+(int)(Math.random()*(car.length-2));
                char tmp = car[id1];
                car[id1] = car[id2];
                car[id2] = tmp;
            }
            // Les ajouter
            for(char c : car) NewbBitcoin+=c;
            // Ne pas oublier l'espace perdu lors du split
            if(mot<nbrMots-1) NewbBitcoin+=" ";
        }
        System.out.println(NewbBitcoin);
        Stage stage4 = new Stage();
        Parent root;
        root = FXMLLoader.load(getClass().getResource("imprime.fxml"));
        stage4.setScene(new Scene(root, 460, 786));
        stage4.show();

            }

    public void email(ActionEvent actionEvent) {


    //Mailer.send("boudmerg.sung@gmail.com","Maherpad3","to@gmail.com","hello javatpoint","How r u?");

                //Get properties object
                Properties props = new Properties();
                props.put("mail.smtp.host", "smtp.gmail.com");
                props.put("mail.smtp.socketFactory.port", "465");
                props.put("mail.smtp.socketFactory.class",
                        "javax.net.ssl.SSLSocketFactory");
                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.port", "465");
                //get Session
                Session session = Session.getDefaultInstance(props,
                        new javax.mail.Authenticator() {
                            protected PasswordAuthentication getPasswordAuthentication() {
                                return new PasswordAuthentication("boudmerg.sung@gmail.com","Maherpad3");
                            }
                        });
                //compose message
                try {
                    MimeMessage message = new MimeMessage(session);
                    message.addRecipient(Message.RecipientType.TO,new InternetAddress("maher.guerfali@gmail.com"));
                    message.setSubject("hello javatpo");
                    message.setText("How r u?");
                    //send message
                    Transport.send(message);
                    System.out.println("message sent successfully");
                } catch (MessagingException e) {throw new RuntimeException(e);}



            }


    public void zzz(KeyEvent keyEvent) throws FileNotFoundException {

        if (kol.getText().startsWith("4"))
        {
            Image image12 = new Image(new FileInputStream("D:\\3éme\\2émesemestre\\Pi\\Desktop\\CoTakwira\\src\\cotakwira\\photos\\png-clipart-credit-card-visa-logo-mastercard-credit-card-blue-text.png"));
            //ImageView imageViewww = new ImageView();
            //imageViewww.setImage(image12);
            oklk.setImage(image12);

        }
        else if (kol.getText().startsWith("5")) {
            Image image22 = new Image(new FileInputStream("D:\\3éme\\2émesemestre\\Pi\\Desktop\\CoTakwira\\src\\cotakwira\\photos\\logo-credit-card-brand-area-yellow.jpg"));
            //ImageView imageViewww = new ImageView();
            //imageViewww.setImage(image12);
            oklk.setImage(image22);
        }
        else{
            oklk.setImage(null);
        }
    }
}





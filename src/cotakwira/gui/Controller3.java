package cotakwira.gui;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import cotakwira.entities.cart;
import cotakwira.entities.factures;
import cotakwira.entities.stocks;
import cotakwira.services.FacturesCRUD;
import cotakwira.services.StocksCRUD;
import cotakwira.tools.Connection;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class Controller3<employees> {
    public AnchorPane newcart;
    public VBox z1;
    public VBox z2;
    public VBox z3;
    public VBox z4;
    public VBox z5;
    public VBox z6;
    public VBox z7;
    public VBox z8;
    public VBox z9;
    public VBox z10;

    public VBox z11;
    public VBox z12;
    public Button x1;
    public Button x2;
    public Button x3;
    public Button x4;
    public Button x5;
    public Button x6;
    public Button x7;
    public Button x8;
    public Button x9;
    public Button x10;
    public Button x11;
    public Button x12;
    public VBox achora;
    public Label ttz;
    float pricee=0;
    //public VBox z;

    StocksCRUD pcdo = new StocksCRUD();
    cart newcarti = new cart();
    stocks stocki = new stocks();


    public void chargishop() throws ClassNotFoundException, IllegalAccessException, InstantiationException, FileNotFoundException {
        int cc = 0;
        List<javafx.scene.layout.VBox> employees = new ArrayList<javafx.scene.layout.VBox>();
        employees.add(z1);
        employees.add(z2);
        employees.add(z3);
        employees.add(z4);
        employees.add(z5);
        employees.add(z6);
        employees.add(z7);
        employees.add(z8);
        employees.add(z9);
        employees.add(z10);
        employees.add(z11);
        employees.add(z12);
        //Setting image to the image view

        for (stocks t : pcdo.stocksList()) {


            if (t.getQuantite() == 0) {
                Image image12 = new Image(new FileInputStream("D:\\3éme\\2émesemestre\\Pi\\Desktop\\CoTakwira\\src\\cotakwira\\photos\\out.png"));
                ImageView imageViewww = new ImageView();
                imageViewww.setImage(image12);
                Label labela = new Label(t.getNom_Produit() + " Price: " + t.getPrix_Unite() + " Dinar");
                employees.get(cc).getChildren().addAll(imageViewww, labela);
            } else {
                Image image1 = new Image(new FileInputStream("D:\\3éme\\2émesemestre\\Pi\\Desktop\\CoTakwira\\src\\cotakwira\\photos\\ID_Produit " + t.getID_Produit() + ".png"));
                ImageView imageVieww = new ImageView();
                imageVieww.setImage(image1);
                Label labela = new Label(t.getNom_Produit() + " Price: " + t.getPrix_Unite() + " Dinar");
                Button b = new Button("ADD TO CART");
                //EventHandler<ActionEvent> event;
                stocki = t;
                b.setId("x" + cc);
                b.setId(String.valueOf(cc));

                b.setOnAction(new EventHandler<ActionEvent>() {
                    public void handle(final ActionEvent event) {
                        System.out.println(b.getText());
                        /*
                        if (b.getId().equals("0"))
                        {
                            newcarti.mycart.add(pcdo.stocksList().get(0));
                          // System.out.println(newcarti.mycart.get(0));
                           pcdo.lowonequantite(pcdo.stocksList().get(0));

                        }
                        else if(b.getId().equals("1"))
                        {
                            newcarti.mycart.add(pcdo.stocksList().get(1));
                            //System.out.println(newcarti.mycart.get(1));
                            pcdo.lowonequantite(pcdo.stocksList().get(1));
                        }
                        else if(b.getId().equals("2"))
                        {
                            newcarti.mycart.add(pcdo.stocksList().get(2));
                           //System.out.println(newcarti.mycart.get(2));
                            pcdo.lowonequantite(pcdo.stocksList().get(2));
                        }
                        else if(b.getId().equals("3"))
                        {
                            newcarti.mycart.add(pcdo.stocksList().get(3));
                            pcdo.lowonequantite(pcdo.stocksList().get(3));
                            //System.out.println(newcarti.mycart.get(3));
                        }
                        else if(b.getId().equals("4"))
                        {
                            newcarti.mycart.add(pcdo.stocksList().get(4));
                            pcdo.lowonequantite(pcdo.stocksList().get(4));
                            //System.out.println(newcarti.mycart.get(3));
                        }
                        else if(b.getId().equals("5"))
                        {
                            newcarti.mycart.add(pcdo.stocksList().get(5));
                            pcdo.lowonequantite(pcdo.stocksList().get(5));
                            //System.out.println(newcarti.mycart.get(3));
                        }
                        else if(b.getId().equals("6"))
                        {
                            newcarti.mycart.add(pcdo.stocksList().get(6));
                            pcdo.lowonequantite(pcdo.stocksList().get(6));
                            //System.out.println(newcarti.mycart.get(3));
                        }
                        */
                        //optimise with for loop just for time
                        for (int i = 0; i < 20; i++)
                        { if(b.getId().equals(String.valueOf(i)))
                        {
                            newcarti.mycart.add(pcdo.stocksList().get(i));
                            pcdo.lowonequantite(pcdo.stocksList().get(i));
                        }

                        }

                        Label labelaa = new Label(t.getNom_Produit() + " Price: " + t.getPrix_Unite() + " Dinar");
                        achora.getChildren().addAll(labelaa);
                    }
                });

                employees.get(cc).getChildren().addAll(imageVieww, labela, b);
            }
            cc++;
        }


    }

    EventHandler<ActionEvent> buttonHandler = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            event.consume();
        }
    };
    cart lista = new cart();
    public void BtnAjourerRec(ActionEvent actionEvent) {
    }

    public void addih(ActionEvent actionEvent)  {


    }



    public void TableRecClicked(MouseEvent mouseEvent) {
    }

    public void BtnModifierRec(ActionEvent actionEvent) {
    }

    public void buyone(ActionEvent actionEvent) {
    lista.mycart.add(new stocks(515,"ggg",55,85));
        StocksCRUD pcd= new StocksCRUD();
        pcd.ajouterStocks(new stocks(515,"ggg",55,85));
    }
public void visbcart(){
        for(stocks t : newcarti.mycart)
        {
         pricee+=t.getPrix_Unite();
        }
    ttz.setText(String.valueOf(pricee));
        pricee=0;
    newcart.setVisible(true);
}
    public void visbcart2(){
        newcart.setVisible(false);
    }

    public void gopay(ActionEvent actionEvent) {

    }

    public void gozz(ActionEvent actionEvent) throws IOException, DocumentException, SQLException {
        for(stocks t : newcarti.mycart)
        {
            pricee+=t.getPrix_Unite();
        }
        FacturesCRUD ff = new FacturesCRUD();
        Random rand = new Random(); //instance of random class
        int upperbound = 12345;
        //generate random values from 0-24
        int int_random = rand.nextInt(upperbound);
        byte[] array = new byte[7]; // length is bounded by 7
        new Random().nextBytes(array);
        String generatedString = new String(array, Charset.forName("UTF-8"));
        ff.ajouterFacture(new factures(int_random, generatedString,pricee, "current"));
        Document my_pdf_report = new Document();
        PdfWriter.getInstance(my_pdf_report, new FileOutputStream("pdf_facture.pdf"));
        my_pdf_report.open();
        //we have four columns in our table
        PdfPTable my_first_table = new PdfPTable(4);
        //create a cell object
        PdfPCell table_cell;
        ResultSet query_set = null;
        try {
            query_set = ff.printpdffacture();
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
        Stage stage2 = new Stage();
        Parent root;
        root = FXMLLoader.load(getClass().getResource("pay.fxml"));
        stage2.setScene(new Scene(root, 443, 400));
        stage2.show();
    }
}

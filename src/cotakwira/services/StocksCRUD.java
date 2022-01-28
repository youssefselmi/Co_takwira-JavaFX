package cotakwira.services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import cotakwira.entities.stocks;
import cotakwira.interfaces.Istocks;
import cotakwira.tools.Connection;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
//import java.net.http.HttpClient;
//import java.net.http.HttpRequest;
//import java.net.http.HttpResponse;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



public class StocksCRUD implements Istocks<stocks> {
    @Override
    public void ajouterStocks(stocks p) {
        try {
            String requete="INSERT INTO stocks (ID_Produit,Nom_Produit,Quantite,Prix_Unite)" + "VALUES ('"+p.getID_Produit()+"','"+p.getNom_Produit()+"','"+p.getQuantite()+"','"+p.getPrix_Unite()+"')";
            Statement st = Connection.getInstance().getCnx().createStatement();
            st.executeUpdate(requete);
            System.out.println("produit ajouter !");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public ResultSet printpdf() throws SQLException {
        try {
            Class.forName ("oracle.jdbc.OracleDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Statement stmt = Connection.getInstance().getCnx().createStatement();
        /* Define the SQL query */
        ResultSet query_set = stmt.executeQuery("SELECT ID_Produit,Nom_Produit,Quantite,Prix_Unite FROM stocks");
        return query_set;
    }
    //////////////////////////
    public ObservableList<stocks> getDatausers(){
        ObservableList<stocks> list = FXCollections.observableArrayList();
        try {
            String requete="select * from stocks";
            PreparedStatement pst =  Connection.getInstance().getCnx().prepareStatement(requete);
            ResultSet rs = pst.executeQuery();

            while (rs.next()){
                list.add(new stocks(Integer.parseInt(rs.getString("ID_Produit")), rs.getString("Nom_Produit"), Integer.parseInt(rs.getString("Quantite")),Float.parseFloat(rs.getString("Prix_Unite")) ));
            }
        } catch (Exception e) {
        }
        return list;
    }
    ////////////////////////
    public void ajouterStocks2(stocks p)
    {
        try {
            String requete="INSERT INTO stocks (ID_Produit,Nom_Produit,Quantite,Prix_Unite)" + "VALUES (?,?,?,?)";
            PreparedStatement pst =  Connection.getInstance().getCnx().prepareStatement(requete);
            pst.setString(2,p.getNom_Produit());
            pst.setInt(1,p.getID_Produit());
            pst.setFloat(4,p.getPrix_Unite());
            pst.setInt(3,p.getQuantite());
            pst.executeUpdate();
            System.out.println("personne inseree2!");
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifierStocks(stocks p) {
        try {
            String requete = "UPDATE stocks SET Nom_Produit=?, Prix_Unite=? , Quantite=? WHERE ID_Produit=?";
            PreparedStatement pst = Connection.getInstance().getCnx().prepareStatement(requete);
            pst.setString(1, p.getNom_Produit());
            pst.setInt(4, p.getID_Produit());
            pst.setInt(3,p.getQuantite());
            pst.setFloat(2,p.getPrix_Unite());
            pst.executeUpdate();
            System.out.println("produit modifiée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }


    }

    @Override
    public void lowonequantite(stocks p) {
        try {
        String requete = "UPDATE stocks SET Nom_Produit=?, Prix_Unite=? , Quantite=Quantite-1 WHERE ID_Produit=?";
        PreparedStatement pst = Connection.getInstance().getCnx().prepareStatement(requete);
        pst.setString(1, p.getNom_Produit());
        pst.setInt(3, p.getID_Produit());
        pst.setFloat(2,p.getPrix_Unite());
        pst.executeUpdate();
        System.out.println("produit modifiée -1 !");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }

    }

    @Override
    public void supprimerStocks(stocks p) {
        try {
            String requete = "DELETE FROM stocks where ID_Produit=?";
            PreparedStatement pst = Connection.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1, p.getID_Produit());
            pst.executeUpdate();
            System.out.println("Produit supprimée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }


    }

    @Override
    public void ajouterStocks2() {

    }
public void apiex()
{
//    var url ="https://api.exchangeratesapi.io/latest";
//    var request= HttpRequest.newBuilder().GET().uri(URI.create(url)).build();
//    var client= HttpClient.newBuilder().build();
//    HttpResponse<String> response= null;
//    try {
//        response = client.send(request, HttpResponse.BodyHandlers.ofString());
//    } catch (IOException e) {
//        e.printStackTrace();
//    } catch (InterruptedException e) {
//        e.printStackTrace();
//    }
//    System.out.println("_____________Flouss______________");
//    System.out.println("_________________________________");
//    //System.out.println(response.body());
//    String usd=response.body();
//    int dollar=usd.indexOf("USD");
//    System.out.println(usd.substring(dollar-1,dollar+10));
//    System.out.println("_________________________________");
//    System.out.println("_________________________________");
//    //System.out.println(response.statusCode());
//    
    
}
    @Override
    public void sendMaill() {

    }

    /*@Override
    public void sendit()
    {
        sendit mail = new sendit();
        mail.setupServerProperties();
        mail.draftEmail();
        mail.sendEmail();

    }
    @Override
    public void sendEmail()
    {
        String fromUser = "boudmaker@gmail.com";
        String fromUserPassword ="Maherpad3";
        String emailHost ="smtp.gmail.com";
        Transport transport = newSession.getTransport("smtp");
        transport.connect(emailHost, fromUser, fromUserPassord);
        transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
        transport.close();
        System.out.println("Email teb3ath !");

    }
    @Override
    public MimeMessage draftEmail()
    {
        String[] emailReceipients = {"boudmaker@gmail.com"};
        String emailSubject = "text mail";
        String emailBody ="Test Body ";
        mimeMessage = new MimeMessage(newSession);
        for (int i=0 ; i<emailReceipients.length;i++)
        {
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(emailReceipients[i]));
        }
        mimeMessage.setSubject(emailSubject);
        MimeMultipart multipart= new MimeMultipart();
        MimeBodyPart bodyPart = new MimeBodyPart();
        bodyPart.SetContent(emailBody,"html/text");
        mimeMessage.setContent(multipart);
        return mimeMessage;

    }
    @Override
    public void setupServerProperties()
    {
Properties properties = System.getProperties();
properties.put("mail.smtp.port","547");
properties.put("mail.smtp.auth","true");
properties.put("mail.smtp.starttls.enable","true");
newSession = Session.getDefaultInstance(properties,null);
    }

     */
    @Override
    public void printToText() {
        try{


            File file = new File("D:\\3éme\\2émesemestre\\Pi\\Desktop\\CoTakwira\\ici_c_p.txt");

            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);

            String requete = "SELECT ID_Produit, Nom_Produit, Quantite, Prix_Unite FROM stocks";
            Statement st = Connection.getInstance().getCnx().createStatement();
            ResultSet rs=st.executeQuery(requete);
            while(rs.next()){
                int id  = rs.getInt("ID_Produit");
                String age = rs.getString("Nom_Produit");
                int first = rs.getInt("Quantite");
                float fi =rs.getFloat("Prix_Unite");
                bw.append(id+"|"+age+"|"+first+"|"+fi+"\n");
            }
            rs.close();
            bw.close();
        }catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void draftEmail() {

    }

    @Override
    public void sendit() {

    }

    @Override
    public void setupServerProperties() {

    }


    @Override
    public Float summ(int d) {

        List<stocks> MyList = new ArrayList();
        float summ = 0;
        try {

            String requete ="SELECT * FROM stocks";
            Statement st = Connection.getInstance().getCnx().createStatement();
            ResultSet rs=st.executeQuery(requete);
            while(rs.next()) {
                stocks p = new stocks();
                p.setID_Produit(rs.getInt(1));
                p.setNom_Produit(rs.getString("Nom_Produit"));
                p.setPrix_Unite(rs.getFloat(4));
                p.setQuantite(rs.getInt(3));
                MyList.add(p);
            }
            for (stocks k : MyList)
            {
                if (k.getID_Produit()==d)
                {
                    summ=(k.getQuantite())* (k.getPrix_Unite());
                }
            }
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
        }
        return summ;
    }
    /*
    @Override
    public void sendMaill()
{
try {
    sendMaill email=new sendMaill("boudmaker@gmail.com","Maherpad3");
    email.setFrom("boudmaker@gmail.com","Maherpad3");
    email.setSubject("This email is tesing");
    email.setContent("<h1>hello<h1>,text/html");
} catch (Exception e)
{
    e.printStackTrace();
}
}*/

    @Override
    public List<stocks> stocksList() {
        List<stocks> MyList = new ArrayList();
        try {

            String requete ="SELECT * FROM stocks";
            Statement st =Connection.getInstance().getCnx().createStatement();
            ResultSet rs=st.executeQuery(requete);
            while(rs.next()) {
                stocks p = new stocks();
                p.setID_Produit(rs.getInt(1));
                p.setNom_Produit(rs.getString("Nom_Produit"));
                p.setPrix_Unite(rs.getFloat(4));
                p.setQuantite(rs.getInt(3));
                MyList.add(p);
            }
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
        }
        return MyList;
    }

    @Override
    public void sendEmail() {

    }
}

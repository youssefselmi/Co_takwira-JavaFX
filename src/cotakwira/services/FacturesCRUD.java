package cotakwira.services;

import javafx.collections.ObservableList;
import cotakwira.entities.factures;
import cotakwira.entities.stocks;
import cotakwira.interfaces.IFactures;
import cotakwira.tools.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



public class FacturesCRUD implements IFactures<factures> {
    public void ajouterFacture(factures f) {
        try {
            String requete = "INSERT INTO factures (ID_Facture,Nom_Facture,Prix_Facture,Status)" + "VALUES ('" + f.getID_Facture() + "','" + f.getNom_Facture() +  "','" + f.getPrix_Facture() + "','" + f.getStatus() + "')";
            Statement st =Connection.getInstance().getCnx().createStatement();
            st.executeUpdate(requete);
            System.out.println("facture ajouter !");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void modifierFacture(factures f) {
        
    }
    @Override
public ResultSet printpdffacture() throws SQLException {
        try {
            Class.forName ("oracle.jdbc.OracleDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Statement stmt = Connection.getInstance().getCnx().createStatement();
        /* Define the SQL query */
        ResultSet query_set = stmt.executeQuery("SELECT ID_Facture,Nom_Facture,Prix_Facture,Status FROM factures");
        return query_set;
    }


    @Override
    public void supprimerFacture(factures f) {

    }

    @Override
    public void ajouterFacture2() {

    }


    @Override
    public void draftEmail() {

    }

    @Override
    public void sendit() {

    }

    @Override
    public void apiex() {

    }

    @Override
    public void setupServerProperties() {

    }

    @Override
    public List<factures> FactureList() {
        List<factures> MyList = new ArrayList();
        try {

            String requete ="SELECT * FROM factures";
            Statement st =Connection.getInstance().getCnx().createStatement();
            ResultSet rs=st.executeQuery(requete);
            while(rs.next()) {
                factures f = new factures();
                f.setID_Facture(rs.getInt(1));
                f.setNom_Facture(rs.getString(2));
                f.setPrix_Facture(rs.getFloat(3));
                f.setStatus(rs.getString(4));
                MyList.add(f);
            }
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
        }
        return MyList;
    }

    @Override
    public ObservableList<factures> getDatausers() {
        return null;
    }


    @Override
    public void supprimerFacture() {
        try {
            String requete = "DELETE FROM factures";
            PreparedStatement pst = Connection.getInstance().getCnx().prepareStatement(requete);
            pst.executeUpdate();
            System.out.println("factures supprimée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
}
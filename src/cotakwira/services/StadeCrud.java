/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cotakwira.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import cotakwira.entities.Match;
import cotakwira.entities.Stade;
import cotakwira.interfaces.IStade;
import cotakwira.tools.Connection;

/**
 *
 * @author esprit
 */
public class StadeCrud implements IStade<Stade> {

    @Override
    public void ajouterStade(Stade t) {
        try {
            String requete
                    = "INSERT INTO `stade` (`id_stade`, `nom_stade`, `ville`, `adresse`, `capacite`,`id_org`) VALUES (?,?, ?, ?, ?,?)";

            PreparedStatement pst = Connection.getInstance().getCnx()
                    .prepareStatement(requete);

            pst.setInt(1, t.getId_stade());
            pst.setString(2, t.getNom_stade());

            pst.setString(3, t.getVille());
            pst.setString(4, t.getAdresse());
            pst.setInt(5, t.getCapacite());
            pst.setInt(6, t.getId_org());

            pst.executeUpdate();
            System.out.println("Stade ajouté");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimerStade(Stade t) {
        try {
            String requete = "DELETE FROM `stade` where nom_stade=?";
            PreparedStatement pst = Connection.getInstance().getCnx()
                    .prepareStatement(requete);
            pst.setString(1, t.getNom_stade());
            pst.executeUpdate();
            System.out.println("Stade supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
     public void supprimerStade2(int id) {
        try {
            String requete = "DELETE FROM `stade` where id_stade=" + id;
            PreparedStatement pst = Connection.getInstance().getCnx()
                    .prepareStatement(requete);
            // pst.setInt(1, t.getIdStade());
            pst.executeUpdate();
            System.out.println("Stade supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void updateStade(Stade t) {
        try {
            int idd = t.getId_stade();
            String requete = "UPDATE `stade` SET `nom_stade`=?,`ville`=?,`adresse`=?,`capacite`=? WHERE id_stade =?";
            PreparedStatement pst = Connection.getInstance().getCnx()
                    .prepareStatement(requete);
            pst.setString(1, t.getNom_stade());
            pst.setString(2,t.getVille());
             pst.setString(3, t.getAdresse());
            pst.setInt(4,t.getCapacite());
           
              pst.setInt(5,t.getId_stade());
            
            pst.executeUpdate();
            System.out.println("Stade modifiée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Stade> displayStades() {
        List<Stade> ListStade = new ArrayList<>();
        try {

            String requete = "SELECT * FROM `stade` WHERE ";
            Statement st = Connection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Stade p = new Stade();

                p.setId_stade(rs.getInt("id_stade"));
                p.setNom_stade(rs.getString("nom_stade"));
                p.setVille(rs.getString("ville"));
                p.setAdresse(rs.getString("adresse"));
                p.setCapacite(rs.getInt("capacite"));
            
                ListStade.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return ListStade;
    }

    public List<Stade> chercherStadeParVille(String v)
    {
         List<Stade> ListStade = new ArrayList<>();
        try {

            String requete = "SELECT * FROM `stade` WHERE ville='"+v+"'";
            Statement st = Connection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Stade p = new Stade();

                p.setId_stade(rs.getInt("id_stade"));
                p.setNom_stade(rs.getString("nom_stade"));
                p.setVille(rs.getString("ville"));
                p.setAdresse(rs.getString("adresse"));
                p.setCapacite(rs.getInt("capacite"));
              // p.setImg(rs.getString("img"));
                ListStade.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return ListStade;
    }
    
    public int OccurenceStade(Stade std)
    {
        int total = 0;
        int idd = std.getId_stade();
        
        String requete = "select count(*) from `match`  where id_stade = " + idd;

        Statement st;
        try {
            st = Connection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            if (rs.next()) {
                String chaine = String.valueOf(rs.getString(1));
                total = Integer.parseInt(chaine);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return total;
    
    }
   public int getNbStadeOrg(int idd){
    
    int total = 0;

        String requete = "select count(*) from `stade`WHERE id_org= "+idd ;

        Statement st;
        try {
            st = Connection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            if (rs.next()) {
                String chaine = String.valueOf(rs.getString(1));
                total = Integer.parseInt(chaine);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return total;
}
 
    
    public  ObservableList<Stade> getStades(int idd) {
        ObservableList<Stade> ListStade = FXCollections.observableArrayList();
        try {

            String requete = "SELECT * FROM `stade` where id_org ="+idd;
            PreparedStatement pst = Connection.getInstance().getCnx()
                    .prepareStatement(requete);
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
               
                ListStade.add(new Stade(rs.getInt("id_org"),rs.getInt("id_stade"), rs.getString("nom_stade"), rs.getString("ville"), rs.getString("adresse"),
                        rs.getInt("capacite")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return ListStade;
    }
    
    public Stade getStadeParId(int idd) {
        Stade p = new Stade();
        try {

            String requete = "SELECT * FROM `stade` WHERE id_stade = " + idd;
            Statement st = Connection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {

                p.setId_stade(rs.getInt("id_stade"));
                p.setNom_stade(rs.getString("nom_stade"));
                p.setVille(rs.getString("ville"));
                p.setAdresse(rs.getString("adresse"));
                p.setCapacite(rs.getInt("capacite"));
             //   p.setImg(rs.getString("img"));
                   

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return p;

    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cotakwira.services;

import cotakwira.entities.Coach;
import cotakwira.entities.Entrainement;
import cotakwira.entities.Equipe;
import cotakwira.entities.Stade;
import cotakwira.interfaces.IEntrainement;
import cotakwira.tools.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class EntrainementCrud implements IEntrainement<Entrainement>{

    @Override
    public void ajouterEntrainement(Entrainement e) {

     
        try {
            String requete= "INSERT INTO entrainement(date_entrainement, duree, id_coach, id_stade, id_equipe ,nom_equipee ,nom_coache ,nom_stadee ,type ,heure)"
                    + "VALUES (?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = Connection.getInstance().getCnx()
                    .prepareStatement(requete);
          // pst.setInt(1, e.getId_coach());
            pst.setDate(1, e.getDate_entrainement());
            pst.setInt(2, e.getDuree());
            pst.setInt(3, e.getId_coach());
            pst.setInt(4, e.getId_stade());
             pst.setInt(5, e.getId_equipe());
           pst.setString(6,e.getNom_equipee());
           pst.setString(7,e.getNom_coache());
           pst.setString(8,e.getNom_stadee());
           pst.setString(9,e.getType());
           pst.setString(10, e.getHeure());





            
            
            pst.executeUpdate();
            System.out.println("Entrainement ajouté");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        
        


    }

    
    
    
    
    
    
    
    
    
    
    @Override
    public void supprimerEntrainement(Entrainement e) {


try {
            String requete = "DELETE FROM entrainement where id_entrainement=?";
            PreparedStatement pst = Connection.getInstance().getCnx()
                    .prepareStatement(requete);
            pst.setInt(1, e.getId_entrainement());
            pst.executeUpdate();
            System.out.println("Entrainement supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void updateEntrainement(Entrainement e) {
        
        
           try {
            String requete = "UPDATE entrainement SET duree=?, date_entrainement=?, heure=?, id_coach=?, id_stade=?, id_equipe=?, nom_equipee=?, nom_stadee=?, nom_coache=?, type=? WHERE id_entrainement=?";
            PreparedStatement pst = Connection.getInstance().getCnx()
                    .prepareStatement(requete);
            pst.setInt(1, e.getDuree());
            pst.setDate(2, e.getDate_entrainement());
            pst.setString(3, e.getHeure());
            pst.setInt(4, e.getId_coach());
            pst.setInt(5, e.getId_stade());
            pst.setInt(6,e.getId_equipe());
            pst.setString(7, e.getNom_equipee());
            pst.setString(8, e.getNom_stadee());
            pst.setString(9, e.getNom_coache());
            pst.setString(10, e.getType());

             pst.setInt(11, e.getId_entrainement());






            
            pst.executeUpdate();
            System.out.println("Entrainement modifiée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
    }
    
    
    
    
    
    
    
    
    
    

    @Override
    public List<Entrainement> afficherEntrainement() {
        
         List<Entrainement> entrainementtab = new ArrayList<>();
        try {
            String requete = "SELECT * FROM entrainement";
            Statement st = Connection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs =  st.executeQuery(requete);
            while(rs.next()){
                Entrainement e = new Entrainement();
                e.setId_entrainement(rs.getInt(1));
                e.setDuree(rs.getInt(2));

                e.setDate_entrainement(rs.getDate(3));
                e.setHeure(rs.getString(4));

                e.setId_equipe(rs.getInt(7));
                e.setId_coach(rs.getInt(5));
                e.setId_stade(rs.getInt(6));
                 e.setNom_equipee(rs.getString(8));
                 e.setNom_stadee(rs.getString(9));
                 e.setNom_coache(rs.getString(10));
                 e.setType(rs.getString(11));





                entrainementtab.add(e);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return entrainementtab;
    }

    @Override
    public List<Entrainement> coachplusentraine() {



 List<Entrainement> entrainementtab = new ArrayList<>();
        try {
            String requete = "SELECT * FROM entrainement \n" +
"WHERE id_coach IN ( \n" +
"SELECT id_coach FROM entrainement  \n" +
"GROUP BY id_coach  \n" +
"HAVING count(id_coach)>1 \n" +
")\n" ;//+
//"ORDER BY id_coach  ";
            Statement st = Connection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs =  st.executeQuery(requete);
            while(rs.next()){
                Entrainement e = new Entrainement();
                e.setId_entrainement(rs.getInt(1));
                e.setDate_entrainement(rs.getDate(3));
                e.setDuree(rs.getInt(2));
                e.setId_equipe(rs.getInt(6));
                e.setId_coach(rs.getInt(4));
                e.setId_stade(rs.getInt(5));



                entrainementtab.add(e);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return entrainementtab;
        
    }
        
    
    
 
    
}
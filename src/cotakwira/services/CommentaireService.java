/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cotakwira.services;

import cotakwira.entities.Article;
import cotakwira.interfaces.ICommentaireService;
import cotakwira.entities.Commentaire;
import cotakwira.services.BadWordFilter;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import cotakwira.tools.Connection;

/**
 *
 * @author khali
 */
public class CommentaireService implements ICommentaireService<Commentaire> {
    	private String output;

    
    public void ajouterCommentaire(Commentaire c) {
         try {
             output = BadWordFilter.getCensoredText(c.getContenu());
            String requete= "INSERT INTO commentaire (contenu,date,id_article,id_joueur)"
                    + "VALUES (?,CURRENT_TIMESTAMP(),?,?)";
            PreparedStatement pst = Connection.getInstance().getCnx()
                    .prepareStatement(requete);
            pst.setString(1, output);
            pst.setInt(2, c.getId_article());
            pst.setInt(3,c.getId_joueur());
            pst.executeUpdate();
            		
                        

            System.out.println("Commentaire inserée");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    
    }
    
    public void supprimerCommentaire(Commentaire c) {
        try {
            String requete = "DELETE FROM commentaire where id=?";
            PreparedStatement pst = Connection.getInstance().getCnx()
                    .prepareStatement(requete);
            pst.setInt(1, c.getId());
            pst.executeUpdate();
            System.out.println("Commentaire supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
    @Override
    public void updateCommentaire(Commentaire c) {
        try {
            String requete = "UPDATE commentaire SET contenu=?,date=CURRENT_TIMESTAMP(),id_article=?,id_joueur=? WHERE id=?";
            PreparedStatement pst = Connection.getInstance().getCnx()
                    .prepareStatement(requete);
            pst.setString(1, c.getContenu());
            pst.setInt(2, c.getId_article());
            pst.setInt(3,c.getId_joueur());
            pst.setInt(4,c.getId());
            pst.executeUpdate();
            System.out.println("Commentaire modifiée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    @Override
    public List<Commentaire> displaycommentaires() {
         List<Commentaire> commentairesList = new ArrayList<>();
        try {
            String requete = "SELECT * FROM commentaire";
            Statement st = Connection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs =  st.executeQuery(requete);
            while(rs.next()){
                Commentaire c = new Commentaire();
                c.setId(rs.getInt("id"));
                c.setContenu(rs.getString("contenu"));
                c.setDate(rs.getDate("date"));
                c.setId_article(rs.getInt("id_article"));
                c.setId_joueur(rs.getInt("id_joueur"));
                commentairesList.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return commentairesList;
    }
    
    @Override
    public List<Commentaire> displaycommentaires_par_article(Integer a) {
         List<Commentaire> commentairesList = new ArrayList<>();
        try {
            String requete = "SELECT * FROM commentaire WHERE id_article= ?";
            PreparedStatement pst = Connection.getInstance().getCnx()
                    .prepareStatement(requete);
            //System.out.println(a);
            pst.setInt(1, a);
            ResultSet rs =  pst.executeQuery();
            while(rs.next()){
                Commentaire c = new Commentaire();
                c.setId(rs.getInt("id"));
                c.setContenu(rs.getString("contenu"));
                c.setDate(rs.getDate("date"));
                c.setId_article(rs.getInt("id_article"));
                c.setId_joueur(rs.getInt("id_joueur"));
                commentairesList.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return commentairesList;
    }

    
}

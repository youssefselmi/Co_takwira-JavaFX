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
import java.util.logging.Level;
import java.util.logging.Logger;
import cotakwira.entities.Match;
import cotakwira.entities.Vote;
import cotakwira.interfaces.IVote;
import cotakwira.tools.Connection;

/**
 *
 * @author esprit
 */
public class VoteCrud implements IVote<Vote> {

    @Override
    public void ajouterVote(Vote t) {
        try {
            String requete = "INSERT INTO vote (id_match,id_user, vote)  VALUES (?,?,?)";

            PreparedStatement pst = Connection.getInstance().getCnx()
                    .prepareStatement(requete);

            pst.setInt(1, t.getId_match());
            pst.setInt(2, t.getId_user());
            pst.setInt(3, t.getVote());

            pst.executeUpdate();
            System.out.println("Vote ajouté");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    
    public void supprimerVote( int id_user) {
        
         try {
            String requete = "DELETE FROM `vote` where id_user=" +id_user;
            PreparedStatement pst = Connection.getInstance().getCnx()
                    .prepareStatement(requete);
          
            pst.executeUpdate();
            System.out.println("Vote supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
public int validerVote(int id_user,int id_m)
   
{int test =0;

        try {

            String requete = "SELECT * FROM `vote` WHERE id_user='" +id_user+ "'AND id_match='" + id_m + "'" ;
            Statement st = Connection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
          if (rs.next()) {

               test =-1;
                   

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       return test;

    
}
    public void updateVote(int id_user,int id_vote,int vote) {
        
        try {
            String requete = "UPDATE `vote` SET vote=? WHERE id_vote='" +id_vote+ "'AND id_user='" + id_user + "'" ;
            PreparedStatement pst = Connection.getInstance().getCnx()
                    .prepareStatement(requete);
            pst.setInt(1,vote);
           /* pst.setInt(2,id_user);*/
            pst.executeUpdate();
            System.out.println("Vote modifiée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } 
    }

    public int CalculerTotal(int idd) {
        int total = 0;
        // int idd = m.getId_match();
        String requete = "select count(*) from `vote`  where id_match = " + idd;

        Statement st;
        try {
            st = Connection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            if (rs.next()) {
                String chaine = String.valueOf(rs.getString(1));
                total = Integer.parseInt(chaine);
                System.out.println(total);
            }

        } catch (SQLException ex) {
            ex.getMessage();
        }
        return total;
    }

    public float CalculerVote(int idd) {
        int sum = 0;
        //int idd = m.getId_match();
        System.out.println(idd);
        float vote = 0;
        int total = CalculerTotal(idd);
        String chaine;
        String requete = "select sum(vote) from `vote`  where id_match = " + idd;

        Statement st;
        try {
            st = Connection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            if (rs.next()) {
                if (rs.getString(1) == null) {
                    chaine = "0";
                    System.out.println(" Match introuvable !!");
                } else {
                    chaine = String.valueOf(rs.getString(1));
                    sum = Integer.parseInt(chaine);
                    vote = (float) sum / total;
                }

            }

        } catch (SQLException ex) {
            System.out.println("ex.getMessage()");
        }

        return vote;
    }
    
     public int CalculerSomme(int idd) {
        int total = 0;
      
         String requete = "select sum(vote) from `vote`  where id_match = " + idd;

        Statement st;
        try {
            st = Connection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            if (rs.next()) {
                String chaine = String.valueOf(rs.getString(1));
                if (rs.getString(1) == null) {
                    chaine = "0";
                    total= Integer.parseInt(chaine);
                    System.out.println(" Match introuvable !!");
                } else {
                    chaine = String.valueOf(rs.getString(1));
                    total= Integer.parseInt(chaine);
                   
                }
                
                System.out.println("somme"+chaine);
                total = Integer.parseInt(chaine);
               
            }

        } catch (SQLException ex) {
            ex.getMessage();
        }
         System.out.println("total"+total);
        return total;
    }


}

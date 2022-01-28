/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cotakwira.services;

import cotakwira.entities.Equipe;
import cotakwira.entities.Joueur;
import java.util.List;
import cotakwira.interfaces.IEquipe;
import cotakwira.tools.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author pc_dell
 */
public class EquipeCrud implements IEquipe<Equipe> {

    @Override
    public void ajouterEquipe(Equipe t) {
   
        try {
            String requete= "INSERT INTO equipe(nom_equipe,id_coach)"
                    + "VALUES (?,?)";
            PreparedStatement pst = Connection.getInstance().getCnx()
                    .prepareStatement(requete);
            pst.setString(1, t.getNom_equipe());

            pst.setInt(2, t.getId_coach());

            pst.executeUpdate();
            System.out.println("Equipe inserée");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }    
  public void mise_a_jour() throws SQLException {
        PreparedStatement ps;
        String query = "UPDATE categorie FROM `joueur` WHERE ";
        ps = Connection.getInstance().getCnx().prepareStatement(query);

        ps.execute();
    }
   /* public void supprimerEquipe(Equipe t) {
 try {
            String requete = "DELETE FROM equipe where id_e=?";
            PreparedStatement pst = Connection.getInstance().getCnx()
                    .prepareStatement(requete);
            pst.setInt(1, t.getId_equipe());
            pst.executeUpdate();
            System.out.println("equipe supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
            }*/

  /*  @Override
   public void updateEquipe(Equipe t) {
    try {
            String requete = "UPDATE equipe SET nom_equipe=?,id_coach=? WHERE id_e=?";
            PreparedStatement pst = Connection.getInstance().getCnx()
                    .prepareStatement(requete);
            pst.setString(1, t.getNom_equipe());
            pst.setInt(2, t.getId_coach());

            pst.setInt(3, t.getId_equipe());

            pst.executeUpdate();
            System.out.println("Equipe modifiée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    }*/
    @Override
     public void updateEquipe(int ie,Equipe t) {
    try {
        //Equipe e=new Equipe();
        String nomequipe="";
            String requete = "UPDATE equipe SET nom_equipe=?,id_coach=? WHERE id_e="+ie+"";
            PreparedStatement pst = Connection.getInstance().getCnx()
                    .prepareStatement(requete);
          pst.setString(1,t.getNom_equipe());
          pst.setInt(2,t.getId_coach());

           //pst.setInt(2, t.getId_equipe());
            pst.executeUpdate();
            System.out.println("equipe modifiée");
        
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    
     
     
     }
       @Override

      public void supprimerEquipe(int ie) {
 try {
            String requete = "DELETE FROM equipe where id_e="+ie+"";
            PreparedStatement pst = Connection.getInstance().getCnx()
                    .prepareStatement(requete);
          //  pst.setInt(1, ie);
            pst.executeUpdate();
            System.out.println("equipe supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
            }
      public int recupereridCoach(String nom_coach)
      {
          int id=0;
                   String req="select *  from coach where CONCAT(nom_coach, ' ', prenom_coach) = '"+nom_coach+"'";

           try {
          
           
            Statement st = Connection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs =  st.executeQuery(req);
            
     while(rs.next())
     {
            id=rs.getInt(1);
     }
           }
          catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
           return id;
      }

   
    @Override
    public List<Equipe> afficherEquipe() {
           List<Equipe> equipeList = new ArrayList<>();
        try {
            String requete = "SELECT equipe.*,coach.nom_coach,coach.prenom_coach FROM equipe INNER JOIN coach ON(equipe.id_coach=coach.id_coach)";
            Statement st = Connection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs =  st.executeQuery(requete);
            while(rs.next()){
                Equipe e = new Equipe();
                e.setId_equipe(rs.getInt(1));
                e.setNom_equipe(rs.getString(2));
                e.setId_coach(rs.getInt(3));

                e.setNom_coach(rs.getString(4));
                e.setPrenom_coach(rs.getString(5));

                equipeList.add(e);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return equipeList;
    }
  /*public List<Equipe> getEquipeparID() {
           List<Equipe> equipeList = new ArrayList<>();
        try {
            String requete = "SELECT equipe.id_equipe, nom_equipe, coach.nom_coach FROM equipe, coach WHERE equipe.id_coach = coach.id_coach";
            Statement st = Connection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs =  st.executeQuery(requete);
            while(rs.next()){
                Equipe e = new Equipe();
                e.setId_equipe(rs.getInt(1));
                e.setNom_equipe(rs.getString(2));
                //e.setId_coach(rs.getInt(3));
                equipeList.add(e);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return equipeList;
    }*/

   
}

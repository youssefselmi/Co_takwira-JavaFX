/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cotakwira.services;

import cotakwira.entities.Reclamation;
import cotakwira.gui.HomeReclamationController;
import cotakwira.interfaces.IReclamation;
import cotakwira.tools.Connection;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author yassin
 */
public class CrudReclamation implements IReclamation<Reclamation> {

    @Override
    public boolean ajouterReclamation(Reclamation t) {
        try {
            String requete = "INSERT INTO reclamation(idRec,idJoueur,SujetRec,DescriptionRec,StatusRec,DateRec,DateTraitement,NomPrenomCoach,NomJoueur,PrenomJoueur,EmailJoueur,TlJoueur,imgRec)"
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = Connection.getInstance().getCnx().prepareStatement(requete);
            ///////////////////////controle valeur idJoueur/////
            String requetee = "SELECT * FROM joueur WHERE id_joueur = '" + t.getIdJoueur() + "'";
            Statement pstt = Connection.getInstance().getCnx().createStatement();
            ResultSet rs = pstt.executeQuery(requetee);
            System.out.println("id Jouer === " + t.getIdJoueur());
            while (rs.next()) {
                pst.setInt(2, rs.getInt(1));
                pst.setString(9, rs.getString(2));//NomJoueur
                pst.setString(10, rs.getString(3));//PrenomJoueur
                pst.setString(11, rs.getString(7));//EmailJoueur
                pst.setString(12, rs.getString(6));//TlJoueur
                break;
            }
            /////////////////////controle reclam coach///////////////////////
            //pst.setString(9, t.getNomPrenomCoach());
            if ((t.getSujetRec()).equals("Coach") && (!(t.getNomPrenomCoach()).equals("")))//NomPrenomCoach
            {
                pst.setString(8, t.getNomPrenomCoach());
            } else {
                pst.setNull(8, Types.INTEGER);
            }

            pst.setInt(1, t.getIdRec());

            pst.setString(3, t.getSujetRec());
            pst.setString(4, t.getDescriptionRec());
            //  pst.setString(5, "Medium");
            pst.setString(5, "non traite");
            pst.setString(13, t.getImgRec());
            long millis = System.currentTimeMillis();
            java.sql.Date DateRec = new java.sql.Date(millis);
            pst.setDate(6, DateRec);

            Date DateNonTraiter = null;
            pst.setDate(7, DateNonTraiter);
            //pst.setInt(9, t.getIdCoach());
            pst.executeUpdate();
            System.out.println("Reclamation ajouté!");

///////////////////////////////////////////////////////////////////////////////////////////////////////:
            int idRec;
            String requete17 = "SELECT MAX(idRec) FROM reclamation ";
            Statement pst17 = Connection.getInstance().getCnx().createStatement();
            ResultSet rs17 = pst.executeQuery(requete17);
            while (rs17.next()) {
                idRec = rs17.getInt(1);
                System.out.println("opaaaa == " + idRec);
            
            String requete5 = "INSERT INTO prioriterec(idRec,PrioriteRec,id)" + "VALUES (?,?,?)";
            PreparedStatement pst5 = Connection.getInstance().getCnx().prepareStatement(requete5);
            System.out.println(" iD ================= " + t.getIdRec());
            System.out.println("opaaaa == " + idRec);
            pst5.setInt(1, idRec);
            pst5.setString(2, "Medium");
            pst5.setInt(3, t.getIdRec());
            pst5.executeUpdate();
            }
///////////////////////////////////////////////////////////////////////////////////////////////////////:

            return true;

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLSTATE: " + ex.getSQLState());
            System.out.println("VnedorError: " + ex.getErrorCode());
        }
        return false;

    }

    @Override
    public boolean supprimerReclamation(int idRec) {
        try {
            String requete = "DELETE FROM reclamation where idRec=" + String.valueOf(idRec) + "";
            PreparedStatement pst = Connection.getInstance().getCnx()
                    .prepareStatement(requete);
            pst.execute(requete);
            System.out.println("Reclamation supprimée");

            return true;

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLSTATE: " + ex.getSQLState());
            System.out.println("VnedorError: " + ex.getErrorCode());
        }
        return false;
    }

//    SujetRec,DescriptionRec,idCoach
    @Override
    public boolean updateReclamation(Reclamation t) {
        try {
            String requete = "UPDATE reclamation SET SujetRec=?,DescriptionRec=?,NomPrenomCoach=?,imgRec=? WHERE idRec=?";
            PreparedStatement pst = Connection.getInstance().getCnx()
                    .prepareStatement(requete);
            pst.setInt(5, t.getIdRec());
            pst.setString(2, t.getDescriptionRec());

            pst.setString(1, t.getSujetRec());

            if ((t.getSujetRec()).equals("Coach")) {
                pst.setString(3, t.getNomPrenomCoach());
            } else {
                pst.setNull(3, Types.INTEGER);
            }

            pst.setString(4, t.getImgRec());

            pst.executeUpdate();
            System.out.println("Reclamation modifiée");

            return true;

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLSTATE: " + ex.getSQLState());
            System.out.println("VnedorError: " + ex.getErrorCode());
        }
        return false;
    }

    @Override
    public List<Reclamation> displayReclamation(Reclamation t) {
        List<Reclamation> ReclamationList = new ArrayList<>();
        try {
            String requete = "SELECT idRec,SujetRec,DescriptionRec,StatusRec,NomPrenomCoach,DateRec,DateTraitement,imgRec FROM reclamation ORDER BY DateRec DESC";
            Statement pst = Connection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                Reclamation r = new Reclamation();

                ImageView img = new ImageView();

                Image image;

                try {
                    if(rs.getString("imgRec") == null)
                            {
                            
                            }
                    else if(rs.getString("imgRec") != null) 
                    {
                    image = new Image(new FileInputStream((rs.getString("imgRec"))));
                    img.setImage(image);
                    img.setPreserveRatio(true);
                    img.setFitWidth(50);
                    img.setFitHeight(50);
                    }
                } catch (FileNotFoundException ex) {
                    System.out.println(ex.getMessage());
                    img.setImage(new Image(getClass().getResource("/resources/media/empty-image.jpg").toString()));
                }

                r.setIdRec(rs.getInt(1));
                r.setSujetRec(rs.getString(2));
                r.setDescriptionRec(rs.getString(3));
                r.setStatusRec(rs.getString(4));
                r.setDateRec(rs.getDate(6));
                r.setNomPrenomCoach(rs.getString(5));
              //  r.setDateTraitement(rs.getDate(7));
                //r.setImgRec(rs.getString(8));
                r.setImgReclamation(img);
                ReclamationList.add(r);
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLSTATE: " + ex.getSQLState());
            System.out.println("VnedorError: " + ex.getErrorCode());
        }
        return ReclamationList;
    }

    @Override
    public void PrioriteRec() {

        int heure;
        int difference = 0;
        long millis = System.currentTimeMillis();
        java.sql.Date Datelyoum = new java.sql.Date(millis);
        int nherleyoum = Integer.valueOf(String.format("%1$td", Datelyoum));

        try {
            String requete = "SELECT idRec,DateRec,StatusRec FROM reclamation WHERE StatusRec = 'non traite' ";
            Statement pst = Connection.getInstance().getCnx().createStatement();
            ResultSet rs = pst.executeQuery(requete);

            while (rs.next()) {

                //heure = rs.getDate(1);
                heure = Integer.valueOf(String.format("%1$td", rs.getDate(2)));
                //System.out.println("day mel base de données "+heure);
                difference = nherleyoum - heure;
                //System.out.println("id = "+rs.getInt(1)+" Day = "+difference+"");
                if (difference >= 7) {
                    String requetee = "UPDATE prioriterec SET PrioriteRec=? where idRec = '" + rs.getInt(1) + "' ";
                    PreparedStatement pstt = Connection.getInstance().getCnx().prepareStatement(requetee);
                    System.out.println("id = " + rs.getInt(1) + " Day = " + difference + "");
                    pstt.setString(1, "High");

                    pstt.executeUpdate();

                }
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLSTATE: " + ex.getSQLState());
            System.out.println("VnedorError: " + ex.getErrorCode());
        }
    }

    @Override
    public int contraintModifier24h(int idRec) {
        int heure;
        int difference = 0;
        long millis = System.currentTimeMillis();
        java.sql.Date Datelyoum = new java.sql.Date(millis);

        int nherleyoum = Integer.valueOf(String.format("%1$td", Datelyoum));
        // System.out.println("day lyou me system "+nherleyoum);

        try {
            String requete = "SELECT DateRec FROM reclamation WHERE idRec = " + String.valueOf(idRec) + "";
            Statement pst = Connection.getInstance().getCnx().createStatement();
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                //heure = rs.getDate(1);
                heure = Integer.valueOf(String.format("%1$td", rs.getDate(1)));
                //System.out.println("day mel base de données "+heure);
                difference = nherleyoum - heure;
                System.out.println("Id " + idRec + " Day = " + difference + " Jour");
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLSTATE: " + ex.getSQLState());
            System.out.println("VnedorError: " + ex.getErrorCode());
        }
        //heure=nherleyoum-heure;
        return difference;
    }

    @Override
    public boolean updateRecStatut(Reclamation t) {
        try {
            String requete = "UPDATE reclamation SET StatusRec=? WHERE idRec=? ";
            PreparedStatement pst = Connection.getInstance().getCnx().prepareStatement(requete);

            pst.setString(1, t.getStatusRec());
            pst.setInt(2, t.getIdRec());

            pst.executeUpdate();

            System.out.println("Status is updated !");
            return true;
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLSTATE: " + ex.getSQLState());
            System.out.println("VnedorError: " + ex.getErrorCode());
        }
        return false;
    }

    @Override
    public int ReclamationIsOpen(int idRec) { // hedhy lle Admin
        int opened = 0;

        try {
            String requete = "UPDATE reclamation SET Opened=? WHERE idRec= " + String.valueOf(idRec) + "";
            PreparedStatement pst = Connection.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1, 1);
            pst.executeUpdate();
            System.out.println("Fonction Opened !!");
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLSTATE: " + ex.getSQLState());
            System.out.println("VnedorError: " + ex.getErrorCode());
        }
        return opened;
    }

    @Override
    public int VerifIsOpenn(int idRec) { // hedhy lel User 
        int opened = 0;

        try {
            String requete = "SELECT Opened FROM reclamation WHERE idRec = " + String.valueOf(idRec) + "";
            Statement pst = Connection.getInstance().getCnx().createStatement();
            ResultSet rs = pst.executeQuery(requete);

            while (rs.next()) {
                opened = rs.getInt(1);
                System.out.println("El tchek te3 Opned is === " + opened);
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLSTATE: " + ex.getSQLState());
            System.out.println("VnedorError: " + ex.getErrorCode());
        }
        return opened;
    }

    @Override
    public List<Reclamation> displayRecForAdmin() {
        List<Reclamation> ReclamationList = new ArrayList<>();
        try {
            String requete = "SELECT * FROM reclamation ORDER BY DateRec DESC";
            Statement pst = Connection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                Reclamation r = new Reclamation();
                ImageView img = new ImageView();
                Image image;

                try {
                    image = new Image(new FileInputStream((rs.getString("imgRec"))));
                    img.setImage(image);
                    img.setPreserveRatio(true);
                    img.setFitWidth(50);
                    img.setFitHeight(50);
                } catch (FileNotFoundException ex) {
                    System.out.println(ex.getMessage());
                }
                r.setIdRec(rs.getInt("idRec"));
                r.setSujetRec(rs.getString("SujetRec"));
                r.setDescriptionRec(rs.getString("DescriptionRec"));
                r.setStatusRec(rs.getString("StatusRec"));
                r.setDateRec(rs.getDate("DateRec"));
                r.setNomPrenomCoach(rs.getString("NomPrenomCoach"));
                r.setDateTraitement(rs.getDate("DateTraitement"));
                r.setImgReclamation(img);
                ReclamationList.add(r);
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLSTATE: " + ex.getSQLState());
            System.out.println("VnedorError: " + ex.getErrorCode());
        }
        return ReclamationList;
    }

    public List<Reclamation> rechercheReclamations(String type, String valeur) {
        List<Reclamation> myList = new ArrayList<Reclamation>();
        String requete = null;
        try {
            if (type.equals("Coach")) {
                requete = "SELECT * from Reclamation where NomPrenomCoach like '%" + valeur + "%'"; //MAJUSCULE NON OBLIGATOIRE 
            } else if (type.equals("DateRec")) {
                requete = "SELECT * from Reclamation where DateRec like '%" + valeur + "%'"; //MAJUSCULE NON OBLIGATOIRE 
            } else if (type.equals("DescriptionRec")) {
                requete = "SELECT * from Reclamation where DescriptionRec like '%" + valeur + "%'"; //MAJUSCULE NON OBLIGATOIRE 
            } else if (type.equals("StatusRec")) {
                requete = "SELECT * from Reclamation where StatusRec ='" + valeur + "'";; //MAJUSCULE NON OBLIGATOIRE 
            } else if (type.equals("DateTraitement")) {
                requete = "SELECT * from Reclamation where DateTraitement like '%" + valeur + "%' or nom like '%" + valeur + "%' or prenom like '%" + valeur + "%' or email like '%" + valeur + "%' or objet like '%" + valeur + "%' or description like '%" + valeur + "%'"; //MAJUSCULE NON OBLIGATOIRE 
            } else if (type.equals("Tout")) {
                requete = "SELECT * from Reclamation where StatusRec like '%" + valeur + "%' or NomPrenomCoach like '%" + valeur + "%' or SujetRec like '%" + valeur + "%' or DateRec like '%" + valeur + "%' or DateTraitement like '%" + valeur + "%' "; //MAJUSCULE NON OBLIGATOIRE 
            }

            Statement pst = Connection.getInstance().getCnx().prepareStatement(requete); // import java.sql.Statement
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                Reclamation r = new Reclamation();
                ImageView img = new ImageView();
                Image image;

                try {
                    image = new Image(new FileInputStream((rs.getString("imgRec"))));
                    img.setImage(image);
                    img.setPreserveRatio(true);
                    img.setFitWidth(50);
                    img.setFitHeight(50);
                } catch (FileNotFoundException ex) {
                    System.out.println(ex.getMessage());
                }
                r.setIdRec(rs.getInt("idRec"));
                r.setSujetRec(rs.getString("SujetRec"));
                r.setDescriptionRec(rs.getString("DescriptionRec"));
                r.setStatusRec(rs.getString("StatusRec"));
                r.setDateRec(rs.getDate("DateRec"));
                r.setNomPrenomCoach(rs.getString("NomPrenomCoach"));
                r.setDateTraitement(rs.getDate("DateTraitement"));
                r.setImgReclamation(img);

                myList.add(r);

            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;

    }

}

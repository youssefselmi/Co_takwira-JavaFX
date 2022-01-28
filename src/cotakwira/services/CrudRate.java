/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cotakwira.services;

import cotakwira.entities.Rate;
import cotakwira.tools.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import cotakwira.interfaces.IRate;
import org.controlsfx.control.Rating;

/**
 *
 * @author yassin
 */
public class CrudRate implements IRate<Rate> {

    @Override
    public void ajouterRate(Rate t) {

        try {

            String requete = "INSERT INTO rate(idRate,idCoach,NomPrenomCoach,NomEquipe,DateRate,Rate)"
                    + "VALUES (?,?,?,?,?,?)";
            PreparedStatement pst = Connection.getInstance().getCnx()
                    .prepareStatement(requete);
            ////////////////////////////
            String requetee = "SELECT * FROM coach WHERE id_coach = '" + t.getIdCoach() + "'";
            Statement pstt = Connection.getInstance().getCnx().createStatement();
            ResultSet rs = pstt.executeQuery(requetee);
            while (rs.next()) {
                pst.setInt(2, rs.getInt(1));
                pst.setString(3, rs.getString(2) + " " + rs.getString(3));//NomPrenomCoach 

            }

            pst.setInt(1, t.getIdRate()); //iD el Rate

            long millis = System.currentTimeMillis();
            java.sql.Date DateRate = new java.sql.Date(millis);
            pst.setDate(5, DateRate);

            pst.setInt(6, t.getRate());
            //pst.setFloat(7,t.getRate());

            pst.setString(4, t.getNomEquipe());//NomEquipe ;                  

            pst.executeUpdate();

            System.out.println("Rate ajouté!");
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLSTATE: " + ex.getSQLState());
            System.out.println("VnedorError: " + ex.getErrorCode());
        }
    }

    @Override
    public List<Rate> displayRate(Rate t) {
        List<Rate> RateList = new ArrayList<>();
        try {
            String requete = "SELECT idRate,NomEquipe,DateRate,Rate FROM rate  ORDER BY DateRate DESC";
            Statement pst = Connection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                Rate r = new Rate();
                r.setIdRate(rs.getInt(1));
                Rating rr=new Rating();
                rr.setRating(rs.getInt(4));
                rr.setDisable(true);

                //System.out.println(rs.getInt(1));
                r.setNomEquipe(rs.getString(2));
                r.setDateRate(rs.getDate(3));
                r.setRate(rs.getInt(4));//lel card
                r.setRateView(rr); //lel tableau
                RateList.add(r);
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLSTATE: " + ex.getSQLState());
            System.out.println("VnedorError: " + ex.getErrorCode());
        }
        return RateList;
    }

//    @Override
//    public void ajouterAvis(Rate t) {
//        try {
//            String requete = "INSERT INTO avis(idAvis,idCoach,idJoueur,rate,description)"
//                    + "VALUES (?,?,?,?,?)";
//            PreparedStatement pst = MyConnection.getInstance().getCnx()
//                    .prepareStatement(requete);
//            pst.setInt(1, t.getidAvis());
//            pst.setInt(2, t.getidCoach());
//            pst.setInt(3, t.getidJoueur());
//            pst.setString(4, t.getRate());
//            pst.setString(5, t.getDescription());
//            pst.executeUpdate();
//            System.out.println("Avis inserée");
//
//        } catch (SQLException ex) {
//            System.out.println("SQLException: " + ex.getMessage());
//            System.out.println("SQLSTATE: " + ex.getSQLState());
//            System.out.println("VnedorError: " + ex.getErrorCode());
//        }
//    }
//
//    public void supprimerAvis(Rate t) {
//        try {
//            String requete = "DELETE FROM avis where idAvis=?";
//            PreparedStatement pst = MyConnection.getInstance().getCnx()
//                    .prepareStatement(requete);
//            pst.setInt(1, t.getidAvis());
//            pst.executeUpdate();
//            System.out.println("Avis supprimée");
//        } catch (SQLException ex) {
//            System.out.println("SQLException: " + ex.getMessage());
//            System.out.println("SQLSTATE: " + ex.getSQLState());
//            System.out.println("VnedorError: " + ex.getErrorCode());
//        }
//
//    }
//
//    public void updateAvis(Rate t) {
//        try {
//            String requete = "UPDATE avis SET idCoach=?,idJoueur=?,rate=?,description=? WHERE idAvis=?";
//            PreparedStatement pst = MyConnection.getInstance().getCnx()
//                    .prepareStatement(requete);
//            pst.setInt(1, t.getidCoach());
//            pst.setInt(2, t.getidJoueur());
//            pst.setString(3, t.getRate());
//            pst.setString(4, t.getDescription());
//            pst.setInt(5, t.getidAvis());
//            pst.executeUpdate();
//            System.out.println("Avis modifiée");
//        } catch (SQLException ex) {
//            System.out.println("SQLException: " + ex.getMessage());
//            System.out.println("SQLSTATE: " + ex.getSQLState());
//            System.out.println("VnedorError: " + ex.getErrorCode());
//        }
//    }
//
//    @Override
//    public List<Rate> displayAvis() {
//        List<Rate> AvisList = new ArrayList<>();
//        try {
//            String requete = "SELECT * FROM avis";
//            Statement st = MyConnection.getInstance().getCnx()
//                    .createStatement();
//            ResultSet rs = st.executeQuery(requete);
//            while (rs.next()) {
//                Rate p = new Rate();
//                p.setidAvis(rs.getInt(1));
//                p.setidCoach(rs.getInt(2));
//                    p.setidJoueur(rs.getInt(3));
//                p.setRate(rs.getString(4));
//                p.setDescription(rs.getString(5));
//                AvisList.add(p);
//            }
//        } catch (SQLException ex) {
//            System.out.println("SQLException: " + ex.getMessage());
//            System.out.println("SQLSTATE: " + ex.getSQLState());
//            System.out.println("VnedorError: " + ex.getErrorCode());
//        }
//        return AvisList;
//    }
}

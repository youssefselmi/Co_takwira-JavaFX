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
import cotakwira.entities.MatchTermine;
import cotakwira.entities.Organisateur;
import cotakwira.interfaces.IOrganisateur;
import cotakwira.tools.Connection;

/**
 *
 * @author esprit
 */
public class OrganisateurCrud implements IOrganisateur<Organisateur> {

    @Override
    public void ajouterOrganisateur(Organisateur t) {
        try {

            String requete = "INSERT INTO `organisateur`(`nom`, `prenom`, `mail`, `mdp`) "
                    + " VALUES (?, ?, ?, ?)";

            PreparedStatement pst = Connection.getInstance().getCnx()
                    .prepareStatement(requete);

            pst.setString(1, t.getNom());
            pst.setString(2, t.getPrenom());
            pst.setString(3, t.getEmail());
            pst.setString(4, t.getMdp());

            pst.executeUpdate();
            System.out.println("Organisateur Ajouté");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void supprimerOrganisateur(int id) {

        try {
            String requete = "DELETE FROM `organisateur` where id_org=" + id;
            PreparedStatement pst = Connection.getInstance().getCnx()
                    .prepareStatement(requete);

            pst.executeUpdate();
            System.out.println("Organisateur supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void updateOrganisateur(Organisateur t) {
        try {

            String requete = "  UPDATE `organisateur` SET `nom`=?,`prenom`=?,`mdp`=?,`mail`=?";

            PreparedStatement pst = Connection.getInstance().getCnx()
                    .prepareStatement(requete);

            pst.setString(1, t.getNom());
            pst.setString(2, t.getPrenom());
            pst.setString(3, t.getMdp());
            pst.setString(4, t.getEmail());

            pst.executeUpdate();
            System.out.println("Organisateur modifiée");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public List<Organisateur> displayOrganisateur() {
        List<Organisateur> List = new ArrayList<>();
        try {

            String requete = "SELECT * FROM `organisateur` WHERE 1";
            Statement st = Connection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Organisateur p = new Organisateur();

                p.setId_org(rs.getInt("id_org"));
                p.setScore(rs.getInt("score"));
                p.setNom(rs.getString("nom"));
                p.setPrenom(rs.getString("prenom"));
                p.setEmail(rs.getString("mail"));
                p.setPrenom(rs.getString("prenom"));
                p.setMdp(rs.getString("mdp"));
                List.add(p);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return List;
    }

    public int getScore(int idd) {

        int s = 0;
        try {

            String requete = "SELECT * FROM `organisateur` WHERE id_org =" + idd;
            Statement st = Connection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);
            if (rs.next()) {

                s = rs.getInt("score");

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return s;
    }

    public void setScore(int score, int idd) {

        try {
            String requete = "  UPDATE `organisateur` SET `score`=? WHERE id_org=" + idd;

            PreparedStatement pst = Connection.getInstance().getCnx()
                    .prepareStatement(requete);

            pst.setInt(1, score);

            pst.executeUpdate();
            System.out.println("Score org modifiée");
        } catch (SQLException ex) {
            Logger.getLogger(OrganisateurCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int Calcul(int nbm, int nbs, int v) {
        VoteCrud vc = new VoteCrud();

        int total = 0;

        float x = ((nbm * 50) + (nbs * (20)) + (v * (30))) / 100;
        total = Math.round(x);

        return total;
    }

    public int getVoteOrg(int id_org) {
        int v = 0;
        try {
            VoteCrud vc = new VoteCrud();
            String req = "select * from `match`WHERE id_org= " + id_org;

            Statement st3;

            st3 = Connection.getInstance().getCnx().createStatement();
            ResultSet a = st3.executeQuery(req);

            while (a.next()) {
                v += vc.CalculerSomme(a.getInt("id_match"));

            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return v;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cotakwira.entities;

import java.util.Date;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.Button;

/**
 *
 * @author esprit
 */
public class Match {

    private int id_match;
    private int id_stade;
    private int id_org;

    private int id_equipe1;
    private int id_equipe2;
    private Date date;
    private String heure_deb;
    private String heure_fin;
    private int nb_spectateur;
    private String description;
    private String status;
    private String score;

    
    //constructeur pour l'affichage*/
    public Match(int id_org,int id_match, int id_stade, int id_equipe1, int id_equipe2, Date date, String heure_deb, String heure_fin, int nb_spectateur, String description, String status) {
        this.id_match = id_match;
        this.id_stade = id_stade;
        this.id_org = id_org;
        this.id_equipe1 = id_equipe1;
        this.id_equipe2 = id_equipe2;
        this.date = date;
        this.heure_deb = heure_deb;
        this.heure_fin = heure_fin;
        this.nb_spectateur = nb_spectateur;
        this.description = description;
        this.status = status;
        
    }

    public Match(int id_match, int id_stade, int id_org, int id_equipe1, int id_equipe2, Date date, String heure_deb, String heure_fin, int nb_spectateur, String description, String status, String score) {
        this.id_match = id_match;
        this.id_stade = id_stade;
        this.id_org = id_org;
        this.id_equipe1 = id_equipe1;
        this.id_equipe2 = id_equipe2;
        this.date = date;
        this.heure_deb = heure_deb;
        this.heure_fin = heure_fin;
        this.nb_spectateur = nb_spectateur;
        this.description = description;
        this.status = status;
        this.score = score;
    }

  

   
    public Match() {

    }

 
/*** constructeur pour l'ajout***/
    public Match(int id_org,int id_stade, int id_equipe1, int id_equipe2, Date date, String heure_deb, String heure_fin, int nb_spectateur, String description, String status, String score) {
        this.id_stade = id_stade;
        this.id_equipe1 = id_equipe1;
        this.id_equipe2 = id_equipe2;
        this.date = date;
        this.heure_deb = heure_deb;
        this.heure_fin = heure_fin;
        this.nb_spectateur = nb_spectateur;
        this.description = description;
        this.status = status;
        this.score = score;
        this.id_org=id_org;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getScore() {
        return score;
    }

    /**
     * ********* getteur***************
     */
    public int getId_match() {

        return id_match;

    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public int getId_stade() {
        return id_stade;
    }

    public int getId_equipe1() {
        return id_equipe1;
    }

    public int getId_equipe2() {
        return id_equipe2;
    }

    public Date getDate() {
        return date;
    }

    public String getHeure_deb() {
        return heure_deb;
    }

    public String getHeure_fin() {
        return heure_fin;
    }

    public int getNb_spectateur() {
        return nb_spectateur;
    }

    public String getDescription() {
        return description;
    }

    /**
     * ************** setteur *****************************
     */
    public void setId_match(int id_match) {
        this.id_match = id_match;
    }

    public void setId_stade(int id_stade) {
        this.id_stade = id_stade;
    }

    public void setId_equipe1(int id_equipe1) {
        this.id_equipe1 = id_equipe1;
    }

    public void setId_equipe2(int id_equipe2) {
        this.id_equipe2 = id_equipe2;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setHeure_deb(String heure_deb) {
        this.heure_deb = heure_deb;
    }

    public void setHeure_fin(String heure_fin) {
        this.heure_fin = heure_fin;
    }

    public void setNb_spectateur(int nb_spectateur) {
        this.nb_spectateur = nb_spectateur;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId_org() {
        return id_org;
    }

    public void setId_org(int id_org) {
        this.id_org = id_org;
    }

    @Override
    public String toString() {
        return "Match{" + "id_match=" + id_match + ", id_stade=" + id_stade + ", id_org=" + id_org + ", id_equipe1=" + id_equipe1 + ", id_equipe2=" + id_equipe2 + ", date=" + date + ", heure_deb=" + heure_deb + ", heure_fin=" + heure_fin + ", nb_spectateur=" + nb_spectateur + ", description=" + description + ", status=" + status + ", score=" + score + '}';
    }

    /**
     * *************** toString *******************
     */
}

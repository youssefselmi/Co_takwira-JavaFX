/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cotakwira.entities;

import java.util.Objects;
import javafx.scene.control.TableColumn;

/**
 *
 * @author pc_dell
 */
public class Equipe {

    int id_equipe;
    String nom_equipe;
    // int nb_joueurs;
    int id_coach;
    String nom_coach;
    String prenom_coach;

    public Equipe() {
    }


      public Equipe(String nom_equipe, int id_coach) {
        this.nom_equipe = nom_equipe;
        //this.nb_joueurs = nb_joueurs;
        this.id_coach = id_coach;
        
    }


 

    public Equipe(int id_equipe, String nom_equipe, int id_coach) {
        this.id_equipe = id_equipe;
        this.nom_equipe = nom_equipe;
        //this.nb_joueurs = nb_joueurs;
        this.id_coach = id_coach;
    }

    public Equipe(int id_equipe) { //lel supprimer
        this.id_equipe = id_equipe;

    }

    public int getId_equipe() {
        return id_equipe;
    }

    public String getNom_equipe() {
        return nom_equipe;
    }

    public String getNom_coach() {
        return nom_coach;
    }

    public void setNom_coach(String nom_coach) {
        this.nom_coach = nom_coach;
    }

    /* public int getNb_joueurs() {
        return nb_joueurs;
    }*/
    public int getId_coach() {
        return id_coach;
    }

    public void setId_equipe(int id_equipe) {
        this.id_equipe = id_equipe;
    }

    public void setNom_equipe(String nom_equipe) {
        this.nom_equipe = nom_equipe;
    }

    /*   public void setNb_joueurs(int nb_joueurs) {
        this.nb_joueurs = nb_joueurs;
    }*/
    public void setId_coach(int id_coach) {
        this.id_coach = id_coach;
    }

    @Override
    public String toString() {
        return "Equipe{" + "id_equipe=" + id_equipe + ", nom_equipe=" + nom_equipe + ", id_coach=" + id_coach + ", nom_coach=" + nom_coach + ", prenom_coach=" + prenom_coach + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + this.id_equipe;
        hash = 29 * hash + Objects.hashCode(this.nom_equipe);
        hash = 29 * hash + this.id_coach;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Equipe other = (Equipe) obj;
        return true;
    }

    public String getPrenom_coach() {
        return prenom_coach;
    }

    public void setPrenom_coach(String prenom_coach) {
        this.prenom_coach = prenom_coach;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cotakwira.entities;

/**
 *
 * @author esprit
 */
public class Organisateur {
    
    private int id_org;
    private String nom;
    private String prenom;
    private String email;
    private String mdp;
    private int score;

    public Organisateur() {
    }

    public Organisateur(int id_org, String nom, String prenom, String email, String mdp, int score) {
        this.id_org = id_org;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.mdp = mdp;
        this.score = score;
    }

    public Organisateur(String nom, String prenom, String email, String mdp, int score) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.mdp = mdp;
        this.score = score;
    }

    public int getId_org() {
        return id_org;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public String getMdp() {
        return mdp;
    }

    public int getScore() {
        return score;
    }

    public void setId_org(int id_org) {
        this.id_org = id_org;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Organisateur{" + "id_org=" + id_org + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", mdp=" + mdp + ", score=" + score + '}';
    }
    
    
}

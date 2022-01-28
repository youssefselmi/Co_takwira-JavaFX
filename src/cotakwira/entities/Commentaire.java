/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cotakwira.entities;

import java.util.Date;

/**
 *
 * @author khali
 */
public class Commentaire {
    int id;
    String contenu;
    Date date;
    int id_article;
    int id_joueur;

    public Commentaire() {
    }

    public Commentaire(String contenu, Date date, int id_article, int id_joueur) {
        this.contenu = contenu;
        this.date = date;
        this.id_article = id_article;
        this.id_joueur = id_joueur;
    }

    public Commentaire(int id, String contenu, Date date, int id_article, int id_joueur) {
        this.id = id;
        this.contenu = contenu;
        this.date = date;
        this.id_article = id_article;
        this.id_joueur = id_joueur;
    }

    public Commentaire(int id, String contenu, int id_article, int id_joueur) {
        this.id = id;
        this.contenu = contenu;
        this.id_article = id_article;
        this.id_joueur = id_joueur;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getId_article() {
        return id_article;
    }

    public void setId_article(int id_article) {
        this.id_article = id_article;
    }

    public int getId_joueur() {
        return id_joueur;
    }

    public void setId_joueur(int id_joueur) {
        this.id_joueur = id_joueur;
    }

    @Override
    public String toString() {
        return "Commentaire{" + "id=" + id + ", contenu=" + contenu + ", date=" + date + ", id_article=" + id_article + ", id_joueur=" + id_joueur + '}';
    }
    
    
    
}

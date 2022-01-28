/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cotakwira.entities;

import java.sql.Date;

/**
 *
 * @author ASUS
 */
public class Entrainement {
    
    
    private int id_entrainement;
    private Date date_entrainement;
    private int duree;
    private int id_coach;
    private int id_stade;
    private int id_equipe;
    private String nom_equipee;
    private String nom_coache;
    private String nom_stadee;
    private String type;
    private String heure;


    
    
    //    private String nom_equipe;

    
    
    public Entrainement()
    {}
    
    
    public Entrainement(int id_entrainement,Date date_entrainement,String heure, int duree,int id_coach,int id_stade,int id_equipe, String nom_equipee, String nom_coache, String nom_stade, String type)
    {
    
        this.id_entrainement=id_entrainement;
        this.date_entrainement=date_entrainement;
            this.heure=heure;
        this.duree=duree;
        this.id_coach=id_coach;
        this.id_stade=id_stade;
        this.id_equipe=id_equipe;
        this.nom_equipee=nom_equipee;
        this.nom_coache=nom_coache;
        this.nom_stadee=nom_stade;
        this.type=type;
    

        
    }

    public Date getDate_entrainement() {
        return date_entrainement;
    }

    public int getDuree() {
        return duree;
    }

    public int getId_coach() {
        return id_coach;
    }

    public int getId_entrainement() {
        return id_entrainement;
    }

    public int getId_equipe() {
        return id_equipe;
    }

    public int getId_stade() {
        return id_stade;
    }

    @Override
    public String toString() {
        return "Entrainement{" + "id_entrainement=" + id_entrainement + ", date_entrainement=" + date_entrainement + ", duree=" + duree + ", id_coach=" + id_coach + ", id_stade=" + id_stade + ", id_equipe=" + id_equipe + ", nom_equipee=" + nom_equipee + ", nom_coache=" + nom_coache + ", nom_stadee=" + nom_stadee + ", type=" + type + ", heure=" + heure + '}';
    }

    
   



    
    
    public void setDate_entrainement(Date date_entrainement) {
        this.date_entrainement = date_entrainement;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public void setId_coach(int id_coach) {
        this.id_coach = id_coach;
    }

    public void setId_entrainement(int id_entrainement) {
        this.id_entrainement = id_entrainement;
    }

    public void setId_equipe(int id_equipe) {
        this.id_equipe = id_equipe;
    }

    public void setId_stade(int id_stade) {
        this.id_stade = id_stade;
    }

    public String getNom_equipee() {
        return nom_equipee;
    }

    public void setNom_equipee(String nom_equipee) {
        this.nom_equipee = nom_equipee;
    }

    public String getNom_coache() {
        return nom_coache;
    }

    public void setNom_coache(String nom_coache) {
        this.nom_coache = nom_coache;
    }

    public String getNom_stadee() {
        return nom_stadee;
    }

    public void setNom_stadee(String nom_stadee) {
        this.nom_stadee = nom_stadee;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

  

   
  
      
    
    
    
    
    
    
}

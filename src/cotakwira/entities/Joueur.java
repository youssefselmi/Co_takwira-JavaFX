package cotakwira.entities;


import java.sql.Date;
import javafx.scene.image.ImageView;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author pc_dell
 */
public class Joueur {
    int id_joueur;
    String nom_joueur	;
    String prenom_joueur;
    Date dateNaissance;
     int age_joueur;	
     int numero;	
     String adresse_mail;
     String ville;	
     int id_equipe;
     String categorie;
     String position;
     String image;
     String password;
     String nom_equipe;
     ImageView imageJouer;

    public Joueur() {
    }

    public Joueur(String nom_joueur, String prenom_joueur, Date DateNaissance,int age_joueur, int numero, String adresse_mail, String ville,String categorie,String position,int id_equipe,String image,String password) {
        this.nom_joueur = nom_joueur;
        this.prenom_joueur = prenom_joueur;
        this.dateNaissance = DateNaissance;
        this.age_joueur=age_joueur;
        this.numero = numero;
        this.adresse_mail = adresse_mail;
        this.ville = ville;
                this.categorie=categorie;
        this.position=position;

        this.id_equipe = id_equipe;
        this.image=image;
        this.password=password;
    }
     public Joueur(String nom_joueur, String prenom_joueur, Date DateNaissance,int age_joueur, int numero, String adresse_mail, String ville,String categorie,String position,int id_equipe,String image,ImageView imageJoueur) {
        this.nom_joueur = nom_joueur;
        this.prenom_joueur = prenom_joueur;
        this.dateNaissance = DateNaissance;
        this.age_joueur=age_joueur;
        this.numero = numero;
        this.adresse_mail = adresse_mail;
        this.ville = ville;
                this.categorie=categorie;
        this.position=position;

        this.id_equipe = id_equipe;
        this.password=password;
        this.imageJouer=imageJoueur;
    }
     
     
     

    public Joueur(String nom_joueur, String prenom_joueur,Date DateNaissance,  int age_joueur, int numero, String adresse_mail, String ville, String position) {
        this.nom_joueur = nom_joueur;
        this.prenom_joueur = prenom_joueur;
        this.dateNaissance = DateNaissance;
        this.age_joueur=age_joueur;
        this.numero = numero;
        this.adresse_mail = adresse_mail;
        this.ville = ville;  
        this.position = position;
    
    }

    public ImageView getImageJouer() {
        return imageJouer;
    }

    public void setImageJouer(ImageView imageJouer) {
        this.imageJouer = imageJouer;
    }
    

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

  

    public int getId_joueur() {
        return id_joueur;
    }

    public String getNom_joueur() {
        return nom_joueur;
    }

    public String getPrenom_joueur() {
        return prenom_joueur;
    }

    public int getAge_joueur() {
        return age_joueur;
    }

    public void setNom_equipe(String nom_equipe) {
        this.nom_equipe = nom_equipe;
    }

    public String getNom_equipe() {
        return nom_equipe;
    }

   

    public int getNumero() {
        return numero;
    }

    public String getAdresse_mail() {
        return adresse_mail;
    }

    public String getVille() {
        return ville;
    }

    public int getId_equipe() {
        return id_equipe;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    

    public void setId_joueur(int id_joueur) {
        this.id_joueur = id_joueur;
    }

    public void setNom_joueur(String nom_joueur) {
        this.nom_joueur = nom_joueur;
    }

    public void setPrenom_joueur(String prenom_joueur) {
        this.prenom_joueur = prenom_joueur;
    }

    public void setAge_joueur(int age_joueur) {
        this.age_joueur = age_joueur;
    }

   

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setAdresse_mail(String adresse_mail) {
        this.adresse_mail = adresse_mail;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public void setId_equipe(int id_equipe) {
        this.id_equipe = id_equipe;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getCategorie() {
        return categorie;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Joueur{" + "id_joueur=" + id_joueur + ", nom_joueur=" + nom_joueur + ", prenom_joueur=" + prenom_joueur + ", dateNaissance=" + dateNaissance + ", age_joueur=" + age_joueur + ", numero=" + numero + ", adresse_mail=" + adresse_mail + ", ville=" + ville + ", categorie=" + categorie + ", position=" + position +", id_equipe=" + id_equipe +  '}';
    }
    

    

     


    }

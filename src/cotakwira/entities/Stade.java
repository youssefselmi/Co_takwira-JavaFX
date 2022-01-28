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
public class Stade {

    private int id_org;
    private int id_stade;
    private String nom_stade;
    private String ville;
    private String adresse;
    private int capacite;
    

    public Stade() {

    }

    

    public Stade(int id_org,int id_stade, String nom_stade, String ville, String adresse, int capacite) {
        this.id_stade = id_stade;
        this.nom_stade = nom_stade;
        this.ville = ville;
        this.adresse = adresse;
        this.capacite = capacite;
        this.id_org = id_org;
       
    }

    public void setId_org(int id_org) {
        this.id_org = id_org;
    }

   /*public Stade(int id_stade, String nom_stade, String ville, String adresse, int capacite) {
        this.id_stade = id_stade;
        this.nom_stade = nom_stade;
        this.ville = ville;
        this.adresse = adresse;
        this.capacite = capacite;
    }*/

    public int getId_org() {
        return id_org;
    }

   

   

    public Stade(int id_org,String nom_stade, String ville, String adresse, int capacite) {
        this.nom_stade = nom_stade;
        this.ville = ville;
        this.adresse = adresse;
        this.capacite = capacite;
        this.id_org = id_org;
    }



    /**
     * ********************* Les getteurs ********************
     */
    public int getId_stade() {
        return id_stade;
    }
     

    public String getNom_stade() {
        return nom_stade;
    }

    public String getVille() {
        return ville;
    }

    public String getAdresse() {
        return adresse;
    }

    public int getCapacite() {
        return capacite;
    }

   

    /**
     * *********************** les setteurs ***************
     */
    public void setId_stade(int id_stade) {
        this.id_stade = id_stade;
    }

    public void setNom_stade(String nom_stade) {
        this.nom_stade = nom_stade;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    @Override
    public String toString() {
        return "Stade{" + "id_org=" + id_org + ", id_stade=" + id_stade + ", nom_stade=" + nom_stade + ", ville=" + ville + ", adresse=" + adresse + ", capacite=" + capacite + '}';
    }


   

   

    
    
    

}

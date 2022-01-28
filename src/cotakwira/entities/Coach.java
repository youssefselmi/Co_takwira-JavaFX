/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cotakwira.entities;

import java.sql.Date;
import javafx.scene.image.ImageView;

/**
 *
 * @author ASUS
 */
public class Coach {
    
    private int id_coach;
    private String nom_coach;
    private String prenom_coach;
    private Date date_naissance;
    private String grade;
    private Date date_fin_contrat;
    private String adresse_mail;
    private float salaire;
    private int nb_trophe_locaux;
    private int nb_trophe_internationaux;
    private String formation_prefere;
    private String image_formation;
    private int age;
    private ImageView imagedisplay ;
    private ImageView imagedisplayformation ;
    private String image_coach;
    private String mdp_coach;


    
        public Coach() {
    }
        
        
    
        public Coach(int id_coach,String nom_coach,String prenom_coach,Date date_naissance,String grade,Date date_fin_contrat,String adresse_mail,float salaire)
        {
        this.id_coach=id_coach;
        this.nom_coach=nom_coach;
        this.prenom_coach=prenom_coach;
        this.date_naissance=date_naissance;
        this.grade=grade;
        this.date_fin_contrat=date_fin_contrat;
        this.adresse_mail=adresse_mail;
        this.salaire=salaire;
        
        }

//    public Coach(String nom_coach, String prenom_coach, Date date_naissance, String grade, Date date_fin_contrat, String adresse_mail, float salaire, int nb_trophe_locaux, int nb_trophe_internationaux, String formation_prefere, String image_formation, String image_coach) {
//       // this.id_coach = id_coach;
//        this.nom_coach = nom_coach;
//        this.prenom_coach = prenom_coach;
//        this.date_naissance = date_naissance;
//        this.grade = grade;
//        this.date_fin_contrat = date_fin_contrat;
//        this.adresse_mail = adresse_mail;
//        this.salaire = salaire;
//        this.nb_trophe_locaux = nb_trophe_locaux;
//        this.nb_trophe_internationaux = nb_trophe_internationaux;
//        this.formation_prefere = formation_prefere;
//        this.image_formation = image_formation;
//        this.image_coach = image_coach;
//    }              
                  
                       
                  
    
    public Coach(String nom_coach, String prenom_coach, Date date_naissance, String grade, Date date_fin_contrat, String adresse_mail, float salaire) {
         
        this.nom_coach=nom_coach;
        this.prenom_coach=prenom_coach;
        this.date_naissance=date_naissance;
        this.grade=grade;
        this.date_fin_contrat=date_fin_contrat;
        this.adresse_mail=adresse_mail;
        this.salaire=salaire;
        
    }

    public Coach(String nom_coach, String prenom_coach, Date date_naissance, String grade, Date date_fin_contrat, String adresse_mail, float salaire, int nb_trophe_locaux, int nb_trophe_internationaux , String formation_prefere, String image_formation, String image_coach, int age) {

        this.nom_coach = nom_coach;
        this.prenom_coach = prenom_coach;
        this.date_naissance = date_naissance;
        this.grade = grade;
        this.date_fin_contrat = date_fin_contrat;
        this.adresse_mail = adresse_mail;
        this.salaire = salaire;
        this.nb_trophe_locaux = nb_trophe_locaux;
        this.nb_trophe_internationaux = nb_trophe_internationaux;
        this.formation_prefere = formation_prefere;
        this.image_formation = image_formation;
        this.image_coach = image_coach;
        this.age = age;
        
    }

//     public Coach(String nom_coach, String prenom_coach, Date date_naissance, String grade, Date date_fin_contrat, String adresse_mail, float salaire, int nb_trophe_locaux, int nb_trophe_internationaux , String formation_prefere, String image_formation) {
//
//   this.nom_coach = nom_coach;
//        this.prenom_coach = prenom_coach;
//        this.date_naissance = date_naissance;
//        this.grade = grade;
//        this.date_fin_contrat = date_fin_contrat;
//        this.adresse_mail = adresse_mail;
//        this.salaire = salaire;
//        this.nb_trophe_locaux = nb_trophe_locaux;
//        this.nb_trophe_internationaux = nb_trophe_internationaux;
//        this.formation_prefere = formation_prefere;
//      //  this.image_formation = image_formation;
//        //this.image_coach = image_coach;
//        //this.age = age;
//        
//    }

//    public Coach(int id_coach, String nom_coach, String prenom_coach, Date date_naissance , String grade, Date date_fin_contrat, String adresse_mail, float salaire, int nb_trophe_locaux, int nb_trophe_internationaux, String formation_prefere) {
//
//        this.prenom_coach = prenom_coach;
//        this.date_naissance = date_naissance;
//        this.grade = grade;
//        this.date_fin_contrat = date_fin_contrat;
//        this.adresse_mail = adresse_mail;
//        this.salaire = salaire;
//        this.nb_trophe_locaux = nb_trophe_locaux;
//        this.nb_trophe_internationaux = nb_trophe_internationaux;
//        this.formation_prefere = formation_prefere;
//      this.image_formation = image_formation;
//       this.image_coach = image_coach;
//       this.age = age;
//
//    }

    public Coach(int id_coach, String nom_coach, String prenom_coach, Date date_naissance , String grade, Date date_fin_contrat, String adresse_mail, float salaire, int nb_trophe_locaux, int nb_trophe_internationaux, String formation_prefere) {
        this.id_coach = id_coach; 
                this.nom_coach = nom_coach;

        this.prenom_coach = prenom_coach;
        this.date_naissance = date_naissance;
        this.grade = grade;
        this.date_fin_contrat = date_fin_contrat;
        this.adresse_mail = adresse_mail;
        this.salaire = salaire;
        this.nb_trophe_locaux = nb_trophe_locaux;
        this.nb_trophe_internationaux = nb_trophe_internationaux ;
        this.formation_prefere = formation_prefere;
    //  this.image_formation = image_formation;
       //this.image_coach = image_coach;
     
    }
    
    
    
    
      public Coach(int id_coach, String nom_coach, String prenom_coach, Date date_naissance , String grade, Date date_fin_contrat, String adresse_mail, float salaire, int nb_trophe_locaux, int nb_trophe_internationaux, String formation_prefere, String image_coach, ImageView imagedisplay) {
        this.id_coach = id_coach; 
                this.nom_coach = nom_coach;

        this.prenom_coach = prenom_coach;
        this.date_naissance = date_naissance;
        this.grade = grade;
        this.date_fin_contrat = date_fin_contrat;
        this.adresse_mail = adresse_mail;
        this.salaire = salaire;
        this.nb_trophe_locaux = nb_trophe_locaux;
        this.nb_trophe_internationaux = nb_trophe_internationaux ;
        this.formation_prefere = formation_prefere;
    //  this.image_formation = image_formation;
       this.image_coach = image_coach;
       this.imagedisplay = imagedisplay;

     
    }
      
      
      
      
        public Coach(int id_coach, String nom_coach, String prenom_coach, Date date_naissance , String grade, Date date_fin_contrat, String adresse_mail, float salaire, int nb_trophe_locaux, int nb_trophe_internationaux, String formation_prefere, String image_formation, String image_coach, ImageView imagedisplay, ImageView imagedisplayformation) {
        this.id_coach = id_coach; 
        this.nom_coach = nom_coach;
        this.prenom_coach = prenom_coach;
        this.date_naissance = date_naissance;
        this.grade = grade;
        this.date_fin_contrat = date_fin_contrat;
        this.adresse_mail = adresse_mail;
        this.salaire = salaire;
        this.nb_trophe_locaux = nb_trophe_locaux;
        this.nb_trophe_internationaux = nb_trophe_internationaux ;
        this.formation_prefere = formation_prefere;
        this.image_coach = image_coach;
        this.image_formation = image_formation;
        this.imagedisplayformation = imagedisplayformation;
        this.imagedisplay = imagedisplay;

     
    }

//         
//         public Coach(int id_coach, String nom_coach, String prenom_coach, Date date_naissance , String grade, Date date_fin_contrat, String adresse_mail, float salaire, int nb_trophe_locaux, int nb_trophe_internationaux, String formation_prefere, String image_formation, String image_coach, ImageView imagedisplay, ImageView imagedisplayformation,String mdp_coach) {
//        this.id_coach = id_coach; 
//        this.nom_coach = nom_coach;
//        this.prenom_coach = prenom_coach;
//        this.date_naissance = date_naissance;
//        this.grade = grade;
//        this.date_fin_contrat = date_fin_contrat;
//        this.adresse_mail = adresse_mail;
//        this.salaire = salaire;
//        this.nb_trophe_locaux = nb_trophe_locaux;
//        this.nb_trophe_internationaux = nb_trophe_internationaux ;
//        this.formation_prefere = formation_prefere;
//       this.image_coach = image_coach;
//       this.image_formation = image_formation;
//      this.imagedisplayformation = imagedisplayformation;
//       this.imagedisplay = imagedisplay;
//       this.mdp_coach = mdp_coach;
//
//     
//    }
//            
            

    public Coach( int id_coach)
    {
    this.id_coach=id_coach;
    
        
    }
            
            

    public Coach(String nom_coach, String prenom_coach, Date date_naissance, String grade, Date date_fin_contrat, String adresse_mail, float salaire, int nb_trophe_locaux, int nb_trophe_internationaux, String formation_prefere, String image_formation, String image_coach,int age,String mdp_coach) {
         this.nom_coach = nom_coach;
        this.prenom_coach = prenom_coach;
        this.date_naissance = date_naissance;
        this.grade = grade;
        this.date_fin_contrat = date_fin_contrat;
        this.adresse_mail = adresse_mail;
        this.salaire = salaire;
        this.nb_trophe_locaux = nb_trophe_locaux;
        this.nb_trophe_internationaux = nb_trophe_internationaux ;
        this.formation_prefere = formation_prefere;
       this.image_coach = image_coach;
       this.image_formation = image_formation;
      this.imagedisplay = imagedisplay;
        this.age = age;
       this.mdp_coach = mdp_coach;   
    }

//    public Coach(String nom_coach, String prenom_coach, Date date, String b0, Date date0, String mail, float salaire, int nbtr1, int nbtr2, String s, String image_formation, String image_coach, int age, String dd) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

       
            
            


 

    public int getId_coach() {
        return id_coach;
    }

    public String getNom_coach() {
        return nom_coach;
    }

    public String getPrenom_coach() {
        return prenom_coach;
    }

    public Date getDate_naissance() {
        return date_naissance;
    }


    

    public Date getDate_fin_contrat() {
        return date_fin_contrat;
    }

    public String getGrade() {
        return grade;
    }

    public String getAdresse_mail() {
        return adresse_mail;
    }

    public float getSalaire() {
        return salaire;
    }

    @Override
    public String toString() {
        return "Coach{" + "id_coach=" + id_coach + ", nom_coach=" + nom_coach + ", prenom_coach=" + prenom_coach + ", date_naissance=" + date_naissance + ", grade=" + grade + ", date_fin_contrat=" + date_fin_contrat + ", adresse_mail=" + adresse_mail + ", salaire=" + salaire + ", nb_trophe_locaux=" + nb_trophe_locaux + ", nb_trophe_internationaux=" + nb_trophe_internationaux + ", formation_prefere=" + formation_prefere + ", image_formation=" + image_formation + ", image_coach=" + image_coach + '}';
    }
    
    
    
    
    

  

    public void setDate_fin_contrat(Date date_fin_contrat) {
        this.date_fin_contrat = date_fin_contrat;
    }

    public void setDate_naissance(Date date_naissance) {
        this.date_naissance = date_naissance;
    }


    

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setId_coach(int id_coach) {
        this.id_coach = id_coach;
    }

    public void setNom_coach(String nom_coach) {
        this.nom_coach = nom_coach;
    }

    public void setPrenom_coach(String prenom_coach) {
        this.prenom_coach = prenom_coach;
    }

    public void setAdresse_mail(String adresse_mail) {
        this.adresse_mail = adresse_mail;
    }

    public void setSalaire(float salaire) {
        this.salaire = salaire;
    }

    public int getNb_trophe_locaux() {
        return nb_trophe_locaux;
    }

    public int getNb_trophe_internationaux() {
        return nb_trophe_internationaux;
    }

    public String getFormation_prefere() {
        return formation_prefere;
    }

    public String getImage_formation() {
        return image_formation;
    }

    public String getImage_coach() {
        return image_coach;
    }

    public void setNb_trophe_locaux(int nb_trophe_locaux) {
        this.nb_trophe_locaux = nb_trophe_locaux;
    }

    public void setNb_trophe_internationaux(int nb_trophe_internationaux) {
        this.nb_trophe_internationaux = nb_trophe_internationaux;
    }

    public void setFormation_prefere(String formation_prefere) {
        this.formation_prefere = formation_prefere;
    }

    public void setImage_formation(String image_formation) {
        this.image_formation = image_formation;
    }

    public void setImage_coach(String image_coach) {
        this.image_coach = image_coach;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    
    
    public ImageView getImagedisplay() {
        return imagedisplay;
    }

    public void setImagedisplay(ImageView imagedisplay) {
        this.imagedisplay = imagedisplay;
    }

    public ImageView getImagedisplayformation() {
        return imagedisplayformation;
    }

    public void setImagedisplayformation(ImageView imagedisplayformation) {
        this.imagedisplayformation = imagedisplayformation;
    }

    public String getMdp_coach() {
        return mdp_coach;
    }

    public void setMdp_coach(String mdp_coach) {
        this.mdp_coach = mdp_coach;
    }
    
    
    
    
    
    
    
    
    
        
        
        
    
    
    
    
}

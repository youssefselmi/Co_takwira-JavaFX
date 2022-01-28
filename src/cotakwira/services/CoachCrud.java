/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cotakwira.services;

import cotakwira.entities.Coach;
import cotakwira.entities.Entrainement;
import cotakwira.entities.Equipe;
import cotakwira.entities.Stade;
import cotakwira.entities.Equipe;
import cotakwira.interfaces.ICoach;
import cotakwira.tools.Connection;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author ASUS
 */
public class CoachCrud implements ICoach<Coach>{

    @Override
    
    
    
   
    public void ajouterCoach(Coach c) {

try {
           String requete= "INSERT INTO coach(nom_coach, prenom_coach, date_naissance, grade, date_fin_contrat, adresse_mail, salaire, nb_trophe_locaux, nb_trophe_internationaux, formation_prefere, image_formation, image_coach, age, mdp_coach)"
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = Connection.getInstance().getCnx()
                    .prepareStatement(requete);
            pst.setInt    (1, c.getId_coach());
            pst.setString (1, c.getNom_coach());
            pst.setString (2, c.getPrenom_coach());
            pst.setDate   (3, c.getDate_naissance());
            pst.setString (4, c.getGrade());
            pst.setDate   (5, (Date) c.getDate_fin_contrat());
            pst.setString (6, c.getAdresse_mail());
            pst.setFloat   (7, c.getSalaire());
            pst.setInt(8, c.getNb_trophe_locaux());
            pst.setInt(9, c.getNb_trophe_locaux());
            pst.setString(10, c.getFormation_prefere());
            pst.setString(11, c.getImage_formation());
            pst.setString(12, c.getImage_coach());
            pst.setInt(13,c.getAge());
            pst.setString(14, c.getMdp_coach());

            






            
            
            pst.executeUpdate();
            System.out.println("Coach ajouté");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }





    }

    @Override
    public void supprimerCoach(Coach c) {

try {
            String requete = "DELETE FROM coach where id_coach=?";
            PreparedStatement pst = Connection.getInstance().getCnx()
                    .prepareStatement(requete);
            pst.setInt(1, c.getId_coach());
            pst.executeUpdate();
            System.out.println("Coach supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }


    }

    @Override
    public void updateCoach(Coach c) {
        
         try {
            String requete = "UPDATE coach SET nom_coach=?,prenom_coach=?,date_naissance=?,grade=?,date_fin_contrat=?,adresse_mail=?,salaire=?,nb_trophe_locaux=?,nb_trophe_internationaux=?,formation_prefere=? WHERE id_coach=?";
            PreparedStatement pst = Connection.getInstance().getCnx().prepareStatement(requete);
            pst.setString(1, c.getNom_coach());
            pst.setString(2, c.getPrenom_coach());
            pst.setDate(3, c.getDate_naissance());
            pst.setString(4, c.getGrade());
            pst.setDate(5,c.getDate_fin_contrat());
            pst.setString(6, c.getAdresse_mail());
            pst.setFloat(7, c.getSalaire());
            pst.setInt(8, c.getNb_trophe_locaux());
            pst.setInt(9, c.getNb_trophe_internationaux());
            pst.setString(10, c.getFormation_prefere());
            pst.setInt(11, c.getId_coach());
    
            pst.executeUpdate();
            System.out.println("Coach modifiée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
    
    
    
    @Override
    public List<Coach> afficherCoachs() {



 List<Coach> coachList = new ArrayList<>();
 
 
 
 
 
        try {
            String requete = "SELECT * FROM coach";
            Statement st = Connection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs =  st.executeQuery(requete);
            while(rs.next()){
                
            Coach c = new Coach();

            
                ImageView img = new ImageView();
                img.setFitWidth(50);
                img.setFitHeight(50);
                
                     Image image;
                      try {
                       image = new Image(new FileInputStream((rs.getString("image_coach"))));
                       
                        img.setImage(image);
                        
                       img.setPreserveRatio(true);
                 
                  
                  
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(CoachCrud.class.getName()).log(Level.SEVERE, null, ex);
                }
                 
                      
                      
                      
                      ImageView imgformation = new ImageView();
                      
                        imgformation.setFitWidth(50);
                        imgformation.setFitHeight(50);

                        
                        Image imageformation;
                      try {
                       imageformation = new Image(new FileInputStream((rs.getString("image_formation"))));
                       
                        imgformation.setImage(imageformation);
                        
                       imgformation.setPreserveRatio(true);
                       
                  
                  
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(CoachCrud.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
                
                c.setId_coach(rs.getInt(1));
                c.setNom_coach(rs.getString(2));
                c.setPrenom_coach(rs.getString(3));
                c.setDate_naissance(rs.getDate(4));
                c.setGrade(rs.getString(5));
                c.setDate_fin_contrat(rs.getDate(6));
                c.setAdresse_mail(rs.getString(7));
                c.setSalaire(rs.getFloat(8));
                c.setNb_trophe_locaux(rs.getInt(9));
                c.setNb_trophe_internationaux(rs.getInt(10));
                c.setFormation_prefere(rs.getString(11));
                c.setImagedisplay(img);
                c.setImagedisplayformation(imgformation);
 
                coachList.add(c);
                     
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return coachList;
    }

    
    
    
   // @Override
    public void supprimerCoach_fin_contrat() {
                
   
       long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);
        
        try {
            String requete = "DELETE FROM coach where date_fin_contrat <= '"+date+"' ";
            
           
             Statement st = Connection.getInstance().getCnx()
                    .createStatement();
            st.executeUpdate(requete);
            
            
            System.out.println("Tableau coaxh a ete mis a jour");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        
        
        
        
        
        
    }

    

    
    public void miseajourCoach() {
        
   
        
 List<Coach> coachList = new ArrayList<>();
 
        try {
            String requete = "SELECT * FROM coach";
            Statement st = Connection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs =  st.executeQuery(requete);
            while(rs.next()){
                Coach c = new Coach();
                c.setId_coach(rs.getInt(1));
                c.setNom_coach(rs.getString(2));
                c.setPrenom_coach(rs.getString(3));
                c.setDate_naissance(rs.getDate(4));
                c.setGrade(rs.getString(5));
                c.setDate_fin_contrat(rs.getDate(6));
                c.setAdresse_mail(rs.getString(7));
                c.setSalaire(rs.getFloat(8));
                coachList.add(c);
                
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
//            float x=0;
//                for(int i=0;i<coachList.size();i++)
//                {
//                x=x+coachList.get(i).getSalaire();
//                    
//                }
//        int nbr;
//            nbr = (int) coachList.stream().count();
//        
//        return nbr;
        
//               float average = (float) coachList.stream()
//                
//               .mapToDouble(e->e.getSalaire()).average().getAsDouble();
//        
//		 System.out.println(average);
//                          
//                return average
         
       

 String coach;
        double salaire_max,salaire_min,total_salaire;
            coach = Stream.of(coachList.stream().count(),
                    salaire_max= coachList.stream().mapToDouble(e ->e.getSalaire()).max().getAsDouble(),
                    salaire_min=coachList.stream().mapToDouble(e -> e.getSalaire()).min().getAsDouble(),
                    total_salaire=coachList.stream().mapToDouble(e -> e.getSalaire()).sum())
                                .map(Object::toString) .collect(Collectors.joining("==="));
                                     
            
                       //  System.out.println("Le nombre des coachs disponible  "+coach+"\n");

                         for(int i=0;i<coachList.size();i++)
                         {
                         if(coachList.get(i).getSalaire()==salaire_max)
                         
                         {
                             System.out.println("Le coach qui a le salaire le plus elevé est  "+coachList.get(i).getNom_coach()+" "+coachList.get(i).getPrenom_coach()+"====>"+salaire_max+" DT");
                         }
                         
                         if(coachList.get(i).getSalaire()==salaire_min)
                         
                         {
                             System.out.println("Le coach qui a le salaire minimale est  "+coachList.get(i).getNom_coach()+" "+coachList.get(i).getPrenom_coach()+"====>"+salaire_min+" DT");
                         }
                         
                         
                         }
                         System.out.println("Le totale des salaire des coachs est  "+total_salaire+" DT");
                          
                          
                          
                          
    }

    @Override
    public void supprimerCoach_fin_contrat(Coach t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void miseajourCoach(Coach t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
    
    public void affichierCoachEntrainement() {



 List<Coach> coachList = new ArrayList<>();
 
        try {
            String requete = "SELECT * FROM coach";
            Statement st = Connection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs =  st.executeQuery(requete);
            while(rs.next()){
                Coach c = new Coach();
                c.setId_coach(rs.getInt(1));
                c.setNom_coach(rs.getString(2));
                c.setPrenom_coach(rs.getString(3));
                c.setDate_naissance(rs.getDate(4));
                c.setGrade(rs.getString(5));
                c.setDate_fin_contrat(rs.getDate(6));
                c.setAdresse_mail(rs.getString(7));
                c.setSalaire(rs.getFloat(8));
                coachList.add(c);
                
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            
        }
      
        
 List<Entrainement> entrainementtab = new ArrayList<>();
        try {
            String requete = "SELECT * FROM entrainement";
            Statement st = Connection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs =  st.executeQuery(requete);
            while(rs.next()){
                Entrainement e = new Entrainement();
                e.setId_entrainement(rs.getInt(1));
                e.setDate_entrainement(rs.getDate(3));
                e.setDuree(rs.getInt(2));
                e.setId_equipe(rs.getInt(6));
                e.setId_coach(rs.getInt(4));
                e.setId_stade(rs.getInt(5));



                entrainementtab.add(e);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
        
        
            
         List<Equipe> equipetab = new ArrayList<>();
        try {
            String requete = "SELECT * FROM equipe";
            Statement st = Connection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs =  st.executeQuery(requete);
            while(rs.next()){
                Equipe e = new Equipe();
                e.setId_equipe(rs.getInt(1));
                e.setNom_equipe(rs.getString(2));
               



                equipetab.add(e);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
        
        
         List<Stade> Stadetab = new ArrayList<>();
        try {
            String requete = "SELECT * FROM stade";
            Statement st = Connection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs =  st.executeQuery(requete);
            while(rs.next()){
                Stade e = new Stade();
                e.setId_stade(rs.getInt(1));
                e.setNom_stade(rs.getString(2));
               



                Stadetab.add(e);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        //System.out.println(Stadetab);
        

        
        for(int j=0;j<entrainementtab.size();j++)
        {
        
        
        
        for(int i=0;i<coachList.size();i++)
        {
            for(int k=0;k<Stadetab.size();k++)
        {
             for(int l=0;l<equipetab.size();l++)
        {
            
            if(coachList.get(i).getId_coach()==entrainementtab.get(j).getId_coach() && entrainementtab.get(j).getId_stade()==Stadetab.get(k).getId_stade()&& equipetab.get(l).getId_equipe()==entrainementtab.get(j).getId_equipe() )
            
                System.out.println("Le coach  "+coachList.get(i).getNom_coach()+"  "+coachList.get(i).getPrenom_coach()+"  Afefcte pour entrainer l'equipe"+equipetab.get(l).getNom_equipe()+" qui se dreoule a   "+entrainementtab.get(j).getDate_entrainement()+"  A stade  "+Stadetab.get(k).getNom_stade()+"\n");
        }       }
       
        }

        }


        
        
        
        




    }
//@Override
    public List<Stade> afficherstade() {



 List<Stade> stadeliste = new ArrayList<>();
      

 try {
            String requete = "SELECT * FROM stade";
            Statement st = Connection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs =  st.executeQuery(requete);
            while(rs.next()){
                Stade c = new Stade();
                c.setId_stade(rs.getInt(1));
                c.setNom_stade(rs.getString(3));
                stadeliste.add(c);
                     
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return stadeliste;
    }
       
    
    
    
        public List<Equipe> afficherequipe() {



 List<Equipe> equipeliste = new ArrayList<>();
      

 try {
            String requete = "SELECT * FROM equipe";
            Statement st = Connection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs =  st.executeQuery(requete);
            while(rs.next()){
                Equipe c = new Equipe();
                c.setId_equipe(rs.getInt(1));
                c.setNom_equipe(rs.getString(2));
                equipeliste.add(c);
                       }
        } catch (SQLException ex) {
            System. out.println(ex.getMessage());
        }
        return equipeliste;
    }
    
    
    
        
        
        
        
        
    public List<Coach> afficherCoachsFront(int identifiant) {



 List<Coach> coachList = new ArrayList<>();
 
 
 
 
 
        try {
            String requete = "SELECT * FROM coach where id_coach = '"+identifiant+"'   ";
            Statement st = Connection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs =  st.executeQuery(requete);
            while(rs.next()){
                
            Coach c = new Coach();

            
                ImageView img = new ImageView();
                
                     Image image;
                      try {
                       image = new Image(new FileInputStream((rs.getString("image_coach"))));
                       
                        img.setImage(image);
                        
                       img.setPreserveRatio(true);
                 
                  
                  
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(CoachCrud.class.getName()).log(Level.SEVERE, null, ex);
                }
                 
                      
                      
                      
                      ImageView imgformation = new ImageView();

             
                        Image imageformation;
                      try {
                       imageformation = new Image(new FileInputStream((rs.getString("image_formation"))));
                       
                        imgformation.setImage(imageformation);
                        
                       imgformation.setPreserveRatio(true);
                 
                  
                  
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(CoachCrud.class.getName()).log(Level.SEVERE, null, ex);
                }
                      
                      
                
                c.setId_coach(rs.getInt(1));
                c.setNom_coach(rs.getString(2));
                c.setPrenom_coach(rs.getString(3));
                c.setDate_naissance(rs.getDate(4));
                c.setGrade(rs.getString(5));
                c.setDate_fin_contrat(rs.getDate(6));
                c.setAdresse_mail(rs.getString(7));
                c.setSalaire(rs.getFloat(8));
                c.setNb_trophe_locaux(rs.getInt(9));
                c.setNb_trophe_internationaux(rs.getInt(10));
                c.setFormation_prefere(rs.getString(11));
                
                c.setImagedisplay(img);
                c.setImagedisplayformation(imgformation);
                
                coachList.add(c);
                     
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return coachList;
    }
        
        
        
        
        
        
        
        
        
        
        
        
        
    
    
}

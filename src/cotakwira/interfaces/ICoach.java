/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cotakwira.interfaces;

import java.util.List;

/**
 *
 * @author ASUS
 */
public interface ICoach<T> {
    
    public void ajouterCoach(T t);
     public void supprimerCoach(T t);
     public void updateCoach(T t);
     public List<T> afficherCoachs();
     public void supprimerCoach_fin_contrat(T t);
     public void miseajourCoach(T t);
     
          public void affichierCoachEntrainement();




     
    
}
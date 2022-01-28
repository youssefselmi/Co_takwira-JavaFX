/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cotakwira.interfaces;

import cotakwira.entities.Entrainement;
import java.util.List;

/**
 *
 * @author ASUS
 */
public interface IEntrainement<T> {
    
     public void ajouterEntrainement(T t);
     public void supprimerEntrainement(T t);
     public void updateEntrainement(T t);
     public List<T> afficherEntrainement();
     public List<Entrainement> coachplusentraine();
     
     
    
    
}
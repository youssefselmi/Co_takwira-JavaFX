/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cotakwira.interfaces;

import java.util.List;
import java.util.Map;
//import static javafx.scene.input.KeyCode.T;

/**
 *
 * @author pc_dell
 */
public interface IJoueur<T> {
      public void ajouterJoueur(T t);
     public void supprimerJoueur(int idj);
     public void updateJoueur(int idj,T t);
     public List<T> afficherJoueur(int id,String categorie); 

    // public Map<T> afficherEquipeJoueur();
    
}

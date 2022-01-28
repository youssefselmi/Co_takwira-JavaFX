/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cotakwira.interfaces;

import java.util.List;

/**
 *
 * @author pc_dell
 */
public interface IEquipe<T> {
     public void ajouterEquipe(T t);
     public void supprimerEquipe(int ie);
     public void updateEquipe(int ie,T t);
     public List<T> afficherEquipe(); 
    
    
}

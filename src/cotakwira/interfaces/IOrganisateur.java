/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cotakwira.interfaces;

import java.util.List;

/**
 *
 * @author esprit
 */
public interface IOrganisateur <T>{
     public void ajouterOrganisateur(T t);

   // public void supprimerMatchTermine(T t);

    public void updateOrganisateur(T t);

    public List<T> displayOrganisateur();
}

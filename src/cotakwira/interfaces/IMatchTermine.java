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
public interface IMatchTermine <T> {
     public void ajouterMatchTermine(T t);

   // public void supprimerMatchTermine(T t);

    public void updateMatchTermine(T t);

    public List<T> displayMatchs();
}

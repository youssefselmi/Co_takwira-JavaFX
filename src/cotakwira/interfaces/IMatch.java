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
public interface IMatch<T> {
    public void ajouterMatch(T t);

    public void supprimerMatch(T t);

    public void updateMatch(T t);

   // public List<T> displayMatchs();
}

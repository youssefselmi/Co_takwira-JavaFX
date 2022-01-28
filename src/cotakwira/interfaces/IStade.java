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
public interface IStade<T> {

    public void ajouterStade(T t);

    public void supprimerStade(T t);

    public void updateStade(T t);

    public List<T> displayStades();
}

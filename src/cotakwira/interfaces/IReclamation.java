/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cotakwira.interfaces;

import cotakwira.entities.Reclamation;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author yassin
 * @param <T>
 */
public interface IReclamation<T> {

    public boolean ajouterReclamation(T t);

    public boolean supprimerReclamation(int idRec);

    public boolean updateReclamation(T t);

    public List<T> displayReclamation(T t);// bel id User
    
    public List<T> displayRecForAdmin();// afficher kolchay ala be3dho :p

    public void PrioriteRec();

    public int contraintModifier24h(int idRec);
    
    public boolean updateRecStatut(T t) ;
    
    public int ReclamationIsOpen(int idRec);

    public int VerifIsOpenn(int idRec);
}

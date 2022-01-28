/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cotakwira.interfaces;

import java.util.List;

/**
 *
 * @author yassin
 * @param <T>
 */
public interface IRate <T>{
    
    public void ajouterRate(T t);

    public List<T> displayRate(T t);
    
    
    
}

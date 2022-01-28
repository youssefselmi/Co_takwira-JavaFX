/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package takiwra.pidev.services;

/**
 *
 * @author esprit
 */
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Item {
    private final DoubleProperty quantity = new SimpleDoubleProperty();
public Item() {
    }
    
    public final double getQuantity() {
        return quantity.get();
    }
    public final DoubleProperty quantityProperty(){
      return quantity;   
    }
    public final void setQuantity(double q)
    {
        this.quantity.set(q);
    }
    
}

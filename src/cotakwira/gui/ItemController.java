/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cotakwira.gui;

import cotakwira.entities.Coach;
import cotakwira.interfaces.MyListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ItemController implements Initializable {

    @FXML
    private ImageView img;
    @FXML
    private Label namecaoch;
    @FXML
    private Label prenomcoach;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
  
    @FXML
    private void click(MouseEvent event) {
         myListener.onClickListener(coach);

    }

      private Coach coach;
    private MyListener myListener;
    
    public void setData(Coach coach, MyListener myListener) {
        try {
            this.coach = coach;
            this.myListener = myListener;
            namecaoch.setText(coach.getNom_coach());
            prenomcoach.setText(/*Main.CURRENCY + */coach.getPrenom_coach());
            Image image = new Image(new FileInputStream((coach.getImage_coach())));
            img.setImage(image);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ItemController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
        
    
}

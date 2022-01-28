/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cotakwira.gui;

import cotakwira.entities.Joueur;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author USER-PC
 */
public class DetailsjoueurController implements Initializable {

    @FXML
    private ImageView Image_joueur;
    @FXML
    private Label Nometprenom;
    @FXML
    private Label datenaissance;
    @FXML
    private Label numero;
    @FXML
    private Label position;
    @FXML
    private Label ville;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    public void initData(Joueur joueur)
    {
        System.out.println(joueur.toString());
        Nometprenom.setText(joueur.getNom_joueur()+" "+joueur.getPrenom_joueur());
                datenaissance.setText(joueur.getDateNaissance().toString());
                numero.setText(Integer.toString(joueur.getNumero()));
                                position.setText(joueur.getPosition());
                                ville.setText(joueur.getVille());



            Image image = null;
            try {
                image = new Image(new FileInputStream("C:/wamp64/www/images/" + joueur.getImage()));
            } catch (FileNotFoundException ex) {
              
            }

           Image_joueur.setImage(image);

        
    }
}

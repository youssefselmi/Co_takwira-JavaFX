/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cotakwira.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import cotakwira.entities.Article;
import cotakwira.entities.Commentaire;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javax.swing.JOptionPane;
import cotakwira.services.ArticleService;
import cotakwira.services.CommentaireService;

/**
 * FXML Controller class
 *
 * @author khali
 */
public class AddCommentaireController implements Initializable {

    @FXML
    private JFXTextArea ta_contenu;
    @FXML
    private JFXButton btn_ajouterC;
    @FXML
    private JFXTextField tfId;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void setId(int value) {
        this.tfId.setText(String.valueOf(value));
    }
    @FXML
    private void ajouterCommentaire(ActionEvent event) {
        try {
            //// SAVE ARTICLE IN DB
            String resContenu = ta_contenu.getText();
            if(resContenu.equals("")){
                Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Alerte");
            alert.setHeaderText("Alerte");
            alert.setContentText("Veuillez remplir les champs vides");
            alert.show();
            }
            else
            {
            
            Commentaire c = new Commentaire(20,resContenu ,Integer.parseInt(tfId.getText()),1);
            
            
            CommentaireService cs = new CommentaireService();
            cs.ajouterCommentaire(c);
            JOptionPane.showMessageDialog(null, "Commentaire ajouté");
            
            //REDIRECTION
            FXMLLoader loader = new FXMLLoader(getClass()
                    .getResource("DisplayArticle.fxml"));
            Parent root = loader.load();
            tfId.getScene().setRoot(root);}
        } catch (IOException ex) {
            Logger.getLogger(AddCommentaireController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void retourCommentaire(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass()
                    .getResource("DisplayArticle.fxml"));
            Parent root = loader.load(); 
           tfId.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(AddCommentaireController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

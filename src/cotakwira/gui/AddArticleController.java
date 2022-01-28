/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cotakwira.gui;

import cotakwira.entities.Article;
import java.io.IOException;
import java.net.URL;
import cotakwira.entities.sendSMS;
import com.jfoenix.controls.JFXButton;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javax.swing.JOptionPane;
import cotakwira.services.ArticleService;

/**
 * FXML Controller class
 *
 * @author khali
 */
public class AddArticleController implements Initializable {

    @FXML
    private TextField tf_type;
    @FXML
    private TextField tf_titre;
    @FXML
    private Button btn_ajouterA;
    @FXML
    private TextArea ta_description;

    /**
     * Initializes the controller class.
     */
    public static FileChooser fc = new FileChooser();
    File selectedfile;
    String path;
    @FXML
    private ImageView image_update;
    @FXML
    private JFXButton image;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO  
    }    
    
    @FXML
    private void ajouterArticle(ActionEvent event) {
       
  
        try {
            //// SAVE ARTICLE IN DB
            String resType = tf_type.getText();
            String resTitre = tf_titre.getText();
            String resDescription = ta_description.getText();

            if((resType.equals(""))||(resTitre.equals(""))||(resDescription.equals(""))){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Alerte");
            alert.setHeaderText("Alerte");
            alert.setContentText("Veuillez remplir les champs vides");
            alert.show();
        }
            else
            {
            
            Article a = new Article(20, resType, resTitre,resDescription,path,1);

            ArticleService pcd = new ArticleService();
            pcd.ajouterArticle(a);
            JOptionPane.showMessageDialog(null, "Article ajouté");
            sendSMS sm = new sendSMS();
            sm.sendSMS(a);
            
            //REDIRECTION
            FXMLLoader loader = new FXMLLoader(getClass()
                    .getResource("DisplayArticle.fxml"));
            Parent root = loader.load(); 
            tf_type.getScene().setRoot(root);}
        } catch (IOException ex) {
            Logger.getLogger(AddArticleController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
            
         
        
        
    }

    @FXML
    private void retourArticle(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass()
                    .getResource("DisplayArticle.fxml"));
            Parent root = loader.load(); 
            tf_type.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(AddArticleController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void importerImageUpdate(ActionEvent event) throws MalformedURLException, FileNotFoundException {

        fc.setInitialDirectory(new File(System.getProperty("user.home") + "\\Desktop"));
        //System.out.println(System.getProperty("user.home"));
        fc.setTitle("Veuillez choisir l'image");
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image", "*.jpg", "*.png"),
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg")
        );
        selectedfile = fc.showOpenDialog(null);
        if (selectedfile != null) {
            
            path = selectedfile.getAbsolutePath();
//                path = selectedFile.toURI().toURL().toExternalForm();
           Image img=new Image(selectedfile.toURI().toString());
//           img_pub=new ImageView(img);
           image_update.setImage(img);
           image_update.setFitHeight(150);
           image_update.setFitWidth(250);
           image.setText(path);

        }
    }
    
}

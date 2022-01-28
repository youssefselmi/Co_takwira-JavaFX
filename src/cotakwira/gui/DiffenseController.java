/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cotakwira.gui;

import com.jfoenix.controls.JFXButton;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class DiffenseController implements Initializable {

     /**
     * Initializes the controller class.
     */
    @FXML
    private MediaView mediaViewDiff;
    private MediaPlayer mediaPlayer;
    private Media media;
    @FXML
    private JFXButton golisteee;
    
    //private MediaView mediaViewDiff;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        String path = new File("D:\\3éme\\2émesemestre\\Pi\\Desktop\\CoTakwira\\src\\resources\\media\\Videos\\SeanceDiffense_2.mp4").getAbsolutePath();
        media = new Media(new File(path).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaViewDiff.setMediaPlayer(mediaPlayer);
        mediaPlayer.setAutoPlay(true);
           
        // TODO
    }    

    @FXML
    private void reoure(ActionEvent event) throws IOException {
        
         Parent home_page_parent = FXMLLoader.load(getClass().getResource("afficheentrainement.fxml"));
        Scene home_page_scene = new Scene (home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);
        app_stage.show();
        
    }

   

   
    
}

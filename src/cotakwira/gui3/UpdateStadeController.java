/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cotakwira.gui3;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import cotakwira.services.MatchCrud;
import cotakwira.services.StadeCrud;
import cotakwira.services.mail;
import cotakwira.entities.Match;
import cotakwira.entities.Stade;

/**
 * FXML Controller class
 *
 * @author esprit
 */
public class UpdateStadeController implements Initializable {

    int id_m;
    @FXML
    private JFXButton BtnMatchs;
    @FXML
    private JFXButton BtnStade;
    @FXML
    private JFXButton BtnStat;
    @FXML
    private JFXButton BtnVote;
    @FXML
    private JFXButton actualiser;
    @FXML
    private Label LabelD;
    @FXML
    private Label LabelD2;
    @FXML
    private JFXTextField t_nom;
    @FXML
    private Label lab_nom;
    @FXML
    private JFXTextField t_ville;
    @FXML
    private Label lab_ville;
    @FXML
    private JFXTextField t_adresse;
    @FXML
    private Label lab_adresse;
    @FXML
    private JFXTextField t_capacite;
    @FXML
    private Label lab_capa;
    @FXML
    private JFXButton BtnModifier;
    @FXML
    private JFXComboBox<Integer> id_stade;

    /**
     * Initializes the controller class.
     */
    int id_org=1;
    double xOffset, yOffset;
    void setTextField(int id) {

        id_m = id;

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void ActualiserInformations(ActionEvent event) {

        id_stade.setPromptText(String.valueOf(id_m));

        int idd = id_m;

        System.out.println(idd);
        Stade sA = new Stade();
        StadeCrud sC = new StadeCrud();
        sA = sC.getStadeParId(idd);

        t_adresse.setPromptText(sA.getAdresse());
        t_capacite.setPromptText(String.valueOf(sA.getCapacite()));
        t_nom.setPromptText(sA.getNom_stade());
        t_ville.setPromptText(sA.getVille());
        
        

    }

    @FXML
    private void ModifierStade(ActionEvent event) {
        
     /*   StadeCrud sc = new StadeCrud();
      //  Stade stade = new Stade();
        
        
        String adress,nom,ville;
        int capa ;
        
        adress = t_adresse.getText();
        nom = t_nom.getText();
        ville = t_ville.getText();
        capa =Integer.parseInt(t_capacite.getText());
        
         Stade stade = new Stade(id_org,id_m,nom,ville,adress,capa);
         sc.updateStade(stade);*/
       int test =0;
        
     
       
       if(t_adresse.getText().trim().length()==0)
       {
           test=0;
           lab_adresse.setText("adresse  vide");
           lab_adresse.setStyle("-fx-text-fill:red;");
       }
       else{
           test =1;
       }
        if(t_nom.getText().trim().length()==0)
       {test=0;
           lab_nom.setText("nom  vide");
           lab_nom.setStyle("-fx-text-fill:red;");
       }
        else{
            test =1;
        }
         if(t_ville.getText().trim().length()==0)
       {test =0;
           lab_ville.setText("ville  vide");
           lab_ville.setStyle("-fx-text-fill:red;");
       }
         else{
             test =1;
         }
        if(t_capacite.getText().trim().length()==0)
        {
            test =0;
            lab_capa.setText(" capacite vide");
            lab_capa.setStyle("-fx-text-fill:red;");
        }
        else{
            test =1;
        }
         boolean isNumeric =  t_capacite.getText().matches("[+-]?\\d*(\\.\\d+)?");
         
         if(isNumeric == false )
         {test =0;
             lab_capa.setText(" input non valide");
            lab_capa.setStyle("-fx-text-fill:red;");
         }
         else{
             lab_capa.setText(" input valide");
            lab_capa.setStyle("-fx-text-fill:green;");
             test=1;
         }
       StadeCrud sc = new StadeCrud();
       mail m = new mail();
        if(test==1){
       String adrr= t_adresse.getText();
       String nom= t_nom.getText();
       String ville= t_ville.getText();
       Integer cap= Integer.parseInt(t_capacite.getText());
      Stade stade = new Stade(id_org,id_m,nom,ville,adrr,cap);
         sc.updateStade(stade);
       m.notificationS(" Modifier Stade","Stade Modifié avec succé");
       }
       else{
             m.notificationF("Modifier Stade","Erreur !!Stade non Modifié");
       }
        
    }


    @FXML
    private void OnClickBtnConsulterMatchs(ActionEvent event) {
    }

    @FXML
    private void OnClickBtnConsulterStade(ActionEvent event) {
    }

    @FXML
    private void OnClickBtnStatMatch(ActionEvent event) {
    }

    @FXML
    private void OnClickBtnVote(ActionEvent event) {
    }

    @FXML
    private void deconnexion(ActionEvent event) {
        
         try {
            Parent root = FXMLLoader.load(getClass().getResource("/cotakwira/gui/Login.fxml"));
            Scene newScene = new Scene(root);
            root.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    xOffset = event.getSceneX();
                    yOffset = event.getSceneY();
                }
            });

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
             
            root.setOnMouseDragged(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    window.setX(event.getScreenX() - xOffset);
                    window.setY(event.getScreenY() - yOffset);
                }
            });
           
            window.setScene(newScene);
           // window.initStyle(StageStyle.TRANSPARENT);
            window.show();
        } catch (IOException ex) {
            ex.getMessage();
        }
    }

    @FXML
    private void exit(ActionEvent event) {
         System.exit(0);
    }

    @FXML
    private void back(ActionEvent event) {
        
              try {
            Parent root = FXMLLoader.load(getClass().getResource("/cotakwira/gui3/AfficherLStade.fxml"));
            Scene newScene = new Scene(root);
            root.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    xOffset = event.getSceneX();
                    yOffset = event.getSceneY();
                }
            });

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            root.setOnMouseDragged(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    window.setX(event.getScreenX() - xOffset);
                    window.setY(event.getScreenY() - yOffset);
                }
            });
            //Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(newScene);
            window.show();
        } catch (IOException ex) {
            ex.getMessage();
        }
    }

}

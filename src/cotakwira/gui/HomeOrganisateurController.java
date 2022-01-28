/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cotakwira.gui;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import cotakwira.tools.Connection;

/**
 * FXML Controller class
 *
 * @author esprit
 */
public class HomeOrganisateurController implements Initializable {

    @FXML
    private Pane Matchs;
    @FXML
    private Label nbM;
    @FXML
    private Pane Stades;
    @FXML
    private Label nbS;
    @FXML
    private Pane Mt;
    @FXML
    private Label nbT;
    @FXML
    private Pane stat;
    @FXML
    private Pane Notif;
    @FXML
    private Pane compte;
    @FXML
    private Label user;
    @FXML
    private JFXButton deconnexion;
    int id_org = LoginController.idLogin;
    double xOffset, yOffset;
    @FXML
    private JFXButton MesMatchs;
    @FXML
    private JFXButton BtnMatchs;
    @FXML
    private JFXButton BtnStade;
    @FXML
    private JFXButton BtnStat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            String A;
            String r = "select * from `user` Where id = " + id_org;

            Statement st2;

            List<String> t = new ArrayList<>();

            st2 = Connection.getInstance().getCnx().createStatement();
            ResultSet rs = st2.executeQuery(r);
            if (rs.next()) {

                A = rs.getString("nom");

                user.setText(A);

            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }

    private void OnMouseMatchC(MouseEvent event) {

    }

    @FXML
    private void deconnexion(ActionEvent event) {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
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
    private void ConsulterMatchs(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/cotakwira/gui3/AfficherMatchLP.fxml"));
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

    @FXML
    private void ConsulterStades(ActionEvent event) {
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

    @FXML
    private void ConsulterMatchsTermine(ActionEvent event) {
        
          try {
            Parent root = FXMLLoader.load(getClass().getResource("/cotakwira/gui3/AfficherMatchLT.fxml"));
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

    @FXML
    private void ConsulterStat(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/cotakwira/gui3/statMatch.fxml"));
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

    @FXML
    private void exit(ActionEvent event) {
        
         System.exit(0);
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

}

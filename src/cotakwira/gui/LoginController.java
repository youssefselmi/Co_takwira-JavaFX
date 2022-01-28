/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cotakwira.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import cotakwira.services.LoginCrud;

/**
 *
 * @author esprit
 */
public class LoginController {

    @FXML
    private JFXTextField inpName;
    @FXML
    private JFXPasswordField inpPassword;
    @FXML
    private JFXButton btnSignIn;
    @FXML
    private Hyperlink nouv_compte;

    public static String usernameautho;
    public static int idLogin;
    public static String role;
    @FXML
    private Label alert;
    @FXML
    private JFXTextField inpEmail;
     double xOffset, yOffset;
     

    @FXML
    private void handleSignIn(ActionEvent event) throws IOException {

        if (inpEmail.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Entrez votre nom d'utilisateur", "Nom d'utilisateur vide", 2);
        } else if (inpPassword.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Entrez votre mot de passe", "Empty Password", 2);

        } else {

            try {
                usernameautho = inpName.getText();

                LoginCrud as = new LoginCrud();
                //String role;

                role = as.verifierUser(inpEmail.getText(), inpPassword.getText());

                idLogin = as.finId(inpEmail.getText(), inpPassword.getText());
                System.out.println("id" + idLogin);
                System.out.println(role);
                if (role.equals("organisateur")) {
                    System.out.println("hello");
                  //  Parent root = FXMLLoader.load(getClass().getResource("/takwira/pidev/gui3/AfficherMatchLP.fxml"));
                  Parent root = FXMLLoader.load(getClass().getResource("HomeOrganisateur.fxml"));
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
                } else if (role.equals("user")) {

                    Parent root = FXMLLoader.load(getClass().getResource("HomeUser.fxml"));
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
                    window.show();
                } else if (role.equals("false")) {

                    alert.setText("login ou mdp incorrect!!");

                }
            } catch (SQLException ex) {

                ex.getMessage();
            }
        }

    }

    @FXML
    private void exit(ActionEvent event) {
        System.exit(0);
    }

}

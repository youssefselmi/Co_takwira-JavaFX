/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cotakwira.gui3;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import cotakwira.services.MatchCrud;
import cotakwira.services.StadeCrud;
import cotakwira.services.mail;
import cotakwira.entities.Match;
import cotakwira.entities.Stade;
import cotakwira.gui.LoginController;
import cotakwira.tools.Connection;

/**
 * FXML Controller class
 *
 * @author esprit
 */
public class AjouterMatchPController implements Initializable {

    @FXML
    private Label lbl_prog;
    @FXML
    private Label lbl_enCours;
    @FXML
    private Label lbl_termine;
    @FXML
    private Label LabelE1;
    @FXML
    private JFXComboBox<Integer> TMEquipe1;
    @FXML
    private JFXComboBox<Integer> TMEquipe2;
    @FXML
    private Label LabelE2;
    @FXML
    private JFXComboBox<Integer> TMStade;
    @FXML
    private Label LabelS;
    @FXML
    private Label LabelS1;
    @FXML
    private JFXTextField TMNb;
    @FXML
    private Label LabelSpect;
    @FXML
    private DatePicker tMDate;
    @FXML
    private Label LabelD;
    @FXML
    private Label LabelD2;
    @FXML
    private JFXComboBox<String> tmHRF;
    @FXML
    private JFXComboBox<String> tmMinF;
    @FXML
    private JFXComboBox<String> tmSF;
    @FXML
    private JFXComboBox<String> tmHRD1;
    @FXML
    private JFXComboBox<String> tmMinD;
    @FXML
    private JFXComboBox<String> tmSD;
    @FXML
    private JFXTextArea tm_description;
    @FXML
    private JFXComboBox<String> tm_statut;
    @FXML
    private JFXButton BtnMatchs;
    @FXML
    private JFXButton actualiser;
    @FXML
    private JFXButton BtnAjouter;
    @FXML
    private JFXButton BtnStade;
    @FXML
    private JFXButton BtnStat;
    @FXML
    private Label lab_desc;
    @FXML
    private Label D;

    /**
     * Initializes the controller class.
     */
    int id_org = LoginController.idLogin;
    @FXML
    private StackPane rootM;
 double xOffset, yOffset;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        int max, m;
        String chaine, ch;

        try {
            /**
             * *************** remplir equipes ********************
             */

            String requete = "select id_e from `equipe` ";

            Statement st = Connection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);

            while (rs.next()) {
                chaine = String.valueOf(rs.getString(1));
                max = Integer.parseInt(chaine);

                TMEquipe1.getItems().add(max);
                TMEquipe2.getItems().add(max);

            }
            /**
             * *************** remplir stade ********************
             */
            Statement state = Connection.getInstance().getCnx()
                    .createStatement();

            String req = "select id_stade from `stade` ";
            ResultSet r = state.executeQuery(req);
            while (r.next()) {
                ch = String.valueOf(r.getString(1));
                m = Integer.parseInt(ch);
                TMStade.getItems().add(m);
            }

            tmHRD1.getItems().addAll("00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23");
            tmHRF.getItems().addAll("00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23");

            tmMinD.getItems().addAll("00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59");
            tmMinF.getItems().addAll("00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59");
            tmSD.getItems().addAll("00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59");
            tmSF.getItems().addAll("00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59");

            /**
             * ************************ Status du match ********************
             */
            tm_statut.getItems().addAll("En Programme", "En cours", "Termine");

        } catch (SQLException ex) {
            ex.getMessage();
        }

    }

    @FXML
    private void handleButtonAction(MouseEvent event) {
    }

    @FXML
    private void AjouterMatchP(ActionEvent event) {

        boolean test = true;
        boolean testM =true;

        MatchCrud pcd = new MatchCrud();
        int nb = 0;
        int id;
        int eq1, eq2, stade;
        int t;
        StadeCrud sc = new StadeCrud();
        Stade s = new Stade();
        //System.out.println("*****"+TMEquipe1.getValue());
        if (TMEquipe1.getValue() == null) {
            test = false;
            LabelE1.setText("Champs  vide");
            LabelE1.setStyle("-fx-text-fill:red;");

        } else {
            test = true;
        }
        if (TMEquipe2.getValue() == null) {
            test = false;
            LabelE2.setText("Champs vide");
            LabelE2.setStyle("-fx-text-fill:red;");

        } else {
            test = true;
        }
        if (test == true) {
            eq2 = TMEquipe2.getValue();
            eq1 = TMEquipe1.getValue();
        } else {
            eq1 = 0;
            eq2 = 0;
        }
        if (eq1 != 0) {
            LabelE1.setText("Equipe 1 valide");
            LabelE1.setStyle("-fx-text-fill:green;");
        }
        if (eq1 == eq2) {
            TMEquipe2.setPromptText("Equipe2");
            LabelE2.setText("Equipe existe déja");
            testM = false;

        } else {
            LabelE2.setText("Equipe 2 valide");
            LabelE2.setStyle("-fx-text-fill:green;");
        }

        stade = TMStade.getValue();
        s = sc.getStadeParId(stade);

        if (TMNb.getText().trim().length() > 0) {
            LabelSpect.setText("Champs valide");
            LabelSpect.setStyle("-fx-text-fill:green;");
            test = true;
            nb = Integer.parseInt(TMNb.getText());

        } else {
            LabelSpect.setText("Champs vide");
            test = false;
        }
        if (nb < 0 || nb > s.getCapacite()) {
            test = false;
            LabelSpect.setText("Nombre invalide");
            LabelSpect.setStyle("-fx-text-fill: #FF0000;");
        } else {
            LabelSpect.setText("Nombre de spectateur valide");
            LabelSpect.setStyle("-fx-text-fill:green;");
        }

        LocalDate date = tMDate.getValue();
        ZoneId defaultZoneId = ZoneId.systemDefault();
        Date d = Date.from(date.atStartOfDay(defaultZoneId).toInstant());

        if (date != null) {
            LabelD.setText(date.toString());

            t = pcd.ComparerDate(d);
            System.out.println(t);
            if (t < 0) {
                LabelD2.setText("Date non valide");
                test = false;
            } else {
                LabelD.setStyle("-fx-text-fill:green;");
                LabelD2.setText("Date valide");
                LabelD2.setStyle("-fx-text-fill:green;");
            }

        } else {
            LabelD.setText("veuillez saisir une date");
        }

        String heure_deb = tmHRD1.getValue() + ":" + tmMinD.getValue() + ":" + tmSD.getValue();
        String heure_fin = tmHRF.getValue() + ":" + tmMinF.getValue() + ":" + tmSF.getValue();;
        String desc = tm_description.getText();
        String status = tm_statut.getValue();
        if ((Integer.parseInt(tmHRD1.getValue()) < Integer.parseInt(tmHRF.getValue()))) {
            test = true;
            tmHRF.setStyle("-fx-text-fill:green;");
        } else {
            if ((Integer.parseInt(tmHRD1.getValue()) == Integer.parseInt(tmHRF.getValue())) && (Integer.parseInt(tmMinD.getValue()) < (Integer.parseInt(tmMinF.getValue())))) {
                test = true;
                D.setStyle("-fx-text-fill:green;");
                D.setText("Heure Fin valide");
                D.setStyle("-fx-text-fill:green;");
                /* tmMinF.setStyle("-fx-text-fill:green;");
                  tmSF.setStyle("-fx-text-fill:green;");*/
            } else {
                test = false;
                D.setStyle("-fx-text-fill:red;");
                D.setText("Heure Fin non valide");

            }
        }

        if (TMStade.getValue() == null) {
            test = false;
        }
        if (desc.trim().length() > 0) {
            lab_desc.setText("champs valide");
            lab_desc.setStyle("-fx-text-fill:green;");
        } else {
            lab_desc.setText("champs vide");
            lab_desc.setStyle("-fx-text-fill: #FF0000;");
            test = false;
        }

        mail n = new mail();
        if ((test == true)&&(testM == true)) {

            Match m = new Match(id_org, stade, eq1, eq2, d, heure_deb, heure_fin, nb, desc, status, "0-0");

            pcd.ajouterMatch(m);

            n.notificationS("Ajouter un nouveau  Match", "Match ajouté avec succés");
            n.ShowDialog(rootM);
        } else {
            n.notificationF("Ajouter un nouveau Match", " Error !!!  Match non ajouté");
            System.out.println("champs videss");
        }

    }

    @FXML
    private void OnClickBtnConsulterMatchs(ActionEvent event) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherMatchLP.fxml"));
            Parent root;

            root = loader.load();
            Scene newScene = new Scene(root);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(newScene);
            window.show();
        } catch (IOException ex) {
            ex.getMessage();

        }
    }

    @FXML
    private void ActualiserInformations(ActionEvent event) {
    }

    @FXML
    private void OnClickBtnConsulterStade(ActionEvent event) {
    }

    @FXML
    private void OnClickBtnStatMatch(ActionEvent event) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("statMatch.fxml"));
            Parent root;

            root = loader.load();
            Scene newScene = new Scene(root);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
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
    private void back(ActionEvent event) {
        
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

}

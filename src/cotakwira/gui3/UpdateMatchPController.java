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
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import cotakwira.services.MatchCrud;
import cotakwira.services.StadeCrud;
import cotakwira.services.mail;
import cotakwira.entities.Match;
import cotakwira.entities.Stade;
import cotakwira.tools.Connection;

/**
 * FXML Controller class
 *
 * @author esprit
 */
public class UpdateMatchPController implements Initializable {

    int id_org = 1;
    private Button btnOrganiser;
    private Button btnConsulter;
    private Button btnModifier;
    private Button btnSupprimer;
    private JFXComboBox<Integer> IDMatch = new JFXComboBox<>();
    @FXML
    private JFXTextField TMNb;
    @FXML
    private JFXComboBox<Integer> TMEquipe1 = new JFXComboBox();
    @FXML
    private JFXComboBox<Integer> TMEquipe2 = new JFXComboBox();
    @FXML
    private JFXComboBox<Integer> TMStade = new JFXComboBox();
    @FXML
    private DatePicker tMDate;
    private JFXTextField TMScore;
    private JFXTextField TMD;
    @FXML
    private Label LabelS;
    @FXML
    private JFXButton actualiser;
    @FXML
    private Label LabelE1;
    @FXML
    private Label LabelE2;
    @FXML
    private Label LabelS1;
    @FXML
    private Label LabelSpect;
    @FXML
    private Label LabelD;
    @FXML
    private Label LabelD2;

    @FXML
    private JFXTextField ref;

    /**
     * Initializes the controller class.
     */
    int id_m;
    @FXML
    private Label lbl_prog;
    @FXML
    private Label lbl_enCours;
    @FXML
    private Label lbl_termine;
    @FXML
    private JFXComboBox<String> tmHRF = new JFXComboBox();
    @FXML
    private JFXComboBox<String> tmMinF = new JFXComboBox();
    ;
    @FXML
    private JFXComboBox<String> tmSF = new JFXComboBox();
    ;
    @FXML
    private JFXComboBox<String> tmHRD1 = new JFXComboBox();
    ;
    @FXML
    private JFXComboBox<String> tmMinD = new JFXComboBox();
    ;
    @FXML
    private JFXComboBox<String> tmSD = new JFXComboBox();
    ;
    @FXML
    private JFXTextArea tm_description;
    @FXML
    private JFXComboBox<String> tm_statut;
    @FXML
    private JFXButton BtnM;
    @FXML
    private JFXButton BtnMatchs;
    @FXML
    private JFXButton BtnStade;
    @FXML
    private JFXButton BtnStat;
    @FXML
    private Label D;
 double xOffset, yOffset;
    void setTextField(int id) {

        id_m = id;

    }

    int getTextField() {
        return id_m;
    }

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

            Statement s = Connection.getInstance().getCnx()
                    .createStatement();

            String re = "select id_match from `match` ";
            ResultSet res = state.executeQuery(re);
            while (res.next()) {
                ch = String.valueOf(res.getString(1));
                m = Integer.parseInt(ch);
                IDMatch.getItems().add(m);
            }
            /**
             * ********************** heure début et fin *********************
             */
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
    private void ActualiserInformations(ActionEvent event) {
        int idd;
        boolean test = false;
        //  if (IDMatch.getValue() != null) {

        // idd = IDMatch.getValue();
        ref.setText(String.valueOf(id_m));

        idd = id_m;

        System.out.println(idd);
        Match mA = new Match();
        MatchCrud matchC = new MatchCrud();
        mA = matchC.getMatchParId(idd);
        /**
         * ****************************************************
         */
        TMEquipe1.setPromptText(String.valueOf(mA.getId_equipe1()));
        TMEquipe2.setPromptText(String.valueOf(mA.getId_equipe2()));
        TMStade.setPromptText(String.valueOf(mA.getId_stade()));
        tMDate.setPromptText(String.valueOf(mA.getDate()));
        TMNb.setPromptText(String.valueOf(mA.getNb_spectateur()));
        /**
         * ************ extraire l'heure , minute et seconde ************
         */

        // l'indice de heure , jour et minute
        tmHRD1.setPromptText(String.valueOf(mA.getHeure_deb().substring(0, 2)));
        tmMinD.setPromptText(String.valueOf(mA.getHeure_deb().substring(3, 5)));
        tmSD.setPromptText(String.valueOf(mA.getHeure_deb().substring(6, 8)));

        tmHRF.setPromptText(String.valueOf(mA.getHeure_fin().substring(0, 2)));
        tmMinF.setPromptText(String.valueOf(mA.getHeure_fin().substring(3, 5)));
        tmSF.setPromptText(String.valueOf(mA.getHeure_fin().substring(6, 8)));

        tm_statut.setPromptText(String.valueOf(mA.getStatus()));
        tm_description.setPromptText(String.valueOf(mA.getDescription()));

        /**
         * *********************************************************
         */

        /*  } else {
            JOptionPane.showMessageDialog(null, "Veuillez saisir un id_match");
        }*/
    }

    @FXML
    private void ModifierMatch(ActionEvent event) {
        boolean test = true;

        MatchCrud pcd = new MatchCrud();
        int duree;
        String score;
        int id;
        int nb = 0;
        int eq1, eq2, stade;
        int t;

        id = id_m;
        eq1 = TMEquipe1.getValue();
        eq2 = TMEquipe2.getValue();
        StadeCrud sc = new StadeCrud();
        Stade s = new Stade();
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
            System.out.println("11111111" + nb);
            test = false;
            LabelSpect.setText("Nombre invalide");
            LabelSpect.setStyle("-fx-text-fill: #FF0000;");
        } else {
            LabelSpect.setText("Nombre de spectateur valide");
            LabelSpect.setStyle("-fx-text-fill:green;");
        }

        if (eq1 != 0) {
            LabelE1.setText("Equipe 1 valide");
            LabelE1.setStyle("-fx-text-fill:green;");
        }
        if (eq1 == eq2) {
            TMEquipe2.setPromptText("Equipe2");
            LabelE2.setText("Equipe existe déja");
            test = false;

        } else {
            LabelE2.setText("Equipe 2 valide");
            LabelE2.setStyle("-fx-text-fill:green;");
        }
/********************************date ******************/
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
                test = true;
                LabelD.setStyle("-fx-text-fill:green;");
                LabelD2.setText("Date valide");
                LabelD2.setStyle("-fx-text-fill:green;");
            }

        } else {
            test = false;
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

            } else {
                test = false;
                D.setStyle("-fx-text-fill:red;");
                D.setText("Heure Fin non valide");

            }
        }

        Match m = new Match(id_org, id, stade, eq1, eq2, d, heure_deb, heure_fin, nb, desc, status);
        mail n = new mail();

        if (test == true) {
            pcd.updateMatch(m);

            n.notificationS("Modifier un  Match", "Match modifié avec succés");
        } else {
            n.notificationF("Modifier un Match", " Error !!!  Match non modifié");
            System.out.println("champs videss");
        }

    }

    private void OnBtnSupprimerClicked(ActionEvent event) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("supprimMatch.fxml"));
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

    private void OnBtnOrganiserMouseUE(MouseEvent event) {

        btnOrganiser.setStyle("-fx-background-color:#0598ff");

    }

    private void OnBtnOrganiserMouseU(MouseEvent event) {
        btnOrganiser.setStyle("-fx-background-color:green");
    }

    private void OnBtnConsulterMouseUE(MouseEvent event) {
        btnConsulter.setStyle("-fx-background-color:#0598ff");
    }

    private void OnBtnConsulterMouseU(MouseEvent event) {
        btnConsulter.setStyle("-fx-background-color:green");
    }

    private void OnBtnModifierMouseUE(MouseEvent event) {
        btnModifier.setStyle("-fx-background-color:#0598ff");
    }

    private void OnBtnModifierMouseU(MouseEvent event) {
        btnModifier.setStyle("-fx-background-color:green");
    }

    private void OnBtnSupprimerMouseE(MouseEvent event) {
        btnSupprimer.setStyle("-fx-background-color:#0598ff");
    }

    private void OnBtnSupprimerMouse(MouseEvent event) {
        btnSupprimer.setStyle("-fx-background-color:green");
    }

    @FXML
    private void handleButtonAction(MouseEvent event) {
    }

    private void OnBtnOrganiserClickedU(ActionEvent event) {

        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("ajoutMatch.fxml"));
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

    private void OnBtnConsulterClickedU(ActionEvent event) {

        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("afficherMatch.fxml"));
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

    private void OnBtnModifierClickedU(ActionEvent event) {

        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("modifMatch.fxml"));
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

}

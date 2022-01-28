/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cotakwira.gui3;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import static com.jfoenix.controls.JFXDialog.DialogTransition.CENTER;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
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
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import cotakwira.services.MatchCrud;
import cotakwira.services.mail;
import cotakwira.entities.Match;
import cotakwira.gui.LoginController;

/**
 * FXML Controller class
 *
 * @author esprit
 */
public class AfficherMatchLPController implements Initializable {

    @FXML
    private TableColumn<Match, Integer> Col_IDS;
    @FXML
    private TableColumn<Match, Integer> Col_IDE1;
    @FXML
    private TableColumn<Match, Integer> Col_IDE2;
    @FXML
    private TableColumn<Match, Date> Col_Date;
    @FXML
    private TableColumn<Match, Integer> Col_IDM;
    @FXML
    private TableColumn<Match, String> Col_desc;
    @FXML
    private TableView<Match> MatchTable;
    Date d = new Date();
    private ObservableList<Match> dataList = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Match, String> Col_Edit;
    int id;
    Match match = new Match();
    Match match2 = new Match();
    int test = 0;
    MatchCrud mc = new MatchCrud();
    @FXML
    private TableColumn<Match, String> infos;
    @FXML
    private TableColumn<Match, String> col_st;
    private JFXDialog dialog;
    @FXML
    private StackPane root;
    @FXML
    private TableColumn<Match, String> Col_alert;
    int id_org = LoginController.idLogin;
     double xOffset, yOffset;
    @FXML
    private JFXButton BtnMatchs;
    @FXML
    private JFXButton BtnStade;
    @FXML
    private JFXButton BtnStat;
    @FXML
    private JFXButton BtnVote;
    @FXML
    private JFXButton BtnAjouter;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // System.out.println("user"+ MainClass.login);
        // TODO
        Col_IDM.setCellValueFactory(new PropertyValueFactory<>("id_match"));
        Col_IDS.setCellValueFactory(new PropertyValueFactory<>("id_stade"));
        Col_IDE1.setCellValueFactory(new PropertyValueFactory<>("id_equipe1"));

        Col_IDE2.setCellValueFactory(new PropertyValueFactory<>("id_equipe2"));
        Col_Date.setCellValueFactory(new PropertyValueFactory<>("date"));
        Col_desc.setCellValueFactory(new PropertyValueFactory<>("description"));
        col_st.setCellValueFactory(new PropertyValueFactory<>("status"));

        Callback<TableColumn<Match, String>, TableCell<Match, String>> cellFoctory = (TableColumn<Match, String> param) -> {
            // make cell containing buttons
            final TableCell<Match, String> cell = new TableCell<Match, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                        FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);

                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#ff1744;"
                        );
                        editIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#00E676;"
                        );
                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {

                            id = MatchTable.getSelectionModel().getSelectedItem().getId_match();
                            // mc.supprimerMatch2(id);
                            mail m = new mail();
                            m.ConfirmerSupressionMatch(root, id);
                            refreshTable();

                        });
                        editIcon.setOnMouseClicked((MouseEvent event) -> {
                            try {
                                match = MatchTable.getSelectionModel().getSelectedItem();
                                FXMLLoader loader = new FXMLLoader();
                                loader.setLocation(getClass().getResource("UpdateMatchP.fxml"));

                                loader.load();

                                UpdateMatchPController uC = loader.getController();
                                uC.setTextField(match.getId_match());
                                Parent root;

                                root = loader.getRoot();
                                Scene newScene = new Scene(root);

                                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                window.setScene(newScene);
                                window.show();

                            } catch (IOException ex) {
                                Logger.getLogger(AfficherMatchLPController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        });

                        HBox managebtn = new HBox(editIcon, deleteIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                        HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));

                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };
        Callback<TableColumn<Match, String>, TableCell<Match, String>> cellFoctory1 = (TableColumn<Match, String> param) -> {
            // make cell containing buttons
            final TableCell<Match, String> cell = new TableCell<Match, String>() {

                @Override
                public void updateItem(String item, boolean empty) {

                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        FontAwesomeIconView MoreIcon = new FontAwesomeIconView(FontAwesomeIcon.EYE);

                        MoreIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:25px;"
                                + "-fx-fill:#ff1744;"
                        );

                        MoreIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#ff1744;"
                        );

                        MoreIcon.setOnMouseClicked((MouseEvent event) -> {

                            try {

                                match = MatchTable.getSelectionModel().getSelectedItem();

                                FXMLLoader loader = new FXMLLoader();

                                loader.setLocation(getClass().getResource("afficherMatchP.fxml"));
                                loader.load();
                                AfficherMatchPController iC = loader.getController();
                                iC.setTextField(match.getId_match());

                                System.out.println(match.getId_match());

                                Parent root;

                                root = loader.getRoot();
                                Scene newScene = new Scene(root);

                                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                window.setScene(newScene);
                                window.show();
                            } catch (IOException ex) {
                                ex.getMessage();
                            }

                        });

                        HBox managebtn = new HBox(MoreIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(MoreIcon, new Insets(2, 2, 0, 3));

                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };

        /**
         * *************************************
         */
        Callback<TableColumn<Match, String>, TableCell<Match, String>> cellFoctory2 = (TableColumn<Match, String> param) -> {
            // make cell containing buttons
            final TableCell<Match, String> cell = new TableCell<Match, String>() {

                @Override
                public void updateItem(String item, boolean empty) {

                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        FontAwesomeIconView alert = new FontAwesomeIconView(FontAwesomeIcon.BELL_ALT);
                        alert.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:20px;"
                                + "-fx-fill:#FFD700;"
                        );

                        alert.setOnMouseClicked((MouseEvent event) -> {
                            MatchCrud mc = new MatchCrud();
                            match = MatchTable.getSelectionModel().getSelectedItem();
                            int test = 0;
                            if (match.getStatus().equals("Termine") == true) {
                                //JFXTextField t1 = new JFXTextField();
                                //JFXTextField t2 = new JFXTextField();

                                JFXComboBox<String> t1 = new JFXComboBox();
                                JFXComboBox<String> t2 = new JFXComboBox();
                                t1.getItems().addAll("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
                                t2.getItems().addAll("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
                                Label lab_t1 = new Label();
                                Label lab_t2 = new Label();
                                HBox hl = new HBox(lab_t1, new Label("    "), new Label("    "), lab_t2);
                                hl.setAlignment(Pos.CENTER);
                                t1.setStyle(
                                        "-fx-background-color: Transparent;"
                                        + "-fx-font-weight:BOLD;"
                                        + "-fx-prompt-text:#808080;"
                                        + "-fx-alignment: center;"
                                        + "-fx-pref-width: 70;"
                                );
                                t2.setStyle(
                                        "-fx-background-color: Transparent;"
                                        + "-fx-font-weight:BOLD;"
                                        + "-fx-prompt-text:#808080;"
                                        + "-fx-alignment: center;"
                                        + "-fx-pref-width: 70;"
                                );
                                Label lab = new Label("-");
                                lab.setStyle("-fx-text-fill:#02aab0  ;"
                                        + "-fx-font-size:22px;"
                                        + "-fx-effect: dropshadow(three-pass-box, derive(grey, 50%), 2, 0.2, 0, 0);"
                                );
                                JFXDialogLayout dialogContent = new JFXDialogLayout();

                                Label lab_s = new Label("Score");
                                lab_s.getStyleClass().add("Label");
                                lab_s.setStyle(
                                        "-fx-text-fill:#02aab0  ;"
                                        + "-fx-font-size:22px;"
                                        + "-fx-effect: dropshadow(three-pass-box, derive(grey, 50%), 2, 0.2, 0, 0);"
                                );

                                HBox h = new HBox(t1, new Label("   "), lab, new Label("   "), t2);
                                VBox hb = new VBox(lab_s, new Label("   "), h, hl);
                                Label lab_h = new Label("Match Terminé");
                                lab_h.setAlignment(Pos.CENTER);
                                Label lab_h2 = new Label("Ajouter ou modifier le score");

                                lab_h.setStyle(
                                        "-fx-text-fill:#3a1c9d ;"
                                        + "-fx-font-size:24px;"
                                        + "-fx-effect: dropshadow(three-pass-box, derive(grey, 50%), 2, 0.2, 0, 0);"
                                );

                                lab_h2.setStyle(
                                        "-fx-text-fill:#3a1c9d ;"
                                        + "-fx-font-size:24px;"
                                        + "-fx-effect: dropshadow(three-pass-box, derive(grey, 50%), 2, 0.2, 0, 0);"
                                );

                                VBox vb = new VBox(lab_h, lab_h2);
                                h.setAlignment(Pos.CENTER);
                                vb.setAlignment(Pos.CENTER);
                                hb.setAlignment(Pos.CENTER);
                                dialogContent.setHeading(vb);
                                dialogContent.setBody(hb);
                                dialogContent.getStyleClass().add("jfx-dialog-overlay-pane");
                                JFXButton close = new JFXButton("Annuler");
                                JFXButton modifier = new JFXButton("Modifier");

                                close.getStyleClass().add("JFXButton");
                                close.setStyle(
                                        "-fx-text-fill:#3a1c9d ;"
                                        + "-fx-font-size:18px;"
                                        + "-fx-effect: dropshadow(three-pass-box, derive(grey, 50%), 2, 0.2, 0, 0);"
                                        + "-fx-background-radius: 40;-fx-background-color:#02aab0  "
                                );

                                modifier.getStyleClass().add("JFXButton");
                                modifier.setStyle(
                                        "-fx-text-fill:#3a1c9d ;"
                                        + "-fx-font-size:18px;"
                                        + "-fx-effect: dropshadow(three-pass-box, derive(grey, 50%), 2, 0.2, 0, 0);"
                                        + "-fx-background-radius: 40;-fx-background-color:#02aab0  "
                                );

                                close.setAlignment(Pos.CENTER);
                                modifier.setAlignment(Pos.CENTER);
                                dialogContent.setActions(close, new Label("   "), modifier);

                                JFXDialog dialog = new JFXDialog(root, dialogContent, JFXDialog.DialogTransition.CENTER);
                                close.setOnAction(new EventHandler<ActionEvent>() {

                                    @Override
                                    public void handle(ActionEvent __) {
                                        dialog.close();
                                    }
                                });
                                modifier.setOnAction(new EventHandler<ActionEvent>() {
                                    @Override
                                    public void handle(ActionEvent event) {
                                        int idd = match.getId_match();
                                        int test = 0;
                                        String s1, s2;
                                        s1 = t1.getValue();
                                        s2 = t2.getValue();

                                        String score = s1 + "-" + s2;
                                        System.out.println(score);
                                        mc.updateScore(score, idd);
                                        dialog.close();
                                    }

                                });
                                dialog.show();
                            }

                        });

                        HBox managebtn = new HBox(alert);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(alert, new Insets(2, 3, 0, 2));
                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };
        /**
         * *************************************
         */
        Col_Edit.setCellFactory(cellFoctory);
        infos.setCellFactory(cellFoctory1);
        Col_alert.setCellFactory(cellFoctory2);

        try {
            MatchCrud mc = new MatchCrud();
            // System.out.println("idorg"+id_org);
            dataList = mc.getMatchs(id_org);
            MatchTable.getItems().addAll(dataList);
        } catch (ParseException ex) {

            ex.getMessage();
        }

    }

    public void refreshTable() {

        /*  dataList.clear();
        try {
            dataList = mc.getMatchs(id_org);
            //MatchTable.getItems().addAll(dataList);
        } catch (ParseException ex) {
            Logger.getLogger(AfficherMatchLPController.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }


    @FXML
    private void OnClickBtnConsulterMatchs(ActionEvent event) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherMatchLP.fxml"));
            Parent root;

            root = loader.load();
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
        } catch (IOException ex) {
            ex.getMessage();

        }
    }

    @FXML
    private void OnClickBtnAjouter(ActionEvent event) {

        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterMatchP.fxml"));
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
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherLStade.fxml"));
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
    private void OnClickBtnVote(ActionEvent event) {

        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("voteMatch.fxml"));
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
            Parent root = FXMLLoader.load(getClass().getResource("/cotakwira/gui/HomeOrganisateur.fxml"));
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

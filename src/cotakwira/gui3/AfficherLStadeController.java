/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cotakwira.gui3;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import cotakwira.services.StadeCrud;
import cotakwira.services.mail;
import cotakwira.entities.Match;
import cotakwira.entities.Stade;
import cotakwira.gui.LoginController;

/**
 * FXML Controller class
 *
 * @author esprit
 */
public class AfficherLStadeController implements Initializable {

    @FXML
    private JFXButton BtnMatchs;
    @FXML
    private JFXButton BtnStade;
    @FXML
    private JFXButton BtnStat;
    @FXML
    private JFXButton BtnVote;
    @FXML
    private TableView<Stade> StadeTable;
    @FXML
    private TableColumn<Stade, Integer> id_stade;
    @FXML
    private TableColumn<Stade, String> nom_stade;
    @FXML
    private TableColumn<Stade, String> adresse;
    @FXML
    private TableColumn<Stade, String> ville;
    @FXML
    private TableColumn<Stade, Integer> capacite;
    @FXML
    private TableColumn<Stade, String> edit;

    /**
     * Initializes the controller class.
     *
     */
    private ObservableList<Stade> dataList = FXCollections.observableArrayList();
    @FXML
    private JFXTextField search;
    int id;
    Stade s = new Stade();
    StadeCrud sc = new StadeCrud();
    @FXML
    private JFXButton BtnAjouter;
    @FXML
    private StackPane root;

    int id_org = LoginController.idLogin;
     double xOffset, yOffset;
//d_org = LoginController.idLogin;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        id_stade.setCellValueFactory(new PropertyValueFactory<>("id_stade"));
        nom_stade.setCellValueFactory(new PropertyValueFactory<>("nom_stade"));
        adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));

        ville.setCellValueFactory(new PropertyValueFactory<>("ville"));
        capacite.setCellValueFactory(new PropertyValueFactory<>("capacite"));

        Callback<TableColumn<Stade, String>, TableCell<Stade, String>> cellFoctory = (TableColumn<Stade, String> param) -> {
            // make cell containing buttons
            final TableCell<Stade, String> cell = new TableCell<Stade, String>() {
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
                            /*  Alert alert = new Alert(AlertType.CONFIRMATION);
                            alert.setTitle("Supprimer");
                            alert.setHeaderText(null);
                            alert.setContentText("Voulez vous supprimer");

                            Optional<ButtonType> action = alert.showAndWait();
                         
                            if (action.get() == ButtonType.OK) {
                                id = StadeTable.getSelectionModel().getSelectedItem().getId_stade();
                               // sc.supprimerStade2(id);
                              
                             */

                            //}
                            id = StadeTable.getSelectionModel().getSelectedItem().getId_stade();
                            mail m = new mail();
                            m.ConfirmerSupressionStade(root, id);
                            refreshTable();

                        });
                        editIcon.setOnMouseClicked((MouseEvent event) -> {
                            try {
                                s = StadeTable.getSelectionModel().getSelectedItem();
                                FXMLLoader loader = new FXMLLoader();
                                loader.setLocation(getClass().getResource("UpdateStade.fxml"));

                                loader.load();

                                UpdateStadeController uC = loader.getController();
                                uC.setTextField(s.getId_stade());
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
        edit.setCellFactory(cellFoctory);

        // StadeTable.getItems().addAll(dataList);
        StadeCrud mc = new StadeCrud();
        dataList = mc.getStades(id_org);
        StadeTable.getItems().addAll(dataList);
        /**
         * **********************************************************************************
         */
        FilteredList<Stade> filteredData = new FilteredList<>(dataList, b -> true);

        search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(stade -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (stade.getNom_stade().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // filtre sur les noms des stades
                } else if (stade.getVille().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // filtre sur les noms des villes
                } else if (String.valueOf(stade.getAdresse()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false; // pas de recherche
                }
            });
        });

        SortedList<Stade> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(StadeTable.comparatorProperty());

        StadeTable.setItems(sortedData);

    }

    public void refreshTable() {

        StadeTable.refresh();

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
    private void OnClickBtnAjouterStade(ActionEvent event) {

        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterStade.fxml"));
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
    private void exit(ActionEvent event) {
         System.exit(0);
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cotakwira.gui3;

import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.Date;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import cotakwira.services.MatchCrud;
import cotakwira.entities.Match;
import cotakwira.gui.LoginController;

/**
 * FXML Controller class
 *
 * @author esprit
 */
public class AfficherMatchLTController implements Initializable {

    @FXML
    private JFXButton BtnMatchs;
    @FXML
    private JFXButton BtnStade;
    @FXML
    private JFXButton BtnStat;
    double xOffset, yOffset;
  String role=  LoginController.role;
    @FXML
    private TableView<Match> MatchTable;
    @FXML
    private TableColumn<Match, Integer> Col_IDM;
    @FXML
    private TableColumn<Match, Integer> Col_IDE1;
    @FXML
    private TableColumn<Match, Integer> Col_IDE2;
    @FXML
    private TableColumn<Match, Integer> Col_IDS;
    @FXML
    private TableColumn<Match, Date> Col_Date;
    @FXML
    private TableColumn<Match, String> Col_desc;
    @FXML
    private TableColumn<Match, String> col_st;
    @FXML
    private TableColumn<Match, String> infos;
    @FXML
    private TableColumn<?, ?> Col_alert;
    @FXML
    private TableColumn<Match, Integer> col_user;
Match match = new Match();
    /**
     * Initializes the controller class.
     */
     private ObservableList<Match> dataList = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Col_IDM.setCellValueFactory(new PropertyValueFactory<>("id_match"));
            Col_IDS.setCellValueFactory(new PropertyValueFactory<>("id_stade"));
            Col_IDE1.setCellValueFactory(new PropertyValueFactory<>("id_equipe1"));
            
            Col_IDE2.setCellValueFactory(new PropertyValueFactory<>("id_equipe2"));
            Col_Date.setCellValueFactory(new PropertyValueFactory<>("date"));
            Col_desc.setCellValueFactory(new PropertyValueFactory<>("description"));
            col_st.setCellValueFactory(new PropertyValueFactory<>("status"));
            col_user.setCellValueFactory(new PropertyValueFactory<>("id_org"));
            
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
            infos.setCellFactory(cellFoctory1);
            MatchCrud mc = new MatchCrud();
            // System.out.println("idorg"+id_org);
            dataList = mc.getMatchsUser();
            MatchTable.getItems().addAll(dataList);
        } catch (ParseException ex) {
            Logger.getLogger(AfficherMatchLTController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
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
    private void OnClickBtnConsulterMatchs(ActionEvent event) {
    }

    @FXML
    private void OnClickBtnConsulterStade(ActionEvent event) {
    }

    @FXML
    private void OnClickBtnStatMatch(ActionEvent event) {
    }

    @FXML
    private void exit(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void back(ActionEvent event) {
        /* selon le role*/
        if(role.equals("user")){
            
           try {
            Parent root = FXMLLoader.load(getClass().getResource("/cotakwira/gui/HomeUser.fxml"));
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
        if(role.equals("organisateur")){
            
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
    }

}

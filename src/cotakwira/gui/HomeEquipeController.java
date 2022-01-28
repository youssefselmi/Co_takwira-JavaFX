/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cotakwira.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXColorPicker;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import cotakwira.entities.Equipe;
//import static edu.takwira.gui.AfficherEquipeController.seletionedEquipe;
import cotakwira.resources.Constants;
import cotakwira.services.EquipeCrud;
import cotakwira.services.JoueurCrud;
import cotakwira.util.JFXDialogTool;
import java.io.IOException;
import static java.lang.String.valueOf;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
//import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javax.management.Notification;
import cotakwira.tools.Connection;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author yassin
 */
public class HomeEquipeController implements Initializable {

    @FXML
    private StackPane stckEquipe;
    @FXML
    private AnchorPane rootEquipe;
    @FXML
    private Pane PaneEquipe;
    @FXML
    private TableView<Equipe> TableView;
    @FXML
    private TableColumn<Equipe, Integer> id_e;
    @FXML
    private TableColumn<Equipe, String> nom_equipe;
    @FXML
    private TableColumn<Equipe, String> nom_coach;
    @FXML
    private TableColumn<Equipe, String> prenom_coach;
    private Pane sidebar;
    @FXML
    private Pane Navicons;
    String erreur;
    Notification n;
    @FXML
    private AnchorPane containerAddEquipe;
    private Text textRec;
    @FXML
    private Text textEq;
    @FXML
    private JFXButton btnAjouterEquipe;
    @FXML
    private JFXButton btnModifierEquipe;
    @FXML
    private JFXTextField tfNomEquipe;
    @FXML
    private JFXComboBox<String> combocoach;
    @FXML
    private JFXColorPicker colorpicker;
    @FXML
    private AnchorPane ContainerDeleteEquipe;
    @FXML
    private JFXButton supprimer;
    

    EquipeCrud service = new EquipeCrud();
    @FXML
    private TableColumn<Equipe, String> ActionEquipe;
    @FXML
    private JFXButton btnNewEquipe;

    private JFXDialogTool dialogAddEquipe;
    private JFXDialogTool dialogDeleteEquipe;

    private static final Stage stage = new Stage();
    static Equipe seletionedEquipee;
    @FXML
    private JFXButton btnCancelAddEquipe;

    int verif = 0;
    @FXML
    private ImageView Mark;
static int conaitre_type_joueur= 0;
static int conaitre_id_equipe= 0;
   static String conaitre_nom_equipe="";


    private TableColumn<Equipe, String> col_btnaffichersenior;
   private TableColumn<Equipe, String> col_btnafficherjunior;
    @FXML
    private ImageView Mark1;
    @FXML
    private Rectangle rectangle;
    @FXML
    private JFXColorPicker colorpicker1;
    @FXML
    private Rectangle rectangle1;
    @FXML
    private Label nomEquipemaillot;
    @FXML
    private JFXButton NavReclamation;
    @FXML
    private JFXButton NavRate;
    @FXML
    private JFXButton NavCoach;
    @FXML
    private JFXButton NavEntrainemet;
    @FXML
    private JFXButton NavEquipe;
    @FXML
    private JFXButton NavArticle;
    @FXML
    private JFXButton NavShop;
    @FXML
    private JFXButton NavStade;
    @FXML
    private JFXButton NavMatch;
    @FXML
    private JFXButton NavHome;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
                  nomEquipemaillot.setVisible(false);
        
        tfNomEquipe.textProperty().addListener((observable, oldValue, newValue) -> {
nomEquipemaillot.setText(newValue);
            nomEquipemaillot.setVisible(true);             testNom() ;
});
         colorpicker.setOnAction(new EventHandler() {
            public void handle(Event t) {
                rectangle.setFill(colorpicker.getValue()); 
                colorpicker.setVisible(false);
                                colorpicker1.setVisible(true);

            }
        });
         colorpicker1.setOnAction(new EventHandler() {
            public void handle(Event t) {
                rectangle1.setFill(colorpicker1.getValue());   
                          colorpicker.setVisible(true);
                                colorpicker1.setVisible(false);
            }
        });
        try {
            String requete = "SELECT * FROM coach";
            Statement st = Connection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                combocoach.getItems().addAll(rs.getString(2) + " " + rs.getString(3));
                // nvpc.getItems().addAll(rs.getString(3));   

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        refreche();
                   col_btnaffichersenior = new TableColumn("affichersenior");
                   javafx.util.Callback<TableColumn<Equipe, String>, TableCell<Equipe, String>> cellFactory
                = (final TableColumn<Equipe, String> param) -> {
                    final TableCell<Equipe, String> cell = new TableCell<Equipe, String>() {
                        
                        final Button btn = new Button("affichersenior");
                        
                        @Override
                        public void updateItem(String item, boolean empty) {
                            super.updateItem(item, empty);
                            if (empty) {
                                setGraphic(null);
                                setText(null);
                            } else {
                         btn.setStyle("-fx-background-color:#4CAF50;-fx-background-radius: 5em;\n");

                                btn.setOnAction(event -> {
                             try {
                                 Equipe e = getTableView().getItems().get(getIndex());
                                 
                                  //String categorie = TableView.getSelectionModel().getSelectedItem().getCateg();

//                             combocoach.setValue(TableView.getSelectionModel().getSelectedItem().getNom_coach()+" "+TableView.getSelectionModel().getSelectedItem().getPrenom_coach());
                           // tfNomEquipe.setText(TableView.getSelectionModel().getSelectedItem().getNom_equipe());

                                 
                                 
                                 conaitre_type_joueur = 1;
                                 
                                conaitre_id_equipe= e.getId_equipe();
                             conaitre_nom_equipe= e.getNom_equipe();


                                 ((Node)event.getSource()).getScene().getWindow().hide();
                                 
                                 FXMLLoader loader = new FXMLLoader(getClass().getResource("HomeJoueur.fxml"));
                                 Parent root = (Parent) loader.load();

                                 HomeJoueurController secController = loader.getController();
                                 secController.refreshJoueur();
                                         new animatefx.animation.BounceIn(root).play();

                           secController.initData();

                                 
                                 
                                 // secController.initialize(url, rb);
                                 Stage stage = new Stage();
                                 stage.setScene(new Scene(root));
                                 stage.initStyle(StageStyle.TRANSPARENT);
                                 stage.initStyle(StageStyle.TRANSPARENT);
                                 stage.show();} catch (IOException ex) {
                                 Logger.getLogger(HomeEquipeController.class.getName()).log(Level.SEVERE, null, ex);
                             }
                            
                                    
                                    
                                
                                    
                                });
                                setGraphic(btn);
                                setText(null);
                            }
                        }
                    };
                    return cell;
           };
        
 col_btnaffichersenior.setCellFactory(cellFactory);
        TableView.getColumns().add(col_btnaffichersenior);
         col_btnafficherjunior = new TableColumn("afficherjunior");
                   javafx.util.Callback<TableColumn<Equipe, String>, TableCell<Equipe, String>> cellFactoryy
                = (final TableColumn<Equipe, String> param) -> {
                    final TableCell<Equipe, String> cell = new TableCell<Equipe, String>() {
                        
                        final Button btn1 = new Button("afficherjunior");
                        
                        @Override
                        public void updateItem(String item, boolean empty) {
                            super.updateItem(item, empty);
                            if (empty) {
                                setGraphic(null);
                                setText(null);
                            } else {
                         btn1.setStyle("-fx-background-color:#4CAF50;-fx-background-radius: 5em;\n");

                                btn1.setOnAction(event -> {
                             try {
                                 Equipe e = getTableView().getItems().get(getIndex());
                                 
                                 
                                 
                                 
                                 conaitre_type_joueur = 2;
                                  
                               
                             conaitre_nom_equipe= e.getNom_equipe();
                                 
                                conaitre_id_equipe= e.getId_equipe();

                                 
                                 
                                 FXMLLoader loader = new FXMLLoader(getClass().getResource("HomeJoueur.fxml"));
                                 Parent root = (Parent) loader.load();
                                                                          new animatefx.animation.BounceIn(root).play();

                                 HomeJoueurController secController = loader.getController();
                                 secController.refreshJoueur();
                                   secController.initData();

                                 
                                 // secController.initialize(url, rb);
                                 Stage stage = new Stage();
                                 stage.setScene(new Scene(root));
                                 stage.show();} catch (IOException ex) {
                                 Logger.getLogger(HomeEquipeController.class.getName()).log(Level.SEVERE, null, ex);
                             }
                            
                                    
                                    
                                
                                    
                                });
                                setGraphic(btn1);
                                setText(null);
                            }
                        }
                    };
                    return cell;
           };
        col_btnafficherjunior.setCellFactory(cellFactoryy);
        TableView.getColumns().add(col_btnafficherjunior);
    
    
    }

    private void refreche() {
        List<Equipe> listequipe = new ArrayList<>();

        listequipe = service.afficherEquipe();


        ObservableList<Equipe> Liste = FXCollections.observableArrayList(listequipe);

        id_e.setCellValueFactory(new PropertyValueFactory<Equipe, Integer>("id_equipe"));
        nom_equipe.setCellValueFactory(new PropertyValueFactory<Equipe, String>("nom_equipe"));
        nom_coach.setCellValueFactory(new PropertyValueFactory<Equipe, String>("nom_coach"));
        prenom_coach.setCellValueFactory(new PropertyValueFactory<Equipe, String>("prenom_coach"));
//                categorieee.setCellValueFactory(new PropertyValueFactory<Equipe, String>("categ"));




        //add cell of button edit 
        Callback<TableColumn<Equipe, String>, TableCell<Equipe, String>> cellFoctory = (TableColumn<Equipe, String> param) -> {
            //make cell containing buttons
            final TableCell<Equipe, String> cell = new TableCell<Equipe, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    // that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                    } else {
                        FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                        FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);
                        FontAwesomeIconView afficheTerrain = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);



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
                            System.out.println("icon delete is pressed !");

                            int id_equipe = Integer.valueOf((TableView.getSelectionModel().getSelectedItem().getId_equipe()));
                            System.out.println("id selectioned is !" + id_equipe);
                            showDialogDeleteEquipe();
                            verif = 1;

                        });
                        editIcon.setOnMouseClicked((MouseEvent event) -> {
                            System.out.println("icon edit is pressed !");
                            textEq = new Text("modifier Equipe");

                            //textRec.setText("Modifier Equipe");
                            int id_equipe = Integer.valueOf((TableView.getSelectionModel().getSelectedItem().getId_equipe()));
                            System.out.println("id selectioned is !" + id_equipe);
                            combocoach.setValue(TableView.getSelectionModel().getSelectedItem().getNom_coach() + " " + TableView.getSelectionModel().getSelectedItem().getPrenom_coach());
                            tfNomEquipe.setText(TableView.getSelectionModel().getSelectedItem().getNom_equipe());

                            //btnUpdateReclam.toFront();
                            showDialogAddEquipe();
                            btnModifierEquipe.toFront();
                            verif = 1;

                        });

//                        afficheTerrain.setOnMouseClicked((MouseEvent event) -> {
//                            System.out.println("affiche terrain is pressed !");
//
//                            int id_equipe = Integer.valueOf((TableView.getSelectionModel().getSelectedItem().getId_equipe()));
//                                                        String categorie = TableView.getSelectionModel().getSelectedItem().getCateg();
//
//                            System.out.println("id selectioned is !" + id_equipe);
////                             combocoach.setValue(TableView.getSelectionModel().getSelectedItem().getNom_coach()+" "+TableView.getSelectionModel().getSelectedItem().getPrenom_coach());
//                            tfNomEquipe.setText(TableView.getSelectionModel().getSelectedItem().getNom_equipe());
//
//                            try {
//                                FXMLLoader loader = new FXMLLoader(getClass().getResource("afficherjoueur.fxml"));
//                                Parent root = (Parent) loader.load();
//
//                                AfficherjoueurController secController = loader.getController();
//                                secController.initData(id_equipe,tfNomEquipe.getText(),categorie);
//
//                                // secController.initialize(url, rb);
//                                Stage stage = new Stage();
//                                stage.setScene(new Scene(root));
//                                stage.show();
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                            }
//
//                        });

                        HBox managebtn = new HBox(editIcon, deleteIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                        HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));
                        setGraphic(managebtn);
                        

                    }

                }

            };

            return cell;
        };
Callback<TableColumn<Equipe, String>, TableCell<Equipe, String>> cellFoctoryy = (TableColumn<Equipe, String> param) -> {
            // make cell containing buttons
            
            final TableCell<Equipe, String> cell = new TableCell<Equipe, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        Button afficherjoueurssenior = new Button(); 
                        afficherjoueurssenior.setText("afficher sunior");
                    afficherjoueurssenior.setStyle("-fx-background-color:#4CAF50;-fx-background-radius: 5em;\n");
                  


                           afficherjoueurssenior.setOnMouseClicked((MouseEvent event) -> {
                        
                                                  
                              int id_equipe = TableView.getSelectionModel().getSelectedItem().getId_equipe();
                               System.out.println(id_equipe);
 
                                                       

                           


                           
                       });
                           
                     

                        HBox managebtn = new HBox(afficherjoueurssenior);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(afficherjoueurssenior ,new Insets(2, 2, 0, 3));
                                               
                     

                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };
//afficherjoueur.setCellFactory(cellFoctoryy);
        ActionEquipe.setCellFactory(cellFoctory);
        TableView.getItems().clear();
        TableView.setItems(Liste);

    }

    @FXML
  /* private void setOnMouseClicked(MouseEvent event) {

        if (verif == 0) {
            JoueurCrud jc = new JoueurCrud();

            seletionedEquipee = TableView.getSelectionModel().getSelectedItem();
            System.out.println(seletionedEquipee);
            int id3 = HomeEquipeController.seletionedEquipee.getId_equipe();
                        String categ = HomeEquipeController.seletionedEquipee.getCateg();
                        
                        
conaitre_type_joueur = 1;
            System.out.println(id3);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("HomeJoueur.fxml"));
                Parent root = (Parent) loader.load();

                HomeJoueurController secController = loader.getController();
                secController.refreshJoueur();

                // secController.initialize(url, rb);
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }  }
    

   @FXML*/
    private void handleShowTableRec(Event event) {
        refreche();
    }



    @FXML
    private void AjouterEquipe(ActionEvent event) {
if ((testNom()) && (testnometprenomCoach())){
        String nom_equipe = tfNomEquipe.getText();

        // int id_coach=Integer.parseInt(valueOf(ListeEquipeController.seletionedEquipe.getId_coach()));
        String nom_coach = combocoach.getSelectionModel().getSelectedItem();
        int idc = service.recupereridCoach(nom_coach);
        Equipe e = new Equipe(nom_equipe, idc);

        EquipeCrud ecd = new EquipeCrud();

        ecd.ajouterEquipe(e);
                
}

             //   ecd.ajouterEquipe(e2);

        refreche();
        closeDialogAddEquipe();

    }

    @FXML
    private void closeDialogAddEquipe() {
        if (dialogAddEquipe != null) {
            dialogAddEquipe.close();
        }
        System.out.println("Exit popup Equipe");
        verif = 0;
        Mark.setVisible(false);
    }

    @FXML
    private void closeDialogAddequipe(MouseEvent event) {
        if (dialogAddEquipe != null) {
            dialogAddEquipe.close();
        }
        System.out.println("Exit popup Equipe");
        verif = 0;
    }

    @FXML
    private void updateequipe(ActionEvent event) {

        int id_equipe = Integer.valueOf((TableView.getSelectionModel().getSelectedItem().getId_equipe()));

        String nom_equipe = tfNomEquipe.getText();
        // int id_coach=Integer.parseInt(valueOf(ListeEquipeController.seletionedEquipe.getId_coach()));
        String nom_coach = combocoach.getSelectionModel().getSelectedItem();
        //String prenom_coach=nvpc.getSelectionModel().getSelectedItem();
        int id = service.recupereridCoach(nom_coach);
        System.out.println(id);       // Integer id_coach=Integer.parseInt(nvidc.getText());
        // setId_coach(nvidc.getSelectionModel().getSelectedItem());

        Equipe e = new Equipe(nom_equipe, id);
        EquipeCrud ee = new EquipeCrud();
        ee.updateEquipe(id_equipe, e);

        refreche();
        closeDialogAddEquipe();

    }

    @FXML
    private void hideDialogDeleteEquipe() {
        if (dialogDeleteEquipe != null) {
            dialogDeleteEquipe.close();
        }
        verif = 0;
    }

    @FXML
    private void supprimerequipe(ActionEvent event) {

        int id_equipe = Integer.valueOf((TableView.getSelectionModel().getSelectedItem().getId_equipe()));

        service.supprimerEquipe(id_equipe);
        refreche();
        hideDialogDeleteEquipe();
    }

    @FXML
    private void hideDialogDeleteReclam(MouseEvent event) {

        rootEquipe.setEffect(Constants.BOX_BLUR_EFFECT);
        ContainerDeleteEquipe.setVisible(true);

        dialogDeleteEquipe = new JFXDialogTool(ContainerDeleteEquipe, stckEquipe);
        dialogDeleteEquipe.show();

        dialogDeleteEquipe.setOnDialogClosed(ev -> {
            TableView.setDisable(false);
            rootEquipe.setEffect(null);
            ContainerDeleteEquipe.setVisible(false);
            refreche();

        });
    }

    @FXML
    private void showDialogAddEquipe() {
        Mark.setVisible(false);
        rootEquipe.setEffect(Constants.BOX_BLUR_EFFECT);
        textRec = new Text("ajouter");
        textEq.setText("ajouter une Equipe");
        //imageContainer.toFront();
        containerAddEquipe.setVisible(true);
        btnAjouterEquipe.setDisable(false);
        btnModifierEquipe.setVisible(true);
        btnAjouterEquipe.toFront();

        dialogAddEquipe = new JFXDialogTool(containerAddEquipe, stckEquipe);
        dialogAddEquipe.show();

        dialogAddEquipe.setOnDialogClosed(ev -> {
            closeStage();
            TableView.setDisable(false);
            rootEquipe.setEffect(null);
            containerAddEquipe.setVisible(false);
            refreche();
            tfNomEquipe.clear();
            combocoach.getSelectionModel().clearSelection();
            rectangle.setFill(Color.TRANSPARENT);
                        rectangle1.setFill(Color.TRANSPARENT);

            textEq.setText("modifier une Equipe");

        });

    }
  

    


    private void showDialogDeleteEquipe() {
        rootEquipe.setEffect(Constants.BOX_BLUR_EFFECT);
        ContainerDeleteEquipe.setVisible(true);

        dialogDeleteEquipe = new JFXDialogTool(ContainerDeleteEquipe, stckEquipe);
        dialogDeleteEquipe.show();

        dialogDeleteEquipe.setOnDialogClosed(ev -> {
            TableView.setDisable(false);
            rootEquipe.setEffect(null);
            ContainerDeleteEquipe.setVisible(false);

            refreche();

        });

    }

    public static void closeStage() {
        if (stage != null) {
            stage.hide();
        }
    }
    private boolean testnometprenomCoach(){
        String nom = combocoach.getSelectionModel().getSelectedItem();
        if(nom.isEmpty())
        {
                        Mark1.setImage(new Image("images/erreurcheckMark.png"));

            
            return false;}
        else{
                        Mark1.setImage(new Image("images/checkMark.png"));

        return true;         
        }
}
        private Boolean testNom() {
                    Mark.setVisible(true);

        int nbNonChar = 0;
        for (int i = 1; i < tfNomEquipe.getText().trim().length(); i++) {
            char ch = tfNomEquipe.getText().charAt(i);
            if (!Character.isLetter(ch)) {
                nbNonChar++;
            }
        }

        if (nbNonChar == 0 && tfNomEquipe.getText().trim().length() >= 3) {
            Mark.setImage(new Image("images/checkMark.png"));
            return true;
        } else {
            Mark.setImage(new Image("images/erreurcheckMark.png"));
//                erreur = erreur + ("Pas de caractere permit dans le telephone\n");
            return false;

        }

    }



    @FXML
    private void minimize_app(ActionEvent event) {
        FirstWindow.stage.setIconified(true);
    }

    @FXML
    private void NavReclamation(ActionEvent event) throws IOException {

        Parent menu = FXMLLoader.load(getClass().getResource("HomeReclamation.fxml"));
        stckEquipe.getChildren().removeAll();
        stckEquipe.getChildren().setAll(menu);
    }

    @FXML
    private void NavRate(ActionEvent event) throws IOException {

        Parent menu = FXMLLoader.load(getClass().getResource("HomeRate.fxml"));
        stckEquipe.getChildren().removeAll();
        stckEquipe.getChildren().setAll(menu);

    }

    @FXML
    private void NavCoach(ActionEvent event) throws IOException {
        Parent menu = FXMLLoader.load(getClass().getResource("HomeCoach.fxml"));
        stckEquipe.getChildren().removeAll();
        stckEquipe.getChildren().setAll(menu);
    }

    @FXML
    private void NavEntrainemet(ActionEvent event) throws IOException {
        Parent menu = FXMLLoader.load(getClass().getResource("afficheentrainement.fxml"));
        stckEquipe.getChildren().removeAll();
        stckEquipe.getChildren().setAll(menu);

    }

    @FXML
    private void NavEquipe(ActionEvent event) throws IOException {
        Parent menu = FXMLLoader.load(getClass().getResource("HomeEquipe.fxml"));
        stckEquipe.getChildren().removeAll();
        stckEquipe.getChildren().setAll(menu);
    }

    @FXML
    private void NavArticle(ActionEvent event) throws IOException {

        Parent menu = FXMLLoader.load(getClass().getResource("DisplayArticle.fxml"));
        stckEquipe.getChildren().removeAll();
        stckEquipe.getChildren().setAll(menu);

    }

    @FXML
    private void NavShop(ActionEvent event) throws IOException {
        Parent menu = FXMLLoader.load(getClass().getResource("stocks.fxml"));
        stckEquipe.getChildren().removeAll();
        stckEquipe.getChildren().setAll(menu);

    }

    @FXML
    private void NavStade(ActionEvent event) throws IOException {

    }

    @FXML
    private void NavMatch(ActionEvent event) throws IOException {

    }

    @FXML
    private void NavHome(ActionEvent event) throws IOException {
        Parent menu = FXMLLoader.load(getClass().getResource("Home.fxml"));
        stckEquipe.getChildren().removeAll();
        stckEquipe.getChildren().setAll(menu);

    }



}

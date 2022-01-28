/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cotakwira.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import cotakwira.entities.Equipe;
import cotakwira.entities.Joueur;
import static cotakwira.gui.HomeEquipeController.closeStage;
import static cotakwira.gui.HomeEquipeController.conaitre_id_equipe;
import static cotakwira.gui.HomeEquipeController.conaitre_nom_equipe;
import static cotakwira.gui.HomeEquipeController.conaitre_type_joueur;
import cotakwira.resources.Constants;
import cotakwira.services.JoueurCrud;
import cotakwira.tools.Connection;
import cotakwira.util.JFXDialogTool;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Point3D;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DateCell;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javafx.util.Duration;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * FXML Controller class
 *
 * @author yassin
 */
public class HomeJoueurController implements Initializable {

    @FXML
    private StackPane stckJoueur;
    @FXML
    private AnchorPane rootJoueur;
    @FXML
    private Pane PaneEquipe;
    //private TableView<Joueur> TableView;
    //private TableColumn<Joueur,Integer > id_joueur;
    @FXML
    private TableColumn<Joueur, String> nom_joueur;
    @FXML
    private TableColumn<Joueur, String> prenom_joueur;

    @FXML
    private TableColumn<Joueur, Date> date_naissance;
    @FXML
    private TableColumn<Joueur, String> mail;
    @FXML
    private TableColumn<Joueur, String> ville;
    @FXML
    private TableColumn<Joueur, String> position;
    @FXML
    private TableColumn<Joueur, Integer> numero;
    private Pane sidebar;
    @FXML
    private JFXButton btnAjouterJoueur;
    @FXML
    private Pane Navicons;
    @FXML
    private AnchorPane containerAddJoueur;
    private Text textRec;
    @FXML
    private JFXButton btnCancelAddJoueur;
    @FXML
    private JFXButton btnModifierJoueur;
    @FXML
    private AnchorPane ContainerDeleteJoueur;
    @FXML
    private JFXButton supprimer;
    @FXML
    private Text textJoueur;

    @FXML
    private TableColumn<Joueur, ImageView> image_joueur;
    JoueurCrud jcd = new JoueurCrud();

    //   String categ= HomeEquipeController.seletionedEquipee.getCateg();
//dddd
    private JFXDialogTool dialogAddJoueur;
    private JFXDialogTool dialogDeleteJoueur;
    private String password = "";
    private static final Stage stage = new Stage();
    @FXML
    private JFXTextField tfPrenomJoueur;
    @FXML
    private JFXTextField tfMailJoueur;
    @FXML
    private JFXComboBox<String> comboPosition;
    @FXML
    private JFXTextField tfNomJoueur;
    @FXML
    private JFXTextField tfNumJoueur;
    @FXML
    private JFXTextField tfvilleJoueur;
    @FXML
    private JFXDatePicker date_naissanceJoueur;
    @FXML
    private JFXTextField tfageJoueur;
    @FXML
    private ImageView imageview_joueur;
    @FXML
    private JFXComboBox<String> Comboid_equipe;
    private String nomImage_Joueur = "";
    @FXML
    private TableView<Joueur> TableViewjoueur;
    @FXML
    private TableColumn<Joueur, Integer> age;
    @FXML
    private TableColumn<Joueur, String> actionJoueur;
    @FXML
    private Label nomEquipe;
    @FXML
    private Label goal;
    @FXML
    private Label attaque2;
    @FXML
    private Label attaque1;
    @FXML
    private Circle cerclegoal;
    @FXML
    private Label milieu1;
    @FXML
    private Label milieu4;
    @FXML
    private Label def1;
    @FXML
    private Label milieu2;
    @FXML
    private Label milieu3;
    @FXML
    private Label def2;
    @FXML
    private Label déf3;
    @FXML
    private Label def4;
    @FXML
    private Circle cercleattaque1;
    @FXML
    private Circle cercleattaque2;
    @FXML
    private Circle mileu1cercle;
    @FXML
    private Circle mileu2cercle;
    @FXML
    private Circle mileu4cercle;
    @FXML
    private Circle mileu3cercle;
    @FXML
    private Circle def2cercle;
    @FXML
    private Circle def3cercle;
    @FXML
    private Circle def1cercle;
    @FXML
    private Circle def4cercle;

 Joueur goalJoueur = new Joueur();
          Joueur attaque1Joueur = new Joueur();
                 Joueur attaque2Joueur = new Joueur();
                 Joueur milieu1Joueur = new Joueur();
               Joueur milieu2Joueur = new Joueur();
               Joueur milieu3Joueur = new Joueur();
               Joueur milieu4Joueur = new Joueur();
               Joueur Defense1Joueur = new Joueur();
               Joueur Defense2Joueur = new Joueur();
               Joueur Defense3Joueur = new Joueur();
               Joueur Defense4Joueur = new Joueur();
private String nom_equipe;
    @FXML
    private JFXTextField equipe;
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
//        sidebar.setVisible(false);
//        Joueur j;
//        Timer timer=new Timer();
//        TimerTask Task=new Mytask();
//        timer.schedule(Task, 0 , 1000);
        refreshJoueur();
                jcd.miseajour();
                            equipe.setText(String.valueOf(conaitre_nom_equipe));

   try {
            String requete = "SELECT * FROM equipe";
            Statement st = Connection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Comboid_equipe.getItems().addAll(rs.getString(2));
                // nvpc.getItems().addAll(rs.getString(3));   

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
//Timeline fiveSecondsWonder = new Timeline(
//                 new KeyFrame(Duration.seconds(2), 
//                 new EventHandler<ActionEvent>() {
//
//    @Override
//    public void handle(ActionEvent event) {
//        System.out.println("this is called every 5 seconds on UI thread");
//         jcd.miseajour();
//        refreshJoueur();
//    }
//}));
//fiveSecondsWonder.setCycleCount(Timeline.INDEFINITE);
//fiveSecondsWonder.play();
        // TODO
        ObservableList<String> List = FXCollections.observableArrayList("Attaque", "Deffense", "Milieu", "Goal");

        System.out.println(password);

        comboPosition.setItems(List);
        LocalDate minDate = LocalDate.of(1980, 1, 1);
        LocalDate maxDate = LocalDate.of(2011, 1, 1);
        date_naissanceJoueur.setDayCellFactory(d
                -> new DateCell() {
            @Override
            public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);
                setDisable(item.isAfter(maxDate) || item.isBefore(minDate));
            }
        });

        // TODO
        /*  String choix = Comboid_equipe.getSelectionModel().getSelectedItem();
        // System.out.println(choix);
        // Connection cnx = edu.takwira.tools.Connection.getInstance().getCnx();
        try {
            String requete = "SELECT * FROM equipe";
            Statement st = edu.takwira.tools.Connection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Comboid_equipe.getItems().addAll(rs.getString(2));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }*/
        refreshJoueur();
initData();
    }

    public void refreshJoueur() {
        System.out.println("cc" + conaitre_type_joueur);

        List<Joueur> ListJoueur = new ArrayList<>();

        if (conaitre_type_joueur == 1) {
            ListJoueur = jcd.afficherJoueur(conaitre_id_equipe, "sunior");
        } else {
            ListJoueur = jcd.afficherJoueur(conaitre_id_equipe, "junior");
        }
        String nom = jcd.recuperernomEquipe(conaitre_id_equipe);
        System.out.println(nom);

        ObservableList<Joueur> Liste = FXCollections.observableArrayList(ListJoueur);

        //id_joueur.setCellValueFactory(new PropertyValueFactory<Joueur,Integer>("id_joueur"));
        nom_joueur.setCellValueFactory(new PropertyValueFactory<Joueur, String>("nom_joueur"));
        prenom_joueur.setCellValueFactory(new PropertyValueFactory<Joueur, String>("prenom_joueur"));
        date_naissance.setCellValueFactory(new PropertyValueFactory<Joueur, Date>("dateNaissance"));
        numero.setCellValueFactory(new PropertyValueFactory<Joueur, Integer>("numero"));
        mail.setCellValueFactory(new PropertyValueFactory<Joueur, String>("adresse_mail"));
        ville.setCellValueFactory(new PropertyValueFactory<Joueur, String>("ville"));

        position.setCellValueFactory(new PropertyValueFactory<Joueur, String>("position"));

        image_joueur.setCellValueFactory(new PropertyValueFactory<Joueur, ImageView>("imageJouer"));
        age.setCellValueFactory(new PropertyValueFactory<Joueur, Integer>("age_joueur"));

        Callback<TableColumn<Joueur, String>, TableCell<Joueur, String>> cellFoctory = (TableColumn<Joueur, String> param) -> {
            //make cell containing buttons
            final TableCell<Joueur, String> cell = new TableCell<Joueur, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    // that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                    } else {
                        FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                        FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);
                        //FontAwesomeIconView afficheTerrain = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);

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

                            int id_joueur = Integer.valueOf((TableViewjoueur.getSelectionModel().getSelectedItem().getId_joueur()));
                            System.out.println("id selectionedjoueuur is !" + id_joueur);
                            showDialogDeleteJoueur();

                        });
                        editIcon.setOnMouseClicked((MouseEvent event) -> {
                            System.out.println("icon edit is pressed !");
                            textJoueur = new Text("modifier Equipe");

                            //textRec.setText("Modifier Equipe");
                             int id_joueur = Integer.valueOf((TableViewjoueur.getSelectionModel().getSelectedItem().getId_joueur()));
                            System.out.println("id selectioned is !" + id_joueur);                          
                            tfNomJoueur.setText(TableViewjoueur.getSelectionModel().getSelectedItem().getNom_joueur());
                            tfPrenomJoueur.setText(TableViewjoueur.getSelectionModel().getSelectedItem().getPrenom_joueur());
                           tfMailJoueur.setText(TableViewjoueur.getSelectionModel().getSelectedItem().getAdresse_mail());
                           tfvilleJoueur.setText(TableViewjoueur.getSelectionModel().getSelectedItem().getVille());
                           tfNumJoueur.setText(String.valueOf(TableViewjoueur.getSelectionModel().getSelectedItem().getNumero()));
                            comboPosition.setValue(TableViewjoueur.getSelectionModel().getSelectedItem().getPosition());
                          // Comboid_equipe.setValue(String.valueOf(TableViewjoueur.getSelectionModel().getSelectedItem().getId_equipe()));
                          
                            date_naissanceJoueur.setValue(TableViewjoueur.getSelectionModel().getSelectedItem().getDateNaissance().toLocalDate());

                            tfageJoueur.setText(String.valueOf(TableViewjoueur.getSelectionModel().getSelectedItem().getAge_joueur()));
                            Comboid_equipe.setVisible(false);
                            
                            showDialogAddJoueur();
                            btnModifierJoueur.toFront();
                            textJoueur.setText("Modifier Joueur");
                            
//
//        java.util.Date date_n = new java.sql.Date(new java.util.Date(date_naissanceJoueur.getEditor().getText()).getTime());
//        int age = Integer.parseInt(this.tfageJoueur.getText());

                       

                        });

                        HBox managebtn = new HBox(editIcon, deleteIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                        HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));
                        setGraphic(managebtn);

                    }

                }

            };


//                    @Override
//                    public void handle(MouseEvent t) {
//                        Stage window = new Stage();
//
//                        window.setMinWidth(250);
//
//                        ImageView imagevPOPUP=new ImageView(new Image("file:C:\\xampp\\htdocs\\TunisiaBonPlan2-Integration\\web\\uploads\\images\\"+TableViewReclamation.getSelectionModel().getSelectedItem().getScreenshot()));
//                        imagevPOPUP.setFitHeight(576);
//                        imagevPOPUP.setFitWidth(1024);
//
//                        VBox layout = new VBox(10);
//                        layout.getChildren().addAll(imagevPOPUP);
//                        layout.setAlignment(Pos.CENTER);
//
//                        //Display window and wait for it to be closed before returning
//                        Scene scene = new Scene(layout);
//                        window.setScene(scene);
//                        window.show();
//                        cell.setOnMouseExited(new EventHandler<MouseEvent>() {
//
//                            @Override
//                            public void handle(MouseEvent t) {
//                                window.close();
//                            }
//                        });
////                        System.out.println(path);
//                    }
                

            return cell;
        };
                //image_joueur.setCellFactory(cellFoctoryy);

        actionJoueur.setCellFactory(cellFoctory);

        TableViewjoueur.setItems(Liste);
          
        
        
TableViewjoueur.setOnMouseClicked(ev -> {

            if (ev.getButton().equals(MouseButton.PRIMARY) && ev.getClickCount() == 2) {

                int id_joueur = Integer.valueOf((TableViewjoueur.getSelectionModel().getSelectedItem().getId_joueur()));
                try {
                    String requeteee2 = "SELECT image FROM joueur WHERE id_joueur  = '" + id_joueur + "'";
                    Statement psttt2 = Connection.getInstance().getCnx().createStatement();
                    ResultSet rss2 = psttt2.executeQuery(requeteee2);
                                        String ImageUrl = "C:/wamp64/www/images/";

                    while (rss2.next()) {
                        Image img2 = new Image(new FileInputStream(ImageUrl+rss2.getString("image")));

                        Stage window = new Stage();
                        window.setMinWidth(250);
                        ImageView imagev = new ImageView();
                        imagev.setImage(img2);
                        imagev.setFitHeight(576);
                        imagev.setFitWidth(1024);

                        VBox layout = new VBox(10);
                        layout.getChildren().addAll(imagev);
                        layout.setAlignment(Pos.CENTER);

                        Scene scene = new Scene(layout);
                        window.setScene(scene);
                        window.show();
                    }

                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
                catch (FileNotFoundException ex) {
				System.out.println(ex.getMessage());
				}				
            }

        });

           
                   }
  public void TransaltionJoueurs()
    { 
            TranslateTransition translate = new TranslateTransition();
  translate.setNode(cerclegoal);
  translate.setDuration(Duration.millis(1000));
  translate.setCycleCount(TranslateTransition.INDEFINITE);
        cerclegoal.getCenterY();
  translate.setByY(20);


  translate.setAutoReverse(true);
  translate.play();
TranslateTransition translate1 = new TranslateTransition();
translate1.setNode(def1cercle);
  translate1.setDuration(Duration.millis(1000));
  translate1.setCycleCount(TranslateTransition.INDEFINITE);
  def1cercle.getCenterX();
  translate1.setByX(20);


  translate1.setAutoReverse(true);
  translate1.play();
  TranslateTransition translate2 = new TranslateTransition();
translate2.setNode(def2cercle);
  translate2.setDuration(Duration.millis(1000));
  translate2.setCycleCount(TranslateTransition.INDEFINITE);
  def2cercle.getCenterX();
  translate2.setByX(20);


  translate2.setAutoReverse(true);
  translate2.play();
    TranslateTransition translate3 = new TranslateTransition();
translate3.setNode(def3cercle);
  translate3.setDuration(Duration.millis(1000));
  translate3.setCycleCount(TranslateTransition.INDEFINITE);
  def3cercle.getCenterX();
  translate3.setByX(20);


  translate3.setAutoReverse(true);
  translate3.play();
   TranslateTransition translate4= new TranslateTransition();
translate4.setNode(def4cercle);
  translate4.setDuration(Duration.millis(1000));
  translate4.setCycleCount(TranslateTransition.INDEFINITE);
  def4cercle.getCenterX();
  translate4.setByX(20);


  translate4.setAutoReverse(true);
  translate4.play();
    TranslateTransition translate5= new TranslateTransition();
translate5.setNode(mileu1cercle);
  translate5.setDuration(Duration.millis(1000));
  translate5.setCycleCount(TranslateTransition.INDEFINITE);
  mileu1cercle.getCenterX();
  translate5.setByX(20);


  translate5.setAutoReverse(true);
  translate5.play();
   TranslateTransition translate6= new TranslateTransition();
translate6.setNode(mileu2cercle);
  translate6.setDuration(Duration.millis(1000));
  translate6.setCycleCount(TranslateTransition.INDEFINITE);
  mileu2cercle.getCenterX();
  translate6.setByX(20);


  translate6.setAutoReverse(true);
  translate6.play();
  TranslateTransition translate7= new TranslateTransition();
translate7.setNode(mileu3cercle);
  translate7.setDuration(Duration.millis(1000));
  translate7.setCycleCount(TranslateTransition.INDEFINITE);
  mileu3cercle.getCenterX();
  translate7.setByX(20);


  translate7.setAutoReverse(true);
  translate7.play();
    TranslateTransition translate8= new TranslateTransition();
translate8.setNode(mileu4cercle);
  translate8.setDuration(Duration.millis(1000));
  translate8.setCycleCount(TranslateTransition.INDEFINITE);
  mileu4cercle.getCenterX();
  translate8.setByX(20);


  translate8.setAutoReverse(true);
  translate8.play();
  TranslateTransition translate9= new TranslateTransition();
translate9.setNode(cercleattaque1);
  translate9.setDuration(Duration.millis(1000));
  translate9.setCycleCount(TranslateTransition.INDEFINITE);
  cercleattaque1.getCenterX();
  translate9.setByX(20);


  translate9.setAutoReverse(true);
  translate9.play();
   TranslateTransition translate10= new TranslateTransition();
translate10.setNode(cercleattaque2);
  translate10.setDuration(Duration.millis(1000));
  translate10.setCycleCount(TranslateTransition.INDEFINITE);
  cercleattaque2.getCenterX();
  translate10.setByX(20);


  translate10.setAutoReverse(true);
  translate10.play();
    }    public void initData()
            
    {
 TransaltionJoueurs();
//                        new animatefx.animation.AnimateFXInterpolator;
//                        
// RotateTransition rotate = new RotateTransition();
// 
//rotate.setNode(cerclegoal);
//
//  rotate.setDuration(Duration.millis(1500));
//  rotate.setCycleCount(TranslateTransition.INDEFINITE);
//  rotate.setInterpolator(Interpolator.LINEAR);
//  rotate.setByAngle(360);
//  rotate.setAxis(Rotate.Z_AXIS);
//  rotate.play();
      
      //s  id_equipe=id;
                //categ=categorie;

      //  this.nom_equipe=nom_equipe;
        //nomEquipe.setText(nom_equipe);
        
        List<Joueur> ListJoueur = new ArrayList<>();
//int a=ListJoueur.stream().filter(e->e.getPosition().equals("Deffense")).collect(Collectors.toList()).get(0).getId_joueur();
//if ( condition si junior ou senior )
//{
//    ListJoueur = jcd.afficherJoueur(id,"junior");
//}
//    else
//{
//       ListJoueur = jcd.afficherJoueur(id,"junior"); 
//        }
nomEquipe.setText(conaitre_nom_equipe);
if(conaitre_type_joueur==1)
{
        ListJoueur = jcd.afficherJoueur(conaitre_id_equipe,"sunior");
}
else{        ListJoueur = jcd.afficherJoueur(conaitre_id_equipe,"junior");
}

        System.out.println(ListJoueur.toString());
                 
                
                  
                  

              
                  int verif=0;

                
                    try {
               goalJoueur= ListJoueur.stream().filter(e->e.getPosition().equals("Goal")).collect(Collectors.toList()).get(0);
                       goal.setText(goalJoueur.getNom_joueur());
                        goal.setVisible(true);
                       cerclegoal.setVisible(true);
                        javafx.scene.image.Image img=null;

            try {
                img = new javafx.scene.image.Image(new FileInputStream("C:/wamp64/www/images/" + goalJoueur.getImage()));
                   cerclegoal.setFill(new ImagePattern(img));
        cerclegoal.setEffect(new DropShadow(+25d, 0d, +2d, Color.DARKGREEN));
                System.out.println("aaaa");
//                        img1 = new javafx.scene.image.Image(new FileInputStream("C:/wamp64/www/images/" + attaque1Joueur.getImage()));
// cercleattaque1.setFill(new ImagePattern(img1));
//    cercleattaque1.setEffect(new DropShadow(+25d, 0d, +2d, Color.DARKGREEN));
//         img = new javafx.scene.image.Image(new FileInputStream("C:/wamp64/www/images/" + attaque2Joueur.getImage()));
// cercleattaque2.setFill(new ImagePattern(img));
//        cercleattaque2.setEffect(new DropShadow(+25d, 0d, +2d, Color.DARKGREEN));

            } catch (FileNotFoundException ex) {
               // Logger.getLogger(AfficherjoueurController.class.getName()).log(Level.SEVERE, null, ex);
            }
               goal.setOnMouseClicked((MouseEvent event) -> {
        
                   try {
                         System.out.println("ABC");
                       // controller.initData(goal);
                       
                       FXMLLoader loader = new FXMLLoader(getClass().getResource("detailsjoueur.fxml"));
                       Parent root = (Parent) loader.load();
                       
                       DetailsjoueurController secController = loader.getController();
                       secController.initData(goalJoueur);
                        Stage stage = new Stage();
                                 stage.setScene(new Scene(root));
                                 stage.initStyle(StageStyle.TRANSPARENT);
                                 stage.initStyle(StageStyle.TRANSPARENT);
                                 stage.show();
                       
                     
                   } catch (IOException ex) {
                     //  Logger.getLogger(AfficherjoueurController.class.getName()).log(Level.SEVERE, null, ex);
                   }
                            


                        });
                    }
     catch (IndexOutOfBoundsException ex)
                {
                     goal.setVisible(false);
                       cerclegoal.setVisible(false);
                    System.out.println("Milieu 3 inex");
                    
                    
                    
                }
                try{
                                            javafx.scene.image.Image img=null;

                     attaque1Joueur=ListJoueur.stream().filter(e->e.getPosition().equals("Attaque")).collect(Collectors.toList()).get(0);
                attaque1.setText(attaque1Joueur.getNom_joueur());
                  attaque1.setVisible(true);
                       cercleattaque1.setVisible(true);
            try {
                img = new javafx.scene.image.Image(new FileInputStream("C:/wamp64/www/images/" + attaque1Joueur.getImage()));
                 cercleattaque1.setFill(new ImagePattern(img));
        cercleattaque1.setEffect(new DropShadow(+25d, 0d, +2d, Color.DARKGREEN));
            } catch (FileNotFoundException ex) {
              //  Logger.getLogger(AfficherjoueurController.class.getName()).log(Level.SEVERE, null, ex);
            }
              attaque1.setOnMouseClicked((MouseEvent event) -> {
        
                   try {
                         System.out.println("ABC");
                       // controller.initData(goal);

                       FXMLLoader loader = new FXMLLoader(getClass().getResource("detailsjoueur.fxml"));
                       Parent root = (Parent) loader.load();

                       DetailsjoueurController secController = loader.getController();
                   //   new animatefx.animation.BounceIn(root).play();

                       secController.initData(attaque1Joueur);
                  System.out.println("yasmine");

                        Stage stage = new Stage();
                                 stage.setScene(new Scene(root));

                                // stage.initStyle(StageStyle.TRANSPARENT);
                                 //stage.initStyle(StageStyle.TRANSPARENT);
                                 stage.show();
                       
                     
                   } catch (IOException ex) {
                     //  Logger.getLogger(AfficherjoueurController.class.getName()).log(Level.SEVERE, null, ex);
                     
                               
                   }
                            


                        });
                  
                }
                         catch (IndexOutOfBoundsException ex)
                {
                    System.out.println("Milieu 3 inex");
                      attaque1.setVisible(false);
                       cercleattaque1.setVisible(false);
                    
                    
                }
                      try{
                                            javafx.scene.image.Image img=null;
 attaque2Joueur=ListJoueur.stream().filter(e->e.getPosition().equals("Attaque")).collect(Collectors.toList()).get(1);


                          attaque2.setText(attaque2Joueur.getNom_joueur());
                           attaque2.setVisible(true);
                       cercleattaque2.setVisible(true);
            try {
                img = new javafx.scene.image.Image(new FileInputStream("C:/wamp64/www/images/" + attaque2Joueur.getImage()));
                 cercleattaque2.setFill(new ImagePattern(img));
        cercleattaque2.setEffect(new DropShadow(+25d, 0d, +2d, Color.DARKGREEN));
            } catch (FileNotFoundException ex) {
              //  Logger.getLogger(AfficherjoueurController.class.getName()).log(Level.SEVERE, null, ex);
            }
              attaque2.setOnMouseClicked((MouseEvent event) -> {
        
                   try {
                         System.out.println("ABC");
                       // controller.initData(goal);
                       
                       FXMLLoader loader = new FXMLLoader(getClass().getResource("detailsjoueur.fxml"));
                       Parent root = (Parent) loader.load();
                       
                       DetailsjoueurController secController = loader.getController();
                       secController.initData(attaque2Joueur);
                        Stage stage = new Stage();
                                 stage.setScene(new Scene(root));
                                 stage.initStyle(StageStyle.TRANSPARENT);
                                 stage.initStyle(StageStyle.TRANSPARENT);
                                 stage.show();
                       
                     
                   } catch (IOException ex) {
                    //   Logger.getLogger(AfficherjoueurController.class.getName()).log(Level.SEVERE, null, ex);
                   }
                            


                        });
                  
                }
                         catch (IndexOutOfBoundsException ex)
                {
                    System.out.println("attaque2 inex");
                    cercleattaque2.setVisible(false);
                    attaque2.setVisible(false);
                    
                    
                }
                          
                          try{
                                            javafx.scene.image.Image img=null;
milieu1Joueur= ListJoueur.stream().filter(e->e.getPosition().equals("Milieu")).collect(Collectors.toList()).get(0);
 milieu1.setText(milieu1Joueur.getNom_joueur());

                     milieu1.setVisible(true);
                       mileu1cercle.setVisible(true);                      
            try {
                img = new javafx.scene.image.Image(new FileInputStream("C:/wamp64/www/images/" + milieu1Joueur.getImage()));
                 mileu1cercle.setFill(new ImagePattern(img));
        mileu1cercle.setEffect(new DropShadow(+25d, 0d, +2d, Color.DARKGREEN));
            } catch (FileNotFoundException ex) {
               // Logger.getLogger(AfficherjoueurController.class.getName()).log(Level.SEVERE, null, ex);
            }
                  milieu1.setOnMouseClicked((MouseEvent event) -> {
        
                   try {
                         System.out.println("ABC");
                       // controller.initData(goal);
                       
                       FXMLLoader loader = new FXMLLoader(getClass().getResource("detailsjoueur.fxml"));
                       Parent root = (Parent) loader.load();
                       
                       DetailsjoueurController secController = loader.getController();
                       secController.initData(milieu1Joueur);
                        Stage stage = new Stage();
                                 stage.setScene(new Scene(root));
                                 stage.initStyle(StageStyle.TRANSPARENT);
                                 stage.initStyle(StageStyle.TRANSPARENT);
                                 stage.show();
                       
                     
                   } catch (IOException ex) {
                   //    Logger.getLogger(AfficherjoueurController.class.getName()).log(Level.SEVERE, null, ex);
                   }
                            


                        });
                  
                }
                         catch (IndexOutOfBoundsException ex)
                {
                    System.out.println("Milieu 3 inex");
                    
                     milieu1.setVisible(false);
                       mileu1cercle.setVisible(false);
                }
                          try{
                                            javafx.scene.image.Image img=null;

        milieu2Joueur=ListJoueur.stream().filter(e->e.getPosition().equals("Milieu")).collect(Collectors.toList()).get(1);
        milieu2.setText(milieu2Joueur.getNom_joueur());
        
                     milieu2.setVisible(true);
                       mileu2cercle.setVisible(true);
            try {
                img = new javafx.scene.image.Image(new FileInputStream("C:/wamp64/www/images/" + milieu2Joueur.getImage()));
                 mileu2cercle.setFill(new ImagePattern(img));
        mileu2cercle.setEffect(new DropShadow(+25d, 0d, +2d, Color.DARKGREEN));
            } catch (FileNotFoundException ex) {
            //    Logger.getLogger(AfficherjoueurController.class.getName()).log(Level.SEVERE, null, ex);
            }
                  milieu2.setOnMouseClicked((MouseEvent event) -> {
        
                   try {
                         System.out.println("ABC");
                       // controller.initData(goal);
                       
                       FXMLLoader loader = new FXMLLoader(getClass().getResource("detailsjoueur.fxml"));
                       Parent root = (Parent) loader.load();
                       
                       DetailsjoueurController secController = loader.getController();
                       secController.initData(milieu2Joueur);
                        Stage stage = new Stage();
                                 stage.setScene(new Scene(root));
                                 stage.initStyle(StageStyle.TRANSPARENT);
                                 stage.initStyle(StageStyle.TRANSPARENT);
                                 stage.show();
                       
                     
                   } catch (IOException ex) {
                   //    Logger.getLogger(AfficherjoueurController.class.getName()).log(Level.SEVERE, null, ex);
                   }
                            


                        });
                  
                }
                         catch (IndexOutOfBoundsException ex)
                {
                    System.out.println("Milieu 3 inex");
                     milieu2.setVisible(false);
                       mileu2cercle.setVisible(false);
                    
                    
                }
                           try{
                                            javafx.scene.image.Image img=null;


        milieu3Joueur=ListJoueur.stream().filter(e->e.getPosition().equals("Milieu")).collect(Collectors.toList()).get(2);
        milieu3.setText(milieu3Joueur.getNom_joueur());
          milieu3.setVisible(true);
                       mileu3cercle.setVisible(true);
            try {
                img = new javafx.scene.image.Image(new FileInputStream("C:/wamp64/www/images/" + milieu3Joueur.getImage()));
                 mileu3cercle.setFill(new ImagePattern(img));
        mileu3cercle.setEffect(new DropShadow(+25d, 0d, +2d, Color.DARKGREEN));
            } catch (FileNotFoundException ex) {
          //      Logger.getLogger(AfficherjoueurController.class.getName()).log(Level.SEVERE, null, ex);
            }
                  milieu3.setOnMouseClicked((MouseEvent event) -> {
        
                   try {
                         System.out.println("ABC");
                       // controller.initData(goal);
                       
                       FXMLLoader loader = new FXMLLoader(getClass().getResource("detailsjoueur.fxml"));
                       Parent root = (Parent) loader.load();
                       
                       DetailsjoueurController secController = loader.getController();
                       secController.initData(milieu3Joueur);
                        Stage stage = new Stage();
                                 stage.setScene(new Scene(root));
                                 stage.initStyle(StageStyle.TRANSPARENT);
                                 stage.initStyle(StageStyle.TRANSPARENT);
                                 stage.show();
                       
                     
                   } catch (IOException ex) {
                //       Logger.getLogger(AfficherjoueurController.class.getName()).log(Level.SEVERE, null, ex);
                   }
                            


                        });
                  
                }
                         catch (IndexOutOfBoundsException ex)
                {
                    System.out.println("Milieu 3 inex");
                              milieu3.setVisible(false);
                       mileu3cercle.setVisible(false);
                    
                }
                                try{
                                            javafx.scene.image.Image img=null;



        milieu4Joueur=ListJoueur.stream().filter(e->e.getPosition().equals("Milieu")).collect(Collectors.toList()).get(3);
                            milieu4.setText(milieu4Joueur.getNom_joueur());
                                      milieu4.setVisible(true);
                       mileu4cercle.setVisible(true);
            try {
                img = new javafx.scene.image.Image(new FileInputStream("C:/wamp64/www/images/" + milieu4Joueur.getImage()));
                 mileu4cercle.setFill(new ImagePattern(img));
        mileu4cercle.setEffect(new DropShadow(+25d, 0d, +2d, Color.DARKGREEN));
            } catch (FileNotFoundException ex) {
       //         Logger.getLogger(AfficherjoueurController.class.getName()).log(Level.SEVERE, null, ex);
            }
                  milieu4.setOnMouseClicked((MouseEvent event) -> {
        
                   try {
                         System.out.println("ABC");
                       // controller.initData(goal);
                       
                       FXMLLoader loader = new FXMLLoader(getClass().getResource("detailsjoueur.fxml"));
                       Parent root = (Parent) loader.load();
                       
                       DetailsjoueurController secController = loader.getController();
                       secController.initData(milieu4Joueur);
                        Stage stage = new Stage();
                                 stage.setScene(new Scene(root));
                                 stage.initStyle(StageStyle.TRANSPARENT);
                                 stage.initStyle(StageStyle.TRANSPARENT);
                                 stage.show();
                       
                     
                   } catch (IOException ex) {
              //         Logger.getLogger(AfficherjoueurController.class.getName()).log(Level.SEVERE, null, ex);
                   }
                            


                        });
                  
                }
                         catch (IndexOutOfBoundsException ex)
                {
                    System.out.println("Milieu 3 inex");
                        milieu4.setVisible(false);
                       mileu4cercle.setVisible(false);
                    
                }
                                 try{
                                            javafx.scene.image.Image img=null;



        Defense1Joueur=ListJoueur.stream().filter(e->e.getPosition().equals("Deffense")).collect(Collectors.toList()).get(0);
                            def1.setText(Defense1Joueur.getNom_joueur());
                                def1.setVisible(true);
                       def1cercle.setVisible(true);
            try {
                img = new javafx.scene.image.Image(new FileInputStream("C:/wamp64/www/images/" + Defense1Joueur.getImage()));
                 def1cercle.setFill(new ImagePattern(img));
        def1cercle.setEffect(new DropShadow(+25d, 0d, +2d, Color.DARKGREEN));
            } catch (FileNotFoundException ex) {
         //       Logger.getLogger(AfficherjoueurController.class.getName()).log(Level.SEVERE, null, ex);
            }
                  def1.setOnMouseClicked((MouseEvent event) -> {
        
                   try {
                         System.out.println("ABC");
                       // controller.initData(goal);
                       
                       FXMLLoader loader = new FXMLLoader(getClass().getResource("detailsjoueur.fxml"));
                       Parent root = (Parent) loader.load();
                                                                new animatefx.animation.BounceIn(root).play();

                       DetailsjoueurController secController = loader.getController();
                       secController.initData(Defense1Joueur);
                        Stage stage = new Stage();
                                 stage.setScene(new Scene(root));
                                 stage.initStyle(StageStyle.TRANSPARENT);
                                 stage.initStyle(StageStyle.TRANSPARENT);
                                 stage.show();
                       
                     
                   } catch (IOException ex) {
              //         Logger.getLogger(AfficherjoueurController.class.getName()).log(Level.SEVERE, null, ex);
                   }
                            


                        });
                  
                }
                         catch (IndexOutOfBoundsException ex)
                {
                    System.out.println("Milieu 3 inex");
                    def1.setVisible(false);
                       def1cercle.setVisible(false);
                    
                }
                                   try{
                                            javafx.scene.image.Image img=null;



        Defense2Joueur=ListJoueur.stream().filter(e->e.getPosition().equals("Deffense")).collect(Collectors.toList()).get(1);
                            def2.setText(Defense2Joueur.getNom_joueur());
                             def2.setVisible(true);
                       def2cercle.setVisible(true);
            try {
                img = new javafx.scene.image.Image(new FileInputStream("C:/wamp64/www/images/" + Defense2Joueur.getImage()));
                 def2cercle.setFill(new ImagePattern(img));
        def2cercle.setEffect(new DropShadow(+25d, 0d, +2d, Color.DARKGREEN));
            } catch (FileNotFoundException ex) {
     //           Logger.getLogger(AfficherjoueurController.class.getName()).log(Level.SEVERE, null, ex);
            }
                  def2.setOnMouseClicked((MouseEvent event) -> {
        
                   try {
                         System.out.println("ABC");
                       // controller.initData(goal);
                       
                       FXMLLoader loader = new FXMLLoader(getClass().getResource("detailsjoueur.fxml"));
                       Parent root = (Parent) loader.load();
                       
                       DetailsjoueurController secController = loader.getController();
                       secController.initData(Defense2Joueur);
                        Stage stage = new Stage();
                                 stage.setScene(new Scene(root));
                                 stage.initStyle(StageStyle.TRANSPARENT);
                                 stage.initStyle(StageStyle.TRANSPARENT);
                                 stage.show();
                       
                     
                   } catch (IOException ex) {
              //         Logger.getLogger(AfficherjoueurController.class.getName()).log(Level.SEVERE, null, ex);
                   }
                            


                        });
                  
                }
                         catch (IndexOutOfBoundsException ex)
                {
                    System.out.println("Milieu 3 inex");
                    def2.setVisible(false);
                       def2cercle.setVisible(false);
                    
                }
                           
                                   try{
                                            javafx.scene.image.Image img=null;



        Defense3Joueur=ListJoueur.stream().filter(e->e.getPosition().equals("Deffense")).collect(Collectors.toList()).get(2);
                            déf3.setText(Defense3Joueur.getNom_joueur());
                                 déf3.setVisible(true);
                       def3cercle.setVisible(true);
            try {
                img = new javafx.scene.image.Image(new FileInputStream("C:/wamp64/www/images/" + Defense3Joueur.getImage()));
                 def3cercle.setFill(new ImagePattern(img));
        def3cercle.setEffect(new DropShadow(+25d, 0d, +2d, Color.DARKGREEN));
            } catch (FileNotFoundException ex) {
       //         Logger.getLogger(AfficherjoueurController.class.getName()).log(Level.SEVERE, null, ex);
            }
                  déf3.setOnMouseClicked((MouseEvent event) -> {
        
                   try {
                         System.out.println("ABC");
                       // controller.initData(goal);
                       
                       FXMLLoader loader = new FXMLLoader(getClass().getResource("detailsjoueur.fxml"));
                       Parent root = (Parent) loader.load();
                       
                       DetailsjoueurController secController = loader.getController();
                       secController.initData(Defense3Joueur);
                        Stage stage = new Stage();
                                 stage.setScene(new Scene(root));
                                 stage.initStyle(StageStyle.TRANSPARENT);
                                 stage.initStyle(StageStyle.TRANSPARENT);
                                 stage.show();
                       
                     
                   } catch (IOException ex) {
                   //    Logger.getLogger(AfficherjoueurController.class.getName()).log(Level.SEVERE, null, ex);
                         
                   }
                            


                        });
                  
                }
                         catch (IndexOutOfBoundsException ex)
                {
                    System.out.println("Milieu 3 inex");
                    déf3.setVisible(false);
                       def3cercle.setVisible(false);
                    
                }
                                                        try{
                                            javafx.scene.image.Image img=null;



        Defense4Joueur=ListJoueur.stream().filter(e->e.getPosition().equals("Deffense")).collect(Collectors.toList()).get(3);
                            def4.setText(Defense4Joueur.getNom_joueur());
                            def4.setVisible(true);
                       def4cercle.setVisible(true);
            try {
                img = new javafx.scene.image.Image(new FileInputStream("C:/wamp64/www/images/" + Defense4Joueur.getImage()));
                 def4cercle.setFill(new ImagePattern(img));
        def4cercle.setEffect(new DropShadow(+25d, 0d, +2d, Color.DARKGREEN));
            } catch (FileNotFoundException ex) {
          //      Logger.getLogger(AfficherjoueurController.class.getName()).log(Level.SEVERE, null, ex);
            }
                  def4.setOnMouseClicked((MouseEvent event) -> {
        
                   try {
                         System.out.println("ABC");
                       // controller.initData(goal);
                       
                       FXMLLoader loader = new FXMLLoader(getClass().getResource("detailsjoueur.fxml"));
                       Parent root = (Parent) loader.load();
                       
                       DetailsjoueurController secController = loader.getController();
                       secController.initData(Defense4Joueur);
                        Stage stage = new Stage();
                                 stage.setScene(new Scene(root));
                                 stage.initStyle(StageStyle.TRANSPARENT);
                                 stage.initStyle(StageStyle.TRANSPARENT);
                                 stage.show();
                       
                     
                   } catch (IOException ex) {
               //        Logger.getLogger(AfficherjoueurController.class.getName()).log(Level.SEVERE, null, ex);
                   }
                            


                        });
                  
                }
                         catch (IndexOutOfBoundsException ex)
                {
                        def4.setVisible(false);
                       def4cercle.setVisible(false);
                    System.out.println("Milieu 3 inex");
                    
                    
                }

//        milieu4.setText(ListJoueur.stream().filter(e->e.getPosition().equals("Milieu")).collect(Collectors.toList()).get(3).getNom_joueur());
//           if(ListJoueur.stream().anyMatch(c->c.getPosition().equals("Deffense")))
//           {
//                          def1.setText(ListJoueur.stream().filter(e->e.getPosition().equals("Deffense")).collect(Collectors.toList()).get(0).getNom_joueur());
//                          
//                           System.out.println("hhh");
//                           try{
//                                                     def2.setText(ListJoueur.stream().filter(e->e.getPosition().equals("Deffense")).collect(Collectors.toList()).get(1).getNom_joueur());
//                                    
//                                       déf3.setText(ListJoueur.stream().filter(e->e.getPosition().equals("Deffense")).collect(Collectors.toList()).get(2).getNom_joueur());
//                           }    catch(Exception ex)  {System.out.println("haahah");}
//                                      
//                                  
//
//           }
//      


               /*    if(ListJoueur.stream().anyMatch(c->c.getPosition().equals("Deffense")))
                   { 
                       if(ListJoueur.stream().anyMatch(c->c.getId_joueur()!=a))
                       {

                          def2.setText(ListJoueur.stream().filter(e->e.getPosition().equals("Deffense")).collect(Collectors.toList()).get(1).getNom_joueur());}
                       else {System.out.println("nrml");}
        
                   }
                                          else {System.out.println("nrml");}*/



        ObservableList<Joueur> Liste = FXCollections.observableArrayList(ListJoueur);
        
    }

    @FXML
    private void setOnMouseClicked(MouseEvent event) {
    }

    @FXML
    private void handleShowTableRec(Event event) {
    }

    private void sidebar(MouseEvent event) {
        sidebar.setVisible(false);
    }

    @FXML
    private void showDialogAddJoueur() {

        rootJoueur.setEffect(Constants.BOX_BLUR_EFFECT);

        textJoueur.setText("Ajouter une Joueur");
        //imageContainer.toFront();
        containerAddJoueur.setVisible(true);
        btnAjouterJoueur.setDisable(false);
        btnModifierJoueur.setVisible(true);
        btnAjouterJoueur.toFront();

        dialogAddJoueur = new JFXDialogTool(containerAddJoueur, stckJoueur);
        dialogAddJoueur.show();

        dialogAddJoueur.setOnDialogClosed(ev -> {
            closeStage();
            TableViewjoueur.setDisable(false);
            rootJoueur.setEffect(null);
            containerAddJoueur.setVisible(false);
            refreshJoueur();
        });

    }

    private void Navicons(MouseEvent event) {
        sidebar.setVisible(true);
    }

    @FXML
    private void closeDialogAddJoueur() {
        if (dialogAddJoueur != null) {
            dialogAddJoueur.close();
        }
        System.out.println("Exit popup joueur");
        
        Comboid_equipe.setVisible(true);
    }

    @FXML
    private void updateJoueur(ActionEvent event) {

        
                                   int id_joueur = Integer.valueOf((TableViewjoueur.getSelectionModel().getSelectedItem().getId_joueur()));
 
        String nom_joueur = tfNomJoueur.getText();
        String prenom_joueur = tfPrenomJoueur.getText();
        
       String ville = tfvilleJoueur.getText();
       String mail = tfMailJoueur.getText();
       int numero = Integer.parseInt(tfNumJoueur.getText());
               String position = comboPosition.getSelectionModel().getSelectedItem();

       
        java.util.Date date_nais = new java.sql.Date(new java.util.Date(date_naissanceJoueur.getEditor().getText()).getTime());

       int age = Integer.parseInt(tfageJoueur.getText());

        JoueurCrud ecd = new JoueurCrud();
        
       Joueur j = new Joueur(nom_joueur, prenom_joueur, (java.sql.Date) date_nais, age,numero ,mail, ville , position);
       ecd.updateJoueur(id_joueur,j );

                refreshJoueur();
                initData();

        closeDialogAddJoueur();


        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        


        
        
        
    }

    @FXML
    private void hideDialogDeleteJoueur() {
        if (dialogDeleteJoueur != null) {
            dialogDeleteJoueur.close();
        }
    }

    @FXML
    private void supprimereJoueur(ActionEvent event) {
                int id_joueur = Integer.valueOf((TableViewjoueur.getSelectionModel().getSelectedItem().getId_joueur()));

        jcd.supprimerJoueur(id_joueur);
        refreshJoueur();
                        initData();

        hideDialogDeleteJoueur();
    }

    @FXML
    private void AjouterJoueur(ActionEvent event) throws MessagingException {

        password = Randompassword.makePassword(8);

        // int id_j=Integer.parseInt(this.id_joueur.getText());
        String nom_j = tfNomJoueur.getText();
        String prenom_j = tfPrenomJoueur.getText();

        java.util.Date date_n = new java.sql.Date(new java.util.Date(date_naissanceJoueur.getEditor().getText()).getTime());
        int age = Integer.parseInt(this.tfageJoueur.getText());

        String adresse_mail = this.tfMailJoueur.getText();
        String ville = this.tfvilleJoueur.getText();
        System.out.println(ville);
        int numero = Integer.parseInt(this.tfNumJoueur.getText());
        String position = this.comboPosition.getSelectionModel().getSelectedItem();
      //  String nom = jcd.recuperernomEquipe(conaitre_id_equipe);
conaitre_nom_equipe=this.equipe.getText();
//        Comboid_equipe.getItems().add("aaa");
//         Comboid_equipe.setValue(nom.);

        int id_equipe = jcd.recupereridEquipe(conaitre_nom_equipe);
        
              List<Joueur> joueurList = new ArrayList<>();
                      JoueurCrud ecd = new JoueurCrud();

              joueurList=ecd.afficherJoueur(conaitre_id_equipe, "junior");
                if(joueurList.size()<6)

                {
                    System.out.println(joueurList.size());

        //Integer   age=Integer.parseInt(this.age.getText());
        sendmail(adresse_mail, nom_j, prenom_j, password);

        Joueur j = new Joueur(nom_j, prenom_j, (java.sql.Date) date_n, age, numero, adresse_mail, ville, "jjj", position, conaitre_id_equipe, nomImage_Joueur, password);

        //  j.setPassword(password);
        if (age <= 18) {
            j.setCategorie("junior");
        } else {
            j.setCategorie("sunior");
        }

        System.out.println(age);

        ecd.ajouterJoueur(j);

        //Equipe e=new Equipe(nom_equipe,)
        InputStream inStream = null;
        OutputStream outStream = null;

        try {
            // InputStream inStream = null;

            File afile = new File("./src/images/" + nomImage_Joueur);
            File bfile = new File("C:/wamp64/www/images/" + nomImage_Joueur);

            inStream = new FileInputStream(afile);
            outStream = new FileOutputStream(bfile);

            byte[] buffer = new byte[1024];

            int length;
            //copy the file content in bytes
            while ((length = inStream.read(buffer)) > 0) {

                outStream.write(buffer, 0, length);

            }

            inStream.close();
            outStream.close();

            System.out.println("File is copied successful!");

        } catch (IOException e) {
            e.printStackTrace();
        }

        closeDialogAddJoueur();
        refreshJoueur();
        initData();

    }
                else System.out.println("Equipe pleine");
    
    }

    @FXML
    private void CalculerAge(ActionEvent event) {
        

        // LocalDate ld=date_naissance.getValue();
        // LocalDate date_n=(date_naissance.getValue());

        java.sql.Date date_n = java.sql.Date.valueOf(date_naissanceJoueur.getValue());

        System.out.println(date_n);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date_n);
        System.out.println("yass");
        Calendar now = Calendar.getInstance();
        Calendar dob = Calendar.getInstance();
        int year_date_naissence = cal.get(cal.YEAR);
        int month_date_naissence = cal.get(cal.MONTH);
        int day_date_naissence = cal.get(cal.DAY_OF_MONTH);
        System.out.println("yasssm");
        dob.set(year_date_naissence, month_date_naissence, day_date_naissence);
        if (dob.after(now)) {
            throw new IllegalArgumentException("Can't be born in the future");
        }
        System.out.println("yassssssmmmmm");
        int year1 = now.get(Calendar.YEAR);
        int year2 = dob.get(Calendar.YEAR);
        int age1 = year1 - year2;
        int month1 = now.get(Calendar.MONTH);
        int month2 = dob.get(Calendar.MONTH);
        if (month2 > month1) {
            age1--;
            System.out.println(age1+"ggg");
        }
        else if (month1 == month2) {
            int day1 = now.get(Calendar.DAY_OF_MONTH);
            System.out.println("hiiii");
            int day2 = dob.get(Calendar.DAY_OF_MONTH);
            System.out.println("heloooo222");
            if (day2 < day1) {
                age1--;
            }
    

            


            // age.setText(age1.getindex().toString());// try this
            // System.out.println(date_n);*/
        }
                System.out.println(age1+"aaaaa");

            // age.setText(age1.toInt());
            //  Integer.parseInt(age.setText(age1));

            tfageJoueur.setText(String.valueOf(age1));
            System.out.println("ddddddddd");

    }

    public static void closeStage() {
        if (stage != null) {
            stage.hide();
        }
    }

    @FXML
    private void handleDragOver_joueur(DragEvent event) {
        if (event.getDragboard().hasFiles()) {
            event.acceptTransferModes(TransferMode.ANY);
        }
    }

    @FXML
    private void handleDrop_joueur(DragEvent event) throws FileNotFoundException {
        List<File> files = event.getDragboard().getFiles();
        Image img = new Image(new FileInputStream(files.get(0)));
        imageview_joueur.setImage(img);
        nomImage_Joueur = files.get(0).getName();
    }

    public static void sendmail(String recepient, String nom, String prenom, String Password_joueur) throws MessagingException {
        System.out.println("preparing");
        Properties properties = new Properties();

        System.out.println("Preparing to send");

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        String myAccountEmail = "co.takwira.tunisie@gmail.com";
        String password = "limitless2021";

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            @Override
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication(myAccountEmail, password);
            }

        });

        Message message = prepareMessage(session, myAccountEmail, recepient, nom, prenom, Password_joueur);
        Transport.send(message);
        System.out.println("send secsussefully");
    }

    private static Message prepareMessage(Session session, String myAccountEmail, String recepient, String nom, String prenom, String Password_joueur) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            String body = "Vous etes le bienvenue à notre application Co-takwira Mr " + nom + " " + prenom + "\n" + "Votre mot de passe sera " + Password_joueur;
            message.setSubject("Inscription effectuée avec succées");
            message.setText(body);
            return message;

        } catch (Exception ex) {
            Logger.getLogger(HomeJoueurController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    private void showDialogDeleteJoueur() {
        rootJoueur.setEffect(Constants.BOX_BLUR_EFFECT);
        ContainerDeleteJoueur.setVisible(true);

        dialogDeleteJoueur = new JFXDialogTool(ContainerDeleteJoueur, stckJoueur);
        dialogDeleteJoueur.show();

        dialogDeleteJoueur.setOnDialogClosed(ev -> {
            TableViewjoueur.setDisable(false);
            rootJoueur.setEffect(null);
            ContainerDeleteJoueur.setVisible(false);

            refreshJoueur();

        });

    }

    @FXML
    private void handleShowJoueurs(Event event) {
        System.out.println("aaa");
        initData();
    }


    @FXML
    private void minimize_app(ActionEvent event) {
        FirstWindow.stage.setIconified(true);
    }

    @FXML
    private void NavReclamation(ActionEvent event) throws IOException {

        Parent menu = FXMLLoader.load(getClass().getResource("HomeReclamation.fxml"));
        stckJoueur.getChildren().removeAll();
        stckJoueur.getChildren().setAll(menu);
    }

    @FXML
    private void NavRate(ActionEvent event) throws IOException {

        Parent menu = FXMLLoader.load(getClass().getResource("HomeRate.fxml"));
        stckJoueur.getChildren().removeAll();
        stckJoueur.getChildren().setAll(menu);

    }

    @FXML
    private void NavCoach(ActionEvent event) throws IOException {
        Parent menu = FXMLLoader.load(getClass().getResource("HomeCoach.fxml"));
        stckJoueur.getChildren().removeAll();
        stckJoueur.getChildren().setAll(menu);
    }

    @FXML
    private void NavEntrainemet(ActionEvent event) throws IOException {
        Parent menu = FXMLLoader.load(getClass().getResource("afficheentrainement.fxml"));
        stckJoueur.getChildren().removeAll();
        stckJoueur.getChildren().setAll(menu);

    }

    @FXML
    private void NavEquipe(ActionEvent event) throws IOException {
        Parent menu = FXMLLoader.load(getClass().getResource("HomeEquipe.fxml"));
        stckJoueur.getChildren().removeAll();
        stckJoueur.getChildren().setAll(menu);
    }

    @FXML
    private void NavArticle(ActionEvent event) throws IOException {

        Parent menu = FXMLLoader.load(getClass().getResource("DisplayArticle.fxml"));
        stckJoueur.getChildren().removeAll();
        stckJoueur.getChildren().setAll(menu);

    }

    @FXML
    private void NavShop(ActionEvent event) throws IOException {
        Parent menu = FXMLLoader.load(getClass().getResource("stocks.fxml"));
        stckJoueur.getChildren().removeAll();
        stckJoueur.getChildren().setAll(menu);

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
        stckJoueur.getChildren().removeAll();
        stckJoueur.getChildren().setAll(menu);

    }

    
    
    
}

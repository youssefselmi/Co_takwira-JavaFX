/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cotakwira.gui;

import java.util.Date;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import cotakwira.util.JFXDialogTool;
import cotakwira.entities.Coach;
import cotakwira.entities.Entrainement;
import cotakwira.entities.Equipe;
import cotakwira.entities.Joueur;
import cotakwira.entities.Stade;
import cotakwira.services.CoachCrud;
import cotakwira.services.Email;
import cotakwira.services.EntrainementCrud;
import cotakwira.tools.Connection;
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
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import cotakwira.util.JFXDialogTool;
import javafx.geometry.Pos;
import org.controlsfx.control.Notifications;


/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AfficheentrainementController implements Initializable {

    @FXML
    private JFXButton goliste;
    @FXML
    private JFXButton btn_update1;
    @FXML
    private TableView<Entrainement> tabview;
    @FXML
    private TableColumn<Entrainement, Integer> id;
    @FXML
    private TableColumn<Entrainement, Integer> duree;
    @FXML
    private TableColumn<Entrainement, Date> date_entrainement;
    @FXML
    private TableColumn<Entrainement, Integer> id_coach;
    @FXML
    private TableColumn<Entrainement, Integer> id_stade;
    @FXML
    private TableColumn<Entrainement, Integer> id_equipe;
    @FXML
    private Pane PutReclamationCard;
    @FXML
    private JFXButton btn_update11;
    static Entrainement selectionedEntrainement;
    @FXML
    private JFXDatePicker tftdateupdate;
    @FXML
    private JFXComboBox<Integer> tftidequipeupdate;
    @FXML
    private JFXComboBox<Integer> tftidstadeupdate;
    @FXML
    private JFXComboBox<Integer> tftidcoachupdate;
    @FXML
    private JFXTextField tftdureeupdate;
    @FXML
    private JFXComboBox<Integer> tftidentrainementupdate;
    @FXML
    private JFXButton btn_update111;
    @FXML
    private TableColumn<Entrainement, String> nom_equipe;
    @FXML
    private TableColumn<Entrainement, String> nom_stade;
    @FXML
    private TableColumn<Entrainement, String> nom_coach;
    @FXML
    private JFXTextField tftnomequipee;
    @FXML
    private JFXTextField tftnomcoache;
    @FXML
    private JFXTextField tftnomstadee;
    @FXML
    private TableColumn<Entrainement, String> ajouterCol;
    @FXML
    private TableColumn<Entrainement, String> Typee;
    @FXML
    private JFXComboBox<String> tfttypeupdate;
    @FXML
    private TableColumn<Entrainement, String> heureentrainement;
    @FXML
    private JFXComboBox<String> tftheureupdate;
    @FXML
    private JFXButton btn_update112;
    @FXML
    private Text textRec;
    @FXML
    private JFXButton btnSaveEntrainement;
    @FXML
    private JFXButton btnCancelAddEntrainement;
    @FXML
    private JFXTextField tftid;
    @FXML
    private JFXDatePicker tftdate;
    @FXML
    private JFXTextField tftduree;
    @FXML
    private JFXComboBox<String> tftheure;
    @FXML
    private TableView<Stade> tabviewstade;
    @FXML
    private TableColumn<Equipe, Integer> id1;
    @FXML
    private JFXComboBox<?> tftgrade;
    @FXML
    private TableColumn<Coach, Integer> idcoach;
    @FXML
    private TableView<Equipe> tabviewstade1;
    @FXML
    private TableColumn<Equipe, String> nom_coach1;
    @FXML
    private TableView<Coach> tabviewcoach;
    @FXML
    private TableColumn<Coach, String> nom_coach2;
    @FXML
    private TableColumn<Coach, String> prenom_coach2;
    @FXML
    private JFXComboBox<Integer> tftidequipe;
    @FXML
    private JFXComboBox<Integer> tftidstade;
    @FXML
    private JFXComboBox<Integer> tftidcoach;
    @FXML
    private JFXTextField txtfield_nom_coach;
    @FXML
    private JFXTextField txtfield_nom_equipe;
    @FXML
    private JFXTextField txtfield_nom_stade;
    @FXML
    private JFXComboBox<String> tfttype;

    private static final Stage stage = new Stage();
    private JFXDialogTool dialogAddEntrainement;
    private JFXDialogTool dialogDeleteEntrainement;
    public static final BoxBlur BOX_BLUR_EFFECT = new BoxBlur(3, 3, 3);
    @FXML
    private JFXButton btnNewEntrainement;
    @FXML
    private TableColumn<Stade, Integer> idStade;
    @FXML
    private TableColumn<Stade, String> nom_stadee;
    @FXML
    private StackPane stckEntrainement;
    @FXML
    private AnchorPane rootEntrainement;
    @FXML
    private AnchorPane containerAddEntrainement;
    static Coach selectionedCoachh;
    static Equipe selectionedEquipe;
    static Stade selectionedStade;
    @FXML
    private AnchorPane ContainerDeleteEntrainement;
    @FXML
    private Pane Navicons;
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
        initialiseAddEntrainement();//hedheya l initialise lkont 7eteha fy controleur te3 add entrainement 

        tftheureupdate.getItems().addAll("9:00", "9:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:00", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30", "17:00", "17:30", "18:00", "18:30", "19:00", "19:30", "20:00", "20:30", "21:00", "21:30", "22:00", "22:30", "23:00");

        EntrainementCrud ec = new EntrainementCrud();
        List<Entrainement> Entrainementliste = new ArrayList<>();
        Entrainementliste = ec.afficherEntrainement();
        ObservableList<Entrainement> Liste = FXCollections.observableArrayList(Entrainementliste);

        id.setCellValueFactory(new PropertyValueFactory<Entrainement, Integer>("id_entrainement"));
        duree.setCellValueFactory(new PropertyValueFactory<Entrainement, Integer>("duree"));
        // prenom_coach.setCellValueFactory(new PropertyValueFactory<Coach,String>("prenom_coach"));
        date_entrainement.setCellValueFactory(new PropertyValueFactory<Entrainement, java.util.Date>("date_entrainement"));
        //grade.setCellValueFactory(new PropertyValueFactory<Coach,String>("grade"));
        // date_entrainement.setCellValueFactory(new PropertyValueFactory<Entrainement,java.util.Date>("date_entrainement"));
        //adresse_mail.setCellValueFactory(new PropertyValueFactory<Coach,String>("adresse_mail"));

        id_coach.setCellValueFactory(new PropertyValueFactory<Entrainement, Integer>("id_coach"));
        id_stade.setCellValueFactory(new PropertyValueFactory<Entrainement, Integer>("id_stade"));
        id_equipe.setCellValueFactory(new PropertyValueFactory<Entrainement, Integer>("id_equipe"));

        nom_equipe.setCellValueFactory(new PropertyValueFactory<Entrainement, String>("nom_equipee"));
        nom_stade.setCellValueFactory(new PropertyValueFactory<Entrainement, String>("nom_stadee"));
        nom_coach.setCellValueFactory(new PropertyValueFactory<Entrainement, String>("nom_coache"));
        Typee.setCellValueFactory(new PropertyValueFactory<Entrainement, String>("type"));
        heureentrainement.setCellValueFactory(new PropertyValueFactory<Entrainement, String>("heure"));

        //Liste.add(listecoach);
        Callback<TableColumn<Entrainement, String>, TableCell<Entrainement, String>> cellFoctory = (TableColumn<Entrainement, String> param) -> {
            // make cell containing buttons

            final TableCell<Entrainement, String> cell = new TableCell<Entrainement, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        Button ajouterPanierButton = new Button();
                        ajouterPanierButton.setText("Type entrainement");
                        ajouterPanierButton.setStyle("-fx-background-color:#4CAF50;-fx-background-radius: 5em;\n");

                        ajouterPanierButton.setOnMouseClicked((MouseEvent event) -> {

                            String type_entrainement = (String) tfttypeupdate.getSelectionModel().getSelectedItem();
                            if (type_entrainement.equals("Attaque")) {
                                //System.out.println("Entraainement d'attaque");
                                Parent home_page_parent;
                                try {
                                    home_page_parent = FXMLLoader.load(getClass().getResource("Attaque.fxml"));
                                    Scene home_page_scene = new Scene(home_page_parent);
                                    Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                    app_stage.setScene(home_page_scene);
                                    app_stage.show();
                                } catch (IOException ex) {
                                    Logger.getLogger(AfficheentrainementController.class.getName()).log(Level.SEVERE, null, ex);
                                }

                            } else if (type_entrainement.equals("Deffence")) {
                                System.out.println("Entraainement de déffence");
                                Parent home_page_parent;
                                try {
                                    home_page_parent = FXMLLoader.load(getClass().getResource("Diffense.fxml"));
                                    Scene home_page_scene = new Scene(home_page_parent);
                                    Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                    app_stage.setScene(home_page_scene);
                                    app_stage.show();
                                } catch (IOException ex) {
                                    Logger.getLogger(AfficheentrainementController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            } else {
                                System.out.println("Entraainement de milieu");
                            }
                            Parent home_page_parent;
                            try {
                                home_page_parent = FXMLLoader.load(getClass().getResource("milieu.fxml"));
                                Scene home_page_scene = new Scene(home_page_parent);
                                Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                app_stage.setScene(home_page_scene);
                                app_stage.show();
                            } catch (IOException ex) {
                                Logger.getLogger(AfficheentrainementController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        });

                        HBox managebtn = new HBox(ajouterPanierButton);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(ajouterPanierButton, new Insets(2, 2, 0, 3));

                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };
        ajouterCol.setCellFactory(cellFoctory);

        tabview.setItems(Liste);

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        Integer choix3 = tftidentrainementupdate.getSelectionModel().getSelectedItem();
        // System.out.println(choix);
        // Connection cnx = Connection.getInstance().getCnx();
        try {
            String requete = "SELECT * FROM entrainement";
            Statement st = Connection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                tftidentrainementupdate.getItems().addAll(rs.getInt(1));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

////////////////////////////////////////////////////////////////////////////////////            
        Integer choix2 = tftidcoachupdate.getSelectionModel().getSelectedItem();
        // System.out.println(choix);
        // Connection cnx = Connection.getInstance().getCnx();
        try {
            String requete = "SELECT * FROM coach";
            Statement st = Connection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                tftidcoachupdate.getItems().addAll(rs.getInt(1));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        /////////////////////////////////////////////////////////////////////////////////////       
        Integer choix4 = tftidstadeupdate.getSelectionModel().getSelectedItem();
        // System.out.println(choix);
        // Connection cnx = Connection.getInstance().getCnx();
        try {
            String requete = "SELECT * FROM stade";
            Statement st = Connection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                tftidstadeupdate.getItems().addAll(rs.getInt(1));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        /////////////////////////////////////////////////////////////////////////////////////       
        Integer choix5 = tftidequipeupdate.getSelectionModel().getSelectedItem();
        // System.out.println(choix);
        // Connection cnx = Connection.getInstance().getCnx();
        try {
            String requete = "SELECT * FROM equipe";
            Statement st = Connection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                tftidequipeupdate.getItems().addAll(rs.getInt(1));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        /////////////////////////////////////////////////////////////////////////////////////       
        tfttypeupdate.getItems().add("Attaque");
        tfttypeupdate.getItems().add("Deffence");
        tfttypeupdate.getItems().add("Milieu");

    }

    public String retournom(int id) {

        String nom = "";

        //int choix= tftidcoachupdate.getSelectionModel().getSelectedItem();
        //  System.out.println(choix);
//            
        try {

            String requetee = "SELECT nom_coach FROM coach WHERE id_coach = '" + id + "'";

            Statement st = Connection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs = st.executeQuery(requetee);

            while (rs.next()) {

                // Coach c = new Coach();
                nom = rs.getString("nom_coach");
                //  c.setId_coach(rs.getInt(2));
                // tftnomcoache.setText(String.valueOf(rs.getString(1)));

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

        return nom;

    }

    public String retournomstade(int idd) {

        String nom_stade = "";

        //int choix= tftidcoachupdate.getSelectionModel().getSelectedItem();
        //  System.out.println(choix);
//            
        try {

            String requetee = "SELECT nom_stade FROM stade WHERE id_stade = '" + idd + "'";

            Statement st = Connection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs = st.executeQuery(requetee);

            while (rs.next()) {

                // Coach c = new Coach();
                nom_stade = rs.getString("nom_stade");
                //  c.setId_coach(rs.getInt(2));
                // tftnomcoache.setText(String.valueOf(rs.getString(1)));

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

        return nom_stade;

    }

    public String retournomequipe(int idequipe) {

        String nom_equipe = "";

        //int choix= tftidcoachupdate.getSelectionModel().getSelectedItem();
        //  System.out.println(choix);
//            
        try {

            String requetee = "SELECT nom_equipe FROM equipe WHERE id_e = '" + idequipe + "'";

            Statement st = Connection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs = st.executeQuery(requetee);

            while (rs.next()) {

                // Coach c = new Coach();
                nom_equipe = rs.getString("nom_equipe");
                //  c.setId_coach(rs.getInt(2));
                // tftnomcoache.setText(String.valueOf(rs.getString(1)));

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

        return nom_equipe;

    }

    @FXML
    private void goliste(ActionEvent event) throws IOException {

        Parent home_page_parent = FXMLLoader.load(getClass().getResource("addentrainement.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);
        app_stage.show();

    }

    @FXML
    private void deletecoach(ActionEvent event) {

    }

    @FXML
    private void setOnMouseClicked(MouseEvent event) {
        selectionedEntrainement = tabview.getSelectionModel().getSelectedItem();
        //tftdureeupdate.setText(AfficheentrainementController.selectionedEntrainement.getDuree());
        //tftprenomupdate.setText(AfficheentrainementController.selectionedEntrainement.getPrenom_coach());
        tftdureeupdate.setText(String.valueOf(AfficheentrainementController.selectionedEntrainement.getDuree()));
        //tftmailupdate.setText(AfficheentrainementController.selectionedEntrainement.getAdresse_mail());
        tftidcoachupdate.setValue(AfficheentrainementController.selectionedEntrainement.getId_coach());
        tftdateupdate.setValue(AfficheentrainementController.selectionedEntrainement.getDate_entrainement().toLocalDate());
        tftidequipeupdate.setValue(AfficheentrainementController.selectionedEntrainement.getId_equipe());
        tftidstadeupdate.setValue(AfficheentrainementController.selectionedEntrainement.getId_stade());
        tftidentrainementupdate.setValue(AfficheentrainementController.selectionedEntrainement.getId_entrainement());
        tftnomequipee.setText(AfficheentrainementController.selectionedEntrainement.getNom_equipee());
        tftnomstadee.setText(AfficheentrainementController.selectionedEntrainement.getNom_stadee());
        tftnomcoache.setText(AfficheentrainementController.selectionedEntrainement.getNom_coache());
        tfttypeupdate.setValue(AfficheentrainementController.selectionedEntrainement.getType());
        tftheureupdate.setValue(AfficheentrainementController.selectionedEntrainement.getHeure());

//      
    }

    @FXML
    private void deleteentrainement(ActionEvent event) {

        showDialogDeleteEntrainement();

    }

    @FXML
    private void updateentrainement(ActionEvent event) {

        int id_entrainement = Integer.parseInt(valueOf(AfficheentrainementController.selectionedEntrainement.getId_entrainement()));
        System.out.println(id_entrainement);
        //int duree=tftdureeupdate.getText();
        int duree = Integer.parseInt(tftdureeupdate.getText());

        String type = (String) tfttypeupdate.getSelectionModel().getSelectedItem();
        String heure = (String) tftheureupdate.getSelectionModel().getSelectedItem();

 
        String nom_equipee = tftnomequipee.getText();
        String nom_stadee = tftnomstadee.getText();
        String nom_coache = tftnomcoache.getText();

        int coach = (int) tftidcoachupdate.getSelectionModel().getSelectedItem();
        int stade = (int) tftidstadeupdate.getSelectionModel().getSelectedItem();
        int equipe = (int) tftidequipeupdate.getSelectionModel().getSelectedItem();

        //float salaire=Float.parseFloat(tftsalaireupdate.getText());
        java.util.Date dtae_entrainement = new java.sql.Date(new java.util.Date(tftdateupdate.getEditor().getText()).getTime());
        //java.util.Date date_fin_contrat = new java.sql.Date(new java.util.Date(tftdatefincontratupdate.getEditor().getText()).getTime());
        Entrainement e = new Entrainement(id_entrainement, (java.sql.Date) dtae_entrainement, heure, duree, coach, stade, equipe, nom_equipee, nom_stadee, nom_coache, type);
        EntrainementCrud ec = new EntrainementCrud();
        ec.updateEntrainement(e);
        refresh();

    }

    private void refresh() {
        EntrainementCrud ec = new EntrainementCrud();

        List<Entrainement> Entrainementliste = new ArrayList<>();

        Entrainementliste = ec.afficherEntrainement();

        ObservableList<Entrainement> Liste = FXCollections.observableArrayList(Entrainementliste);

        id.setCellValueFactory(new PropertyValueFactory<Entrainement, Integer>("id_entrainement"));
        duree.setCellValueFactory(new PropertyValueFactory<Entrainement, Integer>("duree"));
        // prenom_coach.setCellValueFactory(new PropertyValueFactory<Coach,String>("prenom_coach"));
        date_entrainement.setCellValueFactory(new PropertyValueFactory<Entrainement, java.util.Date>("date_entrainement"));
        //grade.setCellValueFactory(new PropertyValueFactory<Coach,String>("grade"));
        // date_entrainement.setCellValueFactory(new PropertyValueFactory<Entrainement,java.util.Date>("date_entrainement"));
        //adresse_mail.setCellValueFactory(new PropertyValueFactory<Coach,String>("adresse_mail"));

        id_coach.setCellValueFactory(new PropertyValueFactory<Entrainement, Integer>("id_coach"));
        id_stade.setCellValueFactory(new PropertyValueFactory<Entrainement, Integer>("id_stade"));
        id_equipe.setCellValueFactory(new PropertyValueFactory<Entrainement, Integer>("id_equipe"));

        nom_equipe.setCellValueFactory(new PropertyValueFactory<Entrainement, String>("nom_equipee"));
        nom_stade.setCellValueFactory(new PropertyValueFactory<Entrainement, String>("nom_stadee"));
        nom_coach.setCellValueFactory(new PropertyValueFactory<Entrainement, String>("nom_coache"));
        Typee.setCellValueFactory(new PropertyValueFactory<Entrainement, String>("type"));
        heureentrainement.setCellValueFactory(new PropertyValueFactory<Entrainement, String>("heure"));

        //Liste.add(listecoach);
        tabview.setItems(Liste);

    }
    
    @FXML
    private void retournomcoach(ActionEvent event) {
        int coach = (int) tftidcoachupdate.getSelectionModel().getSelectedItem();
        String ch = retournom(coach);
        tftnomcoache.setText(ch);

    }

    @FXML
    private void retournomstade(ActionEvent event) {
        int stade = (int) tftidstadeupdate.getSelectionModel().getSelectedItem();
        String ch = retournomstade(stade);
        tftnomstadee.setText(ch);
    }

    @FXML
    private void retournomequipe(ActionEvent event) {
        int equipe = (int) tftidequipeupdate.getSelectionModel().getSelectedItem();
        String ch = retournomequipe(equipe);
        tftnomequipee.setText(ch);
    }

    @FXML
    private void statentrainement(ActionEvent event) throws IOException {
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("statentrainement.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);
        app_stage.show();

    }

    @FXML
    private void newEntrainement(MouseEvent event) {

        int id_entrainement = Integer.parseInt(tftid.getText());
        int duree = Integer.parseInt(tftduree.getText());
        //int id_coach=Integer.parseInt(valueOf(AddentrainementController.selectionedEntrainement.getId_coach())); 
        // int id_stade=Integer.parseInt(valueOf(AddentrainementController.selectionedEntrainement.getId_Stade())); 

        Integer id_coach = (Integer) tftidcoach.getSelectionModel().getSelectedItem();
        Integer id_stade = (Integer) tftidstade.getSelectionModel().getSelectedItem();
        Integer id_equipe = (Integer) tftidequipe.getSelectionModel().getSelectedItem();

        //String date_naissance=tftdatefincontrat.getTypeSelector();
        //String =tftdatefincontrat.getTypeSelector();
        Date date_entrainement = new java.sql.Date(new Date(tftdate.getEditor().getText()).getTime());

        //  Date date_fin_contrat = new java.sql.Date(new Date(tftdatefincontrat.getEditor().getText()).getTime());
        String type = tfttype.getSelectionModel().getSelectedItem();

        String heure = tftheure.getSelectionModel().getSelectedItem();

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////  
        String cq = "";

        try {
            String requete = "SELECT nom_equipe FROM equipe where id_e= '" + id_equipe + "' ";
            Statement st = Connection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {

                Equipe c = new Equipe();
//               
                cq = rs.getString("nom_equipe");
                c.setNom_equipe(rs.getString("nom_equipe"));
                c.setId_equipe(rs.getInt(2));
                txtfield_nom_equipe.setText(String.valueOf(rs.getString(1)));

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        /////////////////////////////////////////////////////////////////////////////////////////////                 

        String cq2 = "";
        String maill="";

        try {
            String requete = "SELECT nom_coach,adresse_mail FROM coach where id_coach= '" + id_coach + "' ";
            Statement st = Connection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {

                Coach cc = new Coach();
//               
                cq2 = rs.getString("nom_coach");
                maill = rs.getString("adresse_mail");
                cc.setNom_coach(rs.getString("nom_coach"));
                cc.setAdresse_mail(rs.getString("adresse_mail"));
                cc.setId_coach(rs.getInt(3));
                // nom_equipee.setText(String.valueOf(rs.getString(1)));

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        //////////////////////////////////////////////////////////////////////////////
        String cq3 = "";

        try {
            String requete = "SELECT nom_stade FROM stade where id_stade= '" + id_stade + "' ";
            Statement st = Connection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {

                Stade cc = new Stade();
//               
                cq3 = rs.getString("nom_stade");
                cc.setNom_stade(rs.getString("nom_stade"));
                cc.setId_stade(rs.getInt(2));
                // nom_equipee.setText(String.valueOf(rs.getString(1)));

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        ///////////////////////////////////////////////////////////////////////////////////////////////////////        
        int err = 0;

        List<Entrainement> entrainementtab = new ArrayList<>();
        try {
            String requete = "SELECT * FROM entrainement";
            Statement st = Connection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Entrainement e = new Entrainement();
                e.setId_entrainement(rs.getInt(1));
                e.setDuree(rs.getInt(2));

                e.setDate_entrainement(rs.getDate(3));
                e.setHeure(rs.getString(4));

                e.setId_equipe(rs.getInt(7));
                e.setId_coach(rs.getInt(5));
                e.setId_stade(rs.getInt(6));
                e.setNom_equipee(rs.getString(8));
                e.setNom_stadee(rs.getString(9));
                e.setNom_coache(rs.getString(10));
                e.setType(rs.getString(11));

                entrainementtab.add(e);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        for (int i = 0; i < entrainementtab.size(); i++) {
            if (id_coach == entrainementtab.get(i).getId_coach() && entrainementtab.get(i).getDate_entrainement().compareTo(date_entrainement) == 0) {
                System.out.println("coach deja a une entrainement dans cette date");
                Notifications.create()
                    .title(" Entrainement")
                    .text("coach deja a une entrainement dans cette date")
                    .darkStyle().position(Pos.TOP_LEFT).showWarning();

                err++;
            }
        }

        /////////////////////////////////////////////////////////////////////////////////////////////////////////////           
        if (err == 0) {
            Entrainement e = new Entrainement(id_entrainement, (java.sql.Date) date_entrainement, heure, duree, id_coach, id_stade, id_equipe, cq, cq3, cq2, type);

            EntrainementCrud ec = new EntrainementCrud();
            ec.ajouterEntrainement(e);

            String Sujet = "Bonjour Mr " + cq2 + "Vous avez une entrainement de type " + type + " planifié a stade " + cq3 + " dans la date  " + date_entrainement + " avev l'equipe  " + cq + "     \n Cordialment";
            try {
                Email.sendmail(maill, Sujet, "Nouvelle Entrainement");
            } catch (Exception ex) {
               // Logger.getLogger(AddentrainementController.class.getName()).log(Level.SEVERE, null, ex);
            }

            List<Joueur> joueurtab = new ArrayList<>();

            try {
                String requete = "SELECT * FROM joueur ";
                Statement st = Connection.getInstance().getCnx()
                        .createStatement();
                ResultSet rs = st.executeQuery(requete);
                while (rs.next()) {

                    Joueur j = new Joueur();

                    j.setId_joueur(rs.getInt("id_joueur"));
                    j.setAdresse_mail(rs.getString("adresse_mail"));
                    j.setId_equipe(rs.getInt("id_e"));
                    joueurtab.add(j);

                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }

            for (int k = 0; k < joueurtab.size(); k++) {
                if (joueurtab.get(k).getId_equipe() == id_equipe) {

                    System.out.println(id_equipe);
                    String Sujettt = "Bonjour Mr votre entrainement sera dans  avec l equipe " + cq + "   \n Cordialment";
                    try {
                        Email.sendmail(joueurtab.get(k).getAdresse_mail(), Sujettt, "Nouvelle Entrainement");
                    } catch (Exception ex) {
                       // Logger.getLogger(AddentrainementController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }

        } else {
            System.out.println("verifier les champs");
        }

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////  
        
        closeDialogAddentrainement();
    }

    @FXML
    private void closeDialogAddEntrainement(MouseEvent event) {
        if (dialogAddEntrainement != null) {
            dialogAddEntrainement.close();
        }
        System.out.println("Exit popup Entrainement");
    }

    @FXML
    private void closeDialogAddentrainement() {
        if (dialogAddEntrainement != null) {
            dialogAddEntrainement.close();
        }
        System.out.println("Exit popup Entrainement");
    }

    @FXML
    private void setOnMouseClicked1(MouseEvent event) {
        selectionedStade = tabviewstade.getSelectionModel().getSelectedItem();
        tftidstade.setValue(AfficheentrainementController.selectionedStade.getId_stade());
        txtfield_nom_stade.setText(AfficheentrainementController.selectionedStade.getNom_stade());

    }

    @FXML
    private void setOnMouseClickedCoach(MouseEvent event) {
        selectionedCoachh = tabviewcoach.getSelectionModel().getSelectedItem();
        tftidcoach.setValue(AfficheentrainementController.selectionedCoachh.getId_coach());
        txtfield_nom_coach.setText(AfficheentrainementController.selectionedCoachh.getNom_coach());

    }

    @FXML
    private void setOnMouseClickedequipe(MouseEvent event) {
        selectionedEquipe = tabviewstade1.getSelectionModel().getSelectedItem();
        tftidequipe.setValue(AfficheentrainementController.selectionedEquipe.getId_equipe());
        txtfield_nom_equipe.setText(AfficheentrainementController.selectionedEquipe.getNom_equipe());

    }
        public static void closeStage() {
        if (stage != null) {
            stage.hide();
        }
    }

    @FXML
    private void showDialogAddEntrainement(MouseEvent event) {

        rootEntrainement.setEffect(BOX_BLUR_EFFECT);

        containerAddEntrainement.setVisible(true);
        btnSaveEntrainement.setDisable(false);
        btnSaveEntrainement.toFront();

        dialogAddEntrainement = new JFXDialogTool(containerAddEntrainement, stckEntrainement);
        dialogAddEntrainement.show();

        dialogAddEntrainement.setOnDialogClosed(ev -> {
            closeStage();
            tabview.setDisable(false);
            rootEntrainement.setEffect(null);
            containerAddEntrainement.setVisible(false);
            refresh();
        });

    }

    private void initialiseAddEntrainement() //hedheya l initialise lkont 7eteha fy controleur te3 add entrainement 
    {

        tftheure.getItems().addAll("9:00", "9:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:00", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30", "17:00", "17:30", "18:00", "18:30", "19:00", "19:30", "20:00", "20:30", "21:00", "21:30", "22:00", "22:30", "23:00");

        CoachCrud ec = new CoachCrud();
        List<Stade> Entrainementliste = new ArrayList<>();
        Entrainementliste = ec.afficherstade();
        ObservableList<Stade> Liste = FXCollections.observableArrayList(Entrainementliste);

        idStade.setCellValueFactory(new PropertyValueFactory<Stade, Integer>("id_stade"));
        nom_stadee.setCellValueFactory(new PropertyValueFactory<Stade, String>("nom_stade"));
        // prenom_coach.setCellValueFactory(new PropertyValueFactory<Coach,String>("prenom_coach"));
        tabviewstade.setItems(Liste);

        CoachCrud ec1 = new CoachCrud();
        List<Coach> coachlisteee = new ArrayList<>();
        coachlisteee = ec1.afficherCoachs();
        ObservableList<Coach> Listee = FXCollections.observableArrayList(coachlisteee);

        idcoach.setCellValueFactory(new PropertyValueFactory<Coach, Integer>("id_coach"));
        nom_coach2.setCellValueFactory(new PropertyValueFactory<Coach, String>("nom_coach"));
        prenom_coach2.setCellValueFactory(new PropertyValueFactory<Coach, String>("prenom_coach"));

        // prenom_coach.setCellValueFactory(new PropertyValueFactory<Coach,String>("prenom_coach"));
        tabviewcoach.setItems(Listee);

        CoachCrud ec2 = new CoachCrud();
        List<Equipe> equipelisteee = new ArrayList<>();
        equipelisteee = ec2.afficherequipe();
        ObservableList<Equipe> Listeee = FXCollections.observableArrayList(equipelisteee);

        id1.setCellValueFactory(new PropertyValueFactory<Equipe, Integer>("id_equipe"));
        nom_coach1.setCellValueFactory(new PropertyValueFactory<Equipe, String>("nom_equipe"));
        // prenom_coach2.setCellValueFactory(new PropertyValueFactory<Coach,String>("prenom_coach"));

        // prenom_coach.setCellValueFactory(new PropertyValueFactory<Coach,String>("prenom_coach"));
        tabviewstade1.setItems(Listeee);

        // TODO
        //////////////////////////////////////////////////////////////////
        Integer choix = tftidcoach.getSelectionModel().getSelectedItem();
        // System.out.println(choix);
        // Connection cnx = Connection.getInstance().getCnx();
        try {
            String requete = "SELECT * FROM coach";
            Statement st = Connection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                tftidcoach.getItems().addAll(rs.getInt(1));
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        /////////////////////////////////////////////////////////////////////
        Integer choix2 = tftidstade.getSelectionModel().getSelectedItem();
        // System.out.println(choix);
        // Connection cnx = Connection.getInstance().getCnx();
        try {
            String requete = "SELECT * FROM stade";
            Statement st = Connection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                tftidstade.getItems().addAll(rs.getInt(1));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        /////////////////////////////////////////////////////////////////////
        Integer choix3 = tftidequipe.getSelectionModel().getSelectedItem();
        // System.out.println(choix);
        // Connection cnx = Connection.getInstance().getCnx();
        try {
            String requete = "SELECT * FROM equipe";
            Statement st = Connection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                tftidequipe.getItems().addAll(rs.getInt(1));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        /////////////////////////////////////////////////////////////////////

        tfttype.getItems().add("Attaque");
        tfttype.getItems().add("Deffence");
        tfttype.getItems().add("Milieu");

    }

    @FXML
    private void hideDialogDeleteEntrainement(MouseEvent event) {
        if (dialogDeleteEntrainement != null) {
            dialogDeleteEntrainement.close();
        }

    }

    @FXML
    private void deleteEntrainement(MouseEvent event) {

        int id_entrainement = Integer.parseInt(valueOf(AfficheentrainementController.selectionedEntrainement.getId_entrainement()));
        System.out.println(id_entrainement);
        //int duree=tftdureeupdate.getText();
        int duree = Integer.parseInt(tftdureeupdate.getText());

        //String id_equipe=tftidequipeupdate.getText();
        //String nom_coach=tftnomupdate.getText();
        //String mail=tftmailupdate.getText();
        //String date_naissance=tftdatefincontrat.getTypeSelector();
        //String =tftdatefincontrat.getTypeSelector();
        int coach = (int) tftidcoachupdate.getSelectionModel().getSelectedItem();
        int stade = (int) tftidstadeupdate.getSelectionModel().getSelectedItem();
        int equipe = (int) tftidequipeupdate.getSelectionModel().getSelectedItem();

        //float salaire=Float.parseFloat(tftsalaireupdate.getText());
        java.util.Date dtae_entrainement = new java.sql.Date(new java.util.Date(tftdateupdate.getEditor().getText()).getTime());
        //java.util.Date date_fin_contrat = new java.sql.Date(new java.util.Date(tftdatefincontratupdate.getEditor().getText()).getTime());
        Entrainement e = new Entrainement(id_entrainement, (java.sql.Date) dtae_entrainement, "", duree, coach, stade, equipe, "", "", "", "");
        EntrainementCrud ec = new EntrainementCrud();
        ec.supprimerEntrainement(e);

        refresh();
        hideDialogDeleteentrainement();

    }

    @FXML
    private void hideDialogDeleteentrainement() {
        if (dialogDeleteEntrainement != null) {
            dialogDeleteEntrainement.close();
        }

    }

    private void showDialogDeleteEntrainement() {
        rootEntrainement.setEffect(BOX_BLUR_EFFECT);
        ContainerDeleteEntrainement.setVisible(true);

        dialogDeleteEntrainement = new JFXDialogTool(ContainerDeleteEntrainement, stckEntrainement);
        dialogDeleteEntrainement.show();

        dialogDeleteEntrainement.setOnDialogClosed(ev -> {
            tabview.setDisable(false);
            rootEntrainement.setEffect(null);
            ContainerDeleteEntrainement.setVisible(false);
            refresh();

        });

    }


    @FXML
    private void minimize_app(ActionEvent event) {
        FirstWindow.stage.setIconified(true);
    }

    @FXML
    private void NavReclamation(ActionEvent event) throws IOException {

        Parent menu = FXMLLoader.load(getClass().getResource("HomeReclamation.fxml"));
        stckEntrainement.getChildren().removeAll();
        stckEntrainement.getChildren().setAll(menu);
    }

    @FXML
    private void NavRate(ActionEvent event) throws IOException {

        Parent menu = FXMLLoader.load(getClass().getResource("HomeRate.fxml"));
        stckEntrainement.getChildren().removeAll();
        stckEntrainement.getChildren().setAll(menu);

    }

    @FXML
    private void NavCoach(ActionEvent event) throws IOException {
        Parent menu = FXMLLoader.load(getClass().getResource("HomeCoach.fxml"));
        stckEntrainement.getChildren().removeAll();
        stckEntrainement.getChildren().setAll(menu);
    }

    @FXML
    private void NavEntrainemet(ActionEvent event) throws IOException {
        Parent menu = FXMLLoader.load(getClass().getResource("afficheentrainement.fxml"));
        stckEntrainement.getChildren().removeAll();
        stckEntrainement.getChildren().setAll(menu);

    }

    @FXML
    private void NavEquipe(ActionEvent event) throws IOException {
        Parent menu = FXMLLoader.load(getClass().getResource("HomeEquipe.fxml"));
        stckEntrainement.getChildren().removeAll();
        stckEntrainement.getChildren().setAll(menu);
    }

    @FXML
    private void NavArticle(ActionEvent event) throws IOException {

        Parent menu = FXMLLoader.load(getClass().getResource("DisplayArticle.fxml"));
        stckEntrainement.getChildren().removeAll();
        stckEntrainement.getChildren().setAll(menu);

    }

    @FXML
    private void NavShop(ActionEvent event) throws IOException {
        Parent menu = FXMLLoader.load(getClass().getResource("stocks.fxml"));
        stckEntrainement.getChildren().removeAll();
        stckEntrainement.getChildren().setAll(menu);

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
        stckEntrainement.getChildren().removeAll();
        stckEntrainement.getChildren().setAll(menu);

    }


}

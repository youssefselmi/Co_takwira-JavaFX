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
import cotakwira.util.JFXDialogTool;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import cotakwira.entities.Coach;
import cotakwira.services.CoachCrud;
import cotakwira.services.Email;
import cotakwira.services.EmailCoach;
import cotakwira.tools.Connection;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import static java.lang.String.valueOf;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.util.Callback;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javax.swing.JFileChooser;
import org.controlsfx.control.Notifications;

//import static takwira.AddcoachController.agee;
//import static takwira.TestController.selectionedCoach;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class HomeCoachController implements Initializable {

    @FXML
    private StackPane stckCoach;
    @FXML
    private AnchorPane rootCoach;
    @FXML
    private Pane PaneRec;
    @FXML
    private TableView<Coach> tabview;
    @FXML
    private TableColumn<Coach, Integer> id;
    @FXML
    private TableColumn<Coach, String> nom_coach;
    @FXML
    private TableColumn<Coach, String> prenom_coach;
    @FXML
    private TableColumn<Coach, java.util.Date> date_naissance;
    @FXML
    private TableColumn<Coach, String> grade;
    @FXML
    private TableColumn<Coach, java.util.Date> date_fin_contrat;
    @FXML
    private TableColumn<Coach, String> adresse_mail;
    @FXML
    private TableColumn<Coach, Float> salaire;
    @FXML
    private AnchorPane containerAddCoach;
    @FXML
    private Text textRec;
    @FXML
    private JFXButton btnSaveCoach;
    @FXML
    private JFXButton btnCancelAddCoach;
    @FXML
    private JFXButton btnUpdateCoach;
    private JFXComboBox<String> formation;
    @FXML
    private AnchorPane ContainerDeleteCoach;
    @FXML
    private TableColumn<Coach, Integer> nb1;
    @FXML
    private TableColumn<Coach, Integer> nb2;
    @FXML
    private TableColumn<Coach, String> Formation;
    @FXML
    private TableColumn<Coach, ImageView> imageCol;
    @FXML
    private TableColumn<Coach, ImageView> imageformationCol;
    private JFXComboBox<Integer> tftidcoachupdate;
    @FXML
    private TableColumn<Coach, String> ActionCoach;
    @FXML
    private JFXButton btnNewCoach;
    private JFXDialogTool dialogAddCoach;
    private JFXDialogTool dialogDeleteCoach;
    public static final BoxBlur BOX_BLUR_EFFECT = new BoxBlur(3, 3, 3);
    private static final Stage stage = new Stage();
    @FXML
    private JFXTextField tftnom;
    @FXML
    private JFXTextField tftprenom;
    @FXML
    private JFXDatePicker tftdatenaissance;
    @FXML
    private JFXTextField tftmail;
    @FXML
    private JFXTextField txtfieldage;
    @FXML
    private JFXTextField nbtr1;
    @FXML
    private JFXTextField nbtr2;
    @FXML
    private JFXTextField tfImagecoach;
    @FXML
    private JFXTextField tfImagecoach2;
    @FXML
    private JFXTextField tftsalaire;
    @FXML
    private JFXDatePicker tftdatefincontrat;
    @FXML
    private ImageView imageview;
    @FXML
    private ImageView imageview1;
    @FXML
    private ImageView imageview2;
    @FXML
    private JFXComboBox<String> Comboformation_coach;
    @FXML
    private JFXComboBox<String> tftgrade;

    static Integer agee = 0;
    @FXML
    private Button btnuploadCoach;
    @FXML
    private Button btnuploadFormation;
         static Coach selectionedCoach;
    @FXML
    private Pane Navicons1;
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

        long millis = System.currentTimeMillis();
//        java.sql.Date date = new java.sql.Date(millis);
//        
//        try {
//            String requete = "DELETE FROM coach where date_fin_contrat <= '"+date+"' ";
//            
//           
//             Statement st = Connection.getInstance().getCnx()
//                    .createStatement();
//            st.executeUpdate(requete);
//            
//            
//            System.out.println("Tableau coaxh a ete mis a jour");
//            
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }

// TODO
        //  tftgrade.getItems().addAll("BBEE", "BBEEF", "BBEEFF");
        tftgrade.getItems().add("b0");
        tftgrade.getItems().add("b1");
        tftgrade.getItems().add("b2");
        tftgrade.getItems().add("b3");

        Comboformation_coach.getItems().add("442 Offensif");
        Comboformation_coach.getItems().add("442 Deffensif");
        Comboformation_coach.getItems().add("433 Deffensif");
        Comboformation_coach.getItems().add("433 Deffensif");

        CoachCrud pcd = new CoachCrud();
        List<Coach> listecoach = new ArrayList<>();

        listecoach = pcd.afficherCoachs();

        ObservableList<Coach> Liste = FXCollections.observableArrayList(listecoach);

        id.setCellValueFactory(new PropertyValueFactory<Coach, Integer>("id_coach"));
        nom_coach.setCellValueFactory(new PropertyValueFactory<Coach, String>("nom_coach"));
        // prenom_coach.setCellValueFactory(new PropertyValueFactory<Coach,String>("prenom_coach"));

        prenom_coach.setCellValueFactory(new PropertyValueFactory<Coach, String>("prenom_coach"));

        date_naissance.setCellValueFactory(new PropertyValueFactory<Coach, java.util.Date>("date_naissance"));
        grade.setCellValueFactory(new PropertyValueFactory<Coach, String>("grade"));
        date_fin_contrat.setCellValueFactory(new PropertyValueFactory<Coach, java.util.Date>("date_fin_contrat"));
        adresse_mail.setCellValueFactory(new PropertyValueFactory<Coach, String>("adresse_mail"));
        salaire.setCellValueFactory(new PropertyValueFactory<Coach, Float>("salaire"));
        nb1.setCellValueFactory(new PropertyValueFactory<Coach, Integer>("nb_trophe_locaux"));
        nb2.setCellValueFactory(new PropertyValueFactory<Coach, Integer>("nb_trophe_internationaux"));
        Formation.setCellValueFactory(new PropertyValueFactory<Coach, String>("formation_prefere"));

        imageCol.setCellValueFactory(new PropertyValueFactory<>("imagedisplay"));
        imageformationCol.setCellValueFactory(new PropertyValueFactory<>("imagedisplayformation"));

        //Liste.add(listecoach);
        tabview.setItems(Liste);

        /////////////////////////////////////////////////////////////////////////////////////////////
        refresh();
    }

    private void refresh() {
        CoachCrud pcd = new CoachCrud();
        List<Coach> listecoach = new ArrayList<>();

        listecoach = pcd.afficherCoachs();

        ObservableList<Coach> Liste = FXCollections.observableArrayList(listecoach);

        id.setCellValueFactory(new PropertyValueFactory<Coach, Integer>("id_coach"));
        nom_coach.setCellValueFactory(new PropertyValueFactory<Coach, String>("nom_coach"));
        prenom_coach.setCellValueFactory(new PropertyValueFactory<Coach, String>("prenom_coach"));
        date_naissance.setCellValueFactory(new PropertyValueFactory<Coach, java.util.Date>("date_naissance"));
        grade.setCellValueFactory(new PropertyValueFactory<Coach, String>("grade"));
        date_fin_contrat.setCellValueFactory(new PropertyValueFactory<Coach, java.util.Date>("date_fin_contrat"));
        adresse_mail.setCellValueFactory(new PropertyValueFactory<Coach, String>("adresse_mail"));
        salaire.setCellValueFactory(new PropertyValueFactory<Coach, Float>("salaire"));
        nb1.setCellValueFactory(new PropertyValueFactory<Coach, Integer>("nb_trophe_locaux"));
        nb2.setCellValueFactory(new PropertyValueFactory<Coach, Integer>("nb_trophe_internationaux"));
        Formation.setCellValueFactory(new PropertyValueFactory<Coach, String>("formation_prefere"));
        imageCol.setCellValueFactory(new PropertyValueFactory<>("imagedisplay"));
        imageformationCol.setCellValueFactory(new PropertyValueFactory<>("imagedisplayformation"));
        
        //Liste.add(listecoach);
        tabview.setItems(Liste);

        //add cell of button edit 
        Callback<TableColumn<Coach, String>, TableCell<Coach, String>> cellFoctory = (TableColumn<Coach, String> param) -> {
            //make cell containing buttons
            final TableCell<Coach, String> cell = new TableCell<Coach, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    // that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
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
                            System.out.println("icon delete is pressed !");

                            int idcoach = Integer.valueOf((tabview.getSelectionModel().getSelectedItem().getId_coach()));
                            System.out.println("id selectioned is !" + idcoach);

                            showDialogDeleteCoach();
                        });
                        editIcon.setOnMouseClicked((MouseEvent event) -> {
                            System.out.println("icon edit is pressed !");
                            tftgrade.setVisible(true);
                            imageview.setVisible(false);
                            imageview2.setVisible(false);
                            imageview1.setVisible(false);
                            txtfieldage.setVisible(false);

                            btnuploadCoach.setVisible(false);
                            btnuploadFormation.setVisible(false);
                            tfImagecoach.setVisible(false);
                            tfImagecoach2.setVisible(false);

                            textRec.setText("Modifier Coach");

                            int idcoach = Integer.valueOf((tabview.getSelectionModel().getSelectedItem().getId_coach()));
                            System.out.println("id selectioned is !" + idcoach);

                            showDialogAddCoach();
                            btnUpdateCoach.toFront();

                            tftnom.setText(tabview.getSelectionModel().getSelectedItem().getNom_coach());
                            tftprenom.setText(tabview.getSelectionModel().getSelectedItem().getPrenom_coach());
                            tftsalaire.setText(String.valueOf(tabview.getSelectionModel().getSelectedItem().getSalaire()));
                            tftmail.setText(tabview.getSelectionModel().getSelectedItem().getAdresse_mail());
                            tftdatenaissance.setValue(tabview.getSelectionModel().getSelectedItem().getDate_naissance().toLocalDate());
                            tftdatefincontrat.setValue(tabview.getSelectionModel().getSelectedItem().getDate_fin_contrat().toLocalDate());
                            tftgrade.setValue(tabview.getSelectionModel().getSelectedItem().getGrade());
                            nbtr1.setText(Integer.toString(tabview.getSelectionModel().getSelectedItem().getNb_trophe_locaux()));
                            nbtr2.setText(Integer.toString(tabview.getSelectionModel().getSelectedItem().getNb_trophe_internationaux()));

                            Comboformation_coach.setValue(tabview.getSelectionModel().getSelectedItem().getFormation_prefere());

                        });

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
        ActionCoach.setCellFactory(cellFoctory);
        // TableView.getItems().clear();
        tabview.setItems(Liste);
    }

    @FXML
    private void setOnMouseClicked(MouseEvent event) {
               selectionedCoach = tabview.getSelectionModel().getSelectedItem();

               tftgrade.setValue(HomeCoachController.selectionedCoach.getGrade());
               
                String gradee=(String) tftgrade.getSelectionModel().getSelectedItem();
 
       if(gradee.equals("b1"))
        {
         // tftgrade.getItems().remove("b1");
            tftgrade.getItems().remove("b0");


        }
     if(gradee.equals("b2"))
        {
          //tftgrade.getItems().remove("b2");
          tftgrade.getItems().remove("b1");
          tftgrade.getItems().remove("b0");

        }
       if(gradee.equals("b3"))
        {
          tftgrade.getItems().remove("b1");
          tftgrade.getItems().remove("b2");
          tftgrade.getItems().remove("b0");
                    tftgrade.getItems().remove("b3");

          tftgrade.getItems().add("grade maximum atteinte");

         //tftgrade.getItems().remove("b3");
        }
    
       
               

    }

    @FXML
    private void handleShowTableRec(Event event) {
    }



    @FXML
    private void newCoach(MouseEvent event) { //Action Button ajouter Coach

        String motdepasse = "";

//        int id_coach=Integer.parseInt(tftid_coach.getText());
        String nom_coach = tftnom.getText();
        String prenom_coach = tftprenom.getText();
        String mail = tftmail.getText();
//String date_naissance=tftdatefincontrat.getTypeSelector();
//String =tftdatefincontrat.getTypeSelector();
//  String grade=(String) tftgrade.getSelectionModel().getSelectedItem();

        float salaire = Float.parseFloat(tftsalaire.getText());
        Date date_naissance = new java.sql.Date(new Date(tftdatenaissance.getEditor().getText()).getTime());

        Date date_fin_contrat = new java.sql.Date(new Date(tftdatefincontrat.getEditor().getText()).getTime());

        int nbtr1 = Integer.parseInt(this.nbtr1.getText());
        int nbtr2 = Integer.parseInt(this.nbtr2.getText());
        String s = Comboformation_coach.getSelectionModel().getSelectedItem();
        String image_coach = tfImagecoach.getText();
        String image_formation = tfImagecoach2.getText();

////////////////////////////////////////////////////////////
        Date date = new Date();
        int year = Integer.valueOf(String.format("%1$tY", date));
        System.out.println(year);

        int year1 = Integer.valueOf(String.format("%1$tY", date_naissance));
        System.out.println(year1 - 1);
// static Integer age=0;

        int age;
        age = year - year1;
        System.out.println(year - year1);

//agee=age;
/////////////////////////////////////////////////////////////////////////////////////////
        Random rand = new Random();

        // Generate random integers in range 0 to 999
        int rand_int1 = rand.nextInt(1000);

        motdepasse = nom_coach.substring(0, 2).toUpperCase() + prenom_coach.substring(0, 2).toLowerCase() + rand_int1;
        System.out.println(motdepasse);

////////////////////////////////////////////////////////////////////////
        int err = 0;
        if (mail.equals("")) {
            Notifications.create()
                    .title("Erreur Ajout Coach")
                    .text("Verifier le champs Email")
                    .darkStyle().position(Pos.TOP_LEFT).showWarning();
            err++;
        }

        if (err == 0) {

            Coach c = new Coach(nom_coach, prenom_coach, (java.sql.Date) date_naissance, "b0", (java.sql.Date) date_fin_contrat, mail, salaire, nbtr1, nbtr2, s, image_formation, image_coach, age, motdepasse);
            CoachCrud cc = new CoachCrud();
            cc.ajouterCoach(c);
            Notifications.create()
                    .title(" Ajout Coach")
                    .text("Coach Ajouté Avec Succes")
                    .darkStyle().position(Pos.TOP_LEFT).showWarning();
        }
// else
//     Notifications.create()
//         .title("Erreur Ajout Coach")
//         .text("Verifier les Champs")
//         
//         .darkStyle().position(Pos.TOP_LEFT).showWarning();

        String Sujet = "Bienvenue Mr " + nom_coach + " " + prenom_coach + " Dans Co_Takwira \n   Votre Contrat avec Co_Takwira expire en   " + date_fin_contrat + "\n Cordialment";
        try {
            EmailCoach.sendmailtocoach(mail, Sujet, "Bienvenue Dans Co_Takwwira");
        } catch (Exception ex) {
          //  Logger.getLogger(AddcoachController.class.getName()).log(Level.SEVERE, null, ex);
        }

        refresh();
        closeDialogAddCoach();

    }

    @FXML
    private void closeDialogAddCoach() {

        if (dialogAddCoach != null) {
            dialogAddCoach.close();
        }
        System.out.println("Exit popup Coach");

        imageview.setVisible(true);
        imageview2.setVisible(true);
        imageview1.setVisible(true);
        txtfieldage.setVisible(true);

        btnuploadCoach.setVisible(true);
        btnuploadFormation.setVisible(true);
        tfImagecoach.setVisible(true);
        tfImagecoach2.setVisible(true);

    }

    @FXML
    private void updateCoach(MouseEvent event) {

        // initialize(url, rb);
        // int id_coach=Integer.parseInt(valueOf(TestController.selectionedCoach.getId_coach())); 
        // int id_coach=(int) tftidcoachupdate.getSelectionModel().getSelectedItem();
        int id_coach = Integer.valueOf((tabview.getSelectionModel().getSelectedItem().getId_coach()));

        System.out.println(id_coach);
        String prenom_coach = tftprenom.getText();
        String nom_coach = tftnom.getText();
        String mail = tftmail.getText();
        //String date_naissance=tftdatefincontrat.getTypeSelector();
        //String =tftdatefincontrat.getTypeSelector();
        String grade = (String) tftgrade.getSelectionModel().getSelectedItem();

        float salaire = Float.parseFloat(tftsalaire.getText());
        java.util.Date date_naissance = new java.sql.Date(new java.util.Date(tftdatenaissance.getEditor().getText()).getTime());
        java.util.Date date_fin_contrat = new java.sql.Date(new java.util.Date(tftdatefincontrat.getEditor().getText()).getTime());

        int nb1 = Integer.parseInt(nbtr1.getText());
        int nb2 = Integer.parseInt(nbtr2.getText());
        String s = Comboformation_coach.getSelectionModel().getSelectedItem();
        // System.out.println(nb1);

        //System.out.println(nb2);

        

        int x1=0;
        int x2=0;
        int x3=0;
        
        if(grade.equals("b1"))
        {
     
           
           salaire=salaire+200; 
           x1++;
          // tftgrade.getItems().add("b0");
    
        

        
        }
        if(grade.equals("b2"))
        {
        
            
          salaire=salaire+700; 
          x2++;
         
      
        
        }
        if(grade.equals("b3"))
        {
            
       
         salaire=salaire+1000; 
         x3++;
  
        
       
            
        
            
         }
        
        
        
        
        
        
        
        

        Coach c = new Coach(id_coach, nom_coach, prenom_coach, (java.sql.Date) date_naissance, grade, (java.sql.Date) date_fin_contrat, mail, salaire, nb1, nb2, s);
        CoachCrud cc = new CoachCrud();
        cc.updateCoach(c);
        refresh();

        tftgrade.setVisible(false);
        
        
        
         if(x1==1)
        {
            
               
            String Sujet="Bonjour Mr "+nom_coach+" "+prenom_coach+ " Felicitation votre salaire a ete augmenté suite a votre nouvelle grade b1 est devenu  "+salaire+"    \n Cordialment" ;
        try {
            Email.sendmail(mail, Sujet,"Augmentation Slaire");
            } catch (Exception ex) {
           // Logger.getLogger(AddcoachController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        }
if(x2==1)
{
         String Sujet="Bonjour Mr "+nom_coach+" "+prenom_coach+ " Felicitation votre salaire a ete augmenté suite a votre nouvelle grade b2 est devenu  "+salaire+"    \n Cordialment" ;
        try {
            Email.sendmail(mail, Sujet,"Augmentation Slaire");
            } catch (Exception ex) {
        //    Logger.getLogger(AddcoachController.class.getName()).log(Level.SEVERE, null, ex);
        }

}

if(x3==1)
{
          String Sujet="Bonjour Mr "+nom_coach+" "+prenom_coach+ " Felicitation votre salaire a ete augmenté suite a votre nouvelle grade b3 est devenu  "+salaire+"    \n Cordialment" ;
        try {
            Email.sendmail(mail, Sujet,"Augmentation Slaire");
            } catch (Exception ex) {
          //  Logger.getLogger(AddcoachController.class.getName()).log(Level.SEVERE, null, ex);
        }
}

        closeDialogAddCoach();
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        

    }

    @FXML
    private void hideDialogDeleteCoach() {

        if (dialogDeleteCoach != null) {
            dialogDeleteCoach.close();
        }

    }

    @FXML
    private void deleteCoach(MouseEvent event) {
        int id_coach = tabview.getSelectionModel().getSelectedItem().getId_coach();
        Coach c = new Coach(id_coach);
        CoachCrud cc = new CoachCrud();
        cc.supprimerCoach(c);
        refresh();
        hideDialogDeleteCoach();
    }

    @FXML
    private void showDialogAddCoach() {
        rootCoach.setEffect(BOX_BLUR_EFFECT);

        textRec.setText("Ajouter une Equipe");
        //imageContainer.toFront();
        containerAddCoach.setVisible(true);
        btnSaveCoach.setDisable(false);
        btnUpdateCoach.setVisible(true);
        btnSaveCoach.toFront();

        dialogAddCoach = new JFXDialogTool(containerAddCoach, stckCoach);
        dialogAddCoach.show();

        dialogAddCoach.setOnDialogClosed(ev -> {
            closeStage();
            tabview.setDisable(false);
            rootCoach.setEffect(null);
            containerAddCoach.setVisible(false);
            refresh();
        });

        //tfNomEquipe.clear();
    }

    public static void closeStage() {
        if (stage != null) {
            stage.hide();
        }
    }

    @FXML
    private void calculeageee(MouseEvent event) {

        Date date_naissance = new java.sql.Date(new Date(tftdatenaissance.getEditor().getText()).getTime());

        Date date = new Date();
        int year = Integer.valueOf(String.format("%1$tY", date));
        System.out.println(year);

        int year1 = Integer.valueOf(String.format("%1$tY", date_naissance));
        System.out.println(year1 - 1);
        // static Integer age=0;

        int age;
        age = year - year1;
        System.out.println(year - year1);

        agee = age + 1;
        txtfieldage.setText(String.valueOf(agee));

    }

    @FXML
    private void addImageButton(ActionEvent event) {
        try {
            JFileChooser chooser = new JFileChooser();
            chooser.showOpenDialog(null);
            File f = chooser.getSelectedFile();
            String filname = f.getAbsolutePath();
            tfImagecoach.setText(filname);
            Image getAbsolutePath = null;

            Image image = new Image(new FileInputStream(filname));

            imageview.setImage(image);
            imageview.setPreserveRatio(true);

        } catch (FileNotFoundException ex) {
         //   Logger.getLogger(AddcoachController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void addImage_formationButton(ActionEvent event) {

        try {
            JFileChooser chooser = new JFileChooser();
            chooser.showOpenDialog(null);
            File f = chooser.getSelectedFile();
            String filnamee = f.getAbsolutePath();
            tfImagecoach2.setText(filnamee);
            Image getAbsolutePath = null;
            Image image = new Image(new FileInputStream(filnamee));

            imageview2.setImage(image);
            imageview2.setPreserveRatio(true);

        } catch (FileNotFoundException ex) {
         //   Logger.getLogger(AddcoachController.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

    private void showDialogDeleteCoach() {
        rootCoach.setEffect(BOX_BLUR_EFFECT);
        ContainerDeleteCoach.setVisible(true);

        dialogDeleteCoach = new JFXDialogTool(ContainerDeleteCoach, stckCoach);
        dialogDeleteCoach.show();

        dialogDeleteCoach.setOnDialogClosed(ev -> {
            tabview.setDisable(false);
            rootCoach.setEffect(null);
            ContainerDeleteCoach.setVisible(false);
            refresh();

        });

    }

    @FXML
    private void extraireexcel(ActionEvent event) {
        
//         try {
//            String query = "SELECT * from coach";
//            PreparedStatement pst = edu.takwira.tools.Connection.getInstance().getCnx().prepareStatement(query);
//            ResultSet rs = pst.executeQuery();

//            XSSFWorkbook wb = new XSSFWorkbook();
//            XSSFSheet sheet = wb.createSheet("Exercices Infos");
//            XSSFRow header = sheet.createRow(0);
//            header.createCell(0).setCellValue("id_coach");
//            header.createCell(1).setCellValue("nom_coach");
////            header.createCell(2).setCellValue("prenom_coach");
////            header.createCell(3).setCellValue("date_naissance");
////            header.createCell(4).setCellValue("grade");
////             header.createCell(5).setCellValue("date_fin_contrat");
////            header.createCell(6).setCellValue("adresse_mail");
////            header.createCell(7).setCellValue("salaire");
//            //header.createCell(8).setCellValue("level");
//            //header.createCell(9).setCellValue("price");
//           // sheet.autoSizeColumn(0);
//
//            sheet.autoSizeColumn(1);
//            sheet.autoSizeColumn(2);
//            sheet.autoSizeColumn(3);
//            sheet.autoSizeColumn(4);
//            sheet.autoSizeColumn(5);
//            sheet.autoSizeColumn(6);
//            sheet.autoSizeColumn(7);
//            //sheet.autoSizeColumn(8);
//            //sheet.autoSizeColumn(9);
//            sheet.setColumnWidth(3, 256 * 25);
//            sheet.setZoom(150);
//            
//            int index = 1;
//            while (rs.next()) {
//                XSSFRow row = sheet.createRow(index);
//                row.createCell(0).setCellValue(rs.getInt("id_coach"));
//                row.createCell(1).setCellValue(rs.getString("nom_coach"));
//                row.createCell(2).setCellValue(rs.getString("prenom_coach"));
//                row.createCell(3).setCellValue(rs.getDate("date_naissance"));
//                row.createCell(4).setCellValue(rs.getString("grade"));
//                row.createCell(5).setCellValue(rs.getDate("date_fin_contrat"));
//                row.createCell(6).setCellValue(rs.getString("adresse_mail"));
//                row.createCell(7).setCellValue(rs.getFloat("salaire"));
//               // row.createCell(8).setCellValue(rs.getString("level"));
//                //row.createCell(9).setCellValue(rs.getFloat("price"));
//               
//                
//                index++;
//            }
//
//            FileOutputStream fileOut = new FileOutputStream("EListe Coach" + index + ".xlsx");
//            index++;
//            wb.write(fileOut);
//            fileOut.close();
//
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle("Inforamtion dialog");
//            alert.setHeaderText(null);
//            alert.setContentText("Liste Coach Exported in Excel Sheet");
//            alert.showAndWait();
//
//            pst.close();
//            rs.close();
//
//        } catch (SQLException | FileNotFoundException ex) {
//            Logger.getLogger(TestController.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(TestController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        
        
    }
    
                          
    @FXML
    private void showstat(ActionEvent event) throws IOException {
        
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("statistiquecoach.fxml"));
        Scene home_page_scene = new Scene (home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);
        app_stage.show();     
    }

    @FXML
    private void MAJ(ActionEvent event) {
        
        List<Coach> suppresioncoach = new ArrayList<>();

        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);
       
            try {
            String requete = "SELECT * FROM coach where date_fin_contrat <= '"+date+"' ";
            Statement st = Connection.getInstance().getCnx().createStatement();
            ResultSet rs =  st.executeQuery(requete);
            while (rs.next()) {
              //  tftidcoachupdate.getItems().addAll(rs.getInt(1));  
               Coach c = new Coach();
                c.setId_coach(rs.getInt(1));
                c.setNom_coach(rs.getString(2));
                c.setPrenom_coach(rs.getString(3));
                c.setDate_naissance(rs.getDate(4));
                c.setGrade(rs.getString(5));
                c.setDate_fin_contrat(rs.getDate(6));
                c.setAdresse_mail(rs.getString(7));
                c.setSalaire(rs.getFloat(8));
                c.setNb_trophe_locaux(rs.getInt(9));
                c.setNb_trophe_internationaux(rs.getInt(10));
                c.setFormation_prefere(rs.getString(11));
                
               
                
                suppresioncoach.add(c);
                
                
                
                
                
                
            }
            } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
         for(int i=0 ;i<suppresioncoach.size();i++)
         {
        
            String Sujet="Contrat expiré "+suppresioncoach.get(i).getNom_coach()+" "+suppresioncoach.get(i).getPrenom_coach()+/*" Dans Co_Takwira \n   Votre Contrat avec Co_Takwira expire en   "+date_fin_contrat+*/"\n Cordialment" ;
            try {
            Email.sendmail(suppresioncoach.get(i).getAdresse_mail(), Sujet,"Contrat Experé");
            } catch (Exception ex) {
         //   Logger.getLogger(AddcoachController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
         }






        CoachCrud cc = new CoachCrud();
        cc.supprimerCoach_fin_contrat();  
        refresh();
        
       //////////////////////////////////////////////////////////////////////////////////////////////////// 
        
        
         
        
        
    }

    @FXML
    private void markergo(ActionEvent event) throws IOException {
        
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("market.fxml"));
        Scene home_page_scene = new Scene (home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);
        app_stage.show();
        
        
    }

    @FXML
    private void minimize_app(ActionEvent event) {
        FirstWindow.stage.setIconified(true);
    }

    @FXML
    private void NavReclamation(ActionEvent event) throws IOException {

        Parent menu = FXMLLoader.load(getClass().getResource("HomeReclamation.fxml"));
        stckCoach.getChildren().removeAll();
        stckCoach.getChildren().setAll(menu);
    }

    @FXML
    private void NavRate(ActionEvent event) throws IOException {

        Parent menu = FXMLLoader.load(getClass().getResource("HomeRate.fxml"));
        stckCoach.getChildren().removeAll();
        stckCoach.getChildren().setAll(menu);

    }

    @FXML
    private void NavCoach(ActionEvent event) throws IOException {
        Parent menu = FXMLLoader.load(getClass().getResource("HomeCoach.fxml"));
        stckCoach.getChildren().removeAll();
        stckCoach.getChildren().setAll(menu);
    }

    @FXML
    private void NavEntrainemet(ActionEvent event) throws IOException {
        Parent menu = FXMLLoader.load(getClass().getResource("afficheentrainement.fxml"));
        stckCoach.getChildren().removeAll();
        stckCoach.getChildren().setAll(menu);

    }

    @FXML
    private void NavEquipe(ActionEvent event) throws IOException {
        Parent menu = FXMLLoader.load(getClass().getResource("HomeEquipe.fxml"));
        stckCoach.getChildren().removeAll();
        stckCoach.getChildren().setAll(menu);
    }

    @FXML
    private void NavArticle(ActionEvent event) throws IOException {

        Parent menu = FXMLLoader.load(getClass().getResource("DisplayArticle.fxml"));
        stckCoach.getChildren().removeAll();
        stckCoach.getChildren().setAll(menu);

    }

    @FXML
    private void NavShop(ActionEvent event) throws IOException {
        Parent menu = FXMLLoader.load(getClass().getResource("stocks.fxml"));
        stckCoach.getChildren().removeAll();
        stckCoach.getChildren().setAll(menu);

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
        stckCoach.getChildren().removeAll();
        stckCoach.getChildren().setAll(menu);

    }

    
    
    
    
    

}

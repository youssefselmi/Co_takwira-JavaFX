/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cotakwira.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXMasonryPane;
import com.jfoenix.controls.JFXTextField;
import cotakwira.alerts.AlertType;
import cotakwira.alerts.AlertsBuilder;
import cotakwira.animation.Animations;
import cotakwira.entities.Rate;
import cotakwira.entities.Reclamation;
import cotakwira.notifications.NotificationType;
import cotakwira.notifications.NotificationsBuilder;
import cotakwira.resources.Constants;
import cotakwira.services.CrudRate;
import cotakwira.services.CrudReclamation;
import cotakwira.tools.Connection;
import cotakwira.util.JFXDialogTool;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author yassin
 */
public class HomeRateController implements Initializable {

    @FXML
    private StackPane stckRate;
    @FXML
    private AnchorPane rootRate;
    @FXML
    private TableView<Rate> TableViewRate;
    @FXML
    private TableColumn<Rate, Integer> col_idRate;
    @FXML
    private TableColumn<Rate, String> col_NomEquipe;
    @FXML
    private TableColumn<Rate, Date> col_DateRate;
    @FXML
    private TableColumn<Rate, Rating> col_Rate;
    @FXML
    private ScrollPane scrollPane;
    private Pane sidebar;
    @FXML
    private AnchorPane containerAddRate;
    @FXML
    private Text textRate;
    @FXML
    private JFXButton btnSaveRate;
    @FXML
    private Rating RatingEquipe;
    @FXML
    private JFXTextField tfDateRate;
    @FXML
    private JFXComboBox<String> ComboRateNomEquipe;

    private JFXDialogTool dialogAddRate;

    private static final Stage stage = new Stage();

    private final JFXMasonryPane mansoryPane = new JFXMasonryPane();

    private final JFXMasonryPane mansoryPaneStatRate = new JFXMasonryPane();
    @FXML
    private ScrollPane scrollPaneRate;

    public static int idUtilisateur = 155;
    @FXML
    private Pane PaneRate;
    @FXML
    private JFXButton btnNewRate;
    @FXML
    private Pane Navicons;
    @FXML
    private JFXButton btnCancelAddRate;
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

        initMansoryCard();
        LoadTableRate();
        statRating();
        initMansoryStat();
    }

    private void initMansoryStat() { // lel statistique Rating
        mansoryPaneStatRate.setPadding(new Insets(15, 15, 15, 15));
        mansoryPaneStatRate.setVSpacing(5);
        mansoryPaneStatRate.setHSpacing(5);
        scrollPaneRate.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPaneRate.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPaneRate.setFitToWidth(true);
        scrollPaneRate.setContent(mansoryPaneStatRate);
    }

    private void initMansoryCard() {// lel cwaret historique Rating
        mansoryPane.setPadding(new Insets(15, 15, 15, 15));
        mansoryPane.setVSpacing(5);
        mansoryPane.setHSpacing(5);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setFitToWidth(true);
        scrollPane.setContent(mansoryPane);
    }



    @FXML
    private void showDialogAddRate(MouseEvent event) {
        long millis = System.currentTimeMillis();
        java.sql.Date DateRate = new java.sql.Date(millis);
        tfDateRate.setText(String.valueOf(DateRate));

        rootRate.setEffect(Constants.BOX_BLUR_EFFECT);
        LoadNomEquipe();
        textRate.setText("Ajouter une Evaluation");
        //imageContainer.toFront();
        containerAddRate.setVisible(true);
        btnSaveRate.setDisable(false);
        btnSaveRate.toFront();

        dialogAddRate = new JFXDialogTool(containerAddRate, stckRate);
        dialogAddRate.show();

        dialogAddRate.setOnDialogOpened(ev -> {
            ComboRateNomEquipe.requestFocus();
        });

        dialogAddRate.setOnDialogClosed(ev -> {
            closeStage();
            // TableViewReclamation.setDisable(false);
            rootRate.setEffect(null);
            containerAddRate.setVisible(false);
            //  LoadTableReclamation();
        });

    }

    public static void closeStage() {
        if (stage != null) {
            stage.hide();
        }
    }

    @FXML
    private void newRate(MouseEvent event) {

        //System.out.println(RatingEquipe.getRating());
        //RatingEquipe.setRating(5.0);
        Rate rec = new Rate();
        rec.setIdCoach(Integer.parseInt(String.valueOf(idUtilisateur)));
        rec.setNomEquipe(ComboRateNomEquipe.getSelectionModel().getSelectedItem());
        int stars = (int) RatingEquipe.getRating();
        rec.setRate(stars);
        System.out.println("9adech men star ===  " + stars);

        String CombonomEquipe = ComboRateNomEquipe.getSelectionModel().getSelectedItem();
        if (CombonomEquipe == null) {
            Animations.shake(ComboRateNomEquipe);
            return;
        }

        CrudRate work = new CrudRate();
        work.ajouterRate(rec);
        LoadTableRate();
        closeDialogAddrate();
    }

    @FXML
    private void closeDialogAddRate(MouseEvent event) {
        if (dialogAddRate != null) {
            dialogAddRate.close();
        }
        loadRating();
        statRating();
    }

    @FXML
    private void closeDialogAddrate() {
        if (dialogAddRate != null) {
            dialogAddRate.close();
        }
        loadRating();
         statRating();
    }

    private void LoadTableRate() {

        CrudRate pcd = new CrudRate();
        List<Rate> listeRate = new ArrayList<>();
        Rate ratee = new Rate();
        ratee.setIdCoach(idUtilisateur);
        listeRate = pcd.displayRate(ratee);

        ObservableList<Rate> Liste = FXCollections.observableArrayList(listeRate);

        col_idRate.setCellValueFactory(new PropertyValueFactory<Rate, Integer>("idRate"));
        col_NomEquipe.setCellValueFactory(new PropertyValueFactory<Rate, String>("NomEquipe"));
        col_DateRate.setCellValueFactory(new PropertyValueFactory<Rate, Date>("DateRate"));
        col_Rate.setCellValueFactory(new PropertyValueFactory<Rate, Rating>("RateView"));
        TableViewRate.setItems(Liste);

//        Callback<TableColumn<Rate, Integer>, TableCell<Rate, Integer>> cellFoctory = (TableColumn<Rate, Integer> param) -> {
//
//            //make cell containing buttons
//            final TableCell<Rate, Integer> cell = new TableCell<Rate, Integer>() {
//
//                public void updateItem(int item, boolean empty) {
//                    super.updateItem(item, empty);
//
//                    if (empty) {
//                        setGraphic(null);
//                    } else {
//                        Rating test = new Rating();
//
//                        HBox managebtn = new HBox(test);
//                        managebtn.setStyle("-fx-alignment:center");
//                        setGraphic(test);
//
//                    }
//                }
//
//            };
//
//            return cell;
//        };
    }

    private void LoadNomEquipe() {//feha mochkla nom equipe lzmo yt3wedech
        ComboRateNomEquipe.getItems().clear();
        CrudRate pcd = new CrudRate();
        Rate t = new Rate();
        t.setIdCoach(idUtilisateur);
        String NomEquipee = null;
        try {
            String requeteee = "SELECT DISTINCT nom_equipee FROM entrainement WHERE id_coach  = '" + t.getIdCoach() + "'";
            Statement psttt = Connection.getInstance().getCnx().createStatement();
            ResultSet rss = psttt.executeQuery(requeteee);
            while (rss.next()) {
                NomEquipee = rss.getString(1);//bech najmt njyb mnha nom de 
                System.out.println("NomEquipee == " + NomEquipee);
                ComboRateNomEquipe.getItems().addAll(rss.getString("nom_equipee"));
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @FXML
    private void handleShowRate(Event event) {
        loadRating();
         statRating();
        Animations.fadeInUp(mansoryPaneStatRate);
        Animations.fadeInUp(scrollPaneRate);

    }

    private void loadRating() {
        mansoryPane.getChildren().clear();
        CrudRate pcd = new CrudRate();
        Rate ratee = new Rate();
        List<Rate> listeRate = new ArrayList<>();
        ratee.setIdCoach(idUtilisateur);
        listeRate = pcd.displayRate(ratee);

        if (!listeRate.isEmpty()) {
            int h = 0;
            int nbStarts = 0;
            for (int i = 0; i < listeRate.size(); i++) {
                VBox root = new VBox();
                Rating ook = new Rating();

                root.setStyle("-fx-background-color: #fff; -fx-background-radius: 15px;-fx-effect:dropshadow(three-pass-box, gray, 10, 0, 0, 0);");
                nbStarts = listeRate.get(i).getRate();
                Date DateRate = listeRate.get(i).getDateRate();
                String nomequipe = listeRate.get(i).getNomEquipe();
                h++;
                ook.setRating(nbStarts);
                ook.setDisable(true);
                // System.out.println(listeRate);
                // System.out.println(h);

                root.setPadding(new Insets(12, 17, 17, 17));

                root.setSpacing(13);
                // root.getChildren().add(ook);
                root.getChildren().addAll(new Label("Nom : " + nomequipe), ook, new Label("DateRate : " + DateRate));
                root.setAlignment(Pos.CENTER);
                mansoryPane.getChildren().add(root);
                Animations.fadeInUp(mansoryPane);
            }

        }

    }

    @FXML
    private void handleTableRate(Event event) {
        loadRating();
         statRating();
        LoadTableRate();
        Animations.fadeInUp(TableViewRate);
        Animations.fadeInUp(mansoryPaneStatRate);
        Animations.fadeInUp(scrollPaneRate);
    }

    private void statRating() { // bech na3meel  sous lensemble sur lensemble 
        mansoryPaneStatRate.getChildren().clear();// ye3ni css 3endeha 14 star ne9semhom 3ela total star (css+est)
        String NomEquipee = null;
        String enfin = null;
        Integer SumRateParEquipe = 0;
        Integer SumRateEquipetLkol = 0;
        Double resultRate;
        
        try {
            String requeteee1 = "SELECT SUM(Rate) From rate";
            Statement psttt1 = Connection.getInstance().getCnx().createStatement();
            ResultSet rss1 = psttt1.executeQuery(requeteee1);
            while (rss1.next()) {
                SumRateEquipetLkol = rss1.getInt(1);//bech najmt njyb mnha nom de 
                System.out.println(" SumRateEquipetLkol ==> " + SumRateEquipetLkol);
                String requeteee = "SELECT DISTINCT NomEquipe FROM rate";
                Statement psttt = Connection.getInstance().getCnx().createStatement();
                ResultSet rss = psttt.executeQuery(requeteee);
                while (rss.next()) {
                    NomEquipee = rss.getString(1);//bech najmt njyb mnha nom de 
                    System.out.println("NomEquipee ==> " + NomEquipee);

                    String requeteee2 = "SELECT SUM(Rate) FROM rate where NomEquipe = '" + NomEquipee + "' ";
                    Statement psttt2 = Connection.getInstance().getCnx().createStatement();
                    ResultSet rss2 = psttt2.executeQuery(requeteee2);
                    while (rss2.next()) {
                        SumRateParEquipe = rss2.getInt(1);//bech najmt njyb mnha nom de 
                        resultRate = (((double) SumRateParEquipe) / SumRateEquipetLkol) * 100;
                        System.out.println("Votee ==> " + SumRateParEquipe);
                        System.out.println("ResulFinal  ==> " + resultRate);

                        DecimalFormat df = new DecimalFormat("########.00");
                        enfin = df.format(resultRate);
                        System.out.println("Heeddha L7achttyyy byyh ===> " + enfin);
                    }

                    //lehna chnekhdem lcode te3 affichage 
                    System.out.println("hahahahahha  " + NomEquipee);
                    VBox root = new VBox();

                    root.setStyle("-fx-background-color:  linear-gradient(to right top, #56ab2f , #a8e063);    -fx-background-radius: 15px;-fx-effect:dropshadow(three-pass-box, gray, 10, 0, 0, 0);");

                    root.setPadding(new Insets(12, 17, 17, 17));

                    root.setSpacing(13);
                    // root.getChildren().add(ook);
                    root.getChildren().addAll(new Label("Nom: " + NomEquipee), new Label("Rating: " + enfin + " %"));
                    root.setAlignment(Pos.CENTER);
                    mansoryPaneStatRate.getChildren().add(root);
                    Animations.fadeInUp(mansoryPaneStatRate);

                }

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @FXML
    private void NavReclamation(ActionEvent event) throws IOException {

        Parent menu = FXMLLoader.load(getClass().getResource("HomeReclamation.fxml"));
        stckRate.getChildren().removeAll();
        stckRate.getChildren().setAll(menu);

    }

    @FXML
    private void NavRate(ActionEvent event) throws IOException {

        Parent menu = FXMLLoader.load(getClass().getResource("HomeRate.fxml"));
        stckRate.getChildren().removeAll();
        stckRate.getChildren().setAll(menu);

    }

    @FXML
    private void NavCoach(ActionEvent event) throws IOException {
        Parent menu = FXMLLoader.load(getClass().getResource("HomeCoach.fxml"));
        stckRate.getChildren().removeAll();
        stckRate.getChildren().setAll(menu);
    }

    @FXML
    private void NavEntrainemet(ActionEvent event) throws IOException {
        Parent menu = FXMLLoader.load(getClass().getResource("afficheentrainement.fxml"));
        stckRate.getChildren().removeAll();
        stckRate.getChildren().setAll(menu);

    }

    @FXML
    private void NavEquipe(ActionEvent event) throws IOException {
        Parent menu = FXMLLoader.load(getClass().getResource("HomeEquipe.fxml"));
        stckRate.getChildren().removeAll();
        stckRate.getChildren().setAll(menu);
    }

    @FXML
    private void NavArticle(ActionEvent event) throws IOException {

        Parent menu = FXMLLoader.load(getClass().getResource("DisplayArticle.fxml"));
        stckRate.getChildren().removeAll();
        stckRate.getChildren().setAll(menu);

    }

    @FXML
    private void NavShop(ActionEvent event) throws IOException {
        Parent menu = FXMLLoader.load(getClass().getResource("stocks.fxml"));
        stckRate.getChildren().removeAll();
        stckRate.getChildren().setAll(menu);

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
        stckRate.getChildren().removeAll();
        stckRate.getChildren().setAll(menu);

    }


        @FXML
    private void close_app(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are You sure do you want Exit !");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            System.exit(0);
        }

    }


    @FXML
    private void minimize_app(ActionEvent event) {
        FirstWindow.stage.setIconified(true);
    }


}

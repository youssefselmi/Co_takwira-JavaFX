/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cotakwira.gui;

import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXMasonryPane;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import cotakwira.alerts.AlertType;
import cotakwira.alerts.AlertsBuilder;
import cotakwira.animation.Animations;
import cotakwira.entities.Reclamation;

import static cotakwira.gui.HomeReclamationController.idRecsupp;
import cotakwira.notifications.NotificationType;
import cotakwira.notifications.NotificationsBuilder;
import cotakwira.resources.Constants;
import cotakwira.services.CrudReclamation;
import cotakwira.tools.Connection;
import cotakwira.util.JFXDialogTool;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.InvalidationListener;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.swing.text.Document;
import java.sql.Date;
import static java.time.LocalDate.now;
import java.util.Locale;

/**
 * FXML Controller class
 *
 * @author yassin
 */
public class AdminReclamationController implements Initializable {

    @FXML
    private StackPane stckReclam;
    @FXML
    private AnchorPane rootRec;
    @FXML
    private Pane PaneRec;
    @FXML
    private TabPane TabPaneRec;
    @FXML
    private TableView<Reclamation> TableViewReclamation;
    @FXML
    private TableColumn<Reclamation, Integer> col_idRec;
    @FXML
    private TableColumn<Reclamation, String> col_SujetRec;
    @FXML
    private TableColumn<Reclamation, String> col_DescriptionRec;
    @FXML
    private TableColumn<Reclamation, String> col_StatusRec;
    @FXML
    private TableColumn<Reclamation, String> col_NomPrenomCoach;
    @FXML
    private TableColumn<Reclamation, Date> col_DateRec;
    @FXML
    private TableColumn<Reclamation, Date> col_DateTraitement;
    @FXML
    private TableColumn<Reclamation, ImageView> col_imgRec;
    @FXML
    private TableColumn<Reclamation, String> ActionRec;
    @FXML
    private ScrollPane scrollPane;
    private Pane sidebar;
    @FXML
    private Pane Navicons;
    @FXML
    private AnchorPane containerAddRec;
    @FXML
    private JFXTextField tfNomRec;
    @FXML
    private JFXTextField tfPrenomRec;
    @FXML
    private JFXTextField tfEmailRec;
    @FXML
    private JFXTextField tfTlRec;
    @FXML
    private JFXComboBox<String> ComboSujetRec;
    @FXML
    private JFXComboBox<String> ComboCoachRec;
    @FXML
    private JFXTextArea tfDescriptionRec;
    @FXML
    private ImageView DragimgRec;
    @FXML
    private AnchorPane ContainerDeleteRec;

    private final JFXMasonryPane mansoryPane = new JFXMasonryPane();
    private JFXDialogTool dialogAddRec;
    private JFXDialogTool dialogDeleteRec;
    @FXML
    private JFXComboBox<String> ComboSatutRec;
    String ImagePath = "";
    private static final Stage stage = new Stage();
    @FXML
    private JFXButton btnUpdateTaite;
    @FXML
    private Button pdf;
    @FXML
    private ComboBox<String> typeRecherche;
    @FXML
    private TextField RechercheTextField;
    ObservableList<String> listeTypeRecherche = FXCollections.observableArrayList("Tout", "Sujet", "Coach", "DateRec", "DateTraitement");
    private static ComboBox<String> typeRechercheStatus;
    @FXML
    private HBox hbox;
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
        try {
            // TODO
            LoadTableReclamation();
       
            ComboSatutRec.getItems().addAll("non traite", "en traitement", "traite");
            //   remplirComboCoach();

            mansoryPane.setPadding(new Insets(15, 15, 15, 15));
            mansoryPane.setVSpacing(25);
            mansoryPane.setHSpacing(25);
            scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
            scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
            scrollPane.setFitToWidth(true);
            scrollPane.setContent(mansoryPane);
            loadCarte();
        } catch (FileNotFoundException ex) {
            System.out.println("Catch lele Card ");
        }

        typeRecherche.setItems(listeTypeRecherche);
        typeRecherche.setValue("Tout");

    }

    private void LoadTableReclamation() {

        CrudReclamation xx = new CrudReclamation();
        xx.PrioriteRec();

        CrudReclamation pcd = new CrudReclamation();

        List<Reclamation> listeRec = new ArrayList<>();

        Reclamation rec = new Reclamation();

        listeRec = pcd.displayRecForAdmin();

        ObservableList<Reclamation> Liste = FXCollections.observableArrayList(listeRec);

        col_idRec.setCellValueFactory(new PropertyValueFactory<Reclamation, Integer>("idRec"));
        col_SujetRec.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("SujetRec"));
        col_DescriptionRec.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("DescriptionRec"));
        col_StatusRec.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("StatusRec"));
        col_NomPrenomCoach.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("NomPrenomCoach"));
        col_DateRec.setCellValueFactory(new PropertyValueFactory<Reclamation, Date>("DateRec"));
        col_DateTraitement.setCellValueFactory(new PropertyValueFactory<Reclamation, Date>("DateTraitement"));
        col_imgRec.setCellValueFactory(new PropertyValueFactory<Reclamation, ImageView>("imgReclamation"));

        TableViewReclamation.setItems(Liste);

        //add cell of button edit 
        Callback<TableColumn<Reclamation, String>, TableCell<Reclamation, String>> cellFoctory = (TableColumn<Reclamation, String> param) -> {
            //make cell containing buttons

            final TableCell<Reclamation, String> cell = new TableCell<Reclamation, String>() {

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

                            int idRec = Integer.valueOf((TableViewReclamation.getSelectionModel().getSelectedItem().getIdRec()));

                            showDialogDeleteReclam();

                        });

                        editIcon.setOnMouseClicked((MouseEvent event) -> {
                            System.out.println("icon edit is pressed !");

                            int idRec = Integer.valueOf((TableViewReclamation.getSelectionModel().getSelectedItem().getIdRec()));
                            ComboSujetRec.setValue(TableViewReclamation.getSelectionModel().getSelectedItem().getSujetRec());
                            tfDescriptionRec.setText(TableViewReclamation.getSelectionModel().getSelectedItem().getDescriptionRec());
                            ComboCoachRec.setValue(TableViewReclamation.getSelectionModel().getSelectedItem().getNomPrenomCoach());
                            CrudReclamation day = new CrudReclamation();
                            day.ReclamationIsOpen(idRec);

                            try {
                                String requeteee = "SELECT imgRec FROM reclamation WHERE idRec  = '" + idRec + "'";
                                Statement psttt = Connection.getInstance().getCnx().createStatement();
                                ResultSet rss = psttt.executeQuery(requeteee);
                                while (rss.next()) {
                                    ImagePath = rss.getString(1);//bech najmt njyb mnha nom de 
                                    Image img = new Image(new FileInputStream(ImagePath));
                                    System.out.println("Pathimage fel update" + ImagePath);
                                    DragimgRec.setImage(img);
                                }

                            } catch (SQLException ex) {
                                System.out.println(ex.getMessage());
                            } catch (FileNotFoundException ex) {
                                DragimgRec.setImage(new Image(getClass().getResource("/resources/media/empty-image.jpg").toExternalForm()));
                            }

                            showDialogAddRec();
                            // btnTraite
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

        ActionRec.setCellFactory(cellFoctory);

        TableViewReclamation.setOnMouseClicked(ev -> {
            if (ev.getButton().equals(MouseButton.PRIMARY) && ev.getClickCount() == 2) {
                int idRecc = Integer.valueOf((TableViewReclamation.getSelectionModel().getSelectedItem().getIdRec()));
                System.out.println("Tab : idRec selectioned is == " + idRecc);
                try {
                    String requeteee2 = "SELECT imgRec FROM reclamation WHERE idRec  = '" + idRecc + "'";
                    Statement psttt2 = Connection.getInstance().getCnx().createStatement();
                    ResultSet rss2 = psttt2.executeQuery(requeteee2);
                    while (rss2.next()) {
                        Image img2 = new Image(new FileInputStream(rss2.getString("imgRec")));

                        Stage window = new Stage();
                        window.setTitle("Reclamation ID : " + String.valueOf(idRecc));
                        window.setMinWidth(250);
                        ImageView imagevPOPUP = new ImageView();
                        imagevPOPUP.setImage(img2);
                        imagevPOPUP.setFitHeight(576);
                        imagevPOPUP.setFitWidth(1024);

                        VBox layout = new VBox(10);
                        layout.getChildren().addAll(imagevPOPUP);
                        layout.setAlignment(Pos.CENTER);

                        //Display window and wait for it to be closed before returning
                        Scene scene = new Scene(layout);
                        window.setScene(scene);
                        window.show();
                    }

                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                } catch (FileNotFoundException ex) {
                    AlertsBuilder.create(AlertType.ERROR, stckReclam, rootRec, mansoryPane, "Sorry it doesn't contain any image !");
                }

            }
        });

        Callback<TableColumn<Reclamation, String>, TableCell<Reclamation, String>> cellFactoryStatus
                = //
                new Callback<TableColumn<Reclamation, String>, TableCell<Reclamation, String>>() {
            @Override
            public TableCell call(final TableColumn<Reclamation, String> param) {
                final TableCell<Reclamation, String> cell = new TableCell<Reclamation, String>() {

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {

                            ImageView imagev;
                            if (item.equals("non traite")) {
                                imagev = new ImageView(new Image("/resources/media/enAttente.png"));
                                imagev.setFitHeight(30);
                                imagev.setFitWidth(100);
                                setGraphic(imagev);

                            }
                            if (item.equals("en traitement")) {
                                imagev = new ImageView(new Image("/resources/media/enTraitement.png"));
                                imagev.setFitHeight(30);
                                imagev.setFitWidth(100);
                                setGraphic(imagev);

                            } else if (item.equals("traite")) {
                                imagev = new ImageView(new Image("/resources/media/traite.png"));
                                imagev.setFitHeight(30);
                                imagev.setFitWidth(100);
                                setGraphic(imagev);

                            }

                        }
                    }
                };
                return cell;
            }
        };

        col_StatusRec.setCellFactory(cellFactoryStatus);

//        TableViewReclamation.setRowFactory(tv -> new TableRow<Reclamation>() {
//            @Override
//            public void updateItem(Reclamation item, boolean empty) {
//                super.updateItem(item, empty);
//                if (item == null) {
//
//                }  if (item.getPrioriteRec().equals("High")) {
//                    setStyle("-fx-background-color:  linear-gradient(to right top, #0083B0, #DEE0E2); ");
//                }
//            }
//           
//        });
        TableViewReclamation.setOnMouseClicked(ev -> {

            if (ev.getButton().equals(MouseButton.PRIMARY) && ev.getClickCount() == 2) {

                int idRecc = Integer.valueOf((TableViewReclamation.getSelectionModel().getSelectedItem().getIdRec()));
                System.out.println("Tab : idRec selectioned is == " + idRecc);
                try {
                    String requeteee2 = "SELECT imgRec FROM reclamation WHERE idRec  = '" + idRecc + "'";
                    Statement psttt2 = Connection.getInstance().getCnx().createStatement();
                    ResultSet rss2 = psttt2.executeQuery(requeteee2);
                    while (rss2.next()) {
                        Image img2 = new Image(new FileInputStream(rss2.getString("imgRec")));

                        Stage window = new Stage();
                        window.setTitle("Reclamation ID : " + String.valueOf(idRecc));
                        window.setMinWidth(250);
                        ImageView imagevPOPUP = new ImageView();
                        imagevPOPUP.setImage(img2);
                        imagevPOPUP.setFitHeight(576);
                        imagevPOPUP.setFitWidth(1024);

                        VBox layout = new VBox(10);
                        layout.getChildren().addAll(imagevPOPUP);
                        layout.setAlignment(Pos.CENTER);

                        //Display window and wait for it to be closed before returning
                        Scene scene = new Scene(layout);
                        window.setScene(scene);
                        window.show();
                    }

                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                } catch (FileNotFoundException ex) {
                    AlertsBuilder.create(AlertType.ERROR, stckReclam, rootRec, mansoryPane, "Sorry it doesn't contain any image !");
                }
            }

        });

    }

    public static void closeStage() {
        if (stage != null) {
            stage.hide();
        }
    }

    @FXML
    private void handleShowTableRec(Event event) {
    }

    @FXML
    private void handleShowCards(Event event) {
    }


    private void showDialogAddRec() {

        if (ImagePath == "") {
            // DragimgRec.setImage(new Image(getClass().getResource("/resources/media/drag-drop.gif").toExternalForm()));
        }

        rootRec.setEffect(Constants.BOX_BLUR_EFFECT);
        //imageContainer.toFront();
        containerAddRec.setVisible(true);
        // btnSaveReclam.setDisable(false);

        dialogAddRec = new JFXDialogTool(containerAddRec, stckReclam);
        dialogAddRec.show();
        dialogAddRec.setOnDialogOpened(ev -> {
            tfDescriptionRec.requestFocus();
        });

        dialogAddRec.setOnDialogClosed(ev -> {
            closeStage();
            TableViewReclamation.setDisable(false);
            rootRec.setEffect(null);
            containerAddRec.setVisible(false);
            LoadTableReclamation();
        });
    }




    @FXML
    private void closeDialogAddrec() {

        if (dialogAddRec != null) {
            dialogAddRec.close();
        }

    }


    @FXML
    private void hideDialogDeleteReclam() {
        if (dialogDeleteRec != null) {
            dialogDeleteRec.close();
        }
    }

    @FXML
    private void deleteReclam(MouseEvent event) {
        if (TableViewReclamation.getSelectionModel().getSelectedItem() != null) {//supprimer mel tableau
            Reclamation rec = TableViewReclamation.getSelectionModel().getSelectedItem();
            CrudReclamation okay = new CrudReclamation();
            Boolean result = okay.supprimerReclamation(rec.getIdRec());
            if (result) {
                hideDialogDeleteReclam();
                AlertsBuilder.create(AlertType.SUCCES, stckReclam, rootRec, TableViewReclamation, Constants.MESSAGE_DELETED);
            } else {
                NotificationsBuilder.create(NotificationType.ERROR, Constants.MESSAGE_ERROR_CONNECTION_MYSQL);
            }
        } else if (TableViewReclamation.getSelectionModel().getSelectedItem() == null && idRecsupp != 0) {//supprimer mel card
            CrudReclamation okayy = new CrudReclamation();
            Boolean result = okayy.supprimerReclamation(idRecsupp);
            System.out.println("idRec mel card" + idRecsupp);
            if (result) {
                hideDialogDeleteReclam();
                AlertsBuilder.create(AlertType.SUCCES, stckReclam, rootRec, TableViewReclamation, Constants.MESSAGE_DELETED);
            } else {
                NotificationsBuilder.create(NotificationType.ERROR, Constants.MESSAGE_ERROR_CONNECTION_MYSQL);
            }
        }

    }

    private void showDialogDeleteReclam() {
        rootRec.setEffect(Constants.BOX_BLUR_EFFECT);
        ContainerDeleteRec.setVisible(true);

        dialogDeleteRec = new JFXDialogTool(ContainerDeleteRec, stckReclam);
        dialogDeleteRec.show();

        dialogDeleteRec.setOnDialogClosed(ev -> {
            TableViewReclamation.setDisable(false);
            rootRec.setEffect(null);
            ContainerDeleteRec.setVisible(false);
            LoadTableReclamation();

        });

    }

//    private void remplirComboCoach() {
//
//        ComboSujetRec.getItems().addAll("Coach", "Application", "Autre");
//        try {
//            Reclamation t = new Reclamation();
//            t.setIdJoueur(69);
//            System.out.println(t.getIdJoueur());
//            Connection cnx = Connection.getInstance().getCnx();
//            String requetee = "SELECT * FROM joueur WHERE id_joueur = '" + t.getIdJoueur() + "'";
//            Statement pstt = Connection.getInstance().getCnx().createStatement();
//            ResultSet rs = pstt.executeQuery(requetee);
//            while (rs.next()) {
//                tfNomRec.setText(rs.getString(2));
//                tfPrenomRec.setText(rs.getString(3));
//                tfEmailRec.setText(rs.getString(7));
//                tfTlRec.setText(rs.getString(6));
//
//                System.out.println(tfNomRec);
//                break;
//            }
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//
//    }
    @FXML
    private void updateStatus(MouseEvent event) throws FileNotFoundException {

        int idReccc = 0;
        if (TableViewReclamation.getSelectionModel().getSelectedItem() != null) {//Modifier mel tableau
            idReccc = Integer.valueOf((TableViewReclamation.getSelectionModel().getSelectedItem().getIdRec()));
            System.out.println("idRec mel Tableau" + idReccc);
        } else if (TableViewReclamation.getSelectionModel().getSelectedItem() == null && idRecsupp != 0) {//Modifier mel card
            idReccc = idRecsupp;
            System.out.println("idRec mel card" + idRecsupp);
        }

        String Statut = ComboSatutRec.getSelectionModel().getSelectedItem();
        if (Statut == null) {
            Animations.shake(ComboSatutRec);
            return;
        }

        System.out.println(idReccc);
        System.out.println(Statut);

        Reclamation rec = new Reclamation(idReccc, Statut);
        CrudReclamation ok = new CrudReclamation();

        Boolean result = ok.updateRecStatut(rec);

        if (result) {
            closeDialogAddrec();
            AlertsBuilder.create(AlertType.SUCCES, stckReclam, rootRec, TableViewReclamation, Constants.MESSAGE_UPDATED);
        } else {
            NotificationsBuilder.create(NotificationType.ERROR, Constants.MESSAGE_ERROR_CONNECTION_MYSQL);
        }
        LoadTableReclamation();
        closeDialogAddrec();
        loadCarte();

    }

    @FXML
    private void fairepdf_reclalamtion(ActionEvent event) {
        long millis = System.currentTimeMillis();
        java.sql.Date DateRapport = new java.sql.Date(millis);

        String DateLyoum = new SimpleDateFormat("yyyyMMddHHmmss", Locale.ENGLISH).format(DateRapport);//yyyyMMddHHmmss
        System.out.println("DateLyoummmmmmmmmmmmmmmmmmmmm   " + DateLyoum);

        CrudReclamation rec = new CrudReclamation();
        com.itextpdf.text.Document document = new com.itextpdf.text.Document();

        try {

            PdfWriter.getInstance(document, new FileOutputStream(String.valueOf(DateLyoum + ".pdf")));//yyyy-MM-dd
            document.open();
            Paragraph ph1 = new Paragraph("Rapport Pour :" + DateRapport);
            Paragraph ph2 = new Paragraph(".");
            PdfPTable table = new PdfPTable(7);

            //On créer l'objet cellule.
            PdfPCell cell;

            //contenu du tableau.
            table.addCell("idRec");
            table.addCell("Sujet");
            table.addCell("Statut");
            table.addCell("Description");
            table.addCell("Coach");
            table.addCell("DateRec");
            table.addCell("DateTraitement");
            //     table.addCell("Image : ");

            rec.displayRecForAdmin().forEach(e
                    -> {
                table.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(String.valueOf(e.getIdRec()));
                table.addCell(e.getSujetRec());
                table.addCell(e.getStatusRec());
                table.addCell(e.getDescriptionRec());
                table.addCell(e.getNomPrenomCoach());
                table.addCell(String.valueOf(e.getDateRec()));
                if (String.valueOf(e.getDateTraitement()).equals("null")) {
                    table.addCell("");
                } else {
                    table.addCell(String.valueOf(e.getDateTraitement())
                    );
                }
                

            }
            );
            document.add(ph1);
            document.add(ph2);
            document.add(table);
            //  document.addAuthor("Bike");
            // AlertDialog.showNotification("Creation PDF ", "Votre fichier PDF a ete cree avec success", AlertDialog.image_checked);
        } catch (Exception e) {
            System.out.println(e);
        }
        document.close();

    }

    private void loadCarte() throws FileNotFoundException {

        mansoryPane.getChildren().clear();

        Reclamation t = new Reclamation();
        t.setIdJoueur(89);

        try {
            String requete = "SELECT * FROM reclamation";
            Statement pstt = Connection.getInstance().getCnx().createStatement();
            ResultSet rs = pstt.executeQuery(requete);
            while (rs.next()) {
                VBox root = new VBox();
                ImageView imageViewCard = new ImageView();
                imageViewCard.setFitWidth(80);
                imageViewCard.setFitHeight(80);
                imageViewCard.setPreserveRatio(true);
                imageViewCard.setSmooth(true);
                imageViewCard.setCache(true);

                MaterialDesignIcon ICON_DELETE = MaterialDesignIcon.DELETE_VARIANT;
                MaterialDesignIcon ICON_DETAILS = MaterialDesignIcon.INFORMATION_OUTLINE;

                MenuItem consulter = new MenuItem("Consulter");
                MenuItem delete = new MenuItem("Delete");

                delete.setGraphic(new MaterialDesignIconView(ICON_DELETE));
                consulter.setGraphic(new MaterialDesignIconView(ICON_DETAILS));

                ContextMenu menu = new ContextMenu(delete, consulter);
                menu.setAutoHide(true);
                menu.setAutoFix(true);
                menu.setConsumeAutoHidingEvents(true);
                menu.setHideOnEscape(true);

                root.setPrefSize(imageViewCard.getFitWidth(), imageViewCard.getFitHeight());

                if (rs.getString("imgRec").equals("")) {
                    root.setStyle("-fx-background-color: #fff; -fx-background-radius: 15px;-fx-effect:dropshadow(three-pass-box, gray, 10, 0, 0, 0);");
                    imageViewCard.setImage(new Image(getClass().getResource("/resources/media/empty-image.jpg").toString()));
                } else {
                    Image img = new Image(new FileInputStream(rs.getString("imgRec")));
                    imageViewCard.setImage(img);
                }
                root.setPadding(new Insets(7, 7, 7, 7));

                root.setSpacing(10);
                if (rs.getString("StatusRec").equals("traite")) {
                    root.getChildren().addAll(imageViewCard, new Label("id: " + rs.getInt("idRec") + "\nSujet: " + rs.getString("SujetRec") + "\nDateRec: " + rs.getString("DateRec") + "\ntraite: " + rs.getDate("DateTraitement")));
                    root.setStyle("-fx-background-color:  linear-gradient(to right top, #56ab2f , #a8e063);  -fx-background-radius: 15px; -fx-effect:dropshadow(three-pass-box, gray, 10, 0, 0, 0);");
                    root.setAlignment(Pos.CENTER);
                }
                if (rs.getString("StatusRec").equals("non traite")) {//en attend
                    root.getChildren().addAll(imageViewCard, new Label("id: " + rs.getInt("idRec") + "\nSujet: " + rs.getString("SujetRec") + "\nDateRec: " + rs.getDate("DateRec")));
                    root.setStyle("-fx-background-color:  linear-gradient(to right top, #DEE0E2 , #ECECEC); -fx-background-radius: 15px; -fx-effect:dropshadow(three-pass-box, gray, 10, 0, 0, 0);");
                    root.setAlignment(Pos.CENTER);
                }
                if (rs.getString("StatusRec").equals("en traitement")) {
                    root.getChildren().addAll(imageViewCard, new Label("id: " + rs.getInt("idRec") + "\nSujet: " + rs.getString("SujetRec") + "\nDateRec: " + rs.getDate("DateRec")));
                    root.setStyle("-fx-background-color:  linear-gradient(to right top, #00B4DB , #0083B0);  -fx-background-radius: 15px; -fx-effect:dropshadow(three-pass-box, gray, 10, 0, 0, 0);");
                    root.setAlignment(Pos.CENTER);
                }

                int id = rs.getInt("idRec");
                root.setOnContextMenuRequested(ev -> {
                    menu.show(root, ev.getScreenX(), ev.getScreenY());
                    System.out.println("Carte id Selectioned is ! ====  " + id);
                    idRecsupp = id;
                    System.out.println(idRecsupp);
                });

                delete.setOnAction(ev -> {

                    showDialogDeleteReclam();

                });

                consulter.setOnAction(ev -> {
                    try {

                        String requeteee = "SELECT idRec,SujetRec,DescriptionRec,StatusRec,NomPrenomCoach,DateRec,DateTraitement,imgRec FROM reclamation WHERE idRec = '" + idRecsupp + "'";
                        Statement psttt = Connection.getInstance().getCnx().createStatement();
                        ResultSet rsss = psttt.executeQuery(requeteee);
                        while (rsss.next()) {

                            ComboSujetRec.setValue(rsss.getString(2));
                            tfDescriptionRec.setText(rsss.getString(3));
                            ComboCoachRec.setValue(rsss.getString(5));

                            ComboSujetRec.setDisable(true);
                            ComboCoachRec.setDisable(true);
                            tfDescriptionRec.setDisable(false);

                            ImagePath = rsss.getString(8);
                            if (ImagePath != "") {
                                Image img = new Image(new FileInputStream(ImagePath));
                                DragimgRec.setImage(img);

                            }

                        }

                    } catch (SQLException ex) {
                        Logger.getLogger(HomeReclamationController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (FileNotFoundException ex) {
                        DragimgRec.setImage(new Image(getClass().getResource("/resources/media/empty-image.jpg").toString()));
                    }
                    showDialogAddRec();
                });

                imageViewCard.setOnMouseClicked(ev -> {
                    idRecsupp = id;
                    System.out.println(idRecsupp);
                    if (ev.getButton().equals(MouseButton.PRIMARY) && ev.getClickCount() == 2) {
                        try {
                            String requeteee2 = "SELECT imgRec FROM reclamation WHERE idRec  = '" + idRecsupp + "'";
                            Statement psttt2 = Connection.getInstance().getCnx().createStatement();
                            ResultSet rss2 = psttt2.executeQuery(requeteee2);
                            while (rss2.next()) {
                                Image img2 = new Image(new FileInputStream(rss2.getString("imgRec")));

                                Stage window = new Stage();
                                window.setTitle("Reclamation ID : " + String.valueOf(idRecsupp));
                                window.setMinWidth(250);
                                ImageView imagevPOPUP = new ImageView();
                                imagevPOPUP.setImage(img2);
                                imagevPOPUP.setFitHeight(576);
                                imagevPOPUP.setFitWidth(1024);

                                VBox layout = new VBox(10);
                                layout.getChildren().addAll(imagevPOPUP);
                                layout.setAlignment(Pos.CENTER);

                                //Display window and wait for it to be closed before returning
                                Scene scene = new Scene(layout);
                                window.setScene(scene);
                                window.show();
                            }

                        } catch (SQLException ex) {
                            System.out.println(ex.getMessage());
                        } catch (FileNotFoundException ex) {
                            AlertsBuilder.create(AlertType.ERROR, stckReclam, rootRec, mansoryPane, "Sorry it doesn't contain any image !");
                        }

                    }
                });

                mansoryPane.getChildren().addAll(root);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
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

    @FXML
    private void NavReclamation(ActionEvent event) throws IOException {

        Parent menu = FXMLLoader.load(getClass().getResource("HomeReclamation.fxml"));
        stckReclam.getChildren().removeAll();
        stckReclam.getChildren().setAll(menu);
    }

    @FXML
    private void NavRate(ActionEvent event) throws IOException {

        Parent menu = FXMLLoader.load(getClass().getResource("HomeRate.fxml"));
        stckReclam.getChildren().removeAll();
        stckReclam.getChildren().setAll(menu);

    }

    @FXML
    private void NavCoach(ActionEvent event) throws IOException {
        Parent menu = FXMLLoader.load(getClass().getResource("HomeCoach.fxml"));
        stckReclam.getChildren().removeAll();
        stckReclam.getChildren().setAll(menu);
    }

    @FXML
    private void NavEntrainemet(ActionEvent event) throws IOException {
        Parent menu = FXMLLoader.load(getClass().getResource("afficheentrainement.fxml"));
        stckReclam.getChildren().removeAll();
        stckReclam.getChildren().setAll(menu);

    }

    @FXML
    private void NavEquipe(ActionEvent event) throws IOException {
        Parent menu = FXMLLoader.load(getClass().getResource("HomeEquipe.fxml"));
        stckReclam.getChildren().removeAll();
        stckReclam.getChildren().setAll(menu);
    }

    @FXML
    private void NavArticle(ActionEvent event) throws IOException {

        Parent menu = FXMLLoader.load(getClass().getResource("DisplayArticle.fxml"));
        stckReclam.getChildren().removeAll();
        stckReclam.getChildren().setAll(menu);

    }

    @FXML
    private void NavShop(ActionEvent event) throws IOException {

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
        stckReclam.getChildren().removeAll();
        stckReclam.getChildren().setAll(menu);

    }

    @FXML
    private void list(ActionEvent event) {
    }

    @FXML
    private void list(KeyEvent event) {
    }
    

}

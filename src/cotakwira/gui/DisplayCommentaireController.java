/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cotakwira.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import cotakwira.entities.Article;
import cotakwira.entities.Commentaire;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;
import cotakwira.services.ArticleService;
import cotakwira.services.CommentaireService;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author khali
 */
public class DisplayCommentaireController implements Initializable {

    @FXML
    private TableView<Commentaire> tableCommentaire;
    @FXML
    private TableColumn<Commentaire, Integer> id;
    @FXML
    private TableColumn<Commentaire, String> contenu;
    @FXML
    private TableColumn<Commentaire, Date> date;
    @FXML
    private TableColumn<Commentaire,Integer> id_article;
    private TableColumn<Commentaire, Integer> id_personnel;
    @FXML
    private JFXTextField tfId;
    @FXML
    private JFXTextField tfType;
    @FXML
    private JFXTextField tfTitre;
    @FXML
    private JFXTextArea taDescription;
    @FXML
    private JFXTextField tfDate;
    @FXML
    private JFXTextField tfIdp;
    @FXML
    private JFXButton BtnModifierC;
    @FXML
    private JFXTextArea taContenuM;
    @FXML
    private TableColumn<?, ?> id_joueur;
    @FXML
    private ImageView img;

    /**
     * Initializes the controller class.
     */
    File selectedfile;
    @FXML
    private Pane PaneRec;
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
    @FXML
    private AnchorPane stckHome;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void TableRecClicked(MouseEvent event) {
    }
    
    public void setId(int value) {
        this.tfId.setText(String.valueOf(value));
    }
    public void setType(String value) {
        this.tfType.setText(value);
    }
    public void setTitre(String value) {
        this.tfTitre.setText(value);
    }
    public void setDate(Date value) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        this.tfDate.setText(sdf.format(value.getTime()));
    }
    public void setDescription(String value) {
        this.taDescription.setText(value);
    }
    public void setIdp(int value) {
        this.tfIdp.setText(String.valueOf(value));
    }
    public void setImg(String path){
        File file=new File(path);
        Image img=new Image(file.toURI().toString());
        this.img.setImage(img);
    }

    @FXML
    private void actualiserCommentaire(ActionEvent event) {
        CommentaireService cs = new CommentaireService();
        List<Commentaire> lc = cs.displaycommentaires_par_article(Integer.parseInt(tfId.getText()));
        System.out.println(Integer.parseInt(tfId.getText()));
        ObservableList<Commentaire> data=FXCollections.observableArrayList(lc);
    id.setCellValueFactory(new PropertyValueFactory<>("id"));
    contenu.setCellValueFactory(new PropertyValueFactory<>("contenu"));
    date.setCellValueFactory(new PropertyValueFactory<>("date"));
    id_article.setCellValueFactory(new PropertyValueFactory<>("id_article"));
    id_joueur.setCellValueFactory(new PropertyValueFactory<>("id_joueur"));
    tableCommentaire.setItems(data);
        
    }

    @FXML
    private void ajouterCommentaire(ActionEvent event) {
        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("AddCommentaire.fxml"));
            Parent root=loader.load();
            AddCommentaireController acc=loader.getController();
            acc.setId(Integer.parseInt(tfId.getText()));
            tableCommentaire.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(DisplayArticleController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void modifier(ActionEvent event) {
        Commentaire C = new Commentaire();
        C = tableCommentaire.getSelectionModel().getSelectedItem();
        if (C == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Alerte");
            alert.setHeaderText("Alerte");
            alert.setContentText("Veillez Choisir un commentaire");
            alert.show();
        }
        else{
            taContenuM.setText(C.getContenu());

        }
    }

    @FXML
    private void modifierCommentaire(ActionEvent event) {
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Vous êtes sûr de modifier le commentaire?");
            Optional<ButtonType> action = alert.showAndWait();
            if (action.get() == ButtonType.OK) {
        Commentaire C = new Commentaire();
        C = tableCommentaire.getSelectionModel().getSelectedItem();
        CommentaireService cs = new CommentaireService();
        
        C.setContenu(taContenuM.getText());
        
        cs.updateCommentaire(C);
        JOptionPane.showMessageDialog(null, "Commentaire modifié");
        
    }
    }

    @FXML
    private void supprimerCommentaire(ActionEvent event) {
        Commentaire C = new Commentaire();
        C = tableCommentaire.getSelectionModel().getSelectedItem();
        if (C == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Alerte");
            alert.setHeaderText("Alerte");
            alert.setContentText("Veuillez Choisir un commentaire à supprimer");
            alert.show();
        }else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation ");
            alert.setHeaderText(null);
            alert.setContentText("vous êtes sûr de supprimer le commentaire?");
            Optional<ButtonType> action = alert.showAndWait();

            if (action.get() == ButtonType.OK) {
                CommentaireService CS= new CommentaireService();
                CS.supprimerCommentaire(C);
                JOptionPane.showMessageDialog(null, "Commentaire supprimé");
                loadCommentaires();
            }

        }
    }
       public void loadCommentaires() {
        CommentaireService CS = new CommentaireService();
        ArrayList<Commentaire> listeCommentaire = (ArrayList<Commentaire>) CS.displaycommentaires();

        ObservableList observableList = FXCollections.observableArrayList(listeCommentaire);
        tableCommentaire.setItems(observableList);

    }

    @FXML
    private void retour(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass()
                    .getResource("DisplayArticle.fxml"));
            Parent root = loader.load();
            tfId.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(DisplayCommentaireController.class.getName()).log(Level.SEVERE, null, ex);
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
        stckHome.getChildren().removeAll();
        stckHome.getChildren().setAll(menu);
    }

    @FXML
    private void NavRate(ActionEvent event) throws IOException {

        Parent menu = FXMLLoader.load(getClass().getResource("HomeRate.fxml"));
        stckHome.getChildren().removeAll();
        stckHome.getChildren().setAll(menu);

    }

    @FXML
    private void NavCoach(ActionEvent event) throws IOException {
        Parent menu = FXMLLoader.load(getClass().getResource("HomeCoach.fxml"));
        stckHome.getChildren().removeAll();
        stckHome.getChildren().setAll(menu);
    }

    @FXML
    private void NavEntrainemet(ActionEvent event) throws IOException {
        Parent menu = FXMLLoader.load(getClass().getResource("afficheentrainement.fxml"));
        stckHome.getChildren().removeAll();
        stckHome.getChildren().setAll(menu);

    }

    @FXML
    private void NavEquipe(ActionEvent event) throws IOException {
        Parent menu = FXMLLoader.load(getClass().getResource("HomeEquipe.fxml"));
        stckHome.getChildren().removeAll();
        stckHome.getChildren().setAll(menu);
    }

    @FXML
    private void NavArticle(ActionEvent event) throws IOException {

        Parent menu = FXMLLoader.load(getClass().getResource("DisplayArticle.fxml"));
        stckHome.getChildren().removeAll();
        stckHome.getChildren().setAll(menu);

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
        stckHome.getChildren().removeAll();
        stckHome.getChildren().setAll(menu);

    }
    
}

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
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;
import javafx.util.Pair;
import javax.swing.JOptionPane;
import static jdk.nashorn.tools.Shell.SUCCESS;
import cotakwira.services.ArticleService;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author khali
 */
public class DisplayArticleController implements Initializable {

    @FXML
    private TableColumn<Article,Integer> id;
    @FXML
    private TableColumn<Article, String> type;
    @FXML
    private TableColumn<Article, String> titre;
    @FXML
    private TableColumn<Article, String> description;
    @FXML
    private TableColumn<Article, Date> date;
    @FXML
    private TableView<Article> tableArticle;
    @FXML
    private TableColumn<Article, Integer> id_personnel;
    @FXML
    private JFXTextField tfType;
    @FXML
    private JFXTextField tfTitre;
    @FXML
    private JFXTextArea taDescription;
    @FXML
    private JFXButton BtnModifierArt;
    @FXML
    private Button modifier;
    @FXML
    private Button stats;
    @FXML
    private TableColumn<Article,String> image;
    @FXML
    private JFXTextField tf_recherche;
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
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ArticleService as = new ArticleService();
        List<Article> la = as.displayArticles();
        ObservableList<Article> data=FXCollections.observableArrayList(la);
    id.setCellValueFactory(new PropertyValueFactory<>("id"));
    type.setCellValueFactory(new PropertyValueFactory<>("type"));
    titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
    description.setCellValueFactory(new PropertyValueFactory<>("description"));
    date.setCellValueFactory(new PropertyValueFactory<>("date"));
    image.setCellValueFactory(new PropertyValueFactory<>("image"));
    id_personnel.setCellValueFactory(new PropertyValueFactory<>("id_personnel"));
    tableArticle.setItems(data);
    
    SortedList<Article> sortedData=tableViewSearchFilter(data);
    tableArticle.setItems(sortedData);    
       
        
    }    


    @FXML
    private void ajouterArticleR(ActionEvent event) {
        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("AddArticle.fxml"));
            Parent root=loader.load();
            AddArticleController aac=loader.getController();
            tableArticle.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(DisplayArticleController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void TableRecClicked(MouseEvent event) {
    }

    @FXML
    private void modifierArticle(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Vous êtes sûr de modifier l'article?");
            Optional<ButtonType> action = alert.showAndWait();
            if (action.get() == ButtonType.OK) {
        Article A = new Article();
        A = tableArticle.getSelectionModel().getSelectedItem();
        ArticleService as = new ArticleService();
        
        A.setType(tfType.getText());
        A.setTitre(tfTitre.getText());
        A.setDescription(taDescription.getText());
        
        as.updateArticle(A);
        JOptionPane.showMessageDialog(null, "Article modifié");
        
    }
    }

    @FXML
    private void modifier(ActionEvent event) {
        Article A = new Article();
        A = tableArticle.getSelectionModel().getSelectedItem();
        if (A == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Alerte");
            alert.setHeaderText("Alerte");
            alert.setContentText("Veillez Choisir un article");
            alert.show();
        }
        else{
            tfType.setText(A.getType());
            tfTitre.setText(A.getTitre());
            taDescription.setText(A.getDescription());
        }
    }

    @FXML
    private void supprimerArticle(ActionEvent event) {
        Article A = new Article();
        A = tableArticle.getSelectionModel().getSelectedItem();
        if (A == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Alerte");
            alert.setHeaderText("Alerte");
            alert.setContentText("Veuillez Choisir un article à supprimer");
            alert.show();
        }else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation ");
            alert.setHeaderText(null);
            alert.setContentText("vous êtes sûr de supprimer l'article?");
            Optional<ButtonType> action = alert.showAndWait();

            if (action.get() == ButtonType.OK) {
                ArticleService AS= new ArticleService();
                AS.supprimerArticle(A);
                JOptionPane.showMessageDialog(null, "Article supprimé");
                loadArticles();
            }

        }
    }
    public void loadArticles() {
        ArticleService AS = new ArticleService();
        ArrayList<Article> listeArticle = (ArrayList<Article>) AS.displayArticles();

        ObservableList observableList = FXCollections.observableArrayList(listeArticle);
        tableArticle.setItems(observableList);

    }

    @FXML
    private void consulterArticle(ActionEvent event) {
        Article A = new Article();
        A = tableArticle.getSelectionModel().getSelectedItem();
        if (A == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Alerte");
            alert.setHeaderText("Alerte");
            alert.setContentText("Veuillez choisir un article à consulter");
            alert.show();
        }else {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("DisplayCommentaire.fxml"));
                Parent root = loader.load();
                DisplayCommentaireController dcc = loader.getController();
                dcc.setId(A.getId());
                dcc.setType(A.getType());
                dcc.setTitre(A.getTitre());
                dcc.setDate(A.getDate());
                dcc.setDescription(A.getDescription());
                
                dcc.setImg(A.getUrl());
                System.out.println(A.getUrl());
                dcc.setIdp(A.getId_personnel());
                tfType.getScene().setRoot(root);
                
            } catch (IOException ex) {
                Logger.getLogger(DisplayArticleController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            }

        }

    @FXML
    private void afficherArticleParCommentaires(ActionEvent event) {
        ArticleService as = new ArticleService();
        List<Pair<Integer,Article>> la = as.article_plus_commentes();
        List<Article> list = new ArrayList<Article>();
            for(int i=0; i<la.size();i++)
            {
                Pair<Integer, Article> pair =  la.get(i);
                list.add(pair.getValue());
            } 
             
        ObservableList<Article> data=FXCollections.observableArrayList(list);
    id.setCellValueFactory(new PropertyValueFactory<>("id"));
    type.setCellValueFactory(new PropertyValueFactory<>("type"));
    titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
    description.setCellValueFactory(new PropertyValueFactory<>("description"));
    date.setCellValueFactory(new PropertyValueFactory<>("date"));
    id_personnel.setCellValueFactory(new PropertyValueFactory<>("id_personnel"));
   tableArticle.setItems(data);
   
   SortedList<Article> sortedData=tableViewSearchFilter(data);
    tableArticle.setItems(sortedData); 
   
    }

    @FXML
    private void stats(ActionEvent event) {
         try {
        // Load the fxml file and create a new stage for the popup.
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXBarChart.fxml"));
        AnchorPane page = (AnchorPane) loader.load();
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Statistiques article");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        Window primaryStage = null;
        dialogStage.initOwner(primaryStage);
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        // Set the persons into the controller.
        FXBarChartController controller = loader.getController();
        ArticleService as = new ArticleService();
        List<Article> la = as.displayArticles();
        ObservableList<Article> data=FXCollections.observableArrayList(la);
    id.setCellValueFactory(new PropertyValueFactory<>("id"));
    type.setCellValueFactory(new PropertyValueFactory<>("type"));
    titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
    description.setCellValueFactory(new PropertyValueFactory<>("description"));
    date.setCellValueFactory(new PropertyValueFactory<>("date"));
        image.setCellValueFactory(new PropertyValueFactory<>("image"));
    id_personnel.setCellValueFactory(new PropertyValueFactory<>("id_personnel"));
    tableArticle.setItems(data);
        controller.setArticleData(data);

        dialogStage.show();

    } catch (IOException e) {
        e.printStackTrace();
    }
    }
    
   

    

    @FXML
    private void trierArticle(ActionEvent event) {
        ArticleService as = new ArticleService();
        List<Article> la = as.trier_article();
        ObservableList<Article> data=FXCollections.observableArrayList(la);
    id.setCellValueFactory(new PropertyValueFactory<>("id"));
    type.setCellValueFactory(new PropertyValueFactory<>("type"));
    titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
    description.setCellValueFactory(new PropertyValueFactory<>("description"));
    date.setCellValueFactory(new PropertyValueFactory<>("date"));
    image.setCellValueFactory(new PropertyValueFactory<>("image"));
    id_personnel.setCellValueFactory(new PropertyValueFactory<>("id_personnel"));
    tableArticle.setItems(data);
    
    SortedList<Article> sortedData=tableViewSearchFilter(data);
    tableArticle.setItems(sortedData); 
    }
    
    private SortedList<Article> tableViewSearchFilter(ObservableList<Article> olist){
          
             // Wrap the ObservableList in a FilteredList (initially display all data).
            FilteredList<Article> filteredData = new FilteredList<>(olist, b -> true);
            // 2. Set the filter Predicate whenever the filter changes.
            tf_recherche.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(art -> {
                    // If filter text is empty, display all persons.
                    
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    // Compare first name and last name of every person with filter text.
                    String lowerCaseFilter = newValue.toLowerCase();
                    
                    if (art.getTitre().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                        return true; // Filter matches first name.
                    } else
                        return false; // Does not match.
                });
            });
            // 3. Wrap the FilteredList in a SortedList.
            SortedList<Article> sortedData = new SortedList<>(filteredData);
            // 4. Bind the SortedList comparator to the TableView comparator.
            // 	  Otherwise, sorting the TableView would have no effect.
            sortedData.comparatorProperty().bind(tableArticle.comparatorProperty());
                 // 5. Add sorted (and filtered) data to the table. 
                 return sortedData;
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cotakwira.gui3;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.StackedAreaChart;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;
import cotakwira.services.MatchCrud;
import cotakwira.services.OrganisateurCrud;
import cotakwira.services.StadeCrud;
import cotakwira.services.VoteCrud;
import cotakwira.gui.LoginController;
import cotakwira.tools.Connection;

/**
 * FXML Controller class
 *
 * @author esprit
 */
public class StatMatchController implements Initializable {
    
    @FXML
    private JFXButton BtnMatchs;
    @FXML
    private JFXButton BtnStade;
    @FXML
    private JFXButton BtnStat;
    @FXML
    private BarChart<?, ?> matchChart;
    @FXML
    private NumberAxis y;
    @FXML
    private CategoryAxis x;
    @FXML
    private Label lab_m;
    @FXML
    private Label lab_s;
    @FXML
    private Label score;

    /**
     * Initializes the controller class.
     */
    int id_org = LoginController.idLogin;
     double xOffset, yOffset;
    @FXML
    private PieChart pieChart;
    
    final CategoryAxis xAxis = new CategoryAxis();
    final NumberAxis yAxis = new NumberAxis();
    @FXML
    StackedBarChart<String, Number> sbc
            = new StackedBarChart<String, Number>(xAxis, yAxis);
    
    XYChart.Series<String, Number> series1
            = new XYChart.Series<String, Number>();
    XYChart.Series<String, Number> series2
            = new XYChart.Series<String, Number>();
    XYChart.Series<String, Number> series3
            = new XYChart.Series<String, Number>();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            
            MatchCrud mc = new MatchCrud();
            StadeCrud st = new StadeCrud();
            VoteCrud vc = new VoteCrud();
            int sr = 0;
            int s = 0;
            OrganisateurCrud oc = new OrganisateurCrud();
            int n1 = mc.getNbMatchOrg(id_org);
            int n2 = st.getNbStadeOrg(id_org);
            String req = "select * from `match`WHERE id_org= " + id_org;
            
            Statement st3;
            
            st3 = Connection.getInstance().getCnx().createStatement();
            ResultSet a = st3.executeQuery(req);
            int v = 0;
            while (a.next()) {
                v += vc.CalculerSomme(a.getInt("id_match"));
                
            }
            System.out.println("vvvvv"+v);
            s = oc.Calcul(n1, n2, v);
            oc.setScore(s, id_org);
            
            lab_m.setText(String.valueOf(mc.getNbMatchOrg(id_org)));
            lab_s.setText(String.valueOf(st.getNbStadeOrg(id_org)));
            score.setText(String.valueOf(oc.getScore(id_org)));
            
          // VoteCrud vc = new VoteCrud();
            //int somme = ;
            
            int T = mc.getNbMatchsTermine(id_org);
            int E = mc.getNbMatchsEnCours(id_org);
            int P = mc.getNbMatchsProgramme(id_org);
            
            XYChart.Series set1 = new XYChart.Series<>();
            set1.getData().add(new XYChart.Data("Programmé", P));
            set1.getData().add(new XYChart.Data("En Cours", E));
            set1.getData().add(new XYChart.Data("Terminé", T));
            
            matchChart.getData().addAll(set1);
            
            ObservableList<PieChart.Data> pieChartData
                    = FXCollections.observableArrayList(
                            new PieChart.Data("Février", mc.getNbMatchFev(id_org)),
                            new PieChart.Data("Mars", mc.getNbMatchMars(id_org)),
                            new PieChart.Data("Avril", mc.getNbMatchAvril(id_org)));
            
            pieChart.setData(pieChartData);
            
            series1.setName("Match Organisé");
            series2.setName("Stade Ajouté");
            series3.setName("Vote");
            String A;
            String r = "select * from `user` ";
            
            Statement st2;
            
            List<String> t = new ArrayList<>();
            
            st2 = Connection.getInstance().getCnx().createStatement();
            ResultSet rs = st2.executeQuery(r);
            while (rs.next()) {
                
                A = rs.getString("nom");
                
                series1.getData().add(new XYChart.Data<String, Number>(A,mc.getNbMatchOrg(rs.getInt("id"))));
                series2.getData().add(new XYChart.Data<String, Number>(A,st.getNbStadeOrg(rs.getInt("id"))));
                series3.getData().add(new XYChart.Data<String, Number>(A,oc.getVoteOrg(rs.getInt("id"))));
                
            }
            
            xAxis.setLabel("Organisateurs");
            
            yAxis.setLabel("Classement");
            
            sbc.getData().addAll(series1, series2, series3);
        } catch (SQLException ex) {
            
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
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cotakwira.gui;

import com.jfoenix.controls.JFXButton;
import cotakwira.entities.Coach;
import cotakwira.tools.Connection;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class StatistiquecoachController implements Initializable {

    @FXML
    private BarChart<?, ?> SalaryChart;
    @FXML
    private NumberAxis y;
    @FXML
    private CategoryAxis x;
    @FXML
    private JFXButton golisteee;

   

    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        List<Coach> coachList = new ArrayList<>();
         //ObservableList<BarChart.Data> data = FXCollections.observableArrayList();

        
         try {
            String requete = "SELECT * FROM coach";
            Statement st = Connection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs =  st.executeQuery(requete);
            while(rs.next()){
                Coach c = new Coach();
                

      
        
                c.setId_coach(rs.getInt(1));
                c.setNom_coach(rs.getString(2));
                c.setPrenom_coach(rs.getString(3));
                c.setDate_naissance(rs.getDate(4));
                c.setGrade(rs.getString(5));
                c.setDate_fin_contrat(rs.getDate(6));
                c.setAdresse_mail(rs.getString(7));
                c.setSalaire(rs.getFloat(8));
                
                 XYChart.Series set1 = new XYChart.Series<>();
                set1.getData().add(new XYChart.Data(rs.getString(2),rs.getFloat(8)));
                               


                SalaryChart.getData().addAll(set1);
        
        
         
              
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         

        
    }    

    @FXML
    private void goaffiche(ActionEvent event) throws IOException {
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("HomeCoach.fxml"));
        Scene home_page_scene = new Scene (home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);
        app_stage.show();
        
        
    }
    
}

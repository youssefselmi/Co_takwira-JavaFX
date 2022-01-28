/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cotakwira.gui;

import cotakwira.entities.Coach;
import cotakwira.interfaces.MyListener;
import cotakwira.services.CoachCrud;
import cotakwira.tools.Connection;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class MarketController implements Initializable {

    @FXML
    private GridPane grid;
    @FXML
    private Label nomcoach;
    @FXML
    private Label prenomcoach;
    @FXML
    private VBox coachcard;
    @FXML
    private ImageView image_coach;
    
    private List<Coach> coachs = new ArrayList<>();

   
    
    private MyListener myListener;
    @FXML
    private Label formationprefere;
    @FXML
    private ImageView image_formation;
    
     private Image image;
    private Image imagee;
    @FXML
    private Label datenais;
    @FXML
    private Label salaire;
    @FXML
    private Label contrat;
    @FXML
    private Label trophelocaux;
    @FXML
    private Label trophelocaux1;
    @FXML
    private ScrollPane scroll;


//      private List<Coach> getData() {
//   
//          List<Coach> coachs = new ArrayList<>();
//         CoachCrud pcd = new CoachCrud();
//         coachs=pcd.afficherCoachs();
//        
//        
//        
//        return coachs;
//      }
    
    public List<Coach> getData() {



 List<Coach> coachs = new ArrayList<>();
 
 
 
 
 
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
                c.setNb_trophe_locaux(rs.getInt(9));
                c.setNb_trophe_internationaux(rs.getInt(10));
                c.setFormation_prefere(rs.getString(11));
                c.setImage_coach(rs.getString("image_coach"));
                 
                c.setImage_formation(rs.getString("image_formation"));
                
             

              
                coachs.add(c);
                     
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return coachs;
    }
    
    
    
    
    
        private void setChosenCoach(Coach coach) {
        try {
            nomcoach.setText(coach.getNom_coach());
            prenomcoach.setText(coach.getPrenom_coach());
            formationprefere.setText(coach.getFormation_prefere());
            datenais.setText(coach.getDate_naissance().toString());
            salaire.setText(Float.toString(coach.getSalaire()));
            contrat.setText(coach.getDate_fin_contrat().toString());
            trophelocaux.setText(Integer.toString(coach.getNb_trophe_locaux()));
            trophelocaux1.setText(Integer.toString(coach.getNb_trophe_internationaux()));

            
            Image image = new Image(new FileInputStream((coach.getImage_coach())));
            image_coach.setImage(image);
            
            
             Image imagee = new Image(new FileInputStream((coach.getImage_formation())));
            image_formation.setImage(imagee);
//            
            /* coachcard.setStyle("-fx-background-color: #" + coach.getColor() + ";\n" +
            "    -fx-background-radius: 30;");*/
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MarketController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          coachs.addAll(getData());
        if (coachs.size() > 0) {
            setChosenCoach(coachs.get(0));
            myListener = new MyListener() {
             
                @Override
                public void onClickListener(Coach coach) {
                    setChosenCoach(coach);
                }
            };
        }
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < coachs.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemController itemController = fxmlLoader.getController();
                itemController.setData(coachs.get(i),myListener);

                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }



    }    
    
}

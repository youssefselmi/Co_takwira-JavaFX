/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cotakwira.gui;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.sun.javafx.collections.ElementObservableListDecorator;
import cotakwira.entities.Article;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;
import cotakwira.services.ArticleService;
import cotakwira.tools.Connection;

/**
 * FXML Controller class
 *
 * @author khali
 */
public class ModifierArticleController implements Initializable {

    @FXML
    private JFXTextField tfType;
    @FXML
    private JFXTextField tfTitre;
    @FXML
    private JFXTextArea taDescription;
    @FXML
    private JFXComboBox<Integer> combo_id;

    /**
     * Initializes the controller class.
     */
    ArticleService as = new ArticleService();
    Article a = new Article();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            String req="select id from article";
            PreparedStatement pst = Connection.getInstance().getCnx()
                    .prepareStatement(req);
            ResultSet rs=pst.executeQuery();
            ObservableList<Integer> id = null;
            List<Integer> list = new ArrayList<>();
            while(rs.next()){
             
             list.add(rs.getInt("id"));
            
            }
            id = FXCollections
                    .observableArrayList(list);
            combo_id.setItems(id);
            
        } catch (SQLException ex) {
            Logger.getLogger(ModifierArticleController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
    
        @FXML
    private void getId(MouseEvent event) {
        combo_id.setOnAction(e ->{
            String req="select type,titre,description from article where id=?";
            try {
                    PreparedStatement pst = Connection.getInstance().getCnx()
                    .prepareStatement(req);             
                    pst.setInt(1,(Integer)combo_id.getSelectionModel().getSelectedItem());
                ResultSet rs=pst.executeQuery();
                while(rs.next()){
                    tfType.setText(rs.getString("type"));
                    tfTitre.setText(rs.getString("titre"));
                    taDescription.setText(rs.getString("description"));
                 
                }
            } catch (SQLException ex) {
                Logger.getLogger(ModifierArticleController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        tfType.setText(a.getType());
        tfTitre.setText(a.getTitre());
        taDescription.setText(a.getDescription());
        
    }

    @FXML
    private void modifierReclamation(ActionEvent event) {
        
       
        as.updateArticle(new Article(combo_id.getSelectionModel().getSelectedItem(), tfType.getText(), tfTitre.getText(), taDescription.getText(), 1));       
        JOptionPane.showMessageDialog(null, "Article modifié");
       
    }


    
}

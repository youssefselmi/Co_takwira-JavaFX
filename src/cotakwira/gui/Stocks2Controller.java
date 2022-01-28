/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cotakwira.gui;

import com.jfoenix.controls.JFXTextField;
import cotakwira.entities.stocks;
import cotakwira.services.StocksCRUD;
import cotakwira.tools.Connection;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author yassin
 */
public class Stocks2Controller implements Initializable {

    public JFXTextField tfIP;
    public JFXTextField tfNP;
    public JFXTextField tfPU;
    public JFXTextField tfQ;
    @FXML
    private TableView<stocks> table_stocks;

    @FXML
    private TableColumn<stocks, Integer> col_id;

    @FXML
    private TableColumn<stocks, String> col_nom;

    @FXML
    private TableColumn<stocks, Integer> col_quantite;

    @FXML
    private TableColumn<stocks, Float> col_prix;

    @FXML
    private TextField filterField;
    ObservableList<stocks> listM;
    ObservableList<stocks> dataList;

    int index = -1;
    @FXML
    private Button ajoutbtn;
    @FXML
    private Button btnshop;
    @FXML
    private Button btnshopp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        UpdateTable();
        search_user();
        // Code Source in description
    }

    public void Add_users() {
        //  MyConnection conn = new MyConnection();
        StocksCRUD pcd = new StocksCRUD();
        String sql = "insert into stocks (ID_Produit,Nom_Produit,Quantite,Prix_Unite)values(?,?,?,? )";
        try {
            PreparedStatement pst = Connection.getInstance().getCnx().prepareStatement(sql);
            pst.setString(1, tfIP.getText());
            pst.setString(2, tfNP.getText());
            pst.setString(3, tfQ.getText());
            pst.setString(4, tfPU.getText());
            pst.execute();

            JOptionPane.showMessageDialog(null, "Users Add succes");
            UpdateTable();
            search_user();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void UpdateTable() {
        col_id.setCellValueFactory(new PropertyValueFactory<stocks, Integer>("ID_Produit"));
        col_nom.setCellValueFactory(new PropertyValueFactory<stocks, String>("Nom_Produit"));
        col_quantite.setCellValueFactory(new PropertyValueFactory<stocks, Integer>("Quantite"));
        col_prix.setCellValueFactory(new PropertyValueFactory<stocks, Float>("Prix_Unite"));

        //    MyConnection mc =new MyConnection();
        StocksCRUD pcd = new StocksCRUD();
        listM = pcd.getDatausers();
        table_stocks.setItems(listM);
    }

    @FXML
    public void sendadd(ActionEvent actionEvent) {
        Add_users();
    }

    //////// methode select users ///////
    void getSelected(MouseEvent event) {
        index = table_stocks.getSelectionModel().getSelectedIndex();
        if (index <= -1) {

            return;
        }
        tfIP.setText(col_id.getCellData(index).toString());
        tfPU.setText(col_nom.getCellData(index).toString());
        tfQ.setText(col_quantite.getCellData(index).toString());
        tfPU.setText(col_prix.getCellData(index).toString());

    }

    @FXML
    public void Edit() {
        try {
            String value1 = tfIP.getText();
            String value2 = tfNP.getText();
            String value3 = tfQ.getText();
            String value4 = tfPU.getText();
            String sql = "update stocks set ID_Produit= '" + value1 + "',Nom_Produit= '" + value2 + "',Quantite= '"
                    + value3 + "',Prix_Unite= '" + value4 + "' where ID_Produit='" + value1 + "' ";
            //StocksCRUD pcd= new StocksCRUD();
            // pcd.modifierStocks(value1,value2,value3,value4);
            PreparedStatement pst = Connection.getInstance().getCnx().prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Update");
            UpdateTable();
            search_user();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    public void Delete() {

        String sql = "delete from stocks where ID_Produit = ?";

        try {
            PreparedStatement pst = Connection.getInstance().getCnx().prepareStatement(sql);
            pst.setString(1, tfIP.getText());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Delete");
            UpdateTable();
            search_user();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    void search_user() {
        StocksCRUD pcd = new StocksCRUD();
        col_id.setCellValueFactory(new PropertyValueFactory<stocks, Integer>("ID_Produit"));
        col_nom.setCellValueFactory(new PropertyValueFactory<stocks, String>("Nom_Produit"));
        col_quantite.setCellValueFactory(new PropertyValueFactory<stocks, Integer>("Quantite"));
        col_prix.setCellValueFactory(new PropertyValueFactory<stocks, Float>("Prix_Unite"));
        dataList = pcd.getDatausers();
        table_stocks.setItems(dataList);
        FilteredList<stocks> filteredData = new FilteredList<>(dataList, b -> true);
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(stocks -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (stocks.getNom_Produit().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches username
                } else {
                    return false; // Does not match.
                }
            });
        });

        SortedList<stocks> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table_stocks.comparatorProperty());
        table_stocks.setItems(sortedData);

    }

    public void BtnAjourerRec(ActionEvent actionEvent) {
    }

    @FXML
    public void TableRecClicked(MouseEvent mouseEvent) {
    }

    public void BtnModifierRec(ActionEvent actionEvent) {
    }

    public void affich(ActionEvent actionEvent) {
        UpdateTable();
        search_user();
    }

    @FXML
    public void shopi(ActionEvent actionEvent) throws IOException {
        Stage stage2 = new Stage();
        Parent root;
        root = FXMLLoader.load(getClass().getResource("shop.fxml"));
        stage2.setScene(new Scene(root, 1214, 530));
        stage2.show();
    }

    @FXML
    public void supprimerbtn(ActionEvent actionEvent) {
        /*StocksCRUD pcd= new StocksCRUD();
        int value1 = Integer.parseInt(tfIP.getText());
        String value2 = tfNP.getText();
        int value3 = Integer.parseInt(tfQ.getText());
        float value4 = Float.parseFloat(tfPU.getText());
        stocks tir= new stocks(value1,value2,value3,value4);
        pcd.supprimerStocks(tir);

         */
        Delete();

    }

}

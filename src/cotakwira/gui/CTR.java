/*package takwira.GUI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.util.ResourceBundle;
        import java.net.URL;
        import java.util.ResourceBundle;
        import javafx.collections.FXCollections;
        import javafx.collections.ObservableList;
        import javafx.collections.transformation.FilteredList;
        import javafx.collections.transformation.SortedList;
        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.fxml.Initializable;
        import javafx.scene.control.Label;
        import javafx.scene.control.TableColumn;
        import javafx.scene.control.TableView;
        import javafx.scene.control.TextField;
        import javafx.scene.control.cell.PropertyValueFactory;
import takwira.entities.stocks;

public class CTR implements Initializable {

    @FXML
    private Label label;
    @FXML private TextField filterField;
    @FXML private TableView<stocks> tableview;
    @FXML private TableColumn<stocks, String> ID_Produit;
    @FXML private TableColumn<stocks, String> Nom_Produit;
    @FXML private TableColumn<stocks, String> Quantite;
    @FXML private TableColumn<stocks, String> Prix_unite;



    //observalble list to store data
    private final ObservableList<stocks> dataList = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle rb) {

        EmpID.setCellValueFactory(new PropertyValueFactory<>("ID_Produit"));
        empName.setCellValueFactory(new PropertyValueFactory<>("Nom_Produit"));
        empEmail.setCellValueFactory(new PropertyValueFactory<>("Quantite"));
        department.setCellValueFactory(new PropertyValueFactory<>("Prix_unite"));


        Employee emp1 = new Employee(112, "AMIT", "ams@gmail.com", "Finance", 30000);
        Employee emp2 = new Employee( 115, "Peter", "peter@gmail.com", "Defence System", 40000);
        Employee emp3 = new Employee( 116, "SAM", "sam@gmail.com", "Radar Anaysist", 80000);
        Employee emp4 = new Employee(117, "Jhon", "jhon@gmail.com", "Ground Staff", 80000);

        dataList.addAll(emp1,emp2, emp3, emp4);

        // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<stocks> filteredData = new FilteredList<>(dataList, b -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(employee -> {
                // If filter text is empty, display all persons.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (stocks..toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true; // Filter matches first name.
                } else if (stocks.getDepartment().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                }
                else if (String.valueOf(employee.getSalary()).indexOf(lowerCaseFilter)!=-1)
                    return true;
                else
                    return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList.
        SortedList<stocks> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(tableview.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tableview.setItems(sortedData);


    }

}

 */
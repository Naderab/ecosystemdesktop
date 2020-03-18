/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pointcollect.services;

import edu.pointcollect.entities.Markers;
import edu.pointcollect.entities.SousCategorieTrie;
import edu.pointcollect.entities.TypePoint;
import edu.pointcollect.entities.Validation;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import np.com.ngopal.control.AutoFillTextBox;
import org.controlsfx.control.textfield.TextFields;
import org.json.simple.JSONArray;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * FXML Controller class
 *
 * @author Khouloud
 */
public class CrudAddressController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private ComboBox<String> comboboxtype;
    @FXML
    private TableView<Markers> table;
    @FXML
    private TableColumn<Markers, String> idTable;
    @FXML
    private TableColumn<Markers, String> nomTable;
    @FXML
    private TableColumn<Markers, String> addressTable;
    @FXML
    private TableColumn<Markers, Double> lngTable;
    @FXML
    private TableColumn<Markers, Double> latTable;
    @FXML
    private TableColumn<Markers, Integer> typeTable;
    @FXML
    private TableColumn<Markers, String> tableDetails;
    @FXML
    private TextField filterField;
    @FXML
    private TextField txtAddress;
    @FXML
    private TextField txtLat;
    @FXML
    private TextField txtLng;
    @FXML
    private TextField txtName;
    @FXML
    private Button okBtn;
    @FXML
    private Button ajouterAddBtn;

    double lat = 0;
    double lng = 0;
    JSONObject testDataObject = null;
    AddressPointCollectCrud crud = new AddressPointCollectCrud();
    boolean verifadd ;
    boolean verifnomm;
    boolean verifTypee;
    @FXML
    private Button ButtonCommande;
    @FXML
    private Label verifadresse;
    @FXML
    private Label verifnom;
     @FXML
    private Label verifType;
    @FXML
    private Label ecosystem;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    
        
        final TypePointCollectCrud p = new TypePointCollectCrud();
        final ArrayList arrayList = (ArrayList) p.listerPointCollectName();
        final ObservableList observableList = FXCollections.observableArrayList((Collection) arrayList);
        comboboxtype.getItems().addAll(observableList);

        table.setEditable(true);
        nomTable.setEditable(true);
        final AddressPointCollectCrud a = new AddressPointCollectCrud();
        final ArrayList arrayListAddress = (ArrayList) a.listerAdressePointCollect();
        final ObservableList observableListAddress = FXCollections.observableArrayList((Collection) arrayListAddress);
        table.setItems(observableListAddress);
        idTable.setCellValueFactory((Callback) new PropertyValueFactory("id"));
        nomTable.setCellValueFactory((Callback) new PropertyValueFactory("name"));
        addressTable.setCellValueFactory((Callback) new PropertyValueFactory("address"));
        lngTable.setCellValueFactory((Callback) new PropertyValueFactory("ing"));
        latTable.setCellValueFactory((Callback) new PropertyValueFactory("lat"));
        typeTable.setCellValueFactory((Callback) new PropertyValueFactory("typePointCollect"));

        tableDetails.setCellValueFactory((Callback) new PropertyValueFactory("DUMMY"));

        TableColumn actionCol = new TableColumn("Action");
        actionCol.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));

        Callback<TableColumn<Markers, String>, TableCell<Markers, String>> cellFactory
                = //
                new Callback<TableColumn<Markers, String>, TableCell<Markers, String>>() {
            public TableCell call(final TableColumn<Markers, String> param) {
                final TableCell<Markers, String> cell = new TableCell<Markers, String>() {

                    final Button btn = new Button("supprimer");

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btn.setOnAction(event -> {
                                Markers p = getTableView().getItems().get(getIndex());
                                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                alert.setTitle("Confirmation");
                                alert.setHeaderText(null);
                                alert.setContentText("Voulez vous supprimer ce type");
                                Optional<ButtonType> action = (Optional<ButtonType>) alert.showAndWait();
                                if (action.get() == ButtonType.OK) {
                                    Markers m = new Markers();

                                    crud.supprimerAdressePointCollect(p.getId());
                                }
                                
                                txtName.clear();
                                txtAddress.clear();
                                txtLat.clear();
                                txtLng.clear();
                                try {
                                    refreshScene(event);
                                } catch (IOException ex) {
                                    Logger.getLogger(CrudAddressController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            });
                            setGraphic(btn);
                            setText(null);
                        }
                    }
                };
                return cell;
            }
        };

        tableDetails.setCellFactory(cellFactory);

        table.setItems(observableListAddress);

        FilteredList<Markers> filteredData = new FilteredList<>(observableListAddress, pp -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(person -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (person.getName().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (person.getAddress().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }
                return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Markers> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(table.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        table.setItems(sortedData);

        final AddressPointCollectCrud aa = new AddressPointCollectCrud();
        // final JSONObject arrayListAddressa = null;
        try {
            aa.list();
        } catch (IOException ex) {
            Logger.getLogger(CrudAddressController.class.getName()).log(Level.SEVERE, null, ex);
        }

        final ObservableList data = FXCollections.observableArrayList((Collection) arrayList);

        //    TextFields.bindAutoCompletion(txtAddress,data);
        // txtAddress = new AutoFillTextBox(data);
        //  AutoFillTextBox autobox = new AutoFillTextBox(); 
        //  TextFields.bindingAutoCompletion(txtAddress,ch);
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("address.json")) {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray employeeList = (JSONArray) obj;
            System.out.println(employeeList);

            //Iterate over  array
            employeeList.forEach(emp -> parseTestData((JSONObject) emp));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        okBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                JSONParser jsonParser = new JSONParser();

                try (FileReader reader = new FileReader("address.json")) {
                    //Read JSON file
                    Object obj = jsonParser.parse(reader);

                    JSONArray employeeList = (JSONArray) obj;
                    System.out.println(employeeList);

                    //Iterate over  array
                    employeeList.forEach(emp -> parseTestData2((JSONObject) emp));

                } catch (FileNotFoundException ee) {
                    ee.printStackTrace();
                } catch (IOException ee) {
                    ee.printStackTrace();
                } catch (ParseException ee) {
                    ee.printStackTrace();
                }

                System.out.println(txtAddress.getText());
            }
        });

    }

    private void parseTestData(JSONObject employee) {
        //Get employee object within list
        testDataObject = (JSONObject) employee.get("markers");

        String description = (String) testDataObject.get("address");

        TextFields.bindAutoCompletion(txtAddress, description);

    }

    private void parseTestData2(JSONObject employee) {
        //Get employee object within list
        JSONObject testDataObject2 = (JSONObject) employee.get("markers");

        String description = (String) testDataObject2.get("address");

        // System.out.println(description);
        //  TextFields.bindAutoCompletion(txtAddress,description);
        if (testDataObject2.containsValue(txtAddress.getText())) {
            //System.out.println(testDataObject2.get("lat"));
            lat = (double) testDataObject2.get("lat");
            lng = (double) testDataObject2.get("lng");
            txtLat.setText(String.valueOf(lat));
            txtLng.setText(String.valueOf(lng));
        }

    }

    @FXML
    private void ajouterAddress(final ActionEvent event) throws IOException, SQLException {
      /*  if (txtName.getText()!="" && txtAddress.getText()!=""&&txtLat.getText()!=""&& txtLng.getText()!=""){
            ajouterAddBtn.setDisable(false);
        }*/
      
      Validation v=new Validation();
      verifadd= v.textFieldIsNull(txtAddress,verifadresse,"Champs adresse obligatoire");
      verifnomm= v.textFieldIsNull(txtName,verifnom,"Champs nom obligatoire");
      
      
        TypePointCollectCrud s = new TypePointCollectCrud();
         AddressPointCollectCrud produitService = new AddressPointCollectCrud();
        int ch = produitService.selectSlug();
        String slug = "-"+String.valueOf(ch);
        Markers p = new Markers(txtName.getText(), txtAddress.getText(), Double.valueOf(txtLat.getText()), Double.valueOf(txtLng.getText()),slug);
        System.err.println(p.toString());
       
        String output = comboboxtype.getSelectionModel().getSelectedItem().toString();
       

        int a = s.selectIdName(output);

        System.out.println("aaaaa" + a);

        if (produitService.SearchAdressePointCollect(txtAddress.getText())==false && verifadd==false && verifnomm==false){
        produitService.ajouterPointCollect(p, a);
         new Alert(Alert.AlertType.CONFIRMATION, "Adresse ajoutée avec sucess").show();
        }
        else {
             new Alert(Alert.AlertType.WARNING, "Cette adresse existe deja !").show();
        }
       

        txtName.clear();
        txtAddress.clear();
        txtLat.clear();
        txtLng.clear();
        refreshScene(event);

    }

    @FXML
    private void actionTypePoint(final ActionEvent event) throws IOException {
        /*AnchorPane pane = FXMLLoader.load(getClass().getResource("/edu/pointcollect/gui/CrudType.fxml" ));
            AnchorPaneTypePoint.getChildren().setAll(pane);   */
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/edu/pointcollect/gui/CrudType.fxml"));
        Parent profileParent = loader.load();

        Scene gestionProfilScene = new Scene(profileParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(gestionProfilScene);
        window.show();

    }

    @FXML
    private void actionCategorie(final ActionEvent event) throws IOException {
        /*AnchorPane pane = FXMLLoader.load(getClass().getResource("/edu/pointcollect/gui/CrudType.fxml" ));
            AnchorPaneTypePoint.getChildren().setAll(pane);   */
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/edu/pointcollect/gui/CrudCategorie.fxml"));
        Parent profileParent = loader.load();

        Scene gestionProfilScene = new Scene(profileParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(gestionProfilScene);
        window.show();

    }

    private void refreshScene(final ActionEvent event) throws IOException {
        /*AnchorPane pane = FXMLLoader.load(getClass().getResource("/edu/pointcollect/gui/CrudType.fxml" ));
            AnchorPaneTypePoint.getChildren().setAll(pane);   */
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/edu/pointcollect/gui/CrudAddress.fxml"));
        Parent profileParent = loader.load();

        Scene gestionProfilScene = new Scene(profileParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(gestionProfilScene);
        window.show();

    }

   @FXML
    private void fontPageCategorie(javafx.scene.input.MouseEvent event) {
        
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/edu/EcoSystem/gui/gestionUser.fxml"));
            Parent profileParent = loader.load();
            
            
            Scene gestionProfilScene = new Scene (profileParent);
            
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(gestionProfilScene);
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(CrudAddressController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

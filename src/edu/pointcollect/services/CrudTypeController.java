/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pointcollect.services;

import static com.sun.deploy.config.JREInfo.clear;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author Khouloud
 */
import edu.pointcollect.entities.*;
import java.net.*;
import javafx.scene.control.cell.*;
import javafx.util.*;
import javafx.collections.transformation.*;
import javafx.collections.*;
import javafx.beans.value.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class CrudTypeController implements Initializable
{
    TypePointCollectCrud crud = new TypePointCollectCrud();
    @FXML
    private TextField name;
    @FXML
    private TextArea description;
    @FXML
    private Button ajouter;
    @FXML
    private TableView<TypePoint> typeTable;
    @FXML
    private TableColumn<TypePoint, String> tableId;
    @FXML
    private TableColumn<TypePoint, String> tableName;
    @FXML
    private TableColumn<TypePoint, String> tableDescription;
    @FXML
    private TableColumn<TypePoint, String> tableDetails;
    private Button btnNew;
    @FXML
    private TextField nameUpdate;
    @FXML
    private TextField idUpdate;
    @FXML
    private TextArea desUpdate;
    @FXML
    private TextField filterField;
    @FXML
    private AnchorPane AnchorPaneTypePoint;
    @FXML
    private Button modifier;
    @FXML
    private Button supprimer;
    @FXML
    private Label verifTitre;
     boolean verifTypee;
    @FXML
    private Label ecosystem;

    public void initialize( URL location,  ResourceBundle resources) {
        final TypePointCollectCrud p = new TypePointCollectCrud();
        final ArrayList arrayList = (ArrayList)p.listerPointCollect();
        final ObservableList observableList = FXCollections.observableArrayList((Collection)arrayList);
        typeTable.setItems(observableList);
        tableId.setCellValueFactory((Callback)new PropertyValueFactory("id"));
        tableName.setCellValueFactory((Callback)new PropertyValueFactory("name"));
        tableDescription.setCellValueFactory((Callback)new PropertyValueFactory("description"));
        tableDetails.setCellValueFactory((Callback)new PropertyValueFactory("DUMMY"));
    
        
         TableColumn actionCol = new TableColumn("Action");
        actionCol.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));

        Callback<TableColumn<TypePoint, String>, TableCell<TypePoint, String>> cellFactory
                = //
                new Callback<TableColumn<TypePoint, String>, TableCell<TypePoint, String>>() {
            public TableCell call(final TableColumn<TypePoint, String> param) {
                final TableCell<TypePoint, String> cell = new TableCell<TypePoint, String>() {

                    final Button btn = new Button("Afficher");

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btn.setOnAction(event -> {
                            TypePoint p = getTableView().getItems().get(getIndex());
                            idUpdate.setText( String.valueOf(p.getId()));
                            nameUpdate.setText(p.getName());
                             desUpdate.setText(p.getDescription());
                               // System.out.println("++"+p.getName());
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

        typeTable.setItems(observableList);
       

    
    
        
         FilteredList<TypePoint> filteredData = new FilteredList<>(observableList, pp -> true);
        
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
                } else if (person.getDescription().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }
                return false; // Does not match.
            });
        });
        
        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<TypePoint> sortedData = new SortedList<>(filteredData);
        
        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(typeTable.comparatorProperty());
        
        // 5. Add sorted (and filtered) data to the table.
        typeTable.setItems(sortedData);
        
    }
    
    @FXML
    private void ajouterType( ActionEvent event) throws IOException {
        Validation v=new Validation();
      verifTypee= v.textFieldIsNull(name,verifTitre,"Champs titre obligatoire");
      
         TypePoint p = new TypePoint(name.getText(), description.getText());
         TypePointCollectCrud produitService = new TypePointCollectCrud();
        
        produitService.ajouterPointCollect(p);
         
       //  FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/ecosystem/gui/CrudType.fxml"));
       /*  Parent root;
        root=loader.load();
        ajouter.getScene().setRoot(root);*/
       new Alert(Alert.AlertType.INFORMATION, "Ce type est ajoute avec succes !!").show();
       name.clear();
       description.clear();
        refreshScene(event);
       
    }
    
     private void dialog(Alert.AlertType alertType,String s){
        Alert alert = new Alert(alertType,s);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle("Info");
        alert.showAndWait();
    }
    
    @FXML
    private void supprimerType( ActionEvent event) throws IOException {
          Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Voulez vous supprimer ce type");
         Optional<ButtonType> action = (Optional<ButtonType>)alert.showAndWait();
        if (action.get() == ButtonType.OK) {
       TypePoint m = new TypePoint();
        
        crud.supprimerPointCollect(Integer.parseInt(idUpdate.getText()));
        }
        idUpdate.clear();
         nameUpdate.clear();
       desUpdate.clear();
        refreshScene(event);
    }
    
    @FXML
    protected void modifierType( ActionEvent event) throws IOException {
         Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Voulez vous modifier ce type");
         Optional<ButtonType> action = (Optional<ButtonType>)alert.showAndWait();
        if (action.get() == ButtonType.OK) {
             TypePoint m = new TypePoint();
            m.setName(nameUpdate.getText());
            m.setDescription(desUpdate.getText());
             int i = Integer.parseInt(idUpdate.getText());
            crud.modifierPointCollect(m, i);
        }
        idUpdate.clear();
         nameUpdate.clear();
       desUpdate.clear();
        refreshScene(event);
    }
    
    @FXML
    private void actionCategorie(final ActionEvent event) throws IOException
    {
     /*AnchorPane pane = FXMLLoader.load(getClass().getResource("/edu/pointcollect/gui/CrudType.fxml" ));
            AnchorPaneTypePoint.getChildren().setAll(pane);   */
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/edu/pointcollect/gui/CrudCategorie.fxml"));
        Parent profileParent = loader.load();
        
        
        Scene gestionProfilScene = new Scene (profileParent);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(gestionProfilScene);
        window.show();
       
    }
      @FXML
    private void actionAddress(final ActionEvent event) throws IOException
    {
     /*AnchorPane pane = FXMLLoader.load(getClass().getResource("/edu/pointcollect/gui/CrudType.fxml" ));
            AnchorPaneTypePoint.getChildren().setAll(pane);   */
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/edu/pointcollect/gui/CrudAddress.fxml"));
        Parent profileParent = loader.load();
        
        
        Scene gestionProfilScene = new Scene (profileParent);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(gestionProfilScene);
        window.show();
       
    }
    
    public void refreshScene(final ActionEvent event) throws IOException
    {
         FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/edu/pointcollect/gui/CrudType.fxml"));
        Parent profileParent = loader.load();
        
        
        Scene gestionProfilScene = new Scene (profileParent);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(gestionProfilScene);
        window.show();
    }

   @FXML
    private void fontPageCategorie(javafx.scene.input.MouseEvent event) {
        
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/edu/pointcollect/gui/ListCategorieFront.fxml"));
            Parent profileParent = loader.load();
            
            
            Scene gestionProfilScene = new Scene (profileParent);
            
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(gestionProfilScene);
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(CrudTypeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
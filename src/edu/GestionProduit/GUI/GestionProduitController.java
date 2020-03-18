/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.GestionProduit.GUI;

import edu.GestionProduit.entities.produit2;
import edu.GestionProduit.service.CrudProduit;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Abir
 */
public class GestionProduitController implements Initializable {

    private CrudProduit crudProduit;

    @FXML
    private Button tfsupp;
    @FXML
    private Button tfmodif;
    @FXML
    private Button tfpublier;
    @FXML
    private TableView<produit2> tableadminiproduit;
    @FXML
    private TableColumn<produit2, String> tftabletitre;
    @FXML
    private TableColumn<produit2, String> tftabledescription;
    @FXML
    private TableColumn<produit2, String> tftableimage;
    @FXML
    private TableColumn<produit2, Integer> tftablepublie;
    @FXML
    private Button btnchercher;
    private TextField Chercher;
    @FXML
    private TextField filterField;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        crudProduit = new CrudProduit();
        initColumns();
        try {
            tableadminiproduit.setItems(crudProduit.getAllProduit2());
        } catch (SQLException ex) {
            Logger.getLogger(GestionProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void initColumns() {
        tftabletitre.setCellValueFactory(new PropertyValueFactory<produit2, String>("titre"));
        tftabledescription.setCellValueFactory(new PropertyValueFactory<produit2, String>("description"));
        tftableimage.setCellValueFactory(new PropertyValueFactory<produit2, String>("image"));
        tftablepublie.setCellValueFactory(new PropertyValueFactory<produit2, Integer>("publie"));

    }

    private void cliquerrechercher(ActionEvent event) throws SQLException {

        // tableadminiproduit.setItems(crudProduit.rechercherProduit(btnchercher.getValue(), Chercher.getText()));
    }
    
     
    @FXML
    private void cluiquesupprimer(ActionEvent event) throws SQLException {

        if (tableadminiproduit.getSelectionModel().getSelectedItem() != null) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Partner");
            alert.setHeaderText(null);
            alert.setContentText("Êtes-vous sûr de vouloir supprimer cette annonce ?");

            Optional<ButtonType> optionDeleteProduitAlert = alert.showAndWait();
            if (optionDeleteProduitAlert.get() == ButtonType.OK) {
                crudProduit.SupprimerProduit(tableadminiproduit.getSelectionModel().getSelectedItem().getId());
                Alert Alert1 = new Alert(Alert.AlertType.INFORMATION);
                Alert1.setTitle("Supprimer une annonce");
                Alert1.setHeaderText("Resultat:");
                Alert1.setContentText("Annonce supprimée avec succès!");

                Alert1.showAndWait();

            } else if (optionDeleteProduitAlert.get() == ButtonType.CANCEL) {

            }

        } else {
            Alert Alert1 = new Alert(Alert.AlertType.WARNING);
            Alert1.setTitle("Select Annonce");
            Alert1.setHeaderText(null);
            Alert1.setContentText("Vous devez d'abord sélectionner une annonce!");
            Alert1.showAndWait();
        }

        tableadminiproduit.setItems(crudProduit.getAllProduit2());
    }

    @FXML
    private void cliquermodifier(ActionEvent event) throws IOException {
        if (tableadminiproduit.getSelectionModel().getSelectedItem() != null) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/edu/EcoSystem/GUI/UpdateProduit.fxml"));
            Parent updatePoduitParent = loader.load();

            //access showPartnersParent 
            UpdateProduitController updateProduitController = loader.getController();
            //Partner p = new Partner();
            //p.setName("Test");
            //updatePartnerController.initPartner(Partners_Show_TableView.getSelectionModel().getSelectedItem());
            updateProduitController.setProduit2(tableadminiproduit.getSelectionModel().getSelectedItem());
            updateProduitController.setAction("update");

            Scene updatePartnerScene = new Scene(updatePoduitParent);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow(); // = new Stage() fel integration
            window.setScene(updatePartnerScene);
            window.show();
        } else {
            //Alert Select Annonce :
            Alert ProduitAlert = new Alert(Alert.AlertType.WARNING);
            ProduitAlert.setTitle("Sélectionner une annonce");
            ProduitAlert.setHeaderText(null);
            ProduitAlert.setContentText("Vous devez d'abord sélectionner une annonce!");
            ProduitAlert.showAndWait();
        }

    }

    @FXML
    private void cliquepublie(ActionEvent event) throws SQLException {
        if (tableadminiproduit.getSelectionModel().getSelectedItem() != null) {//pour la selection d'un champ
            if (tableadminiproduit.getSelectionModel().getSelectedItem().getPublie() == 0) {//s'il n'est pas publie
                crudProduit.publierProduit(tableadminiproduit.getSelectionModel().getSelectedItem().getId());
                tableadminiproduit.setItems(crudProduit.getAllProduit2());
            } else {
                
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Annonce ");
                alert.setHeaderText(null);
                alert.setContentText("Annonce deja publier!");
                alert.showAndWait();
            }

        } else {
            
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Sélectionnez une Annonce");
            alert.setHeaderText(null);
            alert.setContentText("Vous devez d'abord sélectionner une annonce!");
            alert.showAndWait();
            
        }
    }

    @FXML
    private void Search(ActionEvent event) throws SQLException {
        tableadminiproduit.setItems(crudProduit.rechercherProduit(filterField.getText()));
        
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.EcoSystem.controller;

import com.jfoenix.controls.JFXButton;
import edu.EcoSystem.services.DevisCRUD;
import edu.EcoSystem.services.OffreCRUD;
import edu.EcoSystem.entities.Devis;
import edu.EcoSystem.entities.Offre;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ramzi
 */
public class ListBackController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private AnchorPane rootLayout;
    private Stage primaryStage;

    @FXML
    private TableView<Offre> offreTable;

    @FXML
    private TableColumn<Offre, String> tab_titre;

    @FXML
    private TableColumn<Offre, String> tab_type;

    @FXML
    private TableColumn<Offre, String> tab_desc;

    @FXML
    private TableColumn<Offre, Date> tab_dateo;

    @FXML
    private TableColumn<Offre, Date> tab_datev;

    @FXML
    private TextField rechercherOffre;
    @FXML
    private Button demandeDevis;
    @FXML
    private TableView<Offre> demandedevisTable;
    @FXML
    private TableColumn<Offre, String> tab_titred;
    @FXML
    private TableColumn<Offre, String> tab_typed;
    @FXML
    private TableColumn<Offre, String> tab_descd;
    @FXML
    private TableColumn<Offre, Date> tab_dateod;
    @FXML
    private TableColumn<Offre, Date> tab_datevd;
    @FXML
    private TextField rechercherdemande;
    @FXML
    private Button annulerdemandeDevis;
    @FXML
    private TableView<Devis> devisTable;
    @FXML
    private TableColumn<Devis, String> tab_libelle;
    @FXML
    private TableColumn<Devis, String> tab_descdevis;
    @FXML
    private TableColumn<Devis, Date> tab_datedevis;
    @FXML
    private TableColumn<Devis, Date> tab_datevdevis;
    @FXML
    private TableColumn<Devis, Double> tab_TTC;
    @FXML
    private TextField rechercherDevis;
    @FXML
    private Button ActualiserOffre;
    ObservableList observableList;
    ObservableList observableList1;
    @FXML
    private Button Actualiserdemande;
    @FXML
    private Button reclamation_btn;
    @FXML
    private Button gestionPanier;
    @FXML
    private Button gestionCommande;
    @FXML
    private Button gestionEvenement;
    @FXML
    private JFXButton parametreAdmin;
    @FXML
    private Button gestionOffres;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        OffreCRUD offrecrud = new OffreCRUD();
        ArrayList arrayList = (ArrayList) offrecrud.afficherOffre();
        observableList = FXCollections.observableArrayList(arrayList);
        offreTable.setItems(observableList);
        tab_titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        tab_type.setCellValueFactory(new PropertyValueFactory<>("type"));
        tab_desc.setCellValueFactory(new PropertyValueFactory<>("description"));
        tab_dateo.setCellValueFactory(new PropertyValueFactory<>("dateoffre"));
        tab_datev.setCellValueFactory(new PropertyValueFactory<>("datevalidite"));

        OffreCRUD offrecrud1 = new OffreCRUD();
        ArrayList arrayList1 = (ArrayList) offrecrud1.afficherOffreDemander();
        observableList1 = FXCollections.observableArrayList(arrayList1);
        demandedevisTable.setItems(observableList1);
        tab_titred.setCellValueFactory(new PropertyValueFactory<>("titre"));
        tab_typed.setCellValueFactory(new PropertyValueFactory<>("type"));
        tab_descd.setCellValueFactory(new PropertyValueFactory<>("description"));
        tab_dateod.setCellValueFactory(new PropertyValueFactory<>("dateoffre"));
        tab_datevd.setCellValueFactory(new PropertyValueFactory<>("datevalidite"));

        DevisCRUD deviscrud = new DevisCRUD();
        ArrayList arrayListdevis = (ArrayList) deviscrud.afficherDevis();
        ObservableList observableListdevis = FXCollections.observableArrayList(arrayListdevis);
        devisTable.setItems(observableListdevis);
        tab_libelle.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        tab_descdevis.setCellValueFactory(new PropertyValueFactory<>("description"));
        tab_datedevis.setCellValueFactory(new PropertyValueFactory<>("datedevis"));
        tab_datevdevis.setCellValueFactory(new PropertyValueFactory<>("datevalidite"));
        tab_TTC.setCellValueFactory(new PropertyValueFactory<>("totalTTC"));

    }

    @FXML
    private void demanderDevis(ActionEvent event) {
        Offre selectedIndex = offreTable.getSelectionModel().getSelectedItem();
        int selected = offreTable.getSelectionModel().getSelectedIndex();

        if (selected >= 0) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Notification");
            alert.setContentText("Etes-vous sure de vouloir passer une demande ??");
            Optional<ButtonType> result = alert.showAndWait();
            ButtonType button = result.orElse(ButtonType.CANCEL);
            if (button == ButtonType.OK) {

                OffreCRUD OC = new OffreCRUD();
                OC.demande(selectedIndex.getId());
                offreTable.getItems().remove(selected);

                new Alert(Alert.AlertType.INFORMATION, "demande enregistrer ").show();
            }
        } else {
            new Alert(Alert.AlertType.INFORMATION, "Aucun offre selectionné").show();
        }

    }

    @FXML
    private void AnnulerDemande(ActionEvent event) {
        Offre selectedIndex = demandedevisTable.getSelectionModel().getSelectedItem();
        int selected = demandedevisTable.getSelectionModel().getSelectedIndex();

        if (selected >= 0) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Notification");
            alert.setContentText("Etes vous sure de annuler la demande ??");
            Optional<ButtonType> result = alert.showAndWait();
            ButtonType button = result.orElse(ButtonType.CANCEL);
            if (button == ButtonType.OK) {

                OffreCRUD OC = new OffreCRUD();
                OC.Annulerdemande(selectedIndex.getId());
                demandedevisTable.getItems().remove(selected);

                new Alert(Alert.AlertType.INFORMATION, "demande effacé ").show();
            }
        } else {
            new Alert(Alert.AlertType.INFORMATION, "Aucune demande selectionné").show();
        }

    }

    @FXML
    private void ActualiserOffre(ActionEvent event) {
        observableList.clear();
        OffreCRUD offrecrud = new OffreCRUD();
        ArrayList arrayList = (ArrayList) offrecrud.afficherOffre();
        observableList = FXCollections.observableArrayList(arrayList);
        offreTable.setItems(observableList);
    }

    @FXML
    private void ActualiserDemande(ActionEvent event) {

        observableList1.clear();
        OffreCRUD offrecrud1 = new OffreCRUD();
        ArrayList arrayList1 = (ArrayList) offrecrud1.afficherOffreDemander();
        observableList1 = FXCollections.observableArrayList(arrayList1);
        demandedevisTable.setItems(observableList1);
    }

    @FXML
    private void G_reclamation(ActionEvent event) {
    }

    @FXML
    private void gestionPanier(ActionEvent event) throws IOException {
         FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/edu/EcoSystem/gui/AjoutPanier_Back.fxml"));
        /* 
         * if "fx:controller" is not set in fxml
         * fxmlLoader.setController(NewWindowController);
         */
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        //stage.setTitle("Gestion des reclamations");
        stage.setScene(scene);
        stage.show();
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    private void gestionCommande(ActionEvent event) throws IOException {
         FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/edu/EcoSystem/gui/AjoutCommande_Back.fxml"));
        /* 
         * if "fx:controller" is not set in fxml
         * fxmlLoader.setController(NewWindowController);
         */
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        //stage.setTitle("Gestion des reclamations");
        stage.setScene(scene);
        stage.show();
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }

    
            
    private void Gotofront(ActionEvent event) throws IOException {
         FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/edu/EcoSystem/gui/Menu.fxml"));
        /* 
         * if "fx:controller" is not set in fxml
         * fxmlLoader.setController(NewWindowController);
         */
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        //stage.setTitle("Gestion des reclamations");
        stage.setScene(scene);
        stage.show();
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }        

    @FXML
    private void gestionEvenement(ActionEvent event) throws IOException {
        
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/edu/EcoSystem/gui/ListEventBack.fxml"));
        /* 
         * if "fx:controller" is not set in fxml
         * fxmlLoader.setController(NewWindowController);
         */
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        //stage.setTitle("Mes reclamation");
        stage.setScene(scene);
        stage.show();
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    private void gestionOffre(ActionEvent event) throws IOException {
         FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/edu/EcoSystem/gui/ListBack.fxml"));
        /* 
         * if "fx:controller" is not set in fxml
         * fxmlLoader.setController(NewWindowController);
         */
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        //stage.setTitle("Mes reclamation");
        stage.setScene(scene);
        stage.show();
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    private void modifierAdmin(ActionEvent event) {
    }
    

    

}

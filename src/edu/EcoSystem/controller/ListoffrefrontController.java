/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.EcoSystem.controller;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import ecosystem.EcoSystem;
import edu.EcoSystem.services.OffreCRUD;
import edu.EcoSystem.entities.Offre;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.ScrollPane;
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
public class ListoffrefrontController implements Initializable {

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
    private Button btn_ajouter;

    @FXML
    private Button btn_modifier;

    @FXML
    private Button btn_supprimer;
    @FXML
    private AnchorPane ap;
    @FXML
    private ScrollPane pane;
    @FXML
    private TextField rechercherOffre;
    @FXML
    private JFXHamburger affmenu;
    @FXML
    private JFXDrawer menu;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            AnchorPane anchrone = FXMLLoader.load(getClass().getResource("/edu/EcoSystem/gui/test.fxml"));
            menu.setSidePane(anchrone);

            HamburgerBackArrowBasicTransition burgerTask2 = new HamburgerBackArrowBasicTransition(affmenu);
            burgerTask2.setRate(-1);

            affmenu.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
                burgerTask2.setRate(burgerTask2.getRate() * -1);
                burgerTask2.play();

                if (menu.isShown()) {
                    menu.close();
                } else {
                    menu.open();
                    menu.toFront();
                }
            }
            );
        } catch (IOException ex) {
            Logger.getLogger(ListoffrefrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
        // TODO
        OffreCRUD offrecrud = new OffreCRUD();
        ArrayList arrayList = (ArrayList) offrecrud.afficherOffre();
        ObservableList observableList = FXCollections.observableArrayList(arrayList);
        offreTable.setItems(observableList);
        tab_titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        tab_type.setCellValueFactory(new PropertyValueFactory<>("type"));
        tab_desc.setCellValueFactory(new PropertyValueFactory<>("description"));
        tab_dateo.setCellValueFactory(new PropertyValueFactory<>("dateoffre"));
        tab_datev.setCellValueFactory(new PropertyValueFactory<>("datevalidite"));

        btn_ajouter.setOnAction(event -> {
            try {
                Parent root1 = FXMLLoader.load(getClass().getResource("/edu/EcoSystem/gui/AjouterOffre.fxml"));
                Scene scene1 = new Scene(root1);
                primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                primaryStage.setScene(scene1);
                primaryStage.setTitle("Ajouter offres");
                primaryStage.show();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        });

    }

    @FXML
    void modifieroffre(ActionEvent event) {

        Offre selectedEvent = offreTable.getSelectionModel().getSelectedItem();
        if (selectedEvent != null) {
            boolean okClicked = EcoSystem.showOffreEditDialog(selectedEvent);
            if (okClicked) {

            }

        } else {
            // Nothing selected.
            new Alert(Alert.AlertType.ERROR, "Aucun Offre selectionné").show();

        }

    }

    @FXML
    private void supprimeroffre(ActionEvent event) {
        Offre selectedIndex = offreTable.getSelectionModel().getSelectedItem();
        int selected = offreTable.getSelectionModel().getSelectedIndex();

        if (selected >= 0) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Notification");
            alert.setContentText("Etes vous sure de supprimer cette offre ??");
            Optional<ButtonType> result = alert.showAndWait();
            ButtonType button = result.orElse(ButtonType.CANCEL);
            if (button == ButtonType.OK) {
                OffreCRUD OC = new OffreCRUD();
                OC.supprimerOffre(selectedIndex.getId());
                offreTable.getItems().remove(selected);
                new Alert(Alert.AlertType.INFORMATION, "Supprimer Avec succées").show();
            }
        } else {
            new Alert(Alert.AlertType.INFORMATION, "Aucun Offre selectionné").show();
        }

    }

}

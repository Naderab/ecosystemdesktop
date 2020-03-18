/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.EcoSystem.controller;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Nader
 */
public class TestController implements Initializable {

    @FXML
    private AnchorPane menuBar;
    @FXML
    private AnchorPane loginRegisterProfie;
    @FXML
    private HBox profileHB;
    @FXML
    private Circle photoProfilMenu;
    @FXML
    private HBox menuHB;
    @FXML
    private VBox profileVB;
    @FXML
    private JFXButton profilMenu;
    @FXML
    private JFXButton settingsProfile;
    @FXML
    private Label nameUserMenu;
    @FXML
    private Label labelLastLoginMenu;
    @FXML
    private Label LastLoginMenu;
    @FXML
    private JFXButton logoutMenu;
    @FXML
    private JFXButton admin;
    @FXML
    private AnchorPane barreRecherche;
    @FXML
    private JFXButton eventButton1;
    @FXML
    private JFXButton eventButton12;
    @FXML
    private VBox menuVB;
    @FXML
    private VBox accueilVB;
    @FXML
    private VBox catVB;
    @FXML
    private JFXButton panierB;
    @FXML
    private JFXButton eventButton;
    @FXML
    private VBox eventVB;
    @FXML
    private VBox blogVB;
    @FXML
    private JFXButton blogButton;
    @FXML
    private VBox dealVB;
    @FXML
    private JFXButton dealButton;
    @FXML
    private JFXButton catButton;
    @FXML
    private JFXButton produitB;
    @FXML
    private JFXButton eventButton11;
    @FXML
    private JFXButton AjoutEvent;
    @FXML
    private JFXButton mesevenements;
    @FXML
    private JFXButton ajoutOffre;
    @FXML
    private JFXButton listOffres;
    @FXML
    private JFXButton listDevis;

    private AnchorPane rootLayout;
    private Stage primaryStage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void Panier(MouseEvent event) {
    }

    @FXML
    private void showProduit(MouseEvent event) {
    }

    @FXML
    private void Ajout(MouseEvent event) throws IOException {

    }

    @FXML
    private void AfficheEvents(MouseEvent event) throws IOException {

    }


    @FXML
    private void ToMesEvenements(MouseEvent event) throws IOException {

    }

    @FXML
    private void AddOffre(MouseEvent event) {
         try {
            Parent root1 = FXMLLoader.load(getClass().getResource("/edu/EcoSystem/gui/AjouterOffre.fxml"));
            Scene scene1 = new Scene(root1);
            primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            primaryStage.setScene(scene1);
//            primaryStage.setTitle("Ajouter devis ! ");
            primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void ListOffres(MouseEvent event) {
         try {
            Parent root1 = FXMLLoader.load(getClass().getResource("/edu/EcoSystem/gui/Listoffrefront.fxml"));
            Scene scene1 = new Scene(root1);
            primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            primaryStage.setScene(scene1);
//            primaryStage.setTitle("List offres front ! ");
            primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void ListDevis(MouseEvent event) {
        try {
            Parent root1 = FXMLLoader.load(getClass().getResource("/edu/EcoSystem/gui/Listdevisfront.fxml"));
            Scene scene1 = new Scene(root1);
            primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            primaryStage.setScene(scene1);
//            primaryStage.setTitle("List devis front ! ");
            primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}

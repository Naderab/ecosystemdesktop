/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.EcoSystem.controller;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author MEGAJAS
 */
public class MenuController {

    @FXML
    private Button ProfilClient;
    @FXML
    private Button Annonces;
    @FXML
    private Button GestionPanierCom;
    @FXML
    private Button devis;
    @FXML
    private Button evenements;
    @FXML
    private Text lblWelcome112;
    @FXML
    private Text lblWelcome113;
    @FXML
    private Label lbTitre;
    @FXML
    private Button PointCollecte;
    @FXML
    private Text lblWelcome1131;
    @FXML
    private Text lblWelcome111;
    @FXML
    private Text lblWelcome1132;
    @FXML
    private Text lblWelcome1133;
    @FXML
    private Text lblWelcome1134;
    @FXML
    private JFXButton logout;

    

    @FXML
    private void logout(ActionEvent event) {
        try {Stage stage = new Stage();
        Parent home = FXMLLoader.load(getClass().getResource("/edu/EcoSystem/gui/loginUser.fxml"));
        Scene hoomescene = new Scene(home);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(hoomescene);
        app_stage.show();
        } catch (IOException ex) {
            Logger.getLogger(edu.EcoSystem.controller.LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    

    /*@FXML
    private void GestionPanierCom(ActionEvent event) {
        
        try {Stage stage = new Stage();
        Parent home = FXMLLoader.load(getClass().getResource("/edu/EcoSystem/gui/GestionPanierCommande.fxml"));
        Scene hoomescene = new Scene(home);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(hoomescene);
        app_stage.show();
        } catch (IOException ex) {
            Logger.getLogger(edu.EcoSystem.controller.LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }*/
    
}

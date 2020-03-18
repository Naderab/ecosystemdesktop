/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.EcoSystem.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import edu.EcoSystem.entities.Evenement;
import edu.EcoSystem.services.EventCRUD;
import edu.EcoSystem.tools.DateUtil;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author Nader
 */
public class UpdateEventBackController implements Initializable {

    @FXML
    private JFXTextField nom;
    @FXML
    private JFXTextArea adresse;
    @FXML
    private JFXTextArea description;
    @FXML
    private JFXTextField minP;
    @FXML
    private JFXTextField maxP;
    @FXML
    private Label labelNom;
    @FXML
    private Label labelDate;
    @FXML
    private Label labelDescription;
    @FXML
    private Label labelAdresse;
    @FXML
    private Label labelMinP;
    @FXML
    private Label labelCategorie;
    @FXML
    private Label labelPrix;
    @FXML
    private JFXButton retourbutton;
    @FXML
    private JFXButton AjoutEvent;
    @FXML
    private JFXDatePicker dateFin;
    @FXML
    private JFXDatePicker dateDebut1;
    
    private Stage dialogStage;
    private Evenement eventt;
    private boolean okClicked = false;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         AjoutEvent.setStyle("-fx-background-color: #0aaf57");
        retourbutton.setStyle("-fx-background-color: #f43838");
        // TODO
    }    
    
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    
    public void setEvent(Evenement event) {
        this.eventt = event;

        nom.setText(event.getNom());
        description.setText(event.getDescription());
        adresse.setText(event.getAdresse());
        minP.setText(Integer.toString(event.getMinParticipants()));
        maxP.setText(Integer.toString(event.getMaxParticipants()));
        dateDebut1.setValue(DateUtil.convertToLocalDateViaSqlDate(event.getDateDebut()));
        dateFin.setValue(DateUtil.convertToLocalDateViaSqlDate(event.getDateFin()));
    }
public boolean isOkClicked() {
        return okClicked;
    }
    @FXML
    private void Retour(ActionEvent event) {
        dialogStage.close();
    }

    @FXML
    private void SubmitAjout(ActionEvent event) throws SQLException, IOException {
       
        
        if (isInputValid()) {
        java.util.Date dateD = java.util.Date.from(dateDebut1.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        java.util.Date dateF = java.util.Date.from(dateFin.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        java.sql.Date sqldateD = new java.sql.Date(dateD.getTime());
        java.sql.Date sqldateF = new java.sql.Date(dateF.getTime());
            eventt.setNom(nom.getText());
            eventt.setDescription(description.getText());
            eventt.setAdresse(adresse.getText());
            eventt.setDateDebut(sqldateD);
            eventt.setDateFin(sqldateF);
            eventt.setMinParticipants(Integer.parseInt(minP.getText()));
            eventt.setMaxParticipants(Integer.parseInt(maxP.getText()));
            EventCRUD evenementService = new EventCRUD();
            evenementService.updateEvent(eventt,eventt.getId_event());
            TrayNotification tray= new TrayNotification("Information","Evènement Modifié", NotificationType.SUCCESS);
        tray.setAnimationType(AnimationType.POPUP);
        tray.showAndDismiss(Duration.seconds(3));

            okClicked = true;
            dialogStage.close();
        }
    }
    private boolean isInputValid() {
        String errorMessage = "";

        
        java.util.Date dateD = java.util.Date.from(dateDebut1.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        java.util.Date dateF = java.util.Date.from(dateFin.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        java.sql.Date sqldateD = new java.sql.Date(dateD.getTime());
        java.sql.Date sqldateF = new java.sql.Date(dateF.getTime());
        
        java.util.Date datecourante = new java.util.Date();
        java.sql.Date sqldatecourante =new  java.sql.Date(datecourante.getTime());
        if (nom.getText().isEmpty()) {
            labelNom.setText("Champ Nom vide");
            errorMessage += "No valid first name!\n";
            //new Alert(Alert.AlertType.ERROR, " Champ Nom vide").show();
        } 
        if (description.getText().isEmpty()) {
            labelAdresse.setText("Champ Description vide");
            errorMessage += "No valid first name!\n";
            //new Alert(Alert.AlertType.ERROR, "Champ Description vide").show();
        } 
        if (adresse.getText().isEmpty()) {
            labelDescription.setText("Champ Adresse vide");
            errorMessage += "No valid first name!\n";
            //new Alert(Alert.AlertType.ERROR, " Champ Adresse vide").show();
        } 
        if (minP.getText().isEmpty()) {
            labelMinP.setText("Champ min participants vide");
            errorMessage += "No valid first name!\n";
            //new Alert(Alert.AlertType.ERROR, "Champ min participants vide").show();
        } 
        else {
            // try to parse the postal code into an int.
            try {
                Integer.parseInt(minP.getText());
            } catch (NumberFormatException e) {
                labelMinP.setText("(must be an integer)");
                errorMessage += "No valid first name!\n";

            }
            }
        if (maxP.getText().isEmpty()) {
            labelMinP.setText("Champ max participants vide");
            errorMessage += "No valid first name!\n";
            //new Alert(Alert.AlertType.ERROR, "Champ max participants vide").show();
        } 
        else {
            // try to parse the postal code into an int.
            try {
                Integer.parseInt(maxP.getText());
            } catch (NumberFormatException e) {
                labelMinP.setText("(must be an integer)");
                errorMessage += "No valid first name!\n";

            }
            }
        if (Integer.valueOf(minP.getText()) > Integer.valueOf(maxP.getText())) {
            labelMinP.setText("min particpants doit etre inferieur ala max de particpants");
            errorMessage += "No valid first name!\n";
            //new Alert(Alert.AlertType.ERROR, "min particpants doit etre inferieur ala max de particpants").show();
        } 
        if (sqldateD.compareTo(sqldateF) > 0) {
            labelDate.setText("date fin doit etre supérieur ou égal a la date debut");
            errorMessage += "No valid first name!\n";
            //new Alert(Alert.AlertType.ERROR, "date fin doit etre supérieur ou égal a la date debut").show();
        } 
        if(sqldateD.compareTo(sqldatecourante)<0){
            labelDate.setText("date debut doit etre supérieur ou egal a la date courante");
            errorMessage += "No valid first name!\n";
            //new Alert(Alert.AlertType.ERROR, "date debut doit etre supérieur ou egal a la date courante").show();
        }

        

        

        if (errorMessage.length() == 0) {
            return true;
        } else {
            

            return false;
        }
    }
}

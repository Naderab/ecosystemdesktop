/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.EcoSystem.controller;

import edu.EcoSystem.services.OffreCRUD;
import edu.EcoSystem.entities.Offre;
import edu.EcoSystem.tools.DateUtil;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ramzi
 */
public class ModifierOffreController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private AnchorPane rootLayout;
    private Stage primaryStage;

    @FXML
    private Button btn_reset;

    @FXML
    private Button btn_affiche;

    @FXML
    private Button btn_modifier;

    @FXML
    private TextField titre;

    @FXML
    private TextField type;

    @FXML
    private TextArea description;

    @FXML
    private DatePicker dateoffre;

    @FXML
    private DatePicker datevalidite;

    @FXML
    private Label LabelTitre;

    @FXML
    private Label LabelType;

    @FXML
    private Label LabelDesc;

    @FXML
    private Label LabelDatev;
    private Offre offree;
    private Stage dialogStage;
    private boolean okClicked;
    @FXML
    private StackPane ap;
    @FXML
    private ScrollPane pane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        btn_reset.setOnAction(e -> {
            titre.setText("");
            type.setText("");
            description.setText("");
            dateoffre.setValue(null);
            datevalidite.setValue(null);
        });

        /*btn_affiche.setOnAction(event -> {

            try {
                Parent root1 = FXMLLoader.load(getClass().getResource("/edu/gestionoffre/gui/Listoffrefront.fxml"));
                Scene scene1 = new Scene(root1);
                primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                primaryStage.setScene(scene1);
                primaryStage.show();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        });*/
    }

    public void setOffre(Offre offre) {
        this.offree = offre;

        titre.setText(offre.getTitre());
        type.setText(offre.getType());
        description.setText(offre.getDescription());

        dateoffre.setValue(DateUtil.convertToLocalDateViaSqlDate((java.sql.Date) offre.getDateoffre()));
        datevalidite.setValue(DateUtil.convertToLocalDateViaSqlDate((java.sql.Date) offre.getDatevalidite()));

    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void Retour(ActionEvent event) {
        dialogStage.close();
    }

    @FXML
    private void Confirmer(ActionEvent event) throws SQLException, IOException {

        if (isInputValid()) {

            java.util.Date dateoff = java.util.Date.from(dateoffre.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
            java.util.Date dateva = java.util.Date.from(datevalidite.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());

            java.sql.Date sqldateoff = new java.sql.Date(dateoff.getTime());
            java.sql.Date sqldateva = new java.sql.Date(dateva.getTime());

            offree.setTitre(titre.getText());
            offree.setType(type.getText());
            offree.setDescription(description.getText());
            offree.setDateoffre(sqldateoff);
            offree.setDatevalidite(sqldateva);

            OffreCRUD OC = new OffreCRUD();
            OC.modifierOffre(offree, offree.getId());
            //TrayNotification tray = new TrayNotification("Information", "Evènement Modifié", NotificationType.SUCCESS);
            // tray.setAnimationType(AnimationType.POPUP);
            // tray.showAndDismiss(Duration.seconds(3));

            okClicked = true;
            dialogStage.close();
        }
    }

    private boolean isInputValid() {
        String errorMessage = "";

        java.util.Date dateoff = java.util.Date.from(dateoffre.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        java.util.Date dateva = java.util.Date.from(datevalidite.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());

        java.sql.Date sqldateoff = new java.sql.Date(dateoff.getTime());
        java.sql.Date sqldateva = new java.sql.Date(dateva.getTime());

        java.util.Date datecourante = new java.util.Date();
        java.sql.Date sqldatecourante = new java.sql.Date(datecourante.getTime());

        if (titre.getText().isEmpty()) {
            LabelTitre.setText("Champ Titre vide");
            errorMessage += "No valid Titre!\n";
            //new Alert(Alert.AlertType.ERROR, " Champ Nom vide").show();
        }
        if (type.getText().isEmpty()) {
            LabelType.setText("Champ type vide");
            errorMessage += "No valid type!\n";
            //new Alert(Alert.AlertType.ERROR, "Champ Description vide").show();
        }
        if (description.getText().isEmpty()) {
            LabelDesc.setText("Champ Description vide");
            errorMessage += "No valid first name!\n";
            //new Alert(Alert.AlertType.ERROR, "Champ Description vide").show();
        }

        if (sqldateoff.compareTo(dateva) > 0) {
            LabelDatev.setText("date validite doit etre supérieur ou égal a la date offre");
            errorMessage += "No valid first name!\n";
            //new Alert(Alert.AlertType.ERROR, "date fin doit etre supérieur ou égal a la date debut").show();
        }
        /* if (sqldateoff.compareTo(sqldatecourante) < 0) {
            LabelDateo.setText("date du offre doit etre supérieur ou egal a la date courante");
            errorMessage += "No valid first name!\n";
            //new Alert(Alert.AlertType.ERROR, "date debut doit etre supérieur ou egal a la date courante").show();
        }*/

        if (errorMessage.length() == 0) {
            return true;
        } else {

            return false;
        }
    }

}

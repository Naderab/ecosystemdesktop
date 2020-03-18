/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.EcoSystem.controller;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import edu.EcoSystem.services.OffreCRUD;
import edu.EcoSystem.entities.Offre;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ramzi
 */
public class AjouterOffreController implements Initializable {

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
    private Button btn_add;

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
    private Label LabelDateo;
    @FXML
    private Label LabelDatev;
    @FXML
    private AnchorPane ap;
    @FXML
    private ScrollPane pane;
    @FXML
    private JFXHamburger affmenu;
    @FXML
    private JFXDrawer menu;

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

        btn_affiche.setOnAction(event -> {

            try {
                Parent root1 = FXMLLoader.load(getClass().getResource("/edu/EcoSystem/gui/Listoffrefront.fxml"));
                Scene scene1 = new Scene(root1);
                primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                primaryStage.setScene(scene1);
                primaryStage.show();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        });
        
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
            Logger.getLogger(AjouterOffreController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void Ajouteroffre(ActionEvent event) throws SQLException, IOException {
        if (isInputValid()) {

            java.sql.Date datef = java.sql.Date.valueOf(dateoffre.getValue());
            java.sql.Date datevv = java.sql.Date.valueOf(datevalidite.getValue());

            Offre o = new Offre(titre.getText(), type.getText(), description.getText(), datef, datevv, "non", "non", "non");
            OffreCRUD oc = OffreCRUD.getInstance();
            oc.ajouterOffre(o);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Offre insérée avec succés!");
            alert.show();
            titre.setText("");
            type.setText("");
            description.setText("");
            dateoffre.setValue(null);
            datevalidite.setValue(null);
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
        if (sqldateoff.compareTo(sqldatecourante) < 0) {
            LabelDateo.setText("date du offre doit etre supérieur ou egal a la date courante");
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

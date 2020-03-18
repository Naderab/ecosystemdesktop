/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.EcoSystem.controller;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import edu.EcoSystem.services.DevisCRUD;
import edu.EcoSystem.entities.Devis;
import edu.EcoSystem.entities.Offre;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ramzi
 */
public class AjouterDevisController implements Initializable {

    @FXML
    private TextField libelle;
    @FXML
    private TextArea mesage;
    @FXML
    private DatePicker dated;
    @FXML
    private DatePicker datevd;
    @FXML
    private TextArea description;
    @FXML
    private TextField qte;
    @FXML
    private TextField prixuni;
    @FXML
    private TextField montant;
    @FXML
    private Label totalht;
    @FXML
    private Label totalttc;
    @FXML
    private Button btn_ajouter;
    @FXML
    private Button btn_reset;
    @FXML
    private ComboBox<Integer> tva;
    @FXML
    private Label labellibelle;
    @FXML
    private Label labeldated;
    @FXML
    private Label labeldatev;
    @FXML
    private Label labelmessage;
    @FXML
    private Label labeldesc;
    @FXML
    private Button btn_back;
    @FXML
    private Button calculer;
    @FXML
    private TextField idoffre;

    private AnchorPane rootLayout;
    private Stage primaryStage;
    private String descriptionoffre;
    @FXML
    private AnchorPane ap;
    @FXML
    private ScrollPane pane;
    @FXML
    private JFXHamburger affmenu;
    @FXML
    private JFXDrawer menu;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ObservableList<Integer> availableChoices = FXCollections.observableArrayList(0, 9, 11, 19);
        tva.setItems(availableChoices);
        tva.setValue(0);
        
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
            Logger.getLogger(AjouterDevisController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setOffre(Offre offre) {

        description.setText(offre.getDescription());
        idoffre.setText(String.valueOf(offre.getId()));
        offre.setEtat("oui");
    }

    @FXML
    private void Ajouterdevis(ActionEvent event) throws SQLException, IOException {
        if (isInputValid()) {

            java.sql.Date datede = java.sql.Date.valueOf(dated.getValue());
            java.sql.Date datev = java.sql.Date.valueOf(datevd.getValue());
            double ht = CalcultotalHT(event);
            double ttc = CalcultotalTTC(event);
            Devis d = new Devis(libelle.getText(), description.getText(), mesage.getText(), datede, datev, Double.parseDouble(prixuni.getText()), Integer.parseInt(qte.getText()), ht, tva.getSelectionModel().getSelectedItem(), ttc, "non", Integer.parseInt(idoffre.getText()));
            DevisCRUD dc = DevisCRUD.getInstance();
            dc.ajouterDevis(d);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("devis insérée avec succés!");
            alert.show();
            libelle.setText("");
            mesage.setText("");
            description.setText("");
            qte.setText("");
            prixuni.setText("");
            tva.setValue(0);
            dated.setValue(null);
            datevd.setValue(null);
            montant.setText("");
            totalht.setText("0.00 DT");
            totalttc.setText("0.00 DT");
        }
    }

    @FXML
    private void Reset(ActionEvent event) {

        libelle.setText("");
        mesage.setText("");
        qte.setText("");
        prixuni.setText("");
        tva.setValue(0);
        dated.setValue(null);
        datevd.setValue(null);
        montant.setText("");
        totalht.setText("0.00 DT");
        totalttc.setText("0.00 DT");
        labellibelle.setText("");
        labeldated.setText("");
        labeldatev.setText("");
        labelmessage.setText("");

    }

    private boolean isInputValid() {

        String errorMessage = "";

        java.util.Date datedd = java.util.Date.from(dated.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        java.util.Date datevv = java.util.Date.from(datevd.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());

        java.sql.Date sqldated = new java.sql.Date(datedd.getTime());
        java.sql.Date sqldatev = new java.sql.Date(datevv.getTime());

        java.util.Date datecourante = new java.util.Date();
        java.sql.Date sqldatecourante = new java.sql.Date(datecourante.getTime());

        if (libelle.getText().isEmpty()) {
            labellibelle.setText("Champ libelle vide");
            errorMessage += "No valid Titre!\n";
            //new Alert(Alert.AlertType.ERROR, " Champ Nom vide").show();
        }
        if (mesage.getText().isEmpty()) {
            labelmessage.setText("Champ type vide");
            errorMessage += "No valid type!\n";
            //new Alert(Alert.AlertType.ERROR, "Champ Description vide").show();
        }
        if (description.getText().isEmpty()) {
            labeldesc.setText("Champ Description vide");
            errorMessage += "No valid first name!\n";
            //new Alert(Alert.AlertType.ERROR, "Champ Description vide").show();
        }

        if (sqldated.compareTo(sqldatev) > 0) {
            labeldatev.setText("date validite doit etre supérieur ou égal a la date devis");
            errorMessage += "No valid first name!\n";
            //new Alert(Alert.AlertType.ERROR, "date fin doit etre supérieur ou égal a la date debut").show();
        }
        if (sqldated.compareTo(sqldatecourante) <= 0) {
            labeldated.setText("date du devis doit etre supérieur ou egal a la date courante");
            errorMessage += "No valid first name!\n";
            //new Alert(Alert.AlertType.ERROR, "date debut doit etre supérieur ou egal a la date courante").show();
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {

            return false;
        }
    }

    @FXML
    private void back(ActionEvent event) {
        try {
            Parent root1 = FXMLLoader.load(getClass().getResource("/edu/EcoSystem/gui/Listdevisfront.fxml"));
            Scene scene1 = new Scene(root1);
            primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            primaryStage.setScene(scene1);
            primaryStage.setTitle("List devis front ! ");
            primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private double CalcultotalHT(ActionEvent event) {
        double prixunit = Double.parseDouble(this.prixuni.getText());
        int qte = Integer.parseInt(this.qte.getText());
        double totalHT = qte * prixunit;
        return totalHT;
    }

    private double CalcultotalTTC(ActionEvent event) {
        double prixunit = Double.parseDouble(this.prixuni.getText());
        int qte = Integer.parseInt(this.qte.getText());
        int tva = this.tva.getSelectionModel().getSelectedItem();
        double totalHT = qte * prixunit;
        double totalTTC = 0.0;

        switch (tva) {
            case 0:
                totalTTC = totalHT;
                break;
            case 9:
                totalTTC = totalHT + (totalHT * 9) / 100;
                break;
            case 11:
                totalTTC = totalHT + (totalHT * 11) / 100;
                break;
            default:
                totalTTC = totalHT + (totalHT * 19) / 100;
                break;
        }

        return totalTTC;
    }

    @FXML
    private void calculer(ActionEvent event) {

        montant.setText(Float.toString((float) CalcultotalHT(event)));
        totalht.setText(Float.toString((float) CalcultotalHT(event)));
        totalttc.setText(Float.toString((float) CalcultotalTTC(event)));

    }

}

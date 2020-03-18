/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.EcoSystem.controller;

import edu.EcoSystem.services.DevisCRUD;
import edu.EcoSystem.entities.Devis;
import edu.EcoSystem.tools.DateUtil;
import java.io.IOException;
import java.net.URL;
import java.time.ZoneId;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ramzi
 */
public class ModifierDevisController implements Initializable {

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
    private Button btn_confirmer;
    private Stage dialogStage;
    private boolean okClicked;
    private Devis devis;
    private Stage primaryStage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ObservableList<Integer> availableChoices1 = FXCollections.observableArrayList(0, 9, 11, 19);
        tva.setItems(availableChoices1);
        qte.setText("1");
        prixuni.setText("0");
    }

    public void setDevis(Devis devis) {
        this.devis = devis;

        libelle.setText(devis.getLibelle());
        mesage.setText(devis.getMessage());
        dated.setValue(DateUtil.convertToLocalDateViaSqlDate((java.sql.Date) devis.getDatedevis()));
        datevd.setValue(DateUtil.convertToLocalDateViaSqlDate((java.sql.Date) devis.getDatevalidite()));
        description.setText(devis.getDescription());
        qte.setText(String.valueOf(devis.getQte()));
        prixuni.setText(String.valueOf(devis.getPrixunit()));
        montant.setText(String.valueOf(devis.getTotalHT()));
        totalht.setText(String.valueOf(devis.getTotalHT()));
        totalttc.setText(String.valueOf(devis.getTotalTTC()));

        switch (devis.getTVA()) {
            case 0:
                tva.setValue(0);
                break;
            case 9:
                tva.setValue(9);
                break;
            case 11:
                tva.setValue(11);
                break;
            default:
                tva.setValue(19);
                break;
        }
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void ConfirmerUpdate(ActionEvent event) {
        if (isInputValid()) {

            //java.util.Date datedevis = java.util.Date.from(dated.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
            java.util.Date dateva = java.util.Date.from(datevd.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());

            //java.sql.Date sqldatedevis = new java.sql.Date(datedevis.getTime());
            java.sql.Date sqldateva = new java.sql.Date(dateva.getTime());

            devis.setLibelle(libelle.getText());
            devis.setDatevalidite(sqldateva);
            devis.setMessage(mesage.getText());
            devis.setQte(Integer.valueOf(qte.getText()));
            devis.setPrixunit(Double.valueOf(prixuni.getText()));
            devis.setTVA(tva.getSelectionModel().getSelectedItem());
            double ht = CalcultotalHT(event);
            double ttc = CalcultotalTTC(event);
            devis.setTotalHT(ht);
            devis.setTotalTTC(ttc);

            DevisCRUD dc = new DevisCRUD();
            dc.modifierDevis(devis, devis.getId());

            okClicked = true;
            dialogStage.close();
        }
    }

    @FXML
    private void Reset(ActionEvent event) {
        libelle.setText("");
        mesage.setText("");
        qte.setText("1");
        prixuni.setText("0");
        tva.setValue(0);
        montant.setText("");
        totalht.setText("0.00 DT");
        totalttc.setText("0.00 DT");
        labellibelle.setText("");
        labeldated.setText("");
        labeldatev.setText("");
        labelmessage.setText("");

    }

    @FXML
    private void back(ActionEvent event) {
        dialogStage.close();
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
        if (sqldated.compareTo(sqldatev) > 0) {
            labeldatev.setText("date validite doit etre supérieur ou égal a la date devis");
            errorMessage += "No valid first name!\n";
            //new Alert(Alert.AlertType.ERROR, "date fin doit etre supérieur ou égal a la date debut").show();
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {

            return false;
        }
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.GestionProduit.GUI;

import edu.GestionProduit.entities.produit2;
import edu.GestionProduit.service.CrudProduit;
import static java.awt.SystemColor.text;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Abir
 */
public class Produit2Controller implements Initializable {

    @FXML
    private Button ButtonCommande;

    @FXML
    private TextField tftitre;

    @FXML
    private Button Ajouter;

    @FXML
    private TextArea tfdescription;

    @FXML
    private TextField tfprix;

    @FXML
    private ComboBox<?> boxCat;

    @FXML
    private ComboBox<?> boxSousCat;

    @FXML
    private Label controletitre;

    @FXML
    private Label controledes;

    @FXML
    private Label controleprix;

    public static produit2 prod;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void ajouterProduit(ActionEvent event) throws IOException, SQLException {
        if (tftitre.getText().equals("")) {
            controletitre.setText("Veuillez remplir le titre");
        } else if (tfdescription.getText().equals("")) {
            controledes.setText("Veuillez remplir la description");
        } else {

            prod = new produit2();

            prod.setTitre(tftitre.getText());
            prod.setDescription(tfdescription.getText());

            int prix = Integer.parseInt(tfprix.getText());
            CrudProduit cp = new CrudProduit();

            cp.ajouterProduit(prod);

        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.GestionProduit.GUI;

import edu.GestionProduit.entities.produit2;
import edu.GestionProduit.service.CrudProduit;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Abir
 */
public class DetailProduitController implements Initializable {

    @FXML
    private ImageView tfimagedetail;
    @FXML
    private Label detailtitre;
    @FXML
    private Label detailprix;
    @FXML
    private Button tfsuppriannon2;
    @FXML
    private Button tfmodifier2;
    @FXML
    private Button btnaccueil;
    @FXML
    private Button testadmin1;
    @FXML
    private Label detaildescription;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }


    @FXML
    private void cliquesupprim2(ActionEvent event) {
         /*if (tableadminiproduit.getSelectionModel().getSelectedItem() != null) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Partner");
            alert.setHeaderText(null);
            alert.setContentText("Êtes-vous sûr de vouloir supprimer cette annonce ?");

            Optional<ButtonType> optionDeleteProduitAlert = alert.showAndWait();
            if (optionDeleteProduitAlert.get() == ButtonType.OK) {
                crudProduit.SupprimerProduit(tableadminiproduit.getSelectionModel().getSelectedItem().getId());
                /*Alert Alert1 = new Alert(Alert.AlertType.INFORMATION);
                Alert1.setTitle("Supprimer une annonce");
                Alert1.setHeaderText("Resultat:");
                Alert1.setContentText("Annonce supprimée avec succès!");

                Alert1.showAndWait();

            } else if (optionDeleteProduitAlert.get() == ButtonType.CANCEL) {

            }

        } else {
            Alert Alert1 = new Alert(Alert.AlertType.WARNING);
            Alert1.setTitle("Select Annonce");
            Alert1.setHeaderText(null);
            Alert1.setContentText("Vous devez d'abord sélectionner une annonce!");
            Alert1.showAndWait();
        }

        tableadminiproduit.setItems(crudProduit.getAllProduit2());
    }*/
       
}

    @FXML
    private void cliquemodifier2(ActionEvent event) throws IOException {

    }

    @FXML
    private void accueilfromdetail(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/edu/GestionProduit/GUI/Profile.fxml"));
        Parent profileParent = loader.load();

        Scene gestionProfilScene = new Scene(profileParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(gestionProfilScene);
        window.show();
    }

    @FXML
    private void testadmin1(ActionEvent event) {
    }

    void ShowEvent(int id) {
        
        CrudProduit cp = new CrudProduit();
        try {
            produit2 newProduit = cp.getAllProduit2().filtered(e -> e.getId() == id).get(0);
            Image image= new Image("file:C:\\wamp64\\www\\EcoSystem\\web\\uploads\\images/"+newProduit.getImage());
            tfimagedetail.setImage(image);
            detailprix.setText(String.valueOf(newProduit.getPrix()));
            detaildescription.setText(newProduit.getDescription());
            detailtitre.setText(newProduit.getTitre());
            
        } catch (SQLException ex) {
            Logger.getLogger(DetailProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

}

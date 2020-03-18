/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.GestionProduit.GUI;

import edu.GestionProduit.entities.produit2;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

/**
 * FXML Controller class
 *
 * @author Abir
 */
public class DivProduitController implements Initializable {

    @FXML
    private Rectangle rectangle;
    @FXML
    private Label prix;
    @FXML
    private Label nom;
    @FXML
    private Label rating;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void LoadValues(produit2 e) throws IOException {
        nom.setText(e.getTitre());
        prix.setText(String.valueOf(e.getPrix()));
        rating.setText(String.valueOf(e.getRating()));

        Image imageURI2 = new Image("file:C:\\wamp64\\www\\EcoSystem\\web\\uploads\\images/" + e.getImage());

        rectangle.setFill(new ImagePattern(imageURI2));

        rectangle.setOnMouseClicked((MouseEvent event) -> {
            if (event.getClickCount() == 2) {
                try {
                    doubleclick(event, e);
                    //System.out.println(e.getId_event());
                } catch (SQLException ex) {
                    Logger.getLogger(DivProduitController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

    }

    public void doubleclick(MouseEvent event, produit2 selectedetab) throws SQLException {
        if (event.getClickCount() == 2) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/GestionProduit/GUI/DetailProduit.fxml"));
                Parent root = loader.load();
                DetailProduitController DEF = loader.getController();
                DEF.ShowEvent(selectedetab.getId());
                rectangle.getScene().setRoot(root);

            } catch (IOException ex) {
                Logger.getLogger(DivProduitController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pointcollect.services;

import edu.pointcollect.entities.CategorieTrie;
import edu.pointcollect.entities.SousCategorieTrie;
import edu.pointcollect.entities.TypePoint;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Khouloud
 */
public class DivCategorieBackController implements Initializable {

    @FXML
    private Rectangle image;
    @FXML
    private Label titre;

    @FXML
    private Button suppCat;
    @FXML
    private Button idModif;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
    }
    public Rectangle LoadValues(CategorieTrie e) throws IOException {
        titre.setText(e.getName());
        Image imageURI2 = new Image("file:C:/Users/zied/Documents/NetBeansProjects/pidev/" + e.getImg());        
        image.setFill(new ImagePattern(imageURI2));   
        suppCat.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                  CategorieTrieCrud crud = new CategorieTrieCrud();
                                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                alert.setTitle("Confirmation");
                                alert.setHeaderText(null);
                                alert.setContentText("Voulez vous supprimer ce type");
                                Optional<ButtonType> action = (Optional<ButtonType>) alert.showAndWait();
                                if (action.get() == ButtonType.OK) {
                      try {
                          CategorieTrie m = new CategorieTrie();
                          
                          crud.supprimerCategorie(e.getId());
                          
                          FXMLLoader loader = new FXMLLoader();
                          loader.setLocation(getClass().getResource("/edu/pointcollect/gui/CrudCategorie.fxml"));
                          Parent profileParent = loader.load();
                          
                          
                          Scene gestionProfilScene = new Scene (profileParent);
                          
                          Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                          window.setScene(gestionProfilScene);
                          window.show();} catch (IOException ex) {
                          Logger.getLogger(DivCategorieBackController.class.getName()).log(Level.SEVERE, null, ex);
                      }
                                    
                                }
                
            }
        });
        
       
        return image;

      }
    
    public void LoadValues1(SousCategorieTrie e) throws IOException {
        titre.setText(e.getName());
        Image imageURI2 = new Image("file:C:/Users/zied/Documents/NetBeansProjects/pidev/" + e.getImg());        
        image.setFill(new ImagePattern(imageURI2));   
        
         suppCat.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                  SousCategorieTrieCrud crud = new SousCategorieTrieCrud();
                                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                alert.setTitle("Confirmation");
                                alert.setHeaderText(null);
                                alert.setContentText("Voulez vous supprimer ce type");
                                Optional<ButtonType> action = (Optional<ButtonType>) alert.showAndWait();
                                if (action.get() == ButtonType.OK) {
                      try {
                          SousCategorieTrie m = new SousCategorieTrie();
                          
                          crud.supprimerCategorie(e.getId());
                          
                          FXMLLoader loader = new FXMLLoader();
                          loader.setLocation(getClass().getResource("/edu/pointcollect/gui/CrudCategorie.fxml"));
                          Parent profileParent = loader.load();
                          
                          
                          Scene gestionProfilScene = new Scene (profileParent);
                          
                          Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                          window.setScene(gestionProfilScene);
                          window.show();} catch (IOException ex) {
                          Logger.getLogger(DivCategorieBackController.class.getName()).log(Level.SEVERE, null, ex);
                      }
                                    
                                }
                
            }
        });
        
        

      }
    
  

}

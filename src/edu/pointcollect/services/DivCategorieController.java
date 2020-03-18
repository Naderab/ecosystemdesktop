/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pointcollect.services;

import edu.pointcollect.entities.CategorieTrie;
import edu.pointcollect.entities.SousCategorieTrie;
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
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Nader
 */
public class DivCategorieController implements Initializable {

    @FXML
    private Rectangle image;
    @FXML
    private Label titre;

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
        
        return image;

      }
    
    public void LoadValues1(SousCategorieTrie e) throws IOException {
        titre.setText(e.getName());
        Image imageURI2 = new Image("file:C:/Users/zied/Documents/NetBeansProjects/pidev/" + e.getImg());        
        image.setFill(new ImagePattern(imageURI2));   
        

      }
    
    
    
}

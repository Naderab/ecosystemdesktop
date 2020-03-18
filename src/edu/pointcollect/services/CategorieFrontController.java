/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pointcollect.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.ImagePattern;

/**
 * FXML Controller class
 *
 * @author Khouloud
 */
public class CategorieFrontController implements Initializable {

    private List<Image> labels ;
    @FXML
    private FlowPane flowPane ;
    @FXML
    private ImageView img ;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
          flowPane = new FlowPane();
              System.out.println("log:brows song");
          /*    for (int i = 0; i < 10; i++) {
                  Image image = new Image(new File("2.jpg").toURI().toString());
                  ImageView imageView = new ImageView(image);
                  flowPane.getChildren().add(imageView);
              }*/
        
         final CategorieTrieCrud p = new CategorieTrieCrud();
        final ArrayList arrayList = (ArrayList)p.listerCategorie();
        final ObservableList observableList = FXCollections.observableArrayList((Collection)arrayList);
       
          String ch = (String) observableList.get(1);
                  
      /*  for (int i = 0 ; i<observableList.size();i++)
        {
             
           
             
                  String ch = (String) observableList.get(i);
                  System.out.println("kkkk"+ch);
                  
                   Image image = new Image(new File("C:\\Users\\Khouloud\\Pictures\\blog\\a.png").toURI().toString());
                  ImageView imageView = new ImageView(image);
                  flowPane.getChildren().add(imageView);
              
        }
       */
      File file = new File(ch);
          Image image = new Image(file.toURI().toString());
        ImageView imageView = new ImageView();
              flowPane.getChildren().add(imageView);
          
              
         
        
    }    
    
    
    
    
}

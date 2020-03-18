/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.EcoSystem.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import com.nexmo.client.NexmoClientException;
import edu.EcoSystem.entities.CurrentUser;
import edu.EcoSystem.entities.Reclamation;
import edu.EcoSystem.entities.User;
import static edu.EcoSystem.gui.AjoutAdminController.user;
import edu.EcoSystem.services.RecCRUD;
import edu.EcoSystem.services.SendingMail;
import edu.EcoSystem.services.UserCRUD;
import edu.EcoSystem.touls.sms;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author zied
 */
public class AjoutreclamationController implements Initializable {
    
    
    

    @FXML
    private JFXTextArea tftexte;

    @FXML
    private JFXButton envoyer_rec;

    @FXML
    private JFXTextField tfuser_rec;
    
    @FXML
    private JFXComboBox<String> boxSujet;
    
     @FXML
    private Label controle2;

    @FXML
    private Label controle1;

    
    public static Reclamation rec ;
    int iduser;
    @FXML
    private JFXHamburger affmenu;
    @FXML
    private JFXDrawer menu;
    @FXML
    private JFXButton retourecs;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        boxSujet.getItems().addAll("Produit","Evenement","Facture","Point de collect","autres");
        
        
         try {
             AnchorPane anchrone;
             anchrone = FXMLLoader.load(getClass().getResource("/edu/EcoSystem/gui/test.fxml"));
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
             Logger.getLogger(AfficherUserRecsController.class.getName()).log(Level.SEVERE, null, ex);
         }
        
       
        
    }    
    
    
    @FXML
    private void ajoutRec(ActionEvent event) {
        if (tftexte.getText().equals("")){
            controle2.setText("Veuillez remplir Votre réclamation");
            tftexte.setStyle("-fx-border-color: red");
        }
        else{
            java.util.Date datecourante = new java.util.Date();
            java.sql.Date sqldatecourante =new  java.sql.Date(datecourante.getTime());
            rec =new Reclamation();
            rec.setRec_lib(boxSujet.getSelectionModel().getSelectedItem());
            rec.setRec_text(tftexte.getText());
            rec.setEtat(0);
            rec.setRec_writer(CurrentUser.id);
            rec.setRec_date(sqldatecourante); 
            UserCRUD uc= new UserCRUD();
            User use = new  User() ;
            try {
                use =  uc.getUserByUsername(tfuser_rec.getText());
            } catch (SQLException ex) {
                Logger.getLogger(AjoutreclamationController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
               
            rec.setRec_user(use.getId());

            RecCRUD rc=new RecCRUD();
            try {
         
                if(uc.existUsername(tfuser_rec.getText())){
                            rc.ajouterReclamation(rec);
                            TrayNotification tray= new TrayNotification("Information","votre reclamation est envoyée avec succées", NotificationType.SUCCESS);
                tray.setAnimationType(AnimationType.POPUP);
                tray.showAndDismiss(javafx.util.Duration.seconds(5));
                        }
                        else{
                            controle1.setText("Utilisateur n existe pas");
                            tfuser_rec.setStyle("-fx-border-color: red");
                        } 
    
            } catch (SQLException ex) {
                Logger.getLogger(AjoutreclamationController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
   
    
    
    @FXML
    private void retourRecs(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/edu/EcoSystem/gui/afficherUserRecs.fxml"));
        
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Mes reclamations");
        stage.setScene(scene);
        stage.show();
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
}

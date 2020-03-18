/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.EcoSystem.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import edu.EcoSystem.entities.CurrentUser;
import edu.EcoSystem.entities.User;
import static edu.EcoSystem.gui.AjoutAdminController.user;
import edu.EcoSystem.services.SendingMail;
import edu.EcoSystem.services.UserCRUD;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author zied
 */
public class LoginController implements Initializable {

      @FXML
    private AnchorPane content;

    @FXML
    private Button Register;

    @FXML
    private Text lblWelcome122;

    @FXML
    private Button Loging;

    @FXML
    private JFXTextField username;

    @FXML
    private JFXPasswordField password;
    
    @FXML
    private JFXButton newpassword;
    
    @FXML
    private Label controle;
    
     @FXML
    private JFXTextField email;

    @FXML
    private Button confirmation;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

     @FXML
    private void Loging_signal(ActionEvent event) throws SQLException, IOException {
           UserCRUD us = new UserCRUD() ;
                
        if (username.getText().equals("")) {
           controle.setText("veuillez saisir votre Nom");
           username.setStyle("-fx-border-color: red");
        }
        else if (password.getText().equals("")){ 
           controle.setText("veuillez saisir votre mot de passe");
           password.setStyle("-fx-border-color: red");
        }
        else if ( !us.login(username.getText(),password.getText())) {
           controle.setText("cordonnées invalides");
           password.setStyle("-fx-border-color: red");
           username.setStyle("-fx-border-color: red");
        }
        
       
        else { 
            CurrentUser cu=new CurrentUser(us.getUserByUsername(username.getText())) ;
       if (CurrentUser.enable == 1){
            if(CurrentUser.roles.equals("abonner") ){
         FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/edu/EcoSystem/gui/Menu.fxml"));
        /* 
         * if "fx:controller" is not set in fxml
         * fxmlLoader.setController(NewWindowController);
         */
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("espace abonner");
        stage.setScene(scene);
        stage.show();
        ((Node)(event.getSource())).getScene().getWindow().hide();
        
            }
            
            else {
                FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/edu/EcoSystem/gui/gestionUser.fxml"));
        /* 
         * if "fx:controller" is not set in fxml
         * fxmlLoader.setController(NewWindowController);
         */
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("espace admin");
        stage.setScene(scene);
        stage.show();
        ((Node)(event.getSource())).getScene().getWindow().hide();
            }
            
        }
        
        else{
                controle.setText("vous ete bloqué");
                }
            }
    
    }
    
    @FXML
    private void Register_signal(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/edu/EcoSystem/gui/inscription.fxml"));
        /* 
         * if "fx:controller" is not set in fxml
         * fxmlLoader.setController(NewWindowController);
         */
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("INSCRIPTION");
        stage.setScene(scene);
        stage.show();
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }
    
    /*
    @FXML
    private void loginTest_signal(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/edu/EcoSystem/gui/gestionUser.fxml"));
        
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("espace admin");
        stage.setScene(scene);
        stage.show();
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }
*/
   
    
    @FXML
    private void forgetpw(ActionEvent event){
        email.setVisible(true);
        confirmation.setVisible(true);
    }
    
    
    @FXML
    private void Newpassword (ActionEvent event) throws SQLException {
               
            int leftLimit = 97; // letter 'a'
    int rightLimit = 122; // letter 'z'
    int targetStringLength = 9;
    Random random = new Random();
    StringBuilder buffer = new StringBuilder(targetStringLength);
    for (int i = 0; i < targetStringLength; i++) {
        int randomLimitedInt = leftLimit + (int) 
          (random.nextFloat() * (rightLimit - leftLimit + 1));
        buffer.append((char) randomLimitedInt);
    }
    String generatedString = buffer.toString();
        UserCRUD us = new UserCRUD () ;
                
        if (username.getText().equals("")) {
           controle.setText("veuillez saisir votre Nom");
           username.setStyle("-fx-border-color: red");
        }
        else if (!us.existUser(email.getText())) {
            controle.setText("email non valide");
            email.setStyle("-fx-border-color: red");
        }
        else {
        SendingMail sm =new SendingMail("your password has been reset  , you can now login with with : \n password="+ generatedString,email.getText(), "mot de passe reinitialiser");
        sm.envoyer();
        us.modifierPassword(email.getText(), generatedString);
        
        TrayNotification tray= new TrayNotification("Information","veuillez consulter votre Email", NotificationType.SUCCESS);
        tray.setAnimationType(AnimationType.POPUP);
        tray.showAndDismiss(Duration.seconds(5));
        
        
        
        
        
        }   
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
        }
    

    
    
        


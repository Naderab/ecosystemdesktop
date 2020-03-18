/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.EcoSystem.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import edu.EcoSystem.entities.User;
import static edu.EcoSystem.gui.AjoutAdminController.user;
import edu.EcoSystem.services.SendingMail;
import edu.EcoSystem.services.UserCRUD;
import static java.awt.Color.red;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.Duration;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.stage.Stage;
import static javafx.util.Duration.seconds;
import javax.imageio.stream.FileImageInputStream;
import org.controlsfx.control.Notifications;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author zied
 */
public class InscriptionController implements Initializable {
    
    @FXML
    private JFXTextField username;

     @FXML
    private JFXTextField email;

    @FXML
    private JFXPasswordField password2;

    @FXML
    private JFXPasswordField password1;

    @FXML
    private Button ajoutAbonne;

     @FXML
    private Label controle1;

    @FXML
    private Label controle2;

    @FXML
    private Label controle3;

    @FXML
    private Label controle4;
    
    @FXML
    private Button backlogin;

    /**
     * Initializes the controller class.
     
     */
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ajoutAbonne.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!chaine_non_valide(username.getText())) {
                   controle1.setText("le champs nom est invalide!");
                   username.setStyle("-fx-border-color: red");
                   
                }
                    
                
                else if (username.getText().length()<5) {
                    controle1.setText("Le nom doit etre de longueur 5!") ;
                    username.setStyle("-fx-border-color: red;");
                }
                else if (!controle_mail(email.getText())) {
                    controle2.setText("email invalde!");
                    email.setStyle("-fx-border-color: red;");
                }
                else if(password1.getText().length()<5 ) {
                    controle3.setText("mot de passe trés courte!");
                    password1.setStyle("-fx-border-color: red;");
                }
                else if (   true != (password1.getText().equals(password2.getText()))    ) {
                  controle4.setText("les mots de passe ne sont pas identiques!");
                  password2.setStyle("-fx-border-color: red;");
                }
            
                else{
                    try {
                        user=new User() ;
                        user.setEmail(email.getText());
                        user.setUsername(username.getText());
                        user.setPassword(password1.getText());
                        user.setEnable(1);
                        
                        UserCRUD us = new UserCRUD() ;
                        
                        if(!us.existUsername(username.getText())){
                            
                            us.ajoutUser(user, password1.getText());
                            
                            
                            TrayNotification tray= new TrayNotification("Information","veuillez consulter votre Email", NotificationType.SUCCESS);
                            tray.setAnimationType(AnimationType.POPUP);
                            tray.showAndDismiss(javafx.util.Duration.seconds(5));
                            
                            String contenu = "vous ete le bienvenue dans EcoSystem \n tu peut connecté maintenant avec votre nom et mot de passe" ;
                            SendingMail sm = new SendingMail( contenu,user.getEmail(),"Bienbenue") ;
                            SendingMail.envoyer();
                            
                        }
                else{
                            controle1.setText("nom utilisateur existe deja");
                            username.setStyle("-fx-border-color: red");
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(InscriptionController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
    
                 
                   
          
            }
        });
        
        // TODO
    }    
    
    @FXML
    private void retourLogin_signal(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/edu/EcoSystem/gui/loginUser.fxml"));
        
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    public boolean chaine_non_valide(String s) { 
    s=s.toLowerCase() ;
    if (s.contains("  "))
        return false ;
    
for (int i=0;i<s.length();i++) {
   if ((( s.charAt(i)<'a') || (s.charAt(i)>'z')) && (s.charAt(i)!=' '))
    return false ; 
}

return true;
}
    
    
    public boolean controle_mail (String mail ) {
    if(mail.indexOf(" ")!=-1)
        return false ;
return ( Pattern.matches("^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)+$", mail));
}

public boolean chaine_en_espace(String s) { 
    for (int i=0;i<s.length();i++)
        if(s.charAt(i)!=' ')
            return false ;
    return true ;
}
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.EcoSystem.gui;

import com.jfoenix.controls.JFXTextField;
import edu.EcoSystem.entities.User;
import edu.EcoSystem.services.UserCRUD;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author zied
 */
public class AjoutAdminController implements Initializable {
     @FXML
    private JFXTextField username;

    @FXML
    private JFXTextField password2;

    @FXML
    private JFXTextField email;

    @FXML
    private JFXTextField password;

    @FXML
    private Label controle1;
    
    @FXML
    private Label controle2;

    @FXML
    private Label controle3;

    @FXML
    private Label controle4;


    @FXML
    private Button ajoutAdmin;

    @FXML
    private Button retourGU;
    
    
    
    @FXML
    private RadioButton admin;

    @FXML
    private ToggleGroup togglegroup1;

    @FXML
    private RadioButton abonner;

    public static User user ;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ajoutAdmin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!chaine_non_valide(username.getText())) {
                   controle1.setText("le champs nom est invalide");
                   username.setStyle("-fx-border-color: red");
                }
                else if (username.getText().length()<5) {
                    controle1.setText("le champs nom doit etre de longueur 5") ;
                    username.setStyle("-fx-border-color: red");
                }
                else if (!controle_mail(email.getText())) {
                    controle2.setText("email invalde");
                    email.setStyle("-fx-border-color: red");                
                }
                else if(password.getText().length()<5 ) {
                    controle3.setText("mot de passe trés courte");
                    email.setStyle("-fx-border-color: red");
                }
                else if (   true != (password.getText().equals(password2.getText()))    ) {
                  controle4.setText("les mots de passe ne sont pas identiques");
                }
                else{
                    try {
                        user=new User() ;
                        user.setEmail(email.getText());
                        user.setUsername(username.getText());
                        user.setPassword(password.getText());
                        user.setEnable(1);
                        if(admin.isSelected()){
                            user.setRoles("admin");
                        }
                        else if(abonner.isSelected()){
                            user.setRoles("abonner");
                        }
                        
                        UserCRUD us = new UserCRUD() ;
                        if(!us.existUsername(username.getText())){
                            us.ajoutAdmin(user, password.getText());
                            new Alert(Alert.AlertType.INFORMATION, "Ajouter Avec succées").show();
                        }
                        else{
                            controle1.setText("nom utilisateur existe deja");
                            username.setStyle("-fx-border-color: red");
                        }  } catch (SQLException ex) {
                        Logger.getLogger(AjoutAdminController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
          
            }
        });
        // TODO
    }    
    
    
    
    @FXML
    private void retourGU_signal(ActionEvent event) throws IOException{
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

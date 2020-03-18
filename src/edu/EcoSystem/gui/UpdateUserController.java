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
import edu.EcoSystem.services.UserCRUD;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author zied
 */
public class UpdateUserController implements Initializable {
    
    
    @FXML
    private Label controle1;

    @FXML
    private RadioButton admin;

    @FXML
    private ToggleGroup togglegroup1;

    @FXML
    private RadioButton abonner;

    @FXML
    private JFXTextField username;


    @FXML
    private JFXTextField email;


    @FXML
    private Label controle2;


    @FXML
    private JFXButton updateUser;

    @FXML
    private JFXButton annulerUpdate;
    
    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXPasswordField password2;


     public static User user ;
     private Stage dialogStage;
     private boolean okClicked = false;
     
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
     public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    
    public void setUser(User u,int id) {
        this.user = u;

        
        username.setText(u.getUsername());
        email.setText(u.getEmail());
        user.setId(id);
        
        
    }
public boolean isOkClicked() {
        return okClicked;
    }
    @FXML
    private void Retour(ActionEvent event) {
        dialogStage.close();
    }
    
    
    @FXML
    public void updateUser(ActionEvent event){
         if (!chaine_non_valide(username.getText())) {
                   controle1.setText("le champs nom est invalide");
                   username.setStyle("-fx-background-color: red;");
                }
                else if (username.getText().length()<5) {
                    controle1.setText("le champs nom doit etre de longueur 5") ;
                    username.setStyle("-fx-background-color: red;");
                }
                else if (!controle_mail(email.getText())) {
                    controle2.setText("email invalde");
                    email.setStyle("-fx-background-color: red;");
                }
            
                else{
                    User us=new User();
                 us.setEmail(email.getText());
                 us.setUsername(username.getText());
                 us.setPassword(password.getText());
                 us.setId(user.getId());
                 
                 
               
                 
                 UserCRUD uc = new UserCRUD() ;
                 uc.modifierUser(us);
                 System.out.println(user.getId());

                 okClicked = true;
                 dialogStage.close();
                 
                 
                 }
                   
          
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


    @FXML
    private void AnnulerUpdate(ActionEvent event) {
    }
    
    
    
    
    
    
    
    
    
}

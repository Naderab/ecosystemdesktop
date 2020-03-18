/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.EcoSystem.gui;

import com.jfoenix.controls.JFXButton;
import ecosystem.EcoSystem;
import edu.EcoSystem.entities.CurrentUser;
import static edu.EcoSystem.entities.CurrentUser.enable;
import edu.EcoSystem.entities.User;
import edu.EcoSystem.services.UserCRUD;
import edu.EcoSystem.tests.MainClass;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import static javafx.collections.FXCollections.observableList;
import javafx.collections.ObservableList;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import javafx.util.StringConverter;
import static org.controlsfx.control.action.ActionMap.action;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author zied
 */
public class GestionUserController implements Initializable {
     @FXML
    private TableView<User> table;
    
    @FXML
    private TableColumn<?, ?> id;

    @FXML
    private TableColumn<?, ?> nom;

    @FXML
    private TableColumn<?, ?> email;

    @FXML
    private TableColumn<?, ?> role;

    @FXML
    private TableColumn<User,Void> etat;

    @FXML
    private TextField Search;

    @FXML
    private Button ajoutAdmin;

    @FXML
    private JFXButton parametreAdmin;
    

    @FXML
    private Button supprimerUser;
    
    
    
    

    private TextField tfusername;

    private TextField tfemail;

    private PasswordField tfpassword;
    
    
    private Label controle;
    
    
    /*public void setNom(String nom){
this.Nom.setText(Nom);

}*/
    @FXML
    private Button reclamation_btn;
    @FXML
    private TableView<User> table1;
    @FXML
    private TableColumn<User, Integer> id1;
    @FXML
    private TableColumn<User, String> nom1;
    @FXML
    private TableColumn<User, String> email1;
    @FXML
    private TableColumn<User, String> role1;
    @FXML
    private TableColumn<User, Void> etat1;
    @FXML
    private TextField Search1;
    @FXML
    private Button ajoutAdmin1;
    @FXML
    private Button supprimerUser1;
    
    
    UserCRUD usercrud ;
        ArrayList<User> users ;
        ObservableList<User> obs;
        
         ArrayList<User> users1 ;
        ObservableList<User> obs1 ;
    @FXML
    private Button gestionPanier;
    @FXML
    private Button gestionCommande;
    @FXML
    private Button gestionEvenement;
    @FXML
    private JFXButton Gotofront;
    @FXML
    private Button gestionOffres;
    @FXML
    private Button gestionpc;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        
        Search.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
			if (!Search.getText().isEmpty()) {
                            
                            try {
                                ObservableList data = FXCollections.observableArrayList(new UserCRUD().chercherEnabled(Search.getText()));
                                table.setItems(data);
                            } catch (SQLException ex) {
                                Logger.getLogger(GestionUserController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
			} else {
                           
                            
                            try {
                                ObservableList data = FXCollections.observableArrayList(new UserCRUD().getAllUsers());
                                table.setItems(data);
                            } catch (SQLException ex) {
                                Logger.getLogger(GestionUserController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                            
			}
		});
        
         Search1.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
			if (!Search1.getText().isEmpty()) {
                            
                            try {
                                ObservableList data = FXCollections.observableArrayList(new UserCRUD().chercherDisabled(Search.getText()));
                                table1.setItems(data);
                            } catch (SQLException ex) {
                                Logger.getLogger(GestionUserController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
			} else {
                           
                            
                            try {
                                ObservableList data = FXCollections.observableArrayList(new UserCRUD().getAllUsers());
                                table1.setItems(data);
                            } catch (SQLException ex) {
                                Logger.getLogger(GestionUserController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                            
			}
		});

        
        
        
        
        
        
        table.setEditable(true);
        table1.setEditable(true);
        
        usercrud = new UserCRUD();
        
        
         users = (ArrayList <User>) usercrud.afficherUserEnabled();
         obs= FXCollections.observableArrayList(users);
        
         users1 = (ArrayList <User>) usercrud.afficherUserDisabled();
         obs1= FXCollections.observableArrayList(users1);
        
        table.setItems(obs);
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom.setCellValueFactory(new PropertyValueFactory<>("username"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        role.setCellValueFactory(new PropertyValueFactory<>("roles"));
        
        table1.setItems(obs1);
        id1.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom1.setCellValueFactory(new PropertyValueFactory<>("username"));
        email1.setCellValueFactory(new PropertyValueFactory<>("email"));
        role1.setCellValueFactory(new PropertyValueFactory<>("roles"));
  
 
          Callback<TableColumn<User, Void>, TableCell<User, Void>> cellFactory = new Callback<TableColumn<User, Void>, TableCell<User, Void>>() {
            @Override
            public TableCell<User, Void> call(final TableColumn<User, Void> param) {
                final TableCell<User, Void> cell = new TableCell<User, Void>() {

                    private final JFXButton btn = new JFXButton("Bloqué");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            
                                User data = getTableView().getItems().get(getIndex());
                                System.out.println("selectedData: " + data);
                                
                                
                            try {
                                usercrud.modifierEtat(data.getId(), 0);
                            } catch (SQLException ex) {
                                Logger.getLogger(GestionUserController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                                
                                TrayNotification tray = new TrayNotification("Traitement", "User a été bloqué", NotificationType.SUCCESS);
                                tray.setAnimationType(AnimationType.POPUP);
                                tray.showAndDismiss(Duration.seconds(3));
                            
                                
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        etat.setCellFactory(cellFactory);
      
          Callback<TableColumn<User, Void>, TableCell<User, Void>> cellFactory1 = new Callback<TableColumn<User, Void>, TableCell<User, Void>>() {
            @Override
            public TableCell<User, Void> call(final TableColumn<User, Void> param) {
                final TableCell<User, Void> cell = new TableCell<User, Void>() {

                    private final JFXButton btn = new JFXButton("Activé");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            
                                User data = getTableView().getItems().get(getIndex());
                                System.out.println("selectedData: " + data.getUsername());
                                
                                
                            try {
                                usercrud.modifierEtat(data.getId(), 1);
                            } catch (SQLException ex) {
                                Logger.getLogger(GestionUserController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                                
                                TrayNotification tray = new TrayNotification("Traitement", "User a été activé", NotificationType.SUCCESS);
                                tray.setAnimationType(AnimationType.POPUP);
                                tray.showAndDismiss(Duration.seconds(3));
                            
                                
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        etat1.setCellFactory(cellFactory1);
      
  }

        

        
     @FXML
    private void Refrechtable(javafx.scene.input.MouseEvent event) {
         
        
        obs.clear();
        usercrud = new UserCRUD();
        
        
         users = (ArrayList <User>) usercrud.afficherUserEnabled();
         obs= FXCollections.observableArrayList(users);
        table.setItems(obs);
    }
    
          @FXML
    private void Refrechtable1(javafx.scene.input.MouseEvent event) {
         
        
        obs1.clear();
        usercrud = new UserCRUD();
        users1 = (ArrayList <User>) usercrud.afficherUserDisabled();
         obs1= FXCollections.observableArrayList(users1);
        table1.setItems(obs1);
    }

                
    
    

    
    
       

   
    
    @FXML
    private void ajoutAdmin_signal(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/edu/EcoSystem/gui/AjoutAdmin.fxml"));
        /* 
         * if "fx:controller" is not set in fxml
         * fxmlLoader.setController(NewWindowController);
         */
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("ajouter un admin");
        stage.setScene(scene);
        stage.show();
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }
    
    
    
    @FXML
    private void supprimerUser(ActionEvent event){
        
            User selectedIndex = table.getSelectionModel().getSelectedItem();
        int selected = table.getSelectionModel().getSelectedIndex();
        if (selected >= 0) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            
                         alert.setTitle("Notification");
                         alert.setContentText("Etes vous sure de vouloir supprimer ?");
                         Optional<ButtonType> result = alert.showAndWait();
        ButtonType button = result.orElse(ButtonType.CANCEL);
        
        if (button == ButtonType.OK){
        UserCRUD usercrud = new UserCRUD();
        usercrud.supprimerUser(selectedIndex.getId());
        table.getItems().remove(selected);
        new Alert(Alert.AlertType.INFORMATION, "Supprimer Avec succées").show();
        }
    } else {
        new Alert(Alert.AlertType.INFORMATION, "Aucun utilisateur  selectionné").show();
           
        }
        
    }
    
    
    
    /*@FXML
    private void modifierUser(ActionEvent Event){
        
       User selectedEvent = table.getSelectionModel().getSelectedItem();
    if (selectedEvent != null) {
        boolean okClicked = EcoSystem.showEventEditDialog(selectedEvent);
        if (okClicked) {
        }

    } else {
        // Nothing selected.
                new Alert(Alert.AlertType.ERROR, "Aucun utilisateur selectionnÃ©").show();

    }
        
}*/
                
        
        
        
    
    
    
   
   /* public void blockUser(ActionEvent Event) throws SQLException{
        User selectedIndex = table.getSelectionModel().getSelectedItem();
        int selected = table.getSelectionModel().getSelectedIndex();
        if (selected >= 0) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            
                         alert.setTitle("Notification");
                         alert.setContentText("Etes vous sure de vouloir bloqué cette utilisateur ?");
                         Optional<ButtonType> result = alert.showAndWait();
        ButtonType button = result.orElse(ButtonType.CANCEL);
        if (button == ButtonType.OK){
        UserCRUD usercrud = new UserCRUD();
        usercrud.modifierEtat(, enable);
        table.getItems().set(enable, selectedIndex);
        new Alert(Alert.AlertType.INFORMATION, "bloqué Avec succées").show();
        }
    } else {
        new Alert(Alert.AlertType.INFORMATION, "Aucun utilisateur  selectionné").show();
           
        }
    }*/
    
    
    
    
    @FXML
    private void modifierAdmin(ActionEvent Event){
        User us = new User(CurrentUser.username, CurrentUser.email);
        boolean okClicked = EcoSystem.showEventEditDialog(us,CurrentUser.id);
    }
    
    
    
    
    
    
    
    
    
    
      @FXML
    private void G_reclamation(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/edu/EcoSystem/gui/afficherRec.fxml"));
        /* 
         * if "fx:controller" is not set in fxml
         * fxmlLoader.setController(NewWindowController);
         */
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Gestion des reclamations");
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

    @FXML
    private void gestionPanier(ActionEvent event) throws IOException {
         FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/edu/EcoSystem/gui/AjoutPanier_Back.fxml"));
        /* 
         * if "fx:controller" is not set in fxml
         * fxmlLoader.setController(NewWindowController);
         */
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        //stage.setTitle("Gestion des reclamations");
        stage.setScene(scene);
        stage.show();
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    private void gestionCommande(ActionEvent event) throws IOException {
         FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/edu/EcoSystem/gui/AjoutCommande_Back.fxml"));
        /* 
         * if "fx:controller" is not set in fxml
         * fxmlLoader.setController(NewWindowController);
         */
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        //stage.setTitle("Gestion des reclamations");
        stage.setScene(scene);
        stage.show();
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }

    
            
    @FXML
    private void Gotofront(ActionEvent event) throws IOException {
         FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/edu/EcoSystem/gui/Menu.fxml"));
        /* 
         * if "fx:controller" is not set in fxml
         * fxmlLoader.setController(NewWindowController);
         */
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        //stage.setTitle("Gestion des reclamations");
        stage.setScene(scene);
        stage.show();
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }        

    @FXML
    private void gestionEvenement(ActionEvent event) throws IOException {
        
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/edu/EcoSystem/gui/ListEventBack.fxml"));
        /* 
         * if "fx:controller" is not set in fxml
         * fxmlLoader.setController(NewWindowController);
         */
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        //stage.setTitle("Mes reclamation");
        stage.setScene(scene);
        stage.show();
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    private void gestionOffre(ActionEvent event) throws IOException {
         FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/edu/EcoSystem/gui/ListBack.fxml"));
        /* 
         * if "fx:controller" is not set in fxml
         * fxmlLoader.setController(NewWindowController);
         */
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        //stage.setTitle("Mes reclamation");
        stage.setScene(scene);
        stage.show();
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    private void gestionpc(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/edu/pointcollect/gui/CrudAddress.fxml"));
        /* 
         * if "fx:controller" is not set in fxml
         * fxmlLoader.setController(NewWindowController);
         */
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        //stage.setTitle("Mes reclamation");
        stage.setScene(scene);
        stage.show();
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }
    





  
        
    }
    
    
  
    


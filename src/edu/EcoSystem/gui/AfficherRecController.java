/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.EcoSystem.gui;

import com.jfoenix.controls.JFXButton;
import com.nexmo.client.NexmoClientException;
import edu.EcoSystem.entities.Reclamation;
import edu.EcoSystem.entities.User;
import edu.EcoSystem.services.RecCRUD;
import edu.EcoSystem.services.SendingMail;
import edu.EcoSystem.services.UserCRUD;
import edu.EcoSystem.touls.sms;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import javafx.util.StringConverter;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author zied
 */
public class AfficherRecController implements Initializable {

    @FXML
    private TableView<?> table_rec;

    @FXML
    private TableColumn<?, ?> id_rec;

    @FXML
    private TableColumn<?, ?> theme_rec;

    @FXML
    private TableColumn<?, ?> contenu_rec;

    @FXML
    private TableColumn<?, ?> date_rec;

    @FXML
    private TableColumn<?, ?> auteur_rec;

    @FXML
    private TableColumn<?, ?> reclamé_rec;


    RecCRUD rc;
    @FXML
    private Button gestionuser_btn;
    @FXML
    private Button suppRec_btn;
    @FXML
    private TableView<?> table_rec1;
    @FXML
    private TableColumn<?, ?> id_rec1;
    @FXML
    private TableColumn<?, ?> theme_rec1;
    @FXML
    private TableColumn<?, ?> contenu_rec1;
    @FXML
    private TableColumn<?, ?> date_rec1;
    @FXML
    private TableColumn<?, ?> auteur_rec1;
    @FXML
    private TableColumn<?, ?> reclamé_rec1;
    @FXML
    private TableColumn<Reclamation, Void> etat_rec1;
    @FXML
    private Button suppRec_btn1;
    
    ArrayList<Reclamation> recs ;
        ObservableList obs ;
        
         ArrayList<Reclamation> recs1 ;
        ObservableList obs1 ;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        /*

        rc = new RecCRUD();
        UserCRUD uc = new UserCRUD();
        List<User> users = new ArrayList<User>();
        try {
            users = uc.getAllUsers();
        } catch (SQLException ex) {
            Logger.getLogger(AfficherRecController.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (User u : users) {
            try {
                int nb = rc.nbreRec(u.getId());
                System.out.println(nb);

                if (nb >= 3) {

                    uc.modifierEtat(u.getId(), 0);
                    System.out.println(u.getId());
                    
                    sms sm = new sms();
                    sm.sendsms("utilisateur" + u.getUsername() + " est bloqué");
                    String contenu = "vous ete bloqué ";
                    SendingMail sm1 = new SendingMail(contenu, u.getEmail(), "Bienvenue");
                    SendingMail.envoyer();
                }
            } catch (SQLException ex) {
                Logger.getLogger(AfficherRecController.class.getName()).log(Level.SEVERE, null, ex);
                
             
            } catch (IOException ex) {
                Logger.getLogger(AfficherRecController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NexmoClientException ex) {
                Logger.getLogger(AfficherRecController.class.getName()).log(Level.SEVERE, null, ex);
               
                
            }
                

        }
        
        */

        table_rec.setEditable(true);
        table_rec1.setEditable(true);

         rc = new RecCRUD();

         recs = (ArrayList<Reclamation>) rc.getAllRecTraitées();
      obs = FXCollections.observableArrayList(recs);
        
         recs1 = (ArrayList<Reclamation>) rc.getAllRecNonTraitées();
        obs1 = FXCollections.observableArrayList(recs1);

        table_rec.setItems(obs);
        id_rec.setCellValueFactory(new PropertyValueFactory<>("rec_id"));
        theme_rec.setCellValueFactory(new PropertyValueFactory<>("rec_lib"));
        contenu_rec.setCellValueFactory(new PropertyValueFactory<>("rec_text"));
        date_rec.setCellValueFactory(new PropertyValueFactory<>("rec_date"));
        auteur_rec.setCellValueFactory(new PropertyValueFactory<>("rec_writer"));
        reclamé_rec.setCellValueFactory(new PropertyValueFactory<>("rec_user"));
        
        table_rec1.setItems(obs1);
        id_rec1.setCellValueFactory(new PropertyValueFactory<>("rec_id"));
        theme_rec1.setCellValueFactory(new PropertyValueFactory<>("rec_lib"));
        contenu_rec1.setCellValueFactory(new PropertyValueFactory<>("rec_text"));
        date_rec1.setCellValueFactory(new PropertyValueFactory<>("rec_date"));
        auteur_rec1.setCellValueFactory(new PropertyValueFactory<>("rec_writer"));
        reclamé_rec1.setCellValueFactory(new PropertyValueFactory<>("rec_user"));
        
        

      
          Callback<TableColumn<Reclamation, Void>, TableCell<Reclamation, Void>> cellFactory1 = new Callback<TableColumn<Reclamation, Void>, TableCell<Reclamation, Void>>() {
            @Override
            public TableCell<Reclamation, Void> call(final TableColumn<Reclamation, Void> param) {
                final TableCell<Reclamation, Void> cell = new TableCell<Reclamation, Void>() {

                    private final JFXButton btn = new JFXButton("Accepter Reclamation ");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            
                                Reclamation data = getTableView().getItems().get(getIndex());
                                //System.out.println("selectedData: " + data.getUsername());
                                
                                
                            try {
                                rc.modifierEtatRec(data.getRec_id(), 1);
                            } catch (SQLException ex) {
                                Logger.getLogger(GestionUserController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                                
                            
                            rc = new RecCRUD();
        UserCRUD uc = new UserCRUD();
        List<User> users = new ArrayList<User>();
        try {
            users = uc.getAllUsers();
        } catch (SQLException ex) {
            Logger.getLogger(AfficherRecController.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (User u : users) {
            try {
                int nb = rc.nbreRec(u.getId());
                System.out.println(nb);

                if (nb >= 3) {

                    uc.modifierEtat(u.getId(), 0);
                    System.out.println(u.getId());
                    
                    sms sm = new sms();
                    sm.sendsms("utilisateur" + u.getUsername() + " est bloqué");
                    String contenu = "vous ete bloqué ";
                    SendingMail sm1 = new SendingMail(contenu, u.getEmail(), "Bienvenue");
                    SendingMail.envoyer();
                }
            } catch (SQLException ex) {
                Logger.getLogger(AfficherRecController.class.getName()).log(Level.SEVERE, null, ex);
                
             
            } catch (IOException ex) {
                Logger.getLogger(AfficherRecController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NexmoClientException ex) {
                Logger.getLogger(AfficherRecController.class.getName()).log(Level.SEVERE, null, ex);
               
                
            }
                

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

        etat_rec1.setCellFactory(cellFactory1);
        
    }

    @FXML
    private void G_utilisateur(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/edu/EcoSystem/gui/gestionUser.fxml"));
        /* 
         * if "fx:controller" is not set in fxml
         * fxmlLoader.setController(NewWindowController);
         */
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Gestion des utilisateurs");
        stage.setScene(scene);
        stage.show();
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    private void supprimerRec(ActionEvent event) {

        Reclamation selectedIndex = (Reclamation) table_rec.getSelectionModel().getSelectedItem();
        int selected = table_rec.getSelectionModel().getSelectedIndex();
        if (selected >= 0) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

            alert.setTitle("Notification");
            alert.setContentText("Etes vous sure de vouloir supprimer ?");
            Optional<ButtonType> result = alert.showAndWait();
            ButtonType button = result.orElse(ButtonType.CANCEL);

            if (button == ButtonType.OK) {
                RecCRUD rc = new RecCRUD();
                rc.supprimerRec(selectedIndex.getRec_id());
                table_rec.getItems().remove(selected);
                new Alert(Alert.AlertType.INFORMATION, "Supprimer Avec succées").show();
            }
        } else {
            new Alert(Alert.AlertType.INFORMATION, "Aucune reclamation  selectionné").show();

        }

    }
    
    
            @FXML
    private void Refrechtable(javafx.scene.input.MouseEvent event) {
         
        
        obs.clear();
        
        
        
         recs = (ArrayList <Reclamation>) rc.getAllRecTraitées();
         obs= FXCollections.observableArrayList(recs);
        table_rec.setItems(obs);
    }
    
          @FXML
    private void Refrechtable1(javafx.scene.input.MouseEvent event) {
         
        
        obs1.clear();
       
        recs1 = (ArrayList <Reclamation>) rc.getAllRecNonTraitées();
         obs1= FXCollections.observableArrayList(recs1);
        table_rec1.setItems(obs1);
    }

}

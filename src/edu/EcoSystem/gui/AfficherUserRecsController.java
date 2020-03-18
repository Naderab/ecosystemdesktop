/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.EcoSystem.gui;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
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
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
public class AfficherUserRecsController implements Initializable {
    
    
     @FXML
    private AnchorPane ap;

    @FXML
    private ScrollPane pane;

    @FXML
    private TableView<?> table_recUser;
    
    @FXML
    private TableColumn<?, ?> theme_rec;

    @FXML
    private TableColumn<?, ?> contenu_rec;

    @FXML
    private TableColumn<?, ?> date_rec;
    
    @FXML
    private TableColumn<?, ?> reclamé_rec;

    @FXML
    private TableColumn<?, ?> etat_rec;

    @FXML
    private Button ajoutrec;

    @FXML
    private Button supprec;
    
     @FXML
    private JFXHamburger affmenu;

    @FXML
    private JFXDrawer menu;

    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
     
         
         try {
   
             table_recUser.setEditable(true);
             
             RecCRUD rc = new RecCRUD();
             
             
             ArrayList<Reclamation> recs = (ArrayList <Reclamation>) rc.getMyRec();
             ObservableList obs= FXCollections.observableArrayList(recs);
             
             table_recUser.setItems(obs);
             theme_rec.setCellValueFactory(new PropertyValueFactory<>("rec_lib"));
             contenu_rec.setCellValueFactory(new PropertyValueFactory<>("rec_text"));
             date_rec.setCellValueFactory(new PropertyValueFactory<>("rec_date"));
             reclamé_rec.setCellValueFactory(new PropertyValueFactory<>("rec_user"));
             etat_rec.setCellValueFactory(new PropertyValueFactory<>("etat"));

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
             
             
             
             
             
         } catch (SQLException ex) {
             Logger.getLogger(AfficherUserRecsController.class.getName()).log(Level.SEVERE, null, ex);
         }
        
        
        
        
        
    }    
 
    
    
    
    
    
    @FXML
    private void supprimerUser(ActionEvent event){
        
            Reclamation selectedIndex = (Reclamation) table_recUser.getSelectionModel().getSelectedItem();
        int selected = table_recUser.getSelectionModel().getSelectedIndex();
        if (selected >= 0) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            
                         alert.setTitle("Notification");
                         alert.setContentText("Etes vous sure de vouloir supprimer ?");
                         Optional<ButtonType> result = alert.showAndWait();
        ButtonType button = result.orElse(ButtonType.CANCEL);
        
        if (button == ButtonType.OK){
        RecCRUD reccrud = new RecCRUD();
        reccrud.supprimerRec(selectedIndex.getRec_id());
        table_recUser.getItems().remove(selected);
        TrayNotification tray= new TrayNotification("Information","reclamation supprimer avec succée", NotificationType.SUCCESS);
                tray.setAnimationType(AnimationType.POPUP);
                tray.showAndDismiss(javafx.util.Duration.seconds(5));
        }
    } else {
        new Alert(Alert.AlertType.INFORMATION, "Aucune reclamation  selectionné").show();
           
        }
        
    }
    
    
    
    
     @FXML
    private void formRec(ActionEvent event) throws IOException {
         FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/edu/EcoSystem/gui/AjoutreclamationUser.fxml"));
        
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("reclamation");
        stage.setScene(scene);
        stage.show();
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}

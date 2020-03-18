/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.EcoSystem.controller;

import edu.EcoSystem.entities.Panier;
import edu.EcoSystem.services.CRUDPanier;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterAttributes;
import javafx.print.PrinterJob;
import javafx.scene.transform.Scale;

/**
 * FXML Controller class
 *
 * @author MEGAJAS
 */
public class AjoutPanier_BackController implements Initializable {

    @FXML
    private TextField prix;
    @FXML
    private TextField nom;
    @FXML
    private TextField id_client;
    @FXML
    private DatePicker date;
    @FXML
    private Button ajouter;
    @FXML
    private TableView<Panier> table;
    private TableColumn<?, ?> id;
      
    
    public ArrayList<Panier> ran;
    @FXML
    private TableColumn<?, ?> id1;
    @FXML
    private TableColumn<?, ?> prix1;
    @FXML
    private TableColumn<?, ?> date1;
    @FXML
    private TableColumn<?, ?> nom1;
    @FXML
    private TableColumn<?, ?> id_client1;
    @FXML
    private TextField prix_mod;
    @FXML
    private TextField nom_mod;
    @FXML
    private Button modifier;
    @FXML
    private Button supprimer;
    @FXML
    private TextField id_mod;
    @FXML
    private Button ButtonCommande;
    @FXML
    private TextField rechercherPanier;
    @FXML
    private ComboBox<String> trierPanier;
    ObservableList<String> TriList=FXCollections.observableArrayList("prix","Date");
    
    
    @FXML
    private TableView<Panier> tablePanier;
    @FXML
    private TableColumn<?, ?> idP;
    @FXML
    private TableColumn<?, ?> prixP;
    @FXML
    private TableColumn<?, ?> dateP;
    @FXML
    private TableColumn<?, ?> nomP;
    @FXML
    private TableColumn<?, ?> id_clientP;
    @FXML
    private Button imprimerPanier;
    @FXML
    private JFXButton logbackpanier;



    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        affichertable();
        
         trierPanier.getItems().addAll("prix","date");
           trierPanier.valueProperty().addListener(new ChangeListener<String>() {
        @Override public void changed(ObservableValue ov, String t, String t1) {
                  //System.err.println("ok");
              if(trierPanier.getValue().equals("prix"))
                   { 
                      //System.err.println("ok1");
                       TriList.clear();
                       TrierPrixPanier();
                   }
               if(trierPanier.getValue().equals("date"))
                   {
                      
                       TriList.clear();
                       TrierDatePanier();
                   }
              
                   }});
        
        
    }

    @FXML
    private void ajouterpanier(ActionEvent event) throws SQLException{
         CRUDPanier cr = new CRUDPanier();
         if( prix.getText().isEmpty() || nom.getText().isEmpty()  ||id_client.getText().isEmpty())
        {
         Alert alert5 = new Alert(Alert.AlertType.ERROR);
            alert5.setTitle("Verifier le Panier ");
            alert5.setHeaderText(null);
            alert5.setContentText("Merci de bien vouloir remplir votre champ");
            alert5.showAndWait();
        }
         else if(!prix.getText().matches("^([1-9][0-9]{0,4}|100000)$")){    
          Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Valider le Prix   du Panier");
            alert1.setHeaderText(null);
            alert1.setContentText("Veuillez verifier le prix  entre 1 et 99999  ");
            alert1.showAndWait();
         }
         else if(LocalDate.now().isAfter(date.getValue()))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Date Du Panier est inférieure à la date actuelle!");
            alert.setContentText("Erreur");
            alert.showAndWait();
        }
         else if(!nom.getText().matches("^[a-zA-Z\\s]*$") )
         {
              Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Valider les noms des Paniers");
            alert1.setHeaderText(null);
            alert1.setContentText("Veuillez les Noms des Produits ");
            alert1.showAndWait();
         }
        else
        {
        Panier p = new Panier( prix.getText(), date.getValue(),  nom.getText(), Integer.parseInt(id_client.getText()));
                
                //prix.getText(),
                //nom.getText(),
                //Integer.parseInt(id_client.getText()),
       
       cr.ajouterPanier(p);
       
        Notifications notif = Notifications.create()
                .title("Panier ajouté")
                .text("Le panier est bien ajoutée !")
                .darkStyle().graphic(null).hideAfter(Duration.seconds(5))
                .position(Pos.TOP_LEFT)
                .onAction(new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent event) {
                        System.out.println("Clicked ont notif");
                    }
                });

        notif.showConfirm();
        }
        refreshtable();   
        clearPanier();
    }
    public void clearPanier()
     {
         prix.clear();
         nom.clear();
         date.setValue(null);
         id_client.clear();
     
     }
    
    
 
    private void affichertable() {

        Task<ArrayList<Panier>> task = new Task() {

            @Override
            protected Object call() throws SQLException {
                ran = (ArrayList<Panier>) new CRUDPanier().afficherPanier();
                return ran;
            }
        };
        task.setOnSucceeded((WorkerStateEvent e) -> {
            id1.setCellValueFactory(new PropertyValueFactory<>("id"));
            prix1.setCellValueFactory(new PropertyValueFactory<>("prix"));
            date1.setCellValueFactory(new PropertyValueFactory<>("date"));
            nom1.setCellValueFactory(new PropertyValueFactory<>("nom"));
            id_client1.setCellValueFactory(new PropertyValueFactory<>("id_client"));
            table.setItems(FXCollections.observableArrayList(task.getValue()));
        });
        task.setOnFailed(e -> {
            affichertable();
        });
        Thread th = new Thread(task);
        th.start();

    }

    
    
    
    
    
    
    
    @FXML
    private void ShowOnClick(MouseEvent event) {
        
        
        
         table.setOnMouseClicked(e -> {
            Panier s = (Panier) table.getSelectionModel().getSelectedItem();
             try {
                 ran = (ArrayList<Panier>) new CRUDPanier().afficherPanier();
             } catch (SQLException ex) {
                 Logger.getLogger(AjoutPanier_BackController.class.getName()).log(Level.SEVERE, null, ex);
             }

            id_mod.setText(Integer.toString(s.getId()));
            nom_mod.setText(s.getNom());
            prix_mod.setText(s.getPrix());
          

        });
        
        
        
        
        
        
    }

    @FXML
    private void modifier(ActionEvent event) throws SQLException {
        CRUDPanier cr = new CRUDPanier();
        Panier p = new Panier(Integer.parseInt(id_mod.getText()),
                prix_mod.getText(),
                nom_mod.getText()
        );
        cr.modifierPanier(p);
        
        Notifications notif = Notifications.create()
                .title("Panier Modifie")
                .text("Le panier est bien modifiee !")
                .darkStyle().graphic(null).hideAfter(Duration.seconds(5))
                .position(Pos.TOP_LEFT)
                .onAction(new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent event) {
                        System.out.println("Clicked ont notif");
                    }
                });

        notif.showConfirm();
        refreshtable();
        clearPanierBack();
        
    }
    public void clearPanierBack()
     {
         
         id_mod.clear();
         prix_mod.clear();
         nom_mod.clear();
     }

    @FXML
    private void supprimer(ActionEvent event) throws SQLException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
alert.setTitle("Confirmation");
alert.setHeaderText("Suppression");
alert.setContentText("Voulez vous supprimer cet Panier?");

Optional<ButtonType> result = alert.showAndWait();
if (result.get() == ButtonType.OK){
         Panier s = (Panier) table.getSelectionModel().getSelectedItem();
        int id = Integer.parseInt(id_mod.getText());
        CRUDPanier cr = new CRUDPanier();
        cr.supprimerPanier(id);
        
        refreshtable();
       
    } 
else{ 
       clearPanierBack();
    
}}

    public void refreshtable() throws SQLException{
             CRUDPanier cr = new CRUDPanier();
        ran = cr.afficherPanier();
        ObservableList data = FXCollections.observableArrayList(ran);
        table.setItems(data);
    }

    private void ButtonPanier(ActionEvent event) {
         try {Stage stage = new Stage();
        Parent home = FXMLLoader.load(getClass().getResource("/edu/EcoSystem/gui/AjoutPanier_Back.fxml"));
        Scene hoomescene = new Scene(home);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(hoomescene);
        app_stage.show();
        } catch (IOException ex) {
            Logger.getLogger(edu.EcoSystem.controller.AjoutPanier_BackController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void ButtonCommande(ActionEvent event) {
        try {Stage stage = new Stage();
        Parent home = FXMLLoader.load(getClass().getResource("/edu/EcoSystem/gui/AjoutCommande_Back.fxml"));
        Scene hoomescene = new Scene(home);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(hoomescene);
        app_stage.show();
        } catch (IOException ex) {
            Logger.getLogger(edu.EcoSystem.controller.AjoutCommande_BackController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }

    @FXML
    private void rechercherPanier(KeyEvent event) {
        
        Task<ArrayList<Panier>> task = new Task() {

            @Override
            protected Object call() throws SQLException {
                ran = (ArrayList<Panier>) new CRUDPanier().recherchePanier(rechercherPanier.getText());
                       
                //  ran = (ArrayList<Salle>) new CRUDSalle().afficherSalle();
                return ran;
            }
        };
       task.setOnSucceeded((WorkerStateEvent e) -> {
           
            id1.setCellValueFactory(new PropertyValueFactory<>("id"));
            prix1.setCellValueFactory(new PropertyValueFactory<>("prix"));
            date1.setCellValueFactory(new PropertyValueFactory<>("date"));
            nom1.setCellValueFactory(new PropertyValueFactory<>("nom"));
            id_client1.setCellValueFactory(new PropertyValueFactory<>("id_client"));
            table.setItems(FXCollections.observableArrayList(task.getValue()));
        });
        task.setOnFailed(e -> {
            affichertable();
        });
        Thread th = new Thread(task);
        th.start();
    }
     private void affichertableTriePanier() {
        
       Task<ArrayList<Panier>> task = new Task() {

            @Override
            protected Object call() throws SQLException {
                
                ran = (ArrayList<Panier>) new CRUDPanier().afficherPanier();
                return ran;
            }
        };
        task.setOnSucceeded((WorkerStateEvent e) -> {
            idP.setCellValueFactory(new PropertyValueFactory<>("id"));
            prixP.setCellValueFactory(new PropertyValueFactory<>("prix"));
            dateP.setCellValueFactory(new PropertyValueFactory<>("date"));
            nomP.setCellValueFactory(new PropertyValueFactory<>("nom"));
            id_clientP.setCellValueFactory(new PropertyValueFactory<>("id_client"));
            tablePanier.setItems(FXCollections.observableArrayList(task.getValue()));
        });
        task.setOnFailed(e -> {
            affichertableTriePanier();
        });
        Thread th = new Thread(task);
        th.start();
        
        
        
        
    }
   
  private void TrierPanier(ActionEvent event){
    
    if(trierPanier.getSelectionModel().getSelectedItem().equals("prix"))
        {
            TrierPrixPanier();
            
            
       }else if (trierPanier.getSelectionModel().getSelectedItem().equals("date"))
        {
            TrierDatePanier();
        }
    
    }
  public void TrierPrixPanier()
    {
    Task<ArrayList<Panier>> task = new Task() {

            @Override
            protected Object call() throws SQLException {
                
                ran = (ArrayList<Panier>) new CRUDPanier().TrierPrixPanier();
                //  ran = (ArrayList<Salle>) new CRUDSalle().afficherSalle();
                return ran;
            }
        };
        task.setOnSucceeded((WorkerStateEvent e) -> {
            idP.setCellValueFactory(new PropertyValueFactory<>("id"));
            prixP.setCellValueFactory(new PropertyValueFactory<>("prix"));
            dateP.setCellValueFactory(new PropertyValueFactory<>("date"));
            nomP.setCellValueFactory(new PropertyValueFactory<>("nom"));
            id_clientP.setCellValueFactory(new PropertyValueFactory<>("id_client"));
            tablePanier.setItems(FXCollections.observableArrayList(task.getValue()));
        });
        task.setOnFailed(e -> {
            affichertableTriePanier();
        });
        Thread th = new Thread(task);
        th.start(); 
    }
    
    public void TrierDatePanier()
    {
    Task<ArrayList<Panier>> task = new Task() {

            @Override
            protected Object call() throws SQLException {
                
                ran = (ArrayList<Panier>) new CRUDPanier().TrierDatePanier();
                //  ran = (ArrayList<Salle>) new CRUDSalle().afficherSalle();
                return ran;
            }
        };
        task.setOnSucceeded((WorkerStateEvent e) -> {
            idP.setCellValueFactory(new PropertyValueFactory<>("id"));
            prixP.setCellValueFactory(new PropertyValueFactory<>("prix"));
            dateP.setCellValueFactory(new PropertyValueFactory<>("date"));
            nomP.setCellValueFactory(new PropertyValueFactory<>("nom"));
            id_clientP.setCellValueFactory(new PropertyValueFactory<>("id_client"));
            tablePanier.setItems(FXCollections.observableArrayList(task.getValue()));
        });
        task.setOnFailed(e -> {
            affichertableTriePanier();
        });
        Thread th = new Thread(task);
        th.start(); 
    }

    @FXML
    private void imprimerPanier(ActionEvent event) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
         printNode(tablePanier);
   
    }
       public static void printNode(final Node node) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
    Printer printer = Printer.getDefaultPrinter();
    PageLayout pageLayout
        = printer.createPageLayout(Paper.A4, PageOrientation.LANDSCAPE, Printer.MarginType.DEFAULT);
    PrinterAttributes attr = printer.getPrinterAttributes();
    PrinterJob job = PrinterJob.createPrinterJob();
    double scaleX
        = pageLayout.getPrintableWidth() / node.getBoundsInParent().getWidth();
    double scaleY
        = pageLayout.getPrintableHeight() / node.getBoundsInParent().getHeight();
    Scale scale = new Scale(scaleX, scaleY);
    node.getTransforms().add(scale);

    if (job != null && job.showPrintDialog(node.getScene().getWindow())) {
      boolean success = job.printPage(pageLayout, node);
      if (success) {
        job.endJob();

      }
    }
    node.getTransforms().remove(scale);
  }

    @FXML
    private void logbackpanier(ActionEvent event) {
        try {Stage stage = new Stage();
        Parent home = FXMLLoader.load(getClass().getResource("/edu/EcoSystem/gui/loginUser.fxml"));
        Scene hoomescene = new Scene(home);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(hoomescene);
        app_stage.show();
        } catch (IOException ex) {
            Logger.getLogger(edu.EcoSystem.controller.LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
    }

    
      
    
    
        
        
 }


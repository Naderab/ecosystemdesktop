/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.EcoSystem.controller;
import edu.EcoSystem.tools.MyDB;
import edu.EcoSystem.entities.Commande;
import edu.EcoSystem.services.CRUDCommande;
import com.jfoenix.controls.JFXButton;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterAttributes;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.transform.Scale;

/**
 *
 * @author MEGAJAS
 */
public class AjoutCommande_BackController implements Initializable {

    @FXML
    private Button ButtonCommande;
    private TextField nom;
    @FXML
    private TableColumn<?, ?> id_client;
    private DatePicker date;
    @FXML
    private Button ajouter;
    private TableView<?> table;
    private TableColumn<?, ?> prix1;
    private TableColumn<?, ?> date1;
    private TableColumn<?, ?> nom1;
    @FXML
    private TextField id_client1;
    private TextField prix_mod;
    private TextField nom_mod;
    @FXML
    private Button modifier;
    @FXML
    private Button supprimer;
    @FXML
    private Button panier;
    @FXML
    private TextField quantite;
    @FXML
    private TextField prixTotal;
    
    @FXML
    private DatePicker dateCommande;
    
    @FXML
    private TextField prixCommande;
    @FXML
    private TextField etat;
    
     public ArrayList<Commande> ran;
    @FXML
    private TextField prixTotal_mod;
    @FXML
    private TextField prixCom_mod;
    @FXML
    private TextField idcom_mod;
    @FXML
    private TextField etat_mod;
    @FXML
    private DatePicker date_mod;
    @FXML
    private TextField quantite_mod;
    @FXML
    private TableColumn<?, ?> id2;
    @FXML
    private TableColumn<?, ?> quantite2;
    @FXML
    private TableColumn<?, ?> prixTotal2;
    @FXML
    private TableColumn<?, ?> prixCommande2;
    @FXML
    private TableColumn<?, ?> id_client2;
    @FXML
    private TableColumn<?, ?> dateCommande2;
    @FXML
    private TableColumn<?, ?> etat2;
    @FXML
    private TableColumn<?, ?> idPanier2;
    @FXML
    private TableView<Commande> table2;
    @FXML
    private TableColumn<?, ?> id_panier;
    private TextField recherche;
    
    
    
    @FXML
    private ComboBox<String> trier;
    
    
    ObservableList<String> TriList=FXCollections.observableArrayList("quantite","prix_total","prix_commande","DateCommande");
    
    @FXML
    private TableView<Commande> tableTri;
    @FXML
    private TableColumn<?, ?> id3;
    @FXML
    private TableColumn<?, ?> quantite3;
    @FXML
    private TableColumn<?, ?> prixTotal3;
    @FXML
    private TableColumn<?, ?> prixCommande3;
    @FXML
    private TableColumn<?, ?> id_client3;
    @FXML
    private TableColumn<?, ?> dateCommande3;
    @FXML
    private TableColumn<?, ?> etat3;
    @FXML
    private TableColumn<?, ?> idPanier3;
    @FXML
    private TextField rechercher;
    //@FXML
    //private Button trierCommande;
    @FXML
    private Button archive;
    @FXML
    private TableView<Commande> tableArchive;
    @FXML
    private TableColumn<?, ?> idarchive;
    @FXML
    private TableColumn<?, ?> quantitearchive;
    @FXML
    private TableColumn<?, ?> prixTotalarchive;
    @FXML
    private TableColumn<?, ?> prixCommandearchive;
    @FXML
    private TableColumn<?, ?> id_clientarchive;
    @FXML
    private TableColumn<?, ?> dateCommandearchive;
    @FXML
    private TableColumn<?, ?> etatarchive;
    @FXML
    private TableColumn<?, ?> id_panierarchive;
    @FXML
    private Button stat;
    @FXML
    private BarChart<String, Integer> chart;

    @FXML
    private NumberAxis y;
    @FXML
    private CategoryAxis x;
    @FXML
    private Button excel;
    /*@FXML
    private AnchorPane pieChart;*/
    @FXML
    private AnchorPane pieChart;
    @FXML
    private Button imprimer;
    @FXML
    private TextField id_panier1;
    @FXML
    private TableView<Commande> tablee;
    @FXML
    private JFXButton logback;
    
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
         affichertable();
         affichertableTrie();
         affichertableArchive();
         affichertablepanier();
        try {                                                                                                                                                                                                                                                                                                                                                                                                                            
            notif();
        } catch (SQLException ex) {
            Logger.getLogger(AjoutCommande_BackController.class.getName()).log(Level.SEVERE, null, ex);
        }
           
           trier.getItems().addAll("quantite","prix_total","prix_commande","DateCommande");
           
           trier.valueProperty().addListener(new ChangeListener<String>() {
        @Override public void changed(ObservableValue ov, String t, String t1) {
                  //System.err.println("ok");
              if(trier.getValue().equals("quantite"))
                   { 
                      //System.err.println("ok1");
                       TriList.clear();
                       TrierQuantite();
                   }
               if(trier.getValue().equals("prix_total"))
                   {
                      
                       TriList.clear();
                       TrierPrixTotal();
                   }
               if(trier.getValue().equals("prix_commande"))
                   {
                      
                       TriList.clear();
                       TrierPrixCommande();
                   }
               if(trier.getValue().equals("DateCommande"))
                   { 
                      //System.err.println("ok1");
                       TriList.clear();
                       TrierDateCommande();
                   }
              
                   }});
           
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
    private void ShowOnClick(MouseEvent event) {
        
         
         table2.setOnMouseClicked(e -> {
            Commande c = (Commande) table2.getSelectionModel().getSelectedItem();
             try {

                 ran = (ArrayList<Commande>) new CRUDCommande().afficherCommande();
             } catch (SQLException ex) {
                 Logger.getLogger(AjoutCommande_BackController.class.getName()).log(Level.SEVERE, null, ex);
             }

             idcom_mod.setText(Integer.toString(c.getId()));
            quantite_mod.setText(Integer.toString(c.getQuantite()));
            prixTotal_mod.setText(Integer.toString(c.getPrix_total()));
            prixCom_mod.setText(Integer.toString(c.getPrix_commande()));
            date_mod.setValue(c.getDateCommande());
            etat_mod.setText(c.getEtat());
            
         
          

        });
        

        
    }

    @FXML
    private void modifier(ActionEvent event) throws SQLException {
        CRUDCommande cr = new CRUDCommande();

        
        Commande c = new Commande(
                Integer.parseInt(quantite_mod.getText()),
                Integer.parseInt(prixTotal_mod.getText()),
                Integer.parseInt(prixCom_mod.getText()),
                date_mod.getValue(),
                etat_mod.getText(),
                Integer.parseInt(idcom_mod.getText())
        );
        cr.modifierCommande(c);
        
        Notifications notif = Notifications.create()
                .title("Commande Modifie")
                .text("La Commande est bien modifiee !")
                .darkStyle().graphic(null).hideAfter(Duration.seconds(5))
                .position(Pos.TOP_LEFT)
                .onAction(new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent event) {
                        System.out.println("Clicked ont notif");
                    }
                });
        
        notif.showConfirm();
        refreshtable();
         clearModifBack();
    }
     public void clearModifBack()
     {
         
         idcom_mod.clear();
         prixTotal_mod.clear();
         prixCom_mod.clear();
         etat_mod.clear();
         date_mod.setValue(null);
         quantite_mod.clear();
     }
    
    

    @FXML
    private void supprimer(ActionEvent event) throws SQLException {
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Suppression");
        alert.setContentText("Voulez vous supprimer cette Commande?");

Optional<ButtonType> result = alert.showAndWait();

 
        
if (result.get() == ButtonType.OK){
         Commande c = (Commande) table2.getSelectionModel().getSelectedItem();
        int id = Integer.parseInt(idcom_mod.getText());
        CRUDCommande cr = new CRUDCommande();
        cr.supprimerCommande(id);
        
        refreshtable();
       
    } 
else{
   clearModifBack();
    
}     
}

    

    @FXML
    private void panier(ActionEvent event) {
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
    private void ajouterCommande(ActionEvent event) throws SQLException {
        
         CRUDCommande cr = new CRUDCommande();
if( quantite.getText().isEmpty() ||prixTotal.getText().isEmpty() || prixCommande.getText().isEmpty() ||
                id_client.getText().isEmpty()  || etat.getText().isEmpty()||id_panier.getText().isEmpty() )
        {
         Alert alert5 = new Alert(Alert.AlertType.ERROR);
            alert5.setTitle("Verifier la Commande ");
            alert5.setHeaderText(null);
            alert5.setContentText("Merci de bien vouloir remplir votre champ");
            alert5.showAndWait();
        }
        else if(!quantite.getText().matches("^([1-9][0-9]{0,4}|100000)$")){    
          Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Valider a quantite du Commande");
            alert1.setHeaderText(null);
            alert1.setContentText("Veuillez verifier la quantite entre 1 et 99999 ");
            alert1.showAndWait();
         }
        else if(!prixTotal.getText().matches("^([1-9][0-9]{0,4}|100000)$")){    
          Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Valider le Prix Total  du Commande");
            alert1.setHeaderText(null);
            alert1.setContentText("Veuillez verifier le prixTotal entre 1 et 99999  ");
            alert1.showAndWait();
         }
        else if(!prixCommande.getText().matches("^([1-9][0-9]{0,4}|100000)$")){    
          Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Valider le Prix du Commande");
            alert1.setHeaderText(null);
            alert1.setContentText("Veuillez verifier le prix  entre 1 et 99999 ");
            alert1.showAndWait();
         }
        else if(!etat.getText().matches("validée")&&!etat.getText().matches("encours")){    
          Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Valider l'etat du  Commande");
            alert1.setHeaderText(null);
            alert1.setContentText("Veuillez verifier l'etat soit validée ou encours ");
            alert1.showAndWait();
         }
        
        else if(LocalDate.now().isAfter(dateCommande.getValue()))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Date Commande est inférieure à la date actuelle!");
            alert.setContentText("Erreur");
            alert.showAndWait();
        }
        else
        {
      Commande c = new Commande(Integer.parseInt(quantite.getText()), 
               Integer.parseInt(prixTotal.getText()), 
                Integer.parseInt(prixCommande.getText()),
                Integer.parseInt(id_client1.getText()), 
                dateCommande.getValue(),
                etat.getText(),
                Integer.parseInt(id_panier1.getText()));
      
     
       cr.ajouterCommande(c);
       
        
        Notifications notif = Notifications.create()
                .title("Commande ajouté")
                .text("La Commande est bien ajoutée !")
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
        clearBack();
        
    }
    public void clearBack()
     {
         quantite.clear();
         prixTotal.clear();
         prixCommande.clear();
         etat.clear();
         dateCommande.setValue(null);
         id_client1.clear();
         id_panier1.clear();
     
     }
    
     
    private void affichertable() {
        
        Task<ArrayList<Commande>> task = new Task() {

            @Override
            protected Object call() throws SQLException {
                ran = (ArrayList<Commande>) new CRUDCommande().afficherCommande();
                return ran;
            }
        };

        task.setOnSucceeded((WorkerStateEvent e) -> {
            id2.setCellValueFactory(new PropertyValueFactory<>("id"));
            quantite2.setCellValueFactory(new PropertyValueFactory<>("quantite"));
            prixTotal2.setCellValueFactory(new PropertyValueFactory<>("prix_total"));
            prixCommande2.setCellValueFactory(new PropertyValueFactory<>("prix_commande"));
            id_client2.setCellValueFactory(new PropertyValueFactory<>("id_client"));
            dateCommande2.setCellValueFactory(new PropertyValueFactory<>("dateCommande"));
            etat2.setCellValueFactory(new PropertyValueFactory<>("etat"));
            idPanier2.setCellValueFactory(new PropertyValueFactory<>("id_panier"));
            table2.setItems(FXCollections.observableArrayList(task.getValue()));
        });
        task.setOnFailed(e -> {
            affichertable();
        });
        Thread th = new Thread(task);
        th.start();

        
        
        
        
    }
    public void refreshtable() throws SQLException{
        CRUDCommande   cr = new CRUDCommande();
        ran = cr.afficherCommande();
        ObservableList data = FXCollections.observableArrayList(ran);
        table2.setItems(data);
    }
     
    
    @FXML
    private void rechercher(KeyEvent event) {}
    

    @FXML
    private void recherche(KeyEvent event) {
          Task<ArrayList<Commande>> task = new Task() {

            @Override
            protected Object call() throws SQLException {
                ran = (ArrayList<Commande>) new CRUDCommande().rechercheCommande(rechercher.getText());
                       
                
                return ran;
            }
        };
       task.setOnSucceeded((WorkerStateEvent e) -> {
            id2.setCellValueFactory(new PropertyValueFactory<>("id"));
            quantite2.setCellValueFactory(new PropertyValueFactory<>("quantite"));
            prixTotal2.setCellValueFactory(new PropertyValueFactory<>("prix_total"));
            prixCommande2.setCellValueFactory(new PropertyValueFactory<>("prix_commande"));
            id_client2.setCellValueFactory(new PropertyValueFactory<>("id_client"));
            dateCommande2.setCellValueFactory(new PropertyValueFactory<>("dateCommande"));
            etat2.setCellValueFactory(new PropertyValueFactory<>("etat"));
            idPanier2.setCellValueFactory(new PropertyValueFactory<>("id_panier"));
            table2.setItems(FXCollections.observableArrayList(task.getValue()));
        });
        task.setOnFailed(e -> {
            affichertable();
        });
        Thread th = new Thread(task);
        th.start();
    }
    private void affichertableTrie() {
        
       Task<ArrayList<Commande>> task = new Task() {

            @Override
            protected Object call() throws SQLException {
                
                ran = (ArrayList<Commande>) new CRUDCommande().afficherCommande();
                return ran;
            }
        };
        task.setOnSucceeded((WorkerStateEvent e) -> {
            id3.setCellValueFactory(new PropertyValueFactory<>("id"));
            quantite3.setCellValueFactory(new PropertyValueFactory<>("quantite"));
            prixTotal3.setCellValueFactory(new PropertyValueFactory<>("prix_total"));
            prixCommande3.setCellValueFactory(new PropertyValueFactory<>("prix_commande"));
            id_client3.setCellValueFactory(new PropertyValueFactory<>("id_client"));
            dateCommande3.setCellValueFactory(new PropertyValueFactory<>("dateCommande"));
            etat3.setCellValueFactory(new PropertyValueFactory<>("etat"));
            idPanier3.setCellValueFactory(new PropertyValueFactory<>("id_panier"));
            tableTri.setItems(FXCollections.observableArrayList(task.getValue()));
        });
        task.setOnFailed(e -> {
            affichertableTrie();
        });
        Thread th = new Thread(task);
        th.start();
        
        
        
        
    }
   
    private void TrierCommande(ActionEvent event){
    
    if(trier.getSelectionModel().getSelectedItem().equals("quantite"))
        {
            TrierQuantite();
            
            
       }else if (trier.getSelectionModel().getSelectedItem().equals("prix_total"))
        {
            TrierPrixTotal();
        }else if(trier.getSelectionModel().getSelectedItem().equals("prix_commande"))
        {
            TrierPrixCommande();
        }else if(trier.getSelectionModel().getSelectedItem().equals("DateCommande"))
        {
            TrierDateCommande();
        }
    
    }
    
    public void TrierQuantite()
    {
    Task<ArrayList<Commande>> task = new Task() {

            @Override
            protected Object call() throws SQLException {
                
                ran = (ArrayList<Commande>) new CRUDCommande().TrierQuantite();
                return ran;
            }
        };
        task.setOnSucceeded((WorkerStateEvent e) -> {
            id3.setCellValueFactory(new PropertyValueFactory<>("id"));
            quantite3.setCellValueFactory(new PropertyValueFactory<>("quantite"));
            prixTotal3.setCellValueFactory(new PropertyValueFactory<>("prix_total"));
            prixCommande3.setCellValueFactory(new PropertyValueFactory<>("prix_commande"));
            id_client3.setCellValueFactory(new PropertyValueFactory<>("id_client"));
            dateCommande3.setCellValueFactory(new PropertyValueFactory<>("dateCommande"));
            etat3.setCellValueFactory(new PropertyValueFactory<>("etat"));
            idPanier3.setCellValueFactory(new PropertyValueFactory<>("id_panier"));
            tableTri.setItems(FXCollections.observableArrayList(task.getValue()));
        });
        task.setOnFailed(e -> {
            affichertableTrie();
        });
        Thread th = new Thread(task);
        th.start(); 
    }
     public void TrierPrixTotal()
    {
    Task<ArrayList<Commande>> task = new Task() {

            @Override
            protected Object call() throws SQLException {
                
                ran = (ArrayList<Commande>) new CRUDCommande().TrierPrixTotal();
                //  ran = (ArrayList<Salle>) new CRUDSalle().afficherSalle();
                return ran;
            }
        };
        task.setOnSucceeded((WorkerStateEvent e) -> {
            id3.setCellValueFactory(new PropertyValueFactory<>("id"));
            quantite3.setCellValueFactory(new PropertyValueFactory<>("quantite"));
            prixTotal3.setCellValueFactory(new PropertyValueFactory<>("prix_total"));
            prixCommande3.setCellValueFactory(new PropertyValueFactory<>("prix_commande"));
            id_client3.setCellValueFactory(new PropertyValueFactory<>("id_client"));
            dateCommande3.setCellValueFactory(new PropertyValueFactory<>("dateCommande"));
            etat3.setCellValueFactory(new PropertyValueFactory<>("etat"));
            idPanier3.setCellValueFactory(new PropertyValueFactory<>("id_panier"));
            tableTri.setItems(FXCollections.observableArrayList(task.getValue()));
        });
        task.setOnFailed(e -> {
            affichertableTrie();
        });
        Thread th = new Thread(task);
        th.start(); 
    }
    public void TrierPrixCommande()
    {
    Task<ArrayList<Commande>> task = new Task() {

            @Override
            protected Object call() throws SQLException {
                
                ran = (ArrayList<Commande>) new CRUDCommande().TrierPrixCommande();
                //  ran = (ArrayList<Salle>) new CRUDSalle().afficherSalle();
                return ran;
            }
        };
        task.setOnSucceeded((WorkerStateEvent e) -> {
            id3.setCellValueFactory(new PropertyValueFactory<>("id"));
            quantite3.setCellValueFactory(new PropertyValueFactory<>("quantite"));
            prixTotal3.setCellValueFactory(new PropertyValueFactory<>("prix_total"));
            prixCommande3.setCellValueFactory(new PropertyValueFactory<>("prix_commande"));
            id_client3.setCellValueFactory(new PropertyValueFactory<>("id_client"));
            dateCommande3.setCellValueFactory(new PropertyValueFactory<>("dateCommande"));
            etat3.setCellValueFactory(new PropertyValueFactory<>("etat"));
            idPanier3.setCellValueFactory(new PropertyValueFactory<>("id_panier"));
            tableTri.setItems(FXCollections.observableArrayList(task.getValue()));
        });
        task.setOnFailed(e -> {
            affichertableTrie();
        });
        Thread th = new Thread(task);
        th.start(); 
    }
    
    public void TrierDateCommande()
    {
    Task<ArrayList<Commande>> task = new Task() {

            @Override
            protected Object call() throws SQLException {
                
                ran = (ArrayList<Commande>) new CRUDCommande().TrierDateCommande();
                //  ran = (ArrayList<Salle>) new CRUDSalle().afficherSalle();
                return ran;
            }
        };
        task.setOnSucceeded((WorkerStateEvent e) -> {
            id3.setCellValueFactory(new PropertyValueFactory<>("id"));
            quantite3.setCellValueFactory(new PropertyValueFactory<>("quantite"));
            prixTotal3.setCellValueFactory(new PropertyValueFactory<>("prix_total"));
            prixCommande3.setCellValueFactory(new PropertyValueFactory<>("prix_commande"));
            id_client3.setCellValueFactory(new PropertyValueFactory<>("id_client"));
            dateCommande3.setCellValueFactory(new PropertyValueFactory<>("dateCommande"));
            etat3.setCellValueFactory(new PropertyValueFactory<>("etat"));
            idPanier3.setCellValueFactory(new PropertyValueFactory<>("id_panier"));
            tableTri.setItems(FXCollections.observableArrayList(task.getValue()));
        });
        task.setOnFailed(e -> {
            affichertableTrie();
        });
        Thread th = new Thread(task);
        th.start(); 
    }
     public void notif() throws SQLException
        {
            
        
        CRUDCommande cc = new CRUDCommande();
        
        if(cc.Notif()!=-1)
        {
        Notifications notificationBuilder=Notifications.create()
                .title("Notification")
                .text("vous avez "+cc.Notif()+" commande aujourd'hui Veuillez consulter la liste des commandes")
                .graphic(null)
                .hideAfter(Duration.seconds(5))
                .position(Pos.TOP_CENTER);
        
        notificationBuilder.darkStyle();
        notificationBuilder.showWarning();
        
        }else{
        
        Notifications notificationBuilder1=Notifications.create()
                .title("Notification")
                .text("vous n'avez aucun evenement aujourd'hui")
                .graphic(null)
                .hideAfter(Duration.seconds(5))
                .position(Pos.TOP_CENTER);
        
        notificationBuilder1.darkStyle();
        notificationBuilder1.showWarning();
        }
        }

    
    @FXML
    private void Archiver(ActionEvent event) throws SQLException {
        
        Commande e = (Commande) tableArchive.getSelectionModel().getSelectedItem();
                     int id = e.getId();
                     
                     CRUDCommande es = new CRUDCommande();
                     
                     
                     
                     Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
            alert2.setTitle("Confirmation Dialog");
            alert2.setHeaderText("Archive");
            alert2.setContentText("etes vous sur de vouloir archiver ? ");
            Optional <ButtonType> result = alert2.showAndWait(); 

        if(result.get()==ButtonType.OK)
         {
            es.Archiver(e,id);
                     affichertableArchive(); 
         } 
        
        
        
    }
    
     private void affichertableArchive() {
        
        Task<ArrayList<Commande>> task = new Task() {

            @Override
            protected Object call() throws SQLException {
                ran = (ArrayList<Commande>) new CRUDCommande().afficherCommandeArchive();
                return ran;
            }
        };

        task.setOnSucceeded((WorkerStateEvent e) -> {
            idarchive.setCellValueFactory(new PropertyValueFactory<>("id"));
            quantitearchive.setCellValueFactory(new PropertyValueFactory<>("quantite"));
            prixTotalarchive.setCellValueFactory(new PropertyValueFactory<>("prix_total"));
            prixCommandearchive.setCellValueFactory(new PropertyValueFactory<>("prix_commande"));
            id_clientarchive.setCellValueFactory(new PropertyValueFactory<>("id_client"));
            dateCommandearchive.setCellValueFactory(new PropertyValueFactory<>("dateCommande"));
            etatarchive.setCellValueFactory(new PropertyValueFactory<>("etat"));
            id_panierarchive.setCellValueFactory(new PropertyValueFactory<>("id_panier"));
            tableArchive.setItems(FXCollections.observableArrayList(task.getValue()));
        });
        task.setOnFailed(e -> {
            affichertable();
        });
        Thread th = new Thread(task);
        th.start();
        
    }

    @FXML
    private void stat(ActionEvent event) throws SQLException {
       
        com.mysql.jdbc.Connection cnx = MyDB.getInstance().getConnexion();
        String requete;
       
        requete = "SELECT etat ,count(*) as number FROM commande where etat ='validée' OR etat='encours' GROUP BY etat";  
        XYChart.Series<String,Integer> series = new XYChart.Series<>();
       try {
            PreparedStatement ps = cnx.prepareStatement(requete);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next())
            {  
                series.getData().add(new XYChart.Data<>(resultat.getString(1),resultat.getInt(2)));
            }
            chart.getData().add(series);
            }
        catch (SQLException ex) {
            System.out.println("erreur  " + ex.getMessage());
        }
    }

    @FXML
    private void exportExcel(ActionEvent event) throws FileNotFoundException, IOException {
        Workbook workbook = (Workbook) new HSSFWorkbook();
            Sheet spreadsheet = workbook.createSheet("sample");
            org.apache.poi.ss.usermodel.Row row = spreadsheet.createRow(0);
            for (int j = 0; j < tableArchive.getColumns().size(); j++) {
                row.createCell(j).setCellValue(tableArchive.getColumns().get(j).getText());
            }
            for (int i = 0; i < tableArchive.getItems().size(); i++) {
                row = spreadsheet.createRow(i + 1);
                for (int j = 0; j <tableArchive.getColumns().size(); j++) {
                    if(tableArchive.getColumns().get(j).getCellData(i) != null) {
                        row.createCell(j).setCellValue(tableArchive.getColumns().get(j).getCellData(i).toString());
                    }
                    else {
                        row.createCell(j).setCellValue("");
                    }
                }
            }
            FileOutputStream fileOut = new FileOutputStream("TableCommande.xls");
            workbook.write(fileOut);
            fileOut.close();
            System.out.println("Data is wrtten Successfully");
            }

    @FXML
    private void imprimer(ActionEvent event) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
         printNode(tableTri);
   
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

       
       public void affichertablepanier(){
           
               Task<ArrayList<Commande>> task = new Task() {

            @Override
            protected Object call() throws SQLException {
                ran = (ArrayList<Commande>) new CRUDCommande().afficherCommande();
                return ran;
            }
        };
        task.setOnSucceeded((WorkerStateEvent e) -> {
            id_client.setCellValueFactory(new PropertyValueFactory<>("id_client"));
            id_panier.setCellValueFactory(new PropertyValueFactory<>("id_panier"));
            tablee.setItems(FXCollections.observableArrayList(task.getValue()));
        });
        task.setOnFailed(e -> {
            affichertablepanier();
        });
        Thread th = new Thread(task);
        th.start();
        
           
           
           
           
       }
    @FXML
    private void showOnClickPanier(MouseEvent event) {
        
        
      tablee.setOnMouseClicked(e -> {
            Commande s = (Commande) tablee.getSelectionModel().getSelectedItem();
             try {
                 ran = (ArrayList<Commande>) new CRUDCommande().afficherCommande();
             } catch (SQLException ex) {
                 Logger.getLogger(AjoutPanier_BackController.class.getName()).log(Level.SEVERE, null, ex);
             }

            id_client1.setText(Integer.toString(s.getId_client()));
            id_panier1.setText(Integer.toString(s.getId_panier()));
          
          

        });
        
        
    }

    @FXML
    private void logback(ActionEvent event) {
        
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
        
        
        
   


    
    

    
    
    
    
    
    
    
   
    
    
    

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.EcoSystem.gui;

import com.calendarfx.view.CalendarView;
import com.jfoenix.controls.JFXButton;
import edu.EcoSystem.tests.MainClass;
import edu.EcoSystem.tools.Calendar;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Nader
 */
public class TestController implements Initializable {

    @FXML
    private AnchorPane menuBar;
    @FXML
    private AnchorPane loginRegisterProfie;
    @FXML
    private HBox profileHB;
    @FXML
    private Circle photoProfilMenu;
    @FXML
    private HBox menuHB;
    @FXML
    private VBox profileVB;
    @FXML
    private JFXButton profilMenu;
    @FXML
    private JFXButton settingsProfile;
    @FXML
    private Label nameUserMenu;
    @FXML
    private Label labelLastLoginMenu;
    @FXML
    private Label LastLoginMenu;
    @FXML
    private JFXButton logoutMenu;
    @FXML
    private JFXButton admin;
    @FXML
    private AnchorPane barreRecherche;
    @FXML
    private JFXButton eventButton1;
    @FXML
    private JFXButton eventButton12;
    @FXML
    private VBox menuVB;
    @FXML
    private VBox accueilVB;
    @FXML
    private VBox catVB;
    @FXML
    private JFXButton panierB;
    @FXML
    private JFXButton eventButton;
    @FXML
    private VBox eventVB;
    @FXML
    private VBox blogVB;
    @FXML
    private JFXButton blogButton;
    @FXML
    private JFXButton ajoutArticleB;
    @FXML
    private JFXButton listArticleB;
    @FXML
    private VBox dealVB;
    @FXML
    private JFXButton dealButton;
    @FXML
    private JFXButton catButton;
    @FXML
    private JFXButton produitB;
    @FXML
    private JFXButton eventButton11;
    @FXML
    private JFXButton AjoutEvent;
    @FXML
    private JFXButton mesevenements;
    @FXML
    private JFXButton Planning;
    @FXML
    private JFXButton listDevisB;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Panier(MouseEvent event) {
    }

    @FXML
    private void AddArticle(MouseEvent event)  throws IOException{
         FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/edu/EcoSystem/gui/AjouterOffre.fxml"));
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
    private void ListArticles(MouseEvent event)  throws IOException{
           FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/edu/EcoSystem/gui/Listoffrefront.fxml"));
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
    private void ListDevis(MouseEvent event) throws IOException {
           FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/edu/EcoSystem/gui/Listdevisfront.fxml"));
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
    private void showProduit(MouseEvent event) {
    }

    @FXML
    private void Ajout(MouseEvent event) throws IOException {
        boolean okClicked = ecosystem.EcoSystem.showEventAddDialog1();
    }

    @FXML
    private void AfficheEvents(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/edu/EcoSystem/gui/ListEvenementFront.fxml"));
         panierB.getScene().setRoot(root);
    }

    

    @FXML
    private void ToMesEvenements(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/edu/EcoSystem/gui/MesEvenements.fxml"));
         panierB.getScene().setRoot(root);
    }

    @FXML
    private void ToPlanning(MouseEvent event) {
        Calendar cal = new Calendar();
        CalendarView calendar = cal.displayCalendar();
        StackPane sp = new StackPane(calendar);
        
        Scene scene = new  Scene(sp);
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void logout(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/edu/EcoSystem/gui/loginUser.fxml"));
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
    private void ToMenu(MouseEvent event) throws IOException {
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

   
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.GestionProduit.GUI;

import edu.GestionProduit.entities.categorie;
import edu.GestionProduit.entities.produit2;
import edu.GestionProduit.service.CrudCategorie;
import edu.GestionProduit.service.CrudProduit;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Control;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Abir
 */
public class ProfileController implements Initializable {

    @FXML
    private Button tfajouterannonce1;
    @FXML
    private CheckBox chTelephones;
    @FXML
    private CheckBox chElectrom;
    @FXML
    private CheckBox chmaison;
    @FXML
    private CheckBox chElectro;
    @FXML
    private Button testadmin;
    @FXML
    private Button filtrer;
    @FXML
    private TextField Rechercher;
    @FXML
    private ScrollPane pane;
    
    ObservableList<produit2> data=FXCollections.observableArrayList();;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        TilePane b = new TilePane();
        b.setPadding(new javafx.geometry.Insets(30));
        TilePane c = new TilePane();
        FadeTransition ft = new FadeTransition(Duration.millis(1500));
        CrudProduit eventcrud = new CrudProduit();
        try {
            data = eventcrud.getAllProduit2();
        } catch (SQLException ex) {
            Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (produit2 d : data) {

            try {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/GestionProduit/GUI/DivProduit.fxml"));
                Parent root = (Pane) loader.load();
                DivProduitController DHC = loader.getController();
                DHC.LoadValues(d);              
                c.getChildren().removeAll();
                c.getChildren().add(root);
                c.setMaxWidth(Control.USE_COMPUTED_SIZE);
                c.setMaxHeight(Control.USE_COMPUTED_SIZE);
                c.setMinHeight(Control.USE_COMPUTED_SIZE);
                c.setMinWidth(Control.USE_COMPUTED_SIZE);
            } catch (IOException ex) {
                Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        c.setPrefColumns(3);
        c.setPadding(new javafx.geometry.Insets(0));
        c.setHgap(10);
        c.setVgap(80);
        b.getChildren().add(c);
        b.setPrefWidth(1000);
        pane.setContent(b);

    }
    
    @FXML
    private void cliqueajouteranno1(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/edu/GestionProduit/GUI/UpdateProduit.fxml"));
        Parent updateProduitParent = loader.load();
        UpdateProduitController updateProduitController = loader.getController();
        updateProduitController.setAction("create");

        Scene updateProduitScene = new Scene(updateProduitParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(updateProduitScene);
        window.show();
    }

    @FXML
    private void checkCategorie(ActionEvent event) throws SQLException {

        ArrayList<String> list = new ArrayList<>();
         ArrayList<String> cat = new ArrayList<>();
        CrudCategorie crudcategorie = new CrudCategorie();
        if (chTelephones.isSelected()) {
            list.add("Téléphones et Tablettes");
        }
        if (chElectrom.isSelected()) {
            list.add("Electromenager");
        }
        if (chmaison.isSelected()) {
            list.add("Maison et Bureau");
        }
        if (chElectro.isSelected()) {
            list.add("Electronique");
        }

/*
        ArrayList<produit2> liste = CrudPRoduit.Filtercategorie(list,cat);

        vbox.getChildren().remove(pagination);
        int var = CrudCategorie.FilterLessonTheme(list, cat).size();
        pagination = new Pagination(var / itemsPerPage(), 0);
        pagination.setStyle("-fx-border-color:blue;");

        pagination.setPageFactory(new Callback<Integer, Node>() {

            public Node call(Integer pageIndex) {
                int val = 0;
                try {
                    val = CrudCategorie.Filtercategorie(list, cat).size();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }

                if (val > 0) {
                    return createPagebytheme(pageIndex, liste);
                } else {
                    return null;
                }

            }
        });

        AnchorPane.setTopAnchor(pagination, 10.0);
        AnchorPane.setRightAnchor(pagination, 10.0);
        AnchorPane.setBottomAnchor(pagination, 10.0);
        AnchorPane.setLeftAnchor(pagination, 10.0);
        vbox.getChildren().addAll(pagination);

    
    }
         
    */
    }
    @FXML
    private void testadmin(ActionEvent event) throws IOException {
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

    private void detailproduit(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/edu/GestionProduit/GUI/DetailProduit.fxml"));
        Parent profileParent = loader.load();

        Scene gestionProfilScene = new Scene(profileParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(gestionProfilScene);
        window.show();
    }

}

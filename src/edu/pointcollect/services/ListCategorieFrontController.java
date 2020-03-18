/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pointcollect.services;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
//import edu.EcoSystem.gui.ListEvenementFront2Controller;
import edu.pointcollect.entities.CategorieTrie;
import edu.pointcollect.entities.SousCategorieTrie;
import edu.pointcollect.services.CategorieTrieCrud;
import edu.pointcollect.services.SousCategorieTrieCrud;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.util.Duration;
import netscape.javascript.JSObject;

/**
 * FXML Controller class
 *
 * @author khouloud
 */
public class ListCategorieFrontController implements Initializable {

    private ObservableList<CategorieTrie> data;
    private ObservableList<SousCategorieTrie> data1;

    @FXML
    private AnchorPane ap;
    @FXML
    private ScrollPane paneCategorie;
    @FXML
    private Label price;
    @FXML
    private JFXDrawer menu;
    @FXML
    private ScrollPane paneSousCategorie;
    @FXML
    private JFXButton geolocation;
    @FXML
    private JFXTextField searchSousCat;
    @FXML
    private Button idSearch;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        TilePane b = new TilePane();
        b.setPadding(new javafx.geometry.Insets(30));
        TilePane c = new TilePane();
        FadeTransition ft = new FadeTransition(Duration.millis(1500));
        CategorieTrieCrud eventcrud = new CategorieTrieCrud();
        data = eventcrud.listeCategorie();
        for (CategorieTrie d : data) {

            try {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/pointcollect/gui/DivCategorie.fxml"));
                Parent root = (Pane) loader.load();
                DivCategorieController DHC = loader.getController();
                Rectangle image = DHC.LoadValues(d);

                image.setOnMouseClicked((MouseEvent event) -> {
                    if (event.getClickCount() == 1) {
                        try {

                            click(event, d);
                            System.out.println(d.getId());
                        } catch (SQLException ex) {
                            Logger.getLogger(DivCategorieController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });

                //   c.setVgap(40);
                c.getChildren().removeAll();

                c.getChildren().add(root);
                c.setMaxWidth(Control.USE_COMPUTED_SIZE);
                c.setMaxHeight(Control.USE_COMPUTED_SIZE);
                c.setMinHeight(Control.USE_COMPUTED_SIZE);
                c.setMinWidth(Control.USE_COMPUTED_SIZE);
            } catch (IOException ex) {
                Logger.getLogger(ListCategorieFrontController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        c.setPrefColumns(4);
        c.setPadding(new javafx.geometry.Insets(0));
        c.setHgap(10);
        c.setVgap(80);
        b.getChildren().add(c);
        b.setPrefWidth(200);
        paneCategorie.setContent(b);
    }

    public void click(MouseEvent event, CategorieTrie selectedetab) throws SQLException {
        if (event.getClickCount() == 1) {
            System.err.println("Click");
            System.out.println(selectedetab.getId());
            TilePane b = new TilePane();
            b.setPadding(new javafx.geometry.Insets(30));
            TilePane c = new TilePane();
            FadeTransition ft = new FadeTransition(Duration.millis(1500));
            SousCategorieTrieCrud eventcrud = new SousCategorieTrieCrud();
            data1 = eventcrud.listerCategorie(selectedetab.getId());
            for (SousCategorieTrie d : data1) {

                try {
                    System.out.println(d.getName());
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/pointcollect/gui/DivCategorie.fxml"));
                    Parent root = (Pane) loader.load();
                    DivCategorieController DHC = loader.getController();
                    DHC.LoadValues1(d);

                    //   c.setVgap(40);
                    c.getChildren().removeAll();

                    c.getChildren().add(root);
                    c.setMaxWidth(Control.USE_COMPUTED_SIZE);
                    c.setMaxHeight(Control.USE_COMPUTED_SIZE);
                    c.setMinHeight(Control.USE_COMPUTED_SIZE);
                    c.setMinWidth(Control.USE_COMPUTED_SIZE);
                } catch (IOException ex) {
                    Logger.getLogger(ListCategorieFrontController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            c.setPrefColumns(4);
            c.setPadding(new javafx.geometry.Insets(0));
            c.setHgap(10);
            c.setVgap(80);
            b.getChildren().add(c);
            b.setPrefWidth(200);
            paneSousCategorie.setContent(b);
        }
    }

    @FXML
    private void maps(final ActionEvent event) throws IOException {
        final AddressPointCollectCrud aa = new AddressPointCollectCrud();
        try {
            aa.list();
            System.err.println("list database markers "+aa);
        } catch (IOException ex) {
            Logger.getLogger(CrudAddressController.class.getName()).log(Level.SEVERE, null, ex);
        }

        WebView browser = new WebView();

// Get WebEngine via WebView
        WebEngine webEngine = browser.getEngine();

// Load page
        String url = ListCategorieFrontController.class.getResource("/edu/pointcollect/gui/Maps.html").toExternalForm();

        webEngine.setJavaScriptEnabled(true);
        webEngine.load(url);

// Load page
//webEngine.load("html.html");
        webEngine.getLoadWorker().stateProperty().addListener(
                new ChangeListener<Worker.State>() {
            @Override
            public void changed(ObservableValue<? extends Worker.State> observable, Worker.State oldValue, Worker.State newValue) {
                System.out.println("oldValue: " + oldValue);
                System.out.println("newValue: " + newValue);

                if (newValue != Worker.State.SUCCEEDED) {
                    return;
                }
                System.out.println("Succeeded!");
                JSObject window = (JSObject) webEngine.executeScript("window");
                System.out.println("hello: " + window);
            }
        }
        );

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // create scene
        stage.setTitle("Web Map");
        Scene scene = new Scene(browser, 1200, 1000, Color.web("#666970"));
        scene.getStylesheets().add(getClass().getResource("/edu/pointcollect/gui/fxml.css").toExternalForm());
        stage.setScene(scene);
        // show stage
        stage.show();
    }

    static { // use system proxy settings when standalone application
        System.setProperty("java.net.useSystemProxies", "true");
    }


    /*void ShowSousCategorie(int id) {
        TilePane b = new TilePane();
        b.setPadding(new javafx.geometry.Insets(30));
        TilePane c = new TilePane();
        FadeTransition ft = new FadeTransition(Duration.millis(1500));
        SousCategorieTrieCrud eventcrud = new SousCategorieTrieCrud();
        data1 = eventcrud.listerCategorie(id);
        for (SousCategorieTrie d : data1) {

            try {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/pointcollect/gui/DivCategorie.fxml"));
                Parent root = (Pane) loader.load();
                DivCategorieController DHC = loader.getController();
                DHC.LoadValues1(d);
                

                //   c.setVgap(40);
                c.getChildren().removeAll();

                c.getChildren().add(root);
                c.setMaxWidth(Control.USE_COMPUTED_SIZE);
                c.setMaxHeight(Control.USE_COMPUTED_SIZE);
                c.setMinHeight(Control.USE_COMPUTED_SIZE);
                c.setMinWidth(Control.USE_COMPUTED_SIZE);
            } catch (IOException ex) {
                Logger.getLogger(ListCategorieFrontController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        c.setPrefColumns(4);
        c.setPadding(new javafx.geometry.Insets(0));
        c.setHgap(10);
        c.setVgap(80);
        b.getChildren().add(c);
        b.setPrefWidth(1000);
        paneSousCategorie.setContent(b);
    }   */

    @FXML
    private void search(ActionEvent event) {
    }
}

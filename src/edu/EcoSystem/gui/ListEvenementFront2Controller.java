/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.EcoSystem.gui;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import edu.EcoSystem.entities.Evenement;
import edu.EcoSystem.services.EventCRUD;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Nader
 */
public class ListEvenementFront2Controller implements Initializable {

    @FXML
    private AnchorPane ap;
    @FXML
    private JFXTextField recherchetext;
    @FXML
    private ScrollPane pane;
    @FXML
    private Label price;
    @FXML
    private JFXHamburger affmenu;
    @FXML
    private JFXDrawer menu;
    private ObservableList<Evenement> data;
    @FXML
    private ComboBox<String> combo;
    @FXML
    private ComboBox<String> combo1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ObservableList<String> availableChoices = FXCollections.observableArrayList("Prix Croissant", "Prix Décroissant", "Les Plus Visionnés");
        combo.setItems(availableChoices);

        ChangeListener<String> changeListener = new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.equals("Prix Croissant")) {
                    prixCroissant();
                } else if (newValue.equals("Prix Décroissant")) {
                    prixDecroissant();
                } else if (newValue.equals("Les Plus Visionnés")) {
                    lesPlusVisionnes();
                }
            }

        };
        // Selected Item Changed.
        combo.getSelectionModel().selectedItemProperty().addListener(changeListener);

        ObservableList<String> availableChoices1 = FXCollections.observableArrayList("Gratuit", "Payant");
        combo1.setItems(availableChoices1);

        ChangeListener<String> changeListener1 = new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.equals("Gratuit")) {
                    EventCat("gratuit");
                } else if (newValue.equals("Payant")) {
                    EventCat("payant");
                }
            }

        };
        // Selected Item Changed.
        combo1.getSelectionModel().selectedItemProperty().addListener(changeListener1);
        try {
            AnchorPane anchrone = FXMLLoader.load(getClass().getResource("/edu/EcoSystem/gui/test.fxml"));
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
            Logger.getLogger(ListEvenementFront2Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

        TilePane b = new TilePane();
        b.setPadding(new javafx.geometry.Insets(30));
        TilePane c = new TilePane();
        FadeTransition ft = new FadeTransition(Duration.millis(1500));
        EventCRUD eventcrud = new EventCRUD();
        data = eventcrud.displayEventPublie();
        for (Evenement d : data) {

            try {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/EcoSystem/gui/DivEvent.fxml"));
                Parent root = (Pane) loader.load();
                DivEventController DHC = loader.getController();
                DHC.LoadValues(d);

                //   c.setVgap(40);
                c.getChildren().removeAll();

                c.getChildren().add(root);
                c.setMaxWidth(Control.USE_COMPUTED_SIZE);
                c.setMaxHeight(Control.USE_COMPUTED_SIZE);
                c.setMinHeight(Control.USE_COMPUTED_SIZE);
                c.setMinWidth(Control.USE_COMPUTED_SIZE);
            } catch (IOException ex) {
                Logger.getLogger(ListEvenementFront2Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        c.setPrefColumns(2);
        c.setPadding(new javafx.geometry.Insets(0));
        c.setHgap(10);
        c.setVgap(80);
        b.getChildren().add(c);
        b.setPrefWidth(1000);
        pane.setContent(b);

    }

    @FXML
    private void RechercheDynamique(KeyEvent event) throws SQLException {
        EventCRUD ps = new EventCRUD();
        data = ps.displayEventPublie();
        FilteredList<Evenement> filteredData = new FilteredList<>(data, e -> true);
        recherchetext.setOnKeyReleased(e
                -> {
            recherchetext.textProperty().addListener((ObservableValue<? extends String> ObservableValue, String oldValue, String newValue) -> {
                filteredData.setPredicate((Predicate<? super Evenement>) new Predicate<Evenement>() {

                    @Override
                    public boolean test(Evenement t) {
                        if (newValue == null || newValue.isEmpty()) {

                            return true;

                        }
                        String lowerCaseFilter = newValue.toLowerCase();
                        return t.getNom().toLowerCase().contains(lowerCaseFilter.subSequence(0, lowerCaseFilter.length()));
                    }

                });
            });
            TilePane b = new TilePane();
            b.setPadding(new javafx.geometry.Insets(30));
            TilePane c = new TilePane();

            for (Evenement d : filteredData) {

                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/EcoSystem/gui/DivEvent.fxml"));
                    Parent root = (Pane) loader.load();
                    DivEventController DHC = loader.getController();
                    DHC.LoadValues(d);

                    //   c.setVgap(40);
                    c.getChildren().removeAll();

                    c.getChildren().add(root);
                } catch (IOException ex) {
                    Logger.getLogger(ListEvenementFront2Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            c.setPrefColumns(2);
            c.setPadding(new javafx.geometry.Insets(0));
            c.setHgap(10);
            c.setVgap(80);
            b.getChildren().add(c);
            b.setPrefWidth(1000);
            pane.setContent(b);
        });
    }

    private void prixCroissant() {
        EventCRUD ps = new EventCRUD();
        data = ps.displayEventOrderByPrixCroissant();
        TilePane b = new TilePane();
        b.setPadding(new javafx.geometry.Insets(30));
        TilePane c = new TilePane();

        for (Evenement d : data) {
            System.out.println(d.getNom());

            try {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/EcoSystem/gui/DivEvent.fxml"));
                Parent root = (Pane) loader.load();
                DivEventController DHC = loader.getController();

                DHC.LoadValues(d);

                c.getChildren().removeAll();

                c.getChildren().add(root);
            } catch (IOException ex) {
                Logger.getLogger(ListEvenementFront2Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        c.setPrefColumns(2);
        c.setPadding(new javafx.geometry.Insets(0));
        c.setHgap(10);
        c.setVgap(80);
        b.getChildren().add(c);
        b.setPrefWidth(1000);
        pane.setContent(b);
    }

    private void EventCat(String cat) {
        EventCRUD ps = new EventCRUD();
        data = ps.displayEventByCategorie(cat);
        TilePane b = new TilePane();
        b.setPadding(new javafx.geometry.Insets(30));
        TilePane c = new TilePane();

        for (Evenement d : data) {
            System.out.println(d.getNom());

            try {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/EcoSystem/gui/DivEvent.fxml"));
                Parent root = (Pane) loader.load();
                DivEventController DHC = loader.getController();

                DHC.LoadValues(d);

                c.getChildren().removeAll();

                c.getChildren().add(root);
            } catch (IOException ex) {
                Logger.getLogger(ListEvenementFront2Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        c.setPrefColumns(2);
        c.setPadding(new javafx.geometry.Insets(0));
        c.setHgap(10);
        c.setVgap(80);
        b.getChildren().add(c);
        b.setPrefWidth(1000);
        pane.setContent(b);
    }

    private void prixDecroissant() {
        EventCRUD ps = new EventCRUD();
        data = ps.displayEventOrderByPrixdécroissant();
        TilePane b = new TilePane();
        b.setPadding(new javafx.geometry.Insets(30));
        TilePane c = new TilePane();

        for (Evenement d : data) {
            System.out.println(d.getNom());

            try {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/EcoSystem/gui/DivEvent.fxml"));
                Parent root = (Pane) loader.load();
                DivEventController DHC = loader.getController();

                DHC.LoadValues(d);

                //   c.setVgap(40);
                c.getChildren().removeAll();

                c.getChildren().add(root);
            } catch (IOException ex) {
                Logger.getLogger(ListEvenementFront2Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        c.setPrefColumns(2);
        c.setPadding(new javafx.geometry.Insets(0));
        c.setHgap(10);
        c.setVgap(80);
        b.getChildren().add(c);
        b.setPrefWidth(1000);
        pane.setContent(b);
    }

    private void lesPlusVisionnes() {
        EventCRUD ps = new EventCRUD();
        data = ps.displayEventOrderByNombreVu();
        TilePane b = new TilePane();
        b.setPadding(new javafx.geometry.Insets(30));
        TilePane c = new TilePane();

        for (Evenement d : data) {
            System.out.println(d.getNom());

            try {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/EcoSystem/gui/DivEvent.fxml"));
                Parent root = (Pane) loader.load();
                DivEventController DHC = loader.getController();

                DHC.LoadValues(d);

                //   c.setVgap(40);
                c.getChildren().removeAll();

                c.getChildren().add(root);
            } catch (IOException ex) {
                Logger.getLogger(ListEvenementFront2Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        c.setPrefColumns(2);
        c.setPadding(new javafx.geometry.Insets(0));
        c.setHgap(10);
        c.setVgap(80);
        b.getChildren().add(c);
        b.setPrefWidth(1000);
        pane.setContent(b);
    }

    public static String getMonthForInt(int month) {
        String[] monthNames = {"JAN", "FEB", "MAR", "APR", "MAY", "JUNE", "JULY", "AUG", "SEP", "OCR", "NOV", "DEC"};
        return monthNames[month];
    }
}

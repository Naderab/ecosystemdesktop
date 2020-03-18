/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.EcoSystem.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import edu.EcoSystem.entities.CurrentUser;
import edu.EcoSystem.entities.Evenement;
import edu.EcoSystem.entities.Wishlist;
import edu.EcoSystem.services.EventCRUD;
import edu.EcoSystem.services.WishlistCRUD;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
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
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author Nader
 */
public class MyWishlistController implements Initializable {

    @FXML
    private TableView<Evenement> table;
    @FXML
    private TableColumn<Evenement, String> titre;
    @FXML
    private TableColumn<Evenement, String> adresse;
    @FXML
    private TableColumn<Evenement, Date> dateDebut;
    @FXML
    private TableColumn<Evenement, Date> dateFin;
    @FXML
    private TableColumn<Evenement, Void> Action;
    WishlistCRUD wishlistcrud;

    ObservableList<Evenement> observableList = FXCollections.observableArrayList();
    ;
    ObservableList<Wishlist> observableWishlist;
    EventCRUD eventcrud;
    Evenement newEvent;
    @FXML
    private TableColumn<Evenement, Void> Action2;
    @FXML
    private AnchorPane ap;
    @FXML
    private JFXTextField recherchetext;
    @FXML
    private AnchorPane pane;
    @FXML
    private JFXHamburger affmenu;
    @FXML
    private JFXDrawer menu;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        table.setEditable(true);
        eventcrud = new EventCRUD();
        wishlistcrud = new WishlistCRUD();
        observableWishlist = wishlistcrud.displayWishlist(CurrentUser.id);
        //observableList=eventcrud.displayALLEvent();
        for (Wishlist w : observableWishlist) {
            newEvent = eventcrud.displayEventByID(w.getIdEvent());
            observableList.add(newEvent);
        }
        table.setItems(observableList);

        titre.setCellValueFactory(new PropertyValueFactory<>("nom"));
        adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        dateDebut.setCellValueFactory(new PropertyValueFactory<>("dateDebut"));
        dateFin.setCellValueFactory(new PropertyValueFactory<>("dateFin"));

        Callback<TableColumn<Evenement, Void>, TableCell<Evenement, Void>> cellFactory = new Callback<TableColumn<Evenement, Void>, TableCell<Evenement, Void>>() {
            @Override
            public TableCell<Evenement, Void> call(final TableColumn<Evenement, Void> param) {
                final TableCell<Evenement, Void> cell = new TableCell<Evenement, Void>() {

                    private final JFXButton btn = new JFXButton("Détail");

                    {

                        btn.setOnAction((ActionEvent event) -> {
                            Evenement data = getTableView().getItems().get(getIndex());
                            System.out.println("selectedData: " + data);
                            try {
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/EcoSystem/gui/DetailEventFront.fxml"));
                                Parent root = loader.load();
                                DetailEventFrontController DEF = loader.getController();
                                DEF.ShowEvent(data.getId_event());
                                btn.getScene().setRoot(root);

                            } catch (IOException ex) {
                                Logger.getLogger(MyWishlistController.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (SQLException ex) {
                                Logger.getLogger(MyWishlistController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            btn.setStyle("-fx-background-color: #3c8dbc");

                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        Action.setCellFactory(cellFactory);

        Callback<TableColumn<Evenement, Void>, TableCell<Evenement, Void>> cellFactory2 = new Callback<TableColumn<Evenement, Void>, TableCell<Evenement, Void>>() {
            @Override
            public TableCell<Evenement, Void> call(final TableColumn<Evenement, Void> param) {
                final TableCell<Evenement, Void> cell = new TableCell<Evenement, Void>() {

                    private final JFXButton btn1 = new JFXButton("Supprimer");

                    {
                        btn1.setOnAction((ActionEvent event) -> {
                            Evenement data = getTableView().getItems().get(getIndex());
                            System.out.println("selectedData: " + data);
                            try {
                                deleteEvent(data.getId_event(), data);
                            } catch (SQLException ex) {
                                Logger.getLogger(MesEvenementsController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        });

                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            btn1.setStyle("-fx-background-color: #f43838");

                            setGraphic(btn1);
                        }
                    }
                };
                return cell;
            }
        };

        Action2.setCellFactory(cellFactory2);
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
    }

    void deleteEvent(int id, Evenement eventt) throws SQLException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

        alert.setTitle("Notification");
        alert.setContentText("Etes vous sure de vouloir supprimer ?");
        Optional<ButtonType> result = alert.showAndWait();
        ButtonType button = result.orElse(ButtonType.CANCEL);

        if (button == ButtonType.OK) {
            wishlistcrud.deleteEventFromWishlist(id, CurrentUser.id);
            table.getItems().remove(eventt);
            TrayNotification tray = new TrayNotification("Information", "Evènement Supprimé", NotificationType.SUCCESS);
            tray.setAnimationType(AnimationType.POPUP);
            tray.showAndDismiss(Duration.seconds(3));

        }

    }

    @FXML
    private void RechercheDynamique(KeyEvent event) {
    }

}

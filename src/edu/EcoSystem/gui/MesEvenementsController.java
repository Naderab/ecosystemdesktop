/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.EcoSystem.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import edu.EcoSystem.entities.CurrentUser;
import edu.EcoSystem.entities.Evenement;
import edu.EcoSystem.entities.Participation;
import edu.EcoSystem.services.EventCRUD;
import edu.EcoSystem.services.ParticipationCRUD;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import static javafx.collections.FXCollections.observableList;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
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
public class MesEvenementsController implements Initializable {

    @FXML
    private TableView<Evenement> table;
    @FXML
    private TableColumn<?, ?> titre;
    @FXML
    private TableColumn<?, ?> adresse;
    @FXML
    private TableColumn<Evenement, Date> dateDebut;
    @FXML
    private TableColumn<Evenement, Date> dateFin;
    @FXML
    private TableColumn<Evenement, Integer> NbVu;
    @FXML
    private TableColumn<Evenement, Integer> NbParticipants;
    @FXML
    private TableColumn<Evenement, Void> Action;
    ObservableList<Evenement> observableList;
    EventCRUD eventcrud;
    @FXML
    private AnchorPane ap;
    @FXML
    private AnchorPane pane;
    @FXML
    private JFXHamburger affmenu;
    @FXML
    private JFXDrawer menu;
    @FXML
    private Label towishlist;
    @FXML
    private JFXTextField recherchetext;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        table.setEditable(true);
        eventcrud = new EventCRUD();
        observableList = eventcrud.displayMyEvent(CurrentUser.id);
        table.setItems(observableList);

        titre.setCellValueFactory(new PropertyValueFactory<>("nom"));
        adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        dateDebut.setCellValueFactory(new PropertyValueFactory<>("dateDebut"));
        dateFin.setCellValueFactory(new PropertyValueFactory<>("dateFin"));
        NbVu.setCellValueFactory(new PropertyValueFactory<>("nombreVu"));
        NbParticipants.setCellValueFactory(new PropertyValueFactory<>("nombreTickets"));

        Callback<TableColumn<Evenement, Void>, TableCell<Evenement, Void>> cellFactory = new Callback<TableColumn<Evenement, Void>, TableCell<Evenement, Void>>() {
            @Override
            public TableCell<Evenement, Void> call(final TableColumn<Evenement, Void> param) {
                final TableCell<Evenement, Void> cell = new TableCell<Evenement, Void>() {

                    private final JFXButton btn = new JFXButton("Supprimer");

                    {
                        btn.setOnAction((ActionEvent event) -> {
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
                             btn.setStyle("-fx-background-color: #f43838");

                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        Action.setCellFactory(cellFactory);

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

        recherchetext.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!recherchetext.getText().isEmpty()) {

                try {
                    ObservableList data = FXCollections.observableArrayList(new EventCRUD().chercherMyEvent(recherchetext.getText(), 13));
                    table.setItems(data);
                } catch (SQLException ex) {
                    Logger.getLogger(MesEvenementsController.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {

                ObservableList data = FXCollections.observableArrayList(new EventCRUD().displayEventPublie());
                table.setItems(data);

            }
        });
    }

    void deleteEvent(int id, Evenement eventt) throws SQLException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

        alert.setTitle("Notification");
        alert.setContentText("Etes vous sure de vouloir supprimer ?");
        Optional<ButtonType> result = alert.showAndWait();
        ButtonType button = result.orElse(ButtonType.CANCEL);

        if (button == ButtonType.OK) {
            eventcrud.deleteEvent(id);
            table.getItems().remove(eventt);
            TrayNotification tray = new TrayNotification("Information", "Evènement Supprimé", NotificationType.SUCCESS);
            tray.setAnimationType(AnimationType.POPUP);
            tray.showAndDismiss(Duration.seconds(3));

        }

    }

    @FXML
    private void ToWishlist(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/edu/EcoSystem/gui/MyWishlist.fxml"));
        towishlist.getScene().setRoot(root);
    }

    @FXML
    private void RechercheDynamique(KeyEvent event) {
    }

}

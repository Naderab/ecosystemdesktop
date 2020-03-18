/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.EcoSystem.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import edu.EcoSystem.entities.Evenement;
import edu.EcoSystem.entities.Participation;
import edu.EcoSystem.services.EventCRUD;
import edu.EcoSystem.services.ParticipationCRUD;
import edu.EcoSystem.tests.MainClass;
import edu.EcoSystem.tools.SendingMail;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.Period;
import java.time.ZoneId;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
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
 * @author Nader
 */
public class ListEventBackController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TableView<Evenement> table;

    @FXML
    private TableColumn<Evenement, String> nom;

    @FXML
    private TableColumn<Evenement, String> adresse;

    @FXML
    private TableColumn<Evenement, Date> dateDebut;

    @FXML
    private TableColumn<Evenement, String> categorie;

    @FXML
    private TableView<Participation> tableParticipation;

    @FXML
    private TableColumn<Participation, String> IDparticipation;

    @FXML
    private TableColumn<Participation, String> nomEvent;

    @FXML
    private TableColumn<Participation, String> nomUser;

    @FXML
    private TableColumn<Participation, String> nbplace;

    @FXML
    private Button Ajout;

    @FXML
    private Button delete;

    @FXML
    private JFXTextField recherche;

    @FXML
    private TableColumn<Evenement, String> nom1;
    @FXML
    private TableColumn<Evenement, String> adresse1;
    @FXML
    private TableColumn<Evenement, Date> dateDebut1;
    @FXML
    private TableColumn<Evenement, String> categorie1;
    @FXML
    private TableColumn<Evenement, Void> action;
    @FXML
    private JFXButton deletee;
    @FXML
    private TableView<Evenement> tablee;

    ObservableList observableList = FXCollections.observableArrayList();
    ObservableList observableListNonTraites = FXCollections.observableArrayList();
    ObservableList<Participation> observableList2 = FXCollections.observableArrayList();
    EventCRUD eventcrud;
    ParticipationCRUD participationcrud;
    @FXML
    private TableColumn<?, ?> DateFin1;
    @FXML
    private TableColumn<?, ?> Prix1;
    @FXML
    private TableColumn<?, ?> description;
    @FXML
    private TableColumn<?, ?> DateFin;
    @FXML
    private TableColumn<?, ?> Prix;
    @FXML
    private JFXTextField recherche1;
    java.sql.Date sqldateD;
    java.util.Date datecourante;
    @FXML
    private Button reclamation_btn;
    @FXML
    private JFXButton parametreAdmin;
    @FXML
    private Button gestionPanier;
    @FXML
    private Button gestionCommande;
    @FXML
    private Button gestionEvenement;

    /*@FXML
    void UpdateEvent(ActionEvent event) {

        Evenement selectedEvent = table.getSelectionModel().getSelectedItem();
        if (selectedEvent != null) {
            boolean okClicked = MainClass.showEventEditDialog(selectedEvent);
            if (okClicked) {
            }

        } else {
            // Nothing selected.
            new Alert(Alert.AlertType.ERROR, "Aucun Evenement selectionné").show();

        }

    }*/

    @FXML
    void deleteEvent(ActionEvent event) throws SQLException {

        Evenement selectedIndex = table.getSelectionModel().getSelectedItem();
        int selected = table.getSelectionModel().getSelectedIndex();
        if (selected >= 0) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

            alert.setTitle("Notification");
            alert.setContentText("Etes vous sure de vouloir supprimer ?");
            Optional<ButtonType> result = alert.showAndWait();
            ButtonType button = result.orElse(ButtonType.CANCEL);

            if (button == ButtonType.OK) {
                EventCRUD eventcrud = new EventCRUD();
                eventcrud.deleteEvent(selectedIndex.getId_event());
                table.getItems().remove(selected);
                TrayNotification tray = new TrayNotification("Information", "Evènement Supprimé", NotificationType.SUCCESS);
                tray.setAnimationType(AnimationType.POPUP);
                tray.showAndDismiss(Duration.seconds(3));

            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Aucun Evenement  selectionné").show();

        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        table.setEditable(true);
        eventcrud = new EventCRUD();
        participationcrud = new ParticipationCRUD();
        observableList = eventcrud.displayEventPublie();
        observableListNonTraites = eventcrud.displayEventNonTraités();
        observableList2 = participationcrud.displayParticipation();
        datecourante = new java.util.Date();

        for (Participation p : observableList2) {
            java.sql.Date sqldatecourante = new java.sql.Date(datecourante.getTime());
            sqldateD = new java.sql.Date(p.getDateDebut().getTime());
            int periode = (int) (sqldateD.getTime() - sqldatecourante.getTime()) / (1000 * 60 * 60 * 24);
            System.out.println(periode + "  ");
            System.out.println(p.getEmail());
            System.out.println(p.getNomEvent());
            System.out.println(p.getNomUser());

              if (periode <= 3) {

                String contenu = "Bienvenue  " + p.getNomUser() + "  il reste 3 jours à L'événement   "+ p.getNomEvent() +"   dans lequel vous etes participer";
                SendingMail sm = new SendingMail(contenu, p.getEmail(), "Rappel");
                SendingMail.envoyer();

            }
        }
        table.setItems(observableList);
        tableParticipation.setItems(observableList2);
        tablee.setItems(observableListNonTraites);

        IDparticipation.setCellValueFactory(new PropertyValueFactory<>("id_participation"));
        nomEvent.setCellValueFactory(new PropertyValueFactory<>("nomEvent"));
        nomUser.setCellValueFactory(new PropertyValueFactory<>("nomUser"));
        nbplace.setCellValueFactory(new PropertyValueFactory<>("nbPlace"));

        nom1.setCellValueFactory(new PropertyValueFactory<>("nom"));
        adresse1.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        dateDebut1.setCellValueFactory(new PropertyValueFactory<>("dateDebut"));
        categorie1.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        Prix1.setCellValueFactory(new PropertyValueFactory<>("prix"));
        categorie1.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        DateFin1.setCellValueFactory(new PropertyValueFactory<>("dateFin"));

        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        dateDebut.setCellValueFactory(new PropertyValueFactory<>("dateDebut"));
        categorie.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        Prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        DateFin.setCellValueFactory(new PropertyValueFactory<>("dateFin"));

        Callback<TableColumn<Evenement, Void>, TableCell<Evenement, Void>> cellFactory = new Callback<TableColumn<Evenement, Void>, TableCell<Evenement, Void>>() {
            @Override
            public TableCell<Evenement, Void> call(final TableColumn<Evenement, Void> param) {
                final TableCell<Evenement, Void> cell = new TableCell<Evenement, Void>() {

                    private final JFXButton btn = new JFXButton("Accepter");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Evenement data = getTableView().getItems().get(getIndex());
                            System.out.println("selectedData: " + data);
                            try {
                                eventcrud.traiterEvent(data);

                            } catch (SQLException ex) {
                                Logger.getLogger(ListEventBackController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            TrayNotification tray = new TrayNotification("Traitement", "Evènement Traité", NotificationType.SUCCESS);
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
                            btn.setStyle("-fx-background-color: #3c8dbc");
                            //  btn.setStye("");
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        action.setCellFactory(cellFactory);

        recherche1.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!recherche1.getText().isEmpty()) {

                try {
                    ObservableList data = FXCollections.observableArrayList(new EventCRUD().chercher(recherche1.getText()));
                    table.setItems(data);
                } catch (SQLException ex) {
                    Logger.getLogger(ListEventBackController.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {

                ObservableList data = FXCollections.observableArrayList(new EventCRUD().displayEventPublie());
                table.setItems(data);

            }
        });

        recherche.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!recherche.getText().isEmpty()) {

                try {
                    ObservableList data = FXCollections.observableArrayList(new EventCRUD().chercherNonTraités(recherche.getText()));
                    tablee.setItems(data);
                } catch (SQLException ex) {
                    Logger.getLogger(ListEventBackController.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {

                ObservableList data = FXCollections.observableArrayList(new EventCRUD().displayEventNonTraités());
                tablee.setItems(data);

            }
        });

        table.setRowFactory(tv -> {
            TableRow<Evenement> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Evenement selectedEvent = table.getSelectionModel().getSelectedItem();
                    if (selectedEvent != null) {
                        boolean okClicked = ecosystem.EcoSystem.showEventEditDialog(selectedEvent);
                        if (okClicked) {
                        }

                    } else {
                        // Nothing selected.
                        new Alert(Alert.AlertType.ERROR, "Aucun Evenement selectionné").show();

                    }
                    //MyType rowData = row.getItem();
                    //System.out.println(rowData);
                }
            });
            return row;
        });

        Ajout.setStyle("-fx-background-color: #0aaf57");
        delete.setStyle("-fx-background-color: #f43838");
        deletee.setStyle("-fx-background-color: #f43838");

    }

    @FXML
    public void Refrechtable(javafx.scene.input.MouseEvent event) {
        observableList.clear();
        observableList = eventcrud.displayEventPublie();
        table.setItems(observableList);

    }

    @FXML
    void ToAddPage(ActionEvent event) throws IOException {

        boolean okClicked = ecosystem.EcoSystem.showEventAddDialog();

    }

    @FXML
    private void deleteEventt(ActionEvent event) throws SQLException {
        Evenement selectedIndex = tablee.getSelectionModel().getSelectedItem();
        int selected = tablee.getSelectionModel().getSelectedIndex();
        if (selected >= 0) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

            alert.setTitle("Notification");
            alert.setContentText("Etes vous sure de vouloir supprimer ?");
            Optional<ButtonType> result = alert.showAndWait();
            ButtonType button = result.orElse(ButtonType.CANCEL);

            if (button == ButtonType.OK) {
                EventCRUD eventcrud = new EventCRUD();
                eventcrud.deleteEvent(selectedIndex.getId_event());
                tablee.getItems().remove(selected);
                TrayNotification tray = new TrayNotification("Information", "Evènement Supprimé", NotificationType.SUCCESS);
                tray.setAnimationType(AnimationType.POPUP);
                tray.showAndDismiss(Duration.seconds(3));

            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Aucun Evenement  selectionné").show();

        }
    }

    @FXML
    private void Refrechtablee(javafx.scene.input.MouseEvent event) {
        observableListNonTraites.clear();
        observableListNonTraites = eventcrud.displayEventNonTraités();
        tablee.setItems(observableListNonTraites);
    }

    @FXML
    private void G_reclamation(ActionEvent event) {
    }

    @FXML
    private void modifierAdmin(ActionEvent event) {
    }

    @FXML
    private void gestionPanier(ActionEvent event) throws IOException {
         FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/edu/EcoSystem/gui/AjoutPanier_Back.fxml"));
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

    @FXML
    private void gestionCommande(ActionEvent event) throws IOException {
         FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/edu/EcoSystem/gui/AjoutCommande_Back.fxml"));
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

    
            
    @FXML
    private void Gotofront(ActionEvent event) throws IOException {
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

    @FXML
    private void gestionEvenement(ActionEvent event) throws IOException {
        
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/edu/EcoSystem/gui/ListEventBack.fxml"));
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
    private void gestionOffre(ActionEvent event) throws IOException {
         FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/edu/EcoSystem/gui/ListBack.fxml"));
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
    

}

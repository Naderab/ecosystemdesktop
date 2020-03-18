/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.EcoSystem.gui;

import edu.EcoSystem.entities.Evenement;
import edu.EcoSystem.entities.Review;
import static edu.EcoSystem.gui.ListEvenementFront2Controller.getMonthForInt;
import edu.EcoSystem.services.EventCRUD;
import edu.EcoSystem.services.GReviews;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Nader
 */
public class DivEventController implements Initializable {

    private Label id;
    private Rectangle rectangle;
    private Pane sq;
    @FXML
    private Label titre;
    @FXML
    private Label prix;
    @FXML
    private Label dayDeb;
    @FXML
    private Label monthDeb;
    @FXML
    private Label yearDeb;
    @FXML
    private Label dayFin;
    @FXML
    private Label monthFin;
    @FXML
    private Label yearFin;
    @FXML
    private Rectangle image;
    @FXML
    private Label labelprix;
    @FXML
    private Label labelprix1;
    @FXML
    private Label adr;
    @FXML
    private Label NbVue;
    @FXML
    private Label NbRating;
    @FXML
    private Label Complet;
    @FXML
    private Label NbComment;
    @FXML
    private Label User;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void LoadValues(Evenement e) throws IOException {
        try {
            titre.setWrapText(true);
            adr.setWrapText(true);
            titre.setText(e.getNom());
            prix.setText(String.valueOf(e.getPrix()));
            
            if (e.getCategorie().equals("gratuit")) {
                prix.setText("Gratuit");
                labelprix.setText("");
            } else {
                prix.setText(Double.toString(e.getPrix()) + "Dt");
            }
            String datedeb = e.getDateDebut().toString();
            dayDeb.setText(datedeb.substring(8, 10));
            monthDeb.setText(getMonthForInt(e.getDateDebut().getMonth()));
            yearDeb.setText(datedeb.substring(0, 4));
            String datefin = e.getDateFin().toString();
            dayFin.setText(datefin.substring(8, 10));
            monthFin.setText(getMonthForInt(e.getDateFin().getMonth()));
            yearFin.setText(datefin.substring(0, 4));
            adr.setText(e.getAdresse()+"\n");
            NbVue.setText(String.valueOf(e.getNombreVu()));
            NbRating.setText(String.valueOf(e.getRating()/5));
            
            if(e.getMaxParticipants()-e.getNombreTickets() == 0)
            {
                Complet.setText("Evénement Complet");
                Complet.setStyle("-fx-text-fill: #ab4d4d;");
            }
            
            GReviews reviews = new GReviews();
            ObservableList<Review> myList = FXCollections.observableArrayList();
            
                int nbc = reviews.CountReviewEvent(e);
                NbComment.setText(String.valueOf(nbc));
            
            
            EventCRUD ec=new EventCRUD();
            System.out.println(ec.GetUserName(e.getId_user()));
            System.out.println(e.getId_user());
            User.setText(ec.GetUserName(e.getId_user()));
            
            
            
            
            
            Image imageURI2 = new Image("file:C:\\wamp64\\www\\EcoSystem\\web\\uploads\\images/" + e.getImage());
            
            image.setFill(new ImagePattern(imageURI2));
            
            image.setOnMouseClicked((MouseEvent event) -> {
                if (event.getClickCount() == 2) {
                    try {
                        doubleclick(event, e);
                        //System.out.println(e.getId_event());
                    } catch (SQLException ex) {
                        Logger.getLogger(DivEventController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            
        } catch (SQLException ex) {
            Logger.getLogger(DivEventController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void doubleclick(MouseEvent event, Evenement selectedetab) throws SQLException {
        if (event.getClickCount() == 2) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/EcoSystem/gui/DetailEventFront.fxml"));
                Parent root = loader.load();
                DetailEventFrontController DEF = loader.getController();
                DEF.ShowEvent(selectedetab.getId_event());
                image.getScene().setRoot(root);

            } catch (IOException ex) {
                Logger.getLogger(DivEventController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}

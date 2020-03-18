/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.EcoSystem.gui;

import com.itextpdf.text.DocumentException;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import edu.EcoSystem.entities.CurrentUser;
import edu.EcoSystem.entities.Review;
import edu.EcoSystem.entities.Evenement;
import edu.EcoSystem.entities.Participation;
import static edu.EcoSystem.gui.ListEvenementFront2Controller.getMonthForInt;
import edu.EcoSystem.services.EventCRUD;
import edu.EcoSystem.services.GReviews;
import edu.EcoSystem.services.ParticipationCRUD;
import edu.EcoSystem.services.WishlistCRUD;
import edu.EcoSystem.tools.PDF;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.util.Duration;
import org.controlsfx.control.Rating;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author Nader
 */
public class DetailEventFrontController implements Initializable {

    @FXML
    private Rectangle image;
    @FXML
    private Label titre;
    @FXML
    private Label adresse;
    @FXML
    private Label description;
    @FXML
    private Button Participer;
    @FXML
    private Button like;
    @FXML
    private TextField NbTickets;
    @FXML
    private Label nbvue;
    @FXML
    private Button liked;
    Evenement newEvent;
    @FXML
    private Label prix;
    @FXML
    private Label Nbplace;
    @FXML
    private AnchorPane parti;
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
    private ScrollPane comments;
    @FXML
    private JFXTextArea comment;
    @FXML
    private JFXButton AddReview;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void ShowEvent(int id) throws SQLException {

        adresse.setWrapText(true);
        description.setWrapText(true);
        titre.setWrapText(true);

        EventCRUD es = new EventCRUD();
        newEvent = new Evenement();

        newEvent = es.displayALLEvent().filtered(e -> e.getId_event() == id).get(0);
        //int nbrating = (newEvent.getRating() / 5);
        Rating rating = new Rating(5);
        rating.setLayoutX(1165.0);
        rating.setLayoutY(355.0);
        parti.getChildren().add(rating);
        rating.ratingProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                try {
                    es.incrementRating(newEvent, newValue.intValue());
                    TrayNotification tray = new TrayNotification("Information", "Vous avez voter " + newValue + " Etoiles à lévenement" + newEvent.getNom(), NotificationType.SUCCESS);
                    tray.setAnimationType(AnimationType.POPUP);
                    tray.showAndDismiss(Duration.seconds(3));
                } catch (SQLException ex) {
                    Logger.getLogger(DetailEventFrontController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });

        es.incrementNombreVu(newEvent);
        int nbdispo = newEvent.getMaxParticipants() - newEvent.getNombreTickets();
        like.setOnMouseClicked((event) -> {

            like.setVisible(false);
            liked.setVisible(true);

            WishlistCRUD wishlist = new WishlistCRUD();

            // lr.setIduser(worldfriendship.Views.FirstFrame.user);
            try {
                wishlist.addWishlist(newEvent, CurrentUser.id);//CurrentUser here 
                TrayNotification tray = new TrayNotification("Information", "Evènement Ajouté à votre Wishlist", NotificationType.SUCCESS);
                tray.setAnimationType(AnimationType.POPUP);
                tray.showAndDismiss(Duration.seconds(3));

            } catch (IOException ex) {
                Logger.getLogger(DetailEventFrontController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
        liked.setOnMouseClicked((event) -> {
            WishlistCRUD wishlist = new WishlistCRUD();
            try {
                wishlist.deleteEventFromWishlist(newEvent.getId_event(), 1);//CurentUser Here 
                like.setVisible(true);
                liked.setVisible(false);
                TrayNotification tray = new TrayNotification("Information", "Evènement Supprimer de votre Wishlist", NotificationType.SUCCESS);
                tray.setAnimationType(AnimationType.POPUP);
                tray.showAndDismiss(Duration.seconds(3));
            } catch (SQLException ex) {
                Logger.getLogger(DetailEventFrontController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

        ParticipationCRUD pcrud = new ParticipationCRUD();
        System.out.println("jjjjjj " + pcrud.getParticip(id, CurrentUser.id));//Current User Here 
        if (pcrud.getParticip(id, CurrentUser.id) || nbdispo == 0) { //Current User Here 
            Participer.setVisible(false);
            NbTickets.setVisible(false);
        } else {
            Participer.setVisible(true);
            NbTickets.setVisible(true);
        }

        titre.setText(newEvent.getNom() + "\n");
        description.setText(newEvent.getDescription() + "\n");
        adresse.setText(newEvent.getAdresse() + "\n");
        if (newEvent.getCategorie().equals("payant")) {
            prix.setText(String.valueOf(newEvent.getPrix()) + "DT");
        } else {
            prix.setText("Gratuit");
        }

        String datedeb = newEvent.getDateDebut().toString();
        dayDeb.setText(datedeb.substring(8, 10));
        monthDeb.setText(getMonthForInt(newEvent.getDateDebut().getMonth()));
        yearDeb.setText(datedeb.substring(0, 4));
        String datefin = newEvent.getDateFin().toString();
        dayFin.setText(datefin.substring(8, 10));
        monthFin.setText(getMonthForInt(newEvent.getDateFin().getMonth()));
        yearFin.setText(datefin.substring(0, 4));
        nbvue.setText(String.valueOf(newEvent.getNombreVu()));

        if (nbdispo == 0) {
            Nbplace.setText("Evénement Complet");
        } else {
            Nbplace.setText(String.valueOf(nbdispo));
        }
        Image imageURI2 = new Image("file:C:\\wamp64\\www\\EcoSystem\\web\\uploads\\images/" + newEvent.getImage());
        image.setFill(new ImagePattern(imageURI2));
        Reviewslist(newEvent);

        Participer.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                int placedispo = newEvent.getMaxParticipants() - newEvent.getNombreTickets();
                // Event e=new Event();
                if (placedispo == 0) {
                    System.out.println("full");
                } else {
                    try {
                        Participation p = new Participation();
                        p.setDateDebut(newEvent.getDateDebut());
                        p.setDateFin(newEvent.getDateFin());
                        p.setDateDebut(newEvent.getDateDebut());
                        p.setNomEvent(newEvent.getNom());
                        p.setNbPlace(Integer.parseInt(NbTickets.getText()));

                        es.incrementNombreTickets(newEvent, Integer.parseInt(NbTickets.getText()));
                        pcrud.addParticipation(newEvent, Integer.parseInt(NbTickets.getText()), CurrentUser.id);//Current User Here

                        PDF pdf = new PDF();
                        pdf.pdf(p);

                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/EcoSystem/gui/DetailEventFront.fxml"));
                        Parent root = loader.load();
                        DetailEventFrontController pc = loader.getController();
                        pc.ShowEvent(newEvent.getId_event());
                        parti.getScene().setRoot(root);

                    } catch (SQLException | IOException ex) {
                        Logger.getLogger(DetailEventFrontController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (DocumentException ex) {
                        Logger.getLogger(DetailEventFrontController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }
        }
        );

        //test like
        try {
            WishlistCRUD wishlist = new WishlistCRUD();
            boolean b;
            b = wishlist.testlike(CurrentUser.id, id);//Current User Here 
            System.out.println(b);
            if (b == true) {
                like.setVisible(false);
                liked.setVisible(true);
            } else {
                liked.setVisible(false);
                like.setVisible(true);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DetailEventFrontController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void participation(MouseEvent event) {
    }

    public void Reviewslist(Evenement e) throws SQLException {
        TilePane b = new TilePane();

        b.setPadding(new javafx.geometry.Insets(30));
        TilePane c = new TilePane();

        GReviews gr = new GReviews();
        ObservableList<Review> listreview = gr.ListReviews(e);

        for (Review d : listreview) {

            try {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/EcoSystem/gui/DivReview.fxml"));
                Parent root = (Pane) loader.load();
                DivReviewController DHC = loader.getController();
                DHC.LoadValues(d, e);
                c.getChildren().removeAll();
                c.getChildren().add(root);
            } catch (IOException ex) {
                Logger.getLogger(DetailEventFrontController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        c.setPrefColumns(1);
        c.setPadding(new javafx.geometry.Insets(0));
        c.setHgap(25);
        c.setVgap(50);
        b.getChildren().add(c);
        b.setPrefWidth(1000);
        comments.setContent(b);

    }

    @FXML
    private void addReview(ActionEvent event) throws SQLException {
        GReviews greviews = new GReviews();
        Review r = new Review();
        r.setCmt(comment.getText());
        r.setId_event(newEvent);
        java.util.Date date_util = new java.util.Date();
        java.sql.Date date_sql = new java.sql.Date(date_util.getTime());
        r.setDate(date_sql);
        greviews.addReview(r);
        Reviewslist(newEvent);

    }

    @FXML
    private void Retour(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/EcoSystem/gui/ListEvenementFront.fxml"));
        Parent root = loader.load();
        AddReview.getScene().setRoot(root);
    }

}

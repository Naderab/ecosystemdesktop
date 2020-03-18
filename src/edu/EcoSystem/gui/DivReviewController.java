/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.EcoSystem.gui;

import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import edu.EcoSystem.entities.CurrentUser;
import edu.EcoSystem.entities.Likereview;
import edu.EcoSystem.entities.Review;
import edu.EcoSystem.entities.Evenement;
import edu.EcoSystem.services.GReviews;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

/**
 * FXML Controller class
 *
 * @author Nader
 */
public class DivReviewController implements Initializable {

    @FXML
    private Label rname;
    @FXML
    private Pane sq;
    @FXML
    private JFXButton rjaime;
    @FXML
    private JFXButton rliekd;
    @FXML
    private Label nbrlikes;
    @FXML
    private Circle rcircle;
    @FXML
    private Label rcom;
    @FXML
    private Label id;
    @FXML
    private FontAwesomeIconView trash;
    @FXML
    private Label daterev;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void LoadValues(Review r, Evenement e) throws SQLException {

        DetailEventFrontController pr = new DetailEventFrontController();
        rcom.setText(r.getCmt());

        GReviews gr = new GReviews();
        nbrlikes.setText(String.valueOf(gr.CountReview(r)));
        id.setText(String.valueOf(r.getIdCmt()));
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        String strDate = dateFormat.format(r.getDate());
        daterev.setText(strDate);

        trash.setOnMouseClicked((event) -> {
            try {
                GReviews de = new GReviews();
                de.DeleteReveiw(r.getIdCmt());

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/EcoSystem/gui/DetailEventFront.fxml"));
                Parent root = loader.load();
                DetailEventFrontController pu = loader.getController();
                pu.ShowEvent(e.getId_event());
                pu.Reviewslist(e);
                rname.getScene().setRoot(root);

            } catch (SQLException ex) {
                Logger.getLogger(DivReviewController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(DivReviewController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
        rjaime.setOnMouseClicked((event) -> {

            rjaime.setVisible(false);
            rliekd.setVisible(true);

            Likereview lr = new Likereview();
            lr.setIdCmt(r);
            lr.setIduser(CurrentUser.id);//CurentUser Here
            try {
                gr.AddLike(lr);
            } catch (SQLException ex) {
                Logger.getLogger(DivReviewController.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                nbrlikes.setText(String.valueOf(gr.CountReview(r)));
            } catch (SQLException ex) {
                Logger.getLogger(DivReviewController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        rliekd.setOnMouseClicked((event) -> {

            try {
                gr.DeleteLike(CurrentUser.id, r.getIdCmt());//CurentUser Here
                rjaime.setVisible(true);
                rliekd.setVisible(false);
            } catch (SQLException ex) {
                Logger.getLogger(DivReviewController.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                nbrlikes.setText(String.valueOf(gr.CountReview(r)));
            } catch (SQLException ex) {
                Logger.getLogger(DetailEventFrontController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

    }

}

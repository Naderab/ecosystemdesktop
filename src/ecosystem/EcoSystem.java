/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecosystem;

import edu.EcoSystem.controller.ModifierDevisController;
import edu.EcoSystem.controller.ModifierOffreController;
import edu.EcoSystem.entities.Devis;
import edu.EcoSystem.entities.Evenement;
import edu.EcoSystem.entities.Offre;
import edu.EcoSystem.entities.User;
import edu.EcoSystem.gui.AjoutEventBackController;
import edu.EcoSystem.gui.AjoutEventFrontController;
import edu.EcoSystem.gui.UpdateEventBackController;
import edu.EcoSystem.gui.UpdateUserController;
import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 *
 * @author zied
 */
public class EcoSystem extends Application {
    private static Stage primaryStage;

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root= FXMLLoader.load(getClass().getResource("/edu/EcoSystem/gui/loginUser.fxml"));
        Scene scene=new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    
    
    }
    
    public static  boolean showEventEditDialog(User user,int id) {
    try {
        // Load the fxml file and create a new stage for the popup dialog.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(EcoSystem.class.getResource("/edu/EcoSystem/gui/updateUser.fxml"));
        StackPane page = (StackPane) loader.load();

        // Create the dialog Stage.
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Modifier User");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(primaryStage);
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        UpdateUserController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setUser(user,id);

        // Show the dialog and wait until the user closes it
        dialogStage.showAndWait();

        return controller.isOkClicked();
    } catch (IOException e) {
        e.printStackTrace();
        return false;
    }
    }
    
     public static boolean showOffreEditDialog(Offre offre) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(EcoSystem.class.getResource("/edu/EcoSystem/gui/ModifierOffre.fxml"));
            StackPane page = (StackPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Modifier Offre!!");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            ModifierOffreController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setOffre(offre);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static boolean showDevisEditDialog(Devis devis) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(EcoSystem.class.getResource("/edu/EcoSystem/gui/ModifierDevis.fxml"));
            StackPane page = (StackPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Modifier Devis!!");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            ModifierDevisController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setDevis(devis);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static  boolean showEventEditDialog(Evenement eventt) {
    try {
        // Load the fxml file and create a new stage for the popup dialog.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(EcoSystem.class.getResource("/edu/EcoSystem/gui/UpdateEventBack.fxml"));
        StackPane page = (StackPane) loader.load();

        // Create the dialog Stage.
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Modifier Evénement");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(primaryStage);
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        UpdateEventBackController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setEvent(eventt);

        // Show the dialog and wait until the user closes it
        dialogStage.showAndWait();

        return controller.isOkClicked();
    } catch (IOException e) {
        e.printStackTrace();
        return false;
    }
    }
    
    public static  boolean showEventAddDialog() {
    try {
        // Load the fxml file and create a new stage for the popup dialog.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(EcoSystem.class.getResource("/edu/EcoSystem/gui/AjoutEventBack.fxml"));
        StackPane page = (StackPane) loader.load();

        // Create the dialog Stage.
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Ajouter Evénement");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(primaryStage);
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        AjoutEventBackController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        

        // Show the dialog and wait until the user closes it
        dialogStage.showAndWait();

        return controller.isOkClicked();
    } catch (IOException e) {
        e.printStackTrace();
        return false;
    }
    }
    
    public static  boolean showEventAddDialog1() {
    try {
        // Load the fxml file and create a new stage for the popup dialog.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(EcoSystem.class.getResource("/edu/EcoSystem/gui/AjoutEventFront.fxml"));
        StackPane page = (StackPane) loader.load();

        // Create the dialog Stage.
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Ajouter Evénement");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(primaryStage);
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        AjoutEventFrontController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        

        // Show the dialog and wait until the user closes it
        dialogStage.showAndWait();

        return controller.isOkClicked();
    } catch (IOException e) {
        e.printStackTrace();
        return false;
    }
    }
    
}

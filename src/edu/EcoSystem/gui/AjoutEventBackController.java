/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.EcoSystem.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import edu.EcoSystem.entities.CurrentUser;
import edu.EcoSystem.entities.Evenement;
import edu.EcoSystem.services.EventCRUD;
import edu.EcoSystem.tools.SendingMail;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.ZoneId;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import org.apache.commons.lang3.RandomStringUtils;
import org.controlsfx.control.textfield.TextFields;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author Nader
 */
public class AjoutEventBackController implements Initializable {

    @FXML
    private TextField nom;

    @FXML
    private TextArea description;

    @FXML
    private JFXTextField adresse;

    @FXML
    private ChoiceBox<String> categorie;

    @FXML
    private TextField prix;

    @FXML
    private TextField minP;

    @FXML
    private TextField maxP;

    @FXML
    private DatePicker dateDebut;

    @FXML
    private DatePicker dateFin;

    @FXML
    private Button AjoutEvent;

    private Stage dialogStage;
    @FXML
    private JFXButton retourbutton;
    @FXML
    private Label labelNom;
    @FXML
    private Label labelDate;
    @FXML
    private Label labelDescription;
    @FXML
    private Label labelAdresse;
    @FXML
    private Label labelMinP;
    @FXML
    private Label labelCategorie;
    @FXML
    private Label labelPrix;

    private Stage dialogStage1;
    private boolean okClicked = false;
    @FXML
    private ImageView pic1;
    @FXML
    private JFXButton image1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        AjoutEvent.setStyle("-fx-background-color: #0aaf57");
        retourbutton.setStyle("-fx-background-color: #f43838");

        ObservableList<String> availableChoices = FXCollections.observableArrayList("Selectionner catégorie", "payant", "gratuit");
        categorie.setItems(availableChoices);
        categorie.getSelectionModel().selectFirst();

        ChangeListener<String> changeListener = new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.equals("gratuit")) {
                    prix.setDisable(true);
                } else {
                    prix.setDisable(false);
                }
            }
        };
        categorie.getSelectionModel().selectedItemProperty().addListener(changeListener);

        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("markers.json")) {
            Object obj = jsonParser.parse(reader);

            JSONArray employeeList = (JSONArray) obj;
            System.out.println(employeeList);

            employeeList.forEach(emp -> parseTestData((JSONObject) emp));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    private void parseTestData(JSONObject employee) {

        String description = (String) employee.get("address");
        System.out.println(description);

        TextFields.bindAutoCompletion(adresse, description);
    }

    /**
     * Sets the stage of this dialog.
     *
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void addImage(MouseEvent event) throws IOException {
        FileChooser fc = new FileChooser();

        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (.png)", "*.PNG");
        fc.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
        File selectedFile = fc.showOpenDialog(null);
        try {
            BufferedImage bufferedImage = ImageIO.read(selectedFile);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            pic1.setImage(image);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static String saveToFileImageNormal(Image image) {

        String ext = "jpg";
        File dir = new File("C:/wamp64/www/EcoSystem/web/uploads/images");
        String name = String.format("%s.%s", RandomStringUtils.randomAlphanumeric(8), ext);
        File outputFile = new File(dir, name);
        BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
        try {
            ImageIO.write(bImage, "png", outputFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return name;
    }

    @FXML
    void SubmitAjout(ActionEvent event) throws IOException {
        
        
        FXMLLoader loader = new FXMLLoader();
        Image image1 = pic1.getImage();
        String nameImage1 = saveToFileImageNormal(image1);
        //String selectedcategorie = categorie.getSelectionModel().getSelectedItem();
        java.util.Date dateD = java.util.Date.from(dateDebut.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        java.util.Date dateF = java.util.Date.from(dateFin.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        java.sql.Date sqldateD = new java.sql.Date(dateD.getTime());
        java.sql.Date sqldateF = new java.sql.Date(dateF.getTime());

        java.util.Date datecourante = new java.util.Date();
        java.sql.Date sqldatecourante = new java.sql.Date(datecourante.getTime());
        if (nom.getText().isEmpty()) {
            labelNom.setText("Champ Nom vide");
            //new Alert(Alert.AlertType.ERROR, " Champ Nom vide").show();
        } else if (description.getText().isEmpty()) {
            labelAdresse.setText("Champ Description vide");
            //new Alert(Alert.AlertType.ERROR, "Champ Description vide").show();
        } else if (adresse.getText().isEmpty()) {
            labelDescription.setText("Champ Adresse vide");
            //new Alert(Alert.AlertType.ERROR, " Champ Adresse vide").show();
        } else {
            // try to parse the postal code into an int.
            try {
                Integer.parseInt(minP.getText());
            } catch (NumberFormatException e) {
                labelMinP.setText("(must be an integer)");

            }
        }

        if (minP.getText().isEmpty()) {
            labelMinP.setText("Champ min participants vide");
            //new Alert(Alert.AlertType.ERROR, "Champ min participants vide").show();
        } else {
            // try to parse the postal code into an int.
            try {
                Integer.parseInt(maxP.getText());
            } catch (NumberFormatException e) {
                labelMinP.setText("(must be an integer)");

            }
        }

        if (maxP.getText().isEmpty()) {
            labelMinP.setText("Champ max participants vide");
            //new Alert(Alert.AlertType.ERROR, "Champ max participants vide").show();
        } else if (categorie.getSelectionModel().getSelectedItem().equals("Selectionner catégorie")) {
            labelCategorie.setText("Choisir une catégorie");
            //new Alert(Alert.AlertType.ERROR, "Choisir une catégorie").show();
        } else if (Integer.valueOf(minP.getText()) > Integer.valueOf(maxP.getText())) {
            labelMinP.setText("min particpants doit etre inferieur ala max de particpants");
            //new Alert(Alert.AlertType.ERROR, "min particpants doit etre inferieur ala max de particpants").show();
        } else if (sqldateD.compareTo(sqldateF) > 0) {
            labelDate.setText("date fin doit etre supérieur ou égal a la date debut");
            //new Alert(Alert.AlertType.ERROR, "date fin doit etre supérieur ou égal a la date debut").show();
        } else if (sqldateD.compareTo(sqldatecourante) < 0) {
            labelDate.setText("date debut doit etre supérieur ou egal a la date courante");
            //new Alert(Alert.AlertType.ERROR, "date debut doit etre supérieur ou egal a la date courante").show();
        } else {
            if (categorie.getSelectionModel().getSelectedItem().equals("gratuit")) {
                Evenement newevent = new Evenement(nom.getText(), sqldateD, sqldateF, description.getText(), adresse.getText(), Integer.valueOf(minP.getText()), Integer.valueOf(maxP.getText()), categorie.getSelectionModel().getSelectedItem(), 0, nameImage1, CurrentUser.id);
                newevent.setPublie(1);
                EventCRUD evenementService = new EventCRUD();

                evenementService.addEvent(newevent);

            } else {
                Evenement newevent = new Evenement(nom.getText(), sqldateD, sqldateF, description.getText(), adresse.getText(), Integer.valueOf(minP.getText()), Integer.valueOf(maxP.getText()), categorie.getSelectionModel().getSelectedItem(), Float.valueOf(prix.getText()), nameImage1, CurrentUser.id);
                newevent.setPublie(1);
                EventCRUD evenementService = new EventCRUD();
                evenementService.addEvent(newevent);

            }

            TrayNotification tray = new TrayNotification("Information", "Evènement Ajouté", NotificationType.SUCCESS);
            tray.setAnimationType(AnimationType.POPUP);
            tray.showAndDismiss(Duration.seconds(3));

            dialogStage.close();

        }
    }

    @FXML
    private void Retour(ActionEvent event) throws IOException {
        dialogStage.close();

    }

    //API:Calendier,Envoi email pour les utlisateurs participer
}

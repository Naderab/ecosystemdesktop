/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.GestionProduit.GUI;

import static edu.GestionProduit.GUI.Produit2Controller.prod;
import edu.GestionProduit.entities.categorie;
import edu.GestionProduit.entities.produit2;
import edu.GestionProduit.entities.sous_categorie;
import edu.GestionProduit.service.CrudCategorie;
import edu.GestionProduit.service.CrudProduit;
import edu.GestionProduit.service.CrudSousCategorie;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import org.apache.commons.lang3.RandomStringUtils;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

/**
 * FXML Controller class
 *
 * @author Abir
 */
public class UpdateProduitController implements Initializable {

    private int idUser = 1;
    public String action;
    private String imageFile = "";
    private CrudProduit crudProduit;
    private CrudCategorie crudCategorie;
    private CrudSousCategorie crudSousCategorie;
    private produit2 p;

    @FXML
    private Button tfretourafficheproduit;
    @FXML
    private TextField tftitre;
    @FXML
    private TextArea tfdescription;
    @FXML
    private TextField tfprix;
    @FXML
    private TextField tfrating;
    @FXML
    private ComboBox<categorie> boxCat;
    @FXML
    private ComboBox<sous_categorie> boxSousCat;
    @FXML
    private Button buttonimage;
    @FXML
    private Button buttonajouterproduit;
    @FXML
    private Label controletitre;
    @FXML
    private Label controledes;
    @FXML
    private Label controlecate;
    @FXML
    private Label controlesouscat;
    @FXML
    private Label controleimage;
    @FXML
    private Label controleprix;
    private ImageView imageview;
    @FXML
    private Button tfaccueil;
    @FXML
    private ImageView pic1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        Platform.runLater(() -> {

            crudProduit = new CrudProduit();
            crudCategorie = new CrudCategorie();

            try {
                boxCat.setItems(crudCategorie.getAllCategorie());
            } catch (SQLException ex) {
                Logger.getLogger(UpdateProduitController.class.getName()).log(Level.SEVERE, null, ex);
            }

            crudSousCategorie = new CrudSousCategorie();

            if (action.equals("update")) {
                initPartner(p);
            }
        });
    }

    private void initPartner(produit2 p) {
        tftitre.setText(p.getTitre());
        tfdescription.setText(p.getDescription());

    }

    public void setProduit2(produit2 p) {
        this.p = p;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    @FXML
    private void cliquerAjouterproduit(ActionEvent event) throws SQLException, IOException {
        
        if (isValid(tftitre, tfdescription,
                tfprix, tfrating,
                boxCat, boxSousCat, imageFile) == false) {
            

            if (action.equals("update")) {
                javafx.scene.image.Image image1 = pic1.getImage();
                String nameImage1 = saveToFileImageNormal(image1);
                
                p.setId_user(idUser);
                p.setTitre(tftitre.getText());
                p.setDescription(tfdescription.getText());
                p.setPrix(Integer.parseInt(tfprix.getText()));
                p.setRating(Integer.parseInt(tfrating.getText()));
                p.setId_categorie(boxCat.getValue().getId());
                p.setImage(imageFile);
                p.setId_SousCategorie(boxSousCat.getValue().getId());
                crudProduit.updateProduit(p);

            } else {
                
                javafx.scene.image.Image image1 = pic1.getImage();
                String nameImage1 = saveToFileImageNormal(image1);
                
                produit2 p = new produit2();
                p.setId_user(idUser);
                p.setTitre(tftitre.getText());
                p.setDescription(tfdescription.getText());
                p.setPrix(Integer.parseInt(tfprix.getText()));
                p.setRating(Integer.parseInt(tfrating.getText()));
                p.setId_categorie(boxCat.getValue().getId());
                p.setImage(nameImage1);
                p.setId_SousCategorie(boxSousCat.getValue().getId());
                crudProduit.ajouterProduit(p);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ajouter une Annonce");
                alert.setHeaderText("Résultat:");
                alert.setContentText("Annonce ajoutée avec succès!");
                alert.showAndWait();
                
                

            }
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/edu/GestionProduit/GUI/Profile.fxml"));
            Parent parent = loader.load();
            Scene scene = new Scene(parent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();

        } else {
            
            Alert alert1 = new Alert(Alert.AlertType.WARNING);
            alert1.setTitle("Annonce");
            alert1.setHeaderText(null);
            alert1.setContentText("vous devez remplir tous les champs!");
            alert1.showAndWait();

        }
        

    }

    @FXML
    private void cliqueretourafichage(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/edu/GestionProduit/GUI/Profile.fxml"));
        Parent profileParent = loader.load();

        Scene gestionProfilScene = new Scene(profileParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(gestionProfilScene);
        window.show();
    }

    public boolean isValid(TextField tftitre, TextArea tfdescription,
            TextField tfprix, TextField tfrating,
            ComboBox<categorie> boxCat, ComboBox<sous_categorie> boxSousCat, String imageFile) {
        if (tftitre.getText().trim().equals("") || tfdescription.getText().trim().equals("")
                || tfprix.getText().trim().equals("") || tfrating.getText().trim().equals("")
                || boxCat.getSelectionModel().isEmpty()
                || boxSousCat.getSelectionModel().isEmpty() || imageFile.equals("")) {
            return false;
        } else {
            return true;
        }
    }

    @FXML
    private void cliquecatselec(ActionEvent event) throws SQLException {
        boxSousCat.setItems(crudSousCategorie.getAllSousCategorie(boxCat.getValue().getId()));
    }

    @FXML
    private void cliqueretouraccueil(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/edu/GestionProduit/GUI/Profile.fxml"));
        Parent profileParent = loader.load();

        Scene gestionProfilScene = new Scene(profileParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(gestionProfilScene);
        window.show();

    }

    @FXML
    private void cliqueruploadimage(ActionEvent event) {

        FileChooser fc = new FileChooser();

        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (.png)", "*.PNG");
        fc.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
        File selectedFile = fc.showOpenDialog(null);
        try {
            BufferedImage bufferedImage = ImageIO.read(selectedFile);
            WritableImage image = SwingFXUtils.toFXImage(bufferedImage, null);
            pic1.setImage(image);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static String saveToFileImageNormal(Image image) {

        String ext = "jpg";
        File dir = new File("file:C:/wamp64/www/EcoSystem/web/uploads/images");
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
}

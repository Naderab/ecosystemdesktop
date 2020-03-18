/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pointcollect.services;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.sun.javafx.scene.layout.region.Margins.Converter;
import edu.pointcollect.entities.*;
import java.awt.event.MouseEvent;
import java.net.*;
import javafx.collections.*;
import javafx.stage.*;
import javafx.event.*;
import javafx.scene.image.*;
import javafx.embed.swing.*;
import javax.imageio.*;
import java.awt.image.*;
import java.util.logging.*;
import javafx.scene.layout.*;
import javafx.geometry.*;
import javafx.scene.*;
import java.nio.file.*;
import java.io.*;
import java.util.*;
import javax.sql.rowset.serial.*;
import javafx.fxml.*;
import java.sql.*;
import java.util.stream.Collectors;
import javafx.animation.FadeTransition;
import javafx.beans.value.*;
import javafx.beans.property.*;
import javafx.scene.control.*;
import javafx.scene.control.TreeTableColumn.CellDataFeatures;
import javafx.scene.control.cell.TextFieldTreeCell;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Callback;
import javafx.util.Duration;
import static jdk.nashorn.internal.objects.NativeArray.map;
import static jdk.nashorn.internal.objects.NativeObject.keys;
import org.apache.commons.lang3.RandomStringUtils;

public class CrudCategorieController extends TreeCell implements Initializable {

    @FXML
    private Button openButton;
    private Label path;
    @FXML
    private TextField name;
    @FXML
    private TextArea description;

    @FXML
    private Button openButtonSousCat;
    private Label pathSousCat;
    @FXML
    private TextArea descSousCat;
    @FXML
    private TextField nameSousCat;
    @FXML
    private ComboBox<String> choixCat;
    @FXML
    private Button ajouter;
    @FXML
    private Button ajouterSousCat;

      private TableView<Map.Entry<Integer,ArrayList<SousCategorieTrie>>> tablecat;
        private ObservableList<String> availableChoices = FXCollections.observableArrayList();            
   
    @FXML
    private ImageView picCategorie;
    private Label picSousCat;
    @FXML
    private ImageView picSousCategorie;
  
       
       private ObservableList<CategorieTrie> data;
    private ObservableList<SousCategorieTrie> data1;

    @FXML
    private ScrollPane paneCategorie;
    @FXML
    private Label price;
    @FXML
    private JFXHamburger affmenu;
    @FXML
    private JFXDrawer menu;
    @FXML
    private ScrollPane paneSousCategorie;
    @FXML
    private Label verifCategorie;
    @FXML
    private Label verifSousCategorie;
    
    boolean verifCat;
    boolean verifSousCat;
    @FXML
    private Label ecosystem;
       
    public void initialize(final URL url, final ResourceBundle rb) {
        
        CategorieTrieCrud ctr=new CategorieTrieCrud();
        availableChoices=ctr.listerCategorie();
        
        choixCat.setItems(availableChoices);

        
        
         TilePane b = new TilePane();
        b.setPadding(new javafx.geometry.Insets(30));
        TilePane c = new TilePane();
        FadeTransition ft = new FadeTransition(Duration.millis(1500));
        CategorieTrieCrud eventcrud = new CategorieTrieCrud();
        data = eventcrud.listeCategorie();
        for (CategorieTrie d : data) {

            try {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/pointcollect/gui/DivCategorieBack.fxml"));
                Parent root = (Pane) loader.load();
                DivCategorieBackController DHC = loader.getController();
                Rectangle image = DHC.LoadValues(d);
              

                image.setOnMouseClicked((javafx.scene.input.MouseEvent event) -> {
                    if (event.getClickCount() == 1) {
                        try {

                            click(event, d);
                            System.out.println(d.getId());
                        } catch (SQLException ex) {
                            Logger.getLogger(DivCategorieBackController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });

                //   c.setVgap(40);
                c.getChildren().removeAll();

                c.getChildren().add(root);
                c.setMaxWidth(Control.USE_COMPUTED_SIZE);
                c.setMaxHeight(Control.USE_COMPUTED_SIZE);
                c.setMinHeight(Control.USE_COMPUTED_SIZE);
                c.setMinWidth(Control.USE_COMPUTED_SIZE);
            } catch (IOException ex) {
                Logger.getLogger(ListCategorieFrontController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        c.setPrefColumns(3);
        c.setPadding(new javafx.geometry.Insets(0));
        c.setHgap(10);
        c.setVgap(80);
        b.getChildren().add(c);
        b.setPrefWidth(50);
        paneCategorie.setContent(b);

    }

    
 public void click(javafx.scene.input.MouseEvent event, CategorieTrie selectedetab) throws SQLException {
        if (event.getClickCount() == 1) {
            System.err.println("Click");
            System.out.println(selectedetab.getId());
            TilePane b = new TilePane();
            b.setPadding(new javafx.geometry.Insets(30));
            TilePane c = new TilePane();
            FadeTransition ft = new FadeTransition(Duration.millis(1500));
            SousCategorieTrieCrud eventcrud = new SousCategorieTrieCrud();
            data1 = eventcrud.listerCategorie(selectedetab.getId());
            for (SousCategorieTrie d : data1) {

                try {
                    System.out.println(d.getName());
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/pointcollect/gui/DivCategorieBack.fxml"));
                    Parent root = (Pane) loader.load();
                    DivCategorieBackController DHC = loader.getController();
                    DHC.LoadValues1(d);
                    

                    //   c.setVgap(40);
                    c.getChildren().removeAll();

                    c.getChildren().add(root);
                    c.setMaxWidth(Control.USE_COMPUTED_SIZE);
                    c.setMaxHeight(Control.USE_COMPUTED_SIZE);
                    c.setMinHeight(Control.USE_COMPUTED_SIZE);
                    c.setMinWidth(Control.USE_COMPUTED_SIZE);
                } catch (IOException ex) {
                    Logger.getLogger(ListCategorieFrontController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            c.setPrefColumns(3);
            c.setPadding(new javafx.geometry.Insets(0));
            c.setHgap(10);
            c.setVgap(80);
            b.getChildren().add(c);
            b.setPrefWidth(50);
            paneSousCategorie.setContent(b);
        }
    }

   
    
    public static String saveToFileImageNormal(Image image) {

        String ext = "jpg";
        File dir = new File("C:/Users/Khouloud/Pictures/pidev");
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

    private void setExtFilters(final FileChooser chooser) {
        // chooser.getExtensionFilters().addAll((Object[])new FileChooser.ExtensionFilter[] { new FileChooser.ExtensionFilter("All Images", new String[] { "*.*" }), new FileChooser.ExtensionFilter("PNG", new String[] { "*.png" }) });
    }

    @FXML
    private void ajouterCategorie(final ActionEvent event) throws IOException, SQLException {
        
         Validation v=new Validation();
      verifCat= v.textFieldIsNull(name,verifCategorie,"Champs titre obligatoire");
    
      
        
        Image image1 = picCategorie.getImage();
        String nameImage1 = saveToFileImageNormal(image1);

        CategorieTrie p = new CategorieTrie(name.getText(), description.getText(), nameImage1);
        System.err.println(p.toString());
        CategorieTrieCrud produitService = new CategorieTrieCrud();
        
         if (produitService.SearchCategorie(name.getText())==false){
        produitService.ajouterCategorie(p, nameImage1);
       
       new Alert(Alert.AlertType.INFORMATION, "Cette categorie est ajoute avec succes !!").show();
         }
          else {
             new Alert(Alert.AlertType.WARNING, "Cette categorie existe deja !").show();
        }
        name.clear();
         descSousCat.clear();
   
        refreshScene(event);

    }
   

    
    
    @FXML
    private void ajouterSousCategorie(final ActionEvent event) throws IOException, SQLException {
         Validation v=new Validation();
      verifSousCat= v.textFieldIsNull(nameSousCat,verifSousCategorie,"Champs titre obligatoire");
      
           Image image1 = picSousCategorie.getImage();
        String nameImage1 = saveToFileImageNormal(image1);
            SousCategorieTrie p = new SousCategorieTrie(nameSousCat.getText(), descSousCat.getText(), nameImage1);
            System.err.println(p.toString());
            SousCategorieTrieCrud produitService = new SousCategorieTrieCrud();
      String output = choixCat.getSelectionModel().getSelectedItem().toString();
            CategorieTrieCrud s = new CategorieTrieCrud();
           
            
        int a = s.selectIdName(output);
          if (produitService.SearchSousCategorie(name.getText(),a)!=false){
             produitService.ajouterCategorie(p,a);
          new Alert(Alert.AlertType.INFORMATION, "Cette categorie est ajoute avec succes !!"+a).show();
            }
          else {
                     new Alert(Alert.AlertType.WARNING, "Cette sous categorie existe deja dans "+nameSousCat.getText()).show();

        }
           nameSousCat.clear();
         descSousCat.clear();
   
        refreshScene(event);
     
    }

    @FXML
    private void openFile(ActionEvent event) {
    }

    @FXML
    private void openFileSousCat(ActionEvent event) {
    }

    @FXML
    private void addImageCategorie(javafx.scene.input.MouseEvent event) {
         FileChooser fc = new FileChooser();

        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (.png)", "*.PNG");
        fc.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
        File selectedFile = fc.showOpenDialog(null);
        try {
            BufferedImage bufferedImage = ImageIO.read(selectedFile);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            picCategorie.setImage(image);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void addImageSousCategorie(javafx.scene.input.MouseEvent event) {
         FileChooser fc = new FileChooser();

        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (.png)", "*.PNG");
        fc.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
        File selectedFile = fc.showOpenDialog(null);
        try {
            BufferedImage bufferedImage = ImageIO.read(selectedFile);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            picSousCategorie.setImage(image);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
     @FXML
    private void actionTypePoint(final ActionEvent event) throws IOException
    {
     /*AnchorPane pane = FXMLLoader.load(getClass().getResource("/edu/pointcollect/gui/CrudType.fxml" ));
            AnchorPaneTypePoint.getChildren().setAll(pane);   */
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/edu/pointcollect/gui/CrudType.fxml"));
        Parent profileParent = loader.load();
        
        
        Scene gestionProfilScene = new Scene (profileParent);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(gestionProfilScene);
        window.show();
       
    }
    
      @FXML
    private void actionAddress(final ActionEvent event) throws IOException
    {
     /*AnchorPane pane = FXMLLoader.load(getClass().getResource("/edu/pointcollect/gui/CrudType.fxml" ));
            AnchorPaneTypePoint.getChildren().setAll(pane);   */
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/edu/pointcollect/gui/CrudAddress.fxml"));
        Parent profileParent = loader.load();
        
        
        Scene gestionProfilScene = new Scene (profileParent);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(gestionProfilScene);
        window.show();
       
    }
    
     private void refreshScene(final ActionEvent event) throws IOException
    {
     /*AnchorPane pane = FXMLLoader.load(getClass().getResource("/edu/pointcollect/gui/CrudType.fxml" ));
            AnchorPaneTypePoint.getChildren().setAll(pane);   */
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/edu/pointcollect/gui/CrudCategorie.fxml"));
        Parent profileParent = loader.load();
        
        
        Scene gestionProfilScene = new Scene (profileParent);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(gestionProfilScene);
        window.show();
       
    }

    @FXML
    private void fontPageCategorie(javafx.scene.input.MouseEvent event) {
        
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/edu/pointcollect/gui/ListCategorieFront.fxml"));
            Parent profileParent = loader.load();
            
            
            Scene gestionProfilScene = new Scene (profileParent);
            
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(gestionProfilScene);
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(CrudCategorieController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

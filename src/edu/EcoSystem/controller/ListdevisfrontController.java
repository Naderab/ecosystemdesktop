/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.EcoSystem.controller;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import ecosystem.EcoSystem;
import edu.EcoSystem.services.DevisCRUD;
import edu.EcoSystem.services.OffreCRUD;
import edu.EcoSystem.tools.SendingMail;
import edu.EcoSystem.entities.Devis;
import edu.EcoSystem.entities.Offre;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ramzi
 */
public class ListdevisfrontController implements Initializable {

    private AnchorPane rootLayout;
    private Stage primaryStage;
    @FXML
    private TableView<Offre> offreTable;

    @FXML
    private TableColumn<Offre, String> tab_titre;

    @FXML
    private TableColumn<Offre, String> tab_type;

    @FXML
    private TableColumn<Offre, String> tab_desc;

    @FXML
    private TableColumn<Offre, Date> tab_dateo;

    @FXML
    private TableColumn<Offre, Date> tab_datev;

    @FXML
    private TextField rechercherOffre;
    @FXML
    private Button btn_generer;

    @FXML
    private TableView<Devis> devisTable;
    @FXML
    private TableColumn<Devis, String> tab_libelle;
    @FXML
    private TableColumn<Devis, String> tab_descd;
    @FXML
    private TableColumn<Devis, Date> tab_dated;
    @FXML
    private TableColumn<Devis, Date> tab_datevd;
    @FXML
    private TableColumn<Devis, Double> tab_TTC;
    @FXML
    private TextField rechercherDevis;
    @FXML
    private Button btn_modifier;
    @FXML
    private Button btn_supprimer;
    @FXML
    private Button envoyerMail;
    @FXML
    private TextField email;
    @FXML
    private Button pdf;
    @FXML
    private Button btn_actualiser;

    ObservableList observableListdevis;
    @FXML
    private AnchorPane ap;
    @FXML
    private ScrollPane pane;
    @FXML
    private JFXHamburger affmenu;
    @FXML
    private JFXDrawer menu;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
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
            Logger.getLogger(ListdevisfrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
        // TODO

        OffreCRUD offrecrud = new OffreCRUD();
        ArrayList arrayList = (ArrayList) offrecrud.afficherOffreDemander();
        ObservableList observableList = FXCollections.observableArrayList(arrayList);
        offreTable.setItems(observableList);
        tab_titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        tab_type.setCellValueFactory(new PropertyValueFactory<>("type"));
        tab_desc.setCellValueFactory(new PropertyValueFactory<>("description"));
        tab_dateo.setCellValueFactory(new PropertyValueFactory<>("dateoffre"));
        tab_datev.setCellValueFactory(new PropertyValueFactory<>("datevalidite"));

        DevisCRUD deviscrud = new DevisCRUD();
        ArrayList arrayListdevis = (ArrayList) deviscrud.afficherDevis();
        observableListdevis = FXCollections.observableArrayList(arrayListdevis);
        devisTable.setItems(observableListdevis);
        tab_libelle.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        tab_descd.setCellValueFactory(new PropertyValueFactory<>("description"));
        tab_dated.setCellValueFactory(new PropertyValueFactory<>("datedevis"));
        tab_datevd.setCellValueFactory(new PropertyValueFactory<>("datevalidite"));
        tab_TTC.setCellValueFactory(new PropertyValueFactory<>("totalTTC"));
    }
    
    

    @FXML
    private void ModifierDevis(ActionEvent event) {
         Devis selectedEvent = devisTable.getSelectionModel().getSelectedItem();
        if (selectedEvent != null) {
            boolean okClicked = EcoSystem.showDevisEditDialog(selectedEvent);
            if (okClicked) {
                
            }

        } else {
            // Nothing selected.
            new Alert(Alert.AlertType.ERROR, "Aucun devis selectionné").show();

        }
    }

    @FXML
    private void supprimerdevis(ActionEvent event) {
        Devis selectedIndex = devisTable.getSelectionModel().getSelectedItem();
        int selected = devisTable.getSelectionModel().getSelectedIndex();

        if (selected >= 0) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Notification");
            alert.setContentText("Etes vous sure de supprimer cette devis ??");
            Optional<ButtonType> result = alert.showAndWait();
            ButtonType button = result.orElse(ButtonType.CANCEL);
            if (button == ButtonType.OK) {
                DevisCRUD DC = new DevisCRUD();
                DC.supprimerDevis(selectedIndex.getId());
                devisTable.getItems().remove(selected);
                new Alert(Alert.AlertType.INFORMATION, "Supprimer Avec succées").show();
            }
        } else {
            new Alert(Alert.AlertType.INFORMATION, "Aucun Devis selectionné").show();
        }

    }

    @FXML
    private void genererdevis(ActionEvent event) {
        try {
            Offre selectedIndex = offreTable.getSelectionModel().getSelectedItem();
            int selected = offreTable.getSelectionModel().getSelectedIndex();
            if (selected >= 0) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Notification");
                alert.setContentText("Etes vous sure de genérer cette devis ??");
                Optional<ButtonType> result = alert.showAndWait();
                ButtonType button = result.orElse(ButtonType.CANCEL);
                if (button == ButtonType.OK) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/EcoSystem/gui/AjouterDevis.fxml"));
                    Parent root = loader.load();
                    btn_generer.getScene().setRoot(root);
                    AjouterDevisController controller = loader.getController();
                    controller.setOffre(selectedIndex);

                }
            } else {
                new Alert(Alert.AlertType.INFORMATION, "Aucune demande selectionné").show();
            }

        } catch (IOException ex) {
            Logger.getLogger(ListdevisfrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void envoyerMail(ActionEvent event) {

        Devis selectedIndex = devisTable.getSelectionModel().getSelectedItem();
        int selected = devisTable.getSelectionModel().getSelectedIndex();

        if (selected >= 0) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Notification");
            alert.setContentText("Etes vous sure de enovyer cette devis ??");
            Optional<ButtonType> result = alert.showAndWait();
            ButtonType button = result.orElse(ButtonType.CANCEL);
            if (button == ButtonType.OK) {

                String contenu = "Message envoyer";
                SendingMail sm = new SendingMail(contenu, email.getText(), "welcome");
                SendingMail.envoyer();

                new Alert(Alert.AlertType.INFORMATION, "Envoyer Avec succées").show();
            }
        } else {
            new Alert(Alert.AlertType.INFORMATION, "Aucun Devis selectionné").show();
        }

    }

    @FXML
    private void generatepdf(ActionEvent event
    ) {
        Devis selectedIndex = devisTable.getSelectionModel().getSelectedItem();
        int selected = devisTable.getSelectionModel().getSelectedIndex();

        if (selected >= 0) {

            try {
                // System.out.println("Haouet------------------------------------->"+nom);

                com.itextpdf.text.Document document = new com.itextpdf.text.Document();
                PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\ramzi\\Desktop/Devis.pdf"));
                document.open();
                document.add(new Paragraph(" WorldFriendship ", FontFactory.getFont(FontFactory.TIMES)));
                //cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                //String date=new Date().toString();
                //document.add(new Paragraph(" Date : " + new Date().toString()));
                //  document.add(new Paragraph("-----------------------------------------------------------------"));
                // document.add(new Paragraph("-----------------------------------------------------------------"));
                com.itextpdf.text.pdf.PdfPTable table = new com.itextpdf.text.pdf.PdfPTable(2);
                com.itextpdf.text.pdf.PdfPCell cell = new com.itextpdf.text.pdf.PdfPCell(new Paragraph("Devis"));
                cell.setColspan(4);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(BaseColor.PINK);
                table.addCell(cell);

//                table.addCell("nom");
//              table.addCell(nom1);
//              table.addCell("prenom");
//              table.addCell(prenom1);
                table.addCell("Libelle");
                table.addCell(selectedIndex.getLibelle());
                table.addCell("Prix");
                table.addCell(Double.toString(selectedIndex.getPrixunit()));

                //PanierService ps = new PanierService();
                // int id1=ps2.rechercheridutili(nom1, prenom1);
                //float h = ps.getprixtotale();
                //table.addCell("Prenom");
                //    table.addCell(selectedUser.getPrix());
                /*table.addCell("Date");
            table.addCell(LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
            table.addCell("***************************");
            table.addCell("***************************");
            table.addCell("Montant Totale");
            table.addCell(Float.toString(h));*/
                //Image image = Image.getInstance ("file:C://wamp64/www/image/" +"radius.png"); 
                // image.setAbsolutePosition(50,50);
                //document.add(new Paragraph("test\n  test")); 
                //  document.add(image);  
                //    table.addCell("email");
                //      table.addCell(selectedUser.getQuantiteStock());
                //     table.addCell("email");
                //table.addCell(selectedUser.getNom());
                //System.out.println("***********"+selectedUser.getNom().toString());
                //  data = loadPanier();
                document.add(table);
                document.close();

            } catch (FileNotFoundException ex) {
                Logger.getLogger(ListdevisfrontController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (DocumentException ex) {
                Logger.getLogger(ListdevisfrontController.class.getName()).log(Level.SEVERE, null, ex);
            }
            /*PanierService  ps = new PanierService();
       ps.confirmerCommande();*/

        } else {
            new Alert(Alert.AlertType.INFORMATION, "Aucun Devis selectionné").show();
        }
    }

    @FXML
    private void ActualiserDevis(ActionEvent event) {
        observableListdevis.clear();
        DevisCRUD dc = new DevisCRUD();
        ArrayList arrayListdevis = (ArrayList) dc.afficherDevis();
        observableListdevis = FXCollections.observableArrayList(arrayListdevis);
        devisTable.setItems(observableListdevis);

    }

}

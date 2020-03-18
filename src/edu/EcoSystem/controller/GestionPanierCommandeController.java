/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.EcoSystem.controller;


import edu.EcoSystem.tools.MyDB;
import edu.EcoSystem.entities.Panier;
import edu.EcoSystem.entities.Fos_User;
import edu.EcoSystem.entities.Produit;
import edu.EcoSystem.services.CRUDFos_User;
import edu.EcoSystem.services.CRUDPanier;
import edu.EcoSystem.services.CRUDProduit;
import Utils.UserAccess;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import static java.lang.System.in;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import static java.util.Locale.US;
import java.util.Optional;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import static org.apache.poi.hssf.usermodel.HeaderFooter.page;

/**
 *
 * @author MEGAJAS
 */
public class GestionPanierCommandeController implements Initializable{

    @FXML
    private Text titreAjoutProduit;
    private JFXTextField Titre;
    @FXML
    private TableView<Produit> tableproduitFront;
    @FXML
    private Button Modifier;
    @FXML
    private Button Supprimer;
    private TableColumn<?, ?> description;
    private TableColumn<?, ?> categorie;
    @FXML
    private JFXButton GestionMenuFront;
    @FXML
    private JFXButton GestionPanierfront;
    @FXML
    private Button AjouterProduit;
    @FXML
    private TableColumn<?, ?> prix;
    private TableColumn<?, ?> titreProd;
    private TableColumn<?, ?> prixProd;
    private TableColumn<?, ?> quantiteProd;
    public ArrayList<Produit> ran;
    public ArrayList<Panier> ran2;
    @FXML
    private TableColumn<?, ?> quantite;
    @FXML
    private TableColumn<?, ?> titre;
    //private int id;
    private int idUser;
    @FXML
    private TextField titreAjout;
    @FXML
    private TextField prixAjout;
    @FXML
    private TextField quantiteAjout;
    @FXML
    private TableView<Panier> tableprod1;
    @FXML
    private TableColumn<?, ?> titre1;
    @FXML
    private TableColumn<?, ?> prix1;
    @FXML
    private TableColumn<?, ?> quantite1;
    @FXML
    private DatePicker dateFront;
    @FXML
    private TextField titre_mod;
    @FXML
    private TextField prixprod_mod;
    @FXML
    private TextField quantite_mod;
    @FXML
    private TextField id_mod;
    @FXML
    private TableColumn<?, ?> id1;
    private int s1;
    private int id ;
    private int n ;
    private int n2;
   private String maill;
    @FXML
    private Button calcul;
    @FXML
    private TextField prixTotal;
    @FXML
    private Button pdf;
    @FXML
    private Button valider;
    @FXML
    private Text titreAjoutProduit1;
    @FXML
    private Button remise;
    @FXML
    private TextField remiseP;
    @FXML
    private TextField apresRemise;
    private TextField quantiteStock;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        affichertable();
        affichertablePanier();
       
    }

    @FXML
    private void ModifierButton(ActionEvent event) throws SQLException {
        
          CRUDPanier cr = new CRUDPanier();
        Panier p = new Panier(Integer.parseInt(id_mod.getText()),titre_mod.getText(), prixprod_mod.getText(),Integer.parseInt(quantite_mod.getText()));
                
                
                
        cr.modifierPanierNom(p);
        
        Notifications notif = Notifications.create()
                .title("Panier Modifie")
                .text("Le panier est bien modifiee !")
                .darkStyle().graphic(null).hideAfter(Duration.seconds(5))
                .position(Pos.TOP_LEFT)
                .onAction(new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent event) {
                        System.out.println("Clicked ont notif");
                    }
                });

        notif.showConfirm();
        refreshtable2();
         clearModif();
        
    }

    @FXML
    private void SupprimerButton(ActionEvent event) throws SQLException  {
              Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
              alert.setTitle("Confirmation");
              alert.setHeaderText("Suppression");
              alert.setContentText("Voulez vous supprimer cet Panier?");

          Optional<ButtonType> result = alert.showAndWait();
if (result.get() == ButtonType.OK){
         Panier s = (Panier) tableprod1.getSelectionModel().getSelectedItem();
       String titre = titre_mod.getText();
        CRUDPanier cr = new CRUDPanier();
        cr.supprimerPanierNom(titre);
        
        refreshtable2();
        clearModif();
       
    } 
else{
    clearModif();
    
}  
      
 }
    
      public void clearModif()
     {
         
         id_mod.clear();
         titre_mod.clear();
         prixprod_mod.clear();
         quantite_mod.clear();
     }
    
    
    
    @FXML
    private void GestionMenuFront(ActionEvent event) {
        
        try {
            FXMLLoader loader = new FXMLLoader (getClass().getResource("/edu/EcoSystem/gui/Menu.fxml"));
            Parent root =loader.load();
            GestionMenuFront.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(edu.EcoSystem.controller.LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void GestionPanierFront(ActionEvent event) {
    }


    private void affichertable() {
        
        Task<ArrayList<Produit>> task = new Task() {

            @Override
            protected Object call() throws SQLException {
                ran = (ArrayList<Produit>) new CRUDProduit().afficherProduit();
                return ran;
            }
        };

        task.setOnSucceeded((WorkerStateEvent e) -> {
            titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
            prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
            quantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
            tableproduitFront.setItems(FXCollections.observableArrayList(task.getValue()));
        });
        task.setOnFailed(e -> {
            affichertable();
        });
        Thread th = new Thread(task);
        th.start();
    }
    @FXML
    private void ShowOnClick(MouseEvent event) {
        
        tableproduitFront.setOnMouseClicked(e -> {
            Produit s = (Produit) tableproduitFront.getSelectionModel().getSelectedItem();
             try {
                  ran = (ArrayList<Produit>) new CRUDProduit().afficherProduit();
             } catch (SQLException ex) {
                 Logger.getLogger(GestionPanierCommandeController.class.getName()).log(Level.SEVERE, null, ex);
             }
             
            
            titreAjout.setText(s.getTitre());
            prixAjout.setText(Integer.toString(s.getPrix()));
           
          

        });
        
        
    }   

    @FXML
    private void AjouterProduitButton(ActionEvent event) throws SQLException {
        
        
        CRUDFos_User US = new CRUDFos_User();
        Fos_User u1 = US.Get_Single_User(UserAccess.getUser_ID());
        idUser=u1.getId();
        
        CRUDPanier es = new CRUDPanier();
        
        if( quantiteAjout.getText().isEmpty()  )
        {
         Alert alert5 = new Alert(Alert.AlertType.ERROR);
            alert5.setTitle("Verifier la Panier ");
            alert5.setHeaderText(null);
            alert5.setContentText("Merci de bien vouloir remplir votre champ");
            alert5.showAndWait();
        }
        else if(!quantiteAjout.getText().matches("^([1-9][0-9]{0,4}|100000)$")){    
          Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Valider a quantite du Commande");
            alert1.setHeaderText(null);
            alert1.setContentText("Veuillez verifier la quantite entre 1 et 99999 ");
            alert1.showAndWait();
         }
        else if(LocalDate.now().isAfter(dateFront.getValue()))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Date Commande est inférieure à la date actuelle!");
            alert.setContentText("Erreur");
            alert.showAndWait();
        }
        
        else
        {
        
         Panier e ;
        e = new Panier(prixAjout.getText(), titreAjout.getText(), dateFront.getValue(), idUser,Integer.parseInt(quantiteAjout.getText()) );
               
       
       es.ajouterPanierFront(e);
       Notifications notif = Notifications.create()
                .title("Produit ajouté ")
                .text("Le produit est bien ajoutée dans votre Panier !")
                .darkStyle().graphic(null).hideAfter(Duration.seconds(5))
                .position(Pos.TOP_LEFT)
                .onAction(new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent event) {
                        System.out.println("Clicked ont notif");
                    }
                });

        notif.showConfirm();
        
        }   
         refreshtable2(); 
       clear();
}
    public void clear()
     {
         titreAjout.clear();
         prixAjout.clear();
         quantiteAjout.clear();
         dateFront.setValue(null);
     
     }
    
    
    
    public void refreshtable() throws SQLException, SQLException, SQLException, SQLException{
        CRUDProduit   cp = new CRUDProduit();
        ran = cp.afficherProduit();
        ObservableList data = FXCollections.observableArrayList(ran);
        tableproduitFront.setItems(data);
    }
    
      private void affichertablePanier()  {
        
        Task<ArrayList<Panier>> task = new Task() {
        

            @Override
            protected Object call() throws SQLException {
                ran2 = (ArrayList<Panier>) new CRUDProduit().afficherProduitPanier();
                return ran2;
            }
        };

        task.setOnSucceeded((WorkerStateEvent e) -> {
            id1.setCellValueFactory(new PropertyValueFactory<>("id_produit"));
            titre1.setCellValueFactory(new PropertyValueFactory<>("nom"));
            prix1.setCellValueFactory(new PropertyValueFactory<>("prix"));
            quantite1.setCellValueFactory(new PropertyValueFactory<>("quantite"));
            tableprod1.setItems(FXCollections.observableArrayList(task.getValue()));
        });
        task.setOnFailed(e -> {
            affichertablePanier();
        });
        Thread th = new Thread(task);
        th.start();
    }
    
     public void refreshtable2() throws SQLException, SQLException, SQLException, SQLException{
        CRUDProduit   cp = new CRUDProduit();
        ran2 = cp.afficherProduitPanier();
        ObservableList data = FXCollections.observableArrayList(ran2);
        tableprod1.setItems(data);
    } 

    @FXML
    private void showonclickMod(MouseEvent event) {
        
       tableprod1.setOnMouseClicked(e -> {
            Panier s = (Panier) tableprod1.getSelectionModel().getSelectedItem();
             try {
                  ran2 = (ArrayList<Panier>) new CRUDProduit().afficherProduitPanier();
             } catch (SQLException ex) {
                 Logger.getLogger(GestionPanierCommandeController.class.getName()).log(Level.SEVERE, null, ex);
             }

            id_mod.setText(Integer.toString(s.getId_produit()));
            titre_mod.setText(s.getNom());
            prixprod_mod.setText(s.getPrix());
            quantite_mod.setText(Integer.toString(s.getQuantite()));
          
       });
    }
    
   
  
 

    
   
 private int prixtotal() throws SQLException{
     List<Panier> panier1 =new ArrayList<Panier>();
    int s1=0;
    CRUDFos_User US = new CRUDFos_User();
        Fos_User u1 = US.Get_Single_User(UserAccess.getUser_ID());
        idUser=u1.getId();
    
   
            Statement st=MyDB.getInstance().getConnexion().createStatement();
            String req="select prix , quantite from panier where id_client="+idUser+"";
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
              Panier p1 = new Panier(rs.getString(1), rs.getInt(2));
              panier1.add(p1);
            }
           
     
     for(Panier p2: panier1)
     {
         s1+=Integer.parseInt(p2.getPrix())*p2.getQuantite();
        
     }
     
     
     return s1;
 }
   
 
 @FXML
  private void calcul(ActionEvent event) throws SQLException {
           int s =prixtotal();
        prixTotal.setText(Float.toString(s));
  }
  
@FXML
    private void pdf(ActionEvent event) throws IOException, FileNotFoundException, SQLException, DocumentException, BadElementException, URISyntaxException {
        
        
              CRUDPanier outill = new CRUDPanier();
        this.genertePDFDoc(outill);
         
        
    }

    
     public void genertePDFDoc(CRUDPanier outil ) 
               throws FileNotFoundException, BadElementException, IOException, SQLException, DocumentException, URISyntaxException {
        Document document = new Document();

        FileChooser fileChooser = new FileChooser();
        document.addAuthor("*************** Client ***************");
        document.addTitle("Project Report");
        document.addCreationDate();
        //Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PDF (*.PDF, *.pdf)", "*.pdf", "*.PDF");
        fileChooser.getExtensionFilters().add(extFilter);
        Window chooserWindow = null;

        //Show save file dialog
        File file = fileChooser.showSaveDialog(chooserWindow);
        
        String query = "select * from `ecosystem`.`panier`";
        try {
            
            Connection con = MyDB.getInstance().getConnexion();
            ResultSet rs = con.createStatement().executeQuery(query);
            
            PdfWriter.getInstance(document, new FileOutputStream(file));
            document.open();
            Paragraph p1 = new Paragraph("\n\n\n\n");
            document.add(p1);
            
            Paragraph p2 = new Paragraph("\n\n\n\n Client" + "\n" + "---  Liste des Produits  Commandés ---" );
            document.add(p2);
            
            Paragraph p3 = new Paragraph("\n\n");
            document.add(p3);
            
            
            
            PdfPTable my_report_table = new PdfPTable(4);
            //create a cell object
            PdfPCell table_cell;
            
            
            String ref_outil = ("Reference Produit");
            table_cell=new PdfPCell(new Phrase(ref_outil));
            my_report_table.addCell(table_cell);
            
            String panier_outil = (" nom produit");
            table_cell=new PdfPCell(new Phrase(panier_outil));
            my_report_table.addCell(table_cell);
            
            String Nom_outil =("prix Produit");
            table_cell=new PdfPCell(new Phrase(Nom_outil));
            my_report_table.addCell(table_cell);
                
            String produit_outil = ("quantite Produit");
            table_cell=new PdfPCell(new Phrase(produit_outil));
            my_report_table.addCell(table_cell);
               
          
          
            
            
while( rs.next() ){
               
              
    
                String id_outil = rs.getString("id");
                table_cell=new PdfPCell(new Phrase(id_outil));
                my_report_table.addCell(table_cell);
                
               
                String nom_outil = rs.getString("nom");
                table_cell=new PdfPCell(new Phrase(nom_outil));
                my_report_table.addCell(table_cell);
                
                
                
                String prix_outil=rs.getString("prix");
                table_cell=new PdfPCell(new Phrase(prix_outil));
                my_report_table.addCell(table_cell);
                         
               String quantite_outil=rs.getString("quantite");
                table_cell=new PdfPCell(new Phrase(quantite_outil));
                my_report_table.addCell(table_cell);
                         
                                    
            }
/* Attach report table to PDF */
                     
                    document.add(my_report_table);                       
                    document.close();
           
            
        } catch (FileNotFoundException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
    } catch (DocumentException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
    }   catch (SQLException ex) {
            Logger.getLogger(Panier.class.getName()).log(Level.SEVERE, null, ex);
        }
        document.close();
    
    }  
     
  private void setid() throws SQLException{
         CRUDFos_User US = new CRUDFos_User();
        Fos_User u1 = US.Get_Single_User(UserAccess.getUser_ID());
        idUser=u1.getId();
        
        maill=u1.getEmail();
        
      //mail.setText(maill);
  }
  @FXML
    private void btnres(ActionEvent event) throws SQLException {
        
        n=Integer.parseInt(quantite_mod.getText());
        //n2=Integer.parseInt(quantiteStock.getText());
        if((5-n)==0){
        
          Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Pas de stocks");
            alert1.setHeaderText(null);
            alert1.setContentText("Sold out !");
            alert1.showAndWait();
        
        }else if ((5-n)<0){
         Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Pas de stocks");
            alert1.setHeaderText(null);
            alert1.setContentText("Malheureusement , la quantite de produit est insuffisant !");
            alert1.showAndWait();
        }
        else{
            
             Panier s = new Panier(Integer.parseInt(id_mod.getText()),titre_mod.getText(), prixprod_mod.getText(),Integer.parseInt(quantite_mod.getText()));
             
            CRUDPanier cr = new CRUDPanier();
            cr.modifierPanierNom(s);
            
             Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("Réservation avec succée");
            alert1.setHeaderText(null);
            alert1.setContentText("Votre validation est atteinte avec succée ! Vérifier votre e-mail svp !");
            alert1.showAndWait();
            
        
       try {
            String host = "smtp.gmail.com";
            String user = "iteb.fatnassi@esprit.tn";
            String pass = "24121997";
            String to = "fatnassiiteb@gmail.com";
            String from = "iteb.fatnassi@esprit.tn";
            String subject = "C'est un mail pour la validation de votre Panier.";
            String messageText = "Confirmation de votre Panier de Part de Site EcoSystem";
            boolean sessionDebug = false;

            Properties props = System.getProperties();

            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.required", "true");
            props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

            java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            Session mailSession = Session.getDefaultInstance(props, null);
            mailSession.setDebug(sessionDebug);
            Message msg = new MimeMessage(mailSession);
            msg.setFrom(new InternetAddress(from));
            InternetAddress[] address = {new InternetAddress(to)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(subject);
            msg.setText(messageText);
            Transport transport = mailSession.getTransport("smtp");
            transport.connect(host, user, pass);
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();
            System.out.println("message send successfully");
        } catch (Exception ex) {
            System.out.println(ex);
        }    
       
    }
  
    }

    
    
    
    
    
    @FXML
    private void remise(ActionEvent event) throws SQLException {
        
        int s =prixtotal();
        int x ;
        
        if(s<= 400)
        {
            x=30;
        }
        else if( s<= 900)
        {
            x=40;
        }
        else if(s<=1250)
        {
            x=50;
        }
        else 
        {
            x=70;
        }
        remiseP.setText(Float.toString(x));
        
        int AfterRemise ;
        
        AfterRemise =s*x/100;
        
        apresRemise.setText(Float.toString(AfterRemise));
    }
    
   
  
  
  
    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.GestionProduit.service;


import edu.GestionProduit.entities.produit2;
import edu.GestionProduit.tools.MyConnection;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javax.imageio.ImageIO;
import org.apache.commons.lang3.RandomStringUtils;

/**
 *
 * @author Abir
 */
public class CrudProduit {
    Connection cnx = MyConnection.getInstance().getCnx();
   
    
    
    public void ajouterProduit(produit2 p) throws SQLException{
        
        String req = "INSERT INTO `produit2`(`titre`, `description`, `publie`, `prix`, `image`, `rating`, `id_categorie`, `id_user`, `id_SousCategorie`)"
                + " VALUES (?,?,?,?,?,?,?,?,?)";
        PreparedStatement stm = cnx.prepareStatement(req);
       
        
        stm.setString(1, p.getTitre());
        stm.setString(2,p.getDescription());
        stm.setInt(3, p.getPublie());
        stm.setInt(4, p.getPrix());
        stm.setString(5, p.getImage());
        stm.setInt(6, p.getRating());
        stm.setInt(7, p.getId_categorie());
        stm.setInt(8, p.getId_user());
        stm.setInt(9, p.getId_SousCategorie());
        stm.executeUpdate();
                
    }
    
     public ObservableList<produit2> getAllProduit2() throws SQLException{
        ObservableList<produit2> l = FXCollections.observableArrayList();
        Statement st = cnx.createStatement();
        String req = "SELECT * FROM produit2";
        
        ResultSet rs = st.executeQuery(req);
        
        while(rs.next()){
            produit2 p = new produit2();
            p.setId(rs.getInt(1));
            p.setTitre(rs.getString(4));
            p.setDescription(rs.getString(5));
            p.setPublie(rs.getInt(6));
            p.setImage(rs.getString(8));
            p.setPrix(rs.getInt(7));
            p.setRating(rs.getInt(9));
        
            l.add(p);
        }
        return l;
        
    }
       public void SupprimerProduit(int id) throws SQLException {
        
            Statement st = cnx.createStatement();
            String req = "delete from produit2 where id=" + id;
            st.executeUpdate(req);
            
    }
      public void updateProduit(produit2 p) throws SQLException{
        Statement st = cnx.createStatement();
        String req = "UPDATE `produit2` SET `titre`=?,`description`=?,`publie`=?,"
                + "`prix`=?,`image`=?,`rating`=?,`id_categorie`=?,"
                + "`id_user`=?,`id_SousCategorie`=? WHERE `id`=?";
        PreparedStatement ste = cnx.prepareStatement(req);
       
        
        ste.setString(1, p.getTitre());
        ste.setString(2,p.getDescription());
        ste.setInt(3, p.getPublie());
        ste.setInt(4,p.getPrix());
        ste.setString(5,p.getImage());
        ste.setInt(6, p.getRating());
        ste.setInt(7,p.getId_categorie());
        ste.setInt(8,p.getId_user());
        ste.setInt(9,p.getId_SousCategorie());
        ste.setInt(10,p.getId());
        
        System.out.println(ste);
        ste.executeUpdate();
                
    }
      
      public void publierProduit(int idProduit) throws SQLException{
        String req = "UPDATE `produit2` SET `publie`=1  WHERE `id`=?";
        PreparedStatement ste = cnx.prepareStatement(req); 
        ste.setInt(1, idProduit);
        System.out.println(ste);
        ste.executeUpdate();
                
    }
      
       public ObservableList<produit2> rechercherProduit(String toSearch) throws SQLException{
        ObservableList<produit2> l = FXCollections.observableArrayList();
        Statement st = cnx.createStatement();
        String req = "SELECT * FROM produit2 WHERE titre = '"+toSearch+"'";
        System.out.println(req);
        ResultSet rs = st.executeQuery(req);
        
        while(rs.next()){
            produit2 p = new produit2();
            p.setId(rs.getInt(1));
            p.setTitre(rs.getString(4));
            p.setDescription(rs.getString(5));
            p.setPublie(rs.getInt(6));
            p.setImage(rs.getString(8));
            
            l.add(p);
        }
        return l;
        
    }
        
    
        

    }
       
       
   

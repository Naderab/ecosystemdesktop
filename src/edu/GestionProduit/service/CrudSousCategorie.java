/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.GestionProduit.service;

import edu.GestionProduit.entities.sous_categorie;
import edu.GestionProduit.tools.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Abir
 */
public class CrudSousCategorie {
    

    Connection cnx = MyConnection.getInstance().getCnx();


    
    public ObservableList<sous_categorie> getAllSousCategorie(int idC) throws SQLException{
        ObservableList<sous_categorie> l = FXCollections.observableArrayList();
        Statement st = cnx.createStatement();
        String req = "SELECT sc.id,sc.lib FROM `sous_categorie` as sc,categorie as c "
                + "WHERE sc.idCategorie = c.id AND c.id = "+idC;
        ResultSet rs = st.executeQuery(req);
        
        while(rs.next()){
            sous_categorie c = new sous_categorie();
            c.setId(rs.getInt(1));
            c.setLib(rs.getString(2));
          
            
            l.add(c);
        }
        return l;
        
    }
    
}


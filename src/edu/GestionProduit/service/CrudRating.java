/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.GestionProduit.service;

import edu.GestionProduit.tools.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Abir
 */
public class CrudRating {
    
 Connection cnx = MyConnection.getInstance().getCnx();
    PreparedStatement pst;
    ResultSet rs;

    public void ajouterRating(RatingEntity r) {
        try {
            String requete = "INSERT INTO rating (rating,id_user,id_produit) VALUES (?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
          //pst.setInt(1,r.getId() );
            //pst.setInt(1, r.getRating());
            //pst.setInt(2, r.getId_user());
            //pst.setInt(3, r.getId_produit());
           
            pst.executeUpdate();
            System.out.println("Rating ajouté");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    private static class RatingEntity {

        public RatingEntity() {
        }

      
       
    }
    
    
    
}
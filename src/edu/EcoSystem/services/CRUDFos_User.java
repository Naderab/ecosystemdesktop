/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.EcoSystem.services;


import edu.EcoSystem.entities.Fos_User;
import java.sql.Connection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import edu.EcoSystem.tools.MyDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author MEGAJAS
 */
public class CRUDFos_User {
    
    
     Connection cnx;
    ObservableList<Fos_User> oblist = FXCollections.observableArrayList();

    /**
     *
     */
    public CRUDFos_User() throws SQLException {
        this.cnx = MyDB.getInstance().getConnexion();

    }
    
    
 public Fos_User User_Access(Fos_User  u) throws SQLException {
        String query = "SELECT * FROM fos_user WHERE username= ? and password = ?";
        // create the mysql insert preparedstatement
        PreparedStatement preparedStmt = cnx.prepareStatement(query);
        preparedStmt.setString(1, u.getUsername());

        preparedStmt.setString(2, u.getPassword());

        ResultSet rs = preparedStmt.executeQuery();
        while (rs.next()) {

            Fos_User user_found = new Fos_User();
            

            user_found.setRoles(rs.getString("roles"));
            user_found.setId(rs.getInt("id"));
            user_found.setUsername(rs.getString("username"));

            return user_found;

        }
        return null;
    }
 public   Fos_User Get_Single_User(int ID) throws SQLException {
        String query = "SELECT * FROM fos_user WHERE id = ?";
        PreparedStatement preparedStmt = cnx.prepareStatement(query);
        preparedStmt.setInt(1, ID);

        ResultSet rs = preparedStmt.executeQuery();
        while (rs.next()) {

             Fos_User user_found = new  Fos_User();

            user_found.setId(rs.getInt("id"));
            user_found.setUsername(rs.getString("username"));
            user_found.setUsername_canonical(rs.getString("username_canonical"));
            user_found.setEmail(rs.getString("email"));
            user_found.setEmail_canonical(rs.getString("email_canonical"));
            user_found.setEnabled(rs.getInt("enabled"));
            user_found.setSalt(rs.getString("salt"));
            user_found.setPassword(rs.getString("password"));
            user_found.setConfirmation_token(rs.getString("confirmation_token"));
            user_found.setUsername(rs.getString("username"));
            user_found.setRoles(rs.getString("roles"));
            user_found.setNom(rs.getString("nom"));
            return user_found;

        }
        return null;
    }
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
    
    
    
}

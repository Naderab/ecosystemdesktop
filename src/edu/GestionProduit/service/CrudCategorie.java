/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.GestionProduit.service;

import edu.GestionProduit.entities.categorie;

import edu.GestionProduit.tools.MyConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Abir
 */
public class CrudCategorie {

    Connection cnx = MyConnection.getInstance().getCnx();

    
    public ObservableList<categorie> getAllCategorie() throws SQLException{
        ObservableList<categorie> l = FXCollections.observableArrayList();
        Statement st = cnx.createStatement();
        String req = "SELECT * FROM `categorie`";
        ResultSet rs = st.executeQuery(req);
        
        while(rs.next()){
            categorie c = new categorie();
            c.setId(rs.getInt(1));
            c.setLibelle(rs.getString(2));
            c.setId_SousCategorie(rs.getInt(3));
            
            l.add(c);
        }
        return l;
        
    }
    public ArrayList<categorie> Filtercategorie(ArrayList<String> categorie) throws SQLException {
        ArrayList<categorie> newlist = new ArrayList<>();
        String sql = "SELECT * FROM categorie where flag=1";
        int i = 1;
        for (String th : categorie) {
            if (i == 1) {
                sql = sql + " and (theme like ?";
                i++;
                System.out.println(sql);
            } else {
                sql = sql + " or theme like ?";
                i++;
                System.out.println(sql);
            }
        }
        int k = 1;
        if (i > 1) {
            sql += ")";
        }
       
        if (k > 1) {
            sql += " )";
        }

        PreparedStatement statement = cnx.prepareStatement(sql);
        int j = 0;
        for (String s : categorie) {
            j++;
            statement.setString(j, s);
            System.out.println(s);
        }
       
        

        return newlist;
    }
}



   

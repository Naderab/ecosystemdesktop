/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.EcoSystem.services;

import edu.EcoSystem.entities.Evenement;
import edu.EcoSystem.entities.Participation;
import edu.EcoSystem.entities.Wishlist;
import edu.EcoSystem.tools.MyConnection;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Nader
 */
public class WishlistCRUD {

    public void addWishlist(Evenement event, int idUser) throws IOException {
        try {

            String requete
                    = "INSERT INTO wishlist (id_user,id_event) VALUES (?,?)";
            PreparedStatement st = MyConnection.getInstance().getCnx().prepareStatement(requete);
            st.setInt(1, idUser);
            st.setInt(2, event.getId_event());

            st.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }

    }

    public void deleteEventFromWishlist(int idEvent,int idUser) throws SQLException {
        String req = "DELETE FROM wishlist WHERE id_event='" + idEvent + "' AND id_user='"+ idUser +"'";
        PreparedStatement ste = MyConnection.getInstance().getCnx().prepareStatement(req);
        ste.executeUpdate();
    }

    public ObservableList<Wishlist> displayWishlist(int iduser) {
        ObservableList<Wishlist> myList = FXCollections.observableArrayList();
        try {
            String req = "SELECT * FROM wishlist WHERE id_user='"+ iduser +"'";
            Statement pst = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = pst.executeQuery(req);
            while (rs.next()) {
                Wishlist w = new Wishlist();
                w.setId_user(rs.getInt("id"));
                w.setIdEvent(rs.getInt("id_event"));
                w.setId_user(rs.getInt("id_user"));

                myList.add(w);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
        return myList;
    }
    
    public boolean testlike(int id,int idev) throws SQLException {
         int s=0;
        
        String query = "SELECT COUNT(*) as total FROM wishlist where id_user= ? and id_event= ?";
        PreparedStatement st = MyConnection.getInstance().getCnx().prepareStatement(query);
        st.setInt(1, id);
        st.setInt(2,idev);
        ResultSet rs = st.executeQuery();

       while (rs.next()) {
                s = rs.getInt("total");
 
            
        }
        System.out.println("aaaaaaaaaaa"+s);
        if (s>0) {
            return true;
        } else {
            return false;
        }
    }

}

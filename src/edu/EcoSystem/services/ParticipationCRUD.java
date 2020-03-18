/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.EcoSystem.services;

import edu.EcoSystem.entities.Evenement;
import edu.EcoSystem.entities.Participation;
import edu.EcoSystem.tools.MyConnection;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Nader
 */
public class ParticipationCRUD {
    
    public void addParticipation(Evenement event,int nbPlace,int idUser) throws IOException {
        try {

            String requete
                    = "INSERT INTO participation (id_user,id_event,nbPlace) VALUES (?,?,?)";
            PreparedStatement st = MyConnection.getInstance().getCnx().prepareStatement(requete);
            st.setInt(1, idUser);
            st.setInt(2, event.getId_event());
            st.setInt(3, nbPlace);            
            st.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }

    }
    public ObservableList<Participation> displayParticipation() {
        ObservableList<Participation> myList = FXCollections.observableArrayList();
        try {
            String req = "SELECT * FROM participation";
            String reqEvent = "SELECT nom,date_fin,date_debut FROM evenement where id=?";
            String reqUser = "SELECT username,email FROM fos_user where id=?";
            PreparedStatement psEvent=MyConnection.getInstance().getCnx().prepareStatement(reqEvent);
            PreparedStatement psUser=MyConnection.getInstance().getCnx().prepareStatement(reqUser);

            Statement pst = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = pst.executeQuery(req);
            while (rs.next()) {
                Participation p = new Participation();               
                psEvent.setInt(1, rs.getInt("id_event"));
                psUser.setInt(1, rs.getInt("id_user"));               
                ResultSet rsUser = psUser.executeQuery();
                
                ResultSet rsEvent = psEvent.executeQuery();
                while(rsUser.next())
                {
                                    p.setNomUser(rsUser.getString("username"));
                                    p.setEmail(rsUser.getString("email"));

                }
                while(rsEvent.next())
                {
                                    p.setNomEvent(rsEvent.getString("nom"));
                                    p.setDateFin(rsEvent.getDate("date_fin"));
                                    p.setDateDebut(rsEvent.getDate("date_debut"));

                }
                p.setNbPlace(rs.getInt("nbPlace"));
                p.setId_user(rs.getInt("id_user"));
                p.setIdEvent(rs.getInt("id_event"));
                p.setId_participation(rs.getInt("id"));
                myList.add(p);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
        return myList;
    }
    public boolean getParticip(int id,int idUser)
    {
        boolean test = false;
        List<Integer> l=new ArrayList<Integer>();
        try {
            PreparedStatement myStm = MyConnection.getInstance().getCnx().prepareStatement("SELECT * from participation where id_event= ? and id_user= ?");
            myStm.setInt(1, id);
            myStm.setInt(2, idUser);
            ResultSet myRes = myStm.executeQuery();
            while(myRes.next())
            {
                l.add(myRes.getInt("id_user"));
           
            }
            if(l.size()>0){
                test = true;
            }
            else{
                test = false;
            }
            return test;
        } catch(Exception e) {
            e.printStackTrace();
        }
        return test;
    }
    
    
}
